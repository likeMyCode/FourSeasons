package gui;

import four_seasons.DatabaseRequest;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.Map;
import javax.swing.*;

public class SignInPanel extends JPanel {

    MainWindow window;
    JPanel thisPanel, actionPanel;
    JTextField loginField;
    JPasswordField passwordField;
    JButton signInButton;
    JLabel logoImage, backgroundImage, wrongDataLabel, emptyDataLabel,
            registerLabel, loadingGIF, userRegisteredLabel;

    public SignInPanel(MainWindow window) {
        this.window = window;
        thisPanel = this;
        initUI();
    }

    private void initUI() {
        actionPanel = new JPanel();
        loginField = new HintTextField("login");
        passwordField = new HintPasswordField("password");
        signInButton = new JButton("Sign In");
        logoImage = new JLabel(new ImageIcon("src/gui/img/logo.png"));
        backgroundImage = new JLabel(new ImageIcon("src/gui/img/background.png"));
        wrongDataLabel = new JLabel("Incorrect Data");
        emptyDataLabel = new JLabel("Empty Fields");
        registerLabel = new JLabel("Register new account");
        userRegisteredLabel = new JLabel("Account Created!");
        loadingGIF = new JLabel(new ImageIcon("src/gui/img/loading.gif"));

        this.setLayout(null);
        this.setFocusable(true);
        this.add(backgroundImage);

        actionPanel.add(loginField);
        actionPanel.add(passwordField);
        actionPanel.add(signInButton);
        actionPanel.add(logoImage);
        actionPanel.add(wrongDataLabel);
        actionPanel.add(emptyDataLabel);
        actionPanel.add(registerLabel);
        actionPanel.add(userRegisteredLabel);

        actionPanel.setLayout(null);
        actionPanel.setBackground(new Color(252, 252, 252));
        actionPanel.setBounds(80, 180, 221, 280);
        actionPanel.setBorder(BorderFactory.createLineBorder(new Color(214, 215, 213)));
        actionPanel.setOpaque(true);

        loginField.setBounds(38, 125, 150, 26);
        loginField.setFont(new Font("Calibri", Font.PLAIN, 16));
        loginField.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 205)));

        passwordField.setBounds(38, 158, 150, 26);
        passwordField.setFont(new Font("Calibri", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(205, 205, 205)));

        signInButton.setBounds(61, 210, 100, 26);
        signInButton.setBackground(new Color(240, 240, 240));
        signInButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        signInButton.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));

        logoImage.setBounds(35, 10, 150, 90);

        backgroundImage.setBounds(0, 0, 860, 600);

        wrongDataLabel.setBounds(72, 185, 130, 22);
        wrongDataLabel.setForeground(Color.red);
        wrongDataLabel.setFont(new Font("Arial", Font.BOLD, 11));
        wrongDataLabel.setVisible(false);

        registerLabel.setBounds(57, 245, 130, 22);
        registerLabel.setForeground(new Color(89, 199, 200));
        registerLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        registerLabel.setVisible(true);

        userRegisteredLabel.setBounds(68, 185, 130, 22);
        userRegisteredLabel.setForeground(new Color(50, 212, 50));
        userRegisteredLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        userRegisteredLabel.setVisible(false);

        loadingGIF.setBounds(30, 90, 200, 200);

        emptyDataLabel.setBounds(74, 182, 130, 22);
        emptyDataLabel.setForeground(Color.red);
        emptyDataLabel.setFont(new Font("Arial", Font.BOLD, 11));
        emptyDataLabel.setVisible(false);

        backgroundImage.setLayout(null);
        backgroundImage.add(actionPanel);

        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn(loginField.getText(), passwordField.getText());
            }
        });

        signInButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                signIn(loginField.getText(), passwordField.getText());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                e.getComponent().setBackground(new Color(235, 240, 240));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(new Color(240, 240, 240));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        passwordField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    signIn(loginField.getText(), passwordField.getText());
                }
            }
        });

        loginField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 10) {
                    signIn(loginField.getText(), passwordField.getText());
                }
            }
        });

        registerLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel registerPanel = new RegisterPanel(window);
                window.add(registerPanel);
                thisPanel.setVisible(false);
                registerPanel.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                Font font = registerLabel.getFont();
                Map attributes = font.getAttributes();
                attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
                registerLabel.setFont(font.deriveFont(attributes));

            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                e.getComponent().setFont(new Font("Arial", Font.PLAIN, 11));
            }
        });

    }

    public void userRegistered() {
        userRegisteredLabel.setVisible(true);
    }

    private void signIn(String login, String password) {
        if (loginField.getText() == "" || passwordField.getText() == "") {
            emptyDataLabel.setVisible(true);
            return;
        }
        emptyDataLabel.setVisible(false);

        int signStatus = DatabaseRequest.signIn(login, password);
        switch (signStatus) {
            case 0:
                wrongDataLabel.setVisible(true);
                break;
            case 1:
                window.clientLogged(login, password);
                JPanel pickRestaurantPanel = new PickRestaurantPanel(window);
                window.add(pickRestaurantPanel);
                pickRestaurantPanel.setVisible(true);
                thisPanel.setVisible(false);
                break;
            case 2:
                window.managerLogged(login, password);
                if (DatabaseRequest.managerHasRestaurant(login)) {
                    JPanel managerPanel = new ManagerPanel(window);
                    window.add(managerPanel);
                    managerPanel.setVisible(true);
                    thisPanel.setVisible(false);

                } else {
                    JPanel registerRestaurantPanel = new RegisterRestaurantPanel(window);
                    window.add(registerRestaurantPanel);
                    registerRestaurantPanel.setVisible(true);
                    thisPanel.setVisible(false);
                }
                break;
            case -1:
                wrongDataLabel.setVisible(true);
                System.out.println("Unknown Error!");
                break;
        }
    }

    class HintTextField extends JTextField implements FocusListener {

        private final String hint;
        private boolean showingHint;

        public HintTextField(final String hint) {
            super(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
            super.setForeground(new Color(170, 170, 170));
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText("");
                super.setForeground(new Color(0, 0, 0));
                showingHint = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText(hint);
                super.setForeground(new Color(170, 170, 170));
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }

    class HintPasswordField extends JPasswordField implements FocusListener {

        private final String hint;
        private boolean showingHint;

        public HintPasswordField(final String hint) {
            super(hint);
            this.hint = hint;
            this.showingHint = true;
            super.addFocusListener(this);
            super.setForeground(new Color(170, 170, 170));
        }

        @Override
        public void focusGained(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText("");
                super.setForeground(new Color(0, 0, 0));
                showingHint = false;
            }
        }

        @Override
        public void focusLost(FocusEvent e) {
            if (this.getText().isEmpty()) {
                super.setText(hint);
                super.setForeground(new Color(170, 170, 170));
                showingHint = true;
            }
        }

        @Override
        public String getText() {
            return showingHint ? "" : super.getText();
        }
    }
}
