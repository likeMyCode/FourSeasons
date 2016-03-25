package gui;

import four_seasons.DatabaseRequest;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class PickRestaurantPanel extends JPanel {

    private MainWindow window;
    private JPanel header, thisPanel, footer, rightPanel;
    private RestaurantList restaurantList;
    private JScrollPane restaurantPane;
    private JLabel logoImage, panelInfoLabel;
    private JButton userMenu, userLogout;

    public PickRestaurantPanel(MainWindow window) {
        this.window = window;
        thisPanel = this;
        initUI();
    }

    private void initUI() {
        header = new JPanel();
        footer = new JPanel();
        rightPanel = new JPanel();
        restaurantList = new RestaurantList();
        restaurantPane = new JScrollPane(restaurantList);
        logoImage = new JLabel(new ImageIcon("src/gui/img/logo-mini.png"));
        userMenu = new JButton(window.getLogin());
        userLogout = new JButton("Logout");
        panelInfoLabel = new JLabel("Pick Restaurant");

        this.setLayout(null);
        this.setBackground(new Color(220, 220, 220));
        header.setLayout(null);
        header.setFocusable(true);

        rightPanel.setLayout(null);
        rightPanel.setBounds(600, 51, 860 - 600, 500);
        rightPanel.setBackground(new Color(242, 242, 242));
        rightPanel.setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240)));

        header.setBounds(0, 0, 860, 50);
        header.setBackground(new Color(245, 245, 245));

        footer.setBounds(0, 551, 860, 20);
        footer.setBackground(new Color(250, 250, 250));

        logoImage.setBounds(5, 5, 100, 40);

        userMenu.setBounds(730, 0, 100, 50);
        userMenu.setFont(new Font("Segoe Ui", Font.PLAIN, 16));
        userMenu.setBackground(new Color(245, 245, 245));
        userMenu.setBorder(null);

        panelInfoLabel.setBounds(100, 16, 180, 30);
        panelInfoLabel.setFont(new Font("Calibri Light", Font.PLAIN, 21));
        panelInfoLabel.setForeground(new Color(60, 60, 60));

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

        restaurantPane.setBounds(0, 51, 600, 500);
        restaurantPane.setBorder(null);
        restaurantPane.getVerticalScrollBar().setUnitIncrement(30);
        restaurantPane.getVerticalScrollBar().setPreferredSize(new Dimension(7, 0));
        restaurantPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
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

        ArrayList<String> restaurantInfoList = DatabaseRequest.getRestaurantList(window.getLogin());

        if (restaurantInfoList != null) {
            for (int i = 0; i < restaurantInfoList.size(); i += 6) {
                restaurantList.addRestaurant(Integer.parseInt(restaurantInfoList.get(i)),
                        restaurantInfoList.get(i + 1),
                        restaurantInfoList.get(i + 2),
                        Float.parseFloat(restaurantInfoList.get(i + 3)),
                        Integer.parseInt(restaurantInfoList.get(i + 4)),
                        Integer.parseInt(restaurantInfoList.get(i + 5)));
            }
        }

        restaurantList.drawList();

        this.add(header);
        this.add(footer);
        this.add(restaurantPane);
        header.add(logoImage);
        header.add(userMenu);
        header.add(panelInfoLabel);
        this.add(userLogout);
        this.add(rightPanel);
    }

    class RestaurantList extends JPanel {

        private ArrayList<RestaurantEntry> restaurantList;
        private static final int PANEL_WIDTH = 800;
        private static final int PANEL_HEIGHT = 60;
        private int actuallYPosition = 0;

        public RestaurantList() {
            super();
            this.setLayout(null);
            this.setBackground(new Color(245, 245, 245));
            restaurantList = new ArrayList<>();
        }

        public void addRestaurant(int ID, String name, String type, float stars,
                int deliveryCost, int minimumCost) {

            restaurantList.add(new RestaurantEntry(ID, name, type, stars, deliveryCost, minimumCost));
        }

        public void drawList() {
            for (final RestaurantEntry restaurantEntry : restaurantList) {
                JPanel panel = new JPanel();
                ArrayList<JLabel> stars = new ArrayList<>();
                JLabel infoCostLabel = new JLabel("Delivery Cost");
                JLabel deliveryCostLabel = new JLabel(restaurantEntry.getDeliveryCost() + "$");
                JLabel restaurantName = new JLabel(restaurantEntry.getName());
                JLabel restaurantType = new JLabel(restaurantEntry.getType());
                JLabel infoMinCostLabel = new JLabel("Minimum Cost");
                JLabel minimumCostLabel = new JLabel(restaurantEntry.getMinimumCost() + "$");

                panel.setLayout(null);
                panel.setBounds(0, actuallYPosition, PANEL_WIDTH, PANEL_HEIGHT);
                panel.setFocusable(true);

                restaurantName.setBounds(20, 10, 200, 30);
                restaurantName.setFont(new Font("Calibri", Font.PLAIN, 20));

                restaurantType.setBounds(40, 30, 200, 30);
                restaurantType.setFont(new Font("Calibri", Font.PLAIN, 15));
                restaurantType.setForeground(new Color(150, 150, 150));

                infoCostLabel.setBounds(360, 8, 200, 30);
                infoCostLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
                infoCostLabel.setForeground(new Color(150, 150, 150));

                deliveryCostLabel.setBounds(390, 29, 200, 30);
                deliveryCostLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
                deliveryCostLabel.setForeground(new Color(130, 140, 150));

                infoMinCostLabel.setBounds(480, 8, 200, 30);
                infoMinCostLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
                infoMinCostLabel.setForeground(new Color(150, 150, 150));

                minimumCostLabel.setBounds(510, 29, 200, 30);
                minimumCostLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
                minimumCostLabel.setForeground(new Color(150, 150, 150));

                panel.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JPanel menuPanel = new RestaurantMenuPanel(window, restaurantEntry.getID(),
                                restaurantEntry.getMinimumCost(), restaurantEntry.getDeliveryCost());
                        window.add(menuPanel);
                        thisPanel.setVisible(false);
                        menuPanel.setVisible(true);
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
                });

                float starsNumber = restaurantEntry.getStars();
                for (int i = 0; i < 5; i++) {
                    if (starsNumber <= 0) {
                        stars.add(new JLabel(new ImageIcon("src/gui/img/star-none.png")));
                    } else if (starsNumber < 1 && starsNumber > 0) {
                        stars.add(new JLabel(new ImageIcon("src/gui/img/star-half.png")));
                    } else if (starsNumber >= 1) {
                        stars.add(new JLabel(new ImageIcon("src/gui/img/star-full.png")));
                    }
                    starsNumber--;
                }

                int starPosition = 140;
                for (JLabel star : stars) {
                    star.setBounds(starPosition, 5, 50, 50);
                    starPosition += 35;
                    panel.add(star);
                }

                this.add(panel);
                panel.add(restaurantName);
                panel.add(restaurantType);
                panel.add(infoCostLabel);
                panel.add(deliveryCostLabel);
                panel.add(infoMinCostLabel);
                panel.add(minimumCostLabel);

                panel.setBackground(new Color(252, 252, 252));

                actuallYPosition += PANEL_HEIGHT + 4;
            }
            this.setPreferredSize(new Dimension(500, actuallYPosition - 10));
        }

    }

    class RestaurantEntry {

        private String name, type;
        private int deliveryCost;
        private int minimumCost;
        private int ID;
        private float stars;

        public RestaurantEntry(int ID, String name, String type, float stars,
                int deliveryCost, int minimumCost) {
            this.ID = ID;
            this.name = name;
            this.type = type;
            this.stars = stars;
            this.deliveryCost = deliveryCost;
            this.minimumCost = minimumCost;
        }

        public int getID() {
            return this.ID;
        }

        public String getName() {
            return this.name;
        }

        public String getType() {
            return this.type;
        }

        public float getStars() {
            return this.stars;
        }

        public int getDeliveryCost() {
            return this.deliveryCost;
        }

        public int getMinimumCost() {
            return this.minimumCost;
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
