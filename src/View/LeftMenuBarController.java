package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * LeftMenuBarController.java
 * Handles the navigation logic
 * @author Lucas ELiasson
 * @version 3.0
 */
public class LeftMenuBarController implements Initializable {
    private Parent schedule;

    public LeftMenuBarController() throws IOException {
        loadPanels();
    }

    @FXML
    private BorderPane container;

    @FXML
    private Button btn_dashboard, btn_ingredients, btn_perishables, btn_suppliers, btn_schedule;

    @FXML
    public void handleButtonAction(ActionEvent e) throws IOException {
        Parent centerView = null;

        if (e.getSource() == btn_dashboard) {
            container.setCenter(centerView);
           // centerView =FXMLLoader.load(getClass().getResource("123.fxml"));
        } else if (e.getSource() == btn_ingredients) {
            container.setCenter(centerView);
           // centerView =FXMLLoader.load(getClass().getResource("123.fxml"));
        } else if (e.getSource() == btn_perishables) {
            container.setCenter(centerView);
           // centerView =FXMLLoader.load(getClass().getResource("123.fxml"));
        } else if (e.getSource() == btn_suppliers) {
            container.setCenter(centerView);
            //centerView =FXMLLoader.load(getClass().getResource("123.fxml"));
        } else if (e.getSource() == btn_schedule) {
            //centerView = FXMLLoader.load(getClass().getResource("SchedulePanel.fxml"));
            container.setCenter(schedule);
        }
    }

    private void loadPanels() throws IOException {
        schedule = FXMLLoader.load(getClass().getResource("SchedulePanel.fxml"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


























