package swing.user_page;

import service.user_modules.SearchGameModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class SearchGamesPage extends AbstractSearchPage {
    private JPanel searchBarPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JPanel menuPanel;
    private JLabel globalPositionLabel;
    private JPanel tablePanel;
    private JScrollPane scrollPanel;
    private JTable table;
    private JPanel sidebarPanel;
    private JButton searchGameBtn;
    private JButton searchHomeBtn;
    private JButton searchAwayBtn;
    private JPanel bottomPanel;
    private JButton backBtn;
    private JPanel mainPanel;
    private List<List<Integer>> index;

    public SearchGamesPage(Connection conn, String username) {
        super(conn, username);

        this.setContentPane(mainPanel);
        setVisible(true);

        // add actionListener to buttons
        searchHomeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int homeId = index.get(row).get(1);
                dispose();
                new TeamInfoPage(conn,  username, homeId);
            }
        });
        searchAwayBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int awayId = index.get(row).get(2);
                dispose();
                new TeamInfoPage(conn, username, awayId);
            }
        });
        searchGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int gameId = index.get(row).get(0);
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

        // set table
        String[] header = {"season", "date", "home", "away", "winner", "score", "assist", "rebound"};
        index = new ArrayList<>();
        List<List<String>> listData = new SearchGameModule(conn).getAllGames(index);

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
}
