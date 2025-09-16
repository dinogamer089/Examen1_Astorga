/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    private static EntityManager getEntityManager(){
        return HibernateUtil.getEntityManager();
    }

    /**
     * se crea la instancia de usuarioDAO si esta no existe
     */
    public static UsuarioDAO getInstanceUsuarioDAO(){
        if(usuarioDAO == null){
            usuarioDAO = new UsuarioDAO(getEntityManager());
            return usuarioDAO;
        } else{
            return usuarioDAO;
        }
    }

    /**
     * se crea la instancia de profesorDAO si esta no existe
     */
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
}
