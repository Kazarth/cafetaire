package code.view.panes;

import code.control.Callback;
import code.entities.Recipe;
import code.entities.RecipePanes;
import code.entities.Styles;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * RecipePane.java
 * Presents the user with an overview of what recipes are stored.
 * @author Lucas Eliasson
 * @version 1.0
 */
public class RecipeListPane extends StackPane {
    private Callback callback;
    private VBox container;
    private Label titleLabel;
    private Label filterLabel;
    private Button addButton;
    private Button deleteButton;
    private Button viewButton;
    private TextField searchField;
    private Button searchButton;
    private TableView<Recipe> recipeView;
    private RecipePane pane;

    public RecipeListPane(Callback callback, RecipePane pane) {
        this.pane = pane;

        /* callback */
        this.callback = callback;

        /* Container */
        container = new VBox();
        container.setMaxSize(1036,698); // Change upon scale
        container.setPrefSize(1036,698);
        container.setStyle(
                "-fx-background-color: #fff;" +
                        "-fx-background-radius: 20"
        );
        container.setAlignment(Pos.CENTER);

        /* Spacing */
        HBox spaceBox = new HBox();
        spaceBox.setPrefSize(1086,20);

        /* Title */
        HBox titleBox = new HBox();
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
        HBox filterBox = new HBox();
        filterBox.setPadding(new Insets(20,30,0,30));
        filterBox.setAlignment(Pos.CENTER_LEFT);
        filterBox.setPrefSize(1086,20);
        filterLabel = new Label("ALL RECIPES");
        filterBox.getChildren().add(filterLabel);

        /* Buttons and search */
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPrefSize(480,60);
        addButton = new Button("ADD RECIPE");
        addButton.setPrefSize(160,40);
        addButton.getStyleClass().add("greenButtonPanel");
        addButton.setOnAction(e -> createAddView());
        deleteButton = new Button("DELETE RECIPE");
        deleteButton.setPrefSize(160,40);
        deleteButton.getStyleClass().add("greenButtonPanel");
        deleteButton.setOnAction(e -> removeRecipe());
        viewButton = new Button("VIEW RECIPE");
        viewButton.setPrefSize(160,40);
        viewButton.getStyleClass().add("greenButtonPanel");
        viewButton.setOnAction(e -> viewRecipe());
        buttonBox.getChildren().addAll(addButton, deleteButton, viewButton);
        HBox searchBox = new HBox();
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
        HBox buttonAndSearchContainer = new HBox(20);
        buttonAndSearchContainer.setPrefSize(980,60);
        buttonAndSearchContainer.setMaxSize(980,60);
        buttonAndSearchContainer.getChildren().addAll(buttonBox, searchBox);

        /* Table code.view */
        HBox viewContainer = new HBox();
        viewContainer.setPadding(new Insets(10,0,0,0));
        viewContainer.setPrefSize(980,480);
        viewContainer.setMaxSize(980,480);
        viewContainer.setAlignment(Pos.CENTER);
        recipeView = new TableView<>();
        recipeView.setPrefSize(980,473);
        recipeView.setStyle(Styles.getTableRowSelected());
        viewContainer.getChildren().add(recipeView);

        /* Table columns */
        TableColumn<Recipe, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameCol.setPrefWidth(490);
        TableColumn<Recipe, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryCol.setPrefWidth(490);
        recipeView.getColumns().addAll(nameCol, categoryCol);

        recipeView.setItems(getTestValues());

        /* Bottom container */
        HBox bottomSpacing = new HBox();
        bottomSpacing.setStyle(
                "-fx-background-color: #fff;" +
                "-fx-background-radius: 25, 25, 25, 25"
        );
        bottomSpacing.setPrefSize(1086, 70);

        /* Collect to add */
        container.getChildren().addAll(titleBox, filterBox, buttonAndSearchContainer, viewContainer, bottomSpacing);
        getChildren().add(container);
    }

    public void addNewRecipe(Recipe recipe) {
        recipeView.getItems().add(recipe);
    }

    // TODO: add to callback
    private Boolean createAddView() {
        System.out.println("ADD");
        pane.setView(RecipePanes.RecipeAddNewPane);
        return false;
    }

    // TODO: add to callback
    private Boolean removeRecipe() {
        System.out.println("REMOVE");
        return false;
    }

    /* In i callback -- wait */
    private void viewRecipe() {
        try {
            // get name
            TablePosition pos = recipeView.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            TableColumn col = pos.getTableColumn();
            Recipe item = recipeView.getItems().get(row);
            String recipe = (String) col.getCellObservableValue(item).getValue();
            System.out.println(recipe);

            // check against recipe list

            // open new RecipePane with info
            pane.setView(RecipePanes.RecipeViewPane);
            //pane.loadRecipe(recipeView, recipe);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Please choose a recipe before pressing View");
        }
    }

    private void search() {
        System.out.println("SEARCH");
    }

    public void expand() {
        System.out.println("expand");
    }

    public void contract() {
        System.out.println("contract");
    }

    private ObservableList<Recipe> getTestValues() {
        ObservableList <Recipe> recipes = FXCollections.observableArrayList();
        Recipe[] newRecipes = new Recipe[2];
        newRecipes[0] = new Recipe("Choklad Muffins");
        newRecipes[1] = new Recipe("Kanelbullar");
        recipes.addAll(Arrays.asList(newRecipes));
        return recipes;
    }
}
