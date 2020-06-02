package code.view.panes;

import code.control.Callback;
import code.entities.*;
import code.view.popups.ContentPopup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ActiveRecipePane.java
 * Showcases a pane that lets the user interact with the recipe he want to use.
 * @author Lucas Eliasson
 * @version 1.0
 */
public class RecipeViewPane extends Pane implements EnhancedPane { // extended Pane will be the gray area
    private VBox mainContainer; // White box

    private Label titleLabel; // title
    private Label productNameLabel; // product name
    private Label ingredientsLabel; // Ingredients
    private Label stepLabel; // How to bake

    private Button backButton; // back button
    private Button deleteButton; // delete recipe button
    private Button editIngredientsButton; // edit ingredients
    private Button saveEditIngredientsButton;
    private Button editStepsButton; // edit Steps
    private Button bakeButton; // BAKE

    private TableView<Content> ingredientsList; // show ingredients
    private TableColumn<Content, String> nameCol;
    private TableColumn<Content, Double> amountCol;
    private TableColumn<Content, Units> unitCol;
    private TextArea stepsBox; // show steps
    private TextField amountField; // amount of satser??

    private Callback callback;
    private RecipePane source;
    private Recipe recipe;

    public RecipeViewPane(Callback callback, RecipePane source) {
        this.callback = callback;
        this.source = source;

        Font titleFont = Font.font("Segoe UI",FontWeight.BOLD, FontPosture.REGULAR, 24);
        this.titleLabel = new Label("RECIPES");
        this.titleLabel.setFont(titleFont);
        this.titleLabel.setTextFill(Paint.valueOf("#619f81"));
        this.titleLabel.setPadding(new Insets(0,0,17,0));

        /* Title */
        HBox titleBox = new HBox();
        titleBox.setPrefSize(1086, 60);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setStyle(
                "-fx-background-radius: 20 20 0 0;" +
                "-fx-border-width: 0 0 1 0;" +
                "-fx-border-color: #000;"
        );
        titleBox.getChildren().add(titleLabel);

        /* Spacing */
        HBox spaceBox = new HBox();
        spaceBox.setPrefSize(1086,6);

        this.backButton = new Button("BACK TO RECIPES");
        this.backButton.getStyleClass().add("greenButton");
        this.backButton.setOnAction(e -> goBack());
        this.backButton.setPrefSize(160,32);

        /* Back Button */
        HBox backBox = new HBox();
        backBox.setPrefSize(160,50);
        backBox.setAlignment(Pos.CENTER);
        backBox.setStyle("-fx-padding: 0, 10, 0, 100;");
        backBox.getChildren().add(this.backButton);

        Font productFont = Font.font("Segoe UI",FontWeight.BOLD, FontPosture.REGULAR, 32);
        this.productNameLabel = new Label("name"); // replace with getter
        this.productNameLabel.setFont(productFont);

        /* Product name */
        HBox nameBox = new HBox();
        nameBox.setPrefSize(600,50);
        nameBox.setAlignment(Pos.CENTER);
        nameBox.setStyle(
                "-fx-padding: 0, 10, 0, 100;"
        );
        nameBox.getChildren().add(this.productNameLabel);

        this.deleteButton = new Button("DELETE RECIPE");
        this.deleteButton.getStyleClass().add("redButton");
        this.deleteButton.setOnAction(e -> deleteRecipe());
        this.deleteButton.setPrefSize(160,32);

        /* Delete button */
        HBox deleteBox = new HBox();
        deleteBox.setPrefSize(160,50);
        deleteBox.setAlignment(Pos.CENTER);
        deleteBox.setStyle("-fx-padding: 0, 10, 0, 100;");
        deleteBox.getChildren().add(this.deleteButton);

        /* Bar container */
        HBox barContainer = new HBox();
        barContainer.setPrefSize(1086,60);
        barContainer.setAlignment(Pos.CENTER);
        barContainer.getChildren().addAll(backBox, nameBox, deleteBox);

        this.ingredientsLabel = new Label("Ingredients");
        Font guideFont = Font.font("Segoe UI",FontWeight.BOLD, FontPosture.REGULAR, 28);
        this.ingredientsLabel.setFont(guideFont);
        this.ingredientsLabel.setStyle(
                "-fx-border-color: black, transparent, black;" +
                "-fx-border-width: 0 0 1 0, 0 0 1 0, 0 0 1 0;" +
                "-fx-border-insets: 0 0 1 0, 0 0 2 0, 0 0 3 0;"
        );

        this.nameCol = new TableColumn<>();
        this.nameCol.setPrefWidth(258);
        this.nameCol.setText("Name");
        this.nameCol.setCellValueFactory(new PropertyValueFactory<>("ingredient"));

        this.amountCol = new TableColumn<>();
        this.amountCol.setPrefWidth(60);
        this.amountCol.setText("Amount");
        this.amountCol.setCellValueFactory(new PropertyValueFactory<>("value"));

        this.unitCol  = new TableColumn<>();
        this.unitCol.setPrefWidth(40);
        this.unitCol.setText("Unit");
        this.unitCol.setCellValueFactory(new PropertyValueFactory<>("unit"));

        this.ingredientsList = new TableView<>();
        this.ingredientsList.setPrefSize(360,300);
        this.ingredientsList.getStyleClass().add("list");
        this.ingredientsList.setEditable(false);
        this.ingredientsList.getColumns().addAll(this.nameCol, this.amountCol, this.unitCol);

        this.editIngredientsButton = new Button("EDIT INGREDIENTS");
        this.editIngredientsButton.getStyleClass().add("greenButton");
        this.editIngredientsButton.setOnAction(e -> editIngredients());
        this.editIngredientsButton.setPrefSize(150,40);

        /* Ingredients */
        VBox ingredientsContainer = new VBox(10);
        ingredientsContainer.setAlignment(Pos.CENTER);
        ingredientsContainer.getChildren().addAll(this.ingredientsLabel, this.ingredientsList, this.editIngredientsButton);

        this.stepLabel = new Label("How to bake");
        this.stepLabel.setFont(guideFont);
        this.stepLabel.setStyle(
                "-fx-border-color: black, transparent, black;" +
                "-fx-border-width: 0 0 1 0, 0 0 1 0, 0 0 1 0;" +
                "-fx-border-insets: 0 0 1 0, 0 0 2 0, 0 0 3 0;"
        );
        this.stepsBox = new TextArea();
        this.stepsBox.setPrefSize(360,300);
        this.stepsBox.getStyleClass().add("text-field");
        this.stepsBox.setEditable(true);
        this.stepsBox.setWrapText(true);

        this.editStepsButton = new Button("EDIT STEPS");
        this.editStepsButton.getStyleClass().add("greenButton");
        this.editStepsButton.setOnAction(e -> editInstructions());
        this.editStepsButton.setPrefSize(150,40);

        /* Guide */
        VBox stepContainer = new VBox(10);
        stepContainer.setAlignment(Pos.CENTER);
        stepContainer.getChildren().addAll(this.stepLabel, this.stepsBox, this.editStepsButton);

        /* Add to container */
        HBox guideContainer = new HBox(40);
        guideContainer.setAlignment(Pos.CENTER);
        guideContainer.setPrefSize(1086, 440);
        guideContainer.getChildren().addAll(ingredientsContainer, stepContainer);

        this.amountField = new TextField();
        this.amountField.setPromptText("Amount");
        this.amountField.setPrefSize(80, 60);

        this.bakeButton = new Button("BAKE");
        this.bakeButton.getStyleClass().add("bigGreenButton");
        this.bakeButton.setOnAction(e -> bake());
        this.bakeButton.setPrefSize(200,60);

        /* Bottom container */
        HBox bakeBox = new HBox(20);
        bakeBox.setPrefSize(1086, 100);
        bakeBox.setStyle("-fx-background-radius: 0 0 25 25;");
        bakeBox.setAlignment(Pos.CENTER);
        bakeBox.getChildren().addAll(this.amountField, this.bakeButton);

        /* Container */
        this.mainContainer = new VBox();
        this.mainContainer.setPrefSize(1014,695);
        this.mainContainer.setStyle(
                "-fx-background-color: #fff;" +
                "-fx-background-radius: 20"
        );
        //this.container.setAlignment(Pos.CENTER);
        this.mainContainer.setLayoutX(20);
        this.mainContainer.setLayoutY(20);
        this.mainContainer.getChildren().addAll(titleBox, spaceBox, barContainer, guideContainer, bakeBox);

        /* Gray background */
        setPrefSize(1054,736);
        setStyle("-fx-background-color: #6B6C6A;");
        getStylesheets().add("styles.css");
        getChildren().add(this.mainContainer);
    }

    @Override
    public void expand() {
        this.setPrefWidth(1200);
        this.mainContainer.setPrefWidth(1160);
    }

    @Override
    public void contract() {
        this.setPrefWidth(1054);
        this.mainContainer.setPrefWidth(1014);
    }

    @Override
    public void refresh() {
        this.ingredientsList.refresh();
        if (this.callback.getExpanded()) {
            contract();
        } else {
            expand();
        }
    }

    public ArrayList<Content> getRecipeContent() {
        return this.recipe.getContentList();
    }

    /**
     * Gets the already stored recipes from the database and adds them to the tableview
     */
    private void loadIngredients() {
        ObservableList <Content> ingredients = FXCollections.observableArrayList();
        ArrayList<Content> content = this.callback.getRecipe(this.recipe.getName()).getContentList();
        Content[] newContent = new Content[content.size()];
        for (int i = 0; i < content.size(); i++) {
            newContent[i] = content.get(i);
        }
        ingredients.addAll(Arrays.asList(newContent));
        this.ingredientsList.setItems(ingredients);
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    /**
     * Returns the name on the selected row in GUI
     * @return String
     */
    public Content getSelectedContent() {
        TablePosition pos = this.ingredientsList.getSelectionModel().getSelectedCells().get(0);
        System.out.println();
        int row = pos.getRow();

        return this.ingredientsList.getItems().get(row);
    }

    /**
     * Sets the values to the chosen recipe from the source pane
     * @param recipe Chosen recipe
     */
    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        this.productNameLabel.setText(recipe.getName().toUpperCase());
        this.stepsBox.setText(recipe.getInstructions());

        loadIngredients();
    }

    /**
     * Return to the source pane
     */
    private void goBack() {
        System.out.println("RETURN");
        source.setView(RecipePanes.RecipeListPane);
    }

    /**
     * Delete active recipe
     */
    private void deleteRecipe() {
        this.source.deleteRecipe(this.recipe);
        goBack();
    }

    /**
     * Edit ingredients
     */
    private void editIngredients() {
        new ContentPopup(this, this.callback);
        this.ingredientsList.refresh();
    }

    /**
     * Let's the user
     * @return
     */
    private void editInstructions() {
        System.out.println("EDIT STEPS");
        this.stepsBox.setEditable(true);
    }

    /**
     * Removes batch
     */
    private void bake() {
        if (this.amountField.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("NO AMOUNT");
            alert.setContentText("Ooops, please enter an amount to bake!");
            alert.showAndWait();
        } else {
            ArrayList<Content> contentList = this.callback.getRecipe(this.recipe.getName()).getContentList();
            int nSets = Integer.parseInt(this.amountField.getText());

            for (int i = 0; i < nSets; i++) {
                for (Content c : contentList) {
                    String ingredient = c.getIngredient().getType();
                    double amount = c.getValue();
                    double amountFromStock = this.callback.getNumIngredients(ingredient);

                    if ((nSets % amountFromStock) > 0) {
                        this.callback.decrementIngredient(ingredient, (int) amount);
                        if (this.callback.checkProduct(recipe.getName())) {
                            System.out.println("Produkt finns");
                            callback.getProduct(this.recipe.getName()).increment((int) this.recipe.getAmount());
                        } else {
                            System.out.println("Skapa nu produkt");
                            Product product = new Product(recipe.getName(), ProductCategories.Pastry, (int) this.recipe.getAmount(), this.recipe);
                            this.callback.addProduct(product);
                        }
                        goBack();
                    } else {
                        Alert insufficientIngredientsAlert = new Alert(Alert.AlertType.ERROR);
                        insufficientIngredientsAlert.setTitle("Error Dialog");
                        insufficientIngredientsAlert.setHeaderText("Insufficient Amount in Stock");
                        insufficientIngredientsAlert.setContentText("Ooops, you don't have enough ingredients for your choice!");
                        insufficientIngredientsAlert.showAndWait();
                        nSets = 0;
                        break;
                    }
                }
            }
        }
    }
}
