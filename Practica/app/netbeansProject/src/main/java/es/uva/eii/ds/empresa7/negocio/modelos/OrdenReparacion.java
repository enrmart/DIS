package es.uva.eii.ds.empresa7.negocio.modelos;

import org.json.JSONObject;

/**
 * Clase Orden de Reparacion
 * @author enrmart,francu,carlgar
 */
public class OrdenReparacion implements Cloneable {
    private int id;
    private String razones;
    private String momento;
    private Vehiculo vehiculo;
    
    /**
     * Constructor que crea una orden a partir de un Json.
     * @param json de la orden
     */
    public OrdenReparacion(String json){
            JSONObject combinedJson = new JSONObject(json);
            JSONObject ordenJson = combinedJson.getJSONObject("orden");
            id=ordenJson.getInt("id");
            razones=ordenJson.getString("razones");
            momento=ordenJson.getString("momento");
            vehiculo= new Vehiculo(json);
    }
    
    /**
     * Metodo que devuelve los datos de la orden en un String.
     * @return datos de la orden de reparacion
     */
    public String getDatos(){
        return "Orden Reparacion:\n  Fecha y hora: "+momento+"Razones: "+razones;
    }
    /**
     * Metodo que devuelve el id de una orden de reparacion.
     * @return id de la orden
     */
    public int getId(){
        return this.id;
    }
    /**
     * Metodo que devuelve la razones de una reparacion.
     * @return String razones
     */
    public String getRazones(){
        return this.razones;
    }
    /**
     * Metodo que devuelve el momento de la reparacion.
     * @return momento
     */
    public String getMomento(){
        return this.momento;
    }
    /**
     * Metodo que devuelve el vehiculo asociado a la orden de reparacion.
     * @return vehiculo
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    public Vehiculo getVehiculo() throws CloneNotSupportedException{
        return this.vehiculo.clone();
   }
    
    /**
     * Implementacion de Cloneable
     * @return Orden de Reparacion
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    @Override
    public OrdenReparacion clone() throws CloneNotSupportedException{
        try {
            return (OrdenReparacion) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no ser clonable
            return null;
        }
    }
}
