package es.uva.eii.ds.empresa7.interfaz.pares_vista_control.gerente;

import es.uva.eii.ds.empresa7.negocio.controladoresCasoUso.ControladorCUVehiculosFueraPunto;
import java.sql.SQLException;
import java.util.ArrayList;
import es.uva.eii.ds.empresa7.interfaz.GestorInterfazUsuario;
import es.uva.eii.ds.empresa7.negocio.modelos.RepositorioDatos;
import es.uva.eii.ds.empresa7.servicioscomunes.Repositorio;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;


/**
 * Controlador que maneja la vista de Gerente.
 * @author franc, enrmart, carlgar
 */
public class CtrlVistaGerente {
    private VistaGerente vista;
    private ControladorCUVehiculosFueraPunto controlCU;
    private int punto;
    
    /**
     * Constructor del controlador de la vista
     * @param pantalla {@link VistaGerente} que es la entrada de datos 
     */
    public CtrlVistaGerente(VistaGerente pantalla){
        this.vista=pantalla;
        this.controlCU = new ControladorCUVehiculosFueraPunto();
    }
    
    /**
     * Metodo que procesa el evento de pedir el nombre del empleado.
     * @return el nombre del empleado
     */
    public String procesaEventoPedirNombre(){
        return controlCU.pedirNombre();
    }
    
    /**
     * Metodo que procesa el evento de pedir el numero de punto.
     * @return punto con el numero
     */
    public int procesaEventoPedirNumeroPunto() {
        try {
            punto=controlCU.pedirNumeroPunto();
        } catch (CloneNotSupportedException ex) {
           Repositorio al=RepositorioDatos.getInstance();
           al.setValor("error", ex.getMessage());
           GestorInterfazUsuario.generaVistaErrores();
        }
        return punto;
    }
    
    /**
     * Muestra la lista de vehiculos 
     * @param punto punto del Gerente
     * @throws SQLException Si se produce un error al acceder a la base de datos.
     */
    void procesaEventoMostrarVehiculos(){
        ArrayList<String> lista;
        try {
            lista = controlCU.recuperarVehiculos(punto);
            vista.muestraVehiculos(lista);
        } catch (SQLException ex) {
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
     * Manda el String de los datos del vehiculo para coger solo la matricula y realizar la consulta 
     * del punto en el que se encuentra el vehiculo.
     * @throws SQLException Si se produce un error al acceder a la base de datos.
     */
    public void procesaEventoCocheSeleccionado() throws SQLException{
        int vehiculo=vista.getCocheSeleccionado();
        String matr = controlCU.getMatricula(vehiculo);
        String datosPunto=controlCU.recuperarPunto(matr);
        vista.muestraDatosPuntoSeleccionado(datosPunto);
    }

    /**
     * Evento del botón cancelar. Que le hace cancelar el ultimo caso de uso.
     */
    void procesaEventoCancelar(){
        GestorInterfazUsuario.generaVistaIdentificarse();
    }
}
