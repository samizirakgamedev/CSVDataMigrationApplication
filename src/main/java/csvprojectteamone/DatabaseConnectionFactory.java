package csvprojectteamone;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {
    private static Connection connection = null;

    public static Connection getConnection() throws IOException, SQLException {
        if (connection == null) {
            //initialise the connection
            Properties props = new Properties();
            props.load(new FileReader("mysql.properties"));
            connection = DriverManager.getConnection(
                    props.getProperty("dburl"),
                    props.getProperty("dbuserid"),
                    props.getProperty("dbpassword"));
        }
        return connection;
    }
    public static void closeConnection() throws SQLException {
        if(connection != null){
            connection.close();
        }
    }
    private DatabaseConnectionFactory() {
    }
}
