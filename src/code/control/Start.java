package code.control;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Start.java
 * The class which starts the application by setting up a container JFXPanel and a Controller to manage the application.
 * @author Tor Stenfeldt
 * @version 4.0
 */
public class Start {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = screenSize.width/2;
        int centerY = screenSize.height/2;
        int posX = centerX-675;
        int posY = centerY-384;

        JFrame frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Cafetairé Inventory System 1.0");
        ImageIcon icon = new ImageIcon("src/resources/logo/icon.png");
        frame.setIconImage(icon.getImage());
        frame.add(fxPanel);
        frame.setBounds(posX, posY, 1350, 775);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        Controller controller = new Controller();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing (WindowEvent e) {
                controller.handleClosing();
            }
        });

        Platform.runLater(() -> fxPanel.setScene(new Scene(controller.getMainPane())));
    }
}
