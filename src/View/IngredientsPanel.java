package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The class is the Ingredients panel for the Cafetairé application.
 * @author Georg Grankvist
 * @version 1.0
 */



public class IngredientsPanel extends Application {

    //***** Panes and Layouts *****
    private BorderPane borderPane;
    private VBox mainVbox;
    private HBox topHBox;
    private HBox midHBox;
    private HBox bottomHBox;
    private HBox westHBOx;
    private HBox eastHBox;
    private Scene ingredientsScene;
    private TableView tableView;

    //***** Buttons and components *****

    private Button addIngredients;
    private Button removeIngredients;

    private TextField searchTextField;

    private Button addButton;
    private Button removeButton;

    private TableColumn nameColumn;
    private TableColumn categoryColumn;
    private TableColumn stockColumn;
    private TableColumn supplierColumn;
    private TableColumn selectedColumn;


    //***** Styling *****

    private Text titleText;
    private Text overView;
    private Color seaDarkGreen;
    private String buttonStyle = (
            "-fx-background-color: #619f81;" +
            " -fx-text-fill: #FFFFFF;" +
            " -fx-font-family: Segoe UI;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 16;"
            );


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        addIngredients = new Button("ADD NEW INGREDIENT");
        addIngredients.setStyle(buttonStyle);
        addIngredients.setPrefWidth(200);
        addIngredients.setPrefHeight(30);
        removeIngredients = new Button("REMOVE INGREDIENT");

        removeIngredients.setStyle(buttonStyle);
        removeIngredients.setPrefHeight(30);
        removeIngredients.setPrefWidth(200);

        titleText = new Text("INGREDIENTS");
        overView = new Text("OVERVIEW");

        addButton = new Button("ADD");
        addButton.setPrefHeight(30);
        addButton.setPrefWidth(100);
        addButton.setStyle(buttonStyle);

        removeButton = new Button("REMOVE");
        removeButton.setPrefHeight(30);
        removeButton.setPrefWidth(100);
        removeButton.setStyle(buttonStyle);

        searchTextField = new TextField("SEARCH");
        searchTextField.setPrefHeight(32);
        searchTextField.setPrefWidth(250);

        seaDarkGreen = Color.web("#619f81");

        tableView = new TableView();
        borderPane = new BorderPane();
        borderPane.setCenter(tableView);

        nameColumn = new TableColumn("NAME");
        categoryColumn = new TableColumn("CATEGORY");
        stockColumn = new TableColumn("STOCK");
        supplierColumn = new TableColumn("SUPPLIER");
        selectedColumn = new TableColumn("SELECTED ITEM");

        tableView.setStyle("-fx-background-color: #FFFFFF");
        tableView.getColumns().addAll(nameColumn,categoryColumn,stockColumn,supplierColumn,selectedColumn);

        nameColumn.setPrefWidth(200);
        categoryColumn.setPrefWidth(200);
        stockColumn.setPrefWidth(200);
        supplierColumn.setPrefWidth(200);
        selectedColumn.setPrefWidth(250);


        mainVbox = new VBox();
        borderPane.setTop(mainVbox);
        mainVbox.setPrefSize(1366,225);

        topHBox = new HBox();
        topHBox.setPrefSize(1366,85);
        mainVbox.getChildren().add(topHBox);
        topHBox.getChildren().add(titleText);
        topHBox.setAlignment(Pos.CENTER);

        titleText.setFill(seaDarkGreen);
        titleText.setFont(Font.font("Segoe UI", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 40));

        midHBox = new HBox();
        midHBox.setPrefSize(1366,50);
        mainVbox.getChildren().add(midHBox);
        midHBox.setStyle("-fx-background-color: #21252B;");
        midHBox.setStyle("-fx-border-color: lightgray;");
        midHBox.getChildren().add(overView);
        midHBox.setAlignment(Pos.CENTER);
        overView.setFont(Font.font("Segoe UI", FontWeight.LIGHT, FontPosture.REGULAR, 20));
        overView.setFill(Color.BLACK);

        bottomHBox = new HBox();
        westHBOx = new HBox();
        eastHBox = new HBox();
        westHBOx.setPrefSize(683,85);
        eastHBox.setPrefSize(683,85);
        bottomHBox.setPrefSize(1366,85);
        mainVbox.getChildren().add(bottomHBox);
        bottomHBox.getChildren().addAll(westHBOx,eastHBox);
        bottomHBox.setStyle("-fx-color: #619f81");
        westHBOx.setAlignment(Pos.CENTER);
        westHBOx.getChildren().addAll(addIngredients,removeIngredients);
        westHBOx.setSpacing(20);
        eastHBox.setAlignment(Pos.CENTER);
        eastHBox.getChildren().addAll(searchTextField ,addButton,removeButton);
        eastHBox.setSpacing(20);

        ingredientsScene = new Scene(borderPane,1086,768);
        primaryStage.setScene(ingredientsScene);
        primaryStage.show();

    }
}
