package View;

import Control.Callback;
import Entities.IngredientTest;
import Entities.Styles;
import Entities.Supplier;
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
import javafx.scene.text.Text;


/**
 * Supplier Menu.java
 * The supplier menu provides the GUI containing information of the Suppliers {@link Entities.Supplier}.
 * @author Paul Moustakas, Tor Stenfeldt
 * @version 3.0
 */

public class SupplierPane extends StackPane {
    private TableView<Supplier> tableView;
    private TableColumn<Supplier, String> supplierColumn;
    private TableColumn<Supplier, String> categoryColumn;
    private TableColumn<Supplier, String> emailColumn;
    private TableColumn<Supplier, Integer> phoneColumn;
    //private TableColumn<Supplier, String> supplierColumn;


    public SupplierPane(Callback callback) {

        setTableView();

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

        Button buttonSearch = new Button("O");
        buttonSearch.setStyle(Styles.getButton());

        Label labelSearch = new Label("SEARCH:");
        labelSearch.setStyle(Styles.getSearchBar());
        TextField textFieldSearch   =   new TextField("Search");

        // CONTAINER FOR SEARCH BAR (RIGHT) - SEARCH LABEL, SEARCH FIELD
        HBox    hBoxSearchContainer   =   new HBox(15, labelSearch, textFieldSearch, buttonSearch);
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

        // CONTAINER FOR SUPPLIER TABLE
        HBox hBoxTableContainer = new HBox();
        hBoxTableContainer.setStyle("-fx-alignment: center;" + "-fx-background-color: #EEE;" + "");

        //tableBox.setPrefSize(1036, 611);
        hBoxTableContainer.getChildren().add(tableView);

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

    public void setTableView () {

        tableView = new TableView();
        setPrefSize(1068,768);
        //setCenter(tableView); // Deala med denna så att det passar.

        supplierColumn = new TableColumn("Supplier");
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
       // supplierColumn.setStyle();
        categoryColumn = new TableColumn("category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        emailColumn = new TableColumn("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn = new TableColumn("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        tableView.getColumns().addAll(supplierColumn, categoryColumn, emailColumn, phoneColumn);

        tableView.setItems(getSuppliers());

        supplierColumn.setPrefWidth(233);
        categoryColumn.setPrefWidth(234);
        emailColumn.setPrefWidth(234);
        phoneColumn.setPrefWidth(234);
        //selectedColumn.setPrefWidth(250);
    }

    private ObservableList<Supplier> getSuppliers() {

        ObservableList<Supplier> suppliers = FXCollections.observableArrayList();
        suppliers.add(new Supplier("Coca Cola AB", "Dryck", "CocaCola@cocacolacompany.com", "0431-1337"));
        return suppliers;
    }

}
