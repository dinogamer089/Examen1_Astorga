package mx.desarrollo.helper;

import mx.avanti.desarrollo.entity.Profesor;
import mx.desarrollo.integration.ServiceFacadeLocator;

public class ProfesorHelper {

    public void guardarProfesor(Profesor profesor) {
        if (profesor.getRfc() != null) {
            profesor.setRfc(profesor.getRfc().trim().toUpperCase());
        }
        ServiceFacadeLocator.getInstanceFacadeProfesor().registrarProfesor(profesor);
    }
}