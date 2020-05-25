package code.view.panes;

import code.control.Callback;
import code.entities.Ingredient;
import code.entities.Product;
import code.entities.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;

import java.util.Arrays;

/**
 * Dashboard.java
 * The 'main' pane which the application will launch to.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class DashboardPane extends StackPane implements EnhancedPane {
    private TableView<Ingredient> ingredients;
    private TableView<Product> products;
    private Callback controller;

    public DashboardPane(Callback controller) {
        this.controller = controller;

        getStylesheets().add("styles.css");

        Label ingredientsLabel = new Label("Ingredients");
        ingredientsLabel.setStyle(Styles.getBoxTitle());

        TableColumn<Ingredient, String> ingredientName = new TableColumn<>("Ingredient");
        ingredientName.setStyle(Styles.getTableColumn());
        ingredientName.setCellValueFactory(new PropertyValueFactory<>("type"));
        ingredientName.setPrefWidth(548);

        TableColumn<Ingredient, String> ingredientStock = new TableColumn<>("Stock");
        ingredientStock.setStyle(Styles.getTableColumn());
        ingredientStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        ingredientStock.setPrefWidth(100);

        ObservableList<Ingredient> ingredientValues = FXCollections.observableArrayList();
        ingredientValues.addAll(Arrays.asList(controller.getIngredients()));

        this.ingredients = new TableView<>();
        this.ingredients.setStyle(Styles.getDashboardTable());
        this.ingredients.setMaxWidth(650);
        this.ingredients.setMaxHeight(270);
        this.ingredients.getColumns().addAll(ingredientName, ingredientStock);
        this.ingredients.setItems(ingredientValues);

        VBox topLeft = new VBox(10);
        topLeft.setPrefSize(670, 330);
        topLeft.setStyle(Styles.getDashboardBox());
        topLeft.getChildren().addAll(ingredientsLabel, this.ingredients);

        Label deliveries = new Label("Deliveries");
        deliveries.setStyle(Styles.getBoxTitle());

        HBox topRight = new HBox(10);
        topRight.setPrefSize(330, 330);
        topRight.setStyle(Styles.getDashboardBox());
        topRight.getChildren().add(deliveries);

        HBox topBoxes = new HBox(25);
        topBoxes.setAlignment(Pos.CENTER);
        topBoxes.getChildren().addAll(topLeft, topRight);

        Label productsLabel = new Label("Products");
        productsLabel.setStyle(Styles.getBoxTitle());

        TableColumn<Product, String> productName = new TableColumn<>("Product");
        productName.setStyle(Styles.getTableColumn());
        productName.setCellValueFactory(new PropertyValueFactory<>("type"));
        productName.setPrefWidth(548);

        TableColumn<Product, String> productStock = new TableColumn<>("Stock");
        productStock.setStyle(Styles.getTableColumn());
        productStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productStock.setPrefWidth(100);

        ObservableList<Product> productValues = FXCollections.observableArrayList();
        productValues.addAll(Arrays.asList(controller.getProducts()));

        this.products = new TableView<>();
        this.products.setStyle(Styles.getDashboardTable());
        this.products.setMaxWidth(650);
        this.products.setMaxHeight(270);
        this.products.getColumns().addAll(productName, productStock);
        this.products.setItems(productValues);

        VBox bottomLeft = new VBox(10);
        bottomLeft.setPrefSize(670, 330);
        bottomLeft.setStyle(Styles.getDashboardBox());
        bottomLeft.getChildren().addAll(productsLabel, this.products);

        HBox bottomRight = new HBox(10);
        bottomRight.setPrefSize(330, 330);
        bottomRight.setStyle(Styles.getDashboardBox());

        HBox bottomBoxes = new HBox(25);
        bottomBoxes.setAlignment(Pos.CENTER);
        bottomBoxes.getChildren().addAll(bottomLeft, bottomRight);

        VBox boxes = new VBox(25);
        boxes.setAlignment(Pos.CENTER);
        boxes.getChildren().addAll(topBoxes, bottomBoxes);

        setStyle(Styles.getPane());
        getChildren().add(boxes);
        setPrefSize(1086, 768);
    }

    public void expand() {
        setPrefWidth(1346);
    }

    public void contract() {
        setPrefWidth(1086);
    }

    public void refresh() {
        ObservableList<Ingredient> ingredientValues = FXCollections.observableArrayList();
        ingredientValues.addAll(Arrays.asList(controller.getIngredients()));
        this.ingredients.setItems(ingredientValues);
        this.ingredients.refresh();

        ObservableList<Product> productValues = FXCollections.observableArrayList();
        productValues.addAll(Arrays.asList(controller.getProducts()));
        this.products.setItems(productValues);
        this.products.refresh();
    }
}
