package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUpdate {

    public static int getResultSet(Connection conn, String sql) {
        // modify database and return modified state
        Statement stmt = null;
        int affectedRow = 0;

        try {
            stmt = conn.createStatement();
            affectedRow = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return affectedRow;
    }
}
