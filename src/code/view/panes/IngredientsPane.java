package code.view.panes;

import code.entities.Ingredient;
import code.entities.Styles;
import code.view.popups.IngredientPopup;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import code.control.Callback;
import javax.swing.*;
import java.util.Arrays;

/**
 * The class is the Ingredients panel for the Cafetairé application.
 * @author Tor Stenfeldt, Georg Grankvist, Lucas Eliasson
 * @version 1.0
 */
public class IngredientsPane extends StackPane {
    private TableView<Ingredient> tableView;
    private TableColumn<Ingredient, String> nameColumn;
    private TableColumn<Ingredient, String> categoryColumn;
    private TableColumn<Ingredient, Integer> stockColumn;
    private TableColumn<Ingredient, String> supplierColumn;

    private TableColumn<Ingredient, Boolean> selectedColumn;
    private TextField searchTextField;
    private Callback callback;

    private HBox mainContainer;
    private VBox innerContainer;
    private VBox mainVbox;
    private HBox topHBox;
    private HBox midHBox;
    private HBox bottomHBox;
    private HBox westHBOx;
    private HBox eastHBox;

    public IngredientsPane (Callback callback) {
        this.callback = callback;

        // Button instantiation and Configurations
        Button addIngredients = new Button("ADD NEW INGREDIENT");
        addIngredients.setStyle(Styles.getButton());
        addIngredients.setPrefWidth(200);
        addIngredients.setPrefHeight(30);
        addIngredients.setOnAction(e -> addNewIngredientAction());

        Button removeIngredients = new Button("REMOVE INGREDIENT");
        removeIngredients.setStyle(Styles.getButton());
        removeIngredients.setPrefHeight(30);
        removeIngredients.setPrefWidth(200);
        removeIngredients.setOnAction(e -> removeIngredient());

        Button editIngredient = new Button("EDIT");
        editIngredient.setStyle(Styles.getButton());
        editIngredient.setOnAction(e -> {
            editAction();
        });

        Button addButton = new Button("ADD");
        addButton.setPrefHeight(30);
        addButton.setPrefWidth(100);
        addButton.setStyle(Styles.getButton());
        addButton.setOnAction(e -> addAmount());

        Button removeButton = new Button("REMOVE");
        removeButton.setPrefHeight(30);
        removeButton.setPrefWidth(100);
        removeButton.setStyle(Styles.getButton());
        removeButton.setOnAction(e -> removeAmount());

        Text textTitle = new Text();
        Font MenuTitle = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        textTitle.setFill(Paint.valueOf("#619f81"));
        textTitle.setFont(MenuTitle);
        textTitle.setText("INGREDIENTS");

        Text overView = new Text("OVERVIEW");
        overView.setFont(Font.font("Segoe UI", FontWeight.LIGHT, FontPosture.REGULAR, 20));
        overView.setFill(Color.BLACK);

        // Searchbar configuration
        searchTextField = new TextField();
        searchTextField.setPromptText("SEARCH");
        searchTextField.setPrefHeight(32);
        searchTextField.setPrefWidth(150);

        // Ingredient table configuration and design
        tableView = new TableView<>();
        setPrefSize(1086,768);
        setStyle(Styles.getPane());

        searchTextField.textProperty().addListener(this::searchRecord);
      
        nameColumn = new TableColumn<>("NAME");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        categoryColumn = new TableColumn<>("CATEGORY");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        stockColumn = new TableColumn<>("STOCK");
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        supplierColumn  = new TableColumn<>("SUPPLIER");
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        selectedColumn = new TableColumn<>("SELECTED ITEM");
        //selectedColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        CheckBox checkBox = new CheckBox();
        checkBox.setDisable(true);
        checkBox.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (isPressed()) {
                checkBox.setSelected(true);
            }
        });

        tableView.setStyle(Styles.getTableRowSelected() + "-fx-background-radius: 0 0 20 20;");
        tableView.setMaxWidth(980);
        tableView.setMaxHeight(473);

        tableView.getColumns().addAll(nameColumn,categoryColumn,stockColumn,supplierColumn,selectedColumn);

        // loads in data
        tableView.setItems(getIngredient());
        nameColumn.setPrefWidth(196);
        categoryColumn.setPrefWidth(196);
        stockColumn.setPrefWidth(196);
        supplierColumn.setPrefWidth(195);
        selectedColumn.setPrefWidth(195);

        /* LayoutPane configurations and instantiation.
         *  VBOX, HBOX. */
        mainContainer = new HBox(25);
        innerContainer = new VBox(25);
        mainVbox = new VBox();
        topHBox = new HBox();
        midHBox = new HBox();
        bottomHBox = new HBox();
        westHBOx = new HBox(5);
        eastHBox = new HBox(5);

        getChildren().add(innerContainer);

        innerContainer.getChildren().add(mainVbox);
        innerContainer.getChildren().add(tableView);
        mainVbox.getChildren().add(topHBox);
        topHBox.getChildren().add(textTitle);
        mainVbox.getChildren().add(midHBox);
        midHBox.getChildren().add(overView);
        mainVbox.getChildren().add(bottomHBox);

        bottomHBox.getChildren().addAll(westHBOx,eastHBox);
        westHBOx.getChildren().addAll(addIngredients,removeIngredients, editIngredient);
        eastHBox.getChildren().addAll(searchTextField ,addButton,removeButton);

        innerContainer.setAlignment(Pos.CENTER);
        mainContainer.setAlignment(Pos.CENTER);
        topHBox.setAlignment(Pos.CENTER);
        midHBox.setAlignment(Pos.CENTER);
        bottomHBox.setAlignment(Pos.CENTER);
        westHBOx.setAlignment(Pos.CENTER);
        eastHBox.setAlignment(Pos.CENTER);

        innerContainer.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 20 20"
        );
        midHBox.setStyle(
                "-fx-border-color: #6B6C6A;" +
                "-fx-background-color: #FFFFFF"
        );
        innerContainer.setMaxSize(1036,698);

        mainVbox.setPrefSize(1036,225);
        topHBox.setPrefSize(1036,75);
        midHBox.setPrefSize(1036,40);
        bottomHBox.setPrefSize(1036,75);
        westHBOx.setPrefSize(550,37.5);
        eastHBox.setPrefSize(485,37.5);

        westHBOx.setSpacing(20);
        eastHBox.setSpacing(20);
    }

    /**
     * @param ingredient
     */
    public void addNewIngredient(Ingredient ingredient) {
        tableView.getItems().add(ingredient);
    }

    /**
     * Adds a new ingredient from user input
     */
    public void addNewIngredientAction() {
        try {
            new IngredientPopup(this, callback, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to edit an item in the tableView
     */
    public void editAction() {
        String name = tableView.getSelectionModel().getSelectedItem().getType();
        IngredientPopup pane;

        if (name != null) {
            try {
                pane = new IngredientPopup(this, callback, 1);
                Ingredient ingredient = callback.getIngredient(name);
                pane.setOrgIngredient(name);

                if (ingredient.getSupplier() == null) {
                    pane.setValuesForIngredient(ingredient.getType(), ingredient.getCategory(), "");
                } else {
                    pane.setValuesForIngredient(ingredient.getType(), ingredient.getCategory(), ingredient.getSupplier().getName());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Removes selected ingredient from the stock
     */
    public void removeIngredient() {
        ObservableList<Ingredient> ingredientSelected, allIngredients;
        allIngredients = tableView.getItems();
        ingredientSelected = tableView.getSelectionModel().getSelectedItems();
        code.entities.Ingredient ingredient = tableView.getSelectionModel().getSelectedItem();
        ingredientSelected.forEach(allIngredients::remove);

        callback.removeIngredient(ingredient.getType());
    }

    /**
     * Removes selected ingredient from the stock
     */
    public void removeIngredient(Ingredient ingredient) {
        ObservableList<Ingredient> ingredientSelected, allIngredients;
        allIngredients = tableView.getItems();
        ingredientSelected = tableView.getSelectionModel().getSelectedItems();
        ingredientSelected.forEach(allIngredients::remove);
    }

    /**
     * Increments the selected ingredients stock by 1
     */
    public void addAmount() {
        ObservableList<Ingredient> ingredientSelected;
        ingredientSelected = tableView.getSelectionModel().getSelectedItems();
        if (ingredientSelected.size() <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid request \nPlease choose an item first.");
        } else {
            if (callback.incrementIngredient(ingredientSelected.get(0))) {
                ingredientSelected.get(0).increment(); // increment for view
                System.out.println("Completed increase in View");
            }
            tableView.refresh();
        }
    }

    /**
     * Decrements the selected ingredients stock by 1
     */
    public void removeAmount() {
        ObservableList<Ingredient> ingredientSelected;
        ingredientSelected = tableView.getSelectionModel().getSelectedItems();
        if (ingredientSelected.size() <= 0) {
            JOptionPane.showMessageDialog(null, "Invalid request \nPlease choose an item first.");
        } else {
            if (callback.decrementIngredient(ingredientSelected.get(0))) {
                ingredientSelected.get(0).decrement(); // decrement for view
                System.out.println("Completed decrease in View");
            }
            tableView.refresh();
        }
    }

    /**
     * When the menu contracts the Pane will extend
     */
    public void expand() {
        setPrefWidth(1346);
        innerContainer.setMaxSize(1180,698);
        tableView.setMaxWidth(1124);
        nameColumn.setPrefWidth(225);
        categoryColumn.setPrefWidth(225);
        stockColumn.setPrefWidth(224);
        supplierColumn.setPrefWidth(224);
        selectedColumn.setPrefWidth(224);
        //bottomHBox.setPrefSize(1180,75); // not working
    }

    /**
     * When the menu expands the Pane will narrow
     */
    public void contract() {
        setPrefWidth(1086);
        innerContainer.setMaxSize(1036,698);
        tableView.setMaxWidth(980);
        nameColumn.setPrefWidth(196);
        categoryColumn.setPrefWidth(196);
        stockColumn.setPrefWidth(196);
        supplierColumn.setPrefWidth(195);
        selectedColumn.setPrefWidth(195);
        //bottomHBox.setPrefSize(1036,75); // not working
    }

    /**
    * Searchbar functionality. (NEEDS REVISION).
    */
   private void searchRecord(Observable observable, String oldValue, String newValue) {
       if (!searchTextField.getText().equals("")) {
           FilteredList<Ingredient> filteredList = new FilteredList<>(getIngredient(), p -> true);
           filteredList.setPredicate(tableView -> {

               if (newValue == null || newValue.isEmpty()) {
                   return true;
               }

               String typedText = newValue.toLowerCase();

               if (tableView.getType().toLowerCase().contains(typedText)) {
                   return true;
               }

               if (tableView.getSupplier().getName().toLowerCase().contains(typedText)) {
                   return true;
               }

               if (String.valueOf(tableView.getStock()).toLowerCase().contains(typedText)) {
                   return true;
               }

               return false;
           });

           SortedList<Ingredient> sortedList = new SortedList<>(filteredList);
           sortedList.comparatorProperty().bind(tableView.comparatorProperty());
           tableView.setItems(sortedList);
       } else {
           tableView.setItems(getIngredient());
       }
   }

   private ObservableList<Ingredient> getIngredient() {
       ObservableList <Ingredient> ingredients = FXCollections.observableArrayList();
       Ingredient[] receivedIngredients = callback.getIngredients();
       ingredients.addAll(Arrays.asList(receivedIngredients));
       return ingredients;
   }

    /**
     * Refreshes the tableView
     */
    public void refresh(){
        tableView.refresh();
    }
}
