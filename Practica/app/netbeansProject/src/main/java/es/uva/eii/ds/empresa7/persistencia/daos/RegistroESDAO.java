package es.uva.eii.ds.empresa7.persistencia.daos;

import es.uva.eii.ds.empresa7.persistencia.dbaccess.DBConnection;
import java.io.StringWriter;
import java.rmi.registry.Registry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;


/**
 * Clase que se encarga de las consultas a la base de datos relacionadas con RegistroES
 * @author enrmart,fracura,carlgar
 */
public class RegistroESDAO {
    
    private static final String SELECT_ENTRADASALIDATALLER_BY_ORDEN = "SELECT R.LlegadaAlTaller, R.SalidaDelTaller,R.PropuestoParaBaja, R.RazonesParaBaja FROM REGISTROSENTRADASALIDATALLER R " +
"INNER JOIN ORDENESDEREPARACION O ON R.OrdenDeReparacion = O.Id WHERE O.Id=?";
    
    private static final String INSERT_LLEGADAPUNTO_BY_ORDEN ="UPDATE REGISTROSENTRADASALIDATALLER R SET R.EntradaEnPunto=? WHERE R.OrdenDeReparacion=?";
    
    /**
     * Metodo que devuelve los datos de un registro.
     * @param id asociado al registro
     * @return String con los datos del registro
     */
    public static String obtenerRegistro(int id) {
        DBConnection connection = DBConnection.getInstance();
        String entrada="";
        String salida="";
        String causas="";
        boolean baja=false;
        try (
                    PreparedStatement s = connection.getStatement(SELECT_ENTRADASALIDATALLER_BY_ORDEN);
            ){ 
                s.setInt(1, id);
                ResultSet result = s.executeQuery();
                if(result.next()){
                  entrada=result.getString(1);
                  salida=result.getString(2);
                  baja=result.getBoolean(3);
                  causas=result.getString(4);
            } 
            result.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            String datosRegistro = obtenerDatosRegistroJsonString(entrada,salida,baja,causas);  
        return datosRegistro;
    }
    
    private static String obtenerDatosRegistroJsonString(String entrada, String salida, boolean baja, String causas) {
            String registroJsonString="";
            try (                
                    StringWriter stringWriter = new StringWriter();
                    JsonWriter writer = Json.createWriter(stringWriter);
                    ){

                JsonObject registroJson = Json.createObjectBuilder()
                        .add("entrada", entrada)
                        .add("salida", salida)
                        .add("baja",baja)
                        .add("causas",causas)
                        .build();
                writer.writeObject(registroJson);
                registroJsonString = stringWriter.toString();
            } catch(Exception ex) {
                Logger.getLogger(Registry.class.getName()).log(Level.SEVERE, null, ex);
            }           
                         
            return registroJsonString;
        }
    /**
     * Metodo que actualiza la Entrada en el punto
     * @param idOrden id de la orden asociada
     * @param entradaPunto momento de la entrada a actualizar
     * @throws SQLException si hay error de base de datos
    */
    public static void actualizaEntradaPunto(int idOrden, Timestamp entradaPunto) throws SQLException {
        DBConnection connection = DBConnection.getInstance();
         try (
                    PreparedStatement s = connection.getStatement(INSERT_LLEGADAPUNTO_BY_ORDEN);
            ){ 
                s.setInt(2,idOrden);
                s.setTimestamp(1, entradaPunto);
                s.execute();
            }catch(SQLException ex){
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
