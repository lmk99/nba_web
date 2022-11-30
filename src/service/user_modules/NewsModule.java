package service.user_modules;

import dao.DBQuery;
import service.UserMainMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class NewsModule {
    Connection conn;
    String username;

    public NewsModule(Connection conn, String username) {
        this.conn = conn;
        this.username = username;
    }

    // display news module
    public void displayNews() {
        try {
            // get a statement
            Statement stmt = conn.createStatement();
            String sql;
            ResultSet rs = null;

            // using resultset to get every 10 rows in the table if exists
            int page = 0;
            int command;
            while (true) {
                if (page < 0) {
                    System.out.println("Reach the front");
                    page = 0;
                }

                sql = "SELECT * FROM news LIMIT " + page + ", 10";
                rs = DBQuery.getResultSet(conn, sql);

                // iterate the result
                System.out.println("page: " + (page + 1));
                System.out.printf("%-80s %-30s %-30s %-30s\n", "title", "author", "publish date", "topic");
                while (rs.next()) {
                    System.out.printf("%-80s %-30s %-30s %-30s\n", rs.getString("title"),
                            rs.getString("author"), rs.getString("publish_date"),
                            rs.getString("topic"));
                }
                System.out.println();

                System.out.println("1-Next page, 2-Second page, 3-back to main menu, other-quit");
                System.out.print("input: ");
                Scanner sc = new Scanner(System.in);
                command = sc.nextInt();

                if (command == 1)
                    page++;
                else if (command == 2)
                    page--;
                else if (command == 3)
                    break;
                else
                    break;
            }

            if (command == 3) {
                System.out.println();
                new UserMainMenu(conn, username).service();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
