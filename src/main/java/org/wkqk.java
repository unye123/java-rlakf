import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.*;
import java.awt.Font;

public class wkqk {
    public static void main(String[] args) {

        JFrame frame = new JFrame("자격증 홈페이지");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel logoLabel = new JLabel("lee Certificate", SwingConstants.CENTER);
        logoLabel.setFont(new Font("Serif", Font.BOLD, 24));

        frame.add(logoLabel, BorderLayout.NORTH);

        frame.add(logoLabel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}