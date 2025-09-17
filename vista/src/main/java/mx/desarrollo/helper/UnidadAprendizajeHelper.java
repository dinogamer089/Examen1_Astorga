package mx.desarrollo.helper;

import jakarta.persistence.EntityManager;
import mx.avanti.desarrollo.dao.UnidadAprendizajeDAO;
import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.avanti.desarrollo.integration.ServiceLocator;
import mx.avanti.desarrollo.persistence.HibernateUtil;
import mx.desarrollo.integration.ServiceFacadeLocator;

import java.util.List;

public class UnidadAprendizajeHelper {

    private static EntityManager getEntityManager() {
        return HibernateUtil.getEntityManager();
    }

    // Método para obtener todas las Unidades de Aprendizaje de la base de datos
    public List<UnidadAprendizaje> obtenerTodas()
    {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO()
                .obtenerTodos();
    }

    // Método para guardar una Unidad de Aprendizaje
    public void guardarUnidadAprendizaje(UnidadAprendizaje unidad) {
        // Se normaliza el nombre quitando espacios y pasando a mayúsculas
        if (unidad.getNombre() != null) {
            unidad.setNombre(unidad.getNombre().trim().toUpperCase());
        }
        // Usamos ServiceFacadeLocator para obtener el Facade y registrar la unidad
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().registrarUnidadAprendizaje(unidad);
    }

    // Método para eliminar una Unidad de Aprendizaje
    public void eliminarUnidadAprendizaje(UnidadAprendizaje unidad) {
        EntityManager em = HibernateUtil.getEntityManager(); // Obtener el EntityManager
        try {
            em.getTransaction().begin();
            em.remove(em.contains(unidad) ? unidad : em.merge(unidad));  // Asegurarse de que la entidad esté gestionada antes de eliminarla
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
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