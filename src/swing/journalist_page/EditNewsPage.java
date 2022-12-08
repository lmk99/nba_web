package swing.journalist_page;

import service.user_modules.NewsModule;
import swing.user_page.TestPage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EditNewsPage extends JFrame{
    private JButton btn1;
    private JTextField titleArea;
    private JTextArea summaryArea;
    private JPanel mainPanel;
    private JButton btn2;


    private Connection conn;
    private String username;
    private int id;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    public EditNewsPage(Connection conn, String username, String title, int id) {
        this.conn = conn;
        this.username = username;
        this.id = id;

        setTitle(title);
        this.setContentPane(mainPanel);
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn1) {
                    String textSummary = summaryArea.getText();
                    String textTitle = titleArea.getText();
                    String sql = "";
                    if (textSummary.equals("")){
                        // Edit title only
                        if (!textTitle.equals("")){
                            new NewsModule(conn, username).editTitle(textTitle, id);
                            JOptionPane.showMessageDialog(null, "Edit title successfully!");
                        }
                        else{
                            JOptionPane.showMessageDialog(null, "Nothing changed. Back to menu in a second");
                        }
                    }

                    else{
                        // Edit summary only
                        if (textTitle.equals("")){
                            new NewsModule(conn, username).editSummary(textSummary, id);
                            JOptionPane.showMessageDialog(null, "Edit Summary successfully!");
                        }

                        // both summary and title changed
                        else{
                            new NewsModule(conn, username).editTitle(textTitle, id);
                            new NewsModule(conn, username).editSummary(textSummary, id);
                            JOptionPane.showMessageDialog(null, "Edit both Summary and title successfully!");
                        }
                    }
                    new JournalistMainPage(conn, username, title);
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
