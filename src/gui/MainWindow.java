package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainWindow extends JFrame implements Runnable {

    JPanel signInPanel;
    private String login, password;
    JPanel registerRestaurant;
    private boolean clientLogged = false, managerLogged = false;

    public MainWindow() {
        new Thread(this).start();
        initUI();
    }

    void initUI() {

        /*JPanel clientPanel = new ClientPanel(this);
        this.add(clientPanel);
        clientPanel.setVisible(true);*/
        signInPanel = new SignInPanel(this);
        this.add(signInPanel);
        signInPanel.setVisible(true);

        setResizable(false);
        setTitle("Four Seasons");
        setSize(860, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void clientLogged(String login, String password) {
        this.login = login;
        this.password = password;
        clientLogged = true;
        managerLogged = false;
    }

    public void managerLogged(String login, String password) {
        this.login = login;
        this.password = password;
        managerLogged = true;
        clientLogged = false;
    }

    public boolean isClientLogged() {
        return clientLogged;
    }

    public void logout() {
        this.login = null;
        this.password = null;
        clientLogged = false;
        managerLogged = false;
    }

    public boolean isManagerLogged() {
        return managerLogged;
    }

    public String getLogin() {
        return this.login;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void run() {
        //DatabaseRequest.connectToDatabase();
    }

}
