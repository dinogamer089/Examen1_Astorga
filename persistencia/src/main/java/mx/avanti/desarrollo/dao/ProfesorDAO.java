package mx.avanti.desarrollo.dao;

import jakarta.persistence.EntityManager;
import mx.avanti.desarrollo.persistence.AbstractDAO;
import mx.avanti.desarrollo.entity.Profesor;

import java.util.List;

public class ProfesorDAO extends AbstractDAO<Profesor> {
    private final EntityManager entityManager;

    public ProfesorDAO(EntityManager em) {
        super(Profesor.class);
        this.entityManager = em;
    }

    public List<Profesor> obtenerTodos() {
        return entityManager.createQuery("SELECT p FROM Profesor p", Profesor.class)
                .getResultList();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}