
import mx.avanti.desarrollo.dao.UnidadAprendizajeDAO;
import mx.avanti.desarrollo.persistence.HibernateUtil;

public class testDAOUA {
    public static void main(String[] args) {
        UnidadAprendizajeDAO dao = new UnidadAprendizajeDAO(HibernateUtil.getEntityManager());

        for(var ua : dao.obtenerTodos()) {
            System.out.println(ua.getIdUA() + ua.getNombre() + ua.getHorasLaboratorio() + ua.getHorasClase() + ua.getHorasTaller());
        }
    }
}
