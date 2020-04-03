package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LeftMenu extends Application {
    public Button btnDashboard;
    public Button btnIngredients;
    public Button btnPerishables;
    public Button btnSuppliers;
    public Button btnSchedule;
    public Button btnCropExpand;

    private ArrayList<Button> buttons;

    public LeftMenu() {
        btnDashboard = new Button();
        btnIngredients = new Button();
        btnPerishables = new Button();
        btnSuppliers = new Button();
        btnSchedule = new Button();
        btnCropExpand = new Button();

        buttons = new ArrayList<>();
        buttons.add(btnDashboard);
        buttons.add(btnIngredients);
        buttons.add(btnPerishables);
        buttons.add(btnSuppliers);
        buttons.add(btnSchedule);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("LeftMenu.fxml"));
        primaryStage.setTitle("Hello World");

        Scene scene = new Scene(root, 1366,768);
        scene.getStylesheets().add("styles.css");
        btnDashboard.getStyleClass().add("button-active");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void dashboard() {
        btnDashboard.getStyleClass().add("button-active");
        btnIngredients.getStyleClass().clear();
        btnIngredients.getStyleClass().add("button");
        btnPerishables.getStyleClass().clear();
        btnPerishables.getStyleClass().add("button");
        btnSuppliers.getStyleClass().clear();
        btnSuppliers.getStyleClass().add("button");
        btnSchedule.getStyleClass().clear();
        btnSchedule.getStyleClass().add("button");
    }

    public void ingredients() {
        btnDashboard.getStyleClass().clear();
        btnDashboard.getStyleClass().add("button");
        btnIngredients.getStyleClass().add("button-active");
        btnPerishables.getStyleClass().clear();
        btnPerishables.getStyleClass().add("button");
        btnSuppliers.getStyleClass().clear();
        btnSuppliers.getStyleClass().add("button");
        btnSchedule.getStyleClass().clear();
        btnSchedule.getStyleClass().add("button");
    }

    public void perishables() {
        btnDashboard.getStyleClass().clear();
        btnDashboard.getStyleClass().add("button");
        btnIngredients.getStyleClass().clear();
        btnIngredients.getStyleClass().add("button");
        btnPerishables.getStyleClass().add("button-active");
        btnSuppliers.getStyleClass().clear();
        btnSuppliers.getStyleClass().add("button");
        btnSchedule.getStyleClass().clear();
        btnSchedule.getStyleClass().add("button");
    }

    public void suppliers() {
        btnDashboard.getStyleClass().clear();
        btnDashboard.getStyleClass().add("button");
        btnIngredients.getStyleClass().clear();
        btnIngredients.getStyleClass().add("button");
        btnPerishables.getStyleClass().clear();
        btnPerishables.getStyleClass().add("button");
        btnSuppliers.getStyleClass().add("button-active");
        btnSchedule.getStyleClass().clear();
        btnSchedule.getStyleClass().add("button");
    }

    public void schedule() {
        btnDashboard.getStyleClass().clear();
        btnDashboard.getStyleClass().add("button");
        btnIngredients.getStyleClass().clear();
        btnIngredients.getStyleClass().add("button");
        btnPerishables.getStyleClass().clear();
        btnPerishables.getStyleClass().add("button");
        btnSuppliers.getStyleClass().clear();
        btnSuppliers.getStyleClass().add("button");
        btnSchedule.getStyleClass().add("button-active");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
