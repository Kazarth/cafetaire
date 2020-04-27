package View;

import Entities.Ingredient;
import Entities.IngredientTest;
import Entities.Styles;
import Entities.Supplier;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Control.Callback;

import javax.swing.*;

/**
 * The class is the Ingredients panel for the Cafetairé application.
 * @author Georg Grankvist, Lucas Eliasson
 * @version 1.0
 */


public class IngredientsPane extends BorderPane {
    private TableView<IngredientTest> tableView;
    private TableColumn<IngredientTest, String> nameColumn;
    private TableColumn<IngredientTest, String> categoryColumn;
    private TableColumn<IngredientTest, Integer> stockColumn;
    private TableColumn<IngredientTest, String> supplierColumn;
    private TableColumn selectedColumn;

    public IngredientsPane (Callback callback) {
        /** Button instantiation and Configurations */

        Button addIngredients = new Button("ADD NEW INGREDIENT");
        addIngredients.setStyle(Styles.getButton());
        addIngredients.setPrefWidth(200);
        addIngredients.setPrefHeight(30);
        addIngredients.setOnAction(e -> {
            addNewIngredient();
        });

        Button removeIngredients = new Button("REMOVE INGREDIENT");
        removeIngredients.setStyle(Styles.getButton());
        removeIngredients.setPrefHeight(30);
        removeIngredients.setPrefWidth(200);
        removeIngredients.setOnAction(e -> {
            removeIngredient();
        });

        Button addButton = new Button("ADD");
        addButton.setPrefHeight(30);
        addButton.setPrefWidth(100);
        addButton.setStyle(Styles.getButton());
        addButton.setOnAction(e -> {
            addAmount();
        });

        Button removeButton = new Button("REMOVE");
        removeButton.setPrefHeight(30);
        removeButton.setPrefWidth(100);
        removeButton.setStyle(Styles.getButton());
        removeButton.setOnAction(e -> {
            removeAmount();
        });

        /**  Title and overview text configuration */

        Text titleText = new Text("INGREDIENTS");
        Color seaDarkGreen = Color.web("#619f81");
        titleText.setFill(seaDarkGreen);
        titleText.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 40));
        Text overView = new Text("OVERVIEW");
        overView.setFont(Font.font("Segoe UI", FontWeight.LIGHT, FontPosture.REGULAR, 20));
        overView.setFill(Color.BLACK);

        /** Searchbar configuration */

        TextField searchTextField = new TextField();
        searchTextField.setPromptText("SEARCH");
        searchTextField.setPrefHeight(32);
        searchTextField.setPrefWidth(250);

        /** Ingredient table configuration and design */

        tableView = new TableView();
        setPrefSize(1068,768);
        setCenter(tableView);

        nameColumn = new TableColumn("NAME");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        categoryColumn = new TableColumn("CATEGORY");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        stockColumn = new TableColumn("STOCK");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        supplierColumn  = new TableColumn("SUPPLIER");
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));
        selectedColumn = new TableColumn("SELECTED ITEM");
        selectedColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));

        tableView.setStyle(Styles.getTableRowSelected());

        tableView.getColumns().addAll(nameColumn,categoryColumn,stockColumn,supplierColumn,selectedColumn);

        // loads in data
        tableView.setItems(getIngredientTest());

        nameColumn.setPrefWidth(200);
        categoryColumn.setPrefWidth(200);
        stockColumn.setPrefWidth(200);
        supplierColumn.setPrefWidth(200);
        selectedColumn.setPrefWidth(250);

        /** LayoutPane configurations and instantiation.
         *  VBOX, HBOX. */

        VBox mainVbox = new VBox();
        HBox topHBox = new HBox();
        HBox midHBox = new HBox();
        HBox bottomHBox = new HBox();
        HBox westHBOx = new HBox();
        HBox eastHBox = new HBox();
        setTop(mainVbox);

        mainVbox.setPrefSize(1366,225);
        topHBox.setPrefSize(1366,85);
        midHBox.setPrefSize(1366,50);
        bottomHBox.setPrefSize(1366,85);
        westHBOx.setPrefSize(683,85);
        eastHBox.setPrefSize(683,85);

        mainVbox.getChildren().add(topHBox);
        topHBox.getChildren().add(titleText);
        mainVbox.getChildren().add(midHBox);
        midHBox.getChildren().add(overView);
        mainVbox.getChildren().add(bottomHBox);
        bottomHBox.getChildren().addAll(westHBOx,eastHBox);
        westHBOx.getChildren().addAll(addIngredients,removeIngredients);
        eastHBox.getChildren().addAll(searchTextField ,addButton,removeButton);

        topHBox.setAlignment(Pos.CENTER);
        midHBox.setAlignment(Pos.CENTER);
        westHBOx.setAlignment(Pos.CENTER);
        eastHBox.setAlignment(Pos.CENTER);

        midHBox.setStyle("-fx-background-color: #21252B;");
        midHBox.setStyle("-fx-border-color: lightgray;");
        bottomHBox.setStyle("-fx-color: #619f81");

        westHBOx.setSpacing(20);
        eastHBox.setSpacing(20);
    }

    /**
     * Adds a new ingredient from user input
     */
    public void addNewIngredient() {
        System.out.println("Add new Ingredient");

        IngredientTest ingredientTest = null; // Exchange to Ingredient when merging with database --> updates to Ingredient required.

        String name = JOptionPane.showInputDialog("Enter name");
        String category = JOptionPane.showInputDialog("Enter category");
        int stock = Integer.parseInt(JOptionPane.showInputDialog("Enter stock"));
        String supplier = JOptionPane.showInputDialog("Enter supplier");

        /*
        Check against the database if supplier already exist
        if (exist) --> use
        else --> create new
         */

        ingredientTest = new IngredientTest(name, category, stock, supplier);

        // add new table row
        tableView.getItems().add(ingredientTest);

        System.out.println(ingredientTest.toString());
    }

    /**
     * Removes selected ingredient from the stock
     */
    public void removeIngredient() {
        System.out.println("Remove ingredient from stock");
        ObservableList<IngredientTest> ingredientTestSelected, allIngredients;
        allIngredients = tableView.getItems();
        ingredientTestSelected = tableView.getSelectionModel().getSelectedItems();
        ingredientTestSelected.forEach(allIngredients::remove);
    }

    /**
     * Increments the selected ingredients stock by 1
     */
    public void addAmount() {
        System.out.println("Increment the value by 1");
        ObservableList<IngredientTest> ingredientTestSelected;
        ingredientTestSelected = tableView.getSelectionModel().getSelectedItems();
        if (ingredientTestSelected.size() <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid request \nPlease choose an item first.");
        } else {
            ingredientTestSelected.get(0).increment();
            tableView.refresh();
        }
    }

    /**
     * Decrements the selected ingredients stock by 1
     */
    public void removeAmount() {
        System.out.println("Decrement the value by 1");
        ObservableList<IngredientTest> ingredientTestSelected;
        ingredientTestSelected = tableView.getSelectionModel().getSelectedItems();
        if (ingredientTestSelected.size() <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid request \nPlease choose an item first.");
        } else {
            ingredientTestSelected.get(0).decrement();
            tableView.refresh();
        }
    }

    // Test values
    private ObservableList<IngredientTest> getIngredientTest() {
        ObservableList<IngredientTest> ingredients = FXCollections.observableArrayList();
        ingredients.add(new IngredientTest("Mjöl", "Torrvaror", 15, "Lucas AB"));
        ingredients.add(new IngredientTest("Mjölk", "Dryck", 10, "Georg AB"));
        ingredients.add(new IngredientTest("Salt", "Torrvaror", 5, "Julia AB"));
        ingredients.add(new IngredientTest("Coca Cola", "Dryck", 30, "Coca Cola AB"));
        return ingredients;
    }
}
