package mx.desarrollo.integration;

import mx.desarrollo.facade.FacadeProfesor;
import mx.desarrollo.facade.FacadeUsuario;
import mx.desarrollo.facade.FacadeUnidadAprendizaje;

public class ServiceFacadeLocator {

    private static FacadeUsuario facadeUsuario;
    private static FacadeProfesor facadeProfesor;
    private static FacadeUnidadAprendizaje facadeUnidadAprendizaje;

    public static synchronized FacadeProfesor getInstanceFacadeProfesor() {
        if (facadeProfesor == null) {
            facadeProfesor = new FacadeProfesor();
        }
        return  facadeProfesor;
    }

    public static synchronized FacadeUsuario getInstanceFacadeUsuario() {
        if (facadeUsuario == null) {
            facadeUsuario = new FacadeUsuario();
        }
        return facadeUsuario;
    }

    public static synchronized FacadeUnidadAprendizaje getInstanceFacadeUnidadAprendizaje() {
        if (facadeUnidadAprendizaje == null) {
            facadeUnidadAprendizaje = new FacadeUnidadAprendizaje();
        }
        return  facadeUnidadAprendizaje;
    }
}
