package View;


import Entities.IngredientTest;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;
import java.util.ArrayList;

/**
 * The class presents a OK or CANCEL pane.
 * @author Lucas Eliasson
 * @version 1.0
 */
public class newIngredientFX extends AnchorPane {
    private JFrame frame;
    private Label title;
    private Label nameLbl;
    private TextField nameField;
    private Label categoryLbl, supplierLbl;
    private ComboBox<String> categoryBox, supplierBox; // lägg in och läs in listor som vanligt?
    private Button addButton, cancelButton;
    private IngredientsPane source; // sourcePane
    private ArrayList<String> suppliers; // test purposes

    public newIngredientFX(IngredientsPane source) {
        // init Frame
        frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Add new Ingredient");
        frame.add(fxPanel);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);
        Platform.runLater(() -> fxPanel.setScene(new Scene(this)));

        // Init source
        this.source = source;

        // test values
        suppliers = new ArrayList();
        suppliers.add("Lucas");
        suppliers.add("Julia");
        suppliers.add("Coca Cola");

        // Background pane
        setMaxWidth(600); setMaxHeight(400);
        setPrefWidth(600); setPrefHeight(400);
        setStyle(
                "-fx-background-color: #fff"
                );

        // title pane
        title = new Label("ADD NEW INGREDIENT");
        title.setStyle(
                "-fx-text-fill: #619F81;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 25px"
        );
        title.setLayoutX(162.0); title.setLayoutY(20);
        title.setPrefWidth(300); title.setPrefHeight(40);

        // Name pane
        nameLbl = new Label("Enter name");
        nameLbl.setStyle(
                "-fx-text-fill: #000;"
        );
        nameLbl.setPrefWidth(220); nameLbl.setPrefHeight(40);
        nameLbl.setLayoutX(56.0); nameLbl.setLayoutY(100);

        nameField = new TextField();
        nameField.setPromptText("Enter name");
        nameField.setStyle(
                "-fx-background-color: #fff;" +
                "-fx-border-width: 1;" +
                "-fx-border-color: #000;");
        nameField.setPrefWidth(360); nameField.setPrefHeight(40);
        nameField.setLayoutX(144.0); nameField.setLayoutY(100);

        // Category pane
        categoryLbl = new Label("Category");
        categoryLbl.setStyle(
                "-fx-text-fill: #000;");
        categoryLbl.setPrefWidth(220.0); categoryLbl.setPrefHeight(40);
        categoryLbl.setLayoutX(56.0); categoryLbl.setLayoutY(160);

        categoryBox = new ComboBox();
        categoryBox.setPromptText("Select category");
        categoryBox.setStyle(
                "-fx-background-color: #fff;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-color: #000;");
        categoryBox.setPrefWidth(360.0); categoryBox.setPrefHeight(40);
        categoryBox.setLayoutX(144.0); categoryBox.setLayoutY(160);
        categoryBox.setItems(getCategories()); // testing

        // Supplier pane
        supplierLbl = new Label("Supplier");
        supplierLbl.setStyle(
                "-fx-text-fill: #000;");
        supplierLbl.setPrefWidth(220); supplierLbl.setPrefHeight(40);
        supplierLbl.setLayoutX(56.0); supplierLbl.setLayoutY(220);

        supplierBox = new ComboBox<>();
        supplierBox.setPromptText("Enter supplier");
        supplierBox.setEditable(true);
        supplierBox.setStyle(
                "-fx-background-color: #fff;" +
                        "-fx-border-width: 1;" +
                        "-fx-border-color: #000;");
        supplierBox.setPrefWidth(360); supplierBox.setPrefHeight(40);
        supplierBox.setLayoutX(144.0); supplierBox.setLayoutY(220);
        supplierBox.setItems(getSuppliers()); // testing

        // Button pane
        addButton = new Button("ADD NEW INGREDIENT");
        addButton.setStyle(
                "-fx-background-color: #619F81;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 40;" +
                "-fx-text-fill: #fff;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: Bold;");
        addButton.setPrefWidth(200); addButton.setPrefHeight(40);
        addButton.setLayoutX(75); addButton.setLayoutY(310);
        addButton.setOnAction(e -> addAction());

        cancelButton = new Button("CANCEL");
        cancelButton.setStyle(
                "-fx-background-color: #ddd;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-radius: 40;" +
                        "-fx-text-fill: #fff;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: Bold;");
        cancelButton.setPrefWidth(200); cancelButton.setPrefHeight(40);
        cancelButton.setLayoutX(325.0); cancelButton.setLayoutY(310);
        cancelButton.setOnAction(e -> cancelAction());

        // Add all children
        getChildren().addAll(title, nameLbl, nameField, categoryLbl, categoryBox, supplierLbl, supplierBox, addButton, cancelButton);
    }

    /**
     * On press Add button
     */
    public void addAction() {
        IngredientTest test = null;

        String name = nameField.getText();
        String category = categoryBox.getSelectionModel().getSelectedItem();
        String supplier = supplierBox.getSelectionModel().getSelectedItem();

        // Skapa algoritm som kollar mot databas
        // if (!exist) --> Skapa ny
        // else --> låt bli
        /*for (String s: suppliers) {
            if (!s.equals(supplier)) {

            }
        }*/

        test = new IngredientTest(name, category, 0, supplier);

        source.addNewIngredient(test);

        close();
    }

    /**
     * On Cancel button
     */
    public void cancelAction() {
        close();
    }

    /**
     * Close the frame
     */
    private void close() {
        frame.dispose();
    }

    /**
     * For testing purposes
     * @return
     */
    private ObservableList<String> getSuppliers() {
        ObservableList<String> ingredients = FXCollections.observableArrayList();
        ingredients.add("Lucas AB");
        ingredients.add("Georg Inc");
        ingredients.add("Paul M");
        ingredients.add("Coca Cola");
        return ingredients;
    }

    /**
     * For testing purposes
     * @return
     */
    private ObservableList<String> getCategories() {
        ObservableList<String> ingredients = FXCollections.observableArrayList();
        ingredients.add("Dry Food");
        ingredients.add("Fresh Food");
        ingredients.add("Drink");
        return ingredients;
    }
}
