package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import mx.avanti.desarrollo.entity.Imparte;
import mx.avanti.desarrollo.entity.Profesor;
import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.avanti.desarrollo.entity.Dia;
import mx.avanti.desarrollo.entity.Tipo;
import mx.desarrollo.helper.ImparteHelper;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Named("imparteBeanUI")
@ViewScoped
public class ImparteBeanUI implements Serializable {

    private Integer profesorId;
    private Integer unidadId;
    private Tipo tipo = Tipo.Clase;
    private Dia dia = Dia.Lunes;
    private Integer horaInicio = 7;
    private Integer horaFin = 8;

    private int horasRestantes = 0; // 🔹 NUEVO

    private List<Profesor> profesores;
    private List<UnidadAprendizaje> unidades;
    private List<Imparte> asignacionesProfesor;

    private final ImparteHelper helper = new ImparteHelper();

    @PostConstruct
    public void init() {
        profesores = helper.listarProfesores();
        unidades = helper.listarUnidades();
        asignacionesProfesor = new ArrayList<>();
        recalcularDisponibles();
    }

    // Metodo cuando se cambia de profesor
    public void onProfesorChange() {
        asignacionesProfesor = (profesorId == null) ? new ArrayList<>() : helper.listarDeProfesor(profesorId);
        recalcularDisponibles();
    }

    // Calcular horas restantes
    public void recalcularDisponibles() {
        if (profesorId == null || unidadId == null || tipo == null) {
            horasRestantes = 0;
            return;
        }
        UnidadAprendizaje ua = unidades.stream()
                .filter(u -> Objects.equals(u.getIdUA(), unidadId))
                .findFirst().orElse(null);
        if (ua == null) { horasRestantes = 0; return; }

        int limite = switch (tipo) {
            case Clase -> ua.getHorasClase();
            case Taller -> ua.getHorasTaller();
            case Laboratorio -> ua.getHorasLaboratorio();
        };

        int asignadas = asignacionesProfesor.stream()
                .filter(a -> Objects.equals(a.getUnidadAprendizaje().getIdUA(), unidadId))
                .filter(a -> a.getTipo() == tipo)
                .mapToInt(a -> a.getHoraFin().getHour() - a.getHoraInicio().getHour())
                .sum();

        horasRestantes = Math.max(0, limite - asignadas);

        // Ajuste de horaFin si sobrepasa
        int maxFin = getMaxHoraFin();
        if (horaFin < horaInicio + 1) horaFin = horaInicio + 1;
        if (horaFin > maxFin) horaFin = maxFin;
    }

    public int getHorasRestantes() { return Math.max(horasRestantes, 0); }

    public int getMaxHoraFin() {
        // Fin máximo = inicio + horasRestantes (al menos inicio+1), y nunca mayor a 24
        int maxByRemaining = horaInicio + getHorasRestantes();
        int minAllowed = horaInicio + 1;
        return Math.max(minAllowed, Math.min(24, maxByRemaining));
    }

    public void agregarClase() {
        if (profesorId == null || unidadId == null) {
            addMsg(FacesMessage.SEVERITY_WARN, "Selecciona profesor y unidad");
            return;
        }
        if (horaInicio >= horaFin) {
            addMsg(FacesMessage.SEVERITY_WARN, "Hora inicio debe ser menor que hora fin");
            return;
        }

        Imparte imparte = new Imparte();
        Profesor p = new Profesor(); p.setIdProfesor(profesorId);
        UnidadAprendizaje ua = new UnidadAprendizaje(); ua.setIdUA(unidadId);

        imparte.setProfesor(p);
        imparte.setUnidadAprendizaje(ua);
        imparte.setTipo(tipo);
        imparte.setDia(dia);
        imparte.setHoraInicio(LocalTime.of(horaInicio, 0));
        imparte.setHoraFin(LocalTime.of(horaFin, 0));

        try {
            helper.asignar(imparte);
            asignacionesProfesor.add(imparte);

            // Recalcular restantes
            recalcularDisponibles();
            addMsg(FacesMessage.SEVERITY_INFO,
                    "Clase asignada exitosamente. Quedan " + getHorasRestantes() +
                            " h de " + tipo + " en la unidad seleccionada");

        } catch (Exception e) {
            addMsg(FacesMessage.SEVERITY_ERROR, e.getMessage());
        }
    }

    public List<Imparte> asignacionesDia(Dia d) {
        if (profesorId == null || unidadId == null) return List.of();
        return asignacionesProfesor.stream()
                .filter(a -> Objects.equals(a.getUnidadAprendizaje().getIdUA(), unidadId))
                .filter(a -> a.getDia() == d)
                .collect(Collectors.toList());
    }

    private void addMsg(FacesMessage.Severity s, String m) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(s, m, m));
    }

    // Valores para los combobox
    public Dia[] getDias() { return Dia.values(); }
    public Tipo[] getTipos() { return Tipo.values(); }

    // AGetters y setters
    public Integer getProfesorId() { return profesorId; }
    public void setProfesorId(Integer profesorId) { this.profesorId = profesorId; }
    public Integer getUnidadId() { return unidadId; }
    public void setUnidadId(Integer unidadId) { this.unidadId = unidadId; }
    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }
    public Dia getDia() { return dia; }
    public void setDia(Dia dia) { this.dia = dia; }
    public Integer getHoraInicio() { return horaInicio; }
    public void setHoraInicio(Integer horaInicio) { this.horaInicio = horaInicio; }
    public Integer getHoraFin() { return horaFin; }
    public void setHoraFin(Integer horaFin) { this.horaFin = horaFin; }

    public List<Profesor> getProfesores() { return profesores; }
    public List<UnidadAprendizaje> getUnidades() { return unidades; }
    public List<Imparte> getAsignacionesProfesor() { return asignacionesProfesor; }
}
