package residence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ConnectionDB {

    private Connection cnx;

    public ConnectionDB() {
        //Connecting();
    }

    public Connection getConnect() {
        return cnx;
    }

    public void Deconnect() {
        try {
            cnx.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error in Deconnect");
        }
    }

    public void Connecting() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            System.out.println("Pilot is download ...");
            //String url = "jdbc:sqlserver://192.168.1.10:1433;user=sa;password=farid;databaseName=DB_Residence";
            String url="jdbc:sqlserver://localhost:1433;user=sa;password=farid;databaseName=DB_Residence";

            cnx = DriverManager.getConnection(url);

            // JOptionPane.showMessageDialog(null, "The connection is etablished...");
        } catch (SQLException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "error cannot connect server... !" + e.getMessage());
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        new ConnectionDB().Connecting();
    }
}
