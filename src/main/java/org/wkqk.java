import javax.swing.*;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import java.io.*;
import javax.swing.border.EmptyBorder;

public class wkqk {

    private static final String ADMIN_CODE = "4724";
    private static boolean isAdminVerified = false;
    private static JTextArea noticeArea;
    private static JButton deleteButton;

    private static List<String> registeredIds = new ArrayList<>();
    private static Map<String, String> users = new HashMap<>();
    private static Map<String, Map<String, String>> userDetails = new HashMap<>();

    private static final String DATA_FILE = "app_data.dat";

    private static JButton logoutButton;

    private static JPanel initialMainContentPanel;

    private static JPasswordField adminCodeField;
    private static JButton adminCheckButton;

    private static JButton addInquiryButton;


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        noticeArea = new JTextArea(10, 30);
        noticeArea.setEditable(false);
        noticeArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        loadData();


        final JFrame frame = new JFrame("자격증 홈페이지");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                saveData();
                System.exit(0);
            }
        });

        adminCodeField = new JPasswordField(4);
        adminCheckButton = new JButton("관리자 확인");


        JLabel logoLabel = new JLabel("lee Certificate");
        logoLabel.setFont(new Font("Serif", Font.BOLD, 24));

        final JLabel loginLabel = new JLabel("로그인");
        loginLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (loginLabel.getText().equals("로그인")) {
                    final JFrame loginPopup = new JFrame("로그인");
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
                            final JFrame signupPopup = new JFrame("회원가입");
                            signupPopup.setSize(400, 300);
                            signupPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                            JPanel signupMainPanel = new JPanel(new BorderLayout());
                            signupMainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                            JPanel signupInputPanel = new JPanel(new GridLayout(5, 2, 5, 5));

                            JPanel idInputAndButtonPanel = new JPanel();
                            idInputAndButtonPanel.setLayout(new BoxLayout(idInputAndButtonPanel, BoxLayout.X_AXIS));

                            final JTextField signupIdField = new JTextField(10);
                            signupIdField.setPreferredSize(new Dimension(150, 25));
                            signupIdField.setMaximumSize(new Dimension(150, 25));


                            final JButton checkIdButton = new JButton("중복 확인");

                            idInputAndButtonPanel.add(signupIdField);
                            idInputAndButtonPanel.add(Box.createRigidArea(new Dimension(5, 0)));
                            idInputAndButtonPanel.add(checkIdButton);

                            signupInputPanel.add(new JLabel("아이디:"));
                            signupInputPanel.add(idInputAndButtonPanel);

                            JPanel pwInputAndLabelPanel = new JPanel();
                            pwInputAndLabelPanel.setLayout(new BoxLayout(pwInputAndLabelPanel, BoxLayout.Y_AXIS));

                            final JPasswordField signupPwField = new JPasswordField();
                            signupPwField.setPreferredSize(new Dimension(150, 25));
                            signupPwField.setMaximumSize(new Dimension(150, 25));

                            JLabel pwHintLabel = new JLabel("5자리 이상");
                            pwHintLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 10));
                            pwHintLabel.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

                            pwInputAndLabelPanel.add(signupPwField);
                            pwInputAndLabelPanel.add(pwHintLabel);

                            signupInputPanel.add(new JLabel("비밀번호:"));
                            signupInputPanel.add(pwInputAndLabelPanel);


                            final JTextField signupNameField = new JTextField();
                            signupInputPanel.add(new JLabel("이름:"));
                            signupInputPanel.add(signupNameField);

                            final JTextField signupStudentIdField = new JTextField();
                            signupInputPanel.add(new JLabel("학번:"));
                            signupInputPanel.add(signupStudentIdField);

                            final JTextField signupMajorField = new JTextField();
                            signupInputPanel.add(new JLabel("학과:"));
                            signupInputPanel.add(signupMajorField);

                            JPanel signupButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
                            final JButton registerButton = new JButton("가입하기");
                            registerButton.setEnabled(false);

                            final boolean[] isIdCheckedAndAvailable = {false};

                            registerButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent registerE) {
                                    if (isIdCheckedAndAvailable[0]) {
                                        String newId = signupIdField.getText();
                                        char[] newPwChars = signupPwField.getPassword();
                                        String newPassword = new String(newPwChars);
                                        String newName = signupNameField.getText();
                                        String newStudentId = signupStudentIdField.getText();
                                        String newMajor = signupMajorField.getText();

                                        if (newId.isEmpty() || newPassword.isEmpty()) {
                                            JOptionPane.showMessageDialog(signupPopup, "아이디와 비밀번호는 필수 입력입니다.", "경고", JOptionPane.WARNING_MESSAGE);
                                            return;
                                        }

                                        if (newPassword.length() < 5) {
                                            JOptionPane.showMessageDialog(signupPopup, "비밀번호는 5자리 이상이어야 합니다.", "경고", JOptionPane.WARNING_MESSAGE);
                                            return;
                                        }


                                        registeredIds.add(newId);
                                        users.put(newId, newPassword);
                                        Map<String, String> details = new HashMap<>();
                                        details.put("name", newName);
                                        details.put("studentId", newStudentId);
                                        details.put("major", newMajor);
                                        userDetails.put(newId, details);


                                        JOptionPane.showMessageDialog(signupPopup, "회원가입 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);

                                        signupPopup.dispose();
                                        loginPopup.setVisible(true);

                                    } else {
                                        JOptionPane.showMessageDialog(signupPopup, "아이디 중복 확인을 먼저 해주세요.", "경고", JOptionPane.WARNING_MESSAGE);
                                    }
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
                                        isIdCheckedAndAvailable[0] = false;
                                        registerButton.setEnabled(false);
                                    } else {
                                        if (registeredIds.contains(enteredId)) {
                                            JOptionPane.showMessageDialog(signupPopup, "이미 사용 중인 아이디입니다.", "중복 확인", JOptionPane.WARNING_MESSAGE);
                                            isIdCheckedAndAvailable[0] = false;
                                            registerButton.setEnabled(false);
                                        } else {
                                            JOptionPane.showMessageDialog(signupPopup, "사용 가능한 아이디입니다.", "중복 확인", JOptionPane.INFORMATION_MESSAGE);
                                            isIdCheckedAndAvailable[0] = true;
                                            registerButton.setEnabled(true);
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

                            if (users.containsKey(enteredId) && users.get(enteredId).equals(password)) {
                                JOptionPane.showMessageDialog(loginPopup, "로그인 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);
                                loginPopup.dispose();

                                loginLabel.setText("my page");
                                if (logoutButton != null) {
                                    logoutButton.setVisible(true);
                                }
                                if (addInquiryButton != null) {
                                    addInquiryButton.setEnabled(true);
                                }
                                // 로그인 성공 시 바로 my page 화면으로 전환하는 코드 삭제!
                                // showMyPageContent(frame);

                            } else {
                                JOptionPane.showMessageDialog(loginPopup, "아이디 또는 비밀번호가 올바르지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
                                pwField.setText("");
                            }
                        }
                    });
                } else if (loginLabel.getText().equals("my page")) {
                    showMyPageContent(frame);
                }
            }
        });


        JLabel noticeLabel = new JLabel("공지사항");
        noticeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        // noticeArea는 createAndShowGUI 메소드 시작 부분에서 초기화됩니다.


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
        JScrollPane noticeScrollPane = new JScrollPane(noticeArea);
        noticeScrollPane.setPreferredSize(noticeArea.getPreferredSize());
        noticePanel.add(noticeScrollPane, BorderLayout.CENTER);
        noticePanel.add(southButtonPanel, BorderLayout.SOUTH);
        noticePanel.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));

        initialMainContentPanel = new JPanel(new BorderLayout());
        initialMainContentPanel.add(noticePanel, BorderLayout.WEST);

        // 문의하기 섹션 컴포넌트 생성 및 패널 구성
        JLabel inquiryLabel = new JLabel("문의하기");
        inquiryLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        JTextArea inquiryArea = new JTextArea(10, 30); // 공지사항과 같은 크기
        inquiryArea.setEditable(false);
        inquiryArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        inquiryArea.setText("문의 내용이 여기에 표시됩니다."); // 예시 텍스트

        addInquiryButton = new JButton("+");
        addInquiryButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        addInquiryButton.setEnabled(false); // 처음에는 비활성화!


        JPanel inquiryButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        inquiryButtonPanel.add(addInquiryButton);

        JPanel inquiryPanel = new JPanel();
        inquiryPanel.setLayout(new BorderLayout());
        inquiryPanel.add(inquiryLabel, BorderLayout.NORTH);
        JScrollPane inquiryScrollPane = new JScrollPane(inquiryArea);
        inquiryScrollPane.setPreferredSize(inquiryArea.getPreferredSize()); // noticeArea와 같은 선호 크기 설정
        inquiryPanel.add(inquiryScrollPane, BorderLayout.CENTER);
        inquiryPanel.add(inquiryButtonPanel, BorderLayout.SOUTH);

        // 문의하기 패널 오른쪽에 여백(Border) 추가!
        inquiryPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 70)); // 위, 왼쪽, 아래, 오른쪽(70) 여백 설정


        // 초기 메인 콘텐츠 패널에 문의하기 패널을 EAST 영역에 추가!
        initialMainContentPanel.add(inquiryPanel, BorderLayout.EAST);


        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel topPanel = new JPanel(new BorderLayout());

        adminCodeField = new JPasswordField(4);
        adminCheckButton = new JButton("관리자 확인");

        JPanel eastPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        eastPanel.add(loginLabel);
        eastPanel.add(new JLabel("관리자 코드:"));
        eastPanel.add(adminCodeField);
        eastPanel.add(adminCheckButton);

        topPanel.add(eastPanel, BorderLayout.EAST);

        frame.add(initialMainContentPanel, BorderLayout.CENTER);

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
                    if (logoutButton != null) {
                        logoutButton.setVisible(true);
                    }
                    if (addInquiryButton != null) {
                        addInquiryButton.setEnabled(true);
                    }


                    JFrame adminUserManageFrame = new JFrame("사용자 관리 (관리자)");
                    adminUserManageFrame.setSize(300, 400);
                    adminUserManageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    adminUserManageFrame.setLayout(new BorderLayout());

                    DefaultListModel<String> userListModel = new DefaultListModel<>();
                    for (String id : registeredIds) {
                        userListModel.addElement(id);
                    }
                    JList<String> userList = new JList<>(userListModel);
                    JScrollPane listScrollPane = new JScrollPane(userList);

                    JButton deleteUserButton = new JButton("선택된 사용자 삭제");
                    deleteUserButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent deleteE) {
                            String selectedId = userList.getSelectedValue();

                            if (selectedId != null) {
                                int confirm = JOptionPane.showConfirmDialog(adminUserManageFrame,
                                        selectedId + " 사용자를 정말 삭제하시겠습니까?", "사용자 삭제 확인",
                                        JOptionPane.YES_NO_OPTION);

                                if (confirm == JOptionPane.YES_OPTION) {
                                    registeredIds.remove(selectedId);
                                    users.remove(selectedId);
                                    userDetails.remove(selectedId);

                                    userListModel.removeElement(selectedId);

                                    JOptionPane.showMessageDialog(adminUserManageFrame, selectedId + " 사용자가 삭제되었습니다.", "삭제 완료", JOptionPane.INFORMATION_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(adminUserManageFrame, "삭제할 사용자를 선택하세요.", "안내", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    });

                    adminUserManageFrame.add(new JLabel("가입된 사용자 아이디 목록:", SwingConstants.CENTER), BorderLayout.NORTH);
                    adminUserManageFrame.add(listScrollPane, BorderLayout.CENTER);
                    adminUserManageFrame.add(deleteUserButton, BorderLayout.SOUTH);

                    adminUserManageFrame.setLocationRelativeTo(frame);
                    adminUserManageFrame.setVisible(true);


                } else {
                    JOptionPane.showMessageDialog(frame, "관리자 인증 실패!", "오류", JOptionPane.ERROR_MESSAGE);
                    adminCodeField.setText("");
                    isAdminVerified = false;
                    deleteButton.setVisible(false);
                    if (logoutButton != null) {
                        logoutButton.setVisible(false);
                    }
                    if (addInquiryButton != null) {
                        addInquiryButton.setEnabled(false);
                    }
                }
            }
        });

        topPanel.add(centerPanel, BorderLayout.CENTER);
        centerPanel.add(logoLabel);

        frame.add(topPanel, BorderLayout.NORTH);

        logoutButton = new JButton("로그아웃");
        logoutButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        logoutButton.setVisible(false);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(frame,
                        "로그아웃 하시겠습니까?", "로그아웃 확인",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    loginLabel.setText("로그인");
                    isAdminVerified = false;
                    deleteButton.setVisible(false);
                    logoutButton.setVisible(false);

                    if (adminCodeField != null) {
                        adminCodeField.setEnabled(true);
                        adminCodeField.setText("");
                    }
                    if (adminCheckButton != null) {
                        adminCheckButton.setEnabled(true);
                    }
                    if (addInquiryButton != null) {
                        addInquiryButton.setEnabled(false);
                    }


                    BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
                    java.awt.Component centerComponent = layout.getLayoutComponent(BorderLayout.CENTER);

                    if (centerComponent != null) {
                        frame.getContentPane().remove(centerComponent);
                    }

                    frame.getContentPane().add(initialMainContentPanel, BorderLayout.CENTER);

                    frame.revalidate();
                    frame.repaint();

                    JOptionPane.showMessageDialog(frame, "로그아웃 되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southPanel.add(logoutButton);
        frame.add(southPanel, BorderLayout.SOUTH);


        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // 문의하기 추가 버튼 액션 리스너 (기능 미구현)
        addInquiryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "문의 등록 기능은 아직 준비 중입니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(registeredIds);
            oos.writeObject(users);
            oos.writeObject(userDetails);
            oos.writeObject(noticeArea.getText());
            System.out.println("데이터 저장 완료!");
        } catch (IOException e) {
            System.err.println("데이터 저장 중 오류 발생: " + e.getMessage());
        }
    }

    private static void loadData() {
        File dataFile = new File(DATA_FILE);
        if (dataFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                registeredIds = (List<String>) ois.readObject();
                users = (Map<String, String>) ois.readObject();
                userDetails = (Map<String, Map<String, String>>) ois.readObject();
                String savedNoticeText = (String) ois.readObject();

                if (noticeArea != null) {
                    noticeArea.setText(savedNoticeText);
                }

                System.out.println("데이터 불러오기 완료!");
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("데이터 불러오기 중 오류 발생 또는 파일 형식이 다름: " + e.getMessage());
                initializeDefaultData();
            }
        } else {
            System.out.println("저장된 데이터 파일이 없습니다. 초기 데이터로 시작합니다.");
            initializeDefaultData();
        }
    }

    private static void initializeDefaultData() {
        registeredIds.clear();
        users.clear();
        userDetails.clear();

        registeredIds.add("admin");
        registeredIds.add("user1");
        registeredIds.add("test");
        users.put("admin", "admin123");
        users.put("user1", "pass123");
        users.put("test", "testpass");

        Map<String, String> adminDetails = new HashMap<>();
        adminDetails.put("name", "관리자");
        adminDetails.put("studentId", "00000000");
        adminDetails.put("major", "컴퓨터공학");
        userDetails.put("admin", adminDetails);

        Map<String, String> user1Details = new HashMap<>();
        user1Details.put("name", "사용자1");
        user1Details.put("studentId", "20221234");
        user1Details.put("major", "소프트웨어");
        userDetails.put("user1", user1Details);

        Map<String, String> testDetails = new HashMap<>();
        testDetails.put("name", "테스트");
        testDetails.put("studentId", "20235678");
        testDetails.put("major", "디자인");
        userDetails.put("test", testDetails);

        if (noticeArea != null) {
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
        }
    }


    private static void showMyPageContent(JFrame frame) {
        BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();
        java.awt.Component centerComponent = layout.getLayoutComponent(BorderLayout.CENTER);

        if (centerComponent != null) {
            frame.getContentPane().remove(centerComponent);
        }

        JPanel myPagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel certRegLabel = new JLabel("자격증 등록");
        certRegLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));

        JButton addButton = new JButton("+");
        addButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        myPagePanel.add(certRegLabel);
        myPagePanel.add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame inputPopup = new JFrame("내용 추가");
                inputPopup.setSize(300, 150);
                inputPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                inputPopup.setLayout(new FlowLayout());

                JTextField inputField = new JTextField(20);
                JButton confirmAddButton = new JButton("추가");

                inputPopup.add(new JLabel("추가할 내용:"));
                inputPopup.add(inputField);
                inputPopup.add(confirmAddButton);

                inputPopup.setLocationRelativeTo(myPagePanel);
                inputPopup.setVisible(true);

                confirmAddButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent confirmE) {
                        String content = inputField.getText();
                        if (!content.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(inputPopup, "추가할 내용: " + content, "확인", JOptionPane.INFORMATION_MESSAGE);
                            inputPopup.dispose();
                        } else {
                            JOptionPane.showMessageDialog(inputPopup, "내용을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                        }
                    }
                });
            }
        });


        frame.getContentPane().add(myPagePanel, BorderLayout.CENTER);

        frame.revalidate();
        frame.repaint();
    }
}
