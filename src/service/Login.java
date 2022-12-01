package service;

import dao.DBQuery;
import service.journalist_modules.EditNews;
import service.journalist_modules.WriteNews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    private Connection conn;

    public Login(Connection conn) {
        this.conn = conn;
    }
    public void run() {
        try {
            Scanner s = new Scanner(System.in);
            String sql = "";
            ResultSet rs = null;
            boolean acc = false;
            String input_type = null;
            String input_email = null;
            while(!acc){
                System.out.println("Please choose your account type. 1: Fan, 2: Journalist");
                String input_no= s.nextLine();
                if(input_no.equals("1")) {
                    input_type = "FAN";
                    break;
                }
                else if(input_no.equals("2")){
                    input_type = "JOURNALIST";
                    break;
                }
            }
            boolean tmp = false;
            while(!tmp){
                System.out.println("Do you hava an account with us? Y/N");
                String input_ans = s.nextLine();
                if(input_ans.equalsIgnoreCase("y")){
                    System.out.println("Please log in!");
                    System.out.println("Enter your email: ");
                    input_email = s.nextLine();
                    System.out.println("Enter your password: ");
                    String input_password = s.nextLine();

                    sql = "SELECT * FROM " + input_type +"s WHERE email= ?";
                    PreparedStatement pstmt = conn.prepareStatement(sql);
                    pstmt.setString(1,input_email);
                    rs = pstmt.executeQuery();
                    if(rs.next()){
                        String correctPw = rs.getString(2);
                        if(input_password !=correctPw) {
                            while (!input_password.equals(correctPw)) {
                                System.out.println("Wrong password! Try again");
                                input_password = s.nextLine();
                            }
                        }
                        tmp = true;
                        System.out.println("Log in successfully!");
                    }else{
                        System.out.println("This email is not registered!");
                        input_ans = "n";
                    }
                }if(input_ans.equalsIgnoreCase("n")){
                    System.out.println("Please create an account!");
                    System.out.println("Enter your email: ");
                    input_email = s.nextLine();
                    System.out.println("Enter your password: ");
                    String input_password = s.nextLine();

                    System.out.println("Registered successfully!");
                    sql = "insert into " + input_type +"s (email, password) values (?, ?)";
                    PreparedStatement preparedStmt = conn.prepareStatement(sql);
                    preparedStmt.setString (1, input_email);
                    preparedStmt.setString (2, input_password);
                    preparedStmt.execute();

                    tmp = true;
                }
                if(!input_ans.equalsIgnoreCase("n") && !input_ans.equalsIgnoreCase("y")){
                    tmp = false;
                }
            }

            if(input_type.equals("JOURNALIST")) {

                boolean done = false;
                while(!done){
                    System.out.println("Menu:");
                    System.out.println("Enter 1 to edit news");
                    System.out.println("Enter 2 to write news");
                    System.out.println("Enter 3 to quit");
                    String input_me = s.nextLine();
                    if(input_me.equals("1")){
                        EditNews e = new EditNews(conn, input_email);
                        e.run();
                    }
                    if(input_me.equals("2")){
                        WriteNews e = new WriteNews(conn, input_email);
                        e.run();
                    }
                    if(input_me.equals("3")){
                        done = true;
                        conn.close();
                        System.out.println("Exit successfully!");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

