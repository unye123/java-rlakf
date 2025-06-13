import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap; // HashMap 클래스를 사용하기 위해 import 추가
import java.util.Map; // Map 인터페이스를 사용하기 위해 import 추가
import java.awt.Dimension;
import javax.swing.border.EmptyBorder;
import java.awt.Component;

public class wkqk {

    // 미리 정해진 사용자 정보 (아이디: 비밀번호)
    private static final Map<String, String> users = new HashMap<>();

    public static void main(String[] args) {

        // 테스트용 사용자 정보 추가
        users.put("ddd", "1234");

        JFrame frame = new JFrame("자격증 홈페이지");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel logoLabel = new JLabel("lee Certificate");
        logoLabel.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel loginLabel = new JLabel("로그인");
        loginLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (loginLabel.getText().equals("로그인")) {
                    // 로그인 팝업 창 생성
                    JFrame loginPopup = new JFrame("로그인");
                    loginPopup.setSize(300, 200);
                    loginPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    JPanel loginMainPanel = new JPanel(new BorderLayout());
                    loginMainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                    JPanel inputPanel = new JPanel();
                    inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

                    JPanel idPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    idPanel.add(new JLabel("아이디:"));
                    JTextField idField = new JTextField(15);
                    idPanel.add(idField);
                    inputPanel.add(idPanel);

                    JPanel pwPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    pwPanel.add(new JLabel("비밀번호:"));
                    JPasswordField pwField = new JPasswordField(15);
                    pwPanel.add(pwField);
                    inputPanel.add(pwPanel);

                    JPanel loginButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                    JButton loginButton = new JButton("로그인");
                    loginButtonPanel.add(loginButton);
                    inputPanel.add(loginButtonPanel);

                    // 회원가입 문구 (기능 없음)
                    JLabel signupLabel = new JLabel("회원가입");
                    signupLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
                    signupLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseReleased(MouseEvent e) {
                            JOptionPane.showMessageDialog(loginPopup, "회원가입 기능은 현재 준비 중입니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
                        }
                    });


                    loginMainPanel.add(inputPanel, BorderLayout.CENTER);
                    loginMainPanel.add(signupLabel, BorderLayout.SOUTH);

                    loginPopup.add(loginMainPanel);
                    loginPopup.setLocationRelativeTo(frame);
                    loginPopup.setVisible(true);

                    // 로그인 버튼 액션 리스너
                    loginButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent loginE) {
                            String enteredId = idField.getText();
                            char[] enteredPw = pwField.getPassword();
                            String password = new String(enteredPw);

                            // 아이디와 비밀번호 확인
                            if (users.containsKey(enteredId) && users.get(enteredId).equals(password)) {
                                JOptionPane.showMessageDialog(loginPopup, "로그인 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);
                                loginPopup.dispose(); // 로그인 팝업 닫기
                                loginLabel.setText("my page"); // '로그인' 글자를 'my page'로 변경
                            } else {
                                JOptionPane.showMessageDialog(loginPopup, "아이디 또는 비밀번호가 올바르지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                                pwField.setText(""); // 비밀번호 필드 비우기
                            }
                        }
                    });
                } else if (loginLabel.getText().equals("my page")) {
                    // my page 클릭 시 동작 (현재는 아무것도 하지 않음)
                    // 여기에 my page 화면으로 전환하는 코드를 추가할 수 있습니다.
                }
            }
        });


        JLabel noticeLabel = new JLabel("공지사항");
        noticeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));

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
        // 💡💡💡 noticeArea를 JScrollPane 없이 직접 추가합니다! 💡💡💡
        noticePanel.add(noticeArea, BorderLayout.CENTER); // noticeArea 자체를 추가
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
