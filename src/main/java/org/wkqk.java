import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class wkqk {
    public static void main(String[] args) {

        JFrame frame = new JFrame("자격증 홈페이지");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel logoLabel = new JLabel("lee Certificate");
        logoLabel.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel loginLabel = new JLabel("로그인");
        loginLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        JLabel noticeLabel = new JLabel("공지사항");
        noticeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        JTextArea noticeArea = new jtextArea(10, 50);
        

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel centerNoticePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centerNoticePanel.add(noticeLabel);

        topPanel.add(centerPanel, BorderLayout.CENTER);

        centerPanel.add(logoLabel);

        topPanel.add(loginLabel, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerNoticePanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}