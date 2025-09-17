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

    public void eliminarUnidadAprendizaje(UnidadAprendizaje unidad) {
        EntityManager em = HibernateUtil.getEntityManager(); // Get your EntityManager
        try {
            em.getTransaction().begin();
            em.remove(em.contains(unidad) ? unidad : em.merge(unidad));  // Ensure the entity is managed before removing
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;  // Rethrow or handle appropriately
        } finally {
            em.close();
        }
    }

    public void editarUnidadAprendizaje(UnidadAprendizaje unidad) {
        if (unidad.getNombre() != null) {
            unidad.setNombre(unidad.getNombre().toUpperCase());
        }
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().modificarUnidadAprendizaje(unidad);
    }


}
