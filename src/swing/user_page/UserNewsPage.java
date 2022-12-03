package swing.user_page;

import dao.DBConn;
import service.user_modules.NewsModule;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

public class UserNewsPage extends JFrame {

    private String username;
    private Connection conn;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JLabel headLabel;
    private JPanel headPanel;
    private JTable table1;
    private JScrollPane tablePanel;
    private JPanel mainPanel;
    private JPanel turnPagePanel;
    private JPanel BottomPanel;
    private JLabel pageLabel;

    int page;

    public UserNewsPage(Connection conn, String username, String title, int page) {
        this.conn = conn;
        this.username = username;
        this.page = page;

        setTitle(title);
        this.setContentPane(mainPanel);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (page == 0)
                    JOptionPane.showMessageDialog(null, "No previous page!");
                else
                    new UserNewsPage(conn, username, title, page - 1);
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserNewsPage(conn, username, title, page + 1);
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn3)
                     new UserMainMenu(conn, username, title);
            }
        });

        pageLabel.setText("Page " + (this.page + 1));

        setVisible(true);
    }

    private void createUIComponents() {
        String[] header = {"topic", "title", "summary", "author", "publish date", "link"};
        List<List<String>>listData = new NewsModule(conn, username).getNews(this.page);
        int m = listData.size(), n = listData.get(0).size();
        Object[][] data = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = listData.get(i).get(j);
            }
        }

        DefaultTableModel defaultModel = new DefaultTableModel(data, header);
        table1 = new JTable(defaultModel);
    }

    public static void main(String[] args) {
        new UserNewsPage(DBConn.getConn("root", "12345678"), "123", "NBA info system", 0);
    }
}
