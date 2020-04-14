package View;

import Entities.Styles;
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


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        /** Button instantiation and Configurations */

        Button addIngredients = new Button("ADD NEW INGREDIENT");
        addIngredients.setStyle(Styles.getButton());
        addIngredients.setPrefWidth(200);
        addIngredients.setPrefHeight(30);

        Button removeIngredients = new Button("REMOVE INGREDIENT");
        removeIngredients.setStyle(Styles.getButton());
        removeIngredients.setPrefHeight(30);
        removeIngredients.setPrefWidth(200);

        Button addButton = new Button("ADD");
        addButton.setPrefHeight(30);
        addButton.setPrefWidth(100);
        addButton.setStyle(Styles.getButton());

         Button removeButton = new Button("REMOVE");
        removeButton.setPrefHeight(30);
        removeButton.setPrefWidth(100);
        removeButton.setStyle(Styles.getButton());

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

        TableView tableView = new TableView();
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(tableView);

        TableColumn nameColumn = new TableColumn("NAME");
        TableColumn categoryColumn = new TableColumn("CATEGORY");
        TableColumn stockColumn = new TableColumn("STOCK");
        TableColumn supplierColumn  = new TableColumn("SUPPLIER");
        TableColumn selectedColumn = new TableColumn("SELECTED ITEM");

        tableView.setStyle("-fx-background-color: #FFFFFF");
        tableView.getColumns().addAll(nameColumn,categoryColumn,stockColumn,supplierColumn,selectedColumn);

        nameColumn.setPrefWidth(200);
        categoryColumn.setPrefWidth(200);
        stockColumn.setPrefWidth(200);
        supplierColumn.setPrefWidth(200);
        selectedColumn.setPrefWidth(250);

        /** LayoutPane configurations and instantiation.
         *  VBOX, HBOX. */

        VBox mainVbox = new VBox();
        HBox topHBox = new HBox();
        HBox midHBox = new HBox();
        HBox bottomHBox = new HBox();
        HBox westHBOx = new HBox();
        HBox eastHBox = new HBox();
        borderPane.setTop(mainVbox);

        mainVbox.setPrefSize(1366,225);
        topHBox.setPrefSize(1366,85);
        midHBox.setPrefSize(1366,50);
        bottomHBox.setPrefSize(1366,85);
        westHBOx.setPrefSize(683,85);
        eastHBox.setPrefSize(683,85);

        mainVbox.getChildren().add(topHBox);
        topHBox.getChildren().add(titleText);
        mainVbox.getChildren().add(midHBox);
        midHBox.getChildren().add(overView);
        mainVbox.getChildren().add(bottomHBox);
        bottomHBox.getChildren().addAll(westHBOx,eastHBox);
        westHBOx.getChildren().addAll(addIngredients,removeIngredients);
        eastHBox.getChildren().addAll(searchTextField ,addButton,removeButton);

        topHBox.setAlignment(Pos.CENTER);
        midHBox.setAlignment(Pos.CENTER);
        westHBOx.setAlignment(Pos.CENTER);
        eastHBox.setAlignment(Pos.CENTER);

        midHBox.setStyle("-fx-background-color: #21252B;");
        midHBox.setStyle("-fx-border-color: lightgray;");
        bottomHBox.setStyle("-fx-color: #619f81");

        westHBOx.setSpacing(20);
        eastHBox.setSpacing(20);

        Scene ingredientsScene = new Scene(borderPane,1086,768);
        primaryStage.setScene(ingredientsScene);
        primaryStage.show();

    }
}
