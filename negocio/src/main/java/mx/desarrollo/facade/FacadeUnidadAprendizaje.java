package mx.desarrollo.facade;

import mx.avanti.desarrollo.entity.UnidadAprendizaje;
import mx.desarrollo.delegate.DelegateUnidadAprendizaje;

public class FacadeUnidadAprendizaje {

    private final DelegateUnidadAprendizaje delegate = new DelegateUnidadAprendizaje();

    public void registrarUnidadAprendizaje(UnidadAprendizaje unidad) {
        delegate.saveUnidadAprendizaje(unidad);
    }

    public void modificarUnidadAprendizaje(UnidadAprendizaje unidad) {
        delegate.updateUnidadAprendizaje(unidad);
    }

}
