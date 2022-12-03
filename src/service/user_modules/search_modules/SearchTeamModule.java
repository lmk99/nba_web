package service.user_modules.search_modules;

import dao.DBQuery;
import swing.user_page.UserMainMenu;

import java.sql.*;
import java.util.Scanner;

public class SearchTeamModule {
    Connection conn;
    String username;

    public SearchTeamModule(Connection conn, String username) {
        this.conn = conn;
        this.username = username;
    }

    public void run() {
        try {
            String sql;
            ResultSet rs;

            // list all teams' brief information
            sql = "CALL getBriefTeam()";
            CallableStatement cstmt = conn.prepareCall(sql);
            rs = cstmt.executeQuery();

            System.out.printf("%-25s %-20s %-20s\n", "team name", "abbreviation", "nickname");
            while (rs.next()) {
                System.out.printf("%-25s %-20s %-20s\n", rs.getString("full_name"),
                        rs.getString("abbreviation"), rs.getString("nickname"));
            }
            System.out.println();

            int command;
            while (true) {
                // prompt user to enter a abbreviation of a team
                System.out.print("Enter the team abbreviation you want to search: ");
                Scanner sc = new Scanner(System.in);
                String abb = sc.nextLine();

                // search for abb and get its team id
                sql = "SELECT id FROM team WHERE abbreviation=" + "'" + abb + "'";
                rs = DBQuery.getResultSet(conn, sql);
                if (!rs.isBeforeFirst()) {
                    System.out.println("team doesn't exist, please try again.");
                } else {    // display all related info
                    rs.next();
                    int teamId = rs.getInt("id");
                    // including team's player, team's arena, team's coach
                    // search for arena and coach
                    sql = "SELECT abbreviation, arena_name, head_coach, owner FROM team JOIN arena ON team.id = arena.team_id " +
                            "JOIN coach ON team.id = coach.team_id WHERE id = " + teamId;
                    rs = DBQuery.getResultSet(conn, sql);

                    System.out.printf("%-25s %-20s %-20s %-20s\n", "team abbreviation", "arena", "coach", "owner");
                    while (rs.next()) {
                        System.out.printf("%-25s %-20s %-20s\n", rs.getString("abbreviation"),
                                rs.getString("arena_name"), rs.getString("head_coach"),
                                rs.getString("owner"));
                    }
                    System.out.println();

                    // search for players
                    sql = "CALL searchPlayersByTeam(?)";
                    cstmt = conn.prepareCall(sql);
                    cstmt.clearParameters();
                    cstmt.setInt(1, teamId);
                    rs = cstmt.executeQuery();

                    System.out.printf("%-10s %-20s\n", "team", "player");
                    while (rs.next()) {
                        System.out.printf("%-10s %-20s\n", rs.getString("abbreviation"), rs.getString("full_name"));
                    }

                    // for each player, user can also choose to check details of the player

                }

                System.out.println("1-continue searching, 2-back to main menu, other-exit");
                command = sc.nextInt();
                if (command == 1) {
                    continue;
                } else if (command == 2) {
                    break;
                } else
                    break;
            }

            if (command == 2)
                new UserMainMenu(conn, username, "NBA info system");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
