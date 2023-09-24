package es.uva.eii.ds.empresa7.negocio.modelos;

import java.util.Calendar;
import java.util.Date;


/**
 * Clase Alquiler.
 * @author enrmart,francu,carlgar
 */
public class Alquiler implements Cloneable{
    private int id;
    private Date momento;
    private Reserva reserva;
    private Empleado empleado;
    
    /**
     * Contructor de Alquiler.
     * @param idAlquiler id del Alquiler
     * @param reserva reserva asociada
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Alquiler(int idAlquiler,Reserva reserva) throws CloneNotSupportedException {
        this.id=idAlquiler;
        this.momento=Calendar.getInstance().getTime();
        this.reserva=reserva;
        Session sesion=Session.getInstance();
        this.empleado=sesion.getSessionEmpleado();
    }
    /**
     * Metodo que devuelve el empleado asociado al alquiler
     * @return Empleado 
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Empleado getEmpleado() throws CloneNotSupportedException{
        return empleado.clone();
    }
    
    /**
     * Metodo que devuelve el id del Alquiler
     * @return id 
     */
    public int getId() {
        return this.id;
    }

    /**
     * Implementacion del metodo Cloneable
     * @return Alquiler 
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    @Override
    public Alquiler clone() throws CloneNotSupportedException {
        try {
            return (Alquiler) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no ser clonable
            return null;
        }
    }
}
