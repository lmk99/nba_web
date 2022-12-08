package swing.home_page;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SignupPage extends JFrame{
    private Connection conn;
    private JTextField userInput;
    private JPasswordField pwInput;
    private JComboBox comboBox1;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JPanel mainPanel;
    private JLabel hintLabel;
    private JButton btn1;
    private JButton btn2;
    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    public SignupPage(Connection conn, String title){
        this.conn = conn;

        setTitle(title);
        this.setContentPane(mainPanel);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        hintLabel.setFont (hintLabel.getFont ().deriveFont (20.0f));
        setVisible(true);

        comboBox1.addItem("Fan");
        comboBox1.addItem("Journalist");

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== btn2){
                    new LoginPage(conn, title);
                }
            }
        });

        btn1.addActionListener(new ActionListener() {
            String sql = "";
            ResultSet rs = null;
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = userInput.getText();
                String password = pwInput.getText();
                String acc = comboBox1.getSelectedItem().toString();

                if(userID.equals("") || password.equals("")){
                    JOptionPane.showMessageDialog(null, "Can't be null!");
                }else{
                    // check if this username is already registered
                    sql = "SELECT * FROM " + acc +"s WHERE email= ?";
                    try {
                        PreparedStatement pstmt = conn.prepareStatement(sql);
                        pstmt.setString(1, userID);
                        rs = pstmt.executeQuery();
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null, "This username is already registered!");
                        }
                        // not registered -> sign up
                        else{
                            sql = "insert into " + acc +"s (email, password) values (?, ?)";
                            PreparedStatement preparedStmt = conn.prepareStatement(sql);
                            preparedStmt.setString (1, userID);
                            preparedStmt.setString (2, password);
                            preparedStmt.execute();
                            JOptionPane.showMessageDialog(null, "Sign up successfully!");
                            new LoginPage(conn, title);
                        }
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void createUIComponents() {



    }
}
