package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SupplierPanel extends Application {

    private Button  btnAddNewIng;
    private Button  btnRemoveIng;
    private Button  btnEditSupp;
    //Colours
    private Color   cDeepGreen  = Color.web("#619f81");

    //Layout
    String buttonStyle =  (
            "-fx-background-color: #619f81;" +
            " -fx-border-color: #6B6C6A;" +
            " -fx-text-fill: #FFFFFF;" +
            " -fx-font-family: Segoe UI;" +
            "-fx-font-weight: bold;" +
            "-fx-font-size: 16;"
    );


    @Override
    public void start(Stage supplierStage) throws Exception {

        supplierStage.setTitle("Suppliers");
        BorderPane layout   =   new BorderPane();

        //Top Supplier Bar
        Text textSuppMenu   = new Text();
        Font MenuTitle      = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 36);
        textSuppMenu.setFill(cDeepGreen);
        textSuppMenu.setFont(MenuTitle);
        textSuppMenu.setText("SUPPLIERS");

        HBox    hBoxTopLblBar   =   new HBox(textSuppMenu);
        hBoxTopLblBar.setPrefSize(1366, 85);
        hBoxTopLblBar.setAlignment(Pos.CENTER);
        hBoxTopLblBar.setStyle("-fx-background-color: #FFFFFF;");


        //Middle Supplier Bar
        HBox    hBoxTopMidBar   =   new HBox();
        hBoxTopMidBar.setPrefSize(1366, 50);
        hBoxTopMidBar.setStyle("-fx-border-color: #6B6C6A; -fx-background-color: #FFFFFF");

        //Button Bar
        btnAddNewIng    =   new Button("ADD NEW INGREDIENT");
        btnRemoveIng    =   new Button("REMOVE INGREDIENT");
        btnEditSupp     =   new Button("EDIT SUPPLIER");

        //Font Buttons
        btnAddNewIng.setStyle(buttonStyle);
        btnRemoveIng.setStyle(buttonStyle);
        btnEditSupp.setStyle(buttonStyle);


        HBox    hBoxButtonBar   =   new HBox(20, btnAddNewIng, btnRemoveIng, btnEditSupp);
        hBoxButtonBar.setPrefSize(1366, 85);
        hBoxButtonBar.setStyle("-fx-background-color: #FFFFFF;");
        hBoxButtonBar.setAlignment(Pos.CENTER);

        VBox vBoxMainTopBar  =   new VBox(hBoxTopLblBar, hBoxTopMidBar, hBoxButtonBar);
        vBoxMainTopBar.setPrefSize(1366, 225);

        layout.setTop(vBoxMainTopBar);


        Scene supplierScene =   new Scene(layout, 1366, 768);
        supplierStage.setScene(supplierScene);
        supplierStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
