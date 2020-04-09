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
 * The supplier menu provides the GUI containing information of the Suppliers {@link Entities.Supplier}.
 * @author Paul Moustakas, Tor Stenfeldt
 * @version 3.0
 */

public class SupplierPane extends StackPane {
    public SupplierPane() {

        /*
         * TOP HALF OF SUPPLIER PANE (1/2)
         */

        // TITLE FOR SUPPLIER MENU
        Text textTitle = new Text();
        Font MenuTitle = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        textTitle.setFill(Paint.valueOf("#619f81"));
        textTitle.setFont(MenuTitle);
        textTitle.setText("SUPPLIERS");

        // TOP CONTAINER FOR SUPPLIER MENU TITLE
        HBox    hBoxTitleContainer   =   new HBox(textTitle);
        hBoxTitleContainer.setPrefSize(1036, 65);
        hBoxTitleContainer.setAlignment(Pos.CENTER);
        hBoxTitleContainer.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20 20 0 0");


        // MIDDLE CONTAINER FOR FILTERS IN SUPPLIER MENU. TODO: implement filters
        HBox    hBoxFilterContainer   =   new HBox();
        hBoxFilterContainer.setPrefSize(1036, 40);
        hBoxFilterContainer.setStyle("-fx-border-color: #6B6C6A; -fx-background-color: #FFFFFF");


        // BUTTONS FOR BUTTON BAR (LEFT) ADD, REMOVE, EDIT
        Button buttonAdd = new Button("ADD SUPPLIER");
        Button buttonRemove = new Button("REMOVE SUPPLIER");
        Button buttonEdit = new Button("EDIT SUPPLIER");

        buttonAdd.setStyle(Styles.getButton());
        buttonRemove.setStyle(Styles.getButton());
        buttonEdit.setStyle(Styles.getButton());

        // CONTAINER FOR BUTTON BAR (LEFT) - ADD, REMOVE, EDIT
        HBox    hBoxButtonContainer   =   new HBox(15, buttonAdd, buttonRemove, buttonEdit);
        hBoxButtonContainer.setPrefSize(623, 75);
        hBoxButtonContainer.setStyle("-fx-background-color: #FFFFFF;");
        hBoxButtonContainer.setAlignment(Pos.CENTER_LEFT);

        // SEARCH FIELD & TEXT FOR SEARCH BAR (RIGHT)
        Label labelSearch = new Label("SEARCH:");
        labelSearch.setStyle(Styles.getSearchBar());
        TextField textFieldSearch   =   new TextField("Search");

        // CONTAINER FOR SEARCH BAR (RIGHT) - SEARCH LABEL, SEARCH FIELD
        HBox    hBoxSearchContainer   =   new HBox(15, labelSearch, textFieldSearch);
        hBoxSearchContainer.setPrefSize(413, 75);
        hBoxSearchContainer.setStyle("-fx-background-color: #FFFFFF;");
        hBoxSearchContainer.setAlignment(Pos.CENTER_RIGHT);

        // CONTAINER FOR BUTTON BAR AND SEARCH BAR (LEFT & RIGHT)
        HBox hBoxManagementContainer  =    new HBox(hBoxButtonContainer, hBoxSearchContainer);
        hBoxManagementContainer.setPrefSize(1036, 75);
        hBoxManagementContainer.setStyle("-fx-padding: 0 50 0 50");
        hBoxManagementContainer.setAlignment(Pos.BOTTOM_CENTER);


        // VERTICAL CONTAINER FOR TOP(Title), MID(filter), LOW(buttons and search)
        VBox vBoxTopCollector  =   new VBox(hBoxTitleContainer, hBoxFilterContainer, hBoxManagementContainer);
        vBoxTopCollector.setPrefSize(1036, 190);
        vBoxTopCollector.setAlignment(Pos.BOTTOM_CENTER);
        vBoxTopCollector.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20 20 0 0;");


        /*
         *  BOTTOM HALF OF SUPPLIER PANE (2/2)
         */
        // TODO: add a table to contain
        // CONTAINER FOR SUPPLIER TABLE.
       // TableModel  tableModel = new TableModel();
        HBox hBoxTableContainer = new HBox();
        hBoxTableContainer.setPrefSize(936, 438);
        hBoxTableContainer.setStyle("-fx-background-color: #6B6C6A;");

        // CONTAINER FOR TABLE CONTAINER
        HBox hBoxBottomCollector = new HBox();
        hBoxBottomCollector.setPrefSize(1036, 508);
        hBoxBottomCollector.setStyle("-fx-alignment: center; -fx-background-color: #FFFFFF; -fx-background-radius: 0 0 20 20; -fx-padding: 0 0 50 0;");
        hBoxBottomCollector.getChildren().add(hBoxTableContainer);

        // CONTAINER FOR TOPCOLLECTOR AND BOTTOMCOLLECTOR
        VBox vBoxMainCollector = new VBox();
        vBoxMainCollector.setAlignment(Pos.CENTER);
        vBoxMainCollector.getChildren().addAll(vBoxTopCollector, hBoxBottomCollector);

        // MAIN CONTAINER TO PEG AT PANE
        HBox mainContainer = new HBox(25);
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.getChildren().add(vBoxMainCollector);

        setPrefSize(1086, 768);
        setStyle(Styles.getPane());
        getChildren().add(mainContainer);
    }
}
