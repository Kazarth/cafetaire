package View;
import Entities.ProductCategories;
import Entities.Products;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import Control.Callback;

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
    private TextField txtFieldNewPerishable;
    private ComboBox<ProductCategories> cmbBoxPerishables;
    private Spinner<Integer> numberSpinner = new Spinner<>();

    private TableColumn<Products, String> tblColumnName = new TableColumn<>("Name");
    private TableColumn<Products, Double> tblCategories = new TableColumn<>("Category");
    private TableColumn<Products, Integer> tblColumnStock = new TableColumn<>("Quantity");
    private TableView<Products> tblView;

    private static final String btnStyle = "-FX-color: #619F81; -FX-text-fill: #FFFFFF; -FX-font-size: 16";
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
    }


    private Label getTopLabel() {

        Label lbl = new MyLabel("Products");
        lbl.setPrefHeight(SIZE);
        lbl.prefWidthProperty().bind(widthProperty());
        lbl.setStyle(" -fx-border-width: 1 0 1 0;"
                + "-fx-border-color: grey; -fx-font-weight: bold; -fx-text-fill: #619F81; -fx-font-size: 36");
        lbl.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        return lbl;
    }

    public HBox getHCenter(){
        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(15, 12, 15, 12));

        btnNewItem = new Button("Add New Item");
        btnRemoveItem = new Button("Remove Item");
        btnAdd = new Button("Add");
        btnRemove = new Button("Remove");

        txtFieldNewPerishable = new TextField();
        txtFieldNewPerishable.setPromptText("Enter a new perishable");
        cmbBoxPerishables = new ComboBox<>();
        cmbBoxPerishables.setPrefSize(150, 20);
        cmbBoxPerishables.setItems(categoriesToCmb());
        cmbBoxPerishables.getSelectionModel().selectFirst();

        final SpinnerValueFactory.IntegerSpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        numberSpinner.setValueFactory(svf);
        numberSpinner.disabledProperty();
        numberSpinner.setEditable(true);
        numberSpinner.setPromptText("Number of items");
        numberSpinner.setPrefSize(100, 38);


        btnNewItem.setPrefSize(130, btnHeight);
        btnRemoveItem.setPrefSize(130, btnHeight);
        btnAdd.setPrefSize(btnWidth, btnHeight);
        btnRemove.setPrefSize(90, btnHeight);
        btnNewItem.setStyle(btnStyle);
        btnRemoveItem.setStyle(btnStyle);
        btnAdd.setStyle(btnStyle);
        btnRemove.setStyle(btnStyle);
        cmbBoxPerishables.setStyle(btnStyle);

        txtFieldNewPerishable.setPrefSize(150, 38);

        hBox.getChildren().addAll(btnNewItem, btnRemoveItem, txtFieldNewPerishable, cmbBoxPerishables, numberSpinner, btnAdd, btnRemove);
        hBox.setAlignment(Pos.TOP_CENTER);

        hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        btnNewItem.setOnAction(e -> addNewItem());
        btnRemoveItem.setOnAction(e -> deleteItem());

        return hBox;
    }

    public FlowPane getFlowBottom(){
        FlowPane pane = new FlowPane();

        pane.setPadding(new Insets(15,15,15,15));

        tblView = new TableView<>();

        tblColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCategories.setCellValueFactory(new PropertyValueFactory<>("categories"));
        tblColumnStock.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        tblColumnName.setPrefWidth(333);
        tblCategories.setPrefWidth(333);
        tblColumnStock.setPrefWidth(333);


        tblView.getColumns().add(tblColumnName);
        tblView.getColumns().add(tblCategories);
        tblView.getColumns().add(tblColumnStock);

        tblView.setPrefSize(1000, 550);
        tblView.setStyle(tableStyle);

        pane.setAlignment(Pos.CENTER);

        pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(tblView);

        tblView.setItems(itemsToTable());


        return pane;
    }

    private class MyLabel extends Label {

        public MyLabel(String text) {
            super(text);

            setAlignment(Pos.BASELINE_CENTER);
        }
    }

    public ObservableList<Products> itemsToTable(){
        ObservableList<Products> items = FXCollections.observableArrayList();
        items.add(new Products("Dummy Item", ProductCategories.Bread, 1));

        return items;
    }

    public ObservableList<ProductCategories> categoriesToCmb(){
        ObservableList<ProductCategories> categories = FXCollections.observableArrayList();
        categories.addAll(ProductCategories.Bread, ProductCategories.Fruit, ProductCategories.Vegetable, ProductCategories.Dairy);

        return categories;
    }

    private void addNewItem(){
        Products item = new Products(txtFieldNewPerishable.getText(), cmbBoxPerishables.getSelectionModel().getSelectedItem(),
                numberSpinner.getValue());
        tblView.getItems().add(item);
        System.out.println(txtFieldNewPerishable.getText());
        txtFieldNewPerishable.clear();
    }

    private void deleteItem(){
        ObservableList<Products> itemSelected, allItems;
        allItems = tblView.getItems();
        itemSelected = tblView.getSelectionModel().getSelectedItems();

        itemSelected.forEach(allItems::remove);
    }

}
