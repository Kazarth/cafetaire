package View;

import Entities.Styles;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Supplier Menu.java
 * The supplier menu provides the GUI containing supplier information.
 * @author Paul Moustakas, Tor Stenfeldt
 * @version 2.0
 */
public class SupplierPaneRedux extends StackPane {
    public SupplierPaneRedux() {

        // Title of the pane
        Text textTitle = new Text();
        Font MenuTitle = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        textTitle.setFill(Paint.valueOf("#619f81"));
        textTitle.setFont(MenuTitle);
        textTitle.setText("SUPPLIERS");

        // Contains the title
        HBox    hBoxTitleContainer   =   new HBox(textTitle);
        hBoxTitleContainer.setPrefSize(1036, 65);
        hBoxTitleContainer.setAlignment(Pos.CENTER);
        hBoxTitleContainer.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20 20 0 0");

        // Contains filters etc. TODO: implement filters
        HBox    hBoxFilterContainer   =   new HBox();
        hBoxFilterContainer.setPrefSize(1036, 40);
        hBoxFilterContainer.setStyle("-fx-border-color: #6B6C6A; -fx-background-color: #FFFFFF");

        //Button Bar LEFT
        //Buttons ADD, REMOVE, EDIT
        Button buttonAdd = new Button("ADD SUPPLIER");
        Button buttonRemove = new Button("REMOVE SUPPLIER");
        Button buttonEdit = new Button("EDIT SUPPLIER");

        buttonAdd.setStyle(Styles.getButton());
        buttonRemove.setStyle(Styles.getButton());
        buttonEdit.setStyle(Styles.getButton());

        // Contains the buttons
        HBox    hBoxButtonContainer   =   new HBox(15, buttonAdd, buttonRemove, buttonEdit);
        hBoxButtonContainer.setPrefSize(623, 75);
        hBoxButtonContainer.setStyle("-fx-background-color: #FFFFFF;");
        hBoxButtonContainer.setAlignment(Pos.CENTER_LEFT);

        // Search bar
        Label labelSearch = new Label("SEARCH:");
        labelSearch.setStyle(Styles.getSearchBar());
        TextField textFieldSearch   =   new TextField("ingredient");

        // Contains buttons and the search bar
        HBox    hBoxSearchContainer   =   new HBox(15, labelSearch, textFieldSearch);
        hBoxSearchContainer.setPrefSize(413, 75);
        hBoxSearchContainer.setStyle("-fx-background-color: #FFFFFF;");
        hBoxSearchContainer.setAlignment(Pos.CENTER_RIGHT);

        // Button Bar (LEFT & RIGHT)
        HBox hBoxManagementContainer  =    new HBox(hBoxButtonContainer, hBoxSearchContainer);
        hBoxManagementContainer.setPrefSize(1036, 75);
        hBoxManagementContainer.setStyle("-fx-padding: 0 50 0 50");
        hBoxManagementContainer.setAlignment(Pos.BOTTOM_CENTER);

        // Vertical Box  BARS(TOP Title, MID sup, Button)
        VBox vBoxTopCollector  =   new VBox(hBoxTitleContainer, hBoxFilterContainer, hBoxManagementContainer);
        vBoxTopCollector.setPrefSize(1036, 190);
        vBoxTopCollector.setAlignment(Pos.BOTTOM_CENTER);
        vBoxTopCollector.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20 20 0 0;");

        // TODO: add a table to contain
        HBox hBoxTableContainer = new HBox();
        hBoxTableContainer.setPrefSize(936, 438);
        hBoxTableContainer.setStyle("-fx-background-color: #6B6C6A;");

        // Contains the table box
        HBox hBoxBottomCollector = new HBox();
        hBoxBottomCollector.setPrefSize(1036, 508);
        hBoxBottomCollector.setStyle("-fx-alignment: center; -fx-background-color: #FFFFFF; -fx-background-radius: 0 0 20 20; -fx-padding: 0 0 50 0;");
        hBoxBottomCollector.getChildren().add(hBoxTableContainer);

        // Contains the upper and lower collector boxes
        VBox vBoxMainCollector = new VBox();
        vBoxMainCollector.setAlignment(Pos.CENTER);
        vBoxMainCollector.getChildren().addAll(vBoxTopCollector, hBoxBottomCollector);

        // The main box added to the pane
        HBox mainContainer = new HBox(25);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.getChildren().add(vBoxMainCollector);

        setPrefSize(1086, 768);
        setStyle(Styles.getPane());
        getChildren().add(mainContainer);
    }
}
