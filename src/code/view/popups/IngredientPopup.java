package code.view.popups;

import code.control.Callback;
import code.entities.Ingredient;
import code.entities.Styles;
import code.entities.Supplier;
import code.view.panes.IngredientsPane;
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
 * @author Lucas Eliasson, Tor Stenfeldt
 * @version 1.0
 */
public class IngredientPopup extends AnchorPane {
    private IngredientsPane source;                     // sourcePane
    private Callback callback;                          // get logic

    private JFrame frame;
    private TextField nameField;
    private ComboBox<String> categoryBox, supplierBox;  // lägg in och läs in listor som vanligt?
    private String orgIngredient;

    public IngredientPopup(IngredientsPane source, Callback callback, int opener) {
        this.source = source;
        this.callback = callback;

        // Background pane
        setMaxWidth(600); setMaxHeight(400);
        setPrefWidth(600); setPrefHeight(400);
        setStyle("-fx-background-color: #fff");

        // title pane
        Label title = new Label("ADD NEW INGREDIENT");
        title.setStyle(Styles.getPopTitle());
        title.setLayoutX(162.0); title.setLayoutY(20);
        title.setPrefWidth(300); title.setPrefHeight(40);

        // Name pane
        Label nameLbl = new Label("Enter name");
        nameLbl.setStyle("-fx-text-fill: #000;");
        nameLbl.setPrefWidth(220); nameLbl.setPrefHeight(40);
        nameLbl.setLayoutX(56.0); nameLbl.setLayoutY(100);

        this.nameField = new TextField();
        this.nameField.setPromptText("Enter name");
        this.nameField.setStyle(Styles.getPopField());
        this.nameField.setPrefWidth(360);
        this.nameField.setPrefHeight(40);
        this.nameField.setLayoutX(144.0);
        this.nameField.setLayoutY(100);

        // Category pane
        Label categoryLbl = new Label("Category");
        categoryLbl.setStyle("-fx-text-fill: #000;");
        categoryLbl.setPrefWidth(220.0); categoryLbl.setPrefHeight(40);
        categoryLbl.setLayoutX(56.0); categoryLbl.setLayoutY(160);

        this.categoryBox = new ComboBox<>();
        this.categoryBox.setPromptText("Select category");
        this.categoryBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        this.categoryBox.setPrefWidth(360.0);
        this.categoryBox.setPrefHeight(40);
        this.categoryBox.setLayoutX(144.0);
        this.categoryBox.setLayoutY(160);
        this.categoryBox.setItems(getCategories()); // testing

        // Supplier pane
        Label supplierLbl = new Label("Supplier");
        supplierLbl.setStyle("-fx-text-fill: #000;");
        supplierLbl.setPrefWidth(220); supplierLbl.setPrefHeight(40);
        supplierLbl.setLayoutX(56.0); supplierLbl.setLayoutY(220);

        this.supplierBox = new ComboBox<>();
        this.supplierBox.setPromptText("Enter supplier");
        this.supplierBox.setEditable(true);
        this.supplierBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        this.supplierBox.setPrefWidth(360);
        this.supplierBox.setPrefHeight(40);
        this.supplierBox.setLayoutX(144.0);
        this.supplierBox.setLayoutY(220);
        this.supplierBox.setItems(getSuppliersFromDatabase()); // testing

        // Button pane
        Button addButton = new Button();
        addButton.setStyle(Styles.getPopAddButton());
        addButton.setPrefWidth(200); addButton.setPrefHeight(40);
        addButton.setLayoutX(75); addButton.setLayoutY(310);

        // New ingredient
        if (opener == 0) {
            addButton.setText("ADD NEW INGREDIENT");
            addButton.setOnAction(e -> addAction());
        }
        // Edit ingredient
        if (opener == 1) {
            addButton.setText("SAVE INGREDIENT");
            addButton.setOnAction(e -> editAction());
        }

        Button cancelButton = new Button("CANCEL");
        cancelButton.setStyle(Styles.getPopCancelButton());
        cancelButton.setPrefWidth(200); cancelButton.setPrefHeight(40);
        cancelButton.setLayoutX(325.0); cancelButton.setLayoutY(310);
        cancelButton.setOnAction(e -> cancelAction());

        // Add all children
        getChildren().addAll(
                title,
                nameLbl,
                this.nameField,
                categoryLbl,
                this.categoryBox,
                supplierLbl,
                this.supplierBox,
                addButton,
                cancelButton
        );

        initPanel();
    }

    /**
     * TODO: comment
     */
    public void initPanel() {
        this.frame = new JFrame("FX");
        this.frame.setTitle("Add new ingredient");

        final JFXPanel fxPanel = new JFXPanel();
        this.frame.add(fxPanel);

        this.frame.setSize(600,400);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setUndecorated(true);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

        Platform.runLater(() -> fxPanel.setScene(new Scene(this)));
    }
    
    /**
     * On press Add button
     */
    public void addAction() {
        try {
            Ingredient ingredient;

            String name = this.nameField.getText();
            String category = this.categoryBox.getSelectionModel().getSelectedItem();
            String supplierName = this.supplierBox.getSelectionModel().getSelectedItem();
            Supplier supplier = this.callback.getSupplier(supplierName);

            ingredient = new Ingredient(name, category, 1, supplier);

            if (this.callback.addIngredient(ingredient)) {
                this.source.addNewIngredient(ingredient);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please erase search-field to see updates");
        }
        close();
    }

    /**
     * Method to edit an item in the tableView for IngredientPane
     * if name remain unchanged set category and supplier to value from comboBoxes
     * if name is changed remove existing name from database and add the new ingredient to the database
     */
    public void editAction() {
        String name = this.nameField.getText();
        String category = this.categoryBox.getValue();
        String supplierName = this.supplierBox.getValue();
        Supplier supplier = this.callback.getSupplier(supplierName);

        if (this.orgIngredient.equals(name)) {
            Ingredient ingredient = this.callback.getIngredient(name);
            ingredient.setCategory(category);
            ingredient.setSupplier(supplier);
            this.source.refresh();
            close();
        } else {
            Ingredient ingredientTest = new Ingredient(name, category, 1, supplier);
            this.callback.addIngredient(ingredientTest);
            this.callback.removeIngredient(orgIngredient);
            this.source.addNewIngredient(ingredientTest);
            this.source.removeIngredient();
            this.source.refresh();
            close();
        }
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
        this.frame.dispose();
    }

    /**
     * Collects a list of suppliers from the database
     * @return list of suppliers
     */
    private ObservableList<String> getSuppliersFromDatabase() {
        ObservableList<String> listSuppliers = FXCollections.observableArrayList();
        ArrayList<Supplier> receivedSuppliers = this.callback.getSuppliers();

        for (Supplier supplier: receivedSuppliers) {
            listSuppliers.add(supplier.getName());
        }
        return listSuppliers;
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

    public String getOrgIngredient() {
        return this.orgIngredient;
    }

    public void setOrgIngredient(String orgIngredient) {
        this.orgIngredient = orgIngredient;
    }

    /**
     * @param name initial value for item to be edited
     * @param category initial value for item to be edited
     * @param supplier initial value for item to be edited
     */
    public void setValuesForIngredient(String name, String category, String supplier){
        this.nameField.setText(name);
        this.categoryBox.getSelectionModel().select(category);
        this.supplierBox.getSelectionModel().select(supplier);
    }
}
