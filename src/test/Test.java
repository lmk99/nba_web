package test;

import dao.DBConn;
import service.UserMainMenu;

import java.sql.Connection;

public class Test {
    public static void main(String[] args) {
        UserMainMenu menu = new UserMainMenu(DBConn.getConn("root", "12345678"), "123");
        menu.service();
    }
}
