package swing;

import dao.DBConn;
import service.user_modules.NewsModule;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.util.List;

public class NewsTest extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton btn1, btn2, btn3;
    private JLabel jl1, jl2;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    private String username;
    private Connection conn;
    private JTable table1;

    public NewsTest(Connection conn, String username, String title) {
        this.conn = conn;
        this.username = username;

        setTitle(title);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//        contentPane.setLayout(null);
        setContentPane(contentPane);
    }

    public void run() {
        jl1 = new JLabel("All news are here ...");
        jl1.setBounds(20, 20, 690, 30);
        jl1.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        jl1.setForeground(Color.BLACK);
        contentPane.add(jl1);

        String[] header = {"topic", "title", "summary", "author", "publish date", "link"};
        List<List<String>>listData = new NewsModule(conn, username).getNews(0);
        int m = listData.size(), n = listData.get(0).size();
        Object[][] data = new Object[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = listData.get(i).get(j);
            }
        }

        DefaultTableModel defaultModel = new DefaultTableModel(data, header);
        table = new JTable(defaultModel);
//        table.setBackground(Color.cyan);
        table.setPreferredScrollableViewportSize(new Dimension(100, 80));
        table.setFillsViewportHeight(true);

        JScrollPane jScrollPane = new JScrollPane(table);
        contentPane.add(jScrollPane);

        setVisible(true);
    }

    public static void main(String[] args) {
        new NewsTest(DBConn.getConn("root", "12345678"), "123", "NBA info system").run();
    }
}
