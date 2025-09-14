package mx.desarrollo.facade;

import mx.avanti.desarrollo.entity.Profesor;
import mx.desarrollo.delegate.DelegateProfesor;

public class FacadeProfesor {

    private final DelegateProfesor delegate = new DelegateProfesor();

    public void registrarProfesor(Profesor profesor) {
        delegate.saveProfesor(profesor);
    }
}