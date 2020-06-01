package code.view.panes;

import code.control.Callback;
import code.entities.Styles;
import code.entities.Supplier;
import code.view.popups.SupplierPopup;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Supplier Menu.java
 * The supplier menu provides the GUI containing information of the Suppliers {@link code.entities.Supplier}.
 * @author Paul Moustakas, Tor Stenfeldt
 * @version 3.0
 */
public class SupplierPane extends Pane implements EnhancedPane {
    private Callback callback;

    private VBox mainContainer;
    private HBox hBox_menu;
    private HBox hBox_SearchContainer;

    private FlowPane flowPane_tableContainer;

    private TextField textField_Search;
    private TableView<Supplier> tableView;
    private TableColumn<Supplier, String> supplierColumn;
    private TableColumn<Supplier, String> categoryColumn;
    private TableColumn<Supplier, String> emailColumn;
    private TableColumn<Supplier, Integer> phoneColumn;

    public SupplierPane(Callback callback) {
        this.callback = callback;
        HBox title = initTitle();

        // Separates the title from the buttons
        HBox hBox_filler = new HBox();
        hBox_filler.setPrefSize(1014, 40);
        hBox_filler.setStyle(
                "-fx-border-color: #6B6C6A;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-width: 1 0 1 0"
        );

        initMenu();

        // Contains the title, filler and menu bars
        VBox topBars = new VBox(title, hBox_filler, this.hBox_menu);
        topBars.setPrefSize(1014, 190);
        topBars.setAlignment(Pos.BOTTOM_CENTER);
        topBars.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 0 0;"
        );

        initTable();

        this.mainContainer = new VBox();
        this.mainContainer.setStyle(Styles.getPane());
        this.mainContainer.setPrefSize(1014, 695);
        this.mainContainer.setLayoutX(20);
        this.mainContainer.setLayoutY(20);
        this.mainContainer.getChildren().addAll(topBars, this.flowPane_tableContainer);

        getStylesheets().add("styles.css");
        setStyle(Styles.getPane());
        setPrefSize(1054, 736);
        getChildren().add(this.mainContainer);
    }

    private HBox initTitle() {
        Text title = new Text("SUPPLIERS");
        title.setStyle(Styles.getTitle());
        title.setFill(Paint.valueOf("#619f81"));

        HBox titleContainer = new HBox(title);
        titleContainer.setPrefSize(1014, 75);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 0 0"
        );

        return titleContainer;
    }

    private void initMenu() {
        // BUTTONS FOR BUTTON BAR (LEFT) ADD, REMOVE, EDIT
        Button buttonAdd = new Button("ADD SUPPLIER");
        buttonAdd.getStyleClass().add("greenButtonPanel");
        buttonAdd.setMinWidth(170);
        buttonAdd.setPrefHeight(40);
        buttonAdd.setOnAction(e -> addNewSupplierAction());

        Button buttonRemove = new Button("REMOVE SUPPLIER");
        buttonRemove.getStyleClass().add("greenButtonPanel");
        buttonRemove.setMinWidth(170);
        buttonRemove.setPrefHeight(40);
        buttonRemove.setOnAction(e -> removeSupplier());

        Button buttonEdit = new Button("EDIT SUPPLIER");
        buttonEdit.getStyleClass().add("greenButtonPanel");
        buttonEdit.setMinWidth(170);
        buttonEdit.setPrefHeight(40);
        buttonEdit.setOnAction(e -> editSupplierAction());

        // CONTAINER FOR BUTTON BAR (LEFT) - ADD, REMOVE, EDIT
        HBox buttons = new HBox(10, buttonAdd, buttonRemove, buttonEdit);
        buttons.setMinSize(580, 75);
        buttons.setPrefSize(580, 75);
        buttons.setAlignment(Pos.CENTER_LEFT);
        buttons.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-padding: 0 50 0 50"
        );

        Button button_Search = new Button();
        button_Search.getStyleClass().add("greenButtonPanel");
        button_Search.setPrefWidth(40);
        button_Search.setPrefHeight(40);
        button_Search.setOnAction(e -> search());

        try {
            Image selectedImage = new Image(new FileInputStream("src/resources/search.png"));
            ImageView selectedView = new ImageView(selectedImage);
            selectedView.setFitWidth(20);
            selectedView.setFitHeight(20);
            button_Search.setGraphic(selectedView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Label labelSearch = new Label();
        labelSearch.setStyle(Styles.getSearchBar());
        this.textField_Search = new TextField();
        this.textField_Search.setPrefSize(160, 40);
        this.textField_Search.setPromptText("Search");
        this.textField_Search.textProperty().addListener(this :: searchRecord);

        // CONTAINER FOR SEARCH BAR (RIGHT) - SEARCH LABEL, SEARCH FIELD
        this.hBox_SearchContainer = new HBox(10, labelSearch, this.textField_Search, button_Search);
        this.hBox_SearchContainer.setMinSize(380, 75);
        this.hBox_SearchContainer.setPrefSize(434, 75);
        this.hBox_SearchContainer.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-padding: 0 50 0 50;"
        );
        this.hBox_SearchContainer.setAlignment(Pos.CENTER_RIGHT);

        // CONTAINER FOR BUTTON BAR AND SEARCH BAR (LEFT & RIGHT)
        this.hBox_menu = new HBox(buttons, this.hBox_SearchContainer);
        this.hBox_menu.setPrefSize(1014, 75);
        this.hBox_menu.setAlignment(Pos.BOTTOM_CENTER);
    }

    private void initTable() {
        this.supplierColumn = new TableColumn<>("Supplier");
        this.supplierColumn.setStyle(Styles.getTableColumn());
        this.supplierColumn.setPrefWidth(228);
        this.supplierColumn.setResizable(false);
        this.supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        this.categoryColumn = new TableColumn<>("Category");
        this.categoryColumn.setStyle(Styles.getTableColumn());
        this.categoryColumn.setPrefWidth(229);
        this.categoryColumn.setResizable(false);
        this.categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        this.emailColumn = new TableColumn<>("Email");
        this.emailColumn.setStyle(Styles.getTableColumn());
        this.emailColumn.setPrefWidth(229);
        this.emailColumn.setResizable(false);
        this.emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        this.phoneColumn = new TableColumn<>("Phone");
        this.phoneColumn.setStyle(Styles.getTableColumn());
        this.phoneColumn.setPrefWidth(228);
        this.phoneColumn.setResizable(false);
        this.phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        this.tableView = new TableView<>();
        this.tableView.setStyle(Styles.getTableRowSelected());
        this.tableView.setPrefSize(914,465);
        this.tableView.getColumns().addAll(this.supplierColumn, this.categoryColumn, this.emailColumn, this.phoneColumn);
        this.tableView.setItems(getSuppliersFromDatabase());

        this.flowPane_tableContainer = new FlowPane();
        this.flowPane_tableContainer.setPrefSize(1014, 505);
        this.flowPane_tableContainer.setAlignment(Pos.CENTER);
        this.flowPane_tableContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.flowPane_tableContainer.setStyle(
                "-fx-alignment: center;" +
                " -fx-background-color: #fff;" +
                " -fx-background-radius: 0 0 20 20;" +
                " -fx-padding: 0 0 50 0;"
        );
        this.flowPane_tableContainer.getChildren().add(this.tableView);
    }

    public void expand() {
        setPrefWidth(1200);
        this.mainContainer.setPrefWidth(1160);

        this.hBox_menu.setPrefWidth(1160);
        this.hBox_SearchContainer.setPrefWidth(580);
        this.flowPane_tableContainer.setPrefWidth(1160);

        this.tableView.setPrefWidth(1062);
        this.supplierColumn.setPrefWidth(265);
        this.categoryColumn.setPrefWidth(265);
        this.emailColumn.setPrefWidth(265);
        this.phoneColumn.setPrefWidth(265);
    }

    public void contract() {
        setPrefWidth(1054);
        this.mainContainer.setPrefWidth(1014);

        this.hBox_menu.setPrefWidth(1014);
        this.hBox_SearchContainer.setPrefWidth(434);
        this.flowPane_tableContainer.setPrefWidth(1014);

        this.tableView.setPrefWidth(916);
        this.supplierColumn.setPrefWidth(228);
        this.categoryColumn.setPrefWidth(229);
        this.emailColumn.setPrefWidth(229);
        this.phoneColumn.setPrefWidth(228);
    }

    public void refresh(){
        this.tableView.refresh();
    }

    /**
     * Gets suppliers from Database.java
     * @return list of Supplier
     */
    private ObservableList<Supplier> getSuppliersFromDatabase() {
        ObservableList<Supplier> listSuppliers = FXCollections.observableArrayList();
        ArrayList<Supplier> receivedSuppliers = this.callback.getSuppliers();
        listSuppliers.addAll(receivedSuppliers);
        return listSuppliers;
    }

    public void addNewSupplier(Supplier supplier) {
        this.tableView.getItems().add(supplier);
    }


    /**
     * Add new Supplier
     */
    public void addNewSupplierAction() {
        resetSearchField();
        try {
            new SupplierPopup(this, this.callback, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to edit a supplier in the tableView
     */
    public void editSupplierAction(){
        resetSearchField();
        String name = this.tableView.getSelectionModel().getSelectedItem().getName();
        SupplierPopup pane;

        if (name != null) {
            try {
                pane = new SupplierPopup(this, this.callback, 1);
                Supplier supplier = this.callback.getSupplier(name);
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
        allSuppliers = this.tableView.getItems();
        supplierSelected = this.tableView.getSelectionModel().getSelectedItems();
        Supplier supplier = this.tableView.getSelectionModel().getSelectedItem();

        try {
            supplierSelected.forEach(allSuppliers::remove);
            this.callback.removeSupplier(supplier.getName());

        }catch (Exception e) {
            System.err.println("Last element - NullPointer \nRemoveSupplier \nSupplierPane Row 279");
            this.callback.removeSupplier(supplier.getName());
            this.callback.catchSafeState();
        }

    }

    //TODO Remove, depricated method.
    private void search() {
        System.out.println("SEARCH");
    }

    /**
     * Searchbar functionality.
     */
    private void searchRecord(Observable observable, String oldValue, String newValue) {

        FilteredList<Supplier> filteredList = new FilteredList<>(getSuppliersFromDatabase(), p -> true);

        if (!this.textField_Search.getText().equals("")) {
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
            sortedList.comparatorProperty().bind(this.tableView.comparatorProperty());
            this.tableView.setItems(sortedList);

        } else
            this.tableView.setItems(getSuppliersFromDatabase());
    }

    public void resetSearchField () {
        this.textField_Search.setText("");
    }

}
