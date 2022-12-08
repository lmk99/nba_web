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

public class PlayerInfoPage extends AbstractSearchPage {
    private JPanel mainPanel;
    private JTextField searchField;
    private JButton searchBtn;
    private JList list;
    private JButton teamBtn;
    private JButton gamesBtn;
    private JButton backBtn;
    private JPanel searchBarPanel;
    private JPanel menuPanel;
    private JLabel globalPositionLabel;
    private JPanel bottomPanel;
    private JPanel tablePanel;
    private JPanel sidebarPanel;
    private JPanel photoPanel;
    private JLabel photoLabel;
    private JTable table;
    private int playerId;

    public PlayerInfoPage(Connection conn, String username, int playerId) {
        super(conn, username);
        this.playerId = playerId;

        this.setContentPane(mainPanel);
        setVisible(true);

        teamBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int teamId = new SearchTeamModule(conn).getTeamIdByPlayer(playerId);
                System.out.println(teamId);
                if (teamId == 0)
                    JOptionPane.showMessageDialog(null, "Player has retired");
                else {
                    dispose();
                    new TeamInfoPage(conn, username, teamId);
                }
            }
        });
        gamesBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void createUIComponents() {
        menuPanel = super.menuPanel;

        try {
            BufferedImage pic = ImageIO.read(new File("photos/thumbnails/" + playerId + ".png"));
            photoLabel = new JLabel(new ImageIcon(pic.getScaledInstance(500, 350, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] header = {"team", "name", "matches count", "scores total", "assists total", "rebound_total",
                "three pointers scores", "free_throw_scores", "steals total", "blocks total", "fouls total"};
        List<List<String>> listData = new SearchPlayerModule(conn).getPlayerDetails(playerId);
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
        new PlayerInfoPage(DBConn.getConn("root", "12345678"), "123", 15);
    }
}
