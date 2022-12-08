package service.user_modules;

import dao.DBQuery;
import swing.user_page.UserMainMenu;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SearchTeamModule extends SearchModule {

    public SearchTeamModule(Connection conn) {
        super(conn);
    }

    // retrieve all the teams
    public List<List<String>> getAllTeams(List<Integer> index) {
        try {
            String sql;
            ResultSet rs;

            // list all teams' brief information
            sql = "CALL getBriefTeam()";
            CallableStatement cstmt = super.conn.prepareCall(sql);
            rs = cstmt.executeQuery();

            // if rs is empty, return null
            if (!rs.isBeforeFirst())
                return null;

            List<List<String>> res = new LinkedList<>();
            while (rs.next()) {
                List<String> row = new LinkedList<>();
                row.add(rs.getString("full_name"));
                row.add(rs.getString("abbreviation"));
                row.add(rs.getString("nickname"));
                index.add(rs.getInt("id"));
                res.add(row);
            }

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // get team id if exists
    public int getTeamIdByAbb(String abb) {
        try {
            String sql;
            ResultSet rs;

            sql = "SELECT id FROM team WHERE abbreviation=" + "'" + abb + "'";
            rs = DBQuery.getResultSet(super.conn, sql);

            if (!rs.isBeforeFirst())
                return 0;
            else {
                rs.next();
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getTeamIdByPlayer(int playerId) {
        try {
            String sql = "SELECT team_id FROM player WHERE id=" + playerId;
            ResultSet rs;

            rs = conn.createStatement().executeQuery(sql);
            if (!rs.isBeforeFirst())
                return -1;

            rs.next();
            return rs.getInt("team_id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    public List<List<String>> getTeamDetail(int teamId) {
        try {
            String sql;
            ResultSet rs;

            sql = "CALL getTeamAndArena(?)";
            CallableStatement cstmt = super.conn.prepareCall(sql);
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
}
