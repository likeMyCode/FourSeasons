package gui;

import four_seasons.AdditionalMethods;
import four_seasons.DatabaseRequest;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPanel extends JPanel {

    MainWindow window;
    JPanel actionPanel;
    JPanel thisPanel;
    JPanel logPanel;
    JLabel backgroundImage, logoImage, loginLabel, cityLabel, streetLabel,
            houseNumberLabel, flatNumberLabel, passwordLabel, confirmPasswordLabel;
    JTextField loginField, cityField, streetField, houseNumberField, flatNumber;
    JPasswordField passwordField, confirmPassField;
    JButton registerButton;

    public RegisterPanel(MainWindow window) {
        this.window = window;
        thisPanel = this;
        initUI();
    }

    private void initUI() {
        actionPanel = new JPanel();
        backgroundImage = new JLabel(new ImageIcon("src/gui/img/background.png"));
        loginField = new HintTextField("");
        cityField = new HintTextField("");
        streetField = new HintTextField("");
        houseNumberField = new HintTextField("");
        flatNumber = new HintTextField("");
        passwordField = new HintPasswordField("");
        confirmPassField = new HintPasswordField("");
        registerButton = new JButton("Register");
        logoImage = new JLabel(new ImageIcon("src/gui/img/logo.png"));
        loginLabel = new JLabel("Login");
        passwordLabel = new JLabel("Password");
        confirmPasswordLabel = new JLabel("Confirm Pass");
        cityLabel = new JLabel("City");
        streetLabel = new JLabel("Street");
        houseNumberLabel = new JLabel("House Number");
        flatNumberLabel = new JLabel("Flat Number");

        logPanel = new JPanel();

        this.setLayout(null);
        this.setFocusable(true);
        actionPanel.setLayout(null);
        logPanel.setLayout(null);

        actionPanel.setBackground(new Color(252, 252, 252));
        actionPanel.setBounds(80, 100, 300, 420);
        actionPanel.setBorder(BorderFactory.createLineBorder(new Color(214, 215, 213)));
        actionPanel.setOpaque(true);
        logPanel.add(loginField);
        logPanel.add(passwordField);
        logPanel.add(confirmPassField);
        logPanel.add(loginLabel);
        logPanel.add(passwordLabel);
        logPanel.add(confirmPasswordLabel);
        actionPanel.add(cityField);
        actionPanel.add(streetField);
        actionPanel.add(houseNumberField);
        actionPanel.add(flatNumber);
        actionPanel.add(cityLabel);
        actionPanel.add(streetLabel);
        actionPanel.add(houseNumberLabel);
        actionPanel.add(flatNumberLabel);
        actionPanel.add(registerButton);
        actionPanel.add(logoImage);
        actionPanel.add(logPanel);

        logPanel.setBounds(1, 120, 300, 131);
        logPanel.setBackground(new Color(252, 252, 252));

        loginField.setBounds(154, 0, 120, 26);
        loginField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        loginLabel.setBounds(39, 0, 120, 26);
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        loginField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        passwordField.setBounds(154, 34, 120, 26);
        passwordField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        passwordLabel.setBounds(39, 34, 120, 26);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        passwordField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        confirmPassField.setBounds(154, 68, 120, 26);
        confirmPassField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        confirmPasswordLabel.setBounds(39, 68, 120, 26);
        confirmPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        confirmPassField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        cityField.setBounds(155, 232, 120, 26);
        cityField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        cityLabel.setBounds(40, 232, 120, 26);
        cityLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        cityField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        streetField.setBounds(155, 264, 120, 26);
        streetField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        streetLabel.setBounds(40, 264, 120, 26);
        streetLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        streetField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        houseNumberField.setBounds(155, 298, 120, 26);
        houseNumberField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        houseNumberLabel.setBounds(40, 298, 120, 26);
        houseNumberLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        houseNumberField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        flatNumber.setBounds(155, 334, 120, 26);
        flatNumber.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        flatNumberLabel.setBounds(40, 334, 120, 26);
        flatNumberLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        flatNumber.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        logoImage.setBounds(80, 10, 150, 90);
        registerButton.setBounds(110, 380, 90, 26);
        registerButton.setBackground(new Color(240, 240, 240));
        registerButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        registerButton.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));

        backgroundImage.setBounds(0, 0, 860, 600);
        this.add(backgroundImage);
        backgroundImage.setLayout(null);
        backgroundImage.add(actionPanel);

        logoImage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JPanel signInPanel = new SignInPanel(window);
                window.add(signInPanel);
                thisPanel.setVisible(false);
                signInPanel.setVisible(true);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                e.getComponent().setBackground(new Color(212, 212, 212));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(new Color(212, 212, 212));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        registerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                register(loginField.getText(), passwordField.getText(),
                        confirmPassField.getText(), cityField.getText(),
                        streetField.getText(), houseNumberField.getText(),
                        flatNumber.getText());
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

    }

    private void register(String login, String password, String confirmPass,
            String city, String street, String houseNumber, String flatNumber) {

        boolean noError = true;

        if (login == "" || login.length() < 1 || login.length() > 15) {
            loginField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
            noError = false;
        } else {
            loginField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
        }

        if (!password.equals(confirmPass) || password == "" || password.length() < 5 || password.length() > 30) {
            passwordField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
            confirmPassField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
            noError = false;
        } else {
            passwordField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
            confirmPassField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
        }

        if ("".equals(street) || street.length() > 30) {
            streetField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
            noError = false;
        } else {
            streetField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
        }

        if ("".equals(city) || city.length() > 30) {
            cityField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
            noError = false;
        } else {
            cityField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
        }

        if ("".equals(houseNumber) || houseNumber.length() > 6) {
            houseNumberField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
            noError = false;
        } else {
            houseNumberField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
        }

        if ((!AdditionalMethods.isInteger(flatNumber) && !flatNumber.equals("")) || flatNumber.length() > 5) {
            this.flatNumber.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
            noError = false;
        } else {
            this.flatNumber.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
        }

        if (noError) {
            int status = DatabaseRequest.registerClient(AdditionalMethods.removeDiacriticalMarks(login),
                    password,
                    AdditionalMethods.removeDiacriticalMarks(city),
                    AdditionalMethods.removeDiacriticalMarks(street), houseNumber, flatNumber);

            switch (status) {
                case -1:
                    streetField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                    break;
                case 0:
                    loginField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                    break;
                case 1:
                    SignInPanel signInPanel = new SignInPanel(window);
                    window.add(signInPanel);
                    signInPanel.userRegistered();
                    thisPanel.setVisible(false);
                    break;

            }
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
