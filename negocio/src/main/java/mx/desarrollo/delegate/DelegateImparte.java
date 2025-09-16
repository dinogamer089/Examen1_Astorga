package mx.desarrollo.delegate;

import mx.avanti.desarrollo.entity.Imparte;
import mx.avanti.desarrollo.entity.Tipo;
import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.avanti.desarrollo.dao.ImparteDAO;
import mx.avanti.desarrollo.dao.UnidadAprendizajeDAO;
import mx.avanti.desarrollo.integration.ServiceLocator;

import java.time.Duration;
import java.util.List;

public class DelegateImparte {

    public void saveImparte(Imparte imparte) {
        ImparteDAO imparteDAO = ServiceLocator.getInstanceImparteDAO();

        // Rangos validos
        if (imparte.getHoraInicio() == null || imparte.getHoraFin() == null ||
                !imparte.getHoraFin().isAfter(imparte.getHoraInicio())) {
            throw new IllegalArgumentException("El rango horario no es valido, la hora de fin debe ser mayor a la de inicio");
        }

        // Traslapes
        boolean traslape = imparteDAO.existeTraslape(
                imparte.getProfesor().getIdProfesor(),
                imparte.getDia(),
                imparte.getHoraInicio(),
                imparte.getHoraFin()
        );
        if (traslape) {
            throw new IllegalArgumentException("El profesor ya tiene una clase asignada en ese horario");
        }

        // Validar limite de horas
        int idProfesor = imparte.getProfesor().getIdProfesor();
        int idUA = imparte.getUnidadAprendizaje().getIdUA();
        Tipo tipo = imparte.getTipo();

        UnidadAprendizajeDAO uaDAO = ServiceLocator.getInstanceUnidadAprendizajeDAO();
        UnidadAprendizaje ua = uaDAO.findById(idUA);
        if (ua == null) {
            throw new IllegalArgumentException("La unidad de aprendizaje no existe");
        }

        // Limite por tipo de clase
        int limite;
        switch (tipo) {
            case Clase -> limite = ua.getHorasClase();
            case Taller -> limite = ua.getHorasTaller();
            case Laboratorio -> limite = ua.getHorasLaboratorio();
            default -> throw new IllegalArgumentException("Tipo de hora no reconocido");
        }

        int horasNueva = (int) Duration.between(imparte.getHoraInicio(), imparte.getHoraFin()).toHours();
        if (horasNueva <= 0) {
            throw new IllegalArgumentException("La duracion de la clase debe ser positiva");
        }

        // Validar tamaño de clase individual
        if (horasNueva > limite) {
            throw new IllegalArgumentException(
                    "La clase no puede durar mas que el total permitido de " + limite + " hora(s) para " + tipo + " en esa unidad"
            );
        }

        // Limitar horas de un profesor de un mismo tipo para que no se puedan asignar mas que las deifinidas
        int horasYaAsignadas = imparteDAO.horasAsignadasProfesorUnidadTipo(idProfesor, idUA, tipo);
        int restantes = limite - horasYaAsignadas;
        if (horasNueva > restantes) {
            throw new IllegalArgumentException(
                    "No se puede asignar la clase: solo quedan " + Math.max(restantes, 0) +
                            " hora(s) disponibles de " + tipo + " para esa unidad " +
                            "(Limite: " + limite + ", asignadas: " + horasYaAsignadas + ")"
            );
        }

        // Si pasa todo guardar
        imparteDAO.save(imparte);
    }

    public List<Imparte> obtenerTodas() {
        return ServiceLocator.getInstanceImparteDAO().obtenerTodos();
    }

    public List<Imparte> obtenerPorProfesor(int idProfesor) {
        return ServiceLocator.getInstanceImparteDAO().obtenerPorProfesor(idProfesor);
    }
}
