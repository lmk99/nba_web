package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBQuery {

    public static ResultSet getResultSet(Connection conn, String sql) {
        // execute query and return a resultset
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // get a statement
            stmt = conn.createStatement();
            // execute sql
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }
}
