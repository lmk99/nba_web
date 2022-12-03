package swing.user_page;

import dao.DBConn;
import service.user_modules.FollowModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class UserFollowPage extends JFrame {

    private JPanel teamsPanel;
    private JPanel headPanel;
    private JLabel tableHintLabel;
    private JTable table1;
    private JScrollPane teamsScrollPane;
    private JPanel mainPanel;
    private JPanel userPanel;
    private JTextField userInputText;
    private JPanel BottomPanel;
    private JButton btn1;
    private JButton btn2;
    private JLabel userHintLabel;
    private JButton btn3;

    private Connection conn;
    private String username;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    public UserFollowPage(Connection conn, String username, String title) {
        this.conn = conn;
        this.username = username;

        setTitle(title);
        this.setContentPane(mainPanel);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        List<String> followedTeams = new FollowModule(conn, username).getUserCurrentTeams();
        StringBuffer sb = new StringBuffer("You have followed: ");
        for (String team : followedTeams) {
            sb.append(team + "   ");
        }
        userHintLabel.setText(sb.toString());

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn1) {
                    String textValue = userInputText.getText();
                    if (textValue.equals(""))
                        JOptionPane.showMessageDialog(null, "Input cannot be empty");
                    else {
                        int status = new FollowModule(conn, username).updateUserFollow(textValue, 0);
                        if (status == 1)
                            JOptionPane.showMessageDialog(null, "Wrong input, please input the team abbreviation in the table");
                        else {
                            JOptionPane.showMessageDialog(null, "You have add " + textValue + " to you favourite teams");
                            dispose();
                            new UserFollowPage(conn, username, title);
                        }
                    }
                }
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn2)
                    new UserMainMenu(conn, username, title);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn3) {
                    String textValue = userInputText.getText();
                    if (textValue.equals(""))
                        JOptionPane.showMessageDialog(null, "Input cannot be empty");
                    else {
                        int status = new FollowModule(conn, username).updateUserFollow(textValue, 1);
                        if (status == 1)
                            JOptionPane.showMessageDialog(null, "Wrong input, please input the team abbreviation in the table");
                        else {
                            JOptionPane.showMessageDialog(null, "You have unfollowed " + textValue + " to you favourite teams");
                            dispose();  // it may take some time to process update and refresh the window
                            new UserFollowPage(conn, username, title);
                        }
                    }
                }
            }
        });

        setVisible(true);
    }

    private void createUIComponents() {
        String[] header = {"full name", "abbreviation", "nickname"};
        List<List<String>>listData = new FollowModule(conn, username).getAllTeams();
        int m = listData.size(), n = listData.get(0).size();
        Object[][] data = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = listData.get(i).get(j);
            }
        }

        DefaultTableModel defaultModel = new DefaultTableModel(data, header);
        table1 = new JTable(defaultModel);
    }
}