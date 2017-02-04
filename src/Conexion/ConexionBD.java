/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author kelly Jimenez
 * 
 */
public class ConexionBD {

    
    private static String dbName="semillas";
    private static String user="root";
    private static String password="root";
    private static String portservice="3306";
    private static String url="jdbc:mysql://localhost/"+dbName;
    
    public static Connection getConnection () throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, password);
    }
    
     public static void main(String[] args) throws SQLException {
       Connection con = getConnection();
        System.out.println("Conexión Exitosa" );
        con.close();
    }
    
}
