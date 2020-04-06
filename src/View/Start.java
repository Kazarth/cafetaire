package View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;

/**
 * TODO: start an instance of the Controller, which in turn contains the views.
 * Start.java
 * The class which starts an instance of the Controller, MainPane etc.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class Start {
    private static void initAndShowGUI() {
        JFrame frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Cafetairé Inventory System 1.0");
        frame.add(fxPanel);
        frame.setBounds(200, 100, 1366, 768);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setVisible(true);

        Platform.runLater(() -> initFX(fxPanel));
    }

    private static void initFX(JFXPanel fxPanel) {
        fxPanel.setScene(new Scene(new MainPane()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Start::initAndShowGUI);
    }
}
