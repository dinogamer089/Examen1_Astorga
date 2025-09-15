package mx.avanti.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "unidad_aprendizaje")
public class UnidadAprendizaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUA", nullable = false)
    private Integer idUA;

    @Size(max = 100)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Size(max = 10)
    @NotNull
    @Column(name = "horasclase", nullable = false, length = 10)
    private Integer horasclase;

    @Size(max = 10)
    @NotNull
    @Column(name = "horaslaboratorio", nullable = false, length = 10)
    private Integer horaslaboratorio;

    @Size(max = 10)
    @NotNull
    @Column(name = "horastaller", nullable = false, length = 10)
    private Integer horastaller;


    //setters y getters
    public Integer getIdUA(){ return idUA;}
    public void setIdUA(Integer idUA){ this. idUA = idUA;}

    public String getNombre(){ return nombre; }
    public void setNombre(String nombre){this.nombre = nombre;}

    public Integer getHorasClase(){ return horasclase;}
    public void setHorasClase(Integer horasclase){ this.horasclase = horasclase;}

    public Integer getHorasLaboratorio(){ return horaslaboratorio;}
    public void setHorasLaboratorio(Integer horaslaboratorio){ this.horaslaboratorio = horaslaboratorio;}

    public Integer getHorasTaller(){ return horastaller;}
    public void setHorasTaller(Integer horastaller){ this.horastaller = horastaller;}
}
