package mx.desarrollo.helper;

import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.integration.ServiceFacadeLocator;

public class UnidadAprendizajeHelper {

    public void guardarUnidadAprendizaje(UnidadAprendizaje unidad) {
        // Se normaliza el nombre quitando espacios y pasando a mayusculas
        if (unidad.getNombre() != null) {
            unidad.setNombre(unidad.getNombre().trim().toUpperCase());
        }
        ServiceFacadeLocator.getInstanceFacadeUnidadAprendizaje().registrarUnidadAprendizaje(unidad);
    }
}
