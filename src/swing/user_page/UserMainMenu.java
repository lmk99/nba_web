package swing.user_page;

import dao.DBConn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

// command interface for user main menu
public class UserMainMenu extends JFrame {

    private JPanel contentPane;
    private JButton btn1, btn2, btn3;
    private JLabel jl1, jl2;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    private String username;
    private Connection conn;

    public UserMainMenu(Connection conn, String username, String title) {
        this.conn = conn;
        this.username = username;

        setTitle(title);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        jl1 = new JLabel("Hi, " + username, JLabel.LEFT);
        jl1.setBounds(20, 20, 690, 30);
        jl1.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        jl1.setForeground(Color.BLACK);
        contentPane.add(jl1);

        jl2 = new JLabel("What do you want to explore?", JLabel.LEFT);
        jl2.setBounds(20, 70, 690, 30);
        jl2.setFont(new Font(Font.SERIF, Font.BOLD, 24));
//        jl2.setForeground(Color.BLACK);
        contentPane.add(jl2);

        btn1 = new JButton("Check news");
        btn1.setBounds(90, 300, 150, 100);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn1)
                    new UserNewsPage(conn, username, title, 0);
            }
        });
        contentPane.add(btn1);

        btn2 = new JButton("Follow teams");
        btn2.setBounds(270, 300, 150, 100);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserFollowPage(conn, username, title);
            }
        });
        contentPane.add(btn2);

        btn3 = new JButton("search info");
        btn3.setBounds(450, 300, 150, 100);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        contentPane.add(btn3);

        setVisible(true);
    }

    public static void main(String[] args) {
        new UserMainMenu(DBConn.getConn("root", "12345678"), "123", "NBA info system");
    }
}
