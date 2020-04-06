package View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * LeftMenuBarController.java
 * Handles the navigation logic
 * @author Lucas ELiasson
 * @version 3.0
 */
public class LeftMenuBarController implements Initializable {

    @FXML
    private Pane pnl_dashboard, pnl_ingredients, pnl_perishables, pnl_suppliers, pnl_schedule;

    @FXML
    private Button btn_dashboard, btn_ingredients, btn_perishables, btn_suppliers, btn_schedule;

    @FXML
    public void handleButtonAction(ActionEvent e) {
        if (e.getSource() == btn_dashboard) {
            pnl_dashboard.toFront();
        } else if (e.getSource() == btn_ingredients) {
            pnl_ingredients.toFront();
        } else if (e.getSource() == btn_perishables) {
            pnl_perishables.toFront();
        } else if (e.getSource() == btn_suppliers) {
            pnl_suppliers.toFront();
        } else if (e.getSource() == btn_schedule) {
            pnl_schedule.toFront();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


























