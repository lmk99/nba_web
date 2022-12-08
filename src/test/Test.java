package test;

import dao.DBConn;
import swing.home_page.LoginPage;
import swing.user_page.UserMainMenu;

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
        String password = "Majiayi62717722!";

        // get connection
        conn = DBConn.getConn(username, password);

//        Login status = new Login(conn);
//        status.run();
//        UserMainMenu menu = new UserMainMenu(conn, "123");
        LoginPage home = new LoginPage(conn, "NBA info system");
    }
}
