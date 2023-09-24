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
import org.json.JSONObject;


/**
 *Clase que se encarga de las consultas a la base de datos relacionadas con Reserva.
 *@author enrmart, fracura, carlgar
 */
public class ReservaDAO {
    
    
    private static final String SELECT_VEHICULO_CLIENTE_POR_ID_RESERVA = "SELECT R.Vehiculo,ER.Nombre,R.puntoEntrega, A.Id,R.Cliente"
            + " FROM Reservas R, Alquileres A, EstadosReserva ER"
            +" WHERE A.Reserva=R.Id AND ER.Id=R.Estado AND R.Id = ?";
   
    private static final String UPDATE_ESTADO_RESERVA_POR_ID = "UPDATE Reservas R SET R.Estado = ? WHERE R.Id = ?";
    

    /**
     * Metodo que devuelve los datos cominados Json con un id dado; vehiculo,reserva,punto,cliente.
     * @param id de la reserva a consulta
     * @return String con todos los datos de vehiculo,reserva,punto,cliente
     * @throws DatosNoEncontradosException si no se encuentran los datos
    */
    public static String consultaReservaEnAlquiler(int id) throws DatosNoEncontradosException{
        String idReserva=String.valueOf(id);
        String matr= "";
        String estadoReserva= "";
        int puntoEntrega= 0;
        String dniCliente = "";
        String datosReserva;
        String idAlquiler="";
        DBConnection connection = DBConnection.getInstance();
        try (

                    PreparedStatement s = connection.getStatement(SELECT_VEHICULO_CLIENTE_POR_ID_RESERVA);
            ){ 
                s.setInt(1, id);
                ResultSet result = s.executeQuery();
                if(result.next()){
                    matr=result.getString(1);
                    estadoReserva = result.getString(2);
                    puntoEntrega = result.getInt(3);
                    dniCliente = result.getString(5);
                    idAlquiler=result.getString(4);
                }else{
                   throw new DatosNoEncontradosException("No existe ninguna reserva con ese id y que su estado sea enAlquiler");
                }
              
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        datosReserva = obtenerVehiculoReservaJsonString(idReserva,estadoReserva,idAlquiler);
        String vehiculo=VehiculoDAO.getVehiculo(matr);
        String cliente=ClienteDAO.getCliente(dniCliente);
        String punto=PuntoDAO.consultaPunto(puntoEntrega);
        
        // Crer objetos JSON a partir de los strings JSON
        JSONObject reservaJSON = new JSONObject(datosReserva);
        JSONObject vehiculoJSON = new JSONObject(vehiculo);
        JSONObject clienteJSON = new JSONObject(cliente);
        JSONObject puntoJSON = new JSONObject(punto);

        // Combinar los objetos JSON
        JSONObject combinedJson = new JSONObject();
        combinedJson.put("reserva", reservaJSON);
        combinedJson.put("vehiculo", vehiculoJSON);
        combinedJson.put("cliente", clienteJSON);
        combinedJson.put("punto", puntoJSON);

        // Obtener el string JSON combinado
        String combinedJsonString = combinedJson.toString();
        
        return combinedJsonString;
    }
    
    /**
     * Metodo que cambia el estado de una Reserva a 6.
     * @param id de la Reserva a cambiar
     * @param estadoN estado nuevo
     * @param matricula matricula del vehiculo asociada
     */
    public static void anotaEstado(int id,int estadoN,String matricula){
        
        DBConnection connection = DBConnection.getInstance();
        try (

                    PreparedStatement s = connection.getStatement(UPDATE_ESTADO_RESERVA_POR_ID);
            ){ 
                s.setInt(1, estadoN);
                s.setInt(2, id);
                s.executeUpdate();        
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        VehiculoDAO.actualizaEstado(matricula,estadoN);

    }
    
    private static String obtenerVehiculoReservaJsonString(String id, String estado,String idAlquiler) {
            String reservaJsonString="";
              try (                
                    StringWriter stringWriter = new StringWriter();
                    JsonWriter writer = Json.createWriter(stringWriter);
                    ){

                JsonObject vehiculoJson = Json.createObjectBuilder()
                        .add("idReserva",id)
                        .add("estadoReserva", estado)
                        .add("idAlquiler",idAlquiler)
                        .build();
                writer.writeObject(vehiculoJson);
                reservaJsonString = stringWriter.toString();
            } catch(Exception ex) {
                Logger.getLogger(Registry.class.getName()).log(Level.SEVERE, null, ex);
            }           
                         
            return reservaJsonString;
        }
    
    
}
