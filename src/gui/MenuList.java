package gui;

import four_seasons.DatabaseRequest;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuList extends JPanel {

    private ArrayList<MenuEntry> starterList;
    private ArrayList<MenuEntry> soupList;
    private ArrayList<MenuEntry> mainCourseList;
    private ArrayList<MenuEntry> pastaList;
    private ArrayList<MenuEntry> pizzaList;
    private ArrayList<MenuEntry> dessertList;
    private ArrayList<MenuEntry> otherList;
    private ArrayList<JPanel> panelList;
    private static final int PANEL_WIDTH = 800;
    private static final int PANEL_HEIGHT = 60;
    private int actuallYPosition = 0;
    private ManagerPanel managerPanel;

    private int selectedDishID = 0;

    public MenuList(ManagerPanel managerPanel) {
        super();
        this.managerPanel = managerPanel;
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

    public void createList(ArrayList<MenuEntry> dishList) {
        starterList.clear();
        soupList.clear();
        mainCourseList.clear();
        pastaList.clear();
        pizzaList.clear();
        dessertList.clear();
        otherList.clear();

        for (MenuEntry menuEntry : dishList) {
            addDish(menuEntry);
        }
    }

    public int getSelectedDishID() {
        return this.selectedDishID;
    }

    private void addDish(MenuEntry menuEntry) {
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

    private void drawContent(final MenuEntry menuEntry) {
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
                if (selectedDishID == menuEntry.getID()) {
                    managerPanel.editRestaurantRequest();
                } else {
                    managerPanel.dishSelected();
                    clearPanelColor();
                    selectedDishID = menuEntry.getID();
                    if (selectedDishID == menuEntry.getID()) {
                        e.getComponent().setBackground(new Color(233, 250, 230));
                    } else {
                        e.getComponent().setBackground(new Color(250, 250, 250));
                    }
                    managerPanel.editDishRequest();
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
            for (MenuEntry menuEntry : pizzaList) {
                drawContent(menuEntry);
            }
        }

        if (!starterList.isEmpty()) {
            drawSeparator("Starters");
            for (MenuEntry menuEntry : starterList) {
                drawContent(menuEntry);
            }
        }

        if (!soupList.isEmpty()) {
            drawSeparator("Soups");
            for (MenuEntry menuEntry : soupList) {
                drawContent(menuEntry);
            }
        }

        if (!mainCourseList.isEmpty()) {
            drawSeparator("Main Course");
            for (MenuEntry menuEntry : mainCourseList) {
                drawContent(menuEntry);
            }
        }

        if (!pastaList.isEmpty()) {
            drawSeparator("Pasta");
            for (MenuEntry menuEntry : pastaList) {
                drawContent(menuEntry);
            }
        }

        if (!dessertList.isEmpty()) {
            drawSeparator("Dessert");
            for (MenuEntry menuEntry : dessertList) {
                drawContent(menuEntry);
            }
        }

        if (!otherList.isEmpty()) {
            drawSeparator("Other");
            for (MenuEntry menuEntry : otherList) {
                drawContent(menuEntry);
            }
        }
    }

}
