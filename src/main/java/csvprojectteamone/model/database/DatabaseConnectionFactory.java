package csvprojectteamone.model.database;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {
    //Initialises the connection variable
    private static Connection connection = null;
    //Method to create a connection if it does not exist or get the connection.
    public static Connection getConnection() throws IOException, SQLException {
        if (connection == null) {
            //initialise the connection using the properties file
            Properties props = new Properties();
            props.load(new FileReader("mysql.properties"));
            connection = DriverManager.getConnection(
                    props.getProperty("dburl"),
                    props.getProperty("dbuserid"),
                    props.getProperty("dbpassword"));
        }
        return connection;
    }
    //Closes the connection to the database
    public static void closeConnection() throws SQLException {
        //Close the connection
        if(connection != null){
            connection.close();
        }
    }
    //Private constructor to make it a singleton
    private DatabaseConnectionFactory() {
    }
}
