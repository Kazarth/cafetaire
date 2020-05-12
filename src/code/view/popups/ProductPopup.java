package code.view.popups;


import code.control.Callback;
import code.entities.Product;
import code.entities.ProductCategories;
import code.entities.Styles;
import code.view.panes.ProductsPane;
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

public class ProductPopup extends AnchorPane {
    private JFrame frame;

    private Label label_Title;
    private Label label_Name;
    private Label label_Category;
    private Label label_Number;
    private Label label_Phone;

    private TextField textField_Name;
    private ComboBox<ProductCategories> categoryBox;
    private Spinner<Integer> numberSpinner;
    private TextField textField_Phone;

    private Button button_Add;
    private Button button_Cancel;

    private ProductsPane source;
    private Callback callback;
    private String orgProd;

    public ProductPopup(ProductsPane source, Callback callback, int opener) {
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
        label_Title = new Label("ADD NEW PRODUCT");
        label_Title.setStyle(Styles.getPopTitle());
        label_Title.setLayoutX(162.0); label_Title.setLayoutY(20);
        label_Title.setPrefWidth(300); label_Title.setPrefHeight(40);

        // Product Name pane
        label_Name = new Label("Product");
        label_Name.setStyle("-fx-text-fill: #000;");
        label_Name.setPrefWidth(220); label_Name.setPrefHeight(40);
        label_Name.setLayoutX(56.0); label_Name.setLayoutY(100);

        textField_Name = new TextField();
        textField_Name.setPromptText("Enter Product Name");
        textField_Name.setStyle(Styles.getPopField());
        textField_Name.setPrefWidth(360); textField_Name.setPrefHeight(40);
        textField_Name.setLayoutX(144.0); textField_Name.setLayoutY(100);

        // Category pane
        label_Category = new Label("Category");
        label_Category.setStyle("-fx-text-fill: #000;");
        label_Category.setPrefWidth(220.0); label_Category.setPrefHeight(40);
        label_Category.setLayoutX(56.0); label_Category.setLayoutY(160);

        categoryBox = new ComboBox();
        categoryBox.setPromptText("Select category");
        categoryBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        categoryBox.setPrefWidth(360.0); categoryBox.setPrefHeight(40);
        categoryBox.setLayoutX(144.0); categoryBox.setLayoutY(160);
        categoryBox.setItems(getCategories()); // testing

        // Spinner Pane
        label_Number = new Label("Amount");
        label_Number.setStyle("-fx-text-fill: #000;");
        label_Number.setPrefWidth(220); label_Number.setPrefHeight(40);
        label_Number.setLayoutX(56.0); label_Number.setLayoutY(220);

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
        label_Phone = new Label("Phone nr.");
        label_Phone.setStyle("-fx-text-fill: #000;");
        label_Phone.setPrefWidth(220); label_Phone.setPrefHeight(40);
        label_Phone.setLayoutX(56.0);  label_Phone.setLayoutY(280);

        textField_Phone =   new TextField();
        textField_Phone.setPromptText("Enter phone number");
        textField_Phone.setStyle(Styles.getPopField());
        textField_Phone.setPrefWidth(360);   textField_Phone.setPrefHeight(40);
        textField_Phone.setLayoutX(144.0);   textField_Phone.setLayoutY(280);

        // Button pane
        button_Add = new Button();
        button_Add.setStyle(Styles.getPopAddButton());
        button_Add.setPrefWidth(200); button_Add.setPrefHeight(40);
        button_Add.setLayoutX(75); button_Add.setLayoutY(340);

        //New Product
        if (opener == 0){
            button_Add.setText("ADD NEW PRODUCT");
            button_Add.setOnAction(e -> {
                addAction();
            });

        // Edit Product
        } else if (opener == 1){
            button_Add.setText("SAVE PRODUCT");
            button_Add.setOnAction(e -> {
                editAction();
            });
        }

        button_Cancel = new Button("CANCEL");
        button_Cancel.setStyle(Styles.getPopCancelButton());
        button_Cancel.setPrefWidth(200); button_Cancel.setPrefHeight(40);
        button_Cancel.setLayoutX(325.0); button_Cancel.setLayoutY(340);
        button_Cancel.setOnAction(e -> cancelAction());

        // Add all children

            getChildren().addAll(
                    label_Title,
                    label_Name, textField_Name,
                    label_Category, categoryBox,
                    label_Number, numberSpinner,
//                phoneLbl,phoneField,
                    button_Add, button_Cancel);
    }

    /**
     * Action performed Add-button
     */
    public void addAction() {
        Product product;

        String productName = textField_Name.getText();
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
        String name = textField_Name.getText();
        ProductCategories category = categoryBox.getValue();
        int quantity = numberSpinner.getValue();

        if (orgProd.equals(name)){
            Product product = callback.getProductTest(textField_Name.getText());
            product.setCategory(category);
            product.setQuantity(quantity);
            source.refresh();
            close();
        } else if (!orgProd.equals(name)){
            Product product = new Product(name, category, quantity);
            callback.addProductTest(product);
            callback.removeProductTest(orgProd);
            source.addNewProduct(product);
            source.removeProduct();
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
        textField_Name.setText(txt);
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
