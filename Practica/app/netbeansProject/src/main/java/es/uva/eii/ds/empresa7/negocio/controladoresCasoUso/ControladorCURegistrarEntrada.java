package es.uva.eii.ds.empresa7.negocio.controladoresCasoUso;

import es.uva.eii.ds.empresa7.negocio.modelos.Empleado;
import es.uva.eii.ds.empresa7.negocio.modelos.EstadoVehiculo;
import es.uva.eii.ds.empresa7.negocio.modelos.OrdenReparacion;
import es.uva.eii.ds.empresa7.negocio.modelos.Punto;
import es.uva.eii.ds.empresa7.negocio.modelos.RegistroES;
import es.uva.eii.ds.empresa7.negocio.modelos.Session;
import es.uva.eii.ds.empresa7.negocio.modelos.Vehiculo;
import es.uva.eii.ds.empresa7.persistencia.daos.RegistroESDAO;
import es.uva.eii.ds.empresa7.persistencia.daos.VehiculoDAO;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Controlador caso de uso Registrar Entrada.
 * @author enrmart,fracura,carlgar
 */
public class ControladorCURegistrarEntrada {
    private Vehiculo v;
    private OrdenReparacion rep;
    private RegistroES registro;
    private Empleado e;
    
    /**
     * Metodo que comprueba la matricula dada, crea el registro y devuelve los datos
     * @param matricula matricula a comprobar
     * @return datos del Vehiculo asociado
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     * @throws DatosNoEncontradosException si no se encuentran los datos
     */
    public String compruebaMatricula(String matricula) throws CloneNotSupportedException, DatosNoEncontradosException {
        String datosJSON=VehiculoDAO.getDatosVehiculo(matricula);
        this.registro=new RegistroES(datosJSON);
        rep=registro.getOrden();
        v=rep.getVehiculo();
        return v.getDatos();
    }
    
    /**
     * Metodo que devuelve los datos de la orden.
     * @param matricula de la matricula
     * @return datos de la orden
     */
    public String obtenerOrden(String matricula) { 
       return rep.getDatos();
    }
    /**
     * Metodo que devuelve los datos del registro.
     * @return datos de registro
     */
    public String obtenerDatosRegistro(){
        return registro.getDatos();
    }
     /**
     * Metodo que comprueba la baja actualiza el estadso de vehiculo y de registro.
     * @return Un string que indica el estado final del vehiculo
     * @throws SQLException si hay error de base de datos
     */
    public String compruebaBaja() throws SQLException {
        if(!registro.propuestoBaja()){
            v.setEstado(EstadoVehiculo.EnPreparación);
            VehiculoDAO.actualizaEstado(v.getMatricula(),6);
            Timestamp entradaPunto=Timestamp.valueOf(LocalDateTime.now());
            RegistroESDAO.actualizaEntradaPunto(rep.getId(),entradaPunto);
            return "Vehiculo en preparacion, estara disponible en 1 hora";
        }else{
            v.setEstado(EstadoVehiculo.PropuestoParaBaja);
            VehiculoDAO.actualizaEstado(v.getMatricula(),7);
            Timestamp entradaPunto=Timestamp.valueOf(LocalDateTime.now());
            RegistroESDAO.actualizaEntradaPunto(rep.getId(),entradaPunto);
             return "Vehiculo propuesto para baja";
        }
    }
    /**
     * Metodo que coge el nombre de la session.
     * @return el nombre del empleado cogido
     */
    public String pedirNombre(){
        Session sesion=Session.getInstance();
        e=sesion.getSessionEmpleado();
        System.out.println("");
        return e.getNombre();
    }
    /**
     * Metodo que coge el numero del punto asociado a un empleado.
     * @return id del punto 
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable.
     */
    public int pedirNumeroPunto() throws CloneNotSupportedException{
        Punto p= e.getPunto();
        return p.getId();
    }
    
}
