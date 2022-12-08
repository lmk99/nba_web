package swing.user_page;

import dao.DBConn;
import service.user_modules.SearchTeamModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class GamesInfoPage extends AbstractPage {
    private JPanel menuPanel;
    private JPanel mainPanel;
    private JPanel searchBarPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JLabel globalPositionLabel;
    private JPanel tablePanel;
    private JScrollPane scrollPanel;
    private JTable table;
    private JPanel sidebarPanel;
    private JButton playerBtn;
    private JButton homeTeamBtn;
    private JButton awayTeamBtn;
    private JPanel bottomPanel;
    private JButton backBtn;
    private List<List<Integer>> index;
    private int gameId;

    public GamesInfoPage(Connection conn, String username, int gameId) {
        super(conn, username);

        this.setContentPane(mainPanel);
        setVisible(true);

        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        homeTeamBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        awayTeamBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        playerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        menuPanel = super.menuPanel;

        // set table

    }

    public static void main(String[] args) {
        new GamesInfoPage(DBConn.getConn("root", "12345678"), "123", 1610612742);
    }
}
