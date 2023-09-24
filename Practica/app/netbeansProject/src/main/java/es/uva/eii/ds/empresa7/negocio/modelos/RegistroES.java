package es.uva.eii.ds.empresa7.negocio.modelos;

import org.json.JSONObject;

/**
 * Esta clase define una serie de propiedades para un objeto de tipo "RegistroES".
 * @author enrmart,francu,carlgar
 */
public class RegistroES {

    private OrdenReparacion orden;
    private String entrada;
    private String salida;
    private boolean baja;
    private String causa;
    
    /**
     * Constructor de RegistroES a partir de un Json.
     * @param json del registro a crear
     */
    public RegistroES(String json) {
        JSONObject combinedJson = new JSONObject(json);
        JSONObject registroJson = combinedJson.getJSONObject("registro");
        entrada = registroJson.getString("entrada");
        salida = registroJson.getString("salida");
        baja=registroJson.getBoolean("baja");
        causa=registroJson.getString("causas");
        orden=new OrdenReparacion(json);
    } 
    
    /**
     * Metodo que devuelve la Orden de Reparacion asociada al registro.
     * @return Orden de reparacion
     * @throws CloneNotSupportedException si se intenta clonar una clase que no tenga implementado cloneable
     */
    public OrdenReparacion getOrden() throws CloneNotSupportedException {
        return (OrdenReparacion) orden.clone();
    }
    
    /**
     * Metodo que devuelve los datos del RegistroES en un String.
     * @return String con los datos del registro
     */
    public String getDatos(){
        String vf="No";
        if(baja)
            vf="Si";
        return "Registro:\n Momento Entrada: "+entrada+" Momento Salida: "+salida+" Propuesto Baja: "+vf+" Causas: "+causa;
    }
    
    /**
     * Metodo que devuelve si un vehiculo está propuesto para baja o no.
     * @return boolean baja
     */
    public boolean propuestoBaja(){
        return baja;
    }
}
