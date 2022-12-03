package swing;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IndexPage extends JFrame {

    private JPanel contentPane;
    private JButton btn1, btn2;
    private JLabel jl1, jl2;

    private int INDEX_WIDTH = 700;
    private int INDEX_HEIGHT = 700;

    public IndexPage() {
        setTitle("NBA info system");
        setBounds(100, 50, INDEX_WIDTH, INDEX_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        jl1 = new JLabel("NBA info system", JLabel.CENTER);
        jl1.setBounds(0, 20, 690, 30);
        jl1.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        jl1.setForeground(Color.decode("#375a7f"));
        contentPane.add(jl1);

        jl2 = new JLabel("User or Journalist?", JLabel.CENTER);
        jl2.setBounds(0, 150, 690, 30);
        jl2.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        jl2.setForeground(Color.BLACK);
        contentPane.add(jl2);

        btn1 = new JButton("USER ENTRANCE");
        btn1.setBounds(120, 300, 200, 100);
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn1) {
                    dispose();
                    new IndexPage();
                }
            }
        });
        contentPane.add(btn1);

        btn2 = new JButton("JOURNALIST ENTRANCE");
        btn2.setBounds(420, 300, 200, 100);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btn2) {
                    dispose();
                    new IndexPage();
                }
            }
        });
        contentPane.add(btn2);

        setVisible(true);
    }

    public static void main(String[] args) {
        new IndexPage();
    }
}
