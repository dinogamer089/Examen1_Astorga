package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.helper.UnidadAprendizajeHelper;

import java.io.Serializable;

@Named("unidadUI")
@SessionScoped
public class UnidadAprendizajeBeanUI implements Serializable{

    private UnidadAprendizaje unidad;
    private UnidadAprendizajeHelper unidadHelper;

    public UnidadAprendizajeBeanUI() {
        unidadHelper = new UnidadAprendizajeHelper();
    }

    @PostConstruct
    public void init() {
        unidad = new UnidadAprendizaje();
    }

    public void guardar() {
        try {
            unidadHelper.guardarUnidadAprendizaje(unidad);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Unidad de Aprendizaje registrada correctamente"));
            unidad = new UnidadAprendizaje(); // limpiar
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", ex.getMessage()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo registrar la Unidad de Aprendizaje"));
        }
    }

    public void cancelar() {
        unidad = new UnidadAprendizaje();
    }

    // Getters y setters
    public UnidadAprendizaje getUnidad() { return unidad; }
    public void setUnidad(UnidadAprendizaje unidad) { this.unidad = unidad; }
}
