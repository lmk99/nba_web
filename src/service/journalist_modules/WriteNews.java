package service.journalist_modules;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class WriteNews {
    Connection conn;
    String username;

    public WriteNews(Connection conn, String username) {
        this.conn = conn;
        this.username = username;

    }

    public void run() throws SQLException {
        Scanner s = new Scanner(System.in);
        String sql = "";
        ResultSet rs = null;
        System.out.println("Enter title:");
        String input_ti= s.nextLine();
        System.out.println("Enter summary:");
        String input_su= s.nextLine();

        sql = "insert into news (title, summary, author) values (?, ?, ?)";
        PreparedStatement preparedStmt = conn.prepareStatement(sql);
        preparedStmt.setString (1, input_ti);
        preparedStmt.setString (2, input_su);
        preparedStmt.setString (3, username);
        preparedStmt.execute();
        System.out.println("Upload it successfully!");
    }
}
