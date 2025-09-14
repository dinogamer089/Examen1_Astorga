package mx.desarrollo.integration;

import mx.desarrollo.facade.FacadeUsuario;

public class ServiceFacadeLocator {

    private static FacadeUsuario facadeUsuario;




    public static synchronized FacadeUsuario getInstanceFacadeUsuario() {
        if (facadeUsuario == null) {
            facadeUsuario = new FacadeUsuario();
        }
        return facadeUsuario;
    }
}
