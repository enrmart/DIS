package es.uva.eii.ds.empresa7.persistencia.daos;

import es.uva.eii.ds.empresa7.persistencia.dbaccess.DBConnection;
import java.io.StringWriter;
import java.rmi.registry.Registry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;


/**
 * Clase que se encarga de las consultas a la base de datos relacionadas con Punto
 * @author enrmart,fracura,carlgar
 */
public class PuntoDAO {


    private static final String SELECT_PUNTO_POR_MATRICULA = "SELECT P.Nombre,P.Email, P.Telefono, P.Localizacion, P.Id FROM Vehiculos V, Puntos P "
        + "WHERE V.SituadoEn = P.Id AND V.matricula = ?";
    
    public static final String SELECT_PUNTO_BY_ID="SELECT P.Nombre,P.Email, P.Telefono, P.Localizacion FROM Puntos P WHERE P.Id=?";
   
    /**
     * Devuelve los datos del punto donde se encuentra el coche, a partir desu matricula
     * @param matr matricula del coche seleccionado por el gerente
     * @return listadoJSON un ArrayList de String json correspondientes a todos los coches 
     */
    public static String consultaPuntoDeVehiculo(String matr){
        String id="";
        String email = "";
        String telefono = "";    
        String localizacion = ""; 
        String nombre="";
        DBConnection connection = DBConnection.getInstance();
        try (

                    PreparedStatement s = connection.getStatement(SELECT_PUNTO_POR_MATRICULA);
            ){ 
                s.setString(1, matr);
            try (ResultSet result = s.executeQuery()) {
                if(result.next()){
                    nombre =result.getString(1);
                    email = result.getString(2);
                    telefono = result.getString(3);
                    localizacion = result.getString(4);
                    id=result.getString(5);
                }
            }
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            String datosPuntoJSON = obtenerPuntoJsonString(nombre,email, telefono, localizacion,id);  

        return datosPuntoJSON;
    }
    /**
     * Metodo que devuelve el Json de un punto.
     * @param punto id del punto
     * @return Json de los datos de un punto
     */
    public static String consultaPunto(int punto){
        DBConnection connection = DBConnection.getInstance();
        String email = "";
        String telefono = "";    
        String localizacion = ""; 
        String nombre="";
        String id=String.valueOf(punto);
        try (

                    PreparedStatement s = connection.getStatement(SELECT_PUNTO_BY_ID);
            ){ 
                s.setInt(1, punto);
            try (ResultSet result = s.executeQuery()) {
                if(result.next()){
                    nombre =result.getString(1);
                    email = result.getString(2);
                    telefono = result.getString(3);
                    localizacion = result.getString(4);
                }
            }
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            String datosPuntoJSON = obtenerPuntoJsonString(nombre,email, telefono, localizacion,id);
            
        return datosPuntoJSON;
    }
    
    
    private static String obtenerPuntoJsonString(String nombre,String email, String telefono, String localizacion,String id) {
            String puntoJsonString="";
            try (                
                    StringWriter stringWriter = new StringWriter();
                    JsonWriter writer = Json.createWriter(stringWriter);
                    ){

                JsonObject puntoJson = Json.createObjectBuilder()
                        .add("nombre",nombre)
                        .add("email", email)
                        .add("telefono", telefono)
                        .add("localizacion", localizacion)
                        .add("id",id)
                        .build();
                writer.writeObject(puntoJson);
                puntoJsonString = stringWriter.toString();
            } catch(Exception ex) {
                Logger.getLogger(Registry.class.getName()).log(Level.SEVERE, null, ex);
            }             
            return puntoJsonString;
        }
    
}
