package es.uva.eii.ds.empresa7.negocio.modelos;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Session
 * @author yania,enrmart,fracura,carlgar
 */
public class Session {
    
    // Class level (singleton) 
    
    private static Session theSession;
    
    /**
     * Metodo que devuelve la instancia de Session y si no hay la crea.
     * @return instancia de Session
     */
    public static Session getInstance() {
        if (theSession==null) {
            theSession = new Session();
        }
        return theSession;
      }


    // Instance level
    
    private Empleado empleado;
    
    private Session (){
        empleado=null;
    }
    /**
     * Metodo que asigna un empleado a la sesion.
     * @param e empleado que se guarda
     */
    public void setSessionEmpleado(Empleado e) {
        empleado=e;
    }
    /**
     * Getter del empleado guardado en session.
     * @return el empleado guardado en session

     */
    public Empleado getSessionEmpleado(){
        try {
            return empleado.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Session.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
