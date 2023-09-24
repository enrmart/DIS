package es.uva.eii.ds.empresa7.negocio.controladoresCasoUso;


import java.sql.SQLException;
import java.util.ArrayList;
import es.uva.eii.ds.empresa7.negocio.modelos.Entrega;
import es.uva.eii.ds.empresa7.negocio.modelos.Incidencia;
import es.uva.eii.ds.empresa7.negocio.modelos.Punto;
import es.uva.eii.ds.empresa7.negocio.modelos.Reserva;
import es.uva.eii.ds.empresa7.persistencia.daos.EntregaDAO;
import es.uva.eii.ds.empresa7.persistencia.daos.IncidenciaDAO;
import es.uva.eii.ds.empresa7.persistencia.daos.ReservaDAO;
import es.uva.eii.ds.empresa7.negocio.modelos.Session;
import es.uva.eii.ds.empresa7.negocio.modelos.Alquiler;
import es.uva.eii.ds.empresa7.negocio.modelos.Empleado;
import es.uva.eii.ds.empresa7.negocio.modelos.EstadoReserva;
import es.uva.eii.ds.empresa7.negocio.modelos.ListadoIncidencias;
import es.uva.eii.ds.empresa7.negocio.modelos.Vehiculo;
import es.uva.eii.ds.empresa7.negocio.modelos.RepositorioDatos;
import es.uva.eii.ds.empresa7.servicioscomunes.Repositorio;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Controlador que maneja el caso de uso Registrar devoluciones.
 * @author enrmart,fracura,carlgar
 */
public class ControladorCURegistrarDevolucion {
    private Reserva reserva;
    private Entrega entrega;
    private final Session sesion;
    private final Repositorio almacen;
    private Empleado e;
    private ListadoIncidencias listado = null;
    
    /**
     * Constructor del controlador con una vista dada
     */
    public ControladorCURegistrarDevolucion() {
        this.sesion=Session.getInstance();
        this.almacen=RepositorioDatos.getInstance();       
    }
    
    /**
     * Metodo que coge de la session el nombre del empleado.
     * @return nombre del empleado
     */
    public String pedirNombre(){
        e=sesion.getSessionEmpleado();
        return e.getNombre();
    }
     /**
     * Metodo que coge el id del punto asociado al empleado
     * @return id del punto
     */
    public int pedirNumeroPunto(){
        Punto p=null;
        try {
            p = e.getPunto();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(ControladorCURegistrarDevolucion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p.getId();
    }

    /**
     * Metodo que llama al DAO de Reserva y obtiene o un json con la reserva o un codigo de error
     * si se encuentra en alquiler y el punto de Devolucion y el punto dado son el mismo se devuelven los datos de matricula y dni.
     * @param id asociad a la reserva
     * @param punto dado para comparar.
     * @return reserva
     * @throws DatosNoEncontradosException si no se encuentran datos
     * @throws SQLException si hay fallo de base de datos.
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Reserva obtenerReserva(int id,int punto) throws SQLException, CloneNotSupportedException, DatosNoEncontradosException{
            String datosReservaJSON = ReservaDAO.consultaReservaEnAlquiler(id);
            this.reserva = new Reserva(datosReservaJSON);
            Punto puntoDevolucion = reserva.getPuntoDevolucion();
            if(!mismoPunto(puntoDevolucion.getId(),punto)){
                throw new IllegalArgumentException("El punto actual no es el de devolucion");
            }
            return reserva;
    }
    
    private boolean mismoPunto(int puntoDevolucion, int punto){
        return puntoDevolucion == punto;
    }
    
    /**
     * Metodo que coge el id del alquiler asociado a la reserva, el id de la entrega, coge la instancia del momento actual de tiempo, 
     * y crea una entrega en el controlador con todos estos datos.Devuelve al controlador de la vista el id Alquiler.
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    public void gestionaDevolucion() throws CloneNotSupportedException{
        Alquiler alquiler = reserva.getAlquiler();
        int id = EntregaDAO.devuelveId();
        this.entrega = new Entrega(id, alquiler);   
        almacen.setValor("entrega", entrega.clone());
    }
    
    
  
    
    
    /**
     * Metodo que cambia el estado de Reserva en la base y en el objeto, actualiuza el estado del vehiculo inserta la entrega y todas
     * las incidencias asociadas a la entrega en la base de datos.
     * @throws SQLException si hay fallo de base de datos.
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    public void finalizaReserva() throws SQLException, CloneNotSupportedException{
        reserva.setEstado(EstadoReserva.Finalizada);
        Vehiculo v=reserva.getVehiculo();
        ReservaDAO.anotaEstado(reserva.getId(),6,v.getMatricula());
        Alquiler a=entrega.getAlquiler();
        EntregaDAO.anotaDevolucion(entrega.getIdEntrega(), entrega.getMomento(), e.getDni(), a.getId());
        try{
        listado =(ListadoIncidencias)almacen.getValor("listado");
       
        
        if(!listado.getListado().isEmpty()){
        ArrayList<Incidencia>incidencias=listado.getListado();           
                for(Incidencia i : incidencias){ 
                    Entrega en=i.getEntrega();
                    int tipoIncidencia=i.getTipo().ordinal()+1;
                    IncidenciaDAO.registraIncidencia(i.getIdIncidencia(), tipoIncidencia, i.getDescripcion(), i.getImporte(), en.getIdEntrega());
                }
            }
        }catch(Exception e){
            
            }
    }
         
       
    }
