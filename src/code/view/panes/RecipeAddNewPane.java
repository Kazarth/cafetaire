package code.view.panes;

import code.control.Callback;
import code.entities.RecipePanes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
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
    private ListView<String> ingredientsList; // Change to tableView with input as columns

    // input
    private ComboBox field_Name;
    private ComboBox field_Amount;
    private ComboBox field_Unit;
    private Button button_Enter;

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
        Font titleFont = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 32);
        titleLabel = new Label("ADD NEW RECIPE");
        titleLabel.setFont(titleFont);
        titleLabel.setTextFill(Paint.valueOf("#619f81"));
        titleBox.getChildren().add(titleLabel);

        /* List view */
        HBox listBox = new HBox();
        listBox.setPrefSize(1086,200);
        listBox.setAlignment(Pos.CENTER);
        ingredientsList = new ListView<>();
        ingredientsList.setPrefSize(980,200);
        ingredientsList.setItems(getTestValues());
        listLabel = new Label("Added ingredients"); // move listlabel to its own HBox and Pos.Center_Left
        listBox.getChildren().add(ingredientsList);

        /* TextFields */
        HBox fieldBox = new HBox(20);
        fieldBox.setPrefSize(1086,100);
        fieldBox.setAlignment(Pos.CENTER);

        field_Name = new ComboBox();
        field_Name.setPrefSize(200,30);
        field_Name.setEditable(true);
        field_Name.setPromptText("Name");

        field_Amount = new ComboBox();
        field_Amount.setPrefSize(100,30);
        field_Amount.setEditable(true);
        field_Amount.setPromptText("Amount");

        field_Unit = new ComboBox();
        field_Unit.setPrefSize(60,30);
        field_Unit.setEditable(true);
        field_Unit.setPromptText("Unit");

        button_Enter = new Button("ADD");
        button_Enter.setPrefSize(100,30);
        button_Enter.getStyleClass().add("greenButton");

        fieldBox.getChildren().addAll(field_Name, field_Amount, field_Unit,  button_Enter);

        // TODO: Add more ?

        /* buttons */
        HBox buttonBox = new HBox(20);
        buttonBox.setPrefSize(1086,60);
        buttonBox.setAlignment(Pos.CENTER);

        button_Save = new Button("SAVE");
        button_Save.setPrefSize(200,60);
        button_Save.getStyleClass().add("bigGreenButton");

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
        bottomSpacing.setPrefSize(1086, 20);

        /* Collect to add */
        container.getChildren().addAll(titleBox, spaceBox, listLabel, listBox, fieldBox, buttonBox, bottomSpacing); // move listlabel to its own HBox and Pos.Center_Left
        getChildren().add(container);
    }

    private void goBack() {
        pane.setView(RecipePanes.RecipeListPane);
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
