package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// Mysql database connector
public class DBConn {

    // prepare constants
    private final String serverName = "localhost";
    private final int portNumber = 3306;
    private final String dbName = "nba_data";
    private final boolean useSSL = false;

    // constructor
    public DBConn(String username, String password) {}

    // get the database connection
    public static Connection getConn(String username, String password) {
        Connection conn = null;
        try {
            // load Mysql driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // connect to database
            String url = "jdbc:mysql://localhost:3306/nba_data?characterEncoding=UTF-8&SSL=false";
            conn = DriverManager.getConnection(url, username, password);

            System.out.println("Database is successfully connected");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return conn;
    }
}
