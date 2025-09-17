package mx.desarrollo.delegate;

import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.avanti.desarrollo.dao.UnidadAprendizajeDAO;
import mx.avanti.desarrollo.integration.ServiceLocator;

public class DelegateUnidadAprendizaje {

    public void saveUnidadAprendizaje(UnidadAprendizaje unidad) {
        UnidadAprendizajeDAO dao = ServiceLocator.getInstanceUnidadAprendizajeDAO();

        // Validacion negocio: Horas de clase, taller y laboratorio no mayores a 4 horas
        if (unidad.getHorasClase() > 4 || unidad.getHorasTaller() > 4 || unidad.getHorasLaboratorio() > 4) {
            throw new IllegalArgumentException("Las horas de clase, taller o laboratorio no pueden ser mayores a 4 horas");
        }
        dao.save(unidad);
    }

    public void updateUnidadAprendizaje(UnidadAprendizaje unidad) {
        UnidadAprendizajeDAO dao = ServiceLocator.getInstanceUnidadAprendizajeDAO();
        if (unidad.getHorasClase() > 4 || unidad.getHorasTaller() > 4 || unidad.getHorasLaboratorio() > 4) {
            throw new IllegalArgumentException("Las horas de clase, taller o laboratorio no pueden ser mayores a 4 horas");
        }
        dao.update(unidad);
    }


}
