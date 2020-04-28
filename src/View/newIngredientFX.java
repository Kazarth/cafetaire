package View;

import Entities.Ingredient;
import Entities.IngredientTest;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class newIngredientFX implements Initializable {
    @FXML
    private TextField nameField;
    @FXML
    private ComboBox categoryBox, supplierBox; // lägg in och läs in listor som vanligt?
    @FXML
    private Button addButton, cancelButton;

    private Stage stage;

    private ArrayList<String> suppliers;

    private IngredientsPane ingredientsPane;

    @FXML
    public void addAction() {
        IngredientTest test = null;

        String name = nameField.getText();
        String category = categoryBox.getSelectionModel().getSelectedItem().toString();
        String supplier = supplierBox.getSelectionModel().getSelectedItem().toString();

        // Skapa algoritm som kollar mot databas
        // if (!exist) --> Skapa ny
        // else --> låt bli
        /*for (String s: suppliers) {
            if (!s.equals(supplier)) {

            }
        }*/

        test = new IngredientTest(name, category, 0, supplier);
        System.out.println(test.toString());

        ingredientsPane.addNewIngredient(test);

        close();
    }

    @FXML
    public void cancelAction() {
        close();
    }

    private void close() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    public void start(Stage primaryStage, IngredientsPane source) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AddNewIngredient.fxml"));

        Scene scene = new Scene(root);

        stage = primaryStage;

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

        ingredientsPane = source;

        suppliers = new ArrayList();
        suppliers.add("Lucas");
        suppliers.add("Julia");
        suppliers.add("Coca Cola");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Add values to categoryList
        categoryBox.getItems().addAll("Torrvara", "Fäskvara", "Dryck");

        // Add values to Suppliers
        supplierBox.getItems().addAll("Lucas", "Julia", "Coca Cola");
    }
}
