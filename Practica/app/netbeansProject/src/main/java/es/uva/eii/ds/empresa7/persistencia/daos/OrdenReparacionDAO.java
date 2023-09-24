package es.uva.eii.ds.empresa7.persistencia.daos;

import es.uva.eii.ds.empresa7.persistencia.dbaccess.DBConnection;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;
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
 * Clase que se encarga de las consultas a la base de datos relacionadas con orden de reparacion.
 * @author enrmart,fracura,carlgar
 */
public class OrdenReparacionDAO {
    private static final String SELECT_ORDEN_BY_MATRICULA="SELECT O.razones, O.momento FROM ORDENESDEREPARACION O WHERE O.Id=?";
    
    /**
     * Metodo que devuelve los datos de una orden de reparacion.
     * @param id del vehiculo
     * @return datos de la orden de reparacion
     * @throws DatosNoEncontradosException si no se encuentran datos
     */
    public static String getDatosOrdenReparacion(int id) throws DatosNoEncontradosException {
        DBConnection connection = DBConnection.getInstance();
        String razones="";
        String momento="";
        try (
                    PreparedStatement s = connection.getStatement(SELECT_ORDEN_BY_MATRICULA);
            ){ 
                s.setInt(1, id);
                ResultSet result = s.executeQuery();
                if(result.next()){
                    razones = result.getString(1);
                    momento=result.getString(2);
                }else{
                    throw new DatosNoEncontradosException("No existe una orden asociada a ese Vehiculo");
                }
                result.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String datosReparacion = obtenerDatosReparacionJsonString(id,razones,momento);  
        return datosReparacion;
    }
    
    private static String obtenerDatosReparacionJsonString(int id, String razones,String momento) {
            String ordenJsonString="";
            try (                
                    StringWriter stringWriter = new StringWriter();
                    JsonWriter writer = Json.createWriter(stringWriter);
                    ){

                JsonObject ordenJson = Json.createObjectBuilder()
                        .add("id", id)
                        .add("razones", razones)
                        .add("momento",momento)
                        .build();
                writer.writeObject(ordenJson);
                ordenJsonString = stringWriter.toString();
            } catch(Exception ex) {
                Logger.getLogger(Registry.class.getName()).log(Level.SEVERE, null, ex);
            }           
                         
            return ordenJsonString;
        }
    
}
