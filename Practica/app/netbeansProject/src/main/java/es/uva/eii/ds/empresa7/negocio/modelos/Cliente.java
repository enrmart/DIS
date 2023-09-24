package es.uva.eii.ds.empresa7.negocio.modelos;

import org.json.JSONObject;

/**
 * Clase cliente
 * @author enrmart,francu,carlgar
 */
public class Cliente implements Cloneable {
    private String dni;
    private int numero;
    
    /**
     * Constructor de Cliente.
     * @param json del cliente a crear
     */
    public Cliente(String json){
         JSONObject combinedJson = new JSONObject(json);
         JSONObject clienteJSON = combinedJson.getJSONObject("cliente");
         this.dni=clienteJSON.getString("nif");
         this.numero=clienteJSON.getInt("numeroCliente");
    }
    
    /**
     * Metodo que hace el getter del dni
     * @return dni
     */
    public String getDni(){
        return this.dni;
    }
    /**
     * Metodo que devuelve el numero de cliente
     * @return numero 
     */
    public int getNumero(){
        return this.numero;
    }
    
    /**
     * Implementacion de cloneable
     * @return cliente
     * @throws CloneNotSupportedException  si se intenta clonar una clase que no tenga implementado cloneable
     */
    @Override
    public Cliente clone() throws CloneNotSupportedException {
        try {
            return (Cliente) super.clone();
        } catch (CloneNotSupportedException e) {
            // Manejo de excepción en caso de no ser clonable
            return null;
        }
    }
}
