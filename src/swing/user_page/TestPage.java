package swing.user_page;

import dao.DBConn;
import swing.journalist_page.JournalistMainPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

// command interface for user main menu
public class TestPage extends JFrame {

    private JPanel contentPane;
    private JButton btn1, btn2, btn3;
    private JLabel jl1, jl2;

    private JTextField searchInput;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    private String username;
    private Connection conn;

    public TestPage(Connection conn, String username, String title) {
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

        jl2 = new JLabel("What do you want to search?", JLabel.LEFT);
        jl2.setBounds(20, 70, 690, 30);
        jl2.setFont(new Font(Font.SERIF, Font.BOLD, 24));
//        jl2.setForeground(Color.BLACK);
        contentPane.add(jl2);



        searchInput = new JTextField();
        searchInput.setBounds(90, 300, 500, 50);
        contentPane.add(searchInput);
        String keyword = searchInput.getText();




        btn3 = new JButton("Search");
        btn3.setBounds(600, 300, 90, 50);
//        btn3.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                new UserSearchPage(conn, username, title);
//            }
//        });
        contentPane.add(btn3);

        setVisible(true);
    }


}
