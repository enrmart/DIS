package es.uva.eii.ds.empresa7.negocio.controladoresCasoUso;

import es.uva.eii.ds.empresa7.negocio.modelos.Empleado;
import es.uva.eii.ds.empresa7.negocio.modelos.Vehiculo;
import es.uva.eii.ds.empresa7.negocio.modelos.Punto;
import es.uva.eii.ds.empresa7.negocio.modelos.ListadoVehiculos;
import es.uva.eii.ds.empresa7.negocio.modelos.Session;
import java.sql.SQLException;
import java.util.ArrayList;
import es.uva.eii.ds.empresa7.persistencia.daos.PuntoDAO;
import es.uva.eii.ds.empresa7.persistencia.daos.VehiculoDAO;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;


/**
 * Controlador que maneja la lógica de negocio para las funcionalidades del caso de uso del gerente.
 * @author franc, enrmart, carlgar
 */
public class ControladorCUVehiculosFueraPunto {
    private final ListadoVehiculos listado;
    private final Session sesion;
    private Empleado e;
    
    /**
    * Constructor de la clase ControladorCUGerente.
    */
    public ControladorCUVehiculosFueraPunto() {
        this.listado=new ListadoVehiculos();
        this.sesion=Session.getInstance();
    }
    
    /**
     * Metodo que coge el nombre del empleado de loa session
     * @return nombre del empleado
     */
    public String pedirNombre(){
        e=sesion.getSessionEmpleado();
        return e.getNombre();
    }
     /**
     * Metodo que se encarga de obtener el id o numero de punto asociado al empleado.
     * @return id del punto
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable.
     */
    public int pedirNumeroPunto() throws CloneNotSupportedException{
        Punto p= e.getPunto();
        return p.getId();
    }
    
    
    /**
     * Recupera la lista de vehiculos fuera del punto asignado al Gerente
     * @param punto punto del Gerente
     * @return lista de vehiculos
     * @throws SQLException Si se produce un error al acceder a la base de datos.
     * @throws DatosNoEncontradosException si no se encuentran datos
     */
    public ArrayList<String> recuperarVehiculos(int punto) throws SQLException, DatosNoEncontradosException {
        ArrayList<String> listadoJSON=VehiculoDAO.consultaVehiculosFueraPunto(punto);
        listado.creaListado(listadoJSON);
        return listado.getDatosVehiculos();
    }
    
    /**
     * Recupera un punto por su matrícula 
     * @param matricula La matrícula del vehículo asociado al punto que se quiere recuperar.
     * @return String con los datos del punto en el que esta el vehiculo
     * @throws SQLException Si se produce un error al acceder a la base de datos.
     */
    public String recuperarPunto(String matricula) throws SQLException{
       String puntoJSON = PuntoDAO.consultaPuntoDeVehiculo(matricula);
       Punto punto=new Punto(puntoJSON);
       return punto.getDatosPunto();
    }


    /**
     * Metodo que devuelve la matrícula de un vehiculo en la posicion pos.
     * @param pos del vehiculo en el listado Json
     * @return matricula del vehiculo
     */
    public String getMatricula(int pos) {
        ArrayList<String> listadoJSON=listado.getListado();
        String vehiculoJSON=listadoJSON.get(pos);
        Vehiculo v=new Vehiculo(vehiculoJSON);
        return v.getMatricula();
    }
    
}
