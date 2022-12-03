package service.user_modules;

import dao.DBQuery;
import dao.DBUpdate;
import swing.user_page.UserMainMenu;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FollowModule {
    Connection conn;
    String username;

    public FollowModule(Connection conn, String username) {
        this.conn = conn;
        this.username = username;
    }

    public List<List<String>> getAllTeams() {
        try {
            String sql;
            ResultSet rs;

            // list all the team name to list all the teams
            sql = "CALL getBriefTeam()";
            CallableStatement cstmt = conn.prepareCall(sql);
            rs = cstmt.executeQuery();

            List<List<String>> res = new LinkedList<>();
            while (rs.next()) {
                List<String> row = new LinkedList<>();
                row.add(rs.getString("full_name"));
                row.add(rs.getString("abbreviation"));
                row.add(rs.getString("nickname"));
                res.add(row);
            }

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<String> getUserCurrentTeams() {
        try {
            String sql;
            ResultSet rs;

            // search for the team username has followed
            //get team id from user_follow table
            sql = "SELECT abbreviation FROM user_follow JOIN team ON " +
                    "user_follow.team_id=team.id WHERE username=" + "'" + username + "'";
            rs = DBQuery.getResultSet(conn, sql);
            if (rs.isBeforeFirst()) {
                // find the corresponding
                List<String> res = new LinkedList<>();
                while (rs.next()) {
                    res.add(rs.getString("abbreviation"));
                }

                return res;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int updateUserFollow(String abb, int operation) {
        try {
            String sql;
            ResultSet rs;

            // search for pointed team and get its team_id
            sql = "SELECT id FROM team WHERE abbreviation=" + "'" + abb + "'";
            rs = DBQuery.getResultSet(conn, sql);
            if (!rs.isBeforeFirst()) {
                System.out.println("Team Not Found, follow fail");
                return 1;   // 1 indicates update fails
            } else {
                rs.next();
                int teamId = rs.getInt("id");
                // follow a team
                if (operation == 0) {
                    // add the username and team id to user_follow table
                    sql = "INSERT INTO user_follow VALUES(" + "'" + username + "'" + ", " + "'" + teamId + "'" + ")";
                    int updatedRow = DBUpdate.getResultSet(conn, sql);
                    if (updatedRow == 1)
                        return 0;
                    else
                        return 1;
                } else if (operation == 1) {    // unfollow a team
                    sql = "DELETE FROM user_follow WHERE username=" + "'" + username + "'" + "AND team_id=" + teamId;
                    int updatedRow = DBUpdate.getResultSet(conn, sql);
                    if (updatedRow == 1)
                        return 0;
                    else
                        return 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;   // 0 indicate update completes
    }
}
