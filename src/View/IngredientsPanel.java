package View;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

public class IngredientsPanel extends Application {

    //***** Panes and Layouts *****
    private BorderPane borderPane;
    private VBox mainVbox;
    private HBox topHBox;
    private HBox midHBox;
    private HBox bottomHBox;
    private Scene ingredientsScene;

    //***** Buttons and components *****

    private Button addIngredients;
    private Button removeIngredients;

    private TextField searchTextField;

    private Button addButton;
    private Button removeButton;



    //***** Styling *****

    private Text titleText;
    private Text overView;
    private Color seaDarkGreen;
    private Font font;
    private Border border;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        addIngredients = new Button("ADD NEW INGREDIENT");
        removeIngredients = new Button("REMOVE INGREDIENT");

        titleText = new Text("INGREDIENTS");
        overView = new Text("OVERVIEW");

        addButton = new Button("ADD");
        removeButton = new Button("REMOVE");

        searchTextField = new TextField("SEARCH");

        seaDarkGreen = Color.web("#619f81");
        font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20);

        borderPane = new BorderPane();

        mainVbox = new VBox();
        borderPane.setTop(mainVbox);
        mainVbox.setPrefSize(1366,225);

        topHBox = new HBox();
        topHBox.setPrefSize(1366,85);
        mainVbox.getChildren().add(topHBox);
        topHBox.getChildren().add(titleText);
        topHBox.setAlignment(Pos.CENTER);
        titleText.setFont(font);
        titleText.setFill(seaDarkGreen);

        midHBox = new HBox();
        midHBox.setPrefSize(1366,50);
        mainVbox.getChildren().add(midHBox);
        midHBox.setStyle("-fx-background-color: #21252B;");
        midHBox.setStyle("-fx-border-color: lightgray;");
        midHBox.getChildren().add(overView);
        midHBox.setAlignment(Pos.CENTER);
        overView.setFont(Font.font("verdana",FontWeight.LIGHT,FontPosture.REGULAR,15));
        overView.setFill(Color.BLACK);

        bottomHBox = new HBox();
        bottomHBox.setPrefSize(1366,85);
        mainVbox.getChildren().add(bottomHBox);
        bottomHBox.setStyle("-fx-color: #619f81");
        bottomHBox.getChildren().addAll(addIngredients,removeIngredients,searchTextField,addButton,removeButton);
        bottomHBox.setAlignment(Pos.CENTER);
        bottomHBox.setMargin(addIngredients,new Insets(10));
        bottomHBox.setMargin(removeIngredients,new Insets(10));
        bottomHBox.setMargin(searchTextField,new Insets(10));
        bottomHBox.setMargin(addButton,new Insets(10));
        bottomHBox.setMargin(removeButton,new Insets(10));





        Scene ingredientsScene = new Scene(borderPane,1366,768);
        primaryStage.setScene(ingredientsScene);
        primaryStage.show();

    }
}
