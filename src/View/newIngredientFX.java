package View;


import Control.Callback;
import Entities.Ingredient;
import Entities.IngredientTest;
import Entities.Styles;
import Entities.Supplier;
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
    private Callback callback; // get logic
    private ArrayList<String> suppliers; // test purposes

    public newIngredientFX(IngredientsPane source, Callback callback) {
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
        this.callback = callback;

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
        title.setStyle(Styles.getPopTitle());
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
        nameField.setStyle(Styles.getPopField());
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
        categoryBox.setStyle(Styles.getPopField());
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
        supplierBox.setStyle(Styles.getPopField());
        supplierBox.setPrefWidth(360); supplierBox.setPrefHeight(40);
        supplierBox.setLayoutX(144.0); supplierBox.setLayoutY(220);
        supplierBox.setItems(getSuppliers()); // testing

        // Button pane
        addButton = new Button("ADD NEW INGREDIENT");
        addButton.setStyle(Styles.getPopAddButton());
        addButton.setPrefWidth(200); addButton.setPrefHeight(40);
        addButton.setLayoutX(75); addButton.setLayoutY(310);
        addButton.setOnAction(e -> addAction());

        cancelButton = new Button("CANCEL");
        cancelButton.setStyle(Styles.getPopCancelButton());
        cancelButton.setPrefWidth(200); cancelButton.setPrefHeight(40);
        cancelButton.setLayoutX(325.0); cancelButton.setLayoutY(310);
        cancelButton.setOnAction(e -> cancelAction());

        // Add all children
        getChildren().addAll(title, nameLbl, nameField, categoryLbl, categoryBox, supplierLbl, supplierBox, addButton, cancelButton);
    }

    /**
     * On press Add button
     */
    public void addActionCallback() {
        /*Ingredient ingredient = null;
        Supplier newSupplier = null;

        String name = nameField.getText();
        String category = categoryBox.getSelectionModel().getSelectedItem();
        String supplier = supplierBox.getSelectionModel().getSelectedItem();

        if (!callback.addSupplier(supplier)) {
            callback.getSupplier(supplier);
        } else {
            //-- Visa ny nuta --//
            // -- Samla in info --//
            newSupplier = new Supplier("name");
            newSupplier.setCategory(category);
            callback.addSupplier(newSupplier);
        }

        ingredient = new Ingredient(name, newSupplier);
        if (callback.addIngredient(ingredient)) {
            source.addNewIngredient(ingredient);
        } else {
            JOptionPane.showMessageDialog(null, "Error, please input correctly");
        }*/
    }
    
    /**
     * On press Add button
     */
    public void addAction() {
        IngredientTest test = null;

        String name = nameField.getText();
        String category = categoryBox.getSelectionModel().getSelectedItem();
        String supplier = supplierBox.getSelectionModel().getSelectedItem();

        test = new IngredientTest(name, category, 0, supplier);

        if (callback.addIngredientTest(test)) {
            source.addNewIngredient(test);
        }

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
