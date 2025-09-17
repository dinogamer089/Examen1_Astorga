package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.helper.UnidadAprendizajeHelper;

import java.io.Serializable;
import java.util.List;

@Named("unidadUI")
@SessionScoped
public class UnidadAprendizajeBeanUI implements Serializable{

    private UnidadAprendizaje unidad;
    private List<UnidadAprendizaje> unidades;
    private UnidadAprendizajeHelper unidadHelper;

    public UnidadAprendizajeBeanUI() {
        unidadHelper = new UnidadAprendizajeHelper();
    }
    private UnidadAprendizaje seleccionada;

    @PostConstruct
    public void init() {
        unidad = new UnidadAprendizaje();
        unidades = unidadHelper.obtenerTodas();
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
    public List<UnidadAprendizaje> getUnidades() {
        return unidades;
    }
    public void mostrarUnidades() {
        unidades = unidadHelper.obtenerTodas();  // Reload the list when "Mostrar" is clicked
    }
    public void seleccionar(UnidadAprendizaje uda) {
        this.seleccionada = uda;
        System.out.println("Seleccionada: " + uda.getNombre());
    }

    public UnidadAprendizaje getSeleccionada() {
        return seleccionada;
    }
    public void eliminar() {
        if (seleccionada != null) {
            try {
                unidadHelper.eliminarUnidadAprendizaje(seleccionada);  // Assuming you have a helper method to do this
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito", "Unidad de Aprendizaje eliminada correctamente"));
                // Optionally, clear the selected item after deleting
                seleccionada = null;
                unidades = unidadHelper.obtenerTodas();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar la unidad de aprendizaje"));
            }
        }
    }

    public void editar() {
        try {
            if (seleccionada != null) {
                unidadHelper.editarUnidadAprendizaje(seleccionada);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Unidad de Aprendizaje modificada correctamente"));
                seleccionada = null; // limpiar selección
                unidades = unidadHelper.obtenerTodas(); // recargar lista
            }
        } catch (IllegalArgumentException ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", ex.getMessage()));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo modificar la Unidad de Aprendizaje"));
        }
    }



}
