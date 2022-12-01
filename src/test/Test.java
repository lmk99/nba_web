package test;

import dao.DBConn;
import service.Login;
import service.UserMainMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int update_row = 0;
        String sql = "";
        String username = "root";
        String password = "12345678";

        // get connection
        conn = DBConn.getConn(username, password);

        Login status = new Login(conn);
        status.run();
    }
}
