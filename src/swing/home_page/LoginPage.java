package swing.home_page;

import swing.journalist_page.JournalistMainPage;
import swing.user_page.UserMainMenu;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends JFrame {
    private Connection conn;
    private String username;
    private JPanel mainPanel;
    private JButton btn1;
    private JButton btn2;
    private JTextField userIdText;
    private JPasswordField pwText;
    private JLabel pw;
    private JLabel userId;
    private JPanel UserPanel;
    private JLabel welcome;
    private JComboBox comboBox1;
    private JLabel accType;
    private JPanel header;
    private DefaultTableModel model;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    public LoginPage(Connection conn, String title){
        this.conn = conn;

        setTitle(title);
        this.setContentPane(UserPanel);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        welcome.setFont (welcome.getFont ().deriveFont (20.0f));
        setVisible(true);

        comboBox1.addItem("Fan");
        comboBox1.addItem("Journalist");
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sql = "";
                ResultSet rs = null;
                if (e.getSource() == btn1) {
                    String userID = userIdText.getText();
                    String password = pwText.getText();
                    String acc = comboBox1.getSelectedItem().toString();


                    if(userID.equals("") || password.equals("")) {
                        JOptionPane.showMessageDialog(null, "Invalid Log in!");
                    }
                    else{
                        sql = "SELECT * FROM " + acc +"s WHERE email= ?";
                        try {
                            PreparedStatement pstmt = conn.prepareStatement(sql);
                            pstmt.setString(1, userID);
                            rs = pstmt.executeQuery();
                            if(rs.next()){
                                if(password.equals(rs.getString(2))){
                                    JOptionPane.showMessageDialog(null, "Login successfully!");

                                    if(acc.equals("Journalist")){
                                        new JournalistMainPage(conn, userID, title);
                                    }else{
                                        new UserMainMenu(conn, userID);
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null, "Wrong password. Please try again!");
                                }
                            }else{
                                JOptionPane.showMessageDialog(null, "Please register first!");
                            }
                        }catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==btn2){
                    new SignupPage(conn, title);
                }
            }
        });
    }

    private void createUIComponents() {
    }
}
