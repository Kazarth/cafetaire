package code.control;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;
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
        JFrame frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Cafetairé Inventory System 1.0");
        frame.add(fxPanel);
        frame.setBounds(200, 100, 1366, 768);
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
