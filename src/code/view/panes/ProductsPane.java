package code.view.panes;

import code.entities.Product;
import code.entities.Recipe;
import code.entities.Styles;
import code.view.popups.ProductPopup;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import code.control.Callback;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * The products menu provides information regarding products that currently are in stock
 * @author Viktor Polak, Tor Stenfeldt, Georg Grankvist
 * @version 5.1
 */
public class ProductsPane extends Pane implements EnhancedPane {
    private Callback callback;

    private VBox mainContainer;
    private HBox hBoxFiller;
    private VBox topVBoxContainer;
    private HBox hBox_SpinnerContainer;

    private FlowPane flowBottom;
    private Spinner<Integer> numberSpinner = new Spinner<>();
    private TextField searchTextField;

    private TableView<Product> tableView;
    private TableColumn<Product, String> tableColumn_Name = new TableColumn<>("Name");
    private TableColumn<Product, Double> tableColumn_Categories = new TableColumn<>("Category");
    private TableColumn<Product, Integer> tableColumn_Stock = new TableColumn<>("Quantity");
    private TableColumn<Product, Recipe> tableColumn_Recipe = new TableColumn<>("Recipe");

    public ProductsPane(Callback callback) {
        this.callback = callback;

        this.mainContainer = new VBox();
        this.mainContainer.setStyle(Styles.getPane());
        this.mainContainer.setPrefSize(1014, 695);
        this.mainContainer.setLayoutX(20);
        this.mainContainer.setLayoutY(20);
        this.mainContainer.getChildren().addAll(initTopVBoxContainer(), initFlowBottom());

        getStylesheets().add("styles.css");
        setStyle(Styles.getPane());
        setPrefSize(1054, 736);
        getChildren().add(this.mainContainer);
    }

    /**
     * Method which is used to create the top part of the panel
     * @return lbl - a label with the text "Products" which is displayed at the top of this panel
     */
    private HBox initTitle() {
        Text title = new Text("PRODUCTS");
        title.setStyle(Styles.getTitle());
        title.setFill(Paint.valueOf("#619f81"));

        HBox hBox = new HBox(title);
        hBox.setPrefSize(1014, 75);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle(
                "-fx-background-radius: 20 20 0 0;" +
                "-fx-background-color: #FFFFFF;"
        );

        return hBox;
    }

    /**
     * Filler box between titleBox and buttonBoxes // Change to 'filter', ändra från max/min till 50 padding kanske?
     * @return filler HBox
     */
    private HBox initFillerBox() {
        this.hBoxFiller = new HBox();
        this.hBoxFiller.setPrefSize(1014, 40);
        this.hBoxFiller.setStyle(
                "-fx-border-color: #6B6C6A;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-width: 1 0 1 0"
        );

        return this.hBoxFiller;
    }

    /**
     * Method to create a HBox which stacks its content horizontally located below the Label
     * @return hBox - the box which contains every button, comboBox, textField and numberSpinner
     */
    public HBox initButtonContainer() {
        Button button_NewItem = new Button("ADD PRODUCT");
        Button button_RemoveItem = new Button("REMOVE PRODUCT");
        Button button_EditItem = new Button("EDIT PRODUCT");

        button_NewItem.getStyleClass().add("greenButtonPanel");
        button_NewItem.setMinWidth(170);
        button_NewItem.setPrefHeight(40);
        button_NewItem.setOnAction(e -> addNewProductAction());

        button_RemoveItem.getStyleClass().add("greenButtonPanel");
        button_RemoveItem.setMinWidth(170);
        button_RemoveItem.setPrefHeight(40);
        button_RemoveItem.setOnAction(e -> removeProduct());

        button_EditItem.getStyleClass().add("greenButtonPanel");
        button_EditItem.setMinWidth(170);
        button_EditItem.setPrefHeight(40);
        button_EditItem.setOnAction(e -> editProduct());

        HBox buttons = new HBox( button_NewItem, button_RemoveItem, button_EditItem);
        buttons.setSpacing(10);
        buttons.setMinSize(580, 75);
        buttons.setPrefSize(580, 75);
        buttons.setAlignment(Pos.CENTER_LEFT);
        buttons.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        buttons.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-padding: 0 50 0 50"
        );

        return buttons;
    }

    /**
     * Contains buttons to the right in the code.view
     * @return container HBox
     */
    public HBox initSpinner() {
        Button button_Add = new Button();
        Button button_Remove = new Button();

        final SpinnerValueFactory.IntegerSpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
        this.numberSpinner.setValueFactory(svf);
        this.numberSpinner.disabledProperty();
        this.numberSpinner.setEditable(true);
        this.numberSpinner.setPrefHeight(40);
        this.numberSpinner.setPrefWidth(100);

        this.searchTextField = new TextField();
        this.searchTextField.setPromptText("SEARCH");
        this.searchTextField.setPrefHeight(32);
        this.searchTextField.setPrefWidth(150);
        this.searchTextField.textProperty().addListener(this::searchRecord);

        button_Add.getStyleClass().add("greenButtonPanel");
        button_Add.setPrefSize(40, 40);

        button_Remove.getStyleClass().add("greenButtonPanel");
        button_Remove.setPrefSize(40, 40);

        try {
            Image selectedImage = new Image(new FileInputStream("src/resources/plus-40.png"));
            ImageView selectedView = new ImageView(selectedImage);
            selectedView.setFitWidth(20);
            selectedView.setFitHeight(20);
            button_Add.setGraphic(selectedView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Image selectedImage = new Image(new FileInputStream("src/resources/minus-40.png"));
            ImageView selectedView = new ImageView(selectedImage);
            selectedView.setFitWidth(20);
            selectedView.setFitHeight(20);
            button_Remove.setGraphic(selectedView);
        } catch (IOException e) {
            e.printStackTrace();
        }

        button_Add.setOnAction(e -> addQuantity());
        button_Remove.setOnAction(e -> removeQuantity());

        this.hBox_SpinnerContainer = new HBox(this.numberSpinner, button_Add, button_Remove, this.searchTextField);
        this.hBox_SpinnerContainer.setSpacing(10);

        this.hBox_SpinnerContainer.setMinSize(380, 75);
        this.hBox_SpinnerContainer.setPrefSize(434, 75);

        this.hBox_SpinnerContainer.setAlignment(Pos.CENTER_RIGHT);
        this.hBox_SpinnerContainer.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-padding: 0 50 0 50;"
        );

        return this.hBox_SpinnerContainer;
    }

    /**
     * Collection box for buttonHBoxes
     * @return container HBox
     */
    public HBox initMenu() {
        HBox hBox = new HBox();
        hBox.setPrefSize(1014, 75);
        hBox.getChildren().addAll(initButtonContainer(), initSpinner());
        return hBox;
    }

    /**
     * Collection box for the top 3 HBoxes
     * @return container VBox
     */
    public VBox initTopVBoxContainer() {
        this.topVBoxContainer = new VBox(initTitle(), initFillerBox(), initMenu());
        this.topVBoxContainer.setPrefSize(1014, 190);
        this.topVBoxContainer.setAlignment(Pos.BOTTOM_CENTER);
        this.topVBoxContainer.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 0 0;"
        );

        return this.topVBoxContainer;
    }

    /**
     * Method that creates a pane containing a tableView with a number of columns
     * @return pane - a FlowPane which is located at the bottom of the panel
     */
    public FlowPane initFlowBottom() {
        this.tableColumn_Name.setCellValueFactory(new PropertyValueFactory<>("type"));
        this.tableColumn_Categories.setCellValueFactory(new PropertyValueFactory<>("category"));
        this.tableColumn_Stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.tableColumn_Recipe.setCellValueFactory(new PropertyValueFactory<>("recipe"));

        this.tableColumn_Name.setPrefWidth(228);
        this.tableColumn_Categories.setPrefWidth(228);
        this.tableColumn_Stock.setPrefWidth(228);
        this.tableColumn_Recipe.setPrefWidth(228);

        this.tableColumn_Name.setStyle(Styles.getTableColumn());
        this.tableColumn_Categories.setStyle(Styles.getTableColumn());
        this.tableColumn_Stock.setStyle(Styles.getTableColumn());

        this.tableColumn_Name.setResizable(false);
        this.tableColumn_Categories.setResizable(false);
        this.tableColumn_Stock.setResizable(false);
        this.tableColumn_Recipe.setResizable(false);

        this.tableView = new TableView<>();
        this.tableView.setPrefSize(914, 465);
        this.tableView.setStyle(Styles.getTableRowSelected());
        this.tableView.getColumns().addAll(this.tableColumn_Name, this.tableColumn_Categories, this.tableColumn_Stock, this.tableColumn_Recipe);

        this.flowBottom = new FlowPane();
        this.flowBottom.setPrefSize(1014, 505);
        this.flowBottom.setAlignment(Pos.CENTER);
        this.flowBottom.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.flowBottom.setStyle(
                "-fx-alignment: center;" +
                " -fx-background-color: #fff;" +
                " -fx-background-radius: 0 0 20 20;" +
                " -fx-padding: 0 0 50 0;"
        );
        this.flowBottom.getChildren().add(this.tableView);

        this.tableView.setItems(getItemList());
        return this.flowBottom;
    }

    /**
     * Expands the pane and makes the menuPane smaller
     */
    public void expand() {
        setPrefWidth(1200);
        this.mainContainer.setPrefWidth(1160);

        this.topVBoxContainer.setPrefWidth(1160);
        this.hBox_SpinnerContainer.setPrefWidth(580);
        this.flowBottom.setPrefWidth(1160);

        this.tableView.setPrefWidth(1062);
        this.tableColumn_Name.setPrefWidth(265);
        this.tableColumn_Categories.setPrefWidth(265);
        this.tableColumn_Stock.setPrefWidth(265);
        this.tableColumn_Recipe.setPrefWidth(265);
    }

    /**
     * Makes the pane smaller and expands the menuPane
     */
    public void contract() {
        setPrefWidth(1054);
        this.mainContainer.setPrefWidth(1014);

        this.topVBoxContainer.setPrefWidth(1014);
        this.flowBottom.setPrefWidth(1014);
        this.hBox_SpinnerContainer.setPrefWidth(434);

        this.tableView.setPrefWidth(916);
        this.tableColumn_Name.setPrefWidth(228);
        this.tableColumn_Categories.setPrefWidth(229);
        this.tableColumn_Stock.setPrefWidth(229);
        this.tableColumn_Recipe.setPrefWidth(228);
    }

    /**
     * refresh the tableView
     */
    public void refresh(){
        this.tableView.refresh();
    }

    /**
     * @return value of numberSpinner field
     */
    public int getNumberSpinnerValue() {
        return this.numberSpinner.getValue();
    }

    /**
     * Method that returns an observableList which populates the columns in the tableView
     * @return items - the list which populates the columns
     */
    public ObservableList<Product> getItemList() {
        ObservableList<Product> items = FXCollections.observableArrayList();
        Product[] receivedProducts = this.callback.getProducts();
        items.addAll(Arrays.asList(receivedProducts));

        return items;
    }

    /**
     * Method used to add a new item to the tableView
     * Opens a new window with information to be filled in
     */
    public void addNewProductAction() {
        resetSearchField();
        try {
            new ProductPopup(this, this.callback, 0);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method used to delete the selected item in the tableView
     */
    public void removeProduct() {
        resetSearchField();
        ObservableList<Product> itemSelected;
        ObservableList<Product> allItems;

        allItems = this.tableView.getItems();
        itemSelected = this.tableView.getSelectionModel().getSelectedItems();

        Product product = this.tableView.getSelectionModel().getSelectedItem();

        if(product != null) {
            try {
                itemSelected.forEach(allItems::remove);
                this.callback.removeProduct(product.getType());
            } catch (NoSuchElementException e) {
                System.err.println("Last element - NullPointer \nRemoveProduct \nProductPane Row 329");
                this.callback.removeProduct(product.getType());
                this.callback.catchSafeState();
            }
        } else{
            noProductSelected();
        }
    }


    /**
     * Add quantity to existing product
     */
    public void addQuantity() {
        Product product = this.tableView.getSelectionModel().getSelectedItem();

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
    public void removeQuantity() {
        Product product = this.tableView.getSelectionModel().getSelectedItem();

        if (product != null){
            int prodQuantity = product.getStock();
            product.setStock(prodQuantity - getNumberSpinnerValue());
        } else {
            noProductSelected();
        }

        tableView.refresh();
    }

    /**
     * Searchbar functionality
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void searchRecord(Observable observable, String oldValue, String newValue) {
        FilteredList<Product> filteredList = new FilteredList<>(getItemList(), p -> true);

        if (!searchTextField.getText().equals("")) {
            filteredList.setPredicate(tableView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String typedText = newValue.toLowerCase();

                if (tableView.getType().toLowerCase().contains(typedText)) {
                    return true;

                } else if (tableView.getCategory().name().toLowerCase().contains(typedText)) {
                    return true;

                } else  if (String.valueOf(tableView.getStock()).toLowerCase().contains(typedText)) {
                    return true;

                } else if (tableView.getRecipe().getName().toLowerCase().contains(typedText)) {
                    return true;
                }

                else
                    return false;
            });

            SortedList<Product> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedList);
        }

        else
            tableView.setItems(getItemList());
    }



    /**
     * Method used to edit a product in the tableView
     */
    public void editProduct() {
        resetSearchField();
        String name = this.tableView.getSelectionModel().getSelectedItem().getType();
        ProductPopup pane;

        if (name != null) {
            try {
                pane = new ProductPopup(this, this.callback, 1);
                Product product = this.callback.getProduct(name);
                pane.setOrgProd(name);
                pane.setValuesForItem(product.getType(), product.getCategory(), product.getStock(), product.getRecipe());
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        } else {
            noProductSelected();
        }
    }

    /**
     * @param product add product to tableView
     */
    public void addNewProduct(Product product) {
        this.tableView.getItems().add(product);
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

    public void resetSearchField () {
        searchTextField.clear();
    }
}
