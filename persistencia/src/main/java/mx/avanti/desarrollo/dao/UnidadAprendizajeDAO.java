package mx.avanti.desarrollo.dao;

import jakarta.persistence.EntityManager;
import mx.avanti.desarrollo.persistence.AbstractDAO;
import mx.avanti.desarrollo.entity.UnidadAprendizaje;

import java.util.List;

public class UnidadAprendizajeDAO extends AbstractDAO<UnidadAprendizaje> {
    private final EntityManager entityManager;

    public UnidadAprendizajeDAO(EntityManager em) {
        super(UnidadAprendizaje.class);
        this.entityManager = em;
    }

    public List<UnidadAprendizaje> obtenerTodos() {
        return entityManager.createQuery("SELECT ua FROM UnidadAprendizaje ua", UnidadAprendizaje.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }



}
