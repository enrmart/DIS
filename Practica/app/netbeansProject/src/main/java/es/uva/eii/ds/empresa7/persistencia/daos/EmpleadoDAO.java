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
 * Clase que se encarga de las consultas a la base de datos relacionadas con Empleado.
 * @author enrmart,fracura,carlgar
 */
public class EmpleadoDAO {
    public static final String SELECT_EMPLEADO_BY_LOGIN = "SELECT U.Nif, U.Nombre,e.FechaInicioEnEmpresa, TD.NombreTipo, TR.NombreTipo, e.DestinadoEn " +
"FROM EMPLEADOS e " +
"INNER JOIN USUARIOS U ON e.Nif = U.Nif " +
"INNER JOIN VINCULACIONESCONLAEMPRESA V ON e.Nif = V.Empleado " +
"INNER JOIN DISPONIBILIDADES D ON e.Nif = D.Empleado " +
"INNER JOIN ROLESENEMPRESA R ON e.Nif = R.Empleado " +
"INNER JOIN TIPOSDEROL TR ON TR.IdTipo=R.Rol " +     
"INNER JOIN TIPOSDEDISPONIBILIDAD TD ON TD.IdTipo=D.Disponibilidad "+
"WHERE U.Nif = ? AND U.Password = ? " +
"AND V.Vinculo = 1 AND D.Disponibilidad = 3";
    
    /**
     * Busca un empleado en la base de datos con el nombre de usuario y contraseña especificados y 
     * devuelve una representación en formato JSON del empleado encontrado.
     * @param login el nombre de usuario del empleado que se desea buscar
     * @param password la contraseña del empleado que se desea buscar
     * 
     * @return una cadena en formato JSON que representa al empleado encontrado o una cadena vacía 
     * si no se encuentra ningún empleado con las credenciales proporcionadas
     * @throws SQLException si hay error de base de datos
     * @throws DatosNoEncontradosException si no se encuentran los datos
     */
    public static String consultaEmpleadoPorLoginYPassword(String login,String password) throws SQLException,DatosNoEncontradosException {
        String nombre="";
        String fecha="";
        String disponibilidad = "";
        String rol="";
        int punto=0;
        DBConnection connection = DBConnection.getInstance();
        connection.openConnection();
        try (
                PreparedStatement s = connection.getStatement(SELECT_EMPLEADO_BY_LOGIN);
        ){ 
            s.setString(1, login);
            s.setString(2, password);
            ResultSet result = s.executeQuery();
            if(result.next()){
                    nombre=result.getString(2);
                    fecha=result.getString(3);
                    disponibilidad = result.getString(4);
                    rol=result.getString(5);
                    punto=result.getInt(6);
            }else{
                throw new DatosNoEncontradosException("No existe ningun empleado con ese login y esa contraseña que este enActivo");
            } 
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String empleadoJsonString = obtenerEmpleadoJsonString(login,nombre,fecha,disponibilidad,rol);
        String puntoJsonString=PuntoDAO.consultaPunto(punto);
        // Crer objetos JSON a partir de los strings JSON
        JSONObject puntoJSON = new JSONObject(puntoJsonString);
        JSONObject empleadoJSON = new JSONObject(empleadoJsonString);

        // Combinar los objetos JSON
        JSONObject combinedJson = new JSONObject();
        combinedJson.put("punto", puntoJSON);
        combinedJson.put("empleado", empleadoJSON);

        // Obtener el string JSON combinado
        String combinedJsonString = combinedJson.toString();
        
        return combinedJsonString;
    }

     private static String obtenerEmpleadoJsonString(String login, String nombre,String fecha, String disp,String rol) {
        String empleadoJsonString="";
        try (                
                StringWriter stringWriter = new StringWriter();
                JsonWriter writer = Json.createWriter(stringWriter);
                ){
            
            JsonObject empleadoJson = Json.createObjectBuilder()
                    .add("Nif", login)
                    .add("nombre", nombre)
                    .add("FechaInicioEnEmpresa", fecha)
                    .add("Disponibilidad", disp)
                    .add("Rol",rol)
                    .build();
            writer.writeObject(empleadoJson);
            empleadoJsonString = stringWriter.toString();
        } catch(Exception ex) {
            Logger.getLogger(Registry.class.getName()).log(Level.SEVERE, null, ex);
        }             
        return empleadoJsonString;
    }


}
