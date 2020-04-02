package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SupplierPanel extends Application {

    private Button  btnAddNewIng;
    private Button  btnRemoveIng;


    @Override
    public void start(Stage supplierStage) throws Exception {
        supplierStage.setTitle("Suppliers");
        btnAddNewIng    =   new Button("ADD NEW INGREDIENT");
        btnRemoveIng    =   new Button("REMOVE INGREDIENT");

        StackPane layout =   new StackPane();
        layout.getChildren().add(btnAddNewIng);
        layout.getChildren().add(btnRemoveIng);

        Scene supplierScene =   new Scene(layout, 300, 250);
        supplierStage.setScene(supplierScene);
        supplierStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
