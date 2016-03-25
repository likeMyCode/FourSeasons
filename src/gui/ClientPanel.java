package gui;

import four_seasons.AdditionalMethods;
import four_seasons.DatabaseRequest;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ClientPanel extends JPanel {

    private MainWindow window;
    private JPanel thisPanel, header, footer, userInfoPanel, yourOrdersPanel;
    private JScrollPane deliveryPane;
    private JButton userLogout;

    public ClientPanel(MainWindow window) {
        this.window = window;
        thisPanel = this;
        initUI();
    }

    private void initUI() {
        this.setLayout(null);
        this.setBackground(new Color(240, 240, 240));

        userLogout = new JButton("Logout");
        header = new HeaderPanel();
        footer = new FooterPanel();
        yourOrdersPanel = new YourOrdersList();
        deliveryPane = new JScrollPane(yourOrdersPanel);
        userInfoPanel = new UserInfoPanel();

        deliveryPane.setBorder(null);
        deliveryPane.setBounds(0, 51, 600, 500);
        deliveryPane.setBorder(null);
        deliveryPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
        deliveryPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(220, 220, 220);
            }

            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }

            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
        });

        this.add(header);
        this.add(footer);
        this.add(deliveryPane);
        this.add(userInfoPanel);
        userInfoPanel.add(userLogout);
    }

    class HeaderPanel extends JPanel {

        private JLabel logoImage, infoLabel;
        private JButton userMenu;

        public HeaderPanel() {
            setLayout(null);
            setFocusable(true);
            setBounds(0, 0, 860, 50);
            setBackground(new Color(245, 245, 245));

            userMenu = new JButton(window.getLogin());
            logoImage = new JLabel(new ImageIcon("src/gui/img/logo-mini.png"));

            infoLabel = new JLabel("Your Settings");
            infoLabel.setBounds(100, 15, 120, 30);
            infoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 20));

            logoImage.setBounds(5, 5, 100, 40);
            logoImage.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPanel pickRestaurantPanel = new PickRestaurantPanel(window);
                    window.add(pickRestaurantPanel);
                    pickRestaurantPanel.setVisible(true);
                    thisPanel.setVisible(false);
                    window.remove(thisPanel);
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
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });

            userMenu.setBounds(730, 0, 100, 50);
            userMenu.setFont(new Font("Segoe Ui", Font.PLAIN, 16));
            userMenu.setBackground(new Color(245, 245, 245));
            userMenu.setBorder(null);

            userLogout.setBounds(730, 50, 100, 40);
            userLogout.setFont(new Font("Segoe Ui", Font.PLAIN, 16));
            userLogout.setBackground(new Color(245, 245, 245));
            userLogout.setBorder(null);
            userLogout.setVisible(false);

            userLogout.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    thisPanel.setVisible(false);
                    window.logout();
                    JPanel signInPanel = new SignInPanel(window);
                    window.add(signInPanel);
                    signInPanel.setVisible(true);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    e.getComponent().setBackground(new Color(245, 245, 245));
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    userLogout.setBackground(new Color(250, 250, 250));
                    userLogout.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    userLogout.setBackground(new Color(245, 245, 245));
                    userLogout.setVisible(false);
                }
            });

            userMenu.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JPanel clientPanel = new ClientPanel(window);
                    window.add(clientPanel);
                    clientPanel.setVisible(true);
                    thisPanel.setVisible(false);
                    window.remove(thisPanel);
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    userMenu.setBackground(new Color(250, 250, 250));
                    userLogout.setVisible(true);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    e.getComponent().setBackground(new Color(245, 245, 245));
                    userLogout.setVisible(false);
                }
            });

            this.add(logoImage);
            this.add(userMenu);
            this.add(infoLabel);
        }
    }

    class FooterPanel extends JPanel {

        public FooterPanel() {
            this.setBounds(0, 551, 860, 20);
            this.setBackground(new Color(250, 250, 250));
        }
    }

    class UserInfoPanel extends JPanel {

        private final Color bgColor = new Color(252, 252, 252);

        private JLabel infoLabel;
        private JTextField cityField, streetField, houseNumberField, flatNumberField;
        private JLabel passwordLabel, confirmPasswordLabel, oldPasswordLabel,
                cityLabel, streetLabel, houseNumberLabel, flatNumberLabel,
                editDataCorrectLabel, changePasswordCorrectLabel;
        private JButton editButton, changePasswordButton;
        private JPasswordField passwordField, confirmPasswordField, oldPasswordField;

        public UserInfoPanel() {
            this.setLayout(null);
            this.setFocusable(true);
            this.setBounds(601, 50, 860 - 601, 530);
            this.setBackground(bgColor);
            this.setVisible(true);
            initUI();
        }

        private void initUI() {
            ArrayList<String> clientData = DatabaseRequest.
                    getClientData(window.getLogin());

            oldPasswordLabel = new JLabel("Old Password");
            oldPasswordField = new JPasswordField();
            passwordField = new JPasswordField();
            confirmPasswordField = new JPasswordField();
            cityField = new JTextField(clientData.get(0));
            streetField = new JTextField(clientData.get(1));
            houseNumberField = new JTextField(clientData.get(2));
            flatNumberField = new JTextField(clientData.get(3));
            passwordLabel = new JLabel("New Password");
            confirmPasswordLabel = new JLabel("Confirm Pass");
            cityLabel = new JLabel("City");
            streetLabel = new JLabel("Street");
            houseNumberLabel = new JLabel("House Number");
            flatNumberLabel = new JLabel("Flat Number");
            changePasswordButton = new JButton("Change Pass");
            editDataCorrectLabel = new JLabel("Account Edited");
            changePasswordCorrectLabel = new JLabel("Password Changed");

            passwordLabel.setBounds(20, 365, 100, 30);
            passwordLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            passwordField.setBounds(120, 365, 120, 30);
            passwordField.setFont(new Font("Calibri", Font.PLAIN, 14));
            passwordField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

            confirmPasswordLabel.setBounds(20, 405, 100, 30);
            confirmPasswordLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            confirmPasswordField.setBounds(120, 405, 120, 30);
            confirmPasswordField.setFont(new Font("Calibri", Font.PLAIN, 14));
            confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

            changePasswordCorrectLabel.setBounds(90, 290, 130, 22);
            changePasswordCorrectLabel.setForeground(new Color(50, 212, 50));
            changePasswordCorrectLabel.setFont(new Font("Arial", Font.PLAIN, 11));
            changePasswordCorrectLabel.setVisible(false);

            oldPasswordLabel.setBounds(20, 325, 100, 30);
            oldPasswordLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            oldPasswordField.setBounds(120, 325, 120, 30);
            oldPasswordField.setFont(new Font("Calibri", Font.PLAIN, 14));
            oldPasswordField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

            cityLabel.setBounds(20, 85, 100, 30);
            cityLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            cityField.setBounds(120, 85, 120, 30);
            cityField.setFont(new Font("Calibri", Font.PLAIN, 14));
            cityField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

            streetLabel.setBounds(20, 125, 100, 30);
            streetLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            streetField.setBounds(120, 125, 120, 30);
            streetField.setFont(new Font("Calibri", Font.PLAIN, 14));
            streetField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

            houseNumberLabel.setBounds(20, 165, 100, 30);
            houseNumberLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            houseNumberField.setBounds(120, 165, 120, 30);
            houseNumberField.setFont(new Font("Calibri", Font.PLAIN, 14));
            houseNumberField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

            flatNumberLabel.setBounds(20, 205, 100, 30);
            flatNumberLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
            flatNumberField.setBounds(120, 205, 120, 30);
            flatNumberField.setFont(new Font("Calibri", Font.PLAIN, 14));
            flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

            editDataCorrectLabel.setBounds(96, 290, 130, 22);
            editDataCorrectLabel.setForeground(new Color(50, 212, 50));
            editDataCorrectLabel.setFont(new Font("Arial", Font.PLAIN, 11));
            editDataCorrectLabel.setVisible(false);

            JPanel separatePanel = new JPanel();
            infoLabel = new JLabel("Personal Info", SwingConstants.CENTER);
            editButton = new JButton("Edit Data");
            editButton.setBounds(95, 255, 80, 30);
            editButton.setFont(new Font("Calibri", Font.PLAIN, 14));
            editButton.setBackground(new Color(245, 245, 245));
            editButton.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

            separatePanel.setBounds(0, 0, 256, 65);
            separatePanel.setBackground(new Color(130, 237, 125));
            separatePanel.setLayout(null);

            infoLabel.setBounds(0, 0, 256, 65);
            infoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 22));
            infoLabel.setForeground(Color.WHITE);

            editButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    editClientData(cityField.getText(), streetField.getText(), houseNumberField.getText(), flatNumberField.getText());
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    e.getComponent().setBackground(new Color(242, 242, 242));
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    e.getComponent().setBackground(new Color(245, 245, 245));
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });

            changePasswordButton.setBounds(90, 455, 90, 30);
            changePasswordButton.setFont(new Font("Calibri", Font.PLAIN, 14));
            changePasswordButton.setBackground(new Color(245, 245, 245));
            changePasswordButton.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));

            changePasswordButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    changeClientPassword(oldPasswordField.getText(), passwordField.getText(),
                            confirmPasswordField.getText());

                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    e.getComponent().setBackground(new Color(242, 242, 242));
                    setCursor(new Cursor(Cursor.HAND_CURSOR));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    e.getComponent().setBackground(new Color(245, 245, 245));
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
            });

            this.add(editDataCorrectLabel);
            this.add(separatePanel);
            this.add(editButton);
            this.add(oldPasswordField);
            this.add(passwordField);
            this.add(confirmPasswordField);
            this.add(changePasswordCorrectLabel);
            this.add(cityField);
            this.add(streetField);
            this.add(houseNumberField);
            this.add(flatNumberField);
            this.add(passwordLabel);
            this.add(oldPasswordLabel);
            this.add(confirmPasswordLabel);
            this.add(cityLabel);
            this.add(streetLabel);
            this.add(houseNumberLabel);
            this.add(flatNumberLabel);
            this.add(changePasswordButton);
            this.add(userLogout);
            separatePanel.add(infoLabel);
        }

        private void changeClientPassword(String oldPass, String newPass, String confirmPass) {
            boolean noError = true;

            if ("".equals(oldPass) || !oldPass.equals(window.getPassword())) {
                oldPasswordField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                noError = false;
            } else {
                oldPasswordField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
            }

            if (!newPass.equals(confirmPass) || newPass.equals("") || newPass.length() < 5 || newPass.length() > 30) {
                passwordField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                noError = false;
            } else {
                passwordField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
                confirmPasswordField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
            }

            if (noError) {
                DatabaseRequest.changeClientPassword(window.getLogin(), newPass);
                window.setPassword(newPass);
                editDataCorrectLabel.setVisible(false);
                changePasswordCorrectLabel.setVisible(true);
                oldPasswordField.setText("");
                passwordField.setText("");
                confirmPasswordField.setText("");
            } else {
                editDataCorrectLabel.setVisible(false);
                changePasswordCorrectLabel.setVisible(false);
            }
        }

        private void editClientData(String city, String street, String houseNumber, String flatNumber) {

            boolean noError = true;

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
                flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                noError = false;
            } else {
                flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
            }

            if (noError) {
                int status = DatabaseRequest.editClientData(window.getLogin(),
                        AdditionalMethods.removeDiacriticalMarks(city),
                        AdditionalMethods.removeDiacriticalMarks(street),
                        houseNumber, flatNumber);

                switch (status) {
                    case -1:
                        streetField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                        cityField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
                        break;
                    case 1:
                        editDataCorrectLabel.setVisible(true);
                        changePasswordCorrectLabel.setVisible(false);
                }
            } else {
                editDataCorrectLabel.setVisible(false);
                changePasswordCorrectLabel.setVisible(false);
            }
        }
    }

    class YourOrdersList extends JPanel {

        private ArrayList<YourOrder> yourOrdersList;
        private static final int PANEL_WIDTH = 800;
        private static final int PANEL_HEIGHT = 60;
        private int actuallYPosition = 0;

        private int selectedDishID = 0;

        public YourOrdersList() {
            super();
            this.setLayout(null);
            this.setBackground(new Color(245, 245, 245));
            yourOrdersList = new ArrayList<>();
            createList(DatabaseRequest.getOrderList(window.getLogin()));
            drawContent();
        }

        public void createList(ArrayList<YourOrder> newOrdersList) {
            yourOrdersList.clear();

            for (YourOrder yourOrder : newOrdersList) {
                yourOrdersList.add(yourOrder);
            }
        }

        private void rateOrder(int stars, int orderID) {
            DatabaseRequest.rateOrder(orderID, stars);
            thisPanel.remove(deliveryPane);
            yourOrdersPanel = new YourOrdersList();
            deliveryPane = new JScrollPane(yourOrdersPanel);
            deliveryPane.setBorder(null);
            deliveryPane.setBounds(0, 51, 600, 500);
            deliveryPane.setBorder(null);
            deliveryPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
            deliveryPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
                @Override
                protected void configureScrollBarColors() {
                    this.thumbColor = new Color(220, 220, 220);
                }

                @Override
                protected JButton createDecreaseButton(int orientation) {
                    return createZeroButton();
                }

                @Override
                protected JButton createIncreaseButton(int orientation) {
                    return createZeroButton();
                }
            });
            this.add(deliveryPane);
        }

        private void drawContent() {
            for (final YourOrder yourOrder : yourOrdersList) {
                JPanel panel = new JPanel();
                JLabel restaurantNameLabel = new JLabel(yourOrder.getRestaurantName());
                JLabel priceLabel = new JLabel(Float.toString(yourOrder.getPrice()) + " $");
                JLabel statusLabel = new JLabel();
                final JLabel star1, star2, star3, star4, star5;

                panel.setLayout(null);
                panel.setBounds(0, actuallYPosition, PANEL_WIDTH, PANEL_HEIGHT);
                panel.setFocusable(true);

                restaurantNameLabel.setBounds(20, 18, 200, 30);
                restaurantNameLabel.setFont(new Font("Calibri Light", Font.PLAIN, 20));

                priceLabel.setBounds(120, 18, 55, 30);
                priceLabel.setFont(new Font("Calibri", Font.PLAIN, 18));

                statusLabel.setBounds(410, 18, 200, 30);
                statusLabel.setFont(new Font("Calibri Light", Font.PLAIN, 18));

                switch (yourOrder.getStatus()) {
                    case "WAIT_TO_ACCEPT":
                        statusLabel.setBounds(410, 18, 200, 30);
                        statusLabel.setForeground(new Color(87, 199, 223));
                        statusLabel.setText("Waitng for restaurant");
                        break;

                    case "ACCEPTED":
                        statusLabel.setBounds(430, 18, 200, 30);
                        statusLabel.setForeground(new Color(45, 220, 87));
                        statusLabel.setText("Order accepted");
                        break;

                    case "DENIED":
                        statusLabel.setBounds(437, 18, 200, 30);
                        statusLabel.setForeground(new Color(250, 20, 20));
                        statusLabel.setText("Order denied");
                        break;
                }

                star1 = new JLabel(new ImageIcon("src/gui/img/star-none.png"));
                star2 = new JLabel(new ImageIcon("src/gui/img/star-none.png"));
                star3 = new JLabel(new ImageIcon("src/gui/img/star-none.png"));
                star4 = new JLabel(new ImageIcon("src/gui/img/star-none.png"));
                star5 = new JLabel(new ImageIcon("src/gui/img/star-none.png"));

                switch (yourOrder.getStars()) {
                    case 1:
                        star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        break;
                    case 2:
                        star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star2.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        break;
                    case 3:
                        star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star2.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star3.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        break;
                    case 4:
                        star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star2.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star3.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star4.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        break;
                    case 5:
                        star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star2.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star3.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star4.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        star5.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                        break;
                    default:
                        break;
                }

                star1.setBounds(190, 5, 50, 50);
                star2.setBounds(225, 5, 50, 50);
                star3.setBounds(260, 5, 50, 50);
                star4.setBounds(295, 5, 50, 50);
                star5.setBounds(330, 5, 50, 50);

                if (yourOrder.getStatus().equals("ACCEPTED")) {
                    star1.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                rateOrder(1, yourOrder.getID());
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                setCursor(new Cursor(Cursor.HAND_CURSOR));
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            }
                        }
                    });
                    star2.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                rateOrder(2, yourOrder.getID());
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                setCursor(new Cursor(Cursor.HAND_CURSOR));
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            }
                        }
                    });
                    star3.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                rateOrder(3, yourOrder.getID());
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                setCursor(new Cursor(Cursor.HAND_CURSOR));
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            }
                        }
                    });
                    star4.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                rateOrder(4, yourOrder.getID());
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                setCursor(new Cursor(Cursor.HAND_CURSOR));
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            }
                        }
                    });
                    star5.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                rateOrder(5, yourOrder.getID());
                            }
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {
                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {
                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-full.png"));
                                setCursor(new Cursor(Cursor.HAND_CURSOR));
                            }
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            if (yourOrder.getStars() == 0) {
                                star1.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star2.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star3.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star4.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                star5.setIcon(new ImageIcon("src/gui/img/star-none.png"));
                                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                            }
                        }
                    }
                    );
                }

                panel.addMouseListener(
                        new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
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
                        e.getComponent().setBackground(new Color(253, 255, 255));

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        e.getComponent().setBackground(new Color(250, 250, 250));

                    }
                }
                );

                this.add(panel);
                panel.add(statusLabel);
                panel.add(star1);
                panel.add(star2);
                panel.add(star3);
                panel.add(star4);
                panel.add(star5);
                panel.add(restaurantNameLabel);
                panel.add(priceLabel);
                panel.setBackground(
                        new Color(252, 252, 252));
                actuallYPosition += PANEL_HEIGHT + 4;
                this.setPreferredSize(
                        new Dimension(500, actuallYPosition - 10));
            }
        }
    }

    protected JButton createZeroButton() {
        JButton button = new JButton("zero button");
        Dimension zeroDim = new Dimension(0, 0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }
}
