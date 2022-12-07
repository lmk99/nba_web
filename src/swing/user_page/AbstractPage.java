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
    }
}
