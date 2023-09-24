package es.uva.eii.ds.empresa7.negocio.modelos;

import org.json.JSONObject;

/**
 * Esta clase define una serie de propiedades para un objeto de tipo "Punto"
 * @author enrmart,fracura,carlgar
 */
public class Punto implements Cloneable{
    private int id;
    private String nombre;
    private String email;
    private String telefono;
    private String localizacion;

    /**
     * Este constructor en Java crea un objeto de tipo Punto a partir de una cadena de texto
     * en formato JSON que contiene información sobre el punto. La cadena de texto se convierte 
     * en un objeto JsonObject y se utiliza para asignar valores a las propiedades del objeto Punto.
     * @param json una cadena de texto en formato JSON que contiene información sobre un punto
     */
    public Punto(String json) {
            JSONObject combinedJson = new JSONObject(json);
            if(combinedJson.keySet().contains("punto")){
            JSONObject puntoJSON = combinedJson.getJSONObject("punto");
            this.id=Integer.parseInt(puntoJSON.getString("id"));
            this.nombre=puntoJSON.getString("nombre");
            this.email = puntoJSON.getString("email");
            this.telefono= puntoJSON.getString("telefono");
            this.localizacion = puntoJSON.getString("localizacion");            
            }else{
                this.id=Integer.parseInt(combinedJson.getString("id"));
                this.nombre=combinedJson.getString("nombre");
                this.email = combinedJson.getString("email");
                this.telefono= combinedJson.getString("telefono");
                this.localizacion = combinedJson.getString("localizacion"); 
            }     
    }
            

    /**
     * Este método estático en Java recupera información del punto asociado a un vehículo
     * utilizando su matrícula como parámetro. Devuelve una cadena de texto que contiene 
     * información sobre la localización, correo electrónico y teléfono del punto.
     * @return una cadena de texto con información sobre el punto asociado al vehículo
     */
    public String getDatosPunto(){
        String datosPunto = "";   
        datosPunto += "Localizacion: " + this.localizacion + "\r\n" +  "Email:" + this.email+ "\r\n" + "Telefono:" + this.telefono;
        return datosPunto;
    }
    
    /**
     * Metodo que devuelve el id del punto.
     * @return id del punto
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Implementacion de Cloneable
     * @return Punto
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    @Override
    public Punto clone() throws CloneNotSupportedException {
        try {
            return (Punto) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no ser clonable
            return null;
        }
    }
    
}
