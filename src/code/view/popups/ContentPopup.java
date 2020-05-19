package code.view.popups;

import code.control.Callback;
import code.entities.*;
import code.view.panes.IngredientsPane;
import code.view.panes.RecipeViewPane;
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
public class ContentPopup extends AnchorPane {
    private RecipeViewPane source;                     // sourcePane
    private Callback callback;                          // get logic
    private JFrame frame;
    private TextField nameField;
    private TextField amountField;
    private ComboBox<String> unitBox;  // lägg in och läs in listor som vanligt?
    private String orgIngredient;
    private ArrayList<Content> contents;

    public ContentPopup(RecipeViewPane source, Callback callback) {
        this.source = source;
        this.callback = callback;
        this.contents = source.getRecipeContent();
        getStylesheets().add("styles.css");

        // Background pane
        setMaxWidth(600); setMaxHeight(400);
        setPrefWidth(600); setPrefHeight(400);
        setStyle("-fx-background-color: #fff");

        // title pane
        Label title = new Label("EDIT INGREDIENTS");
        title.setStyle(Styles.getPopTitle());
        title.setLayoutX(162.0); title.setLayoutY(20);
        title.setPrefWidth(300); title.setPrefHeight(40);

        // Name pane
        Label nameLbl = new Label("Name");
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

        // Amount pane
        Label amountLbl = new Label("Amount");
        amountLbl.setStyle("-fx-text-fill: #000;");
        amountLbl.setPrefWidth(220.0); amountLbl.setPrefHeight(40);
        amountLbl.setLayoutX(56.0); amountLbl.setLayoutY(160);
        this.amountField = new TextField();
        this.amountField.setText(loadAmount());
        this.amountField.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        this.amountField.setPrefWidth(360.0);
        this.amountField.setPrefHeight(40);
        this.amountField.setLayoutX(144.0);
        this.amountField.setLayoutY(160);

        // Unit pane
        Label unitLbl = new Label("Unit");
        unitLbl.setStyle("-fx-text-fill: #000;");
        unitLbl.setPrefWidth(220); unitLbl.setPrefHeight(40);
        unitLbl.setLayoutX(56.0); unitLbl.setLayoutY(220);

        this.unitBox = new ComboBox<>();
        this.unitBox.setPromptText("Select unit");
        this.unitBox.setEditable(true);
        this.unitBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        this.unitBox.setPrefWidth(360);
        this.unitBox.setPrefHeight(40);
        this.unitBox.setLayoutX(144.0);
        this.unitBox.setLayoutY(220);
        this.unitBox.setItems(getUnits()); // testing

        // Button pane
        Button addButton = new Button("SAVE CHANGES");
        addButton.getStyleClass().add("greenButton");
        addButton.setPrefWidth(200); addButton.setPrefHeight(40);
        addButton.setLayoutX(75); addButton.setLayoutY(310);

        Button cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add("grayButton");
        cancelButton.setPrefWidth(200); cancelButton.setPrefHeight(40);
        cancelButton.setLayoutX(325.0); cancelButton.setLayoutY(310);
        cancelButton.setOnAction(e -> cancelAction());

        // Add all children
        getChildren().addAll(
                title,
                nameLbl,
                this.nameField,
                amountLbl,
                this.amountField,
                unitLbl,
                this.unitBox,
                addButton,
                cancelButton
        );
        initPanel();
    }

    public String loadAmount() {
        // get content row from tableView
        // get index
        // put in amount field
        return "test";
    }

    /**
     * TODO: comment
     */
    public void initPanel() {
        this.frame = new JFrame("FX");
        this.frame.setTitle("Edit ingredient");

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
     * Returns an array of units
     * @return ObservableList of units
     */
    private ObservableList<String> getUnits() {
        ObservableList<String> unitList = FXCollections.observableArrayList();
        //unitList.addAll(Arrays.asList(Units.values()));
        for (Units u: Units.values()) {
            unitList.add(u.name());
        }
        return unitList;
    }

    /**
     * On press Add button
     */
    public void addAction() {
        close();
    }

    /**
     * Method to edit an item in the tableView for IngredientPane
     * if name remain unchanged set category and supplier to value from comboBoxes
     * if name is changed remove existing name from database and add the new ingredient to the database
     */
    public void editAction() {

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
        //this.categoryBox.getSelectionModel().select(category);
        //this.supplierBox.getSelectionModel().select(supplier);
    }
}
