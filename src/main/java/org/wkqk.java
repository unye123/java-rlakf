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

        JTextArea noticeArea = new JTextArea(10, 50);
        noticeArea.setEditable(false);
        noticeArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        noticeArea.setText( "1. 자격증 접수는 6월 20일까지입니다.\n" +
                "2. 시험 일정은 7월 1일입니다.\n" +
                "3. 응시자 유의사항을 꼭 확인하세요.\n" +
                "4. 마감일 전까지 사진 등록 필수입니다.\n" +
                "5. 신분증 지참 필수.\n" +
                "6. 자리 배정표는 시험 하루 전 제공.\n" +
                "7. 시험 장소는 추후 공지 예정.\n" +
                "8. 준비물은 개별 확인 요망.\n" +
                "9. 합격 발표는 8월 초 예정.\n" +
                "10. 문의는 홈페이지 Q&A를 이용하세요.");

        JButton moreButton = new JButton("+");
        moreButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        moreButton.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame popup = new JFrame("전체 공지사항");
                popup.setSize(500, 400);

                JTextArea fullNoticeArea = new JTextArea();
                fullNoticeArea.setEditable(false);
                fullNoticeArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
                fullNoticeArea.setText( "1. 자격증 접수는 6월 20일까지입니다.\n" +
                        "2. 시험 일정은 7월 1일입니다.\n" +
                        "3. 응시자 유의사항을 꼭 확인하세요.\n" +
                        "4. 마감일 전까지 사진 등록 필수입니다.\n" +
                        "5. 신분증 지참 필수.\n" +
                        "6. 자리 배정표는 시험 하루 전 제공.\n" +
                        "7. 시험 장소는 추후 공지 예정.\n" +
                        "8. 준비물은 개별 확인 요망.\n" +
                        "9. 합격 발표는 8월 초 예정.\n" +
                        "10. 문의는 홈페이지 Q&A를 이용하세요.\n" +
                        "11. 재시험 일정은 별도 공지됩니다.\n" +
                        "12. 모의고사 일정은 추후 안내됩니다.\n");
            }
        }));
                JScrollPane scrollPane = new JScrollPane(fullNoticeArea);
                popup.add(scrollPane);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);

        JPanel noticePanel = new JPanel();
        noticePanel.setLayout(new BorderLayout());
        noticePanel.add(noticeLabel, BorderLayout.NORTH);
        noticePanel.add(noticeArea, BorderLayout.CENTER);
        noticePanel.add(moreButton, BorderLayout.SOUTH);

        frame.add(noticePanel, BorderLayout.CENTER);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel topPanel = new JPanel(new BorderLayout());

        centerPanel.add(logoLabel);

        topPanel.add(loginLabel, BorderLayout.EAST);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(centerNoticePanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}