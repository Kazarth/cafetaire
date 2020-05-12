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

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * The products menu provides information regarding products that currently are in stock
 * @author Viktor Polak
 * @version 5.1
 */
public class ProductsPane extends StackPane {
    private Spinner<Integer> numberSpinner = new Spinner<>();

    private TableColumn<Product, String> tableColumn_Name = new TableColumn<>("Name");
    private TableColumn<Product, Double> tableColumn_Categories = new TableColumn<>("Category");
    private TableColumn<Product, Integer> tableColumn_Stock = new TableColumn<>("Quantity");
    private TableColumn<Product, String> tableColumn_Ingredients = new TableColumn<>("Ingredients");
    private TableView<Product> tableView;
    private Callback callback;

    public ProductsPane(Callback callback) {
        this.callback = callback;
        VBox mainContainer = new VBox();
        mainContainer.setMaxSize(1036, 698);

        mainContainer.getChildren().addAll(initTopVBoxContainer(), initFlowBottom());
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
    private HBox initHBoxTop() {
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
     * Filler box between titleBox and buttonBoxes // Change to 'filter', ändra från max/min till 50 padding kanske?
     * @return filler HBox
     */
    private HBox initHBoxFillerBox() {
        HBox hBoxFiller = new HBox();
        hBoxFiller.setMinSize(1036, 40);
        hBoxFiller.setMaxSize(1036, 40);
        hBoxFiller.setStyle("-fx-border-color: #6B6C6A;" +
                            "-fx-border-width: 1 0 1 0;" +
                            "-fx-background-color: #fff");

        return hBoxFiller;
    }

    /**
     * Method to create a HBox which stacks its content horizontally located below the Label
     * @return hBox - the box which contains every button, comboBox, textField and numberSpinner
     */
    public HBox initHBoxCenterLeft() {
        Button button_NewItem = new Button("ADD PRODUCT");
        Button button_RemoveItem = new Button("REMOVE PRODUCT");
        Button button_EditItem = new Button("EDIT PRODUCT");

        button_NewItem.setStyle(Styles.getButton());
        button_RemoveItem.setStyle(Styles.getButton());
        button_EditItem.setStyle(Styles.getButton());

        HBox hBox = new HBox(15, button_NewItem, button_RemoveItem, button_EditItem);
        hBox.setSpacing(10);
        hBox.setMinSize(600, 75);
        hBox.setMaxSize(600, 75);
        hBox.setAlignment(Pos.CENTER_LEFT);

        hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        button_NewItem.setOnAction(e -> addNewProductAction());
        button_RemoveItem.setOnAction(e -> removeProduct());
        button_EditItem.setOnAction(e -> editProduct());

        hBox.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 0 50 0 50");

        return hBox;
    }

    /**
     * Contains buttons to the right in the code.view
     * @return container HBox
     */
    public HBox initHBoxCenterRight(){
        Button button_Add = new Button("ADD");
        Button button_Remove = new Button("REMOVE");

        final SpinnerValueFactory.IntegerSpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        numberSpinner.setValueFactory(svf);
        numberSpinner.disabledProperty();
        numberSpinner.setEditable(true);
        numberSpinner.setPrefHeight(38);
        numberSpinner.setPrefWidth(100);

        button_Add.setStyle(Styles.getButton());
        button_Remove.setStyle(Styles.getButton());

        button_Add.setPrefSize(100,30);
        button_Remove.setPrefSize(100,30);

        button_Add.setOnAction(e -> addQuantity());
        button_Remove.setOnAction(e -> removeQuantity());

        HBox hBox = new HBox(15, numberSpinner, button_Add, button_Remove);

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
    public HBox initHBoxContainerBtn() {
        HBox hBox = new HBox();
        setPrefSize(1036, 75);
        hBox.getChildren().addAll(initHBoxCenterLeft(), initHBoxCenterRight());
        return hBox;
    }

    /**
     * Collection box for the top 3 HBoxes
     * @return container VBox
     */
    public VBox initTopVBoxContainer(){
        VBox vBox =   new VBox(initHBoxTop(), initHBoxFillerBox(), initHBoxContainerBtn());
        vBox.setPrefSize(1036, 190);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20 20 0 0;");

        return vBox;
    }

    /**
     * Method that creates a pane containing a tableView with a number of columns
     * @return pane - a FlowPane which is located at the bottom of the panel
     */
    public FlowPane initFlowBottom() {
        FlowPane pane = new FlowPane();

        pane.setPadding(new Insets(15,15,15,15));

        pane.setMinSize(1036, 508);
        pane.setMaxSize(1036, 508);

        tableView = new TableView<>();

        tableColumn_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumn_Categories.setCellValueFactory(new PropertyValueFactory<>("category"));
        tableColumn_Stock.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tableColumn_Name.setPrefWidth(233);
        tableColumn_Categories.setPrefWidth(234);
        tableColumn_Stock.setPrefWidth(234);
        tableColumn_Ingredients.setPrefWidth(234);

        tableColumn_Name.setStyle(Styles.getTableColumn());
        tableColumn_Categories.setStyle(Styles.getTableColumn());
        tableColumn_Stock.setStyle(Styles.getTableColumn());

        tableView.getColumns().addAll(tableColumn_Name, tableColumn_Categories, tableColumn_Stock, tableColumn_Ingredients);

        tableView.setPrefHeight(458);
        tableView.setStyle(Styles.getTableRowSelected());

        pane.setAlignment(Pos.CENTER);

        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(tableView);

        tableView.setItems(getItemList());

        pane.setStyle("-fx-alignment: center;" +
                " -fx-background-color: #fff;" +
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
    public ObservableList<Product> getItemList() {
        ObservableList<Product> items = FXCollections.observableArrayList();
        // temp item to populate tableView
        Product product = new Product("Dummy Item", ProductCategories.Bread, 1);
        callback.addProductTest(product);
        Product[] receivedProducts = callback.getProductTest();
        items.addAll(Arrays.asList(receivedProducts));

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
    public void removeProduct() {
        ObservableList<Product> itemSelected;
        ObservableList<Product> allItems;

        allItems = tableView.getItems();
        itemSelected = tableView.getSelectionModel().getSelectedItems();

        Product product = tableView.getSelectionModel().getSelectedItem();

        try {
            itemSelected.forEach(allItems::remove);
            callback.removeProductTest(product.getName());
        } catch (NoSuchElementException e){
            e.printStackTrace();
        }

    }


    /**
     * Add quantity to existing product
     */
    public void addQuantity() {
        Product product = tableView.getSelectionModel().getSelectedItem();

    if (product != null){
        int prodQuantity = product.getQuantity();
        product.setQuantity(prodQuantity + getNumberSpinnerValue());
    } else {
        noProductSelected();
    }

        refresh();
    }


    /**
     * Remove quantity from existing product
     */
    public void removeQuantity() {
        Product product = tableView.getSelectionModel().getSelectedItem();

        if (product != null){
            int prodQuantity = product.getQuantity();
            product.setQuantity(prodQuantity - getNumberSpinnerValue());
        } else {
            noProductSelected();
        }

        tableView.refresh();
    }

    /**
     * Method used to edit a product in the tableView
     */
    public void editProduct() {
        String name = tableView.getSelectionModel().getSelectedItem().getName();
        ProductPopup pane;

        if (name != null) {
            try {
                pane = new ProductPopup(this, callback, 1);
                Product product = callback.getProductTest(name);
                pane.setOrgProd(name);
                pane.setValuesForItem(product.getName(), product.getCategory(), product.getQuantity());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            noProductSelected();
        }
    }

    /**
     * Expands the pane and makes the menuPane smaller
     */
    public void expand() {
        setPrefWidth(1346);
        System.out.println("Expanding");
    }

    /**
     * Makes the pane smaller and expands the menuPane
     */
    public void contract() {
        setPrefWidth(1086);
        System.out.println("Contracting");
    }

    /**
     * @param product add product to tableView
     */
    public void addNewProduct(Product product) {
        tableView.getItems().add(product);
    }

    /**
     * refresh the tableView
     */
    public void refresh(){
        tableView.refresh();
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
