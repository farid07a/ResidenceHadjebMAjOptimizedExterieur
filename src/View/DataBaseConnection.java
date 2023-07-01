/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author farid
 */
public class DataBaseConnection {
    private static DataBaseConnection instance;
    private Connection connection;
    public static DataBaseConnection getInstance(){
        if (instance==null) {
            instance=new DataBaseConnection();
        }
        return instance;
    }
    
    private DataBaseConnection(){
    
    }
    
    public void ConnectionToDatabase() throws SQLException , ClassNotFoundException{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Pilot is download ...");
            //String url = "jdbc:sqlserver://192.168.1.10:1433;user=sa;password=farid;databaseName=DB_Residence";
            String url="jdbc:sqlserver://localhost:1433;user=sa;password=farid;databaseName=DB_Residence";

            connection = DriverManager.getConnection(url);        
    }
    
    public Connection getConnection(){
        return connection;
    }
}
