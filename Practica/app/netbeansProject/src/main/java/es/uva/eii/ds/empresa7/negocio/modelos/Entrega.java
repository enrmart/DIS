package es.uva.eii.ds.empresa7.negocio.modelos;


import java.util.Calendar;
import java.util.Date;


/**
 * La clase Entrega representa una entrega de un alquiler.
 * @author enrmart,fracura,carlgar
 */
public class Entrega implements Cloneable{
    
    private int idEntrega;
    private Date momento;
    private Alquiler alquiler;
    private Empleado empleado;
    
     /**
     * Constructor de la clase Entrega.
     * 
     * @param idEntrega el ID de la entrega
     * @param alquiler alquiler asociado
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Entrega(int idEntrega, Alquiler alquiler) throws CloneNotSupportedException{
        this.idEntrega = idEntrega;
        this.momento = Calendar.getInstance().getTime();
        this.alquiler = alquiler;
        this.empleado = alquiler.getEmpleado();        
    }
    
     /**
     * Obtiene el ID de la entrega.
     * 
     * @return el ID de la entrega
     */
    public int getIdEntrega() {
        return idEntrega;
    }

    /**
     * Obtiene el momento en que se realizó la entrega.
     * 
     * @return el momento en que se realizó la entrega
     */
    public Date getMomento() {
        return momento;
    }

      /**
     * Obtiene el estado del alquiler en el momento de la entrega.
     * 
     * @return el estado del alquiler en el momento de la entrega
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Alquiler getAlquiler() throws CloneNotSupportedException {
        return alquiler.clone();
    }

    /**
     * Obtiene el empleado responsable de la entrega.
     * 
     * @return el empleado responsable de la entrega
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Empleado getEmpleado() throws CloneNotSupportedException {
        return (Empleado)empleado.clone();
    }
    
    /**
     * Implementacion de Cloneable
     * @return Entrega
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    @Override
    public Entrega clone() throws CloneNotSupportedException {
        try {
            return (Entrega) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no ser clonable
            return null;
        }
    }
}
