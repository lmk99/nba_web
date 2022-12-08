package swing.user_page;

import service.user_modules.SearchModule;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

public class AbstractSearchPage extends AbstractPage {
    public JTextField searchField;
    public JButton searchBtn;

    {
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

    public AbstractSearchPage(Connection conn, String username) {
        super(conn, username);
        searchField = new JTextField();
        searchBtn = new JButton("Search");
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = searchField.getText();
                int[] res = new SearchModule(conn).search(input);

                if (res[0] == 0) {
                    JOptionPane.showMessageDialog(null, "No matched data");
                } else if (res[0] == 1) {
                    dispose();
                    new TeamInfoPage(conn, username, res[1]);
                } else {
                    dispose();
                    new PlayerInfoPage(conn, username, res[1]);
                }
            }
        });
    }
}
