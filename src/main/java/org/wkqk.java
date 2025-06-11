import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class wkqk {

    private static final String ADMIN_CODE = "4724";
    private  static boolean isAdminVerified = false;
    private static JTextArea noticeArea;

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

        noticeArea = new JTextArea(10, 50);
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

                if (isAdminVerified) {
                    JFrame addNoticePopup = new JFrame("공지사항 추가");
                    addNoticePopup.setSize(400, 300);
                    addNoticePopup.setLayout(new BorderLayout());

                    JTextArea newNoticeInputArea = new JTextArea();
                    newNoticeInputArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
                    JScrollPane inputScrollPane = new JScrollPane(newNoticeInputArea);

                    JButton addButton = new JButton("추가");
                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent addE) {
                            String newNotice = newNoticeInputArea.getText();
                            if (!newNotice.trim().isEmpty()) {
                                noticeArea.append("\n" + newNotice);
                                addNoticePopup.dispose();
                            } else {
                                JOptionPane.showMessageDialog(addNoticePopup, "추가할 내용을 입력하세요.",
                                        "경고", JOptionPane.WARNING_MESSAGE);
                }
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

                JScrollPane scrollPane = new JScrollPane(fullNoticeArea);
                popup.add(scrollPane);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        }));

        JPanel noticePanel = new JPanel();
        noticePanel.setLayout(new BorderLayout());
        noticePanel.add(noticeLabel, BorderLayout.NORTH);
        noticePanel.add(noticeArea, BorderLayout.CENTER);
        noticePanel.add(moreButton, BorderLayout.SOUTH);

        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        mainContentPanel.add(noticePanel);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel topPanel = new JPanel(new BorderLayout());

        JPasswordField adminCodeField = new JPasswordField(4);
        JButton adminCheckButton = new JButton("관리자 확인");

        JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        eastPanel.add(loginLabel);
        eastPanel.add(new JLabel("관리자 코드:"));
        eastPanel.add(adminCodeField);
        eastPanel.add(adminCheckButton);

        topPanel.add(eastPanel, BorderLayout.EAST);

        frame.add(mainContentPanel, BorderLayout.CENTER);

        adminCheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                char[] inputCode = adminCodeField.getPassword();
                String enteredCode = new String(inputCode);

                if (enteredCode.equals(ADMIN_CODE)) {
                    JOptionPane.showMessageDialog(frame, "관리자 인증 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "관리자 인증 실패!", "오류", JOptionPane.ERROR_MESSAGE);
                    adminCodeField.setText("");
                }
            }
        });

        topPanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(logoLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
