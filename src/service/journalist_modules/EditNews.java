package service.journalist_modules;

import dao.DBQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EditNews {
    Connection conn;
    String username;

    public EditNews(Connection conn, String username) {
        this.conn = conn;
        this.username = username;

    }

    public void run() throws SQLException {
        Scanner s = new Scanner(System.in);
        String sql = "";
        ResultSet rs = null;
        sql = "SELECT * FROM news";
        rs = DBQuery.getResultSet(conn, sql);
        while (rs.next()) {
            String id = rs.getString("id");
            String title = rs.getString("title");
            String summary = rs.getString("summary");
            System.out.println(id);
            System.out.println(title);
            System.out.println(summary);
        }
        //  现在列了所有news， 要改成仅显示该user写的
        // 如果没有，ask them to write one first
        boolean d = false;
        while(!d){
            System.out.println("Enter id of the news you want to edit:");
            String input_nid= s.nextLine();
            System.out.println("1 - Edit title, 2 - Edit summary, 3 - quit");
            String input_eo= s.nextLine();
            if(input_eo.equals("1")){
                System.out.println("Enter a new title:");
                String input_title= s.nextLine();
                sql = "UPDATE news SET title = ?, author = ? WHERE id = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString (1, input_title);
                preparedStmt.setString (2, username);
                preparedStmt.setString (3, input_nid);
                preparedStmt.execute();
            }
            if(input_eo.equals("2")){
                System.out.println("Enter a new summary:");
                String input_sum= s.nextLine();
                sql = "UPDATE news SET summary = ?, author = ? WHERE id = ?";
                PreparedStatement preparedStmt = conn.prepareStatement(sql);
                preparedStmt.setString (1, input_sum);
                preparedStmt.setString (2, username);
                preparedStmt.setString (3, input_nid);
                preparedStmt.execute();
            }
            if(input_eo.equals("3")){
                d = true;
                System.out.println("Exit!");
            }
        }

    }
}
