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
        this.nameField.setEditable(false);
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
        Button save_Button = new Button("SAVE CHANGES");
        save_Button.getStyleClass().add("greenButton");
        save_Button.setPrefWidth(200); save_Button.setPrefHeight(40);
        save_Button.setLayoutX(75); save_Button.setLayoutY(310);
        save_Button.setOnAction(e -> saveAction());

        Button cancelButton = new Button("CANCEL");
        cancelButton.getStyleClass().add("grayButton");
        cancelButton.setPrefWidth(200); cancelButton.setPrefHeight(40);
        cancelButton.setLayoutX(325.0); cancelButton.setLayoutY(310);
        cancelButton.setOnAction(e -> cancelAction());

        // load data
        Content content;
        content = source.getSelectedContent();
        setValuesForIngredient(content.getIngredient().getType(), content.getValue(), content.getUnit());

        // Add all children
        getChildren().addAll(
                title,
                nameLbl,
                this.nameField,
                amountLbl,
                this.amountField,
                unitLbl,
                this.unitBox,
                save_Button,
                cancelButton
        );
        initPanel();
    }

    /**
     * @param name initial value for item to be edited
     * @param amount initial value for item to be edited
     * @param unit initial value for item to be edited
     */
    public void setValuesForIngredient(String name, double amount, Units unit){
        this.nameField.setText(name);
        this.amountField.setText(String.valueOf(amount));
        this.unitBox.getSelectionModel().select(String.valueOf(unit));
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
     * Method to edit an item in the tableView for IngredientPane
     * if name remain unchanged set category and supplier to value from comboBoxes
     * if name is changed remove existing name from database and add the new ingredient to the database
     */
    public void saveAction() {
        try {
            String name = this.nameField.getText();
            Ingredient ingredient = callback.getIngredient(name);
            double amount = Double.parseDouble(this.amountField.getText());
            Units unit = Units.valueOf(unitBox.getSelectionModel().getSelectedItem());
            Recipe recipe = this.source.getRecipe();
            recipe.getContent(name).setValue(amount);
            recipe.getContent(name).setUnit(unit);
            source.refresh();
            close();
        } catch (Exception e) {
            e.printStackTrace();
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
}
