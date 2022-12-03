package swing.user_page;

import dao.DBConn;
import service.user_modules.SearchTeamModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class TeamBasicInfoPage extends JFrame {
    private Connection conn;
    private String username;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    private JPanel mainPanel;
    private JPanel header;
    private JTable table1;
    private JScrollPane scrollPane;
    private JButton btn1;
    private int teamId;
    private String title;

    public TeamBasicInfoPage(Connection conn, String username, String title, int teamId) {
        this.conn = conn;
        this.username = username;
        this.title = title;
        this.teamId = teamId;

        setTitle(title);
        this.setContentPane(mainPanel);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserSearchPage(conn, username, title);
            }
        });

        setVisible(true);
    }

    private void createUIComponents() {
        header = new JPanel();
        JMenuBar bar = new JMenuBar();
        bar.setBounds(0, 40, 690, 30);
        bar.setBackground(Color.decode("#65991a"));
        header.add(bar);

        JMenu jm = new JMenu("Basic info");
        jm.setPreferredSize(new Dimension(100, 30));
        jm.setForeground(Color.WHITE);
        bar.add(jm);

        JMenuItem jmi1 = new JMenuItem("Team state");
        JMenuItem jmi2 = new JMenuItem("Staff");
        jm.add(jmi1);
        jm.add(jmi2);

        JMenu jm1 = new JMenu("players");
        jm1.setPreferredSize(new Dimension(100, 30));
        jm1.setForeground(Color.WHITE);
        bar.add(jm1);

        JMenu jm2 = new JMenu("games");
        jm2.setPreferredSize(new Dimension(100, 30));
        jm2.setForeground(Color.WHITE);
        bar.add(jm2);

        String[] header = {"full name", "abbreviation", "arena", "city", "state", "year_founded", "owner"};
        List<List<String>> listData = new SearchTeamModule(conn, username).searchTeamBasic(this.teamId);
        int m = listData.size(), n = listData.get(0).size();
        Object[][] data = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = listData.get(i).get(j);
            }
        }

        DefaultTableModel defaultModel = new DefaultTableModel(data, header);
        table1 = new JTable(defaultModel);

        jmi1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TeamBasicInfoPage(conn, username, title, teamId);
            }
        });
        jmi2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}
