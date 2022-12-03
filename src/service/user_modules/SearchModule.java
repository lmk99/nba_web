package service.user_modules;

import service.user_modules.search_modules.SearchTeamModule;

import java.sql.Connection;
import java.util.Scanner;

public class SearchModule {
    public Connection conn;
    public String username;

    public SearchModule(Connection conn, String username) {
        this.conn = conn;
        this.username = username;
    }

    public void run() {
        System.out.println("1-team, 2-player, 3-game");
        Scanner sc = new Scanner(System.in);
        int command = sc.nextInt();
        if (command == 1)
            new SearchTeamModule(conn, username).run();
    }
}
