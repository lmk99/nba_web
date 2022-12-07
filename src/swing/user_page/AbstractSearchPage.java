package swing.user_page;

import service.user_modules.SearchModule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class AbstractSearchPage extends AbstractPage {
    public JTextField searchField;
    public JButton searchBtn;

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
