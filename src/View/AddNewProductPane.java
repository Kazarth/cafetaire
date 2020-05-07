package View;


import Control.Callback;
import Entities.Product;
import Entities.ProductCategories;
import Entities.Styles;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;

/**
 * The class handles a Pop-up window for adding a product to products in database.
 * @author Viktor Polak, Lucas Eliasson
 * @version 2.0
 */

public class AddNewProductPane extends AnchorPane {
    private JFrame frame;

    private Label title;
    private Label nameLbl;
    private Label categoryLbl;
    private Label numberLabel;
    private Label phoneLbl;

    private TextField nameField;
    private ComboBox<ProductCategories> categoryBox;
    private Spinner<Integer> numberSpinner;
    private TextField phoneField;

    private Button addButton, cancelButton;

    private ProductsPane source;
    private Callback callback;
    private int opener;
    private String orgProd;

    public AddNewProductPane(ProductsPane source, Callback callback, int opener) {
        // init Frame
        frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Add new PRODUCT");
        frame.add(fxPanel);
        frame.setSize(600,460);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setVisible(true);
        Platform.runLater(() -> fxPanel.setScene(new Scene(this)));
        this.opener = opener;

        // Init source
        this.source = source;
        this.callback = callback;

        // Background pane
        setMaxWidth(600); setMaxHeight(400);
        setPrefWidth(600); setPrefHeight(400);
        setStyle(
                "-fx-background-color: #fff"
        );

        // title pane
        title = new Label("ADD NEW PRODUCT");
        title.setStyle(Styles.getPopTitle());
        title.setLayoutX(162.0); title.setLayoutY(20);
        title.setPrefWidth(300); title.setPrefHeight(40);

        // Product Name pane
        nameLbl = new Label("Product");
        nameLbl.setStyle("-fx-text-fill: #000;");
        nameLbl.setPrefWidth(220); nameLbl.setPrefHeight(40);
        nameLbl.setLayoutX(56.0); nameLbl.setLayoutY(100);

        nameField = new TextField();
        nameField.setPromptText("Enter Product Name");
        nameField.setStyle(Styles.getPopField());
        nameField.setPrefWidth(360); nameField.setPrefHeight(40);
        nameField.setLayoutX(144.0); nameField.setLayoutY(100);

        // Category pane
        categoryLbl = new Label("Category");
        categoryLbl.setStyle("-fx-text-fill: #000;");
        categoryLbl.setPrefWidth(220.0); categoryLbl.setPrefHeight(40);
        categoryLbl.setLayoutX(56.0); categoryLbl.setLayoutY(160);

        categoryBox = new ComboBox();
        categoryBox.setPromptText("Select category");
        categoryBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        categoryBox.setPrefWidth(360.0); categoryBox.setPrefHeight(40);
        categoryBox.setLayoutX(144.0); categoryBox.setLayoutY(160);
        categoryBox.setItems(getCategories()); // testing

        // Spinner Pane
        numberLabel = new Label("Amount");
        numberLabel.setStyle("-fx-text-fill: #000;");
        numberLabel.setPrefWidth(220); numberLabel.setPrefHeight(40);
        numberLabel.setLayoutX(56.0); numberLabel.setLayoutY(220);

        final SpinnerValueFactory.IntegerSpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        numberSpinner = new Spinner<>();
        numberSpinner.setValueFactory(svf);
        numberSpinner.disabledProperty();
        numberSpinner.setEditable(true);
        numberSpinner.setPromptText("Enter Number Of Products");
        numberSpinner.setStyle(Styles.getPopField());
        numberSpinner.setPrefWidth(360); numberSpinner.setPrefHeight(40);
        numberSpinner.setLayoutX(144.0); numberSpinner.setLayoutY(220);

        // Phone Pane
        phoneLbl = new Label("Phone nr.");
        phoneLbl.setStyle("-fx-text-fill: #000;");
        phoneLbl.setPrefWidth(220); phoneLbl.setPrefHeight(40);
        phoneLbl.setLayoutX(56.0);  phoneLbl.setLayoutY(280);

        phoneField  =   new TextField();
        phoneField.setPromptText("Enter phone number");
        phoneField.setStyle(Styles.getPopField());
        phoneField.setPrefWidth(360);   phoneField.setPrefHeight(40);
        phoneField.setLayoutX(144.0);   phoneField.setLayoutY(280);

        // Button pane
        addButton = new Button();
        addButton.setStyle(Styles.getPopAddButton());
        addButton.setPrefWidth(200); addButton.setPrefHeight(40);
        addButton.setLayoutX(75); addButton.setLayoutY(340);

        //New Product
        if (opener == 0){
            addButton.setText("ADD NEW PRODUCT");
            addButton.setOnAction(e -> {
                addAction();
            });

        // Edit Product
        } else if (opener == 1){
            addButton.setText("SAVE PRODUCT");
            addButton.setOnAction(e -> {
                editAction();
            });
        }


        cancelButton = new Button("CANCEL");
        cancelButton.setStyle(Styles.getPopCancelButton());
        cancelButton.setPrefWidth(200); cancelButton.setPrefHeight(40);
        cancelButton.setLayoutX(325.0); cancelButton.setLayoutY(340);
        cancelButton.setOnAction(e -> cancelAction());

        // Add all children

            getChildren().addAll(
                    title,
                    nameLbl, nameField,
                    categoryLbl, categoryBox,
                    numberLabel, numberSpinner,
//                phoneLbl,phoneField,
                    addButton, cancelButton);
    }

    /**
     * Action performed Add-button
     */
    public void addAction() {
        Product product;

        String productName = nameField.getText();
        ProductCategories category     = categoryBox.getSelectionModel().getSelectedItem();
        int quantity = numberSpinner.getValue();
//        String phone        = phoneField.getText();

        if ((!productName.equals("")) && (category != null)) {
            product = new Product(productName, category, quantity);

            System.out.println(product.toString());

            if (callback.addProductTest(product)) {
                source.addNewProduct(product);

                close();
            }
        } else {
            alert();
        }


    }

    /**
     * Action performed Cancel-button
     */
    public void cancelAction() {
        close();
    }
    /**
     * Close the frame
     */
    private void close() {
        frame.dispose();
    }

    /**
     * Method used to edit an item in the tableView in ProductPane and in the database
     * if name remains unchanged set values from the other fields to product
     * if name is changed remove old name from database and add the new one
     */
    public void editAction(){
        String name = nameField.getText();
        ProductCategories category = categoryBox.getValue();
        int quantity = numberSpinner.getValue();

        if (orgProd.equals(name)){
            Product product = callback.getProductTest(nameField.getText());
            product.setCategory(category);
            product.setQuantity(quantity);
            source.refresh();
            close();
        } else if (!orgProd.equals(name)){
            Product product = new Product(name, category, quantity);
            callback.addProductTest(product);
            callback.removeProductTest(orgProd);
            source.addNewProduct(product);
            source.deleteItem();
            source.refresh();
            close();
        } else{
            alert();
        }

    }

    /**
     *
     * @param txt set initial value for item to edit
     * @param category set initial value for item to edit
     * @param quantity set initial value for item to edit
     */
    public void setValuesForItem(String txt, ProductCategories category, int quantity){
        nameField.setText(txt);
        categoryBox.getSelectionModel().select(category);
        numberSpinner.getValueFactory().setValue(quantity);
    }


//    //From database
//    private ObservableList<String> getSuppliersFromDatabase() {
//        ObservableList<String> listProducts = FXCollections.observableArrayList();
//        ArrayList<Product> receivedProducts = callback.getProductTest();
//
//        for (Product product: receivedProducts) {
//            listProducts.add(product.getName());
//        }
//        return listProducts;
//    }



    /**
     * ObservableList that populates the comboBox
     * @return the observableList which then is added to the comboBox
     */
    private ObservableList<ProductCategories> getCategories() {
        ObservableList<ProductCategories> Products = FXCollections.observableArrayList();
        Products.addAll(ProductCategories.Bread, ProductCategories.Fruit, ProductCategories.Vegetable, ProductCategories.Dairy, ProductCategories.Pastries);

        return Products;
    }

    @Override
    public Node getStyleableNode() {
        return null;
    }

    /**
     * Alerts the user if a field is empty
     */
    private void alert(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Please enter a value for every field!");

        alert.showAndWait();
    }

    public String getOrgProd() {
        return orgProd;
    }

    public void setOrgProd(String orgProd) {
        this.orgProd = orgProd;
    }
}
