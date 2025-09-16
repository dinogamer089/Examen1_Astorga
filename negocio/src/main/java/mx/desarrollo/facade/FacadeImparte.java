package mx.desarrollo.facade;

import mx.avanti.desarrollo.entity.Imparte;
import mx.desarrollo.delegate.DelegateImparte;

import java.util.List;

public class FacadeImparte {

    private final DelegateImparte delegate = new DelegateImparte();

    public void asignarUnidad(Imparte imparte) {
        delegate.saveImparte(imparte);
    }

    public List<Imparte> obtenerTodas() {
        return delegate.obtenerTodas();
    }

    public List<Imparte> obtenerPorProfesor(int idProfesor) {
        return delegate.obtenerPorProfesor(idProfesor);
    }
}
