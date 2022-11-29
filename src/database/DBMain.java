package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBMain {
    public static void main(String[] args) throws SQLException {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int update_row = 0;
        String sql = "";
        String username = "root";
        String password = "12345678";

        // get connection
        conn = DBConn.getConn(username, password);

        // query
        sql = "SELECT * FROM player";

        // execute query
        rs = DBQuery.getResultSet(conn, sql);

        // display result
        System.out.println("query result: ");
        while (rs.next()) {
            int rowId = rs.getRow();
            String fullName = rs.getString("full_name");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
        }

        // update
        sql = "UPDATE player SET full_name = '123' WHERE first_name = '123'";

        // execute
        update_row = DBUpdate.getResultSet(conn, sql);

        System.out.println("update result: ");
        if (update_row > 0)
            System.out.println("successfully update. affect " + update_row + " row");
        else
            System.out.println("unaffected");
    }
}
