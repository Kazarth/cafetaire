package code.control;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * TODO: start an instance of the Controller, which in turn contains the views.
 * Start.java
 * The class which starts an instance of the Controller, MainPane etc.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class Start {
    private static void initAndShowGUI(){
        JFrame frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Cafetairé Inventory System 1.0");
        frame.add(fxPanel);
        frame.setBounds(200, 100, 1366, 768);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        frame.setResizable(false);
        frame.setVisible(true);

        Controller controller = new Controller();
        Platform.runLater(() -> initFX(controller, fxPanel));

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing (WindowEvent e) {
                controller.handleClosing();
            }
        });
    }



    private static void initFX(Controller controller, JFXPanel fxPanel) {
        fxPanel.setScene(new Scene(controller.getMainPane()));
    }

    public static void main(String[] args) {
        initAndShowGUI();
    }
}
