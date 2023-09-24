package es.uva.eii.ds.empresa7.interfaz.pares_vista_control.attcliente;

import es.uva.eii.ds.empresa7.interfaz.GestorInterfazUsuario;
import es.uva.eii.ds.empresa7.negocio.controladoresCasoUso.ControladorCUAnadirIncidencias;
import es.uva.eii.ds.empresa7.negocio.modelos.TipoIncidencia;
import es.uva.eii.ds.empresa7.negocio.modelos.RepositorioDatos;
import es.uva.eii.ds.empresa7.servicioscomunes.Repositorio;
import java.util.ArrayList;


/**
 * Controlador que maneja la vista de incidencias.
 * @author enrmart,fracura,carlgar
 */
public class CtrlVistaIncidencias {
    
    private VistaIncidencias vista;
    private ControladorCUAnadirIncidencias controlCU;
    private TipoIncidencia tipo;
    private String incidencia;
    
    /**
     * Constructor del controlador de la vista
     * @param pantalla que es la entrada de datos
     */
    public CtrlVistaIncidencias(VistaIncidencias pantalla){
        this.vista=pantalla;
        this.controlCU = new ControladorCUAnadirIncidencias();
    }
    
    /**
     * Metodo que procesa el evento de añadir una incidencia y comprueba que el tipo sea valido y la descripcion no se encuentre vacía.
     * Si hay algun error se llama a la vista para que muestre los errores. Si no hay error prosigue mostrando cargo asociado.
     */
    public void procesaEventoAnadirIncidencia(){
        this.incidencia = vista.getDescripcionIncidencia();
        String tipoS= vista.getTipoIncidencia();
        
        try{
            
            int tipoI = Integer.parseInt(tipoS);
            if(tipoI < 1 || tipoI > 4){
                Repositorio al=RepositorioDatos.getInstance();
                al.setValor("error", "Tipo de incidencia invalido.");
                GestorInterfazUsuario.generaVistaErrores();
            }
            
            switch(tipoI){
                case 1:
                    this.tipo = TipoIncidencia.Accidente;
                    break;
                case 2:
                    this.tipo = TipoIncidencia.Avería;
                    break;
                case 3:
                    this.tipo = TipoIncidencia.SuciedadExtrema;
                    break;
                case 4:
                    this.tipo = TipoIncidencia.RetrasoEnLaEntrega;
                    break;
            }
        }catch(Exception e){
            this.tipo=Enum.valueOf(TipoIncidencia.class,tipoS);
        }
            
            
        
        if(!this.comprobarIncidencia(incidencia)){
            Repositorio al=RepositorioDatos.getInstance();
            al.setValor("error", "Descripcion vacia.");
            GestorInterfazUsuario.generaVistaErrores(); 
        }
        vista.muestraCargosExtra();
    }
    private boolean comprobarIncidencia(String descInc){
         return !descInc.isEmpty();
     }
    
    /**
     * Metodo que coge el cargo asociado que en la vista de forma dfefault se encuentra en 0, se cosigue el id de la entrega asociada y se registra la incidencia en la base de datos.
     */
    public void procesaEventoAnadirCargo(){
        double importe = vista.getImporte();
        ArrayList<String> incidencias=controlCU.registrarIncidencia(incidencia, tipo, importe);
        vista.muestraDatosIncidencias(incidencias);
    }
    
    /**
     * Metodo que llama al gestor para pasar de la vista de incidencias a la vista de Aatencion cliente con 2 variantes si han existido incidencias y si no.
     */
    public void procesaEventoVueltaAVistaAtencionCliente(){
       GestorInterfazUsuario.vueltaAVistaAtencionCliente(); 
    }
    /**
     * Metodo que llama al gestor para volver a la vista de atencion cliente.
     */
    public void finalizaCasoUso(){
        controlCU.registrarInformacion();
        GestorInterfazUsuario.vueltaAVistaAtencionCliente(); 
    }
}
