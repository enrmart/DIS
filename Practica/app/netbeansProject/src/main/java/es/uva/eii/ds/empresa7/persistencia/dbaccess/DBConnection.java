/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.uva.eii.ds.empresa7.persistencia.dbaccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


/**
 * 
 * This class represents a singleton instance of a database connection.
 * 
 *It is used to connect to a database, create statements and close the connection.
 *
 * @author yania
*/
public class DBConnection {
    
    // class level (singleton)
    private static DBConnection theDBConnection;
    
    /**
     * Devuelve una instancia de la clase DBConnection, creándola si aún no existe.
     *
     * @return una instancia de la clase DBConnection
     */
    public static DBConnection getInstance() {
        if (theDBConnection == null) {
            Properties prop = new Properties();
            InputStream read; 
            String url, user, password;
            try {                   
                read = DBConnection.class.getResourceAsStream("config.db");                
                prop.load(read);
                url = prop.getProperty("url");
                user = prop.getProperty("user");
                password = prop.getProperty("password");
                read.close(); 
                theDBConnection = new DBConnection(url, user, password);
            } catch (FileNotFoundException e) {
                System.err.println("DB configuration file not found.");
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, "DB configuration file not found.");       
               
            } catch (IOException e) {
                System.err.println("Couln't read DB configuration file.");
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, "Couln't read DB configuration file.");        
            }      
        }
        return theDBConnection;
    }

    // instance level
    private Connection conn;
    private String url;
    private String user;
    private String password;

    private DBConnection(String url, String user, String password) {        
        this.url = url;
        this.user = user;
        this.password = password;
        
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver"); 
        }
        catch (ClassNotFoundException ex) {
            System.err.println("Derby driver not found.");
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, "Derby driver not found.");
        }
    }

    /**
     * Este método establece una conexión con la base de datos.
     * Si se produce una excepción mientras se intenta abrir la conexión, se imprime un mensaje de error en la consola
     * y se registra la excepción.
     * 
     */
    public void openConnection() {
        try {
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Cierra la conexión actual a la base de datos.
     * Si ocurre algún error, lo registra en el registro de errores.
     * 
     */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     *Obtiene un objeto PreparedStatement para realizar una consulta SQL preparada en la base de datos.
     *
     *@param s la consulta SQL preparada
     *@return un objeto PreparedStatement para ejecutar la consulta SQL preparada
     */
    public PreparedStatement getStatement(String s) {
        PreparedStatement stmt = null;
        try {
                stmt = conn.prepareStatement(s);
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
                Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stmt;
    }
    
}