package es.uva.eii.ds.empresa7.interfaz.pares_vista_control.tecnico;

import es.uva.eii.ds.empresa7.interfaz.GestorInterfazUsuario;
import es.uva.eii.ds.empresa7.negocio.controladoresCasoUso.ControladorCURegistrarEntrada;
import es.uva.eii.ds.empresa7.negocio.modelos.RepositorioDatos;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;
import es.uva.eii.ds.empresa7.servicioscomunes.Repositorio;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que se encarga de la logica de vistaTécnico.
 * @author fracura, enrmart, carlgar
 */
public class CtrlVistaTecnico {
    private VistaTecnico vista;
    private ControladorCURegistrarEntrada controlCU;
    
    /**
     * Constructor del control de la vista. 
     * @param vista con la vista a setear como atributo.
     */
    public CtrlVistaTecnico(VistaTecnico vista){
        this.vista=vista;
        this.controlCU=new ControladorCURegistrarEntrada();
    }
    /**
     * Metodo que procesa el evento de introducir la matricula, 
     * coge los datos de vista se los pasa al controlador de CU y finalmente muestra datos en pantalla.
     */
    public void procesaEventoIntroducirMatricula() {
        try {
            String matricula=vista.getMatricula();
            String datosVehiculo=controlCU.compruebaMatricula(matricula);
            String datosOrden=controlCU.obtenerOrden(matricula);
            String datosRegistro=controlCU.obtenerDatosRegistro();
            vista.muestraDatos(datosVehiculo,datosOrden,datosRegistro);
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
     * Metodo que procesa el evento de Preparacion comprobando la baja.
     * @throws SQLException error base de datos
     */
    void procesaEventoPreparacion() throws SQLException {
       String estado=controlCU.compruebaBaja();
       vista.muestraRetroalimentacio(estado);
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
     */
    public int procesaEventoPedirNumeroPunto(){
        try {
            return controlCU.pedirNumeroPunto();
        } catch (CloneNotSupportedException ex) {
            Repositorio al=RepositorioDatos.getInstance();
            al.setValor("error", ex.getMessage());
            GestorInterfazUsuario.generaVistaErrores();Logger.getLogger(CtrlVistaTecnico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
}
