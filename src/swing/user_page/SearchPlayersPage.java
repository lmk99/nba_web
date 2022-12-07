package swing.user_page;

import dao.DBConn;
import service.user_modules.SearchPlayerModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SearchPlayersPage extends AbstractSearchPage {
    private JPanel mainPanel;
    private JPanel searchBarPanel;
    private JButton searchBtn;
    private JTextField searchField;
    private JPanel menuPanel;
    private JLabel globalPositionLabel;
    private JPanel tablePanel;
    private JScrollPane scrollPanel;
    private JTable table;
    private JPanel sidebarPanel;
    private JButton searchGameBtn;
    private JButton searchTeamBtn;
    private JPanel bottomPanel;
    private JButton backBtn;
    private JButton searchPlayerBtn;
    private List<List<Integer>> index;

    public SearchPlayersPage(Connection conn, String username) {
        super(conn, username);

        this.setContentPane(mainPanel);
        setVisible(true);

        // add actionListener to buttons
        searchTeamBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int teamId = index.get(row).get(1);
                dispose();
                new TeamInfoPage(conn, username, teamId);
            }
        });
        searchPlayerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int playerId = index.get(row).get(0);
                dispose();
                new PlayerInfoPage(conn, username, playerId);
            }
        });
        searchGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new UserMainMenu(conn, username);
            }
        });
    }

    private void createUIComponents() {
        menuPanel = super.menuPanel;
        searchField = super.searchField;
        searchBtn = super.searchBtn;

        // set table
//        String[] header = {"team", "name", "match count", "scores", "assists", "rebounds", "three-point scores",
//                "free throw scores", "steals", "blocks", "fouls"};
        String[] header = {"team", "full name", "first name", "last name"};
        index = new ArrayList<>();
        List<List<String>> listData = new SearchPlayerModule(conn).getAllPlayers(index);

        int m = listData.size(), n = listData.get(0).size();
        Object[][] data = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = listData.get(i).get(j);
            }
        }

        DefaultTableModel defaultModel = new DefaultTableModel(data, header);
        table = new JTable(defaultModel);
    }

    public static void main(String[] args) {
        new SearchPlayersPage(DBConn.getConn("root", "12345678"), "123");
    }
}
