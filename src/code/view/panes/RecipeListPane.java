package code.view.panes;

import code.control.Callback;
import code.entities.Recipe;
import code.entities.RecipePanes;
import code.entities.Styles;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * RecipePane.java
 * Presents the user with an overview of what recipes are stored.
 * @author Lucas Eliasson, Paul Moustakas
 * @version 1.0
 */
public class RecipeListPane extends Pane implements EnhancedPane {
    private VBox vBox_mainContainer;
    private HBox hBox_titleContainer;
    private HBox hBox_filter;
    private HBox hBox_search;
    private HBox hBox_menu;
    private FlowPane flowPane_tableContainer;

    private Callback callback;
    private TextField textField_search;
    private TableView<Recipe> recipeTable;
    private TableColumn<Recipe, String> tableColumn_tableName;
    private TableColumn<Recipe, String> tableColumn_tableCategory;
    private RecipePane pane;

    public RecipeListPane(Callback callback, RecipePane pane) {
        this.callback = callback;
        this.pane = pane;

        initTitle();
        initFiller();
        initMenu();
        initView();

        this.vBox_mainContainer = new VBox();
        this.vBox_mainContainer.setPrefSize(1014,695);
        this.vBox_mainContainer.setStyle(
                "-fx-background-color: #fff;" +
                "-fx-background-radius: 20"
        );
        this.vBox_mainContainer.setLayoutX(20);
        this.vBox_mainContainer.setLayoutY(20);
        this.vBox_mainContainer.getChildren().addAll(this.hBox_titleContainer, this.hBox_filter, this.hBox_menu, this.flowPane_tableContainer);

        this.setStyle(Styles.getPane());
        this.setPrefSize(1054, 736);
        this.getChildren().add(this.vBox_mainContainer);
    }

    private void initTitle() {
        Text title = new Text("RECIPES");
        title.setStyle(Styles.getTitle());
        title.setFill(Paint.valueOf("#619f81"));

        /* Title */
        this.hBox_titleContainer = new HBox(title);
        this.hBox_titleContainer.setMinSize(1014, 70);
        this.hBox_titleContainer.setPrefSize(1160, 70);
        this.hBox_titleContainer.setAlignment(Pos.CENTER);
        this.hBox_titleContainer.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 0 0;"
        );
    }

    private void initFiller() {
        this.hBox_filter = new HBox();
        this.hBox_filter.setPrefSize(1160,35);
        this.hBox_filter.setMinHeight(35);
        this.hBox_filter.setMaxHeight(35);
        this.hBox_filter.setStyle(
                "-fx-border-color: #6B6C6A;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-width: 1 0 1 0"
        );
    }

    private void initMenu() {
        Button addButton = new Button("CREATE RECIPE");
        addButton.setMinWidth(170);
        addButton.setPrefHeight(40);
        addButton.getStyleClass().add("greenButtonPanel");
        addButton.setOnAction(e -> createAddView());

        Button deleteButton = new Button("DELETE RECIPE");
        deleteButton.setMinWidth(170);
        deleteButton.setPrefHeight(40);
        deleteButton.getStyleClass().add("greenButtonPanel");
        deleteButton.setOnAction(e -> deleteRecipe());

        Button viewButton = new Button("VIEW RECIPE");
        viewButton.setMinWidth(170);
        viewButton.setPrefHeight(40);
        viewButton.getStyleClass().add("greenButtonPanel");
        viewButton.setOnAction(e -> viewRecipe());

        /* Buttons and search */
        HBox buttonBox = new HBox(10, addButton, deleteButton, viewButton);
        buttonBox.setPrefSize(580, 75);
        buttonBox.setMinSize(580, 75);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-padding: 0 50 0 50"
        );

        this.textField_search = new TextField();
        this.textField_search.setPrefSize(290,40);
        this.textField_search.setPromptText("SEARCH");
        this.textField_search.textProperty().addListener(this :: searchRecord);

        Button searchButton = new Button();
        searchButton.getStyleClass().add("greenButtonPanel");
        searchButton.setPrefSize(40,40);
        searchButton.setOnAction(e -> search());

        try {
            Image selectedImage = new Image(new FileInputStream("src/resources/search.png"));
            ImageView selectedView = new ImageView(selectedImage);
            selectedView.setFitWidth(20);
            selectedView.setFitHeight(20);
            searchButton.setGraphic(selectedView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.hBox_search = new HBox(10, this.textField_search, searchButton);
        this.hBox_search.setMinSize(380, 75);
        this.hBox_search.setPrefSize(434, 75);
        this.hBox_search.setAlignment(Pos.CENTER_RIGHT);
        this.hBox_search.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-padding: 0 50 0 50;"
        );

        this.hBox_menu = new HBox(buttonBox, this.hBox_search);
        this.hBox_menu.setPrefSize(1014,75);
        this.hBox_menu.setAlignment(Pos.BOTTOM_CENTER);
    }

    private void initView() {
        this.tableColumn_tableName = new TableColumn<>("Name");
        this.tableColumn_tableName.setStyle(Styles.getTableColumn());
        this.tableColumn_tableName.getStyleClass().add("name-column");
        this.tableColumn_tableName.setPrefWidth(457);
        this.tableColumn_tableName.setResizable(false);
        this.tableColumn_tableName.setCellValueFactory(new PropertyValueFactory<>("name"));

        this.tableColumn_tableCategory = new TableColumn<>("Category");
        this.tableColumn_tableCategory.setStyle(Styles.getTableColumn());
        this.tableColumn_tableCategory.getStyleClass().add("name-column");
        this.tableColumn_tableCategory.setPrefWidth(457);
        this.tableColumn_tableCategory.setResizable(false);
        this.tableColumn_tableCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        this.recipeTable = new TableView<>();
        this.recipeTable.setPrefSize(916,465);
        this.recipeTable.getStyleClass().add("view");
        this.recipeTable.getColumns().addAll(this.tableColumn_tableName, this.tableColumn_tableCategory);
        this.recipeTable.setItems(getRecipes());

        this.flowPane_tableContainer = new FlowPane();
        this.flowPane_tableContainer.setPrefSize(1014, 505);
        this.flowPane_tableContainer.setAlignment(Pos.CENTER);
        this.flowPane_tableContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.flowPane_tableContainer.setStyle(
                "-fx-alignment: center;" +
                "-fx-background-color: #fff;" +
                "-fx-background-radius: 0 0 20 20;" +
                "-fx-padding: 0 0 50 0;"
        );
        this.flowPane_tableContainer.getChildren().add(this.recipeTable);
    }

    public void expand() {
        this.setPrefWidth(1200);
        this.vBox_mainContainer.setPrefWidth(1160);

        this.hBox_menu.setPrefWidth(1160);
        this.hBox_search.setPrefWidth(580);

        this.flowPane_tableContainer.setPrefWidth(1160);
        this.recipeTable.setPrefWidth(1062);
        this.tableColumn_tableName.setPrefWidth(530);
        this.tableColumn_tableCategory.setPrefWidth(530);
    }

    public void contract() {
        this.setPrefWidth(1054);
        this.vBox_mainContainer.setPrefWidth(1014);

        this.hBox_menu.setPrefWidth(1014);
        this.hBox_search.setPrefWidth(434);

        this.flowPane_tableContainer.setPrefWidth(1014);
        this.recipeTable.setPrefWidth(916);
        this.tableColumn_tableName.setPrefWidth(457);
        this.tableColumn_tableCategory.setPrefWidth(457);
    }

    /**
     * Adds a new recipe
     * @param recipe
     */
    public void addNewRecipe(Recipe recipe) {
        recipeTable.getItems().add(recipe);
    }

    /**
     * Opens the recipe-view
     */
    private void createAddView() {
        pane.setView(RecipePanes.RecipeAddNewPane);
    }

    /**
     * Deletes active recipe from View pane
     */
    public void deleteRecipe(Recipe recipe) {
        if (callback.removeRecipe(recipe.getName())) {
            recipeTable.getItems().remove(recipe);
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Element not selected.");
            alert.setContentText("You need to select a recipe in the list to delete it. ");
            alert.showAndWait();
        }
    }

    /**
     * Deletes selected recipe
     */
    private void deleteRecipe() {
        try {
            // get row
            TablePosition pos = recipeTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            Recipe recipe = recipeTable.getItems().get(row);

            if (callback.removeRecipe(recipe.getName())) {
                recipeTable.getItems().remove(recipe);
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Element not selected.");
            alert.setContentText("You need to select a recipe in the list to delete it. ");
            alert.showAndWait();
        }
    }

    /**
     * Gets selected recipe to be showcased
     */
    private void viewRecipe() {
        try {
            // get name
            TablePosition pos = recipeTable.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            TableColumn col = pos.getTableColumn();
            Recipe item = recipeTable.getItems().get(row);
            String recipe = (String) col.getCellObservableValue(item).getValue();
            System.out.println(recipe);

            // open new RecipePane with info
            pane.loadRecipe(RecipePanes.RecipeViewPane, callback.getRecipe(recipe));
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Element not selected.");
            alert.setContentText("You need to select a recipe in the list to view it. ");
            alert.showAndWait();
        }
    }

    private void search() {
        System.out.println("SEARCH");
    }

    @Override
    public void refresh() {
        this.recipeTable.refresh();
        if (this.callback.getExpanded()) {
            contract();
        } else {
            expand();
        }
    }

    /**
     * Gets the already stored recipes from the database and adds them to the tableview
     * @return list of recipes
     */
    private ObservableList<Recipe> getRecipes() {
        ObservableList <Recipe> recipes = FXCollections.observableArrayList();
        Recipe[] newRecipes = callback.getRecipes();
        recipes.addAll(Arrays.asList(newRecipes));
        return recipes;
    }

    /**
     * Searchbar functionality.
     */
    private void searchRecord(Observable observable, String oldValue, String newValue) {
        FilteredList<Recipe> filteredList = new FilteredList<>(getRecipes(), p -> true);

        if (!textField_search.getText().equals("")) {
            filteredList.setPredicate(tableView -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String typedText = newValue.toLowerCase();

                if (tableView.getName().toLowerCase().contains(typedText)) {
                    return true;

                } else
                    return false;
            });

            SortedList<Recipe> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(recipeTable.comparatorProperty());
            recipeTable.setItems(sortedList);

        } else
            recipeTable.setItems(getRecipes());
    }
}
