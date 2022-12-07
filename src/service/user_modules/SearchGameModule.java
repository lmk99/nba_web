package service.user_modules;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SearchGameModule extends SearchModule {

    public SearchGameModule(Connection conn) { super(conn); }

    public List<List<String>> getAllGames(List<List<Integer>> index) {
        try {
            String sql;
            ResultSet rs;

            sql = "CALL getAllGames()";
            CallableStatement cstmt = super.conn.prepareCall(sql);
            rs = cstmt.executeQuery();

            if (!rs.isBeforeFirst())
                return null;

            List<List<String>> res = new LinkedList<>();
            while (rs.next()) {
                List<String> row = new LinkedList<>();
                row.add(rs.getString("season"));
                row.add(rs.getString("date"));
                row.add(rs.getString("home"));
                row.add(rs.getString("away"));
                row.add(rs.getString("winner"));
                row.add(rs.getString("scores"));
                row.add(rs.getString("assists"));
                row.add(rs.getString("rebound"));
                List<Integer> indexRow = new ArrayList<>();
                indexRow.add(rs.getInt("id"));
                indexRow.add(rs.getInt("home_team_id"));
                indexRow.add(rs.getInt("away_team_id"));
                res.add(row);
                index.add(indexRow);
            }

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<List<String>> getPlayerGames(int playerId) {
        try {
            String sql;
            ResultSet rs;

            sql = "CALL getPlayerGames(?)";
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
                row.add(rs.getString("min"));
                row.add(rs.getString("pts"));
                row.add(rs.getString("fgm"));
                row.add(rs.getString("fga"));
                row.add(rs.getString("fg3m"));
                row.add(rs.getString("fg3a"));
                row.add(rs.getString("ftm"));
                row.add(rs.getString("fta"));
                row.add(rs.getString("oreb"));
                row.add(rs.getString("dreb"));
                row.add(rs.getString("reb"));
                row.add(rs.getString("ast"));
                row.add(rs.getString("blk"));
                row.add(rs.getString("_to"));
                row.add(rs.getString("pf"));
                res.add(row);
            }

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<List<String>> getGameDetails(int gameId) {
        try {
            String sql = "SELECT * FROM game_details WHERE game_id=" + gameId;
            ResultSet rs = super.conn.prepareCall(sql).executeQuery();

            if (!rs.isBeforeFirst())
                return null;

            List<List<String>> res = new LinkedList<>();
            while (rs.next()) {
                List<String> row = new LinkedList<>();
                row.add(rs.getString("game_id"));
                row.add(rs.getString("team_id"));
                row.add(rs.getString("player_id"));
                row.add(rs.getString("min"));
                row.add(rs.getString("pts"));
                row.add(rs.getString("fgm"));
                row.add(rs.getString("fga"));
                row.add(rs.getString("fg3m"));
                row.add(rs.getString("fg3a"));
                row.add(rs.getString("ftm"));
                row.add(rs.getString("fta"));
                row.add(rs.getString("oreb"));
                row.add(rs.getString("dreb"));
                row.add(rs.getString("reb"));
                row.add(rs.getString("ast"));
                row.add(rs.getString("blk"));
                row.add(rs.getString("_to"));
                row.add(rs.getString("pf"));
                res.add(row);
            }

            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
