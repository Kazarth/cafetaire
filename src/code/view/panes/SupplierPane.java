package code.view.panes;

import code.control.Callback;
import code.entities.Ingredient;
import code.entities.Styles;
import code.entities.Supplier;
import code.view.popups.SupplierPopup;
import com.sun.jdi.event.ExceptionEvent;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import org.junit.Ignore;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Supplier Menu.java
 * The supplier menu provides the GUI containing information of the Suppliers {@link code.entities.Supplier}.
 * @author Paul Moustakas, Tor Stenfeldt
 * @version 3.0
 */
public class SupplierPane extends StackPane {
    private TableView<Supplier> tableView;
    private TableColumn<Supplier, String> supplierColumn;
    private TableColumn<Supplier, String> categoryColumn;
    private TableColumn<Supplier, String> emailColumn;
    private TableColumn<Supplier, Integer> phoneColumn;
    private Callback callback;
    private TextField textField_Search;

    public SupplierPane(Callback callback) {
        this.callback = callback;
        this.getStylesheets().add("styles.css");

        setTableView();

        // TITLE FOR SUPPLIER MENU
        Text textTitle = new Text();
        Font MenuTitle = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        textTitle.setFill(Paint.valueOf("#619f81"));
        textTitle.setFont(MenuTitle);
        textTitle.setText("SUPPLIERS");

        // TOP CONTAINER FOR SUPPLIER MENU TITLE
        HBox    hBoxTitleContainer   =   new HBox(textTitle);
        hBoxTitleContainer.setPrefSize(1036, 75);
        hBoxTitleContainer.setAlignment(Pos.CENTER);
        hBoxTitleContainer.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20 20 0 0");

        // MIDDLE CONTAINER FOR FILTERS IN SUPPLIER MENU. TODO: implement filters
        HBox    hBoxFilterContainer   =   new HBox();
        hBoxFilterContainer.setPrefSize(1036, 40);
        hBoxFilterContainer.setStyle("-fx-border-color: #6B6C6A; -fx-background-color: #FFFFFF; -fx-border-width: 1 0 1 0");

        // BUTTONS FOR BUTTON BAR (LEFT) ADD, REMOVE, EDIT
        Button buttonAdd = new Button("ADD SUPPLIER");
        buttonAdd.setOnAction(e -> addNewSupplierAction());
        Button buttonRemove = new Button("REMOVE SUPPLIER");
        buttonRemove.setOnAction(e -> removeSupplier());
        Button buttonEdit = new Button("EDIT SUPPLIER");
        buttonEdit.setOnAction(e -> editSupplierAction());


        buttonAdd.getStyleClass().add("greenButtonPanel");
        buttonAdd.setPrefWidth(170);
        buttonAdd.setPrefHeight(40);

        buttonRemove.getStyleClass().add("greenButtonPanel");
        buttonRemove.setPrefWidth(170);
        buttonRemove.setPrefHeight(40);

        buttonEdit.getStyleClass().add("greenButtonPanel");
        buttonEdit.setPrefWidth(170);
        buttonEdit.setPrefHeight(40);


        // CONTAINER FOR BUTTON BAR (LEFT) - ADD, REMOVE, EDIT
        HBox    hBoxButtonContainer   =   new HBox(10, buttonAdd, buttonRemove, buttonEdit);
        hBoxButtonContainer.setPrefSize(623, 75);
        hBoxButtonContainer.setStyle("-fx-background-color: #FFFFFF;");
        hBoxButtonContainer.setAlignment(Pos.CENTER_LEFT);

        Button buttonSearch = new Button();

        buttonSearch.getStyleClass().add("greenButtonPanel");
        buttonSearch.setPrefWidth(40);
        buttonSearch.setPrefHeight(40);


        buttonSearch.setOnAction(e -> search());
        try {
            Image selectedImage = new Image(new FileInputStream("src/resources/search.png"));
            ImageView selectedView = new ImageView(selectedImage);
            selectedView.setFitWidth(20);
            selectedView.setFitHeight(20);
            buttonSearch.setGraphic(selectedView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Label labelSearch = new Label("SEARCH:");
        labelSearch.setStyle(Styles.getSearchBar());
        textField_Search   =   new TextField();
        textField_Search.setPrefSize(160, 40);
        textField_Search.setPromptText("Search");
        textField_Search.textProperty().addListener(this :: searchRecord);

        // CONTAINER FOR SEARCH BAR (RIGHT) - SEARCH LABEL, SEARCH FIELD
        HBox    hBoxSearchContainer   =   new HBox(10, labelSearch, textField_Search, buttonSearch);
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
        tableView = new TableView<>();
        tableView.setStyle(Styles.getTableRowSelected());
        setPrefSize(1068,768);

        supplierColumn = new TableColumn<>("Supplier");
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        supplierColumn.setStyle(Styles.getTableColumn());
        categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryColumn.setStyle(Styles.getTableColumn());
        emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setStyle(Styles.getTableColumn());
        phoneColumn = new TableColumn<>("Phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneColumn.setStyle(Styles.getTableColumn());

        tableView.getColumns().addAll(supplierColumn, categoryColumn, emailColumn, phoneColumn);

        tableView.setItems(getSuppliersFromDatabase());

        supplierColumn.setPrefWidth(233);
        categoryColumn.setPrefWidth(234);
        emailColumn.setPrefWidth(234);
        phoneColumn.setPrefWidth(234);
    }

    /**
     * Gets suppliers from Database.java
     * @return list of Supplier
     */
    private ObservableList<Supplier> getSuppliersFromDatabase() {
        ObservableList<Supplier> listSuppliers = FXCollections.observableArrayList();
        ArrayList<Supplier> receivedSuppliers = callback.getSuppliers();
        listSuppliers.addAll(receivedSuppliers);
        return listSuppliers;
    }

    public void addNewSupplier(Supplier supplier) {
        tableView.getItems().add(supplier);
    }


    /**
     * Add new Supplier
     */
    public void addNewSupplierAction() {
        resetSearchField();
        try {
            new SupplierPopup(this, callback, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
  
    public void expand() {
        setPrefWidth(1346);
        System.out.println("Expanding");
    }

    public void contract() {
        setPrefWidth(1086);
        System.out.println("Contracting");
    }

    /**
     * Method used to edit a supplier in the tableView
     */
    public void editSupplierAction(){
        resetSearchField();
        String name = tableView.getSelectionModel().getSelectedItem().getName();
        SupplierPopup pane;

        if (name != null) {
            try {
                pane = new SupplierPopup(this, callback, 1);
                Supplier supplier = callback.getSupplier(name);
                pane.setOrgSupp(name);
                pane.setValuesForSupplier(supplier.getName(), supplier.getCategory(), supplier.getEmail(), supplier.getPhone());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Removes selected supplier
     */
    public void removeSupplier() {
        resetSearchField();
        ObservableList<Supplier> supplierSelected, allSuppliers;
        allSuppliers = tableView.getItems();
        supplierSelected = tableView.getSelectionModel().getSelectedItems();
        Supplier supplier = tableView.getSelectionModel().getSelectedItem();

        try {
            supplierSelected.forEach(allSuppliers::remove);
            callback.removeSupplier(supplier.getName());

        }catch (Exception e) {
            System.err.println("Last element - NullPointer \nRemoveSupplier \nSupplierPane Row 279");
            callback.catchSafeState();
        }

    }

    private void search() {
        System.out.println("SEARCH");
    }

    public void refresh(){
        tableView.refresh();
    }


    /**
     * Searchbar functionality.
     */
    private void searchRecord(Observable observable, String oldValue, String newValue) {

        FilteredList<Supplier> filteredList = new FilteredList<>(getSuppliersFromDatabase(), p -> true);

        if (!textField_Search.getText().equals("")) {
            filteredList.setPredicate(tableView -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String typedText = newValue.toLowerCase();

                if (tableView.getSupplierName().toLowerCase().contains(typedText)) {
                    return true;

                } else if (tableView.getCategory() != null) {
                    tableView.getCategory().toLowerCase().contains(typedText);
                    return true;


                } else if (String.valueOf(tableView.getEmail()).toLowerCase().contains(typedText)) {
                    return true;

                } else if (tableView.getPhone().toLowerCase().contains(typedText)) {
                    return true;

                } else
                    return false;
            });

            SortedList<Supplier> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedList);

        } else
            tableView.setItems(getSuppliersFromDatabase());
    }

    public void resetSearchField () {
        textField_Search.setText("");
    }

}
