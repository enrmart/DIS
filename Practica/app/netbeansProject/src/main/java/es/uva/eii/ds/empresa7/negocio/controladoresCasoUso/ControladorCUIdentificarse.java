package es.uva.eii.ds.empresa7.negocio.controladoresCasoUso;

import es.uva.eii.ds.empresa7.negocio.modelos.Empleado;
import java.sql.SQLException;
import es.uva.eii.ds.empresa7.negocio.modelos.Rol;
import es.uva.eii.ds.empresa7.persistencia.daos.EmpleadoDAO;
import es.uva.eii.ds.empresa7.negocio.modelos.Session;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;

/**
 * Clase que se encarga del controlador del caso de uso de Identificarse
 * @author enrmart,fracura,carlgar
 */
public class ControladorCUIdentificarse {
    
    private Empleado e;
    private Session sesion;
    
    /**
    * Constructor de la clase ControladorCUIdentificarse.
    */
    public ControladorCUIdentificarse() {
        sesion=Session.getInstance();
    }
    
    /**
     * Identifica a un empleado con el nombre de usuario y contraseña especificados 
     *
     * @param p un arreglo de caracteres que contiene la contraseña del empleado
     * @param d un arreglo de caracteres que contiene el nombre de usuario del empleado
     * @return rol del empleado
     * @throws SQLException si hay error en la base de datos
     * @throws DatosNoEncontradosException si no se encuentran datos
     */
    public Rol IdentificarEmpleado(char[] p, char[] d) throws SQLException, DatosNoEncontradosException {
        String d2="",p2="";
        for(int i=0;i<p.length;i++)
            p2=p2+p[i];
        for(int i=0;i<d.length;i++)
            d2=d2+d[i];
        String empleadoJSON=EmpleadoDAO.consultaEmpleadoPorLoginYPassword(d2, p2);
        e=new Empleado(empleadoJSON);
        sesion.setSessionEmpleado(e);
        if(e.estaActivo())
           return e.getRol();
        else
            throw new IllegalArgumentException("El empleado no esta activo");
    }

    
}
