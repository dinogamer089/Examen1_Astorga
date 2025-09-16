package mx.desarrollo.helper;

import mx.avanti.desarrollo.entity.Imparte;
import mx.avanti.desarrollo.entity.Profesor;
import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.avanti.desarrollo.dao.ProfesorDAO;
import mx.avanti.desarrollo.dao.UnidadAprendizajeDAO;
import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.avanti.desarrollo.integration.ServiceLocator;

import java.util.List;

public class ImparteHelper {

    public List<Profesor> listarProfesores() {
        return ServiceLocator.getInstanceProfesorDAO().obtenerTodos();
    }

    public List<UnidadAprendizaje> listarUnidades() {
        return ServiceLocator.getInstanceUnidadAprendizajeDAO().obtenerTodos();
    }

    public void asignar(Imparte imparte) {
        ServiceFacadeLocator.getInstanceFacadeImparte().asignarUnidad(imparte);
    }

    // Consulta de las asignaciones de un profesor
    public List<Imparte> listarDeProfesor(int idProfesor) {
        return ServiceFacadeLocator.getInstanceFacadeImparte().obtenerPorProfesor(idProfesor);
    }
}
