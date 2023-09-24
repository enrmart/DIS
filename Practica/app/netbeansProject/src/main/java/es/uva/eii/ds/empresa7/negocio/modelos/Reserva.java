package es.uva.eii.ds.empresa7.negocio.modelos;

import org.json.JSONObject;


/**
 * La clase Reserva representa una reserva de un vehículo para un cliente en un punto de entrega determinado.
 * Contiene información como la matrícula del vehículo, el estado del vehículo, el color del vehículo,
 * el modelo del vehículo, la marca del vehículo, el estado de la reserva, el punto de entrega y el DNI del cliente.
 * @author enrmart,fracura,carlgar
 */
public class Reserva implements Cloneable {
    private int idReserva;
    private Vehiculo vehiculo;
    private EstadoReserva estadoReserva;
    private Punto puntoEntrega;
    private Cliente cliente;
    private Alquiler alquiler;

    /**
     * Constructor de la clase Reserva que recibe un objeto JSON y lo utiliza para inicializar sus atributos.
     * @param json una cadena de caracteres que contiene un objeto JSON con la información de la reserva.
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     * 
     */
    public Reserva(String json) throws CloneNotSupportedException {
            JSONObject combinedJson = new JSONObject(json);
            JSONObject reservaJSON = combinedJson.getJSONObject("reserva");
            this.idReserva=Integer.parseInt(reservaJSON.getString("idReserva"));
            this.vehiculo=new Vehiculo(json);
            this.estadoReserva = Enum.valueOf(EstadoReserva.class,reservaJSON.getString("estadoReserva"));
            this.puntoEntrega = new Punto(json);
            this.cliente = new Cliente(json);
            int idAlquiler=Integer.parseInt(reservaJSON.getString("idAlquiler"));
            this.alquiler=new Alquiler(idAlquiler,this);
    }
  
    /**
     * Metodo que devuelve true o false según si el estado de alquiler es igual a 5.
     * @return boolean si se cumple la condicion o no.
     */
    public boolean enAlquiler(){
        return this.estadoReserva.equals(EstadoReserva.EnAlquiler);
    }
    
    /**
     * Metodo que devuelve el punto de devolucion asociado a la reserva.
     * @return el punto de devolucion.
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Punto getPuntoDevolucion() throws CloneNotSupportedException{
        return puntoEntrega.clone();
    }
    
    /**
     * Metodo que devuelve el estado del alquiler.
     * @return estado  del alquiler.
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Alquiler getAlquiler() throws CloneNotSupportedException{
        return alquiler.clone();
    }
    
    /**
     * Metodo que cambia el estado de Alquiler.
     * @param estadoN con el estado a cambiar.
     */
    public void setEstado(EstadoReserva estadoN){
        this.estadoReserva = estadoN;
    }
    
    /**
     * Getter del id de la reserva.
     * @return id de la reserva
     */
    public int getId() {
        return this.idReserva;
    }
     /**
     * Metodo que devuelve vehiculo asociado a una reserva.
     * @return vehiculo asociado a la reserva
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Vehiculo getVehiculo() throws CloneNotSupportedException {
        return vehiculo.clone();
    }
    
    /**
     * Metodo que devuelve un cliente asociado a una reserva.
     * @return cliente asociado
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Cliente getCliente() throws CloneNotSupportedException {
        return  cliente.clone();
    }
    
    /**
     * Implementacion de Cloneable
     * @return Reserva
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    @Override
    public Reserva clone() throws CloneNotSupportedException {
        try {
            return (Reserva) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no ser clonable
            return null;
        }
    }
}
