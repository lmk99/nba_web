package dao;

import service.Login;

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
        String password = "Majiayi62717722!";

        // get connection
        conn = DBConn.getConn(username, password);

        Login status = new Login(conn);
        status.run();
    }
}
