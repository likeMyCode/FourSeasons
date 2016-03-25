package gui;

import four_seasons.AdditionalMethods;
import four_seasons.DatabaseRequest;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RegisterRestaurantPanel extends JPanel {

    private MainWindow window;
    private JPanel thisPanel, registerPanel;
    private JLabel backgroundImage, logoImage, nameLabel, cuisineLabel,
            cityLabel, streetLabel, houseNumberLabel, flatNumberLabel,
            deliveryCostLabel, minimumCostLabel;
    private JTextField nameField, cuisineField, cityField, streetField,
            houseNumberField, flatNumberField, deliveryCostField, minimumCostField;
    private JButton registerButton;

    public RegisterRestaurantPanel(MainWindow window) {
        this.window = window;
        thisPanel = this;
        initUI();
    }

    void initUI() {
        backgroundImage = new JLabel(new ImageIcon("src/gui/img/background.png"));
        registerPanel = new JPanel();
        logoImage = new JLabel(new ImageIcon("src/gui/img/logo.png"));
        nameField = new JTextField();
        cuisineField = new JTextField();
        cityField = new JTextField();
        streetField = new JTextField();
        houseNumberField = new JTextField();
        flatNumberField = new JTextField();
        deliveryCostField = new JTextField();
        minimumCostField = new JTextField();
        nameLabel = new JLabel("Name");
        cityLabel = new JLabel("City");
        streetLabel = new JLabel("Street");
        houseNumberLabel = new JLabel("House Number");
        flatNumberLabel = new JLabel("Flat Number");
        cuisineLabel = new JLabel("Cuisine");
        deliveryCostLabel = new JLabel("Delivery Cost");
        minimumCostLabel = new JLabel("Minimum Cost");
        registerButton = new JButton("Register");

        this.setLayout(null);
        registerPanel.setLayout(null);

        backgroundImage.setBounds(0, 0, 860, 600);
        backgroundImage.setLayout(null);

        registerPanel.setBounds(40, 50, 300, 475);
        registerPanel.setBackground(new Color(252, 252, 252));
        registerPanel.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));

        logoImage.setBounds(72, 20, 160, 100);

        nameField.setBounds(154, 140, 120, 26);
        nameField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        nameLabel.setBounds(39, 140, 120, 26);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        nameField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        cityField.setBounds(154, 175, 120, 26);
        cityField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        cityLabel.setBounds(39, 175, 120, 26);
        cityLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        cityField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        streetField.setBounds(154, 210, 120, 26);
        streetField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        streetLabel.setBounds(39, 210, 120, 26);
        streetLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        streetField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        houseNumberField.setBounds(154, 245, 120, 26);
        houseNumberField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        houseNumberLabel.setBounds(39, 245, 120, 26);
        houseNumberLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        houseNumberField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        flatNumberField.setBounds(154, 280, 120, 26);
        flatNumberField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        flatNumberLabel.setBounds(39, 280, 120, 26);
        flatNumberLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        cuisineField.setBounds(154, 315, 120, 26);
        cuisineField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        cuisineLabel.setBounds(39, 315, 120, 26);
        cuisineLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        cuisineField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        deliveryCostField.setBounds(154, 350, 120, 26);
        deliveryCostField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        deliveryCostLabel.setBounds(39, 350, 120, 26);
        deliveryCostLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        deliveryCostField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        minimumCostField.setBounds(154, 385, 120, 26);
        minimumCostField.setFont(new Font("Calibri Light", Font.PLAIN, 16));
        minimumCostLabel.setBounds(39, 385, 120, 26);
        minimumCostLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        minimumCostField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));

        registerButton.setBounds(102, 430, 100, 26);
        registerButton.setBackground(new Color(240, 240, 240));
        registerButton.setFont(new Font("Calibri", Font.PLAIN, 14));
        registerButton.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));

        registerButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                register(AdditionalMethods.removeDiacriticalMarks(nameField.getText()),
                        AdditionalMethods.removeDiacriticalMarks(cityField.getText()),
                        AdditionalMethods.removeDiacriticalMarks(streetField.getText()),
                        AdditionalMethods.removeDiacriticalMarks(houseNumberField.getText()),
                        AdditionalMethods.removeDiacriticalMarks(flatNumberField.getText()),
                        AdditionalMethods.removeDiacriticalMarks(cuisineField.getText()),
                        AdditionalMethods.removeDiacriticalMarks(deliveryCostField.getText()),
                        AdditionalMethods.removeDiacriticalMarks(minimumCostField.getText()));
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

        this.add(backgroundImage);
        backgroundImage.add(registerPanel);
        registerPanel.add(logoImage);
        registerPanel.add(nameField);
        registerPanel.add(cityField);
        registerPanel.add(streetField);
        registerPanel.add(houseNumberField);
        registerPanel.add(flatNumberField);
        registerPanel.add(cuisineField);
        registerPanel.add(deliveryCostField);
        registerPanel.add(minimumCostField);

        registerPanel.add(nameLabel);
        registerPanel.add(cityLabel);
        registerPanel.add(streetLabel);
        registerPanel.add(houseNumberLabel);
        registerPanel.add(flatNumberLabel);
        registerPanel.add(cuisineLabel);
        registerPanel.add(deliveryCostLabel);
        registerPanel.add(minimumCostLabel);
        registerPanel.add(registerButton);
    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    private void register(String name, String city, String street, String houseNumber,
            String flatNumber, String cuisine, String deliveryCost, String minimumCost) {

        boolean noError = true;

        if (name.equals("") || name.length() > 30) {
            nameField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
            noError = false;
        } else {
            nameField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
        }

        if (city.equals("") || city.length() > 30) {
            cityField.setBorder(BorderFactory.createLineBorder(Color.red));
            noError = false;
        } else {
            cityField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
        }

        if (street.equals("") || street.length() > 30) {
            streetField.setBorder(BorderFactory.createLineBorder(Color.red));
            noError = false;
        } else {
            streetField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
        }

        if (houseNumber.equals("") || houseNumber.length() > 6) {
            houseNumberField.setBorder(BorderFactory.createLineBorder(Color.red));
            noError = false;
        } else {
            houseNumberField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
        }

        if (cuisine.equals("") || cuisine.length() > 20) {
            cuisineField.setBorder(BorderFactory.createLineBorder(Color.red));
            noError = false;
        } else {
            cuisineField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
        }

        if (deliveryCost.equals("")) {
            deliveryCost = "0";
        };

        if (deliveryCost.length() > 5 || AdditionalMethods.isInteger(deliveryCost)) {
            deliveryCostField.setBorder(BorderFactory.createLineBorder(Color.red));
            noError = false;
        } else {
            deliveryCostField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
        }

        if (minimumCost.length() > 4 || AdditionalMethods.isInteger(minimumCost)) {
            minimumCostField.setBorder(BorderFactory.createLineBorder(Color.red));
            noError = false;
        } else {
            minimumCostField.setBorder(BorderFactory.createLineBorder(new Color(180, 185, 183)));
        }

        if (minimumCost.equals("")) {
            minimumCost = "0";
        }

        if ((!AdditionalMethods.isInteger(flatNumber) && !flatNumber.equals("")) || flatNumber.length() > 5) {
            flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(255, 0, 0)));
            noError = false;
        } else {
            flatNumberField.setBorder(BorderFactory.createLineBorder(new Color(235, 235, 235)));
        }

        if (noError) {
            int register = DatabaseRequest.registerRestaurant(name, city, street,
                    houseNumber, flatNumber, cuisine, deliveryCost, minimumCost, window.getLogin());

            switch (register) {
                case -2:
                    System.out.println("UNKNOWN ERROR");
                    break;
                case -1:
                    cityField.setBorder(BorderFactory.createLineBorder(Color.red));
                    streetField.setBorder(BorderFactory.createLineBorder(Color.red));
                    break;
                case 1:
                    System.out.println("REGISTERED");
            }
        }
    }
}
