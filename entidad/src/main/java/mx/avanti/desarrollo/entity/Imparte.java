package mx.avanti.desarrollo.entity;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "imparte")
public class Imparte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idimparte")
    private Integer idImparte;

    @ManyToOne
    @JoinColumn(name = "idprofesor", nullable = false)
    private Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "idUA", nullable = false)
    private UnidadAprendizaje unidadAprendizaje;

    @Enumerated(EnumType.STRING)
    @Column(name = "dia", nullable = false, length = 15)
    private Dia dia;

    @Column(name = "horaInicio", nullable = false)
    private LocalTime horaInicio;

    @Column(name = "horaFin", nullable = false)
    private LocalTime horaFin;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", nullable = false, length = 20)
    private Tipo tipo;

    // Getters y setters
    public Integer getIdImparte() { return idImparte; }
    public void setIdImparte(Integer idImparte) { this.idImparte = idImparte; }

    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }

    public UnidadAprendizaje getUnidadAprendizaje() { return unidadAprendizaje; }
    public void setUnidadAprendizaje(UnidadAprendizaje unidadAprendizaje) { this.unidadAprendizaje = unidadAprendizaje; }

    public Dia getDia() { return dia; }
    public void setDia(Dia dia) { this.dia = dia; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public Tipo getTipo() { return tipo; }
    public void setTipo(Tipo tipo) { this.tipo = tipo; }
}
