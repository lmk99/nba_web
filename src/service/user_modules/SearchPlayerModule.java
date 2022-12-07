package service.user_modules;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchPlayerModule extends SearchModule {
    public SearchPlayerModule(Connection conn) {
        super(conn);
    }

    public List<List<String>> getAllPlayers(List<List<Integer>> index) {
        try {
            String sql;
            ResultSet rs;

            sql = "SELECT player.id, team_id, abbreviation, player.full_name, first_name, last_name FROM player " +
                    "LEFT OUTER JOIN team ON player.team_id = team.id ORDER BY abbreviation DESC";
            rs = super.conn.createStatement().executeQuery(sql);

            if (!rs.isBeforeFirst())
                return null;

            List<List<String>> res = new LinkedList<>();
            while (rs.next()) {
                List<String> row = new LinkedList<>();
                row.add(rs.getString("abbreviation"));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("first_name"));
                row.add(rs.getString("last_name"));
                List<Integer> indexRow = new ArrayList<>();
                indexRow.add(rs.getInt("id"));
                indexRow.add(rs.getInt("team_id"));
                res.add(row);
                index.add(indexRow);
            }

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<List<String>> getTeamPlayers(int teamId) {
        try {
            String sql;
            ResultSet rs;
            CallableStatement cstmt;

            // search for players
            sql = "CALL getTeamPlayers(?)";
            cstmt = super.conn.prepareCall(sql);
            cstmt.clearParameters();
            cstmt.setInt(1, teamId);
            rs = cstmt.executeQuery();

            if (!rs.isBeforeFirst())
                return null;

            List<List<String>> res = new LinkedList<>();
            while (rs.next()) {
                List<String> row = new LinkedList<>();
                row.add(rs.getString("abbreviation"));
                row.add(rs.getString("full_name"));
                row.add(rs.getString("match_count"));
                row.add(rs.getString("scores"));
                row.add(rs.getString("assists"));
                row.add(rs.getString("rebound"));
                res.add(row);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<List<String>> getPlayerDetails(int playerId) {
        try {
            String sql = "CALL getPlayerDetails(?)";
            ResultSet rs;

            CallableStatement cstmt = super.conn.prepareCall(sql);
            cstmt.clearParameters();
            cstmt.setInt(1, playerId);
            rs = cstmt.executeQuery();

            if (!rs.isBeforeFirst())
                return null;

            List<List<String>> res = new LinkedList<>();
            while (rs.next()) {
                List<String> row = new LinkedList<>();
                row.add(rs.getString("team"));
                row.add(rs.getString("name"));
                row.add(rs.getString("match_count"));
                row.add(rs.getString("scores"));
                row.add(rs.getString("assists"));
                row.add(rs.getString("rebound"));
                row.add(rs.getString("three_pointers_scores"));
                row.add(rs.getString("free_throw_scores"));
                row.add(rs.getString("steals"));
                row.add(rs.getString("blocks"));
                row.add(rs.getString("fouls"));
                res.add(row);
            }
            return res;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getPlayerIdByName(String name) {
        try {
            String sql = "SELECT id FROM player WHERE full_name=" + "'" + name + "'";
            ResultSet rs = conn.createStatement().executeQuery(sql);

            if (!rs.isBeforeFirst())
                return 0;

            rs.next();
            return rs.getInt("id");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
