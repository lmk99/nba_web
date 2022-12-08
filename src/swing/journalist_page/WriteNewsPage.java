package swing.journalist_page;

import service.user_modules.NewsModule;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WriteNewsPage extends JFrame{
    private JButton btn1;
    private JTextField titleArea;
    private JTextArea summaryArea;
    private JComboBox comboBox1;
    private JPanel mainPanel;
    private JButton btn2;


    private Connection conn;
    private String username;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    public WriteNewsPage(Connection conn, String username, String title) {
        this.conn = conn;
        this.username = username;

        setTitle(title);
        this.setContentPane(mainPanel);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        comboBox1.addItem("entertainment");
        comboBox1.addItem("business");


        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn1) {
                    String textSummary = summaryArea.getText();
                    String textTitle = titleArea.getText();
                    String topic = comboBox1.getSelectedItem().toString();
                    if (textSummary.equals(""))
                        JOptionPane.showMessageDialog(null, "Summary cannot be empty");
                    if (textTitle.equals(""))
                        JOptionPane.showMessageDialog(null, "Title cannot be empty");
                    else {
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        String publishDate = dtf.format(now);

                        new NewsModule(conn,username).writeNews(topic, textTitle, textSummary, username, publishDate);
                        JOptionPane.showMessageDialog(null, "Upload it successfully!");
                        new JournalistMainPage(conn, username, title);
                    }
                }
            }
        });

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn2) {
                    new JournalistMainPage(conn, username, title);
                }
            }
        });
        setVisible(true);
    }
    private void createUIComponents() {
    }
}
