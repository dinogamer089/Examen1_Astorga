package mx.desarrollo.helper;

import mx.avanti.desarrollo.entity.Usuario;
import mx.desarrollo.integration.ServiceFacadeLocator;

public class LoginHelper {

    public Usuario Login(String correo, String contrasena) {

        Usuario usuario = ServiceFacadeLocator.getInstanceFacadeUsuario().login(contrasena, correo);

        if (usuario != null) {
            if (usuario.getContrasena().equals(contrasena))
            {
                return usuario;
            }
        }
        return null;
    }
}
