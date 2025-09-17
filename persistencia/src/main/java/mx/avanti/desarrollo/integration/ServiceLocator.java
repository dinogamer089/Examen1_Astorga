package mx.avanti.desarrollo.integration;

import jakarta.persistence.EntityManager;
import mx.avanti.desarrollo.dao.*;
import mx.avanti.desarrollo.persistence.HibernateUtil;


/**
 *
 * @author total
 */
public class ServiceLocator {


    private static UsuarioDAO usuarioDAO;
    private static ProfesorDAO profesorDAO;
    private static UnidadAprendizajeDAO UnidadAprendizajeDAO;
    private static ImparteDAO imparteDAO;

    private static EntityManager getEntityManager(){
        return HibernateUtil.getEntityManager();
    }

    /**
     * se crean las instancias DAO en caso de que no existan
     */
    public static UsuarioDAO getInstanceUsuarioDAO(){
        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO(getEntityManager());
            return usuarioDAO;
        } else{
            return usuarioDAO;
        }
    }

    public static ProfesorDAO getInstanceProfesorDAO(){
        if(profesorDAO == null){
            profesorDAO = new ProfesorDAO(getEntityManager());
            return profesorDAO;
        } else{
            return profesorDAO;
        }
    }

    public static UnidadAprendizajeDAO getInstanceUnidadAprendizajeDAO(){
        if(UnidadAprendizajeDAO == null){
            UnidadAprendizajeDAO = new UnidadAprendizajeDAO(getEntityManager());
            return UnidadAprendizajeDAO;
        } else{
            return UnidadAprendizajeDAO;
        }
    }

    public static ImparteDAO getInstanceImparteDAO(){
        if(imparteDAO == null){
            imparteDAO = new ImparteDAO(getEntityManager());
        }
        return imparteDAO;
    }

}