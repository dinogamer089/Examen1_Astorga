package mx.avanti.desarrollo.dao;

import jakarta.persistence.EntityManager;
import mx.avanti.desarrollo.entity.Imparte;
import mx.avanti.desarrollo.entity.Dia;
import mx.avanti.desarrollo.entity.Tipo;
import mx.avanti.desarrollo.persistence.AbstractDAO;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;

public class ImparteDAO extends AbstractDAO<Imparte> {
    private final EntityManager entityManager;

    public ImparteDAO(EntityManager em) {
        super(Imparte.class);
        this.entityManager = em;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public List<Imparte> obtenerTodos() {
        return entityManager.createQuery("SELECT i FROM Imparte i", Imparte.class)
                .getResultList();
    }

    // Obtener todos los imparte del profesor
    public List<Imparte> obtenerPorProfesor(int idProfesor) {
        return entityManager.createQuery(
                        "SELECT i FROM Imparte i WHERE i.profesor.idProfesor = :id",
                        Imparte.class)
                .setParameter("id", idProfesor)
                .getResultList();
    }

    // Validacion para traslapes en las horas
    public boolean existeTraslape(int idProfesor, Dia dia, LocalTime inicio, LocalTime fin) {
        String jpql = "SELECT COUNT(i) FROM Imparte i " +
                "WHERE i.profesor.idProfesor = :idProfesor " +
                "AND i.dia = :dia " +
                "AND (i.horaInicio < :fin AND i.horaFin > :inicio)";
        Long count = entityManager.createQuery(jpql, Long.class)
                .setParameter("idProfesor", idProfesor)
                .setParameter("dia", dia)
                .setParameter("inicio", inicio)
                .setParameter("fin", fin)
                .getSingleResult();
        return count > 0;
    }

    /*
    * Metodos para validar
    */

    /** Lista asignaciones del mismo profesor, misma unidad y el mismo tipo, para la validacion de total de horas */
    public List<Imparte> listarPorProfesorUnidadTipo(int idProfesor, int idUA, Tipo tipo) {
        return entityManager.createQuery(
                        "SELECT i FROM Imparte i " +
                                "WHERE i.profesor.idProfesor = :pid " +
                                "AND i.unidadAprendizaje.idUA = :ua " +
                                "AND i.tipo = :tipo",
                        Imparte.class)
                .setParameter("pid", idProfesor)
                .setParameter("ua", idUA)
                .setParameter("tipo", tipo)
                .getResultList();
    }

    /** Suma de horas asignadas al profesor para esa unidad y ese tipo */
    public int horasAsignadasProfesorUnidadTipo(int idProfesor, int idUA, Tipo tipo) {
        List<Imparte> lista = listarPorProfesorUnidadTipo(idProfesor, idUA, tipo);
        long total = 0;
        for (Imparte i : lista) {
            long h = Duration.between(i.getHoraInicio(), i.getHoraFin()).toHours();
            if (h > 0) total += h;
        }
        return (int) total;
    }
}
