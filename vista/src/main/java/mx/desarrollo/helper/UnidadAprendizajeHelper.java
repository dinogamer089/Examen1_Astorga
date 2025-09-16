package mx.desarrollo.helper;

import jakarta.persistence.EntityManager;
import mx.avanti.desarrollo.dao.UnidadAprendizajeDAO;
import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.avanti.desarrollo.persistence.HibernateUtil;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.util.List;

public class UnidadAprendizajeHelper {

    private UnidadAprendizajeDAO unidadAprendizajeDAO;

    private static EntityManager getEntityManager(){
        return HibernateUtil.getEntityManager();
    }

    public UnidadAprendizajeHelper() {
        this.unidadAprendizajeDAO = new UnidadAprendizajeDAO(getEntityManager()); // Make sure this is properly instantiated
    }

    // Method to fetch all Unidades de Aprendizaje from the database
    public List<UnidadAprendizaje> obtenerTodas() {
        return unidadAprendizajeDAO.obtenerTodos();  // Call to the DAO method that fetches data
    }

    public void guardarUnidadAprendizaje(UnidadAprendizaje unidad) {
        // Se normaliza el nombre quitando espacios y pasando a mayusculas
        if (unidad.getNombre() != null) {
            unidad.setNombre(unidad.getNombre().trim().toUpperCase());
        }
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().registrarUnidadAprendizaje(unidad);
    }
}
