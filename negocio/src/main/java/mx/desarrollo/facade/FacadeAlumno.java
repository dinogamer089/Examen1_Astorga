package mx.desarrollo.facade;

import mx.desarrollo.delegate.DelegateAlumno;
import mx.avanti.desarrollo.entity.Alumno;

public class FacadeAlumno {

    private final DelegateAlumno delegateAlumno;

    public FacadeAlumno() {
        this.delegateAlumno = new DelegateAlumno();
    }

    public void guardarAlumno(Alumno alumno){
        delegateAlumno.saveAlumno(alumno);
    }

}
