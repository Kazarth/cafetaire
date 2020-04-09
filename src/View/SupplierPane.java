package View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Supplier Menu.java
 * The supplier menu provides the GUI containing supplier information.
 * @author Paul Moustakas
 * @version 1.0
 */

public class SupplierPane extends BorderPane {
    public SupplierPane() {
        //Top Supplier Bar
        Text textSuppMenu   = new Text();
        Font MenuTitle      = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 36);
        textSuppMenu.setFill(Paint.valueOf("#619f81"));
        textSuppMenu.setFont(MenuTitle);
        textSuppMenu.setText("SUPPLIERS");

        HBox    hBoxTopLblBar   =   new HBox(textSuppMenu);
        hBoxTopLblBar.setPrefSize(1086, 85);
        hBoxTopLblBar.setAlignment(Pos.CENTER);
        hBoxTopLblBar.setStyle("-fx-background-color: #FFFFFF;");

        //Middle Supplier Bar
        HBox    hBoxTopMidBar   =   new HBox();
        hBoxTopMidBar.setPrefSize(1086, 50);
        hBoxTopMidBar.setStyle("-fx-border-color: #6B6C6A; -fx-background-color: #FFFFFF");

        //Button Bar LEFT
        //Buttons ADD, REMOVE, EDIT
        Button btnAddNewIng = new Button("ADD NEW INGREDIENT");
        Button btnRemoveIng = new Button("REMOVE INGREDIENT");
        Button btnEditSupp = new Button("EDIT SUPPLIER");

        String buttonStyle = (
                "-fx-background-color: #619f81;" +
                " -fx-text-fill: #FFFFFF;" +
                " -fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16;"
        );

        btnAddNewIng.setStyle(buttonStyle);
        btnRemoveIng.setStyle(buttonStyle);
        btnEditSupp.setStyle(buttonStyle);

        HBox    hBoxButtonBarLeft   =   new HBox(20, btnAddNewIng, btnRemoveIng, btnEditSupp);
        hBoxButtonBarLeft.setPrefSize(543, 85);
        hBoxButtonBarLeft.setStyle("-fx-background-color: #FFFFFF;");
        hBoxButtonBarLeft.setAlignment(Pos.CENTER);

        //Button Bar Right
        String search = (
                "-fx-text-fill: BLACK;" +
                "-fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16;"
        );

        Label lblSearch = new Label("SEARCH");
        lblSearch.setStyle(search);
        TextField textFieldSearch   =   new TextField("search");

        HBox    hBoxButtonBarRight   =   new HBox(20, lblSearch, textFieldSearch);
        hBoxButtonBarRight.setPrefSize(543, 85);
        hBoxButtonBarRight.setStyle("-fx-background-color: #FFFFFF;");
        hBoxButtonBarRight.setAlignment(Pos.CENTER);

        // Button Bar (LEFT & RIGHT)
        HBox hBoxButtonBar  =    new HBox(hBoxButtonBarLeft, hBoxButtonBarRight);

        // Vertical Box  BARS(TOP Title, MID sup, Button)
        VBox vBoxMainTopBar  =   new VBox(hBoxTopLblBar, hBoxTopMidBar, hBoxButtonBar);
        //vBoxMainTopBar.setPrefSize(1086, 225);
        //setTop(vBoxMainTopBar);


        HBox mainBox = new HBox(25);
        mainBox.getChildren().add(vBoxMainTopBar);
        mainBox.setStyle("-fx-background-radius: 20 20 20 20;");

        setPrefSize(1086, 768);
        setTop(mainBox);

        // Center Panel for Table
        HBox    hBoxCenterTable =   new HBox();
    }
}
