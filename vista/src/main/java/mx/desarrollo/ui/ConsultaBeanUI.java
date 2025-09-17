package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import mx.avanti.desarrollo.entity.Dia;
import mx.avanti.desarrollo.entity.Imparte;
import mx.avanti.desarrollo.entity.Profesor;
import mx.desarrollo.helper.ImparteHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Named("consultaUI")
@ViewScoped
public class ConsultaBeanUI implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ConsultaBeanUI.class.getName());

    private List<Profesor> listaProfesores = new ArrayList<>();
    private Profesor profesorSeleccionado;
    private List<Imparte> asignaciones = new ArrayList<>();

    private final ImparteHelper helper = new ImparteHelper();

    @PostConstruct
    public void init() {
        try {
            listaProfesores = helper.listarProfesores();
            if (listaProfesores == null) listaProfesores = new ArrayList<>();

            // Ordenar alfabéticamente por nombre
            listaProfesores.sort((p1, p2) -> p1.getNombre().compareToIgnoreCase(p2.getNombre()));

            LOG.info("ConsultaBeanUI.init: profesores cargados = " + listaProfesores.size());

        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al inicializar ConsultaBeanUI", e);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inicializando vista", e.getMessage()));
        }
    }


    // Método invocado desde el h:commandLink (al hacer clic en un profesor)
    public void setProfesorSeleccionado(Profesor profesor) {
        try {
            this.profesorSeleccionado = profesor;
            if (profesor != null && profesor.getIdProfesor() != null) {
                asignaciones = helper.listarDeProfesor(profesor.getIdProfesor());
                if (asignaciones == null) asignaciones = new ArrayList<>();
                LOG.info("Profesor seleccionado id=" + profesor.getIdProfesor() + " -> asignaciones=" + asignaciones.size());
            } else {
                asignaciones = new ArrayList<>();
                LOG.info("Profesor seleccionado nulo o sin id");
            }
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, "Error al cargar asignaciones", ex);
            FacesContext.getCurrentInstance()
                    .addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error cargando asignaciones", ex.getMessage()));
            asignaciones = new ArrayList<>();
        }
    }

    // Filtrado por día
    private List<Imparte> filtrarPorDia(Dia dia) {
        if (asignaciones == null) return List.of();
        return asignaciones.stream()
                .filter(a -> a.getDia() == dia)
                .collect(Collectors.toList());
    }


    public List<Imparte> getHorarioLunes() { return filtrarPorDia(Dia.Lunes); }
    public List<Imparte> getHorarioMartes() { return filtrarPorDia(Dia.Martes); }
    public List<Imparte> getHorarioMiercoles() { return filtrarPorDia(Dia.Miercoles); }
    public List<Imparte> getHorarioJueves() { return filtrarPorDia(Dia.Jueves); }
    public List<Imparte> getHorarioViernes() { return filtrarPorDia(Dia.Viernes); }

    // Getters y setters
    public List<Profesor> getListaProfesores() { return listaProfesores; }
    public Profesor getProfesorSeleccionado() { return profesorSeleccionado; }
}
