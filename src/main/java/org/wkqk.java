import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class wkqk {
    public static void main(String[] args) {
        JFrame frame = new JFrame("자격증 홈페이지");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel logoLabel = new JLabel("lee Certificate");
        logoLabel.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel loginLabel = new JLabel("로그인");
        loginLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        JTextArea noticeArea = new JTextArea(10, 30);
        noticeArea.setEditable(false);
        noticeArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        noticeArea.setText(
                "1. 자격증 접수는 6월 20일까지입니다.\n" +
                        "2. 시험 일정은 7월 1일입니다.\n" +
                        "3. 응시자 유의사항을 꼭 확인하세요.\n" +
                        "4. 마감일 전까지 사진 등록 필수입니다.\n" +
                        "5. 신분증 지참 필수.\n" +
                        "6. 자리 배정표는 시험 하루 전 제공.\n" +
                        "7. 시험 장소는 추후 공지 예정.\n" +
                        "8. 준비물은 개별 확인 요망.\n" +
                        "9. 합격 발표는 8월 초 예정.\n" +
                        "10. 문의는 홈페이지 Q&A를 이용하세요."
        );

        JButton moreButton = new JButton("+");
        moreButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        moreButton.addActionListener(e -> {
            JFrame popup = new JFrame("전체 공지사항");
            popup.setSize(500, 400);

            JTextArea fullNoticeArea = new JTextArea();
            fullNoticeArea.setEditable(false);
            fullNoticeArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
            fullNoticeArea.setText(
                    noticeArea.getText() +
                            "\n11. 재시험 일정은 별도 공지됩니다.\n" +
                            "12. 모의고사 일정은 추후 안내됩니다.\n"
            );

            JScrollPane scrollPane = new JScrollPane(fullNoticeArea);
            popup.add(scrollPane);
            popup.setLocationRelativeTo(null);
            popup.setVisible(true);
        });

        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.add(moreButton);

        JPanel noticePanel = new JPanel(new BorderLayout());
        noticePanel.add(new JLabel("공지사항"), BorderLayout.NORTH);
        noticePanel.add(noticeArea, BorderLayout.CENTER);
        noticePanel.add(southButtonPanel, BorderLayout.SOUTH);
        noticePanel.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0)); // 왼쪽 2cm 여백

        JPanel examSchedulePanel = new JPanel(new BorderLayout());
        JLabel examScheduleLabel = new JLabel("시험 일정");
        examScheduleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));
        JTextArea examScheduleArea = new JTextArea(10, 30);
        examScheduleArea.setEditable(false);
        examScheduleArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        examScheduleArea.setText(
                "2024년 제1회 자격검정 시험:\n" +
                        "- 원서 접수 기간: 6월 15일 ~ 6월 20일\n" +
                        "- 시험일: 7월 1일\n" +
                        "- 합격자 발표: 7월 30일"
        );
        JScrollPane examScheduleScrollPane = new JScrollPane(examScheduleArea);
        examScheduleScrollPane.setPreferredSize(examScheduleArea.getPreferredSize());
        examSchedulePanel.add(examScheduleLabel, BorderLayout.NORTH);
        examSchedulePanel.add(examScheduleScrollPane, BorderLayout.CENTER);
        examSchedulePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 180, 70)); // 아래 5cm, 오른쪽 2cm 여백

        JPanel mainContentPanel = new JPanel(new BorderLayout());
        mainContentPanel.add(noticePanel, BorderLayout.WEST);
        mainContentPanel.add(examSchedulePanel, BorderLayout.EAST);
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 180, 0)); // 아래 5cm 여백

        JPanel topPanel = new JPanel(new BorderLayout());
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        centerPanel.add(logoLabel);
        topPanel.add(centerPanel, BorderLayout.CENTER);
        topPanel.add(loginLabel, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(mainContentPanel, BorderLayout.CENTER);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
