package gui;

import four_seasons.AdditionalMethods;
import four_seasons.DatabaseRequest;
import gui.MenuEntry;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ManagerPanel extends JPanel {

    private MainWindow window;
    private JPanel thisPanel, header, footer, managePanel;
    private JPanel editRestaurantPanel, addDishPanel, editDishPanel, showOrderDetailsPanel;
    private JPanel manageAdd, manageEdit, manageDelete, manageShowMenu, manageShowOrders;
    private YourOrdersList yourOrderPanel;
    private MenuList menuList;
    private JScrollPane menuPane;
    private JLabel logoImage, resNameLabel;
    private JButton userMenu, userLogout;
    private int selectedDishID;
    public static ArrayList<JPanel> dishPanels;
    public ArrayList<MenuEntry> dishList;

    public ManagerPanel(MainWindow window) {
        this.window = window;
        thisPanel = this;
        initUI();
    }

    private void initUI() {

        JLabel addLabel, addIcon, editLabel, editIcon, deleteLabel, deleteIcon,
                showMenuLabel, showMenuIcon, showOrdersLabel, showOrdersIcon;
        yourOrderPanel = new YourOrdersList();
        dishPanels = new ArrayList<>();
        manageAdd = new JPanel();
        manageEdit = new JPanel();
        manageDelete = new JPanel();
        manageShowMenu = new JPanel();
        manageShowOrders = new JPanel();
        addLabel = new JLabel("Add Dish");
        addIcon = new JLabel(new ImageIcon("src/gui/img/addRestaurant.png"));
        editLabel = new JLabel("Edit Restaurant");
        editIcon = new JLabel(new ImageIcon("src/gui/img/editRestaurant.png"));
        deleteLabel = new JLabel("Delete Dish");
        deleteIcon = new JLabel(new ImageIcon("src/gui/img/deleteRestaurant.png"));
        resNameLabel = new JLabel(DatabaseRequest.getRestaurantName(DatabaseRequest.getRestaurantID(window.getLogin())));
        showMenuLabel = new JLabel("Show Menu");
        showMenuIcon = new JLabel(new ImageIcon("src/gui/img/showMenu.png"));
        showOrdersLabel = new JLabel("Show Orders");
        showOrdersIcon = new JLabel(new ImageIcon("src/gui/img/showMenu.png"));
        header = new JPanel();
        footer = new JPanel();
        addDishPanel = new AddDishPanel();
        showOrderDetailsPanel = new ShowOrderDetailsPanel();
        editRestaurantPanel = new EditRestaurantPanel();
        managePanel = new JPanel();
        menuList = new MenuList(this);
        editDishPanel = new EditDishPanel();
        userMenu = new JButton(window.getLogin());
        userLogout = new JButton("Logout");
        menuPane = new JScrollPane(yourOrderPanel);
        logoImage = new JLabel(new ImageIcon("src/gui/img/logo-mini.png"));

        this.setLayout(null);
        this.setBackground(new Color(240, 240, 240));
        header.setLayout(null);
        header.setFocusable(true);

        resNameLabel.setBounds(100, 15, 120, 30);
        resNameLabel.setFont(new Font("Calibri Light", Font.PLAIN, 21));

        addIcon.setBounds(0, 0, 20, 29);
        addLabel.setBounds(23, 0, 200, 29);
        addLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        addLabel.setForeground(new Color(120, 120, 120));

        showOrderDetailsPanel.setVisible(false);

        editIcon.setBounds(0, 0, 20, 29);
        editLabel.setBounds(23, 0, 200, 29);
        editLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        editLabel.setForeground(new Color(120, 120, 120));

        deleteIcon.setBounds(0, 0, 20, 29);
        deleteLabel.setBounds(23, 0, 200, 29);
        deleteLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        deleteLabel.setForeground(new Color(120, 120, 120));

        showMenuIcon.setBounds(0, 0, 20, 29);
        showMenuLabel.setBounds(23, 0, 200, 29);
        showMenuLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        showMenuLabel.setForeground(new Color(120, 120, 120));

        showOrdersIcon.setBounds(0, 0, 20, 29);
        showOrdersLabel.setBounds(23, 0, 200, 29);
        showOrdersLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
        showOrdersLabel.setForeground(new Color(120, 120, 120));

        manageAdd.add(addIcon);
        manageAdd.add(addLabel);
        manageAdd.setLayout(null);
        manageAdd.setBounds(30, 0, 85, 29);

        manageEdit.add(editIcon);
        manageEdit.add(editLabel);
        manageEdit.setLayout(null);
        manageEdit.setBounds(130, 0, 120, 29);

        manageDelete.add(deleteIcon);
        manageDelete.add(deleteLabel);
        manageDelete.setLayout(null);
        manageDelete.setBounds(375, 0, 95, 29);

        manageShowMenu.add(showMenuIcon);
        manageShowMenu.add(showMenuLabel);
        manageShowMenu.setLayout(null);
        manageShowMenu.setBounds(265, 0, 95, 29);

        manageShowOrders.add(showOrdersIcon);
        manageShowOrders.add(showOrdersLabel);
        manageShowOrders.setLayout(null);
        manageShowOrders.setBounds(265, 0, 105, 29);
        manageShowOrders.setVisible(false);

        manageAdd.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addDishRequest();
                manageShowMenu.setVisible(false);
                manageShowOrders.setVisible(true);
                drawMenu();
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
                e.getComponent().setBackground(new Color(245, 245, 245));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                e.getComponent().setForeground(Color.BLACK);
                e.getComponent().setBackground(new Color(240, 240, 240));
            }
        });

        manageEdit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                editRestaurantRequest();
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
                e.getComponent().setBackground(new Color(245, 245, 245));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                e.getComponent().setForeground(Color.BLACK);
                e.getComponent().setBackground(new Color(240, 240, 240));
            }
        });

        manageDelete.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteDishRequest();
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
                e.getComponent().setBackground(new Color(245, 245, 245));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                e.getComponent().setForeground(Color.BLACK);
                e.getComponent().setBackground(new Color(240, 240, 240));
            }
        });

        manageShowMenu.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawMenu();
                manageShowMenu.setVisible(false);
                manageShowOrders.setVisible(true);
                editRestaurantRequest();
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
                e.getComponent().setBackground(new Color(245, 245, 245));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                e.getComponent().setForeground(Color.BLACK);
                e.getComponent().setBackground(new Color(240, 240, 240));
            }
        });

        manageShowOrders.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawOrders();
                manageShowMenu.setVisible(true);
                manageShowOrders.setVisible(false);
                manageDelete.setVisible(false);
                addDishPanel.setVisible(false);
                editDishPanel.setVisible(false);
                editRestaurantPanel.setVisible(true);
                menuList.dishNotSelected();
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
                e.getComponent().setBackground(new Color(245, 245, 245));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                e.getComponent().setForeground(Color.BLACK);
                e.getComponent().setBackground(new Color(240, 240, 240));
            }
        });

        manageEdit.setVisible(true);
        manageDelete.setVisible(false);

        header.setBounds(0, 0, 860, 50);
        header.setBackground(new Color(245, 245, 245));

        footer.setBounds(0, 551, 860, 20);
        footer.setBackground(new Color(250, 250, 250));

        logoImage.setBounds(5, 5, 100, 40);
        logoImage.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                drawOrders();
                addDishPanel.setVisible(false);
                editDishPanel.setVisible(false);
                editRestaurantPanel.setVisible(true);
                manageDelete.setVisible(false);
                manageShowOrders.setVisible(false);
                manageShowMenu.setVisible(true);
                menuList.dishNotSelected();
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

        userLogout.setBounds(730, 50, 100, 30);
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
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                userLogout.setBackground(new Color(245, 245, 245));
                userLogout.setVisible(false);
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        userMenu.addMouseListener(new MouseListener() {
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
                userMenu.setBackground(new Color(245, 245, 245));
                userLogout.setVisible(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                e.getComponent().setBackground(new Color(245, 245, 245));
                userLogout.setVisible(false);
            }
        });

        managePanel.setBounds(0, 51, 600, 29);
        managePanel.setLayout(null);
        managePanel.setBackground(new Color(240, 240, 240));

        menuPane.setBorder(null);
        menuPane.setBounds(0, 80, 600, 471);
        menuPane.setBorder(null);
        menuPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
        menuPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        editRestaurantPanel.setVisible(true);

        this.add(header);
        this.add(footer);
        this.add(menuPane);
        this.add(addDishPanel);
        this.add(showOrderDetailsPanel);
        this.add(editRestaurantPanel);
        this.add(editDishPanel);
        header.add(logoImage);
        header.add(resNameLabel);
        header.add(userMenu);
        this.add(userLogout);
        this.add(managePanel);
        managePanel.add(manageShowMenu);
        managePanel.add(manageAdd);
        managePanel.add(manageEdit);
        managePanel.add(manageDelete);
        managePanel.add(manageShowOrders);
    }

    public void dishSelected() {
        manageDelete.setVisible(true);
    }

    public void dishNotSelected() {
        manageDelete.setVisible(false);
        menuList.dishNotSelected();
    }

    public void drawOrders() {
        this.remove(menuPane);
        yourOrderPanel = new YourOrdersList();
        menuPane = new JScrollPane(yourOrderPanel);
        menuPane.setBorder(null);
        menuPane.setBounds(0, 80, 600, 471);
        menuPane.setBorder(null);
        menuPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
        menuPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        this.add(menuPane);
        thisPanel.revalidate();
        thisPanel.repaint();
    }

    public void drawMenu() {
        ArrayList<MenuEntry> menuEntry = new ArrayList<>();

        this.remove(menuPane);
        menuList = new MenuList(this);
        menuPane = new JScrollPane(menuList);
        menuPane.setBorder(null);
        menuPane.setBounds(0, 80, 600, 471);
        menuPane.setBorder(null);
        menuPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
        menuPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
        this.add(menuPane);
        menuList.createList(DatabaseRequest.getDishList(DatabaseRequest.getRestaurantID(window.getLogin())));
        menuList.drawList();
        thisPanel.revalidate();
        thisPanel.repaint();
    }

    private void showOrderDetailsRequest(int orderID) {
        editRestaurantPanel.setVisible(false);
        editDishPanel.setVisible(false);
        this.remove(showOrderDetailsPanel);
        showOrderDetailsPanel = new ShowOrderDetailsPanel(orderID);
        this.add(showOrderDetailsPanel);
        showOrderDetailsPanel.setVisible(true);
        thisPanel.revalidate();
        thisPanel.repaint();
    }

    private void addDishRequest() {
        editRestaurantPanel.setVisible(false);
        editDishPanel.setVisible(false);
        showOrderDetailsPanel.setVisible(false);
        this.remove(addDishPanel);
        addDishPanel = new AddDishPanel();
        this.add(addDishPanel);
        addDishPanel.setVisible(true);
        manageDelete.setVisible(false);
        menuList.dishNotSelected();
        thisPanel.revalidate();
        thisPanel.repaint();
    }

    public void editDishRequest() {
        editRestaurantPanel.setVisible(false);
        showOrderDetailsPanel.setVisible(false);
        addDishPanel.setVisible(false);
        this.remove(editDishPanel);
        editDishPanel = new EditDishPanel();
        this.add(editDishPanel);
        editDishPanel.setVisible(true);
        thisPanel.revalidate();
        thisPanel.repaint();
    }

    private void deleteDishRequest() {
        menuList.deleteSelected();
        manageDelete.setVisible(false);
        drawMenu();
    }

    public void editRestaurantRequest() {
        showOrderDetailsPanel.setVisible(false);
        editDishPanel.setVisible(false);
        addDishPanel.setVisible(false);
        this.remove(editRestaurantPanel);
        editRestaurantPanel = new EditRestaurantPanel();
        this.add(editRestaurantPanel);
        editRestaurantPanel.setVisible(true);

        dishNotSelected();
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
            createList(DatabaseRequest.getOrderList(DatabaseRequest.getRestaurantID(window.getLogin())));
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
            thisPanel.remove(menuPane);
            yourOrderPanel = new YourOrdersList();
            menuPane = new JScrollPane(yourOrderPanel);
            menuPane.setBorder(null);
            menuPane.setBounds(0, 51, 600, 500);
            menuPane.setBorder(null);
            menuPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
            menuPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
            this.add(menuPane);
        }

        private void changeStatus(int orderID, String status) {
            DatabaseRequest.setOrderStatus(orderID, status);
            drawOrders();
        }

        private void drawContent() {
            for (final YourOrder yourOrder : yourOrdersList) {
                final JPanel panel = new JPanel();
                JLabel restaurantNameLabel = new JLabel(yourOrder.getRestaurantName());
                JLabel priceLabel = new JLabel(Float.toString(yourOrder.getPrice()) + " $");
                JLabel statusLabel = new JLabel();
                JButton acceptButton, denyButton;
                final JLabel star1, star2, star3, star4, star5;

                panel.setLayout(null);
                panel.setBounds(0, actuallYPosition, PANEL_WIDTH, PANEL_HEIGHT);
                panel.setFocusable(true);

                restaurantNameLabel.setBounds(20, 18, 200, 30);
                restaurantNameLabel.setFont(new Font("Calibri Light", Font.PLAIN, 20));

                priceLabel.setBounds(120, 18, 55, 30);
                priceLabel.setFont(new Font("Calibri", Font.PLAIN, 18));

                acceptButton = new JButton("Accept");
                denyButton = new JButton("Deny");

                acceptButton.setBounds(415, 17, 70, 30);
                acceptButton.setFont(new Font("Calibri", Font.PLAIN, 15));
                acceptButton.setBackground(new Color(40, 220, 60));
                acceptButton.setForeground(Color.WHITE);
                acceptButton.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
                acceptButton.setVisible(false);

                denyButton.setBounds(505, 17, 70, 30);
                denyButton.setFont(new Font("Calibri", Font.PLAIN, 15));
                denyButton.setBackground(new Color(220, 40, 60));
                denyButton.setForeground(Color.WHITE);
                denyButton.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
                denyButton.setVisible(false);

                if (yourOrder.getStatus().equals("WAIT_TO_ACCEPT")) {
                    acceptButton.setVisible(true);
                    denyButton.setVisible(true);
                }

                acceptButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        changeStatus(yourOrder.getID(), "ACCEPTED");
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
                        e.getComponent().setBackground(new Color(60, 230, 80));
                        panel.setBackground(new Color(253, 255, 255));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        e.getComponent().setBackground(new Color(40, 220, 60));
                        panel.setBackground(new Color(250, 250, 250));
                    }
                });

                denyButton.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        changeStatus(yourOrder.getID(), "DENIED");
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
                        e.getComponent().setBackground(new Color(230, 60, 80));
                        panel.setBackground(new Color(253, 255, 255));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        e.getComponent().setBackground(new Color(220, 40, 60));
                        panel.setBackground(new Color(250, 250, 250));
                    }
                });

                statusLabel.setBounds(410, 18, 200, 30);
                statusLabel.setFont(new Font("Calibri Light", Font.PLAIN, 18));

                switch (yourOrder.getStatus()) {
                    case "ACCEPTED":
                        statusLabel.setBounds(430, 18, 200, 30);
                        statusLabel.setForeground(new Color(40, 220, 60));
                        statusLabel.setText("Order Accepted");
                        break;
                    case "DENIED":
                        statusLabel.setBounds(437, 18, 200, 30);
                        statusLabel.setForeground(new Color(220, 40, 60));
                        statusLabel.setText("Order Denied");
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

                panel.addMouseListener(
                        new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        showOrderDetailsRequest(yourOrder.getID());
                    }

                    @Override
                    public void mousePressed(MouseEvent e
                    ) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e
                    ) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e
                    ) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        e.getComponent().setBackground(new Color(253, 255, 255));

                    }

                    @Override
                    public void mouseExited(MouseEvent e
                    ) {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        e.getComponent().setBackground(new Color(250, 250, 250));

                    }
                }
                );

                this.add(panel);
                panel.add(statusLabel);
                panel.add(acceptButton);
                panel.add(denyButton);
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

    class ShowOrderDetailsPanel extends JPanel {

        private final Color bgColor = new Color(252, 252, 252);
        private JLabel infoLabel;
        private int orderID;

        public ShowOrderDetailsPanel() {
            this.setLayout(null);
            this.setFocusable(true);
            this.setBounds(601, 80, 860 - 601, 530);
            this.setBackground(bgColor);
        }

        public ShowOrderDetailsPanel(int orderID) {
            this.setLayout(null);
            this.setFocusable(true);
            this.setBounds(601, 80, 860 - 601, 530);
            this.setBackground(bgColor);
            this.orderID = orderID;

            initUI();
        }

        private void initUI() {
            ArrayList<OrderDetails> orderDetailsList = DatabaseRequest.getOrderDetails(orderID);

            JPanel separatePanel = new JPanel();
            separatePanel.setBounds(0, 0, 256, 65);
            separatePanel.setBackground(new Color(130, 237, 125));
            separatePanel.setLayout(null);

            JPanel orderList = new JPanel();
            orderList.setBounds(0, 120, 256, 200);
            orderList.setBackground(new Color(253, 253, 253));
            orderList.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
            orderList.setLayout(null);

            JLabel tableInfo = new JLabel("  Dish Name                           Price       Amount");
            tableInfo.setBounds(0, 90, 256, 30);
            tableInfo.setBackground(new Color(253, 253, 253));
            tableInfo.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
            tableInfo.setOpaque(true);

            infoLabel = new JLabel("Order Details", SwingConstants.CENTER);
            infoLabel.setBounds(0, 0, 256, 65);
            infoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 22));
            infoLabel.setForeground(Color.WHITE);

            int actuallYPosition = 0;
            float orderPrice = 0;
            for (OrderDetails orderDetails : orderDetailsList) {
                JLabel dishName = new JLabel(orderDetails.getDishName());
                JLabel dishPrice = new JLabel(Float.toString(orderDetails.getDishPrice()) + " $");
                JLabel dishAmount = new JLabel(Integer.toString(orderDetails.getAmount()));

                dishName.setBounds(10, actuallYPosition, 200, 30);
                dishName.setFont(new Font("Calibri", Font.PLAIN, 14));

                dishPrice.setBounds(150, actuallYPosition, 100, 30);
                dishPrice.setFont(new Font("Calibri", Font.PLAIN, 15));

                dishAmount.setBounds(230, actuallYPosition, 100, 30);
                dishAmount.setFont(new Font("Calibri", Font.PLAIN, 15));

                orderList.add(dishName);
                orderList.add(dishPrice);
                orderList.add(dishAmount);

                actuallYPosition += 30;
                orderPrice += orderDetails.getDishPrice() * orderDetails.getAmount();
            }
            
            JLabel orderPriceLabel = new JLabel(Float.toString(AdditionalMethods.round(orderPrice, 2)) + " $");
            orderPriceLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
            orderPriceLabel.setBounds(160, 320, 100, 30);
            orderPriceLabel.setBackground(new Color(253, 253, 253));
            orderPriceLabel.setOpaque(true);
            
            JLabel orderPriceInfoLabel = new JLabel("Order Price:");
            orderPriceInfoLabel.setFont(new Font("Calibri", Font.PLAIN, 17));
            orderPriceInfoLabel.setBounds(10, 320, 140, 30);
            orderPriceInfoLabel.setBackground(new Color(253, 253, 253));
            orderPriceInfoLabel.setOpaque(true);

            this.add(tableInfo);
            this.add(orderList);
            this.add(orderPriceLabel);
            this.add(orderPriceInfoLabel);
            this.add(separatePanel);
            separatePanel.add(infoLabel);
        }
    }

    class EditDishPanel extends JPanel {

        private final Color bgColor = new Color(252, 252, 252);
        private JTextField nameField, priceField;
        private JTextArea descriptionArea;
        private JComboBox typeCB;
        private JCheckBox vegetarianBox, spicyBox, fishBox, garlicBox;
        private JLabel nameLabel, priceLabel, descriptionLabel, typeLabel,
                infoLabel;
        private JButton editButton;

        private String[] typeArray = {"Pizza", "Starter", "Soup", "Main Course", "Pasta", "Dessert", "Other"};

        public EditDishPanel() {
            this.setLayout(null);
            this.setFocusable(true);
            this.setBounds(601, 80, 860 - 601, 530);
            this.setBackground(bgColor);
            this.setVisible(false);

            if (menuList.getSelectedDishID() != 0) {
                initUI();
            }
        }

        private void editDish(String name, String description, String price,
                String type, boolean vegetarian, boolean spicy, boolean fish,
                boolean garlic) {

            boolean noError = true;
            type = type.replaceAll(" ", "_").toUpperCase();

            if (name.isEmpty() || name.length() > 20) {
                nameField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                nameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (price.isEmpty() || !AdditionalMethods.isFloat(price) || price.length() > 5) {
                priceField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                priceField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (description.length() > 80) {
                descriptionArea.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                descriptionArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (noError) {
                DatabaseRequest.editDish(menuList.getSelectedDishID(), name, description, Float.parseFloat(price), type,
                        vegetarian, spicy, fish, garlic);
                drawMenu();
                editRestaurantRequest();
            }

        }

        private void initUI() {
            ArrayList<String> dishData = DatabaseRequest.
                    getDishData(menuList.getSelectedDishID());

            JPanel separatePanel = new JPanel();
            infoLabel = new JLabel("Edit Dish", SwingConstants.CENTER);

            separatePanel.setBounds(0, 0, 256, 65);
            separatePanel.setBackground(new Color(130, 237, 125));
            separatePanel.setLayout(null);

            infoLabel.setBounds(0, 0, 256, 65);
            infoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 22));
            infoLabel.setForeground(Color.WHITE);

            nameField = new JTextField();
            priceField = new JTextField();
            descriptionArea = new JTextArea(4, 30);
            typeCB = new JComboBox(typeArray);
            vegetarianBox = new JCheckBox("Vegetarian");
            spicyBox = new JCheckBox("Spicy");
            fishBox = new JCheckBox("Fish");
            garlicBox = new JCheckBox("Garlic");
            editButton = new JButton("Edit Dish");
            nameLabel = new JLabel("Dish Name");
            priceLabel = new JLabel("Dish Price");
            descriptionLabel = new JLabel("Description");
            typeLabel = new JLabel("Dish Type");

            nameField.setText(dishData.get(0));
            descriptionArea.setText(dishData.get(1));
            priceField.setText(dishData.get(3));
            typeCB.setSelectedItem(dishData.get(2));

            if ("1".equals(dishData.get(4))) {
                vegetarianBox.setSelected(true);

            } else {
                vegetarianBox.setSelected(false);
            }

            if ("1".equals(dishData.get(5))) {
                spicyBox.setSelected(true);

            } else {
                spicyBox.setSelected(false);
            }

            if ("1".equals(dishData.get(6))) {
                fishBox.setSelected(true);

            } else {
                fishBox.setSelected(false);
            }

            if ("1".equals(dishData.get(7))) {
                garlicBox.setSelected(true);

            } else {
                garlicBox.setSelected(false);
            }

            nameLabel.setBounds(25, 90, 100, 30);
            nameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            nameField.setBounds(100, 90, 120, 30);
            nameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            nameField.setFont(new Font("Calibri", Font.PLAIN, 14));

            descriptionLabel.setBounds(25, 130, 100, 30);
            descriptionLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            descriptionArea.setBounds(100, 130, 120, 60);
            descriptionArea.setFont(new Font("Calibri", Font.PLAIN, 14));
            descriptionArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            descriptionArea.setLineWrap(true);
            descriptionArea.setWrapStyleWord(true);

            typeLabel.setBounds(25, 200, 100, 30);
            typeLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            typeCB.setBounds(100, 200, 120, 30);
            typeCB.setFont(new Font("Calibri", Font.PLAIN, 14));
            typeCB.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            typeCB.setBackground(new Color(255, 255, 255));

            priceLabel.setBounds(25, 240, 100, 30);
            priceLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            priceField.setBounds(100, 240, 120, 30);
            priceField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            priceField.setFont(new Font("Calibri", Font.PLAIN, 14));

            vegetarianBox.setBounds(100, 285, 100, 30);
            vegetarianBox.setFont(new Font("Calibri", Font.PLAIN, 14));
            vegetarianBox.setBackground(bgColor);

            fishBox.setBounds(100, 315, 100, 30);
            fishBox.setFont(new Font("Calibri", Font.PLAIN, 14));
            fishBox.setBackground(bgColor);

            spicyBox.setBounds(100, 345, 100, 30);
            spicyBox.setFont(new Font("Calibri", Font.PLAIN, 14));
            spicyBox.setBackground(bgColor);

            garlicBox.setBounds(100, 375, 100, 30);
            garlicBox.setFont(new Font("Calibri", Font.PLAIN, 14));
            garlicBox.setBackground(bgColor);

            editButton.setBounds(75, 420, 100, 30);
            editButton.setFont(new Font("Calibri", Font.PLAIN, 14));
            editButton.setBackground(new Color(245, 245, 245));
            editButton.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

            editButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    editDish(nameField.getText(), descriptionArea.getText(),
                            priceField.getText(), typeCB.getSelectedItem().toString(),
                            vegetarianBox.isSelected(), spicyBox.isSelected(),
                            fishBox.isSelected(), garlicBox.isSelected());

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

            this.add(separatePanel);
            this.add(editButton);
            separatePanel.add(infoLabel);
            this.add(nameLabel);
            this.add(nameField);
            this.add(priceLabel);
            this.add(priceField);
            this.add(descriptionLabel);
            this.add(descriptionArea);
            this.add(typeLabel);
            this.add(typeCB);
            this.add(vegetarianBox);
            this.add(fishBox);
            this.add(garlicBox);
            this.add(spicyBox);

        }
    }

    class EditRestaurantPanel extends JPanel {

        private JTextField nameField, cuisineField, cityField, streetField,
                houseNumberField, flatNumberField, deliveryCostField, minimumCostField;
        private JLabel nameLabel, cuisineLabel, cityLabel, streetLabel,
                houseNumberLabel, flatNumberLabel, deliveryCostLabel, minimumCostLabel, infoLabel;
        private JButton editData;
        private final Color bgColor = new Color(252, 252, 252);

        public EditRestaurantPanel() {
            this.setLayout(null);
            this.setFocusable(true);
            this.setBounds(601, 80, 860 - 601, 530);
            this.setBackground(bgColor);
            this.setVisible(false);

            initUI();
        }

        private void editRestaurant(String name, String cuisine, String city,
                String street, String houseNumber, String flatNumber, String deliveryCost,
                String minimumCost) {
            boolean noError = true;

            if (name.isEmpty() || name.length() > 30) {
                nameField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                nameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (cuisine.isEmpty() || cuisine.length() > 20) {
                cuisineField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                cuisineField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (city.isEmpty() || city.length() > 30) {
                cityField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                cityField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (street.isEmpty() || street.length() > 30) {
                streetField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                streetField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (houseNumber.isEmpty() || houseNumber.length() > 6) {
                houseNumberField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                houseNumberField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if ((!AdditionalMethods.isInteger(flatNumber) && !flatNumber.isEmpty()) || flatNumber.length() > 5) {
                flatNumberField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if ((!AdditionalMethods.isInteger(deliveryCost) && !deliveryCost.isEmpty()) || deliveryCost.length() > 5) {
                deliveryCostField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
                if (deliveryCost.isEmpty()) {
                    noError = true;
                    deliveryCost = "0";
                    flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
                }
            } else {
                deliveryCostField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if ((!AdditionalMethods.isInteger(minimumCost) && !minimumCost.isEmpty()) || minimumCost.length() > 4) {
                minimumCostField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
                if (minimumCost.isEmpty()) {
                    noError = true;
                    minimumCost = "0";
                    flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
                }
            } else {
                minimumCostField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (noError) {
                int editRestaurant = DatabaseRequest.editRestaurant(name, cuisine,
                        city, street, houseNumber, flatNumber,
                        Integer.parseInt(deliveryCost), Integer.parseInt(minimumCost),
                        DatabaseRequest.getRestaurantID(window.getLogin()));
            }

            this.remove(editRestaurantPanel);
            editRestaurantPanel = new EditRestaurantPanel();
            this.add(editRestaurantPanel);
            thisPanel.revalidate();
            thisPanel.repaint();
        }

        private void initUI() {
            JPanel separatePanel = new JPanel();
            infoLabel = new JLabel("Edit Restaurant", SwingConstants.CENTER);
            ArrayList<String> restaurantData = DatabaseRequest.
                    getRestaurantData(DatabaseRequest.getRestaurantID(window.getLogin()));

            nameField = new JTextField(restaurantData.get(0));
            cuisineField = new JTextField(restaurantData.get(1));
            cityField = new JTextField(restaurantData.get(2));
            streetField = new JTextField(restaurantData.get(3));
            houseNumberField = new JTextField(restaurantData.get(4));
            flatNumberField = new JTextField(restaurantData.get(5));
            deliveryCostField = new JTextField(restaurantData.get(6));
            minimumCostField = new JTextField(restaurantData.get(7));
            editData = new JButton("Save Changes");

            nameLabel = new JLabel("Name");
            cuisineLabel = new JLabel("Cuisine");
            cityLabel = new JLabel("City");
            streetLabel = new JLabel("Street");
            houseNumberLabel = new JLabel("House Number");
            flatNumberLabel = new JLabel("Flat Number");
            deliveryCostLabel = new JLabel("Delivery Cost");
            minimumCostLabel = new JLabel("Minimum Cost");

            separatePanel.setBounds(0, 0, 256, 65);
            separatePanel.setBackground(new Color(125, 209, 237));
            separatePanel.setLayout(null);

            infoLabel.setBounds(0, 0, 256, 65);
            infoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 22));
            infoLabel.setForeground(Color.WHITE);

            nameLabel.setBounds(25, 90, 100, 30);
            nameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            nameField.setBounds(120, 90, 120, 30);
            nameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            nameField.setFont(new Font("Calibri", Font.PLAIN, 14));

            cuisineLabel.setBounds(25, 130, 100, 30);
            cuisineLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            cuisineField.setBounds(120, 130, 120, 30);
            cuisineField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            cuisineField.setFont(new Font("Calibri", Font.PLAIN, 14));

            cityLabel.setBounds(25, 170, 100, 30);
            cityLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            cityField.setBounds(120, 170, 120, 30);
            cityField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            cityField.setFont(new Font("Calibri", Font.PLAIN, 14));

            streetLabel.setBounds(25, 210, 100, 30);
            streetLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            streetField.setBounds(120, 210, 120, 30);
            streetField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            streetField.setFont(new Font("Calibri", Font.PLAIN, 14));

            houseNumberLabel.setBounds(25, 250, 100, 30);
            houseNumberLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            houseNumberField.setBounds(120, 250, 120, 30);
            houseNumberField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            houseNumberField.setFont(new Font("Calibri", Font.PLAIN, 14));

            flatNumberLabel.setBounds(25, 290, 100, 30);
            flatNumberLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            flatNumberField.setBounds(120, 290, 120, 30);
            flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            flatNumberField.setFont(new Font("Calibri", Font.PLAIN, 14));

            deliveryCostLabel.setBounds(25, 330, 100, 30);
            deliveryCostLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            deliveryCostField.setBounds(120, 330, 120, 30);
            deliveryCostField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            deliveryCostField.setFont(new Font("Calibri", Font.PLAIN, 14));

            minimumCostLabel.setBounds(25, 370, 100, 30);
            minimumCostLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            minimumCostField.setBounds(120, 370, 120, 30);
            minimumCostField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            minimumCostField.setFont(new Font("Calibri", Font.PLAIN, 14));

            editData.setBounds(75, 420, 100, 30);
            editData.setFont(new Font("Calibri", Font.PLAIN, 14));
            editData.setBackground(new Color(245, 245, 245));
            editData.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

            editData.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    editRestaurant(nameField.getText(), cuisineField.getText(),
                            cityField.getText(), streetField.getText(),
                            houseNumberField.getText(), flatNumberField.getText(),
                            deliveryCostField.getText(), minimumCostField.getText());
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

            this.add(separatePanel);
            this.add(nameField);
            this.add(nameLabel);
            this.add(cityField);
            this.add(cityLabel);
            this.add(streetField);
            this.add(streetLabel);
            this.add(cuisineField);
            this.add(cuisineLabel);
            this.add(houseNumberField);
            this.add(houseNumberLabel);
            this.add(flatNumberField);
            this.add(flatNumberLabel);
            this.add(deliveryCostField);
            this.add(deliveryCostLabel);
            this.add(minimumCostField);
            this.add(minimumCostLabel);
            this.add(editData);
            separatePanel.add(infoLabel);

        }
    }

    class AddDishPanel extends JPanel {

        private JTextField nameField, priceField;
        private JTextArea descriptionArea;
        private JComboBox typeCB;
        private JCheckBox vegetarianBox, spicyBox, fishBox, garlicBox;
        private JLabel nameLabel, priceLabel, descriptionLabel, typeLabel,
                infoLabel;
        private JButton addButton;
        private Color bgColor = new Color(254, 254, 254);

        private String[] typeArray = {"Pizza", "Starter", "Soup", "Main Course", "Pasta", "Dessert", "Other"};

        public AddDishPanel() {
            this.setLayout(null);
            this.setFocusable(true);
            this.setBounds(601, 80, 860 - 601, 530);
            this.setBackground(bgColor);
            this.setVisible(false);

            initUI();
        }

        public void addDish(String name, String description, String price,
                String type, boolean vegetarian, boolean spicy, boolean fish,
                boolean garlic) {
            boolean noError = true;
            type = type.replaceAll(" ", "_").toUpperCase();

            if (name.isEmpty() || name.length() > 20) {
                nameField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                nameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (price.isEmpty() || !AdditionalMethods.isFloat(price) || price.length() > 5) {
                priceField.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                priceField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (description.length() > 80) {
                descriptionArea.setBorder(BorderFactory.createLineBorder(Color.RED, 1, true));
                noError = false;
            } else {
                descriptionArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            }

            if (noError) {
                DatabaseRequest.addNewDish(name, description, Float.parseFloat(price),
                        type, vegetarian, spicy, fish, garlic, window.getLogin());
                drawMenu();
                editRestaurantRequest();
            }

        }

        private void initUI() {
            JPanel separatePanel = new JPanel();
            nameField = new JTextField();
            priceField = new JTextField();
            descriptionArea = new JTextArea(4, 30);
            typeCB = new JComboBox(typeArray);
            vegetarianBox = new JCheckBox("Vegetarian");
            spicyBox = new JCheckBox("Spicy");
            fishBox = new JCheckBox("Fish");
            garlicBox = new JCheckBox("Garlic");
            addButton = new JButton("Add Dish");

            infoLabel = new JLabel("Add Dish", SwingConstants.CENTER);
            nameLabel = new JLabel("Dish Name");
            priceLabel = new JLabel("Dish Price");
            descriptionLabel = new JLabel("Description");
            typeLabel = new JLabel("Dish Type");

            nameLabel.setBounds(25, 90, 100, 30);
            nameLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            nameField.setBounds(100, 90, 120, 30);
            nameField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            nameField.setFont(new Font("Calibri", Font.PLAIN, 14));

            separatePanel.setBounds(0, 0, 255, 65);
            separatePanel.setBackground(new Color(237, 220, 125));
            separatePanel.setLayout(null);

            descriptionLabel.setBounds(25, 130, 100, 30);
            descriptionLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            descriptionArea.setBounds(100, 130, 120, 60);
            descriptionArea.setFont(new Font("Calibri", Font.PLAIN, 14));
            descriptionArea.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            descriptionArea.setLineWrap(true);
            descriptionArea.setWrapStyleWord(true);

            typeLabel.setBounds(25, 200, 100, 30);
            typeLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            typeCB.setBounds(100, 200, 120, 30);
            typeCB.setFont(new Font("Calibri", Font.PLAIN, 14));
            typeCB.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            typeCB.setBackground(new Color(255, 255, 255));

            priceLabel.setBounds(25, 240, 100, 30);
            priceLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            priceField.setBounds(100, 240, 120, 30);
            priceField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true));
            priceField.setFont(new Font("Calibri", Font.PLAIN, 14));

            vegetarianBox.setBounds(100, 285, 100, 30);
            vegetarianBox.setFont(new Font("Calibri", Font.PLAIN, 14));
            vegetarianBox.setBackground(bgColor);

            fishBox.setBounds(100, 315, 100, 30);
            fishBox.setFont(new Font("Calibri", Font.PLAIN, 14));
            fishBox.setBackground(bgColor);

            spicyBox.setBounds(100, 345, 100, 30);
            spicyBox.setFont(new Font("Calibri", Font.PLAIN, 14));
            spicyBox.setBackground(bgColor);

            garlicBox.setBounds(100, 375, 100, 30);
            garlicBox.setFont(new Font("Calibri", Font.PLAIN, 14));
            garlicBox.setBackground(bgColor);

            infoLabel.setBounds(0, 0, 256, 65);
            infoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 22));
            infoLabel.setForeground(Color.WHITE);

            addButton.setBounds(75, 420, 100, 30);
            addButton.setFont(new Font("Calibri", Font.PLAIN, 14));
            addButton.setBackground(new Color(245, 245, 245));
            addButton.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

            addButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    addDish(nameField.getText(), descriptionArea.getText(),
                            priceField.getText(), typeCB.getSelectedItem().toString(),
                            vegetarianBox.isSelected(), spicyBox.isSelected(),
                            fishBox.isSelected(), garlicBox.isSelected());
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

            this.add(separatePanel);
            this.add(addButton);
            separatePanel.add(infoLabel);
            this.add(nameLabel);
            this.add(nameField);
            this.add(priceLabel);
            this.add(priceField);
            this.add(descriptionLabel);
            this.add(descriptionArea);
            this.add(typeLabel);
            this.add(typeCB);
            this.add(vegetarianBox);
            this.add(fishBox);
            this.add(garlicBox);
            this.add(spicyBox);
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
