package View;
import Entities.ProductCategories;
import Entities.Products;
import Entities.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import Control.Callback;

import javax.swing.*;
import java.util.Scanner;

/**
 * The products menu provides information regarding products that currently are in stock
 * @author Viktor Polak
 * @version 1.0
 */

public class ProductsPane extends BorderPane {
    private Button btnNewItem;
    private Button btnRemoveItem;
    private Button btnAdd;
    private Button btnRemove;
    private TextField txtFieldNewProducts;
    private ComboBox<ProductCategories> cmbBoxProducts;
    private Spinner<Integer> numberSpinner = new Spinner<>();

    private TableColumn<Products, String> tblColumnName = new TableColumn<>("Name");
    private TableColumn<Products, Double> tblCategories = new TableColumn<>("Category");
    private TableColumn<Products, Integer> tblColumnStock = new TableColumn<>("Quantity");
    private TableView<Products> tblView;

    private static final String btnStyle = Styles.getButton();
    private static final String tableStyle = "-FX-color: #21252B; -FX-header-color: #FFFFFF";


    private int btnWidth = 75;
    private int btnHeight = 25;

    private final int SIZE = 60;

    private Callback callback;


    public ProductsPane(Callback callback){
        Text textTitle = new Text();
        Font MenuTitle = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        textTitle.setFill(Paint.valueOf("#619f81"));
        textTitle.setFont(MenuTitle);
        textTitle.setText("Products");

        setTop(getTopLabel());
        setCenter(getHCenter());
        setBottom(getFlowBottom());

        setStyle(Styles.getDashboardBox());
    }

    /**
     * Method which is used to create the top part of the panel
     * @return lbl - a label with the text "Products" which is displayed at the top of this panel
     */
    private Label getTopLabel() {

        Label lbl = new MyLabel("Products");
        lbl.setPrefHeight(SIZE);
        lbl.prefWidthProperty().bind(widthProperty());
        lbl.setStyle(" -fx-border-width: 1 1 1 1;"
                + "-fx-border-color: grey; -fx-font-weight: bold; -fx-text-fill: #619F81; -fx-font-size: 36");
        lbl.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        return lbl;
    }


    /**
     * Method to create a HBox which stacks its content horizontally located below the Label
     * @return hBox - the box which contains every button, comboBox, textField and numberSpinner
     */
    public HBox getHCenter(){
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12, 15, 12));

        btnNewItem = new Button("Add New Item");
        btnRemoveItem = new Button("Remove Item");
        btnAdd = new Button("Add");
        btnRemove = new Button("Remove");

        txtFieldNewProducts = new TextField();
        txtFieldNewProducts.setPromptText("Enter a new product");
        cmbBoxProducts = new ComboBox<>();
        cmbBoxProducts.setPrefSize(150, 20);
        cmbBoxProducts.setItems(categoriesToCmb());
        cmbBoxProducts.getSelectionModel().selectFirst();

        final SpinnerValueFactory.IntegerSpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        numberSpinner.setValueFactory(svf);
        numberSpinner.disabledProperty();
        numberSpinner.setEditable(true);
        numberSpinner.setPrefSize(100, 38);


        btnNewItem.setPrefSize(130, btnHeight);
        btnRemoveItem.setPrefSize(130, btnHeight);
        btnAdd.setPrefSize(btnWidth, btnHeight);
        btnRemove.setPrefSize(90, btnHeight);
        btnNewItem.setStyle(btnStyle);
        btnRemoveItem.setStyle(btnStyle);
        btnAdd.setStyle(btnStyle);
        btnRemove.setStyle(btnStyle);
        cmbBoxProducts.setStyle("-fx-background-color: #619f81;" +
                " -fx-text-fill: #FFFFFF;" +
                " -fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12;" +
                "-fx-padding: 5 10 5 10;" +
                "-fx-background-radius: 10;");

        txtFieldNewProducts.setPrefSize(150, 38);

        hBox.getChildren().addAll(btnNewItem, btnRemoveItem, txtFieldNewProducts, cmbBoxProducts, numberSpinner, btnAdd, btnRemove);
        hBox.setAlignment(Pos.TOP_CENTER);

        hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        btnNewItem.setOnAction(e -> addNewItem());
        btnRemoveItem.setOnAction(e -> deleteItem());
        btnAdd.setOnAction(e -> addQuantityToProduct());
        btnRemove.setOnAction(e -> removeQuantityFromProduct());
        txtFieldNewProducts.setOnAction(e -> txtFieldEmpty());

        return hBox;
    }

    /**
     * Method that creates a pane containing a tableView with a number of columns
     * @return pane - a FlowPane which is located at the bottom of the panel
     */
    public FlowPane getFlowBottom(){
        FlowPane pane = new FlowPane();

        pane.setPadding(new Insets(15,15,15,15));

        tblView = new TableView<>();

        tblColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCategories.setCellValueFactory(new PropertyValueFactory<>("categories"));
        tblColumnStock.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tblColumnName.setPrefWidth(325);
        tblCategories.setPrefWidth(326);
        tblColumnStock.setPrefWidth(326);

        tblColumnName.setStyle(Styles.getTableColumn());
        tblCategories.setStyle(Styles.getTableColumn());
        tblColumnStock.setStyle(Styles.getTableColumn());

        tblView.getColumns().add(tblColumnName);
        tblView.getColumns().add(tblCategories);
        tblView.getColumns().add(tblColumnStock);

        tblView.setPrefSize(980, 550);
        tblView.setStyle(tableStyle);

        pane.setAlignment(Pos.CENTER);

        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(tblView);

        tblView.setItems(itemsToTable());

        return pane;
    }


    /**
     * Method used to check if textField is empty
     * @return true if txtFieldNewProducts is empty and false if it's not
     */
    public boolean txtFieldEmpty(){
        if (txtFieldNewProducts.getText() == null || txtFieldNewProducts.getText().equals("")) {
            return true;
        }
            return false;
    }
        //Kanske inte behövs eftersom numberSpinner.getValue() alltid returnerar ett int-värde
//    public boolean numberSpinnerNotInteger(){
//        try {
//            Integer.parseInt(String.valueOf(numberSpinner.getValue()));
//            return false;
//        } catch (NumberFormatException e){
//            return true;
//        }
//    }

    public int getNumberSpinnerValue() {
        int value = numberSpinner.getValue();

        return value;
    }

    /**
     * Private class used for the label at the top of the panel
     */
    private class MyLabel extends Label {

        public MyLabel(String text) {
            super(text);

            setAlignment(Pos.BASELINE_CENTER);
        }
    }

    /**
     * Method that returns an observableList which populates the columns in the tableView
     * @return items - the list which populates the columns
     */
    public ObservableList<Products> itemsToTable(){
        ObservableList<Products> items = FXCollections.observableArrayList();
        items.add(new Products("Dummy Item", ProductCategories.Bread, 1));

        return items;
    }

    /**
     *
     * @return categories - an observableList which populates the comboBox
     */
    public ObservableList<ProductCategories> categoriesToCmb(){
        ObservableList<ProductCategories> categories = FXCollections.observableArrayList();
        categories.addAll(ProductCategories.Bread, ProductCategories.Fruit, ProductCategories.Vegetable, ProductCategories.Dairy);

        return categories;
    }

    /**
     * Method used to add a new item to the tableView
     * if the textField is empty item won't be added and a popup-window will show up instead
     */
    private void addNewItem(){
        if (!txtFieldEmpty()) {
            Products item = new Products(txtFieldNewProducts.getText(), cmbBoxProducts.getSelectionModel().getSelectedItem(),
                    numberSpinner.getValue());
            tblView.getItems().add(item);
            System.out.println(txtFieldNewProducts.getText());
            txtFieldNewProducts.clear();
        } else if (txtFieldEmpty()){
            return;
            //JOptionPane.showMessageDialog(null, "Please enter a name for your product!");
        }
    }

    /**
     * Method used to delete the selected item in the tableView
     */
    private void deleteItem(){
        ObservableList<Products> itemSelected;
        ObservableList<Products> allItems;

        allItems = tblView.getItems();
        itemSelected = tblView.getSelectionModel().getSelectedItems();

        itemSelected.forEach(allItems::remove);
    }

    public void addQuantityToProduct(){
        Products products = tblView.getSelectionModel().getSelectedItem();

        int prodQuantity = products.getQuantity();

        products.setQuantity(prodQuantity + getNumberSpinnerValue());

        tblView.refresh();
    }

    public void removeQuantityFromProduct(){
        Products products = tblView.getSelectionModel().getSelectedItem();

        int prodQuantity = products.getQuantity();

        products.setQuantity(prodQuantity - getNumberSpinnerValue());

        tblView.refresh();
    }
}
