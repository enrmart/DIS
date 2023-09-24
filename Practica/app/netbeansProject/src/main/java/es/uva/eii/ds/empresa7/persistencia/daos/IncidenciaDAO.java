package es.uva.eii.ds.empresa7.persistencia.daos;


import es.uva.eii.ds.empresa7.persistencia.dbaccess.DBConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *Clase que se encarga de las consultas a la base de datos relacionadas con Incidencia.
 *@author enrmart, fracura, carlgar
 */
public class IncidenciaDAO {
    
    private static final String INSERT_DATOS_INCIDENCIA = "INSERT INTO INCIDENCIAS (Id, Tipo, Descripcion, CargoAsociado, Entrega) VALUES (?,?,?,?,?)";
    private static final String SELECT_MAX_ID = "SELECT COALESCE(MAX(Id) + 1, 1) AS NextId FROM INCIDENCIAS";
    
    
    /**
     * Metodo que se encarga de añadir una incidencia a la base de datos.
     * @param id de la incidencia
     * @param tipo de incidencia
     * @param descripcion de la incidencia
     * @param cargoAsociado a la incidencia
     * @param idEntrega de la entrega asociada a la incidencia.
     */
    public static void registraIncidencia(int id, int tipo, String descripcion, double cargoAsociado, int idEntrega){
        DBConnection connection = DBConnection.getInstance();
        try (

                    PreparedStatement s = connection.getStatement(INSERT_DATOS_INCIDENCIA);
            ){ 
                s.setInt(1, id);
                s.setInt(2, tipo);
                s.setString(3, descripcion);
                s.setDouble(4, cargoAsociado);
                s.setInt(5, idEntrega);
                s.executeUpdate();
           
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Metodo que devuelve el id más alto utilizado para una incidencia que uno mas estaría libre.
     * @return el id más alto +1.
     */
    public static int devuelveId(){
        DBConnection connection = DBConnection.getInstance();
        int id = 0;
        try (

                    PreparedStatement s = connection.getStatement(SELECT_MAX_ID);
            ){ 
                ResultSet result = s.executeQuery();   
                if(result.next()){
                    id = result.getInt(1);
                }
            result.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return id++;
    }
    
}
