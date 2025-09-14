package mx.desarrollo.ui;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import mx.desarrollo.helper.LoginHelper;
import mx.avanti.desarrollo.entity.Usuario;

import java.io.IOException;
import java.io.Serializable;

@Named("loginUI")
@SessionScoped
public class LoginBeanUI implements Serializable {

    private LoginHelper loginHelper;
    private Usuario usuario;

    public LoginBeanUI() {
        loginHelper = new LoginHelper();
    }

    @PostConstruct
    public void init() {
        usuario = new Usuario();
    }

    public void login() throws IOException {
        Usuario us = loginHelper.Login(usuario.getCorreo(), usuario.getContrasena());
        if (us != null && us.getId() != null) {
            usuario = us;
            FacesContext.getCurrentInstance().getExternalContext()
                    .redirect(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/Principal.xhtml");
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o contraseña incorrecta", "Intente de nuevo"));
        }
    }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}
