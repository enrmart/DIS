package es.uva.eii.ds.empresa7.persistencia.daos;

import es.uva.eii.ds.empresa7.persistencia.dbaccess.DBConnection;
import es.uva.eii.ds.empresa7.servicioscomunes.DatosNoEncontradosException;
import java.io.StringWriter;
import java.rmi.registry.Registry;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import org.json.JSONObject;

/**
 *Clase que se encarga de las consultas a la base de datos relacionadas con Vehiculo.
 *@author enrmart, fracura, carlgar
 */
public class VehiculoDAO {
    
private static final String SELECT_VEHICULOS_ASIGNADOS_FUERA = "SELECT V.Matricula, EV.Nombre, V.Color, Md.Nombre, M.Nombre "
        + "FROM Vehiculos V, Modelos Md, Marcas M, ESTADOSVEHICULO EV "
        + "WHERE V.SituadoEn <> V.PuntoAsignado and V.PuntoAsignado = ? AND V.Modelo = Md.Id AND Md.Marca = M.Id AND V.ESTADO=EV.ID";

private static final String UPDATE_ESTADO_VEHICULO = "UPDATE Vehiculos V SET V.Estado = ? WHERE V.Matricula=?";

private static final String SELECT_VEHICULOS_BY_MATRICULA_ORDEN="SELECT V.Matricula, EV.Nombre, V.Color, O.Id FROM Vehiculos V, ORDENESDEREPARACION O, REGISTROSENTRADASALIDATALLER R, ESTADOSVEHICULO EV "
        +"WHERE V.matricula=? AND O.Vehiculo=V.matricula AND O.Id=R.OrdenDeReparacion AND V.Estado=4 AND EV.Id=V.Estado";

private static final String SELECT_VEHICULOS_BY_MATRICULA="SELECT V.Matricula, EV.Nombre, V.Color, Md.Nombre, M.Nombre "
        + "FROM Vehiculos V,Modelos Md, Marcas M,ESTADOSVEHICULO EV WHERE V.matricula=? AND EV.Id=V.Estado";


    /**
     * Devuelve una coleccion de cadenas en formato JSON acerca de los vehiculos fuera de un punto
     * @param punto punto asignado de los vehiculos a consultar
     * @return listadoJSON
     * @throws DatosNoEncontradosException si no se encuentra ningun vehiculo
     */
    public static ArrayList<String> consultaVehiculosFueraPunto(int punto) throws DatosNoEncontradosException{
        DBConnection connection = DBConnection.getInstance();
        String matricula;
        String estado;
        String color;
        String modelo;
        String marca;
        ArrayList<String> listadoJSON=new ArrayList<>();
        try (

                    PreparedStatement s = connection.getStatement(SELECT_VEHICULOS_ASIGNADOS_FUERA);
            ){ 
                s.setInt(1, punto);
                ResultSet result = s.executeQuery();
                while(result.next()){
                        matricula=result.getString(1);
                        estado=result.getString(2);
                        color=result.getString(3);
                        modelo=result.getString(4);
                        marca=result.getString(5);
                        String vehiculoJSON=obtenerVehiculoJsonString(matricula,estado,color,modelo,marca);
                        listadoJSON.add(vehiculoJSON);
                }
                if(listadoJSON.isEmpty()){
                    throw new DatosNoEncontradosException("Ningun Vehiculo se encuentra situado en un punto distinto a su punto asignado");
                }
                result.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        return listadoJSON;
    }

    private static String obtenerVehiculoJsonString(String matr, String estado, String color, String nombreModelo, String nombreMarca) {
            String vehiculoJsonString="";
            try (                
                    StringWriter stringWriter = new StringWriter();
                    JsonWriter writer = Json.createWriter(stringWriter);
                    ){

                JsonObject vehiculoJson = Json.createObjectBuilder()
                        .add("matricula", matr)
                        .add("estado", estado)
                        .add("color", color)
                        .add("modelo", nombreModelo)
                        .add("marca", nombreMarca)
                        .build();
                writer.writeObject(vehiculoJson);
                vehiculoJsonString = stringWriter.toString();
            } catch(Exception ex) {
                Logger.getLogger(Registry.class.getName()).log(Level.SEVERE, null, ex);
            }             
            return vehiculoJsonString;
        }
    /**
     * Metodo que se encarga de actualizar el estado de un Vehiculo.
     * @param matricula del vehiculo
     * @param estadoN estado Nuevo
     */
    public static void actualizaEstado(String matricula,int estadoN) {
         DBConnection connection = DBConnection.getInstance();
        try (

                    PreparedStatement s = connection.getStatement(UPDATE_ESTADO_VEHICULO);
            ){ 
                s.setInt(1, estadoN);
                s.setString(2,matricula);
                s.executeUpdate();        
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    
    
    /**
     * Metodo que se encarga de devolver el Json de un Vehiculo a partir de una matricula.
     * @param matricula asociada al vehiculo
     * @return Json del vehiculo
     */ 
    public static String getVehiculo(String matricula) {
        DBConnection connection = DBConnection.getInstance();
        String estado="";
        String color="";
        String modelo="";
        String marca="";
        try (

                    PreparedStatement s = connection.getStatement(SELECT_VEHICULOS_BY_MATRICULA);
            ){ 
                s.setString(1, matricula);
                ResultSet result = s.executeQuery();
                if(result.next()){
                        matricula=result.getString(1);
                        estado=result.getString(2);
                        color=result.getString(3);
                        modelo=result.getString(4);
                        marca=result.getString(5);
                } else{
                    throw new IllegalArgumentException();
                }
            result.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            String vehiculoJSON=obtenerVehiculoJsonString(matricula,estado,color,modelo,marca);
        return vehiculoJSON;
    }
    
    
    
     /**
     * Metodo que devuelve un Json combinado de los datos de registro,orden y vehiculo asociados a una matricula.
     * @param matricula asociada a un vehiculo
     * @return Json de los datos combinados
     * @throws DatosNoEncontradosException si no se encuentran datos
     */
    public static String getDatosVehiculo(String matricula) throws DatosNoEncontradosException {
        DBConnection connection = DBConnection.getInstance();
        String estado="";
        String color="";
        String modelo="";
        String marca="";
        int idOrden=0;
        try (

                    PreparedStatement s = connection.getStatement(SELECT_VEHICULOS_BY_MATRICULA_ORDEN);
            ){ 
                s.setString(1, matricula);
                ResultSet result = s.executeQuery();
                if(result.next()){
                        matricula=result.getString(1);
                        estado=result.getString(2);
                        color=result.getString(3);
                        idOrden = result.getInt(4);
                } else{
                    throw new DatosNoEncontradosException("No existe ningun Vehiculo con esa matricula");
                }
            result.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String vehiculo=obtenerVehiculoJsonString(matricula,estado,color,modelo,marca);
        String orden=OrdenReparacionDAO.getDatosOrdenReparacion(idOrden);

        String registro=RegistroESDAO.obtenerRegistro(idOrden);
         // Crer objetos JSON a partir de los strings JSON
        JSONObject registroJSON = new JSONObject(registro);
        JSONObject ordenJSON = new JSONObject(orden);
        JSONObject vehiculoJSON = new JSONObject(vehiculo);

        // Combinar los objetos JSON
        JSONObject combinedJson = new JSONObject();
        combinedJson.put("registro", registroJSON);
        combinedJson.put("orden", ordenJSON);
        combinedJson.put("vehiculo", vehiculoJSON);

        // Obtener el string JSON combinado
        String combinedJsonString = combinedJson.toString();
        
        return combinedJsonString;
    }


}