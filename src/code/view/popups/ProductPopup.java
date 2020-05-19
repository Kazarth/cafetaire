package code.view.popups;

import code.control.Callback;
import code.entities.Product;
import code.entities.ProductCategories;
import code.entities.Recipe;
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
    private ProductsPane source;
    private Callback callback;

    private JFrame frame;
    private TextField textField_Name;
    private ComboBox<ProductCategories> categoryBox;
    private ComboBox<Recipe> ComboBox_Recipe;
    private Spinner<Integer> numberSpinner;
    private String orgProd;

    public ProductPopup(ProductsPane source, Callback callback, int opener) {
        this.source = source;
        this.callback = callback;

        // Background pane
        setMaxWidth(600); setMaxHeight(400);
        setPrefWidth(600); setPrefHeight(400);
        setStyle("-fx-background-color: #fff");

        // title pane
        Label label_Title = new Label("ADD NEW PRODUCT");
        label_Title.setStyle(Styles.getPopTitle());
        label_Title.setLayoutX(162.0); label_Title.setLayoutY(20);
        label_Title.setPrefWidth(300); label_Title.setPrefHeight(40);

        // Product Name pane
        Label label_Name = new Label("Product");
        label_Name.setStyle("-fx-text-fill: #000;");
        label_Name.setPrefWidth(220); label_Name.setPrefHeight(40);
        label_Name.setLayoutX(56.0); label_Name.setLayoutY(100);

        this.textField_Name = new TextField();
        this.textField_Name.setPromptText("Enter Product Name");
        this.textField_Name.setStyle(Styles.getPopField());
        this.textField_Name.setPrefWidth(360);
        this.textField_Name.setPrefHeight(40);
        this.textField_Name.setLayoutX(144.0);
        this.textField_Name.setLayoutY(100);

        // Category pane
        Label label_Category = new Label("Category");
        label_Category.setStyle("-fx-text-fill: #000;");
        label_Category.setPrefWidth(220.0); label_Category.setPrefHeight(40);
        label_Category.setLayoutX(56.0); label_Category.setLayoutY(160);

        this.categoryBox = new ComboBox<>();
        this.categoryBox.setPromptText("Select category");
        this.categoryBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        this.categoryBox.setPrefWidth(360.0);
        this.categoryBox.setPrefHeight(40);
        this.categoryBox.setLayoutX(144.0);
        this.categoryBox.setLayoutY(160);
        this.categoryBox.setItems(getCategories()); // testing

        // Spinner Pane
        Label label_Number = new Label("Amount");
        label_Number.setStyle("-fx-text-fill: #000;");
        label_Number.setPrefWidth(220); label_Number.setPrefHeight(40);
        label_Number.setLayoutX(56.0); label_Number.setLayoutY(220);

        final SpinnerValueFactory.IntegerSpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100);
        this.numberSpinner = new Spinner<>();
        this.numberSpinner.setValueFactory(svf);
        this.numberSpinner.disabledProperty();
        this.numberSpinner.setEditable(true);
        this.numberSpinner.setPromptText("Enter Number Of Products");
        this.numberSpinner.setStyle(Styles.getPopField());
        this.numberSpinner.setPrefWidth(360);
        this.numberSpinner.setPrefHeight(40);
        this.numberSpinner.setLayoutX(144.0);
        this.numberSpinner.setLayoutY(220);

        // Recipe Pane
        Label label_Recipe = new Label("Recipe");
        label_Recipe.setStyle("-fx-text-fill: #000;");
        label_Recipe.setPrefWidth(220); label_Recipe.setPrefHeight(40);
        label_Recipe.setLayoutX(56.0);  label_Recipe.setLayoutY(280);

        this.ComboBox_Recipe = new ComboBox<>();
        this.ComboBox_Recipe.setPromptText("Select a recipe");
        this.ComboBox_Recipe.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        this.ComboBox_Recipe.setPrefWidth(360);
        this.ComboBox_Recipe.setPrefHeight(40);
        this.ComboBox_Recipe.setLayoutX(144);
        this.ComboBox_Recipe.setLayoutY(280);
        this.ComboBox_Recipe.setItems(populateRecipeBox());
        this.ComboBox_Recipe.setEditable(false);

        // Button pane
        Button button_Add = new Button();
        button_Add.setStyle(Styles.getPopAddButton());
        button_Add.setPrefWidth(200); button_Add.setPrefHeight(40);
        button_Add.setLayoutX(75); button_Add.setLayoutY(340);

        //New Product
        if (opener == 0) {
            button_Add.setText("ADD NEW PRODUCT");
            button_Add.setOnAction(e -> addAction());

        // Edit Product
        } else if (opener == 1) {
            button_Add.setText("SAVE PRODUCT");
            button_Add.setOnAction(e -> editAction());
        }

        Button button_Cancel = new Button("CANCEL");
        button_Cancel.setStyle(Styles.getPopCancelButton());
        button_Cancel.setPrefWidth(200); button_Cancel.setPrefHeight(40);
        button_Cancel.setLayoutX(325.0); button_Cancel.setLayoutY(340);
        button_Cancel.setOnAction(e -> cancelAction());

        // Add all children
        getChildren().addAll(
                label_Title,
                label_Name,
                this.textField_Name,
                label_Category,
                this.categoryBox,
                label_Number,
                this.numberSpinner,
                label_Recipe,
                this.ComboBox_Recipe,
                button_Add,
                button_Cancel
        );

        initFrame();
    }

    public void initFrame() {
        this.frame = new JFrame("FX");
        this.frame.setTitle("Add new PRODUCT");

        final JFXPanel fxPanel = new JFXPanel();
        this.frame.add(fxPanel);

        this.frame.setSize(600,460);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setUndecorated(true);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

        Platform.runLater(() -> fxPanel.setScene(new Scene(this)));
    }

    /**
     * Action performed Add-button
     */
    public void addAction() {
        Product product;

        String productName = this.textField_Name.getText();
        ProductCategories category = this.categoryBox.getSelectionModel().getSelectedItem();
        int quantity = this.numberSpinner.getValue();
        Recipe recipe = this.ComboBox_Recipe.getValue();
        Recipe noRecipe = new Recipe("No Recipe");

        if ((!productName.equals("")) && (category != null)) {
            if (recipe != null) {
                product = new Product(productName, category, quantity, recipe);
                System.out.println(product.toString());
                System.out.println(recipe.toString());
            } else {
                product = new Product(productName, category, quantity, noRecipe);
            }

            if (this.callback.addProduct(product)) {
                this.source.addNewProduct(product);
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
        this.frame.dispose();
    }

    /**
     * Method used to edit an item in the tableView in ProductPane and in the database
     * if name remains unchanged set values from the other fields to product
     * if name is changed remove old name from database and add the new one
     */
    public void editAction(){
        String name = this.textField_Name.getText();
        ProductCategories category = this.categoryBox.getValue();
        int quantity = this.numberSpinner.getValue();
        Recipe recipe = this.ComboBox_Recipe.getValue();
        Product product;

        if (this.orgProd.equals(name)){
            product = this.callback.getProduct(this.textField_Name.getText());
            product.setCategory(category);
            product.setStock(quantity);
            if (recipe == null) {
                product.setRecipe(null);
            } else {
                product.setRecipe(recipe);
            }
        } else {
            if (recipe == null){
                product = new Product(name, category, quantity, null);
            } else {
                product = new Product(name, category, quantity, recipe);
            }
            this.callback.addProduct(product);
            this.callback.removeProduct(this.orgProd);
            this.source.addNewProduct(product);
            this.source.removeProduct();
        }
        this.source.refresh();
        close();
    }

    /**
     *
     * @param txt set initial value for item to edit
     * @param category set initial value for item to edit
     * @param quantity set initial value for item to edit
     */
    public void setValuesForItem(String txt, ProductCategories category, int quantity, Recipe recipe) {
        this.textField_Name.setText(txt);
        this.categoryBox.getSelectionModel().select(category);
        this.numberSpinner.getValueFactory().setValue(quantity);
        this.ComboBox_Recipe.getSelectionModel().select(recipe);
    }

    /**
     * ObservableList that populates the comboBox
     * @return the observableList which then is added to the comboBox
     */
    private ObservableList<ProductCategories> getCategories() {
        ObservableList<ProductCategories> Products = FXCollections.observableArrayList();

        Products.addAll(
                ProductCategories.Bread,
                ProductCategories.Fruit,
                ProductCategories.Vegetable,
                ProductCategories.Dairy,
                ProductCategories.Pastries
        );

        return Products;
    }

    private ObservableList<Recipe> populateRecipeBox(){
        ObservableList<Recipe> recipes = FXCollections.observableArrayList();
        Recipe noRecipe = new Recipe("No Recipe");

        recipes.add(noRecipe);
        recipes.addAll(callback.getRecipes());

        return recipes;
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
        return this.orgProd;
    }

    public void setOrgProd(String orgProd) {
        this.orgProd = orgProd;
    }
}
