package service.user_modules;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

public class SearchModule {
    public Connection conn;

    public SearchModule(Connection conn) { this.conn = conn; }

    // search keyword globally, including team abbreviation, and player name
    public int[] search(String input) {
        // invoke search player and search team module's method
        int teamId = new SearchTeamModule(conn).getTeamIdByAbb(input);
        int playerId = new SearchPlayerModule(conn).getPlayerIdByName(input);

        int[] res = new int[2];
        if (teamId != 0) {
            res[0] = 1;
            res[1] = teamId;
            return res;
        } else if (playerId != 0) {
            res[0] = 2;
            res[1] = playerId;
        }

        return res;
    }
}
