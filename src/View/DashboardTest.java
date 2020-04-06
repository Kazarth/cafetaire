package View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;

import javax.swing.*;

public class DashboardTest {
    private static void initAndShowGUI() { // This method is invoked on Swing thread
        JFrame frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Test Container");
        frame.add(fxPanel);
        frame.setBounds(200, 100, 1086, 768);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setVisible(true);

        Platform.runLater(() -> initFX(fxPanel));
    }

    private static void initFX(JFXPanel fxPanel) { // This method is invoked on JavaFX thread
        fxPanel.setScene(new Scene(new Dashboard()));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DashboardTest::initAndShowGUI);
    }
}
