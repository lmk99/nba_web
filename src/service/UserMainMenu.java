package service;

import dao.DBConn;
import dao.DBQuery;
import service.user_modules.FollowModule;
import service.user_modules.NewsModule;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// command interface for user main menu
public class UserMainMenu {
    private String username;
    private Connection conn;

    public UserMainMenu(Connection conn, String username) {
        this.conn = conn;
        this.username = username;
    }

    // service module
    public void service() {
        System.out.println("Hi, " + username);
        System.out.println("Welcome to nba database, what do you want to do?");
        System.out.println("input: 1-check news, 2-follow team, 3-search player or game, other-exit");
        Scanner sc = new Scanner(System.in);
        System.out.print("Input: ");
        int command = sc.nextInt();

        if (command == 1)
            new NewsModule(conn, username).displayNews();
        else if (command == 2)
            new FollowModule(conn, username).followTeams();
    }
}
