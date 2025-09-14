package mx.desarrollo.delegate;

import mx.avanti.desarrollo.entity.Profesor;
import mx.avanti.desarrollo.dao.ProfesorDAO;
import mx.avanti.desarrollo.integration.ServiceLocator;

public class DelegateProfesor {

    public void saveProfesor(Profesor profesor) {
        ProfesorDAO dao = ServiceLocator.getInstanceProfesorDAO();

        // Validación en negocio: evitar RFC duplicado
        Profesor existente = dao.findByOneParameterUnique(profesor.getRfc(), "rfc");
        if (existente != null) {
            throw new IllegalArgumentException("El RFC ya esta registrado");
        }

        dao.save(profesor);
    }
}