package gui;

import four_seasons.AdditionalMethods;
import four_seasons.DatabaseRequest;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class RestaurantMenuPanel extends JPanel {

    private MainWindow window;
    private JPanel thisPanel, header, footer;
    private MenuList menuList;
    private JScrollPane menuPane;
    private JLabel logoImage, resNameLabel;
    private JButton userMenu, userLogout;
    private OrderPanel orderPanel;
    private int resID;
    private int minimumCost, deliveryCost;

    public RestaurantMenuPanel(MainWindow window, int resID, int minimumCost, int deliveryCost) {
        this.window = window;
        thisPanel = this;
        this.resID = resID;
        this.minimumCost = minimumCost;
        this.deliveryCost = deliveryCost;
        initUI();
    }

    private void initUI() {
        header = new JPanel();
        footer = new JPanel();
        userMenu = new JButton(window.getLogin());
        userLogout = new JButton("Logout");
        orderPanel = new OrderPanel();
        menuList = new MenuList(orderPanel);
        menuPane = new JScrollPane(menuList);
        logoImage = new JLabel(new ImageIcon("src/gui/img/logo-mini.png"));
        resNameLabel = new JLabel(DatabaseRequest.getRestaurantName(resID));

        this.setLayout(null);
        this.setBackground(new Color(220, 220, 220));
        header.setLayout(null);
        header.setFocusable(true);

        header.setBounds(0, 0, 860, 50);
        header.setBackground(new Color(245, 245, 245));

        footer.setBounds(0, 551, 860, 20);
        footer.setBackground(new Color(250, 250, 250));
        
        resNameLabel.setBounds(100,15,120,30);
        resNameLabel.setFont(new Font("Calibri Light", Font.PLAIN, 21));

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

        menuPane.setBounds(0, 51, 600, 500);
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
        menuList.createList(DatabaseRequest.getDishList(resID));
        menuList.drawList();
        thisPanel.revalidate();
        thisPanel.repaint();

        this.add(header);
        this.add(footer);
        this.add(menuPane);
        header.add(resNameLabel);
        header.add(logoImage);
        header.add(userMenu);
        this.add(userLogout);
        this.add(orderPanel);
    }

    protected JButton createZeroButton() {
        JButton button = new JButton("zero button");
        Dimension zeroDim = new Dimension(0, 0);
        button.setPreferredSize(zeroDim);
        button.setMinimumSize(zeroDim);
        button.setMaximumSize(zeroDim);
        return button;
    }

    class OrderPanel extends JPanel {

        private JLabel infoLabel, priceLabel, sumPriceLabel, minPriceInfoLabel, deliveryCostInfoLabel,
                minimumPriceLabel, deliveryCostLabel;
        private JScrollPane orderPane;
        private JButton orderButton;
        private OrderList orderList;
        private ArrayList<OrderEntry> orderedDishList;
        private float orderPrice;

        public OrderPanel() {
            this.setLayout(null);
            this.setBounds(600, 51, 860 - 600, 500);
            this.setBackground(new Color(245, 245, 245));
            this.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
            orderedDishList = new ArrayList<>();
            orderPrice = 0;

            initUI();
        }

        private void orderFood() {
            if (orderPrice > 0 && !orderedDishList.isEmpty()) {
                DatabaseRequest.orderFood(resID, window.getLogin(), orderedDishList, deliveryCost);
                JPanel clientPanel = new ClientPanel(window);
                window.add(clientPanel);
                clientPanel.setVisible(true);
                thisPanel.setVisible(false);
                window.remove(thisPanel);
            }
        }

        private void deleteDish(OrderEntry orderEntry) {
            orderedDishList.remove(orderEntry);
            drawOrderList();
            this.orderPrice -= orderEntry.getPrice() * orderEntry.getAmount();
            this.orderPrice = AdditionalMethods.round(orderPrice, 2);
            this.remove(sumPriceLabel);
            sumPriceLabel = new JLabel(Float.toString(orderPrice) + " $");
            sumPriceLabel.setBounds(140, 390, 260 - 140, 25);
            sumPriceLabel.setFont(new Font("Calibri Light", Font.BOLD, 18));
            sumPriceLabel.setOpaque(true);
            sumPriceLabel.setBackground(new Color(254, 254, 254));
            this.add(sumPriceLabel);
            checkOrderReady();
        }

        private void refreshOrderPanel() {
            drawOrderList();
            orderPrice = 0;
            for (OrderEntry orderEntry : orderedDishList) {
                orderPrice += orderEntry.getPrice() * orderEntry.getAmount();
            }
            this.remove(sumPriceLabel);
            sumPriceLabel = new JLabel(Float.toString(orderPrice) + " $");
            sumPriceLabel.setBounds(140, 390, 260 - 140, 25);
            sumPriceLabel.setFont(new Font("Calibri Light", Font.BOLD, 18));
            sumPriceLabel.setOpaque(true);
            sumPriceLabel.setBackground(new Color(254, 254, 254));
            this.add(sumPriceLabel);
            checkOrderReady();
        }

        private void checkOrderReady() {
            if (orderPrice < minimumCost) {
                orderButton.setBackground(new Color(240, 234, 234));
                orderButton.setText("Minimum Cost");
                orderButton.setEnabled(false);
            } else {
                orderButton.setBackground(new Color(234, 234, 234));
                orderButton.setText("Order Food");
                orderButton.setEnabled(true);
            }
        }

        private void addDishToOrder(String name, String price, int amount) {
            boolean changed = false;
            for (OrderEntry orderEntry : orderedDishList) {
                if (orderEntry.getName().equals(name)) {
                    orderEntry.setAmount(orderEntry.getAmount() + 1);
                    changed = true;
                    break;
                }
            }
            if (!changed) {
                this.orderedDishList.add(new OrderEntry(name, Float.parseFloat(price), amount));
            }
            drawOrderList();
            this.orderPrice += Float.parseFloat(price);
            this.orderPrice = AdditionalMethods.round(orderPrice, 2);
            this.remove(sumPriceLabel);
            sumPriceLabel = new JLabel(Float.toString(orderPrice) + " $");
            sumPriceLabel.setBounds(140, 390, 260 - 140, 25);
            sumPriceLabel.setFont(new Font("Calibri Light", Font.BOLD, 18));
            sumPriceLabel.setOpaque(true);
            sumPriceLabel.setBackground(new Color(254, 254, 254));
            this.add(sumPriceLabel);
            checkOrderReady();
        }

        private void drawOrderList() {
            this.remove(orderPane);
            orderList = new OrderList();
            orderList.createList(this.orderedDishList);
            orderList.drawList();
            orderPane = new JScrollPane(orderList);
            orderPane.setBounds(0, 115, 260, 225);
            orderPane.setBackground(new Color(250, 250, 250));
            orderPane.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
            orderPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
            orderPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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
            this.add(orderPane);
            this.revalidate();
            this.repaint();
        }

        private void initUI() {
            JPanel header = new JPanel();
            JLabel orderInfoLabel = new JLabel("   Name                      Price");
            header.setBounds(0, 0, 260, 65);
            header.setBackground(new Color(130, 237, 125));
            header.setLayout(null);

            orderList = new OrderList();
            orderList.createList(orderedDishList);
            orderList.drawList();

            orderInfoLabel.setBounds(0, 90, 260, 25);
            orderInfoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 17));
            orderInfoLabel.setOpaque(true);
            orderInfoLabel.setBackground(new Color(253, 253, 253));

            orderPane = new JScrollPane(orderList);
            orderPane.setBackground(new Color(250, 250, 250));
            orderPane.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
            orderPane.setBounds(0, 115, 260, 225);

            infoLabel = new JLabel("Your Order");
            infoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 22));
            infoLabel.setForeground(Color.WHITE);
            infoLabel.setBounds(80, 17, 200, 35);

            priceLabel = new JLabel("  Order Price:");
            priceLabel.setBounds(0, 390, 140, 25);
            priceLabel.setFont(new Font("Calibri Light", Font.PLAIN, 18));
            priceLabel.setOpaque(true);
            priceLabel.setBackground(new Color(253, 253, 253));

            deliveryCostInfoLabel = new JLabel("  Delivery Cost:");
            deliveryCostInfoLabel.setBounds(0, 365, 140, 25);
            deliveryCostInfoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 18));
            deliveryCostInfoLabel.setOpaque(true);
            deliveryCostInfoLabel.setBackground(new Color(253, 253, 253));

            minPriceInfoLabel = new JLabel("  Minimum Cost:");
            minPriceInfoLabel.setBounds(0, 340, 140, 25);
            minPriceInfoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 18));
            minPriceInfoLabel.setOpaque(true);
            minPriceInfoLabel.setBackground(new Color(253, 253, 253));

            deliveryCostLabel = new JLabel(Integer.toString(deliveryCost) + " $");
            deliveryCostLabel.setBounds(140, 365, 140, 25);
            deliveryCostLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
            deliveryCostLabel.setOpaque(true);
            deliveryCostLabel.setBackground(new Color(253, 253, 253));

            minimumPriceLabel = new JLabel(Integer.toString(minimumCost) + " $");
            minimumPriceLabel.setBounds(140, 340, 140, 25);
            minimumPriceLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
            minimumPriceLabel.setOpaque(true);
            minimumPriceLabel.setBackground(new Color(253, 253, 253));

            sumPriceLabel = new JLabel(Float.toString(orderPrice) + " $");
            sumPriceLabel.setBounds(140, 390, 260 - 140, 25);
            sumPriceLabel.setFont(new Font("Calibri Light", Font.BOLD, 18));
            sumPriceLabel.setOpaque(true);
            sumPriceLabel.setBackground(new Color(254, 254, 254));

            orderButton = new JButton("Order Food");
            orderButton.setBounds(80, 445, 100, 30);
            orderButton.setBorder(null);
            orderButton.setBackground(new Color(234, 234, 234));
            orderButton.setFont(new Font("Calibri Light", Font.PLAIN, 14));
            orderButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    orderFood();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    if (orderButton.isEnabled()) {
                        setCursor(new Cursor(Cursor.HAND_CURSOR));
                        e.getComponent().setBackground(new Color(236, 237, 236));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (orderButton.isEnabled()) {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        e.getComponent().setBackground(new Color(234, 234, 234));
                    }
                }
            });
            checkOrderReady();

            header.add(infoLabel);
            this.add(deliveryCostInfoLabel);
            this.add(minimumPriceLabel);
            this.add(minPriceInfoLabel);
            this.add(deliveryCostLabel);
            this.add(sumPriceLabel);
            this.add(header);
            this.add(orderPane);
            this.add(orderButton);
            this.add(priceLabel);
            this.add(orderInfoLabel);
        }

        class OrderList extends JPanel {

            private ArrayList<OrderEntry> orderList;

            public OrderList() {
                orderList = new ArrayList<>();
                this.setLayout(null);
                this.setBackground(new Color(250, 250, 250));
            }

            public void drawList() {
                JLabel name, price, deleteIcon;
                JTextField amountField;
                int actuallYPosition = 0;

                for (final OrderEntry orderEntry : orderList) {
                    JPanel panel = new JPanel();
                    panel.setBounds(0, actuallYPosition, 259, 30);
                    panel.setBackground(new Color(251, 251, 251));
                    panel.setLayout(null);

                    name = new JLabel(orderEntry.getName());
                    price = new JLabel("$" + Float.toString(orderEntry.getPrice()));

                    name.setBounds(10, 0, 130, 30);
                    name.setFont(new Font("Calibri Light", Font.PLAIN, 14));

                    price.setBounds(140, 0, 100, 30);
                    price.setFont(new Font("Calibri Light", Font.BOLD, 14));

                    deleteIcon = new JLabel(new ImageIcon("src/gui/img/bin.png"));
                    deleteIcon.setBounds(230, 5, 15, 15);
                    deleteIcon.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            deleteDish(orderEntry);
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

                    amountField = new JTextField(Integer.toString(orderEntry.getAmount()));
                    amountField.setBounds(190, 5, 30, 20);
                    amountField.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));
                    amountField.setBackground(Color.WHITE);
                    amountField.setFont(new Font("Calibri Light", Font.PLAIN, 14));
                    final String amount = amountField.getText();

                    this.add(panel);
                    panel.add(name);
                    panel.add(price);
                    panel.add(deleteIcon);
                    panel.add(amountField);

                    actuallYPosition += 31;
                    this.setPreferredSize(new Dimension(220, actuallYPosition - 10));
                }
            }

            public void createList(ArrayList<OrderEntry> orderList) {
                for (OrderEntry orderEntry : orderList) {
                    this.orderList.add(orderEntry);
                }
            }
        }

    }

    class MenuList extends JPanel {

        private ArrayList<gui.MenuEntry> starterList;
        private ArrayList<gui.MenuEntry> soupList;
        private ArrayList<gui.MenuEntry> mainCourseList;
        private ArrayList<gui.MenuEntry> pastaList;
        private ArrayList<gui.MenuEntry> pizzaList;
        private ArrayList<gui.MenuEntry> dessertList;
        private ArrayList<gui.MenuEntry> otherList;
        private ArrayList<JPanel> panelList;
        private static final int PANEL_WIDTH = 800;
        private static final int PANEL_HEIGHT = 60;
        private int actuallYPosition = 0;
        private ManagerPanel managerPanel;
        private OrderPanel orderPanel;

        private int selectedDishID = 0;

        public MenuList(OrderPanel orderPanel) {
            super();
            this.orderPanel = orderPanel;
            panelList = new ArrayList<>();
            this.setLayout(null);
            this.setBackground(new Color(245, 245, 245));
            starterList = new ArrayList<>();
            soupList = new ArrayList<>();
            mainCourseList = new ArrayList<>();
            pastaList = new ArrayList<>();
            pizzaList = new ArrayList<>();
            dessertList = new ArrayList<>();
            otherList = new ArrayList<>();
        }

        public void createList(ArrayList<gui.MenuEntry> dishList) {
            starterList.clear();
            soupList.clear();
            mainCourseList.clear();
            pastaList.clear();
            pizzaList.clear();
            dessertList.clear();
            otherList.clear();

            for (gui.MenuEntry menuEntry : dishList) {
                addDish(menuEntry);
            }
        }

        public int getSelectedDishID() {
            return this.selectedDishID;
        }

        private void addDish(gui.MenuEntry menuEntry) {
            switch (menuEntry.getRealType()) {
                case "STARTER":
                    starterList.add(menuEntry);
                    break;
                case "SOUP":
                    soupList.add(menuEntry);
                    break;
                case "MAIN_COURSE":
                    mainCourseList.add(menuEntry);
                    break;
                case "PASTA":
                    pastaList.add(menuEntry);
                    break;
                case "PIZZA":
                    pizzaList.add(menuEntry);
                    break;
                case "DESSERT":
                    dessertList.add(menuEntry);
                    break;
                case "OTHER":
                    otherList.add(menuEntry);
                    break;
            }
        }

        public void deleteSelected() {
            DatabaseRequest.deleteDish(this.selectedDishID);
            selectedDishID = 0;
        }

        public void dishNotSelected() {
            selectedDishID = 0;
            clearPanelColor();
        }

        private void clearPanelColor() {
            for (JPanel panel : panelList) {
                panel.setBackground(new Color(250, 250, 250));
            }
        }

        private void drawContent(final gui.MenuEntry menuEntry) {
            JPanel panel = new JPanel();
            panelList.add(panel);
            JLabel nameLabel = new JLabel(menuEntry.getName());
            JLabel descriptionLabel = new JLabel(menuEntry.getDescription());
            JLabel priceLabel = new JLabel("$" + menuEntry.getPrice(), SwingConstants.CENTER);
            JLabel vegetarianIcon = new JLabel(new ImageIcon("src/gui/img/vegetarian.png"));
            JLabel fishIcon = new JLabel(new ImageIcon("src/gui/img/fish.png"));
            JLabel spicyIcon = new JLabel(new ImageIcon("src/gui/img/spicy.png"));
            JLabel garlicIcon = new JLabel(new ImageIcon("src/gui/img/garlic.png"));

            ArrayList<JLabel> icons = new ArrayList<>();

            panel.setLayout(null);
            panel.setBounds(0, actuallYPosition, PANEL_WIDTH, PANEL_HEIGHT);
            panel.setFocusable(true);

            nameLabel.setBounds(20, 10, 200, 30);
            nameLabel.setFont(new Font("Calibri", Font.PLAIN, 20));

            descriptionLabel.setBounds(40, 31, 400, 35);
            descriptionLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
            descriptionLabel.setForeground(new Color(130, 130, 130));

            priceLabel.setBounds(525, 17, 55, 30);
            priceLabel.setFont(new Font("Calibri", Font.BOLD, 18));
            priceLabel.setForeground(new Color(255, 255, 255));
            priceLabel.setBackground(new Color(80, 174, 204));
            priceLabel.setOpaque(true);

            garlicIcon.setVisible(false);
            fishIcon.setVisible(false);
            spicyIcon.setVisible(false);
            vegetarianIcon.setVisible(false);

            if (menuEntry.isFish()) {
                icons.add(fishIcon);
            }
            if (menuEntry.isGarlic()) {
                icons.add(garlicIcon);
            }
            if (menuEntry.isVegetarian()) {
                icons.add(vegetarianIcon);
            }
            if (menuEntry.isSpicy()) {
                icons.add(spicyIcon);
            }

            int iconPosition = 330;
            for (JLabel icon : icons) {
                icon.setBounds(iconPosition, 18, 30, 30);
                icon.setVisible(true);
                panel.add(icon);
                iconPosition += 40;
            }

            panel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    orderPanel.addDishToOrder(menuEntry.getName(), menuEntry.getPrice(), 1);
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
                    if (selectedDishID == menuEntry.getID()) {
                        e.getComponent().setBackground(new Color(233, 250, 230));
                    } else {
                        e.getComponent().setBackground(new Color(253, 255, 255));
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    if (selectedDishID == menuEntry.getID()) {
                        e.getComponent().setBackground(new Color(233, 250, 230));
                    } else {
                        e.getComponent().setBackground(new Color(250, 250, 250));
                    }
                }
            });

            this.add(panel);
            panel.add(nameLabel);
            panel.add(descriptionLabel);
            panel.add(priceLabel);

            panel.setBackground(new Color(252, 252, 252));
            actuallYPosition += PANEL_HEIGHT + 4;
            this.setPreferredSize(new Dimension(500, actuallYPosition - 10));
        }

        private void drawSeparator(String name) {
            JPanel panel = new JPanel();
            JLabel label = new JLabel(name);

            panel.setLayout(null);
            panel.setBounds(0, actuallYPosition, PANEL_WIDTH, PANEL_HEIGHT + 5);
            panel.setFocusable(true);

            label.setBounds(30, 15, 200, 40);
            label.setFont(new Font("Calibri Light", Font.PLAIN, 25));

            this.add(panel);
            panel.add(label);

            panel.setBackground(new Color(255, 255, 255));
            actuallYPosition += PANEL_HEIGHT + 10;
            this.setPreferredSize(new Dimension(500, actuallYPosition - 10));

        }

        public void drawList() {
            actuallYPosition = 0;
            if (!pizzaList.isEmpty()) {
                drawSeparator("Pizza");
                for (gui.MenuEntry menuEntry : pizzaList) {
                    drawContent(menuEntry);
                }
            }

            if (!starterList.isEmpty()) {
                drawSeparator("Starters");
                for (gui.MenuEntry menuEntry : starterList) {
                    drawContent(menuEntry);
                }
            }

            if (!soupList.isEmpty()) {
                drawSeparator("Soups");
                for (gui.MenuEntry menuEntry : soupList) {
                    drawContent(menuEntry);
                }
            }

            if (!mainCourseList.isEmpty()) {
                drawSeparator("Main Course");
                for (gui.MenuEntry menuEntry : mainCourseList) {
                    drawContent(menuEntry);
                }
            }

            if (!pastaList.isEmpty()) {
                drawSeparator("Pasta");
                for (gui.MenuEntry menuEntry : pastaList) {
                    drawContent(menuEntry);
                }
            }

            if (!dessertList.isEmpty()) {
                drawSeparator("Dessert");
                for (gui.MenuEntry menuEntry : dessertList) {
                    drawContent(menuEntry);
                }
            }

            if (!otherList.isEmpty()) {
                drawSeparator("Other");
                for (gui.MenuEntry menuEntry : otherList) {
                    drawContent(menuEntry);
                }
            }
        }

    }

    class MenuEntry {

        private int ID;
        private String name, description, price, type;
        private boolean vegetarian, spicy, fish, garlic;

        public MenuEntry(int ID, String name, String description, String price,
                String type, boolean vegetarian, boolean spicy,
                boolean fish, boolean garlic) {
            this.ID = ID;
            this.name = name;
            this.description = description;
            this.price = price;
            this.type = type;
            this.vegetarian = vegetarian;
            this.spicy = spicy;
            this.fish = fish;
            this.garlic = garlic;
        }

        public String getName() {
            return this.name;
        }

        public int getID() {
            return this.ID;
        }

        public String getDescription() {
            String formatDescription = "";
            formatDescription = formatDescription.concat(this.description);
            return formatDescription;
        }

        public String getPrice() {
            return this.price;
        }

        public String getRealType() {
            return this.type;
        }

        public String getType() {
            switch (this.type) {
                case "STARTER":
                    return "Starter";
                case "SOUP":
                    return "Soup";
                case "MAIN_COURSE":
                    return "Main Course";
                case "PASTA":
                    return "Pasta";
                case "PIZZA":
                    return "Pizza";
                case "DESSERT":
                    return "Dessert";
                case "OTHER":
                    return "Other";
                default:
                    return null;
            }
        }

        public boolean isVegetarian() {
            return this.vegetarian;
        }

        public boolean isFish() {
            return this.fish;
        }

        public boolean isSpicy() {
            return this.spicy;
        }

        public boolean isGarlic() {
            return this.garlic;
        }
    }

}
