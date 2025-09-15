package mx.desarrollo.helper;

import mx.desarrollo.integration.ServiceFacadeLocator;
import mx.avanti.desarrollo.entity.Usuario;
import java.io.Serializable;

public class LoginHelper implements Serializable {

    /**
     * Metodo para hacer login llamará a la instancia de usuarioFacade
     * @param correo
     * @param password
     * @return Usuario si existe, null si no1
     */
    public Usuario Login(String correo, String password) {
        return ServiceFacadeLocator.getInstanceFacadeUsuario().login(password, correo);
    }
}
