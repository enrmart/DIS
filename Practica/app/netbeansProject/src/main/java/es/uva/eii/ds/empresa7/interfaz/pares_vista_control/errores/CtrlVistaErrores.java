package es.uva.eii.ds.empresa7.interfaz.pares_vista_control.errores;

import es.uva.eii.ds.empresa7.interfaz.GestorInterfazUsuario;
import es.uva.eii.ds.empresa7.negocio.controladoresCasoUso.ControladorCUError;


/**
 * Controlador vista errores.
 * @author enrmart,francu,carlgar
 */
public class CtrlVistaErrores {
    private VistaErrores vista;
    private ControladorCUError controlCU;
    public CtrlVistaErrores(VistaErrores vista){
        this.vista=vista;
        this.controlCU=new ControladorCUError();
    }

    /**
     * Metodo que cierra la vista de errores.
     */
    public void cierraVista() {
       GestorInterfazUsuario.cierraErrores();
    }

    /**
     * Metodo getter del mensaje de error.
     * @return error recibido
     */
    public String getMessage() {
       return controlCU.getError();
        
    }
    
}
