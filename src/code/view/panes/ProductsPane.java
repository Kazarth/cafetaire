package code.view.panes;

import code.entities.Product;
import code.entities.ProductCategories;
import code.entities.Styles;
import code.view.popups.ProductPopup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import code.control.Callback;

import java.util.NoSuchElementException;

/**
 * The products menu provides information regarding products that currently are in stock
 * @author Viktor Polak
 * @version 5.0
 */
public class ProductsPane extends StackPane {
    private Spinner<Integer> numberSpinner = new Spinner<>();

    private TableColumn<Product, String> tblColumnName = new TableColumn<>("Name");
    private TableColumn<Product, Double> tblCategories = new TableColumn<>("Category");
    private TableColumn<Product, Integer> tblColumnStock = new TableColumn<>("Quantity");
    private TableColumn<Product, String> tblColumnIngredients = new TableColumn<>("Ingredients");
    private TableView<Product> tblView;
    private Callback callback;

    public ProductsPane(Callback callback) {
        this.callback = callback;
        VBox mainContainer = new VBox();
        mainContainer.setMaxSize(1036, 698);

        mainContainer.getChildren().addAll(getTopVBoxContainer(), getFlowBottom());
        getChildren().add(mainContainer);

        mainContainer.setAlignment(Pos.CENTER);
        setStyle(Styles.getPane());
        mainContainer.setStyle(Styles.getPane());

        setPrefSize(1086, 768);
    }

    /**
     * Method which is used to create the top part of the panel
     * @return lbl - a label with the text "Products" which is displayed at the top of this panel
     */
    private HBox getHBoxTop() {
        Text textTitle = new Text();
        Font MenuTitle = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        textTitle.setFill(Paint.valueOf("#619f81"));
        textTitle.setFont(MenuTitle);
        textTitle.setText("PRODUCTS");

        HBox hBox = new HBox();
        hBox.setPrefSize(1036, 75);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().add(textTitle);
        hBox.setStyle("-fx-background-radius: 20 20 0 0;" +
                        "-fx-background-color: #FFFFFF;");

        return hBox;
    }

    /**
     * Filler box between titleBox and buttonBoxes
     * @return filler HBox
     */
    private HBox getFillerBox() {
        HBox hBoxFiller = new HBox();
        hBoxFiller.setMinSize(1036, 40);
        hBoxFiller.setMaxSize(1036, 40);
        hBoxFiller.setStyle("-fx-border-color: #6B6C6A;" +
                            "-fx-background-color: #FFFFFF");

        return hBoxFiller;
    }

    /**
     * Method to create a HBox which stacks its content horizontally located below the Label
     * @return hBox - the box which contains every button, comboBox, textField and numberSpinner
     */
    public HBox getHCenterLeft() {
        Button btnNewItem = new Button("ADD PRODUCT");
        Button btnRemoveItem = new Button("REMOVE PRODUCT");
        Button btnEditItem = new Button("EDIT PRODUCT");

        btnNewItem.setStyle(Styles.getButton());
        btnRemoveItem.setStyle(Styles.getButton());
        btnEditItem.setStyle(Styles.getButton());

        HBox hBox = new HBox(15, btnNewItem, btnRemoveItem, btnEditItem);
        hBox.setSpacing(10);
        hBox.setMinSize(600, 75);
        hBox.setMaxSize(600, 75);
        hBox.setAlignment(Pos.CENTER_LEFT);

        hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        btnNewItem.setOnAction(e -> addNewProductAction());
        btnRemoveItem.setOnAction(e -> deleteItem());
        btnEditItem.setOnAction(e -> editItem());

        hBox.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 0 50 0 50");

        return hBox;
    }

    /**
     * Contains buttons to the right in the code.view
     * @return container HBox
     */
    public HBox getHCenterRight(){
        Button btnAdd = new Button("ADD");
        Button btnRemove = new Button("REMOVE");

        final SpinnerValueFactory.IntegerSpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        numberSpinner.setValueFactory(svf);
        numberSpinner.disabledProperty();
        numberSpinner.setEditable(true);
        numberSpinner.setPrefHeight(38);

        btnAdd.setStyle(Styles.getButton());
        btnRemove.setStyle(Styles.getButton());

        btnAdd.setOnAction(e -> addQuantityToProduct());
        btnRemove.setOnAction(e -> removeQuantityFromProduct());

        HBox hBox = new HBox(15, numberSpinner, btnAdd, btnRemove);

        hBox.setMaxSize(435, 75);
        hBox.setMinSize(435, 75);

        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 0 50 0 50;");

        return hBox;
    }

    /**
     * Collection box for buttonHBoxes
     * @return container HBox
     */
    public HBox getHBoxContainerBtn() {
        HBox hBox = new HBox();
        setPrefSize(1036, 75);
        hBox.getChildren().addAll(getHCenterLeft(), getHCenterRight());
        return hBox;
    }

    /**
     * Collection box for the top 3 HBoxes
     * @return container VBox
     */
    public VBox getTopVBoxContainer(){
        VBox vBox =   new VBox(getHBoxTop(), getFillerBox(), getHBoxContainerBtn());
        vBox.setPrefSize(1036, 190);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20 20 0 0;");

        return vBox;
    }

    /**
     * Method that creates a pane containing a tableView with a number of columns
     * @return pane - a FlowPane which is located at the bottom of the panel
     */
    public FlowPane getFlowBottom() {
        FlowPane pane = new FlowPane();

        pane.setPadding(new Insets(15,15,15,15));

        pane.setMinSize(1036, 508);
        pane.setMaxSize(1036, 508);

        tblView = new TableView<>();

        tblColumnName.setCellValueFactory(new PropertyValueFactory<>("type"));
        tblCategories.setCellValueFactory(new PropertyValueFactory<>("category"));
        tblColumnStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tblColumnName.setPrefWidth(233);
        tblCategories.setPrefWidth(234);
        tblColumnStock.setPrefWidth(234);
        tblColumnIngredients.setPrefWidth(234);

        tblColumnName.setStyle(Styles.getTableColumn());
        tblCategories.setStyle(Styles.getTableColumn());
        tblColumnStock.setStyle(Styles.getTableColumn());

        tblView.getColumns().addAll(tblColumnName, tblCategories, tblColumnStock, tblColumnIngredients);

        tblView.setPrefHeight(458);
        tblView.setStyle(Styles.getTableRowSelected());

        pane.setAlignment(Pos.CENTER);

        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(tblView);

        tblView.setItems(itemsToTable());

        pane.setStyle("-fx-alignment: center;" +
                " -fx-background-color: #FFFFFF;" +
                " -fx-background-radius: 0 0 20 20;" +
                " -fx-padding: 0 0 50 0;");

        return pane;
    }

    /**
     * @return value of numberSpinner field
     */
    public int getNumberSpinnerValue() {
        int value = numberSpinner.getValue();

        return value;
    }

    /**
     * Method that returns an observableList which populates the columns in the tableView
     * @return items - the list which populates the columns
     */
    public ObservableList<Product> itemsToTable() {
        ObservableList<Product> items = FXCollections.observableArrayList();
        items.add(new Product("Dummy Item", ProductCategories.Bread, 1));
        return items;
    }

    /**
     * Method used to add a new item to the tableView
     * Opens a new window with information to be filled in
     */
    public void addNewProductAction() {
        try {
            new ProductPopup(this, callback, 0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method used to delete the selected item in the tableView
     */

    public void deleteItem() {
        ObservableList<Product> itemSelected;
        ObservableList<Product> allItems;

        allItems = tblView.getItems();
        itemSelected = tblView.getSelectionModel().getSelectedItems();

        try {
            itemSelected.forEach(allItems::remove);
        } catch (NoSuchElementException e){
            e.printStackTrace();
        }
    }


    /**
     * Add quantity to existing product
     */
    public void addQuantityToProduct() {
        Product product = tblView.getSelectionModel().getSelectedItem();

    if (product != null){
        int prodQuantity = product.getStock();
        product.setStock(prodQuantity + getNumberSpinnerValue());
    } else {
        noProductSelected();
    }

        refresh();
    }


    /**
     * Remove quantity from existing product
     */
    public void removeQuantityFromProduct() {
        Product product = tblView.getSelectionModel().getSelectedItem();

        if (product != null){
            int prodQuantity = product.getStock();
            product.setStock(prodQuantity - getNumberSpinnerValue());
        } else {
            noProductSelected();
        }

        tblView.refresh();
    }

    /**
     * Method used to edit a product in the tableView
     */
    public void editItem() {
        String name = tblView.getSelectionModel().getSelectedItem().getType();
        ProductPopup pane;

        if (name != null) {
            try {
                pane = new ProductPopup(this, callback, 1);
                Product product = callback.getProduct(name);
                pane.setOrgProd(name);
                pane.setValuesForItem(product.getType(), product.getCategory(), product.getStock());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            noProductSelected();
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
     * @param product add product to tableView
     */
    public void addNewProduct(Product product) {
        tblView.getItems().add(product);
    }

    /**
     * refresh the tableView
     */
    public void refresh(){
        tblView.refresh();
    }

    /**
     * run method if no product is selected in tableView
     */
    public void noProductSelected() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Product Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select a product!");

        alert.showAndWait();
    }
}
