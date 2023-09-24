package es.uva.eii.ds.empresa7.negocio.modelos;

import org.json.JSONObject;

/**
 * Clase que representa un vehículo.
 * @author enrmart,fracura,carlgar
 */
public class Vehiculo implements Cloneable {
    private String matricula;
    private EstadoVehiculo estado;
    private String color;
    private String nombreModelo;
    private String nombreMarca;
    
    
    /**
     * Constructor de la clase Vehiculo que recibe un objeto JSON como parámetro.
     * @param json del vehiculo
     */ 
    public Vehiculo(String json) {
        JSONObject combinedJson = new JSONObject(json);
        if(combinedJson.keySet().contains("vehiculo")){
            JSONObject vehiculoJSON = combinedJson.getJSONObject("vehiculo");
           this.matricula=vehiculoJSON.getString("matricula");
           this.estado=Enum.valueOf(EstadoVehiculo.class,vehiculoJSON.getString("estado"));
           this.color=vehiculoJSON.getString("color");
           this.nombreModelo=vehiculoJSON.getString("modelo");
           this.nombreMarca=vehiculoJSON.getString("marca");         
        }else{
            this.matricula=combinedJson.getString("matricula");
            this.estado=Enum.valueOf(EstadoVehiculo.class,combinedJson.getString("estado"));
           this.color=combinedJson.getString("color");
           this.nombreModelo=combinedJson.getString("modelo");
           this.nombreMarca=combinedJson.getString("marca");
        }
        
    }
    
    /**
     * Metodo que devuelve los datos de un vehiculo en un String.
     * @return datos del vehiculo
     */
    public String getDatos() {
      return "Vehiculo:\n Matricula: "+matricula+" Estado: EnTaller Color: "+color;
    }
    /**
     * Getter de la matricula del vehiculo.
     * @return matricula asociada al vehiculo
     */
    public String getMatricula() {
        return this.matricula;
    }
    /**
     * Setter del estado del vehiculo a un estado nuevo.
     * @param estadoN estado nuevo a setear
     */
    public void setEstado(EstadoVehiculo estadoN) {
        this.estado=estadoN;
    }
    /**
     * Getter del Estado del vehiculo.
     * @return estado actual del vehiculo
     */
    public EstadoVehiculo getEstado() {
        return estado;
    }

   /**
     * Implementacion de Cloneable
     * @return Vehiculo
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    @Override
    public Vehiculo clone() throws CloneNotSupportedException {
        try {
            return (Vehiculo) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no ser clonable
            return null;
        }
    }
}







       