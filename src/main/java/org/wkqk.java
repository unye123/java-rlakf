import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

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

    private static JTextArea registeredCertsArea;
    private static JButton addCertButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        noticeArea = new JTextArea(10, 30);
        noticeArea.setEditable(false);
        noticeArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        loadData();

        JFrame frame = new JFrame("자격증 홈페이지");
        frame.setSize(1080, 720);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                saveData();
                System.exit(0);
            }
        });

        adminCodeField = new JPasswordField(4);
        adminCheckButton = new JButton("관리자 확인");

        JLabel logoLabel = new JLabel("lee Certificate");
        logoLabel.setFont(new Font("Serif", Font.BOLD, 24));

        JLabel loginLabel = new JLabel("로그인");
        loginLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        loginLabel.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                if (loginLabel.getText().equals("로그인")) {
                    showLoginPopup(frame, loginLabel);
                } else if (loginLabel.getText().equals("my page")) {
                    showMyPageContent(frame);
                }
            }
        });

        JLabel noticeLabel = new JLabel("공지사항");
        noticeLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        JButton moreButton = new JButton("+");
        moreButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        deleteButton = new JButton("-");
        deleteButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        deleteButton.setVisible(false);
        deleteButton.addActionListener(e -> {
            String selectedText = noticeArea.getSelectedText();
            if (selectedText != null && !selectedText.isEmpty()) {
                String fullText = noticeArea.getText();
                String newText = fullText.replace(selectedText, "");
                noticeArea.setText(newText);
                JOptionPane.showMessageDialog(frame, "선택된 공지사항 내용이 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "삭제할 내용을 선택하세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        moreButton.addActionListener(e -> {
            if (isAdminVerified) {
                JFrame addNoticePopup = new JFrame("공지사항 추가");
                addNoticePopup.setSize(400, 300);
                addNoticePopup.setLayout(new BorderLayout());

                JTextArea newNoticeInputArea = new JTextArea();
                newNoticeInputArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
                JScrollPane inputScrollPane = new JScrollPane(newNoticeInputArea);

                JButton addButton = new JButton("추가");
                addButton.addActionListener(ev -> {
                    String newNotice = newNoticeInputArea.getText();
                    if (!newNotice.trim().isEmpty()) {
                        noticeArea.append("\n" + (noticeArea.getText().trim().isEmpty() ? "" : "\n") + newNotice);
                        addNoticePopup.dispose();
                    } else {
                        JOptionPane.showMessageDialog(addNoticePopup, "추가할 내용을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
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
                fullNoticeArea.setText(noticeArea.getText() +
                        "\n11. 재시험 일정은 별도 공지됩니다.\n" +
                        "12. 모의고사 일정은 추후 안내됩니다.\n");

                JScrollPane scrollPane = new JScrollPane(fullNoticeArea);
                popup.add(scrollPane);
                popup.setLocationRelativeTo(null);
                popup.setVisible(true);
            }
        });

        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.add(deleteButton);
        southButtonPanel.add(moreButton);

        JPanel noticePanel = new JPanel(new BorderLayout());
        noticePanel.add(noticeLabel, BorderLayout.NORTH);
        JScrollPane noticeScrollPane = new JScrollPane(noticeArea);
        noticeScrollPane.setPreferredSize(noticeArea.getPreferredSize());
        noticePanel.add(noticeScrollPane, BorderLayout.CENTER);
        noticePanel.add(southButtonPanel, BorderLayout.SOUTH);
        noticePanel.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 0));

        initialMainContentPanel = new JPanel(new BorderLayout());
        initialMainContentPanel.add(noticePanel, BorderLayout.WEST);

        JLabel inquiryLabel = new JLabel("문의하기");
        inquiryLabel.setFont(new Font("맑은 고딕", Font.BOLD, 14));

        JTextArea inquiryArea = new JTextArea(10, 30);
        inquiryArea.setEditable(false);
        inquiryArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        inquiryArea.setText("문의 내용이 여기에 표시됩니다.");

        addInquiryButton = new JButton("+");
        addInquiryButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        addInquiryButton.setEnabled(false);

        JPanel inquiryButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        inquiryButtonPanel.add(addInquiryButton);

        JPanel inquiryPanel = new JPanel(new BorderLayout());
        inquiryPanel.add(inquiryLabel, BorderLayout.NORTH);
        JScrollPane inquiryScrollPane = new JScrollPane(inquiryArea);
        inquiryScrollPane.setPreferredSize(inquiryArea.getPreferredSize());
        inquiryPanel.add(inquiryScrollPane, BorderLayout.CENTER);
        inquiryPanel.add(inquiryButtonPanel, BorderLayout.SOUTH);
        inquiryPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 70));

        initialMainContentPanel.add(inquiryPanel, BorderLayout.EAST);

        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel topPanel = new JPanel(new BorderLayout());

        JPanel myPageContainerPanel = new JPanel(new BorderLayout());
        myPageContainerPanel.setBorder(BorderFactory.createEmptyBorder(10, 70, 10, 10));

        myPageContentPanel = new JPanel();
        myPageContentPanel.setLayout(new BoxLayout(myPageContentPanel, BoxLayout.Y_AXIS));

        certRegSectionFullPanel = new JPanel();
        certRegSectionFullPanel.setLayout(new BoxLayout(certRegSectionFullPanel, BoxLayout.Y_AXIS));

        certRegSectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel certRegLabel = new JLabel("자격증 등록");
        certRegLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        addCertButton = new JButton("+");
        addCertButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        certRegSectionPanel.add(certRegLabel);
        certRegSectionPanel.add(addCertButton);

        registeredCertsArea = new JTextArea();
        registeredCertsArea.setEditable(false);
        registeredCertsArea.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        JScrollPane certsScrollPane = new JScrollPane(registeredCertsArea);
        certsScrollPane.setPreferredSize(new Dimension(300, 150));
        certsScrollPane.setMaximumSize(new Dimension(300, 400));

        certRegSectionFullPanel.add(certRegSectionPanel);
        certRegSectionFullPanel.add(certsScrollPane);

        myPageContentPanel.add(certRegSectionFullPanel);
        myPageContentPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        JPanel examInfoSectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel examInfoLabel = new JLabel("시험 일정/정보");
        examInfoLabel.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        JButton viewExamButton = new JButton("보기");
        viewExamButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        examInfoSectionPanel.add(examInfoLabel);
        examInfoSectionPanel.add(viewExamButton);

        myPageContentPanel.add(examInfoSectionPanel);

        myPageContainerPanel.add(myPageContentPanel, BorderLayout.WEST);

        adminCodeField = new JPasswordField(4);
        adminCheckButton = new JButton("관리자 확인");

        JPanel eastPanelFlow = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        eastPanelFlow.add(loginLabel);
        eastPanelFlow.add(new JLabel("관리자 코드:"));
        eastPanelFlow.add(adminCodeField);
        eastPanelFlow.add(adminCheckButton);

        topPanel.add(eastPanelFlow, BorderLayout.EAST);

        frame.add(initialMainContentPanel, BorderLayout.CENTER);
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(myPageContainerPanel, BorderLayout.CENTER);

        addCertButton.addActionListener(e -> {
            JFrame inputPopup = new JFrame("내용 추가");
            inputPopup.setSize(300, 150);
            inputPopup.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            inputPopup.setLayout(new FlowLayout());

            JTextField inputField = new JTextField(20);
            JButton confirmAddButton = new JButton("추가");

            inputPopup.add(new JLabel("추가할 내용:"));
            inputPopup.add(inputField);
            inputPopup.add(confirmAddButton);

            inputPopup.setLocationRelativeTo(frame);
            inputPopup.setVisible(true);

            confirmAddButton.addActionListener(confirmE -> {
                String content = inputField.getText();
                if (!content.trim().isEmpty()) {
                    registeredCertsArea.append((registeredCertsArea.getText().trim().isEmpty() ? "" : "\n") + content);
                    inputPopup.dispose();
                } else {
                    JOptionPane.showMessageDialog(inputPopup, "내용을 입력하세요.", "경고", JOptionPane.WARNING_MESSAGE);
                }
            });
        });

        viewExamButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "시험 일정/정보 보기 기능은 아직 준비 중입니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
        });

        adminCheckButton.addActionListener(e -> {
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
                deleteUserButton.addActionListener(deleteE -> {
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
        });

        deleteButton = new JButton("-");
        deleteButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        deleteButton.setVisible(false);
        deleteButton.addActionListener(e -> {
            String selectedText = noticeArea.getSelectedText();

            if (selectedText != null && !selectedText.isEmpty()) {
                String fullText = noticeArea.getText();
                String newText = fullText.replace(selectedText, "");
                noticeArea.setText(newText);

                JOptionPane.showMessageDialog(frame, "선택된 공지사항 내용이 삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(frame, "삭제할 내용을 선택하세요.", "안내", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        moreButton = new JButton("+");
        moreButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));

        JPanel southButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        southButtonPanel.add(deleteButton);
        southButtonPanel.add(moreButton);

        frame.add(southButtonPanel, BorderLayout.SOUTH);

        logoutButton = new JButton("로그아웃");
        logoutButton.setFont(new Font("맑은 고딕", Font.PLAIN, 14));
        logoutButton.setVisible(false);

        logoutButton.addActionListener(e -> {
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
        });

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addInquiryButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "문의 등록 기능은 아직 준비 중입니다.", "안내", JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
