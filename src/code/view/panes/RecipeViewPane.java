package code.view.panes;

import code.control.Callback;
import code.entities.Recipe;
import code.entities.RecipePanes;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import javax.swing.*;

/**
 * ActiveRecipePane.java
 * Showcases a pane that lets the user interact with the recipe he want to use.
 * @author Lucas Eliasson
 * @version 1.0
 */
public class RecipeViewPane extends StackPane { // extended Pane will be the gray area
    private VBox container; // White box

    private Label titleLabel; // title
    private Label productNameLabel; // product name
    private Label ingredientsLabel; // Ingredients
    private Label stepLabel; // How to bake

    private Button backButton; // back button
    private Button deleteButton; // delete recipe button
    private Button editIngredientsButton; // edit ingredients
    private Button editStepsButton; // edit Steps
    private Button bakeButton; // BAKE

    private TextArea ingredientsBox; // show ingredients
    private TextArea stepsBox; // show steps

    private TextField amountField; // amount of satser??

    private Callback callback;
    private RecipePane recipePane;

    private Recipe recipe;

    public RecipeViewPane(Callback callback, RecipePane source) {
        this.callback = callback;
        this.recipePane = source;

        /* Gray background */
        setPrefSize(1086,768);
        setStyle(
                "-fx-background-color: #6B6C6A;"
        );
        getStylesheets().add("styles.css");

        /* Container */
        container = new VBox();
        container.setMaxSize(1036,698); // Change upon scale
        container.setPrefSize(1036,698);
        container.setStyle(
                "-fx-background-color: #fff;" +
                "-fx-background-radius: 25"
        );
        container.setAlignment(Pos.CENTER);

        /* Spacing */
        HBox spaceBox = new HBox();
        spaceBox.setPrefSize(1086,20);

        /* Title */
        HBox titleBox = new HBox();
        titleBox.setPrefSize(1086, 48);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(
                "-fx-background-radius: 25 25 0 0;" +
                "-fx-border-width: 0 0 1 0;" +
                "-fx-border-color: #000;" +
                "-fx-padding: 10;"
        );
        Font titleFont = Font.font("Segoe UI",FontWeight.BOLD, FontPosture.REGULAR, 32);
        titleLabel = new Label("RECIPES");
        titleLabel.setFont(titleFont);
        titleLabel.setTextFill(Paint.valueOf("#619f81"));
        titleBox.getChildren().add(titleLabel);

        /* Back Button */
        HBox backBox = new HBox();
        backBox.setPrefSize(160,50);
        backBox.setAlignment(Pos.CENTER);
        backBox.setStyle(
                "-fx-padding: 0, 10, 0, 100;"
        );
        backButton = new Button("BACK TO RECIPES");
        backButton.getStyleClass().add("greenButton");
        backButton.setOnAction(e -> goBack());
        backButton.setPrefSize(160,32);
        backBox.getChildren().add(backButton);

        /* Product name */
        HBox nameBox = new HBox();
        nameBox.setPrefSize(600,50);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setStyle(
                "-fx-padding: 0, 10, 0, 100;"
        );
        Font productFont = Font.font("Segoe UI",FontWeight.BOLD, FontPosture.REGULAR, 32);
        productNameLabel = new Label("name"); // replace with getter
        productNameLabel.setFont(productFont);
        nameBox.getChildren().add(productNameLabel);

        /* Delete button */
        HBox deleteBox = new HBox();
        deleteBox.setPrefSize(160,50);
        deleteBox.setAlignment(Pos.CENTER);
        deleteBox.setStyle(
                "-fx-padding: 0, 10, 0, 100;"
        );
        deleteButton = new Button("DELETE RECIPE");
        deleteButton.getStyleClass().add("redButton");
        deleteButton.setOnAction(e -> deleteRecipe());
        deleteButton.setPrefSize(160,32);
        deleteBox.getChildren().add(deleteButton);

        /* Bar container */
        HBox barContainer = new HBox();
        barContainer.setPrefSize(1086,60);
        barContainer.setAlignment(Pos.CENTER);
        barContainer.getChildren().addAll(backBox, nameBox, deleteBox);

        /* Ingredients */
        VBox ingredientsContainer = new VBox(10);
        ingredientsContainer.setAlignment(Pos.CENTER);
        ingredientsLabel = new Label("Ingredients");
        Font guideFont = Font.font("Segoe UI",FontWeight.BOLD, FontPosture.REGULAR, 28);
        ingredientsLabel.setFont(guideFont);
        ingredientsLabel.setStyle(
                "-fx-border-color: black, transparent, black;" +
                "-fx-border-width: 0 0 1 0, 0 0 1 0, 0 0 1 0;" +
                "-fx-border-insets: 0 0 1 0, 0 0 2 0, 0 0 3 0;"
        );
        ingredientsBox = new TextArea();
        ingredientsBox.setPrefSize(360,300);
        ingredientsBox.getStyleClass().add("text-field");
        editIngredientsButton = new Button("EDIT INGREDIENTS");
        editIngredientsButton.getStyleClass().add("greenButton");
        editIngredientsButton.setOnAction(e -> editIngredients());
        editIngredientsButton.setPrefSize(150,40);
        ingredientsContainer.getChildren().addAll(ingredientsLabel, ingredientsBox, editIngredientsButton);

        /* Guide */
        VBox stepContainer = new VBox(10);
        stepContainer.setAlignment(Pos.CENTER);
        stepLabel = new Label("How to bake");
        stepLabel.setFont(guideFont);
        stepLabel.setStyle(
                "-fx-border-color: black, transparent, black;" +
                        "-fx-border-width: 0 0 1 0, 0 0 1 0, 0 0 1 0;" +
                        "-fx-border-insets: 0 0 1 0, 0 0 2 0, 0 0 3 0;"
        );
        stepsBox = new TextArea();
        stepsBox.setPrefSize(360,300);
        stepsBox.getStyleClass().add("text-field");
        editStepsButton = new Button("EDIT STEPS");
        editStepsButton.getStyleClass().add("greenButton");
        editStepsButton.setOnAction(e -> editSteps());
        editStepsButton.setPrefSize(150,40);
        stepContainer.getChildren().addAll(stepLabel, stepsBox, editStepsButton);

        /* Add to container */
        HBox guideContainer = new HBox(40);
        guideContainer.setAlignment(Pos.CENTER);
        guideContainer.setPrefSize(1086, 440);
        guideContainer.getChildren().addAll(ingredientsContainer, stepContainer);

        /* Bottom container */
        HBox bakeBox = new HBox(20);
        bakeBox.setPrefSize(1086, 100);
        bakeBox.setStyle(
                "-fx-background-radius: 0 0 25 25;"
        );
        bakeBox.setAlignment(Pos.CENTER);
        amountField = new TextField();
        amountField.setPromptText("Amount");
        amountField.setPrefSize(80, 60);
        bakeButton = new Button("BAKE");
        bakeButton.getStyleClass().add("bigGreenButton");
        bakeButton.setOnAction(e -> bake());
        bakeButton.setPrefSize(200,60);
        bakeBox.getChildren().addAll(amountField, bakeButton);

        /* Collect to add */
        container.getChildren().addAll(titleBox, spaceBox, barContainer, guideContainer, bakeBox);
        getChildren().add(container);
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        productNameLabel.setText(recipe.getName());
        ingredientsBox.setText(recipe.toString());
        stepsBox.setText(recipe.getInstructions());
    }

    private void goBack() {
        System.out.println("RETURN");
        recipePane.setView(RecipePanes.RecipeListPane);
    }

    private Boolean deleteRecipe() {
        System.out.println("DELETE");
        return false;
    }

    private Boolean editIngredients() {
        System.out.println("EDIT INGREDIENTS");
        return false;
    }

    private Boolean editSteps() {
        System.out.println("EDIT STEPS");
        return false;
    }

    private void bake() {
        if (amountField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("NO AMOUNT");
            alert.setContentText("Ooops, please enter an amount to bake!");

            alert.showAndWait();
        } else {
            String print = "BAKE " + amountField.getText() + " COOKIES";
            System.out.println(print);
        }
    }

    private void activeButton() {

    }
}
