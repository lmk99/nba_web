package swing.user_page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public abstract class AbstractPage extends JFrame {
    public Connection conn;
    public String username;
    public JPanel menuPanel;

    public AbstractPage(Connection conn, String username) {
        this.conn = conn;
        this.username = username;
    }

    {
        // default setting
        int INDEX_WIDTH = 700;
        int INDEX_HEIGHT = 700;
        setTitle("NBA data system");
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set menu
        menuPanel = new JPanel();
        JMenuBar bar = new JMenuBar();
        bar.setBounds(120, 15, 330, 30);
        menuPanel.add(bar);

        JMenu teamsMenu = new JMenu("Search Teams");
        teamsMenu.setBounds(120, 20, 30, 30);
        teamsMenu.setForeground(Color.WHITE);
        bar.add(teamsMenu);
        teamsMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new SearchTeamsPage(conn, username);
            }
        });

        JMenu playersMenu = new JMenu("Search Players");
        playersMenu.setBounds(160, 20, 30, 30);
        playersMenu.setForeground(Color.WHITE);
        bar.add(playersMenu);
        playersMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new SearchPlayersPage(conn, username);
            }
        });

        JMenu gamesMenu = new JMenu("Search Games");
        gamesMenu.setBounds(200, 20, 30, 30);
        gamesMenu.setForeground(Color.WHITE);
        bar.add(gamesMenu);
        gamesMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new SearchGamesPage(conn, username);
            }
        });
    }
}
