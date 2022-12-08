package swing.user_page;

import dao.DBConn;
import service.user_modules.SearchPlayerModule;
import service.user_modules.SearchTeamModule;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

public class TeamInfoPage extends AbstractPage {
    private JPanel mainPanel;
    private JPanel searchBarPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JPanel tablePanel;
    private JPanel sidebarPanel;
    private JButton playersBtn;
    private JButton gamesBtn;
    private JPanel photoPanel;
    private JLabel photoLabel;
    private JTable table;
    private JPanel bottomPanel;
    private JButton backBtn;
    private JPanel menuPanel;
    private JLabel globalPositionLabel;
    private int teamId;

    public TeamInfoPage(Connection conn, String username, int teamId) {
        super(conn, username);
        this.teamId = teamId;

        this.setContentPane(mainPanel);
        setVisible(true);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SearchTeamsPage(conn, username);
            }
        });
    }

    private void createUIComponents() {
        menuPanel = super.menuPanel;

        try {
            BufferedImage pic = ImageIO.read(new File("photos/teams/" + teamId + ".png"));
            photoLabel = new JLabel(new ImageIcon(pic.getScaledInstance(500, 350, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] header = {"team name", "abbreviation", "arena", "city", "state", "year founded", "owner"};
        List<List<String>> listData = new SearchTeamModule(conn).getTeamDetail(teamId);
        int m = listData.size(), n = listData.get(0).size();
        Object[][] data = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = listData.get(i).get(j);
            }
        }

        DefaultTableModel defaultModel = new DefaultTableModel(data, header);
        table = new JTable(defaultModel);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
    }

    public static void main(String[] args) {
        new TeamInfoPage(DBConn.getConn("root", "12345678"), "123", 1610612737);
    }
}
