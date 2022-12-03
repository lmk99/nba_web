package service.user_modules;

import dao.DBQuery;
import swing.user_page.UserMainMenu;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SearchTeamModule {
    Connection conn;
    String username;

    public SearchTeamModule(Connection conn, String username) {
        this.conn = conn;
        this.username = username;
    }

    public List<List<String>> getAllTeams() {
        try {
            String sql;
            ResultSet rs;

            // list all teams' brief information
            sql = "CALL getBriefTeam()";
            CallableStatement cstmt = conn.prepareCall(sql);
            rs = cstmt.executeQuery();

            List<List<String>> res = new LinkedList<>();
            while (rs.next()) {
                List<String> row = new LinkedList<>();
                row.add(rs.getString("full_name"));
                row.add(rs.getString("abbreviation"));
                res.add(row);
            }

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public int getTeamId(String abb) {
        try {
            String sql;
            ResultSet rs;

            sql = "SELECT id FROM team WHERE abbreviation=" + "'" + abb + "'";
            rs = DBQuery.getResultSet(conn, sql);

            if (!rs.isBeforeFirst())
                return -1;
            else {
                rs.next();
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<List<String>> searchTeamBasic(int teamId) {
        try {
            String sql;
            ResultSet rs;

            sql = "CALL getTeamAndArena(?)";
            CallableStatement cstmt = conn.prepareCall(sql);
            cstmt.clearParameters();
            cstmt.setInt(1, teamId);
            rs = cstmt.executeQuery();

            List<List<String>> res = new LinkedList<>();
            while (rs.next()) {
                List<String> row = new LinkedList<>();
                row.add(rs.getString("full_name"));
                row.add(rs.getString("abbreviation"));
                row.add(rs.getString("arena_name"));
                row.add(rs.getString("city"));
                row.add(rs.getString("state"));
                row.add(rs.getString("year_founded"));
                row.add(rs.getString("owner"));
                res.add(row);
            }

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<List<String>> searchTeamPlayer(int teamId) {
        try {
            String sql;
            ResultSet rs;
            CallableStatement cstmt;

            // search for players
            sql = "CALL searchPlayersByTeam(?)";
            cstmt = conn.prepareCall(sql);
            cstmt.clearParameters();
            cstmt.setInt(1, teamId);
            rs = cstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<List<String>> searchTeamStaff(int teamId) {
        return null;
    }
}
