package es.uva.eii.ds.empresa7.interfaz.pares_vista_control.attcliente;


import es.uva.eii.ds.empresa7.interfaz.GestorInterfazUsuario;
import es.uva.eii.ds.empresa7.negocio.controladoresCasoUso.ControladorCURegistrarDevolucion;
import es.uva.eii.ds.empresa7.negocio.modelos.Cliente;
import es.uva.eii.ds.empresa7.negocio.modelos.Reserva;
import es.uva.eii.ds.empresa7.negocio.modelos.Vehiculo;
import es.uva.eii.ds.empresa7.negocio.modelos.RepositorioDatos;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;
import es.uva.eii.ds.empresa7.servicioscomunes.Repositorio;
import java.sql.SQLException;

/**
 * Controlador que maneja la vista de atencion al cliente.
 * @author enrmart,fracura,carlgar
 */
public class CtrlVistaAtencionCliente {
    private VistaAtencionCliente vista;
    private ControladorCURegistrarDevolucion controlCU;
    private int punto;
    
    /**
     * Constructor del controlador de la vista
     * @param pantalla que es la entrada de datos
     */
    public CtrlVistaAtencionCliente(VistaAtencionCliente pantalla){
        this.vista=pantalla;
        this.controlCU = new ControladorCURegistrarDevolucion();
    }
    
    /**
     * Metodo que llama al controlador de caso de uso para pasar el id y el punto y procesa el evento Alquiler.
     */
    public void procesaEventoAlquiler(){
        try {
            int idReserva=Integer.parseInt(vista.getId());
            Reserva reserva=controlCU.obtenerReserva(idReserva,punto);
            Vehiculo v=reserva.getVehiculo();
            Cliente c=reserva.getCliente();
            vista.muestraDatos(v.getMatricula(),c.getDni(),c.getNumero());
        } catch (SQLException ex) {
            Repositorio al=RepositorioDatos.getInstance();
            al.setValor("error", ex.getMessage());
            GestorInterfazUsuario.generaVistaErrores();
        } catch (CloneNotSupportedException ex) {
            Repositorio al=RepositorioDatos.getInstance();
            al.setValor("error", ex.getMessage());
            GestorInterfazUsuario.generaVistaErrores();
        } catch (DatosNoEncontradosException ex) {
            Repositorio al=RepositorioDatos.getInstance();
            al.setValor("error", ex.getMessage());
            GestorInterfazUsuario.generaVistaErrores();
        }
    }
    
    
    /**
     * Metodo que pasa el nif del empleado al controlador del caso de Uso y procesa el evento de devolucion.
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    public void procesaEventoDevolucion() throws CloneNotSupportedException{
        controlCU.gestionaDevolucion();
    }
    
    /**
     * Metodo que se finaliza la Reserva
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     * @throws SQLException hay error de sql
     */
    public void finalizaReserva() throws SQLException, CloneNotSupportedException {        
        controlCU.finalizaReserva();
    }
    
    /**
     * Metodo que llama al gestor para volver a la vista Identificarse desde la vista atencion al cliente.
     */
    public void procesaEventoGeneraVistaIdentificarse(){
        GestorInterfazUsuario.generaVistaIdentificarse();
    }
    
    /**
     * Metodo que que llama al gestor para generar la vista de incidencias desde la de atencion al cliente
     * y le pasa el id de la entrega asociada a las incidencias. 
     */
    public void procesaEventoGeneraVistaIncidencias(){
        GestorInterfazUsuario.generaVistaIncidencias();
    }
     /**
     * Metodo que procesa el evento de pedir nombre de empleado.
     * @return el nombre pedido
     */
    public String procesaEventoPedirNombre() {
        return controlCU.pedirNombre();
    }
    /**
     * Metodo que procesa el evneto de pedir numerto del punto.
     * @return punto con el numero del punto
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable.
     */
    public int procesaEventoPedirNumeroPunto() throws CloneNotSupportedException{
        punto=controlCU.pedirNumeroPunto();
        return punto;
    }
    
}
