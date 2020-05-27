package code.view.panes;

import code.control.Callback;
import code.entities.Recipe;
import code.entities.RecipePanes;
import code.entities.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * RecipePane.java
 * Presents the user with an overview of what recipes are stored.
 * @author Lucas Eliasson
 * @version 1.0
 */
public class RecipeListPane extends StackPane implements EnhancedPane {
    private VBox container;
    private HBox titleBox;
    private HBox filterBox;
    private HBox buttonBox;
    private HBox searchBox;
    private HBox buttonAndSearchContainer;
    private HBox viewContainer;
    private HBox bottomSpacing;

    private Callback callback;
    private Label titleLabel;
    private Label filterLabel;
    private Button addButton;
    private Button deleteButton;
    private Button viewButton;
    private TextField searchField;
    private Button searchButton;
    private TableView<Recipe> recipeView;
    private TableColumn<Recipe, String> nameCol;
    TableColumn<Recipe, String> categoryCol;
    private RecipePane pane;

    public RecipeListPane(Callback callback, RecipePane pane) {
        this.pane = pane;

        /* callback */
        this.callback = callback;

        /* StackPane */
        setStyle(Styles.getPane());
        setPrefSize(1086, 768);

        /* Container */
        container = new VBox();
        container.setMaxSize(1036,698); // Change upon scale
        container.setPrefSize(1036,698);
        container.setStyle(
                "-fx-background-color: #fff;" +
                        "-fx-background-radius: 20"
        );
        container.setAlignment(Pos.CENTER);

        /* Title */
        titleBox = new HBox();
        titleBox.setPrefSize(1086, 85);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(
                "-fx-background-radius: 20 20 0 0;" +
                        "-fx-border-width: 0 0 1 0;" +
                        "-fx-border-color: #000;" +
                        "-fx-padding: 10;"
        );
        Font MenuTitle = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        titleLabel = new Label("RECIPES");
        titleLabel.setFont(MenuTitle);
        titleLabel.setTextFill(Paint.valueOf("#619f81"));
        titleBox.getChildren().add(titleLabel);

        /* Label */
        filterBox = new HBox();
        filterBox.setPadding(new Insets(20,30,0,30));
        filterBox.setAlignment(Pos.CENTER_LEFT);
        filterBox.setPrefSize(1086,20);
        filterLabel = new Label("ALL RECIPES");
        filterBox.getChildren().add(filterLabel);

        /* Buttons and search */
        buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPrefSize(480,60);
        addButton = new Button("ADD RECIPE");
        addButton.setPrefSize(160,40);
        addButton.getStyleClass().add("greenButtonPanel");
        addButton.setOnAction(e -> createAddView());
        deleteButton = new Button("DELETE RECIPE");
        deleteButton.setPrefSize(160,40);
        deleteButton.getStyleClass().add("greenButtonPanel");
        deleteButton.setOnAction(e -> deleteRecipe());
        viewButton = new Button("VIEW RECIPE");
        viewButton.setPrefSize(160,40);
        viewButton.getStyleClass().add("greenButtonPanel");
        viewButton.setOnAction(e -> viewRecipe());
        buttonBox.getChildren().addAll(addButton, deleteButton, viewButton);
        searchBox = new HBox();
        searchBox.setPrefSize(480,60);
        searchBox.setAlignment(Pos.CENTER_RIGHT);
        Label searchLabel = new Label("SEARCH");
        searchLabel.setPadding(new Insets(0,10,0,0));
        searchField = new TextField();
        searchField.setPromptText("Search");
        searchField.setPrefSize(240,40);
        searchButton = new Button();
        searchButton.setPrefSize(40,40);
        searchButton.getStyleClass().add("greenButtonSquare");
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
        searchBox.getChildren().addAll(searchLabel, searchField, searchButton);
        buttonAndSearchContainer = new HBox(20);
        buttonAndSearchContainer.setPrefSize(980,60);
        buttonAndSearchContainer.setMaxSize(980,60);
        buttonAndSearchContainer.setAlignment(Pos.CENTER);
        buttonAndSearchContainer.getChildren().addAll(buttonBox, searchBox);

        /* Table code.view */
        viewContainer = new HBox();
        viewContainer.setPadding(new Insets(10,0,0,0));
        viewContainer.setPrefSize(980,480);
        viewContainer.setAlignment(Pos.CENTER);
        recipeView = new TableView<>();
        recipeView.setPrefSize(980,473);
        recipeView.getStyleClass().add("view");
        viewContainer.getChildren().add(recipeView);

        /* Table columns */
        nameCol = new TableColumn<>("Name");
        nameCol.getStyleClass().add("name-column");
        nameCol.setStyle(Styles.getTableColumn());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(490);
        categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.getStyleClass().add("name-column");
        categoryCol.setStyle(Styles.getTableColumn());
        categoryCol.setPrefWidth(490);
        recipeView.getColumns().addAll(nameCol, categoryCol);

        recipeView.setItems(getRecipes());

        /* Bottom container */
        bottomSpacing = new HBox();
        bottomSpacing.setStyle(
                "-fx-background-color: #fff;" +
                "-fx-background-radius: 25, 25, 25, 25"
        );
        bottomSpacing.setPrefSize(1086, 70);

        /* Collect to add */
        container.getChildren().addAll(titleBox, filterBox, buttonAndSearchContainer, viewContainer, bottomSpacing);
        getChildren().add(container);
    }

    public void expand() {
        container.setMaxWidth(1196);
        filterBox.setMaxWidth(1196);
        buttonAndSearchContainer.setMaxWidth(1140); // FIX
        buttonBox.setMaxWidth(640);
        recipeView.setMinWidth(1140);
        nameCol.setMinWidth(570);
        categoryCol.setMinWidth(570);
        viewContainer.setMinWidth(1196);
        bottomSpacing.setMaxWidth(1196);
    }

    public void contract() {
        container.setMaxWidth(1036);
        filterBox.setMaxWidth(1036);
        buttonAndSearchContainer.setMaxWidth(980); // FIX
        buttonBox.setMaxWidth(480);
        recipeView.setMinWidth(980);
        nameCol.setMinWidth(490);
        categoryCol.setMinWidth(490);
        nameCol.setMaxWidth(490);
        categoryCol.setMaxWidth(490);
        viewContainer.setMinWidth(1036);
        bottomSpacing.setMaxWidth(1036);
    }

    /**
     * Adds a new recipe
     * @param recipe
     */
    public void addNewRecipe(Recipe recipe) {
        recipeView.getItems().add(recipe);
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
            recipeView.getItems().remove(recipe);
        } else {
            JOptionPane.showMessageDialog(null, "No selected recipe to be deleted");
        }
    }

    /**
     * Deletes selected recipe
     */
    private void deleteRecipe() {
        // get row
        TablePosition pos = recipeView.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        Recipe recipe = recipeView.getItems().get(row);

        if (callback.removeRecipe(recipe.getName())) {
            recipeView.getItems().remove(recipe);
        } else {
            JOptionPane.showMessageDialog(null, "No selected recipe to be deleted");
        }
    }

    /**
     * Gets selected recipe to be showcased
     */
    private void viewRecipe() {
        try {
            // get name
            TablePosition pos = recipeView.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            TableColumn col = pos.getTableColumn();
            Recipe item = recipeView.getItems().get(row);
            String recipe = (String) col.getCellObservableValue(item).getValue();
            System.out.println(recipe);

            // open new RecipePane with info
            pane.loadRecipe(RecipePanes.RecipeViewPane, callback.getRecipe(recipe));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please choose a recipe before pressing View");
        }
    }

    private void search() {
        System.out.println("SEARCH");
    }

    @Override
    public void refresh() {
        recipeView.refresh();
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
}
