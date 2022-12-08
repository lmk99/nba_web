package swing.journalist_page;

import service.user_modules.NewsModule;
import swing.home_page.LoginPage;
import swing.user_page.TestPage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JournalistMainPage extends JFrame {

    private JPanel teamsPanel;
    private JPanel headPanel;
    private JLabel tableHintLabel;
    private JTable table1;
    private JScrollPane teamsScrollPane;
    private JPanel mainPanel;
    private JPanel userPanel;
    private JTextField userInputText;
    private JPanel BottomPanel;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JLabel userHintLabel;
    private DefaultTableModel model;


    private Connection conn;
    private String username;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    public JournalistMainPage(Connection conn, String username, String title) {
        this.conn = conn;
        this.username = username;

        setTitle(title);
        this.setContentPane(mainPanel);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        table1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == btn1)
                    new WriteNewsPage(conn, username, title);
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn2)
                    JOptionPane.showMessageDialog(null, "Log out successfully!");
                    new LoginPage(conn, title);
            }
        });

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() == -1){
                    JOptionPane.showMessageDialog(null, "Selected the row you want to edit");
                }else{
                    String data = (String) table1.getValueAt(table1.getSelectedRow(), 0);
                    int id = Integer.parseInt(data);
                    new EditNewsPage(conn, username, title, id);
                }
            }
        });

        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table1.getSelectedRow() != -1){
                    String data = (String) table1.getValueAt(table1.getSelectedRow(), 0);
                    int id = Integer.parseInt(data);
                    model.removeRow(table1.getSelectedRow());
                    new NewsModule(conn, username).deleteNews(id);
                    JOptionPane.showMessageDialog(null, "Selected row deleted successfully");

                }else{
                    JOptionPane.showMessageDialog(null, "Selected the row you want to delete");
                }
            }
        });
        setVisible(true);
    }

    private void createUIComponents() {
        String[] header = {"Id", "Topic", "Title", "Summary", "Publish Date"};
        List<List<String>>listData = new NewsModule(conn, username).getMyNews(username);
        int m = listData.size();
        if(m == 0){
            this.model = new DefaultTableModel();
            JOptionPane.showMessageDialog(null, "You haven't write any news!");
        }else {
            int n = listData.get(0).size();
            Object[][] data = new Object[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    data[i][j] = listData.get(i).get(j);
                }
            }
            this.model = new DefaultTableModel(data, header);
        }
            table1 = new JTable(this.model);
    }
}
