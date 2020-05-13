package code.view.panes;

import code.control.Callback;
import code.entities.Ingredient;
import code.entities.RecipePanes;
import code.entities.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.util.Arrays;

public class RecipeAddNewPane extends StackPane {
    // main functionality
    private Callback callback;
    private VBox container;
    private Label titleLabel;

    // lis values
    private Label listLabel;
    private TableView<String> ingredientsList; // Change to tableView with input as columns
    TableColumn<String, String> nameCol;
    TableColumn<String, Double> amountCol;
    TableColumn<String, String> unitCol;

    // input
    private ComboBox field_Name;
    private ComboBox field_Amount;
    private ComboBox field_Unit;
    private Button button_Enter;
    private Label label_Instructions;
    private TextArea field_Instructions;
    private TextField field_RName;

    // save & cancel
    private Button button_Save;
    private Button button_Cancel;

    // source
    private RecipePane pane;

    public RecipeAddNewPane(Callback callback, RecipePane source) {
        getStylesheets().add("styles.css");

        this.pane = source;

        /* callback */
        this.callback = callback;

        /* Container */
        container = new VBox();
        container.setMaxSize(1036,698); // Change upon scale
        container.setPrefSize(1036,698);
        container.setStyle(
                "-fx-background-color: #fff;" +
                        "-fx-background-radius: 25"
        );
        container.setAlignment(Pos.CENTER);

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
        Font titleFont = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 32);
        titleLabel = new Label("ADD NEW RECIPE");
        titleLabel.setFont(titleFont);
        titleLabel.setTextFill(Paint.valueOf("#619f81"));
        titleBox.getChildren().add(titleLabel);

        /* Spacing */
        HBox spaceBox = new HBox();
        spaceBox.setStyle("-fx-background-color: #fff");
        spaceBox.setPrefSize(1086,60);

        /* Recipe Name */
        HBox recipeNameBox = new HBox(10);
        recipeNameBox.setPrefSize(1086, 40);
        recipeNameBox.setStyle("-fx-background-color: #eee");
        recipeNameBox.setAlignment(Pos.CENTER_LEFT);
        recipeNameBox.setStyle("-fx-padding: 30, 0, 0, 0");
        Label label_RecipeName = new Label("Recipe name: ");
        field_RName = new TextField();
        field_RName.setPrefSize(300,40);
        recipeNameBox.getChildren().addAll(label_RecipeName, field_RName);

        /* List view */
        VBox listBox = new VBox(10);
        listBox.setPrefSize(480,200);
        listBox.setAlignment(Pos.CENTER);

        ingredientsList = new TableView<>();
        ingredientsList.setPrefSize(460,200);
        ingredientsList.setItems(getTestValues());
        ingredientsList.getStyleClass().add("list");
        ingredientsList.setEditable(false);

        nameCol = new TableColumn<>();
        nameCol.setPrefWidth(248);
        nameCol.setText("Name");
        //nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        amountCol = new TableColumn<>();
        amountCol.setPrefWidth(115);
        amountCol.setText("Amount");
        //amountCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
        unitCol  = new TableColumn<>();
        unitCol.setPrefWidth(115);
        unitCol.setText("Unit");
        //unitCol.setCellValueFactory(new PropertyValueFactory<>("unit"));

        ingredientsList.getColumns().addAll(nameCol, amountCol, unitCol);

        listLabel = new Label("Added ingredients");
        listBox.getChildren().addAll(listLabel, ingredientsList);

        /* Instructions view */ // -----> Move
        VBox instructionsBox = new VBox(10);
        instructionsBox.setPrefSize(480,200);
        instructionsBox.setAlignment(Pos.CENTER);
        field_Instructions = new TextArea();
        field_Instructions.setPrefSize(460,200);
        field_Instructions.getStyleClass().add("text-field");
        label_Instructions = new Label("Enter instructions");
        instructionsBox.getChildren().addAll(label_Instructions, field_Instructions);

        /* List + Instructions */
        HBox container_list_instr = new HBox(20);
        container_list_instr.setPrefSize(1086,200);
        container_list_instr.setAlignment(Pos.CENTER);
        container_list_instr.getChildren().addAll(listBox, instructionsBox);

        /* TextFields */
        HBox fieldBox = new HBox(20);
        fieldBox.setPrefSize(1086,100);
        fieldBox.setAlignment(Pos.CENTER);

        field_Name = new ComboBox();
        field_Name.setPrefSize(200,30);
        field_Name.setEditable(true);
        field_Name.setPromptText("Name");
        field_Name.getStyleClass().add("text-field");

        field_Amount = new ComboBox();
        field_Amount.setPrefSize(100,30);
        field_Amount.setEditable(true);
        field_Amount.setPromptText("Amount");
        field_Amount.getStyleClass().add("text-field");

        field_Unit = new ComboBox();
        field_Unit.setPrefSize(100,30);
        field_Unit.setEditable(false);
        field_Unit.setItems(getUnits());
        field_Unit.setPromptText("Unit");
        field_Unit.getStyleClass().add("text-field");

        button_Enter = new Button("ADD");
        button_Enter.setPrefSize(100,30);
        button_Enter.getStyleClass().add("greenButton");
        button_Enter.setOnAction(e -> addIngredient());

        fieldBox.getChildren().addAll(field_Name, field_Amount, field_Unit,  button_Enter);

        // TODO: Add more ?

        /* Space box */
        HBox spacing_addAndButtons = new HBox();
        spacing_addAndButtons.setStyle(
                "-fx-background-color: #fff;"
        );
        spacing_addAndButtons.setPrefSize(1086, 120);

        /* buttons */
        HBox buttonBox = new HBox(20);
        buttonBox.setPrefSize(1086,60);
        buttonBox.setAlignment(Pos.CENTER);

        button_Save = new Button("SAVE");
        button_Save.setPrefSize(200,60);
        button_Save.getStyleClass().add("bigGreenButton");
        button_Save.setOnAction(e -> saveRecipe());

        button_Cancel = new Button("CANCEL");
        button_Cancel.setPrefSize(200,60);
        button_Cancel.getStyleClass().add("bigGrayButton");
        button_Cancel.setOnAction(e -> goBack());

        buttonBox.getChildren().addAll(button_Save, button_Cancel);

        /* Bottom container */
        HBox bottomSpacing = new HBox();
        bottomSpacing.setStyle(
                "-fx-background-color: #fff;" +
                        "-fx-background-radius: 25, 25, 25, 25"
        );
        bottomSpacing.setPrefSize(1086, 40);

        /* Collect to add */
        container.getChildren().addAll(titleBox, spaceBox, recipeNameBox, container_list_instr, fieldBox, spacing_addAndButtons, buttonBox, bottomSpacing);
        getChildren().add(container);
    }

    private void saveRecipe() {
        System.out.println(field_RName.getText());
    }

    private void addIngredient() {
        String name = null, unit = null;
        double amount = 0;
        try {
            name = (String) field_Name.getValue();
            amount = Double.parseDouble((String) field_Amount.getValue());
            unit = (String) field_Unit.getValue();
        } catch (Exception e) {
            System.out.println("Please enter a correct amount");
        }

        // if ingredient exist, use it, else create new

        System.out.println("Added Ingredient: \n" +
                "Name" + name + "\n" +
                "Amount: " + amount + " " + unit);
    }

    private void goBack() {
        pane.setView(RecipePanes.RecipeListPane);
    }

    private ObservableList<String> getUnits() {
        ObservableList<String> unitList = FXCollections.observableArrayList();
        String[] units = {"DL", "MSK", "TSK"};
        unitList.addAll(Arrays.asList(units));
        return unitList;
    }

    private ObservableList<String> getTestValues() {
        ObservableList <String> ingredients = FXCollections.observableArrayList();
        String[] newIngredients = new String[2];
        newIngredients[0] = "Mjöl";
        newIngredients[1] = "Socker";
        ingredients.addAll(Arrays.asList(newIngredients));
        return ingredients;
    }
}
