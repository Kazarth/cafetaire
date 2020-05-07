package View;


import Control.Callback;
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
public class AddNewIngredientPane extends AnchorPane {
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

    private int opener;
    private String orgIngredient;

    public AddNewIngredientPane(IngredientsPane source, Callback callback, int opener) {
        // init Frame
        frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Add new ingredient");
        frame.add(fxPanel);
        frame.setSize(600,400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);
        Platform.runLater(() -> fxPanel.setScene(new Scene(this)));
        this.opener = opener;


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
        categoryBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
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
        supplierBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        supplierBox.setPrefWidth(360); supplierBox.setPrefHeight(40);
        supplierBox.setLayoutX(144.0); supplierBox.setLayoutY(220);
        supplierBox.setItems(getSuppliersFromDatabase()); // testing

        // Button pane
        addButton = new Button();
        addButton.setStyle(Styles.getPopAddButton());
        addButton.setPrefWidth(200); addButton.setPrefHeight(40);
        addButton.setLayoutX(75); addButton.setLayoutY(310);

        // New ingredient
        if (opener == 0) {
            addButton.setText("ADD NEW INGREDIENT");
            addButton.setOnAction(e -> addAction());
        }
        // Edit ingredient
        if (opener == 1){
            addButton.setText("SAVE INGREDIENT");
            addButton.setOnAction(e -> editAction());
        }

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
    public void addAction() {
        try {
            IngredientTest test = null;

            String name = nameField.getText();
            String category = categoryBox.getSelectionModel().getSelectedItem();
            String supplier = supplierBox.getSelectionModel().getSelectedItem();

            test = new IngredientTest(name, category, 1, supplier);

            if (callback.addIngredientTest(test)) {
                source.addNewIngredient(test);
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
    public void editAction(){
        String name = nameField.getText();
        String category = categoryBox.getValue();
        String supplier = supplierBox.getValue();

        if (orgIngredient.equals(name)) {
            IngredientTest ingredientTest = callback.getIngredientTest(name);
            ingredientTest.setCategory(category);
            ingredientTest.setSupplier(supplier);
            source.refresh();
            close();
        } else if (!orgIngredient.equals(name)){
            IngredientTest ingredientTest = new IngredientTest(name, category, 1, supplier);
            callback.addIngredientTest(ingredientTest);
            callback.removeIngredient(orgIngredient);
            source.addNewIngredient(ingredientTest);
            source.removeIngredient();
            source.refresh();
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
        frame.dispose();
    }

    /**
     * Collects a list of suppliers from the database
     * @return list of suppliers
     */
    private ObservableList<String> getSuppliersFromDatabase() {
        ObservableList<String> listSuppliers = FXCollections.observableArrayList();
        ArrayList<Supplier> receivedSuppliers = callback.getSuppliers();

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
        return orgIngredient;
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
        nameField.setText(name);
        categoryBox.getSelectionModel().select(category);
        supplierBox.getSelectionModel().select(supplier);
    }
}
