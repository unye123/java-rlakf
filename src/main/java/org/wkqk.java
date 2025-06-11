import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;

public class wkqk {

    private static final String ADMIN_CODE = "4724";
    private static boolean isAdminVerified = false;
    private static JTextArea noticeArea;
    private static JButton deleteButton;

    private static List<String> registeredIds = new ArrayList<>();

    public static void main(String[] args) {

        registeredIds.add("admin");
        registeredIds.add("user1");
        registeredIds.add("test");


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

                JLabel signupLabel = new JLabel("회원가입");
                signupLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
                signupLabel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        JFrame signupPopup = new JFrame("회원가입");
                        signupPopup.setSize(400, 300);
                        signupPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        JPanel signupMainPanel = new JPanel(new BorderLayout());
                        signupMainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                        JPanel signupInputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

                        JPanel idInputAndButtonPanel = new JPanel();
                        idInputAndButtonPanel.setLayout(new BoxLayout(idInputAndButtonPanel, BoxLayout.X_AXIS));

                        JTextField signupIdField = new JTextField(10);
                        // 💡💡💡 아이디 입력 필드의 선호 크기 설정! 💡💡💡
                        signupIdField.setPreferredSize(new Dimension(150, 25)); // 예시 크기 (가로 150, 세로 25)
                        signupIdField.setMaximumSize(new Dimension(150, 25)); // 최대 크기도 설정 (BoxLayout에 힌트 제공)


                        JButton checkIdButton = new JButton("중복 확인");

                        idInputAndButtonPanel.add(signupIdField);
                        idInputAndButtonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
                        idInputAndButtonPanel.add(checkIdButton);

                        signupInputPanel.add(new JLabel("아이디:"));
                        signupInputPanel.add(idInputAndButtonPanel);


                        signupInputPanel.add(new JLabel("비밀번호:"));
                        signupInputPanel.add(new JPasswordField());

                        signupInputPanel.add(new JLabel("이름:"));
                        signupInputPanel.add(new JTextField());

                        signupInputPanel.add(new JLabel("학번:"));
                        signupInputPanel.add(new JTextField());

                        signupInputPanel.add(new JLabel("학과:"));
                        signupInputPanel.add(new JTextField());

                        JPanel signupButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                        JButton registerButton = new JButton("가입하기");
                        registerButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent registerE) {
                                JOptionPane.showMessageDialog(signupPopup, "회원가입 시도 (기능 미구현)", "안내", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });
                        signupButtonPanel.add(registerButton);


                        signupMainPanel.add(signupInputPanel, BorderLayout.CENTER);
                        signupMainPanel.add(signupButtonPanel, BorderLayout.SOUTH);

                        signupPopup.add(signupMainPanel);

                        signupPopup.setLocationRelativeTo(loginPopup);
                        signupPopup.setVisible(true);

                        checkIdButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent checkE) {
                                String enteredId = signupIdField.getText();

                                if (enteredId.isEmpty()) {
                                    JOptionPane.showMessageDialog(signupPopup, "아이디를 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                                } else {
                                    if (registeredIds.contains(enteredId)) {
                                        JOptionPane.showMessageDialog(signupPopup, "이미 사용 중인 아이디입니다.", "중복 확인", JOptionPane.WARNING_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(signupPopup, "사용 가능한 아이디입니다.", "중복 확인", JOptionPane.INFORMATION_MESSAGE);
                                    }
                                }
                            }
                        });
                    }
                });


                loginMainPanel.add(inputPanel, BorderLayout.CENTER);
                loginMainPanel.add(signupLabel, BorderLayout.SOUTH);

                loginPopup.add(loginMainPanel);

                loginPopup.setLocationRelativeTo(frame);
                loginPopup.setVisible(true);

                loginButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent loginE) {
                        String enteredId = idField.getText();
                        char[] enteredPw = pwField.getPassword();
                        String password = new String(enteredPw);

                        JOptionPane.showMessageDialog(loginPopup, "입력된 아이디: " + enteredId + "\n입력된 비밀번호: " + password, "로그인 시도", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        });


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

        deleteButton = new JButton("-");
        deleteButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        deleteButton.setVisible(false);

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedText = noticeArea.getSelectedText();

                if (selectedText != null && !selectedText.isEmpty()) {
                    String fullText = noticeArea.getText();
                    String newText = fullText.replace(selectedText, "");
                    noticeArea.setText(newText);

                    JOptionPane.showMessageDialog(frame, "선택된 공지사항 내용이 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "삭제할 내용을 선택하세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


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
                                noticeArea.append("\n" + (noticeArea.getText().trim().isEmpty() ? "" : "\n") + newNotice);
                                addNoticePopup.dispose();
                            } else {
                                JOptionPane.showMessageDialog(addNoticePopup, "추가할 내용을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });

                    addNoticePopup.add(new JLabel("새 공지사항 내용을 입력하세요:"), BorderLayout.NORTH);
                    addNoticePopup.add(inputScrollPane, BorderLayout.CENTER);
                    addNoticePopup.add(addButton, BorderLayout.SOUTH);

                    addNoticePopup.setLocationRelativeTo(frame);
                    addNoticePopup.setVisible(true);

                } else {
                    JFrame popup = new JFrame("전체 공지사항");
                    popup.setSize(500, 400);

                    JTextArea fullNoticeArea = new JTextArea();
                    fullNoticeArea.setEditable(false);
                    fullNoticeArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
                    fullNoticeArea.setText( noticeArea.getText() +
                            "\n11. 재시험 일정은 별도 공지됩니다.\n" +
                            "12. 모의고사 일정은 추후 안내됩니다.\n");

                    JScrollPane scrollPane = new JScrollPane(fullNoticeArea);
                    popup.add(scrollPane);
                    popup.setLocationRelativeTo(null);
                    popup.setVisible(true);
                }
            }
        }));

        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.add(deleteButton);
        southButtonPanel.add(moreButton);

        JPanel noticePanel = new JPanel();
        noticePanel.setLayout(new BorderLayout());
        noticePanel.add(noticeLabel, BorderLayout.NORTH);
        noticePanel.add(noticeArea, BorderLayout.CENTER);
        noticePanel.add(southButtonPanel, BorderLayout.SOUTH);


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
                    isAdminVerified = true;
                    adminCodeField.setEnabled(false);
                    adminCheckButton.setEnabled(false);
                    deleteButton.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "관리자 인증 실패!", "오류", JOptionPane.ERROR_MESSAGE);
                    adminCodeField.setText("");
                    isAdminVerified = false;
                    deleteButton.setVisible(false);
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
