package four_seasons;

import gui.MainWindow;
import java.awt.EventQueue;

public class FourSeasons {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                MainWindow mainWindow = new MainWindow();
                mainWindow.setVisible(true);
            }
        });
    }

}
