package service.user_modules;

import dao.DBConn;
import dao.DBQuery;
import dao.DBUpdate;
import service.UserMainMenu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FollowModule {
    Connection conn;
    String username;

    public FollowModule(Connection conn, String username) {
        this.conn = conn;
        this.username = username;
    }

    // follow teams module
    public void followTeams() {
        try {
            String sql;
            Statement stmt;
            ResultSet rs;

            // list all the team name
            sql = "SELECT full_name, abbreviation, nickname FROM team";
            rs = DBQuery.getResultSet(conn, sql);

            System.out.printf("%-25s %-20s %-20s\n", "team name", "abbreviation", "nickname");
            while (rs.next()) {
                System.out.printf("%-25s %-20s %-20s\n", rs.getString("full_name"),
                        rs.getString("abbreviation"), rs.getString("nickname"));
            }
            System.out.println();

            int command;
            while (true) {
                // search for the team username has followed
                //get team id from user_follow table
                sql = "SELECT abbreviation FROM user_follow JOIN team ON " +
                        "user_follow.team_id=team.id WHERE username=" + username;
                rs = DBQuery.getResultSet(conn, sql);
                if (rs.isBeforeFirst()) {
                    // find the corresponding
                    System.out.println("You have already followed: ");
                    while (rs.next()) {
                        System.out.print(rs.getString("abbreviation") + "   ");
                    }
                    System.out.println();
                }
                System.out.println();

                // prompt for user to input an abbreviation
                Scanner sc = new Scanner(System.in);
                System.out.print("Input your team: ");
                String abb = sc.nextLine();

                // search for pointed team and get its team_id
                sql = "SELECT id FROM team WHERE abbreviation=" + "'" + abb + "'";
                rs = DBQuery.getResultSet(conn, sql);
                if (!rs.isBeforeFirst())
                    System.out.println("Team Not Found, follow fail");
                else {
                    rs.next();
                    int teamId = rs.getInt("id");
                    // add the username and team id to user_follow table
                    sql = "INSERT INTO user_follow VALUES(" + username + ", " + teamId + ")";
                    int updatedRow = DBUpdate.getResultSet(conn, sql);
                    if (updatedRow == 1)
                        System.out.println("You have successfully follow " + abb);
                    else
                        System.out.println("Follow fail, please try again");
                }

                // query next move
                System.out.println("1-continue follow, 2-back to main menu, other-exit");
                System.out.print("Your input: ");
                command = sc.nextInt();

                if (command == 1)
                    continue;
                else if (command == 2)
                    break;
                else
                    break;
            }

            if (command == 2) {
                System.out.println();
                new UserMainMenu(conn, username).service();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
