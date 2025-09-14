package mx.avanti.desarrollo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "profesor")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idprofesor", nullable = false)
    private Integer idProfesor;

    @Size(max = 50)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Size(max = 50)
    @NotNull
    @Column(name = "apellido_paterno", nullable = false, length = 50)
    private String apellidoPaterno;

    @Size(max = 50)
    @Column(name = "apellido_materno", length = 50)
    private String apellidoMaterno;

    /**
     * RFC: acepta formatos de 12 o 13 caracteres
     * Patron: 3 o 4 letras (A-Z, Ñ, &), 6 digitos fecha AAMMDD, 3 caracteres homoclave alfanumericos
     * Se van segmentando las restricciones por el numero de caracter
     */
    @NotNull
    @Size(max = 13)
    @Pattern(regexp = "^[A-ZÑ&]{3,4}\\d{6}[A-Z0-9]{3}$", message = "RFC invalido")
    @Column(name = "rfc", nullable = false, unique = true, length = 13)
    private String rfc;

    // Getters y setters
    public Integer getIdProfesor() { return idProfesor; }
    public void setIdProfesor(Integer idProfesor) { this.idProfesor = idProfesor; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }

    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }

    public String getRfc() { return rfc; }
    public void setRfc(String rfc) {
        if (rfc != null) {
            this.rfc = rfc.trim().toUpperCase();
        } else {
            this.rfc = null;
        }
    }
}