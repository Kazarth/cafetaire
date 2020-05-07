package Control;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.FileNotFoundException;

/**
 * TODO: start an instance of the Controller, which in turn contains the views.
 * Start.java
 * The class which starts an instance of the Controller, MainPane etc.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class Start {
    private static void initAndShowGUI() throws FileNotFoundException {
        JFrame frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Cafetairé Inventory System 1.0");
        frame.add(fxPanel);
        frame.setBounds(200, 100, 1366, 768);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setVisible(true);

        Controller controller = new Controller();

        Platform.runLater(() -> initFX(controller, fxPanel));
    }

    private static void initFX(Controller controller, JFXPanel fxPanel) {
        fxPanel.setScene(new Scene(controller.getMainPane()));
    }

    public static void main(String[] args) throws FileNotFoundException {
        initAndShowGUI();
    }
}
