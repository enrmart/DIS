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
 * Clase que se encarga de los accesos a la base de datos relacionados con cliente.
 * @author enrmart,francu,carlgar
 */
class ClienteDAO {
    
    private static final String SELECT_CLIENTE_BY_NIF="SELECT C.NumeroCliente FROM CLIENTES C WHERE C.Nif=?";
    
    /**
     * Metodo que devuelve el cliente con el nif asociado.
     * @param nif del cliente a obtener
     * @return datos del cliente esn un String
     * @throws DatosNoEncontradosException si no se encuentran datos
     * @throws SQLException si hay error de base de datos
     */
    public static String getCliente(String nif) throws DatosNoEncontradosException{
        int numeroCliente=0;
        DBConnection connection = DBConnection.getInstance();
        try (

                    PreparedStatement s = connection.getStatement(SELECT_CLIENTE_BY_NIF);
            ){ 
                s.setString(1, nif);
                ResultSet result = s.executeQuery();
                if(result.next()){
                    numeroCliente=result.getInt(1);
                }
              
            result.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        String datosCliente=obtenerClienteJsonString(nif,numeroCliente);
        return datosCliente;
    }
     
     private static String obtenerClienteJsonString(String id,int numero) {
            String clienteJsonString="";
              try (                
                    StringWriter stringWriter = new StringWriter();
                    JsonWriter writer = Json.createWriter(stringWriter);
                    ){

                JsonObject vehiculoJson = Json.createObjectBuilder()
                        .add("nif",id)
                        .add("numeroCliente",numero)
                        .build();
                writer.writeObject(vehiculoJson);
                clienteJsonString = stringWriter.toString();
            } catch(Exception ex) {
                Logger.getLogger(Registry.class.getName()).log(Level.SEVERE, null, ex);
            }                 
            return clienteJsonString;
        }
}
