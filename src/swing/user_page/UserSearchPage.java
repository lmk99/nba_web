package swing.user_page;

import dao.DBConn;
import service.user_modules.FollowModule;
import service.user_modules.SearchTeamModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class UserSearchPage extends JFrame {
    private Connection conn;
    private String username;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;
    private JPanel mainPanel;
    private JTable table1;
    private JTextField textField;
    private JButton btn1;
    private JButton btn2;
    private JScrollPane scrollPane;

    public UserSearchPage(Connection conn, String username, String title) {
        this.conn = conn;
        this.username = username;

        setTitle(title);
        this.setContentPane(mainPanel);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn1) {
                    int teamId = new SearchTeamModule(conn, username).getTeamId(textField.getText());
                    if (teamId == -1)
                        JOptionPane.showMessageDialog(null, "Please input a valid team abbreviation");
                    else {
                        new TeamBasicInfoPage(conn, username, title, teamId);
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

        setVisible(true);
    }

    private void createUIComponents() {
        String[] header = {"full name", "abbreviation"};
        List<List<String>>listData = new SearchTeamModule(conn, username).getAllTeams();
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

    public static void main(String[] args) {
        new UserSearchPage(DBConn.getConn("root", "12345678"), "123", "NBA info system");
    }
}
