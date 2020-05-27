package code.view.panes;

import code.control.Callback;
import code.entities.Ingredient;
import code.entities.Product;
import code.entities.Styles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
public class DashboardPane extends Pane implements EnhancedPane {
    private TableView<Ingredient> ingredients;
    private TableView<Product> products;
    private Callback controller;
    private VBox topLeft, bottomLeft;
    private HBox topRight, bottomRight;


    public DashboardPane(Callback controller) {
        this.controller = controller;
        getStylesheets().add("styles.css");

        this.topLeft = initIngredients();
        this.topRight = initDeliveries();
        this.bottomLeft = initProducts();
        this.bottomRight = initBottomRight();

        setStyle(Styles.getPane());
        getChildren().addAll(topLeft, topRight, bottomLeft, bottomRight);
        setPrefSize(1054, 736);
    }

    private VBox initIngredients() {
        Label label = new Label("Ingredients");
        label.setStyle(Styles.getBoxTitle());

        TableColumn<Ingredient, String> name = new TableColumn<>("Ingredient");
        name.setStyle(Styles.getTableColumn());
        name.setCellValueFactory(new PropertyValueFactory<>("type"));
        name.setPrefWidth(548);

        TableColumn<Ingredient, String> stock = new TableColumn<>("Stock");
        stock.setStyle(Styles.getTableColumn());
        stock.setCellValueFactory(new PropertyValueFactory<>("stockAndUnit"));
        stock.setPrefWidth(100);

        ObservableList<Ingredient> values = FXCollections.observableArrayList();
        values.addAll(Arrays.asList(controller.getIngredients()));

        this.ingredients = new TableView<>();
        this.ingredients.setStyle(Styles.getDashboardTable());
        this.ingredients.setMaxWidth(650);
        this.ingredients.setMaxHeight(270);
        this.ingredients.getColumns().addAll(name, stock);
        this.ingredients.setItems(values);

        VBox box = new VBox(10);
        box.setPrefSize(663, 338);
        box.setStyle(Styles.getDashboardBox());
        box.getChildren().addAll(label, this.ingredients);
        box.setLayoutX(20);
        box.setLayoutY(20);

        return box;
    }

    private HBox initDeliveries() {
        Label label = new Label("Deliveries");
        label.setStyle(Styles.getBoxTitle());

        HBox box = new HBox(10);
        box.setPrefSize(332, 338);
        box.setStyle(Styles.getDashboardBox());
        box.getChildren().add(label);
        box.setLayoutX(702);
        box.setLayoutY(20);

        return box;
    }

    private VBox initProducts() {
        Label label = new Label("Products");
        label.setStyle(Styles.getBoxTitle());

        TableColumn<Product, String> name = new TableColumn<>("Product");
        name.setStyle(Styles.getTableColumn());
        name.setCellValueFactory(new PropertyValueFactory<>("type"));
        name.setPrefWidth(548);

        TableColumn<Product, String> stock = new TableColumn<>("Stock");
        stock.setStyle(Styles.getTableColumn());
        stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        stock.setPrefWidth(100);

        ObservableList<Product> values = FXCollections.observableArrayList();
        values.addAll(Arrays.asList(controller.getProducts()));

        this.products = new TableView<>();
        this.products.setStyle(Styles.getDashboardTable());
        this.products.setMaxWidth(650);
        this.products.setMaxHeight(270);
        this.products.getColumns().addAll(name, stock);
        this.products.setItems(values);

        VBox box = new VBox(10);
        box.setPrefSize(663, 338);
        box.setStyle(Styles.getDashboardBox());
        box.getChildren().addAll(label, this.products);
        box.setLayoutX(20);
        box.setLayoutY(378);

        return box;
    }

    private HBox initBottomRight() {
        HBox box = new HBox(10);
        box.setPrefSize(332, 338);
        box.setStyle(Styles.getDashboardBox());
        box.setLayoutX(702);
        box.setLayoutY(378);

        return box;
    }

    public void expand() {
        setPrefWidth(1200);
        this.topLeft.setPrefSize(760, 338);
        this.bottomLeft.setPrefSize(760, 338);

        this.topRight.setPrefSize(380, 338);
        this.topRight.setLayoutX(800);

        this.bottomRight.setPrefSize(380, 338);
        this.bottomRight.setLayoutX(800);
    }

    public void contract() {
        setPrefWidth(1054);
        this.topLeft.setPrefSize(663, 338);
        this.bottomLeft.setPrefSize(663, 338);

        this.topRight.setPrefSize(332, 338);
        this.topRight.setLayoutX(702);

        this.bottomRight.setPrefSize(332, 338);
        this.bottomRight.setLayoutX(702);
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
