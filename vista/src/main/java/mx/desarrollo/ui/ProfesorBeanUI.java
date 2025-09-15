package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.avanti.desarrollo.entity.Profesor;
import mx.desarrollo.helper.ProfesorHelper;

import java.io.Serializable;

@Named("profesorUI")
@SessionScoped
public class ProfesorBeanUI implements Serializable {

    private Profesor profesor;
    private ProfesorHelper profesorHelper;

    public ProfesorBeanUI() {
        profesorHelper = new ProfesorHelper();
    }

    @PostConstruct
    public void init() {
        profesor = new Profesor();
    }

    public void guardar() {
        try {
            profesorHelper.guardarProfesor(profesor);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Profesor registrado correctamente"));
            profesor = new Profesor(); // limpiar
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", ex.getMessage()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar al profesor"));
        }
    }

    public void cancelar() {
        profesor = new Profesor();
    }

    // Getters y setters
    public Profesor getProfesor() { return profesor; }
    public void setProfesor(Profesor profesor) { this.profesor = profesor; }
}