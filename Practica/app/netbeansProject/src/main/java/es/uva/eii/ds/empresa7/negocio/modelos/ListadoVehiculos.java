package es.uva.eii.ds.empresa7.negocio.modelos;

import java.io.StringReader;
import java.util.ArrayList;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonReaderFactory;

/**
 * Clase que almacena el listado de vehiculos que estan en distinto punto 
 * @author enrmart,francu,carlgar
 */
public class ListadoVehiculos {
    
    private ArrayList<String>listado;
    
    /**
     * Transforma un listado de Strings JSON en un listado de Strings con determinado formato 
     * @param listadoJSON un ArrayList de String en formato JSON 
     */
    public void creaListado(ArrayList<String>listadoJSON){
        this.listado=listadoJSON;
    }
    
    /**
     * Metodo que devuelve un Arraylist String con los datos de vehiculos de un listado.
     * @return vehiculos con los datos de todos los vehiculos del listado
     */
    public ArrayList<String> getDatosVehiculos(){
        ArrayList<String>vehiculos=new ArrayList<>();
        JsonReaderFactory factory = Json.createReaderFactory(null);
        for (int i = 0; i < listado.size(); i++){
            try (
                JsonReader reader = factory.createReader(new StringReader(listado.get(i)));
        ){
            JsonObject vehiculoJSON = reader.readObject();
            String datosVehiculo = "";
            datosVehiculo += "Matricula:" +  vehiculoJSON.getString("matricula") + ",estado:" + vehiculoJSON.getString("estado") + ",color:" + vehiculoJSON.getString("color") + ",modelo:" + vehiculoJSON.getString("modelo") + ",marca:" + vehiculoJSON.getString("marca");
            vehiculos.add(datosVehiculo);
            }
        }
        return vehiculos;
    }
    
    /**
     * Devuelve un ArrayList de Strings
     * @return un ArrayList de String
     */
    public ArrayList<String>getListado(){
        return new ArrayList<>(this.listado);
    }
}
