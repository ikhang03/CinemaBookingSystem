/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DBConnection;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private int userID;

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }

    public void connectToDatabase() throws SQLException {
        String server = "localhost";
        String port = "3307";
        String database = "ticketcinemabookingsystem";
        String userName = "root";
        String password = "";
        connection = java.sql.DriverManager.getConnection("jdbc:mysql://" + server + ":" + port + "/" + database, userName, password);
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    public void setUserID(int id) {
        this.userID = id;
    }

    public int getUserID() {
        return userID;
    }

}
