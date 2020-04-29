package View;

import Entities.IngredientTest;
import Entities.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Control.Callback;
import javax.swing.*;
import java.util.Stack;

/**
 * The class is the Ingredients panel for the Cafetairé application.
 * @author Georg Grankvist, Lucas Eliasson
 * @version 1.0
 */

public class IngredientsPane extends StackPane {
    private TableView<IngredientTest> tableView;
    private TableColumn<IngredientTest, String> nameColumn;
    private TableColumn<IngredientTest, String> categoryColumn;
    private TableColumn<IngredientTest, Integer> stockColumn;
    private TableColumn<IngredientTest, String> supplierColumn;
    private TableColumn selectedColumn;
    private Callback callback;

    public IngredientsPane() {}

    public IngredientsPane (Callback callback) {
        this.callback = callback;

        /** Button instantiation and Configurations */

        Button addIngredients = new Button("ADD NEW INGREDIENT");
        addIngredients.setStyle(Styles.getButton());
        addIngredients.setPrefWidth(200);
        addIngredients.setPrefHeight(30);
        addIngredients.setOnAction(e -> {
            addNewIngredientAction();
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
        setPrefSize(1086,768);
        setStyle(Styles.getPane());


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

        tableView.setStyle(Styles.getTableRowSelected() + "-fx-background-radius: 0 0 20 20;");
        tableView.setMaxWidth(980);
        tableView.setMaxHeight(473);

        tableView.getColumns().addAll(nameColumn,categoryColumn,stockColumn,supplierColumn,selectedColumn);

        // loads in data
        tableView.setItems(getIngredientTest());


        nameColumn.setPrefWidth(196);
        categoryColumn.setPrefWidth(196);
        stockColumn.setPrefWidth(196);
        supplierColumn.setPrefWidth(195);
        selectedColumn.setPrefWidth(195);

        /** LayoutPane configurations and instantiation.
         *  VBOX, HBOX. */


        HBox mainContainer = new HBox(25);
        VBox innerContainer = new VBox(25);
        VBox mainVbox = new VBox();
        HBox topHBox = new HBox();
        HBox midHBox = new HBox();
        HBox bottomHBox = new HBox();
        HBox westHBOx = new HBox(5);
        HBox eastHBox = new HBox(5);

        getChildren().add(innerContainer);

        innerContainer.getChildren().add(mainVbox);
        innerContainer.getChildren().add(tableView);
        mainVbox.getChildren().add(topHBox);
        topHBox.getChildren().add(titleText);
        mainVbox.getChildren().add(midHBox);
        midHBox.getChildren().add(overView);
        mainVbox.getChildren().add(bottomHBox);

        bottomHBox.getChildren().addAll(westHBOx,eastHBox);
        westHBOx.getChildren().addAll(addIngredients,removeIngredients);
        eastHBox.getChildren().addAll(searchTextField ,addButton,removeButton);

        innerContainer.setAlignment(Pos.CENTER);
        mainContainer.setAlignment(Pos.CENTER);
        topHBox.setAlignment(Pos.CENTER);
        midHBox.setAlignment(Pos.CENTER);
        bottomHBox.setAlignment(Pos.CENTER);
        westHBOx.setAlignment(Pos.CENTER);
        eastHBox.setAlignment(Pos.CENTER);

        innerContainer.setStyle("-fx-background-color: #EEEEEE ; -fx-background-radius: 20 20 20 20");


        innerContainer.setMaxSize(1036,698);

        mainVbox.setPrefSize(1036,225);
        topHBox.setPrefSize(1036,75);
        midHBox.setPrefSize(1036,75);
        bottomHBox.setPrefSize(1036,75);
        westHBOx.setPrefSize(518,37.5);
        eastHBox.setPrefSize(518,37.5);

        westHBOx.setSpacing(20);
        eastHBox.setSpacing(20);
    }

    public TableView<IngredientTest> getTableView() {
        return tableView;
    }

    /**
     *
     * @param ingredient
     */
    public void addNewIngredient(IngredientTest ingredient) {
        tableView.getItems().add(ingredient);
    }

    /**
     * Adds a new ingredient from user input
     */
    public void addNewIngredientAction() {
        try {
            new newIngredientFX(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Removes selected ingredient from the stock
     */
    public void removeIngredient() {
        ObservableList<IngredientTest> ingredientTestSelected, allIngredients;
        allIngredients = tableView.getItems();
        ingredientTestSelected = tableView.getSelectionModel().getSelectedItems();
        ingredientTestSelected.forEach(allIngredients::remove);
    }

    /**
     * Increments the selected ingredients stock by 1
     */
    public void addAmount() {
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
