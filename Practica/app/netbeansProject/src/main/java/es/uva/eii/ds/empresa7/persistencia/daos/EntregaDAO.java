package es.uva.eii.ds.empresa7.persistencia.daos;


import es.uva.eii.ds.empresa7.persistencia.dbaccess.DBConnection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *Clase que se encarga de las consultas a la base de datos relacionadas con Entrega.
 *@author enrmart, fracura, carlgar
 */
public class EntregaDAO {
    
    
    private static final String INSERT_DATOS_DEVOLUCION = "INSERT INTO ENTREGAS (Id, Momento, Alquiler, Empleado) VALUES (?,?,?,?)";
    private static final String SELECT_MAX_ID = "SELECT COALESCE(MAX(Id) + 1, 1) AS NextId FROM ENTREGAS";
    

    /**
     * Metodo que inserta una ENTREGA en la base de datos.
     * @param id de la entrega
     * @param momento de la entrega
     * @param nifEmpleado del empleado asociado
     * @param alquiler del alquiler asociado
     */
    public static void anotaDevolucion(int id, Date momento, String nifEmpleado, int alquiler){
        DBConnection connection = DBConnection.getInstance();
        try (

                    PreparedStatement s = connection.getStatement(INSERT_DATOS_DEVOLUCION);
            ){ 
            java.sql.Date fecha = new java.sql.Date(momento.getTime());
                s.setInt(1, id);
                s.setDate(2, fecha);
                s.setInt(3, alquiler);
                s.setString(4, nifEmpleado);
                s.executeUpdate();
           
            } catch (SQLException ex) {
                Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Metodo que busca el id mas alto usado en la base de datos y devuelvve ese +1.
     * @return el valor para el proximo id en la base si no hay devuelve el 1
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
