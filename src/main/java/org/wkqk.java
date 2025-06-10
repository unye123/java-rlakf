import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

public class wkqk {
    public static void main(String[] args) {

        JFrame frame = new JFrame("자격증 홈페이지");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel logoLabel = new JLabel("lee Certificate");
        logoLabel.setFont(new Font("Serif", Font.BOLD, 24));

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel topPanel = new JPanel(new BorderLayout());

        topPanel.add(C=centerPanel, BorderLayout.CENTER);

        centerPanel.add(logoLabel);

        topPanel.add(cnter)
        frame.add(logoLabel, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}