package code.view.panes;

import code.entities.Ingredient;
import code.entities.Styles;
import code.view.popups.IngredientPopup;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import code.control.Callback;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * The class is the Ingredients panel for the Cafetairé application.
 * @author Tor Stenfeldt, Georg Grankvist, Lucas Eliasson
 * @version 1.0
 */
public class IngredientsPane extends Pane implements EnhancedPane {
    private VBox mainContainer;
    private HBox upperHbox;
    private HBox hBoxFiller;
    private HBox btnContainer;
    private FlowPane bottomPane;

    private Spinner<Integer> numberSpinner = new Spinner<>();
    private TableView<Ingredient> tableView;
    private TableColumn<Ingredient, String> nameColumn = new TableColumn<>("Name");
    private TableColumn<Ingredient, String> categoryColumn = new TableColumn<>("Category");
    private TableColumn<Ingredient, Integer> stockColumn = new TableColumn<>("Stock");
    private TableColumn<Ingredient, String> supplierColumn = new TableColumn<>("Supplier");

    private TextField searchTextField;
    private Callback callback;

    public IngredientsPane (Callback callback) {
        this.callback = callback;

        this.mainContainer = new VBox();
        this.mainContainer.setStyle(Styles.getPane());
        this.mainContainer.setPrefSize(1014, 695);
        this.mainContainer.setLayoutX(20);
        this.mainContainer.setLayoutY(20);
        this.mainContainer.getChildren().addAll(initTopContainer(), initFlowBottom());

        getStylesheets().add("styles.css");
        setStyle(Styles.getPane());
        getChildren().add(this.mainContainer);
        setPrefSize(1054, 736);
    }

    /**
     * Initializes and returns an HBox containing the title for the pane.
     * @return hBox
     */

    public HBox initUpperHBox () {
        Text textTitle = new Text("INGREDIENTS");
        textTitle.setStyle(Styles.getTitle());
        textTitle.setFill(Paint.valueOf("#619f81"));

        this.upperHbox = new HBox(textTitle);
        this.upperHbox.setPrefSize(1014, 75);
        this.upperHbox.setAlignment(Pos.CENTER);
        this.upperHbox.setStyle(
                "-fx-background-radius: 20 20 0 0;" +
                "-fx-background-color: #FFFFFF;"
        );

        return this.upperHbox;
    }

    /**
     * Initializes and returns a "filler" HBox, positioned inbetween the upper Title HBox and the
     * button HBoxes.
     * @return hBoxFiller
     */
    public HBox initFillerHBox () {
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
     * Initializes and returns the HBox containing the buttons to add, remove, and edit Ingredients.
     * Also contains a TextField used to search the TableView.
     * @return hBox
     */
    public HBox initHBoxLeft() {
        Button button_newIngredient = new Button("ADD INGREDIENT");
        Button button_removeIngredient = new Button("REMOVE INGREDIENT");
        Button button_editIngredient = new Button( "EDIT INGREDIENT");

        button_newIngredient.getStyleClass().add("greenButtonPanel");
        button_newIngredient.setMinWidth(170);
        button_newIngredient.setPrefHeight(40);

        button_removeIngredient.getStyleClass().add("greenButtonPanel");
        button_removeIngredient.setMinWidth(170);
        button_removeIngredient.setPrefHeight(40);

        button_editIngredient.getStyleClass().add("greenButtonPanel");
        button_editIngredient.setMinWidth(170);
        button_editIngredient.setPrefHeight(40);

        HBox hBox = new HBox(button_newIngredient, button_removeIngredient, button_editIngredient);
        hBox.setSpacing(10);
        hBox.setMinSize(580, 75);
        hBox.setPrefSize(580, 75);
        hBox.setAlignment(Pos.CENTER_LEFT);

        hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        button_newIngredient.setOnAction(e -> addNewIngredientAction());
        button_removeIngredient.setOnAction(e -> removeIngredient());
        button_editIngredient.setOnAction(e -> editAction());

        hBox.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-padding: 0 50 0 50"
        );

        return hBox;
    }

    /**
     * Initializes and returns an HBox containing the Spinner for increasing and decreasing stock.
     * @return hBox
     */
    public HBox initHBoxRight() {
        Button button_Add = new Button();
        Button button_Remove = new Button();

        final SpinnerValueFactory.IntegerSpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 80);
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

        this.btnContainer = new HBox(this.numberSpinner, button_Add, button_Remove, this.searchTextField);
        this.btnContainer.setSpacing(10);

        this.btnContainer.setMinSize(380, 75);
        this.btnContainer.setPrefSize(434, 75);

        this.btnContainer.setAlignment(Pos.CENTER_RIGHT);
        this.btnContainer.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-padding: 0 50 0 50;"
        );

        return this.btnContainer;
    }

    /**
     * Initializes and returns an HBox containing the buttons in the pane.
     * @return hBox
     */
    public HBox initBtnContainer() {
        HBox btnContainer = new HBox();
        btnContainer.setPrefSize(1014, 75);
        btnContainer.getChildren().addAll(initHBoxLeft(), initHBoxRight());
        return btnContainer;
    }

    /**
     * Initializes and returns a FlowPane containing the TableView for the pane.
     * @return pane
     */
    public FlowPane initFlowBottom() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockAndUnit"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        nameColumn.setPrefWidth(228);
        categoryColumn.setPrefWidth(228);
        stockColumn.setPrefWidth(228);
        supplierColumn.setPrefWidth(228);

        nameColumn.setStyle(Styles.getTableColumn());
        categoryColumn.setStyle(Styles.getTableColumn());
        stockColumn.setStyle(Styles.getTableColumn());
        supplierColumn.setStyle(Styles.getTableColumn());

        nameColumn.setResizable(false);
        categoryColumn.setResizable(false);
        stockColumn.setResizable(false);
        supplierColumn.setResizable(false);

        tableView = new TableView<>();
        tableView.setPrefSize(914, 465);
        tableView.setStyle(Styles.getTableRowSelected());
        tableView.getColumns().addAll(nameColumn, categoryColumn, stockColumn, supplierColumn);

        bottomPane = new FlowPane();
        bottomPane.setPrefSize(1014, 505);
        bottomPane.setAlignment(Pos.CENTER);
        bottomPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        bottomPane.getChildren().add(tableView);

        tableView.setItems(getIngredient());

        bottomPane.setStyle(
                "-fx-alignment: center;" +
                " -fx-background-color: #fff;" +
                " -fx-background-radius: 0 0 20 20;" +
                " -fx-padding: 0 0 50 0;"
        );

        return bottomPane;
    }

    /**
     * Expands the pane and makes the menuPane smaller
     */
    public void expand() {
        setPrefWidth(1200);
        this.mainContainer.setPrefWidth(1160);

        this.upperHbox.setPrefWidth(1160);
        this.hBoxFiller.setPrefWidth(1160);
        this.btnContainer.setPrefWidth(580);
        this.bottomPane.setPrefWidth(1160);

        this.tableView.setPrefWidth(1062);
        this.nameColumn.setPrefWidth(265);
        this.categoryColumn.setPrefWidth(265);
        this.stockColumn.setPrefWidth(265);
        this.supplierColumn.setPrefWidth(265);
    }

    /**
     * Makes the pane smaller and expands the menuPane
     */
    public void contract() {
        setPrefWidth(1054);
        this.mainContainer.setPrefWidth(1014);

        this.upperHbox.setMinWidth(1014);
        this.hBoxFiller.setMinWidth(1014);
        this.btnContainer.setMinWidth(434);
        this.bottomPane.setMinWidth(1014);

        this.tableView.setPrefWidth(916);
        this.nameColumn.setPrefWidth(228);
        this.categoryColumn.setPrefWidth(229);
        this.stockColumn.setPrefWidth(229);
        this.supplierColumn.setPrefWidth(228);
    }

    /**
     * Refreshes the tableView
     */
    public void refresh(){
        tableView.refresh();
    }

    /**
     * Initializes and returns a VBox containing the top half of the pane.
     * @return pane
     */
    public VBox initTopContainer(){
        VBox vBox =  new VBox(initUpperHBox(),initFillerHBox(), initBtnContainer());
        vBox.setPrefSize(1036, 190);
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setStyle("-fx-background-color: #FFFFFF; -fx-background-radius: 20 20 0 0;");

        return vBox;
    }

    /**
     * Returns the value entered into the NumberSpinner.
     * @return
     */

    public int getNumberSpinnerValue() {
        return numberSpinner.getValue();
    }

    /**
     * @param ingredient
     */
    public void addNewIngredient(Ingredient ingredient) {
        resetSearchField();
        tableView.getItems().add(ingredient);
    }

    /**
     * Adds a new ingredient from user input
     */
    public void addNewIngredientAction() {
        resetSearchField();
        try {
            new IngredientPopup(this, callback, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method used to edit an item in the tableView
     */
    public void editAction() {
        resetSearchField();
        String name = tableView.getSelectionModel().getSelectedItem().getType();
        IngredientPopup pane;

        if (name != null) {
            try {
                pane = new IngredientPopup(this, callback, 1);
                Ingredient ingredient = callback.getIngredient(name);
                pane.setOrgIngredient(name);

                if (ingredient.getSupplier() == null) {
                    pane.setValuesForIngredient(ingredient.getType(), ingredient.getCategory(), "", ingredient.getUnit());
                } else {
                    pane.setValuesForIngredient(ingredient.getType(), ingredient.getCategory(), ingredient.getSupplier().getName(), ingredient.getUnit());
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Removes selected ingredient from the stock
     */
    public void removeIngredient() {
        resetSearchField();
        ObservableList<Ingredient> ingredientSelected, allIngredients;
        allIngredients = tableView.getItems();
        ingredientSelected = tableView.getSelectionModel().getSelectedItems();
        code.entities.Ingredient ingredient = tableView.getSelectionModel().getSelectedItem();

        try {
            ingredientSelected.forEach(allIngredients::remove);
            callback.removeIngredient(ingredient.getType());

        }catch (NoSuchElementException e) {
            System.err.println("Last element - NullPointer \nRemoveIngredient \nIngredientPane Row 366");
            callback.removeIngredient(ingredient.getType());
            callback.catchSafeState();
        }
    }

    /**
     * Increments the selected ingredients stock by 1
     */
    public void addQuantity() {
        Ingredient ingredient = tableView.getSelectionModel().getSelectedItem();

        if (ingredient != null) {
            int prodQuantity = ingredient.getStock();
            ingredient.setStock(prodQuantity + getNumberSpinnerValue());
            ingredient.setStockAndUnit();
        } else {
            noIngredientSelected();
        }

        refresh();
    }

    /**
     * Remove quantity from existing product
     */
    public void removeQuantity() {
        Ingredient ingredient = tableView.getSelectionModel().getSelectedItem();

        if (ingredient != null){
            int prodQuantity = ingredient.getStock();
            ingredient.setStock(prodQuantity - getNumberSpinnerValue());
            ingredient.setStockAndUnit();
        } else {
            noIngredientSelected();
        }
        tableView.refresh();
    }

    /**
     * Searchbar functionality.
     */
    private void searchRecord(Observable observable, String oldValue, String newValue) {
        FilteredList<Ingredient> filteredList = new FilteredList<>(getIngredient(), p -> true);

        if (!searchTextField.getText().equals("")) {
            filteredList.setPredicate(tableView -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String typedText = newValue.toLowerCase();

                if (tableView.getType().toLowerCase().contains(typedText)) {
                    return true;

                } else if (tableView.getSupplier() != null) {
                    tableView.getSupplier().getName().toLowerCase().contains(typedText);
                    return true;

                } else if (String.valueOf(tableView.getStock()).toLowerCase().contains(typedText))
                    return true;

                else
                    return false;

            });

            SortedList<Ingredient> sortedList = new SortedList<>(filteredList);
            sortedList.comparatorProperty().bind(tableView.comparatorProperty());
            tableView.setItems(sortedList);
        }

        else
            tableView.setItems(getIngredient());
    }

    private ObservableList<Ingredient> getIngredient() {
        ObservableList <Ingredient> ingredients = FXCollections.observableArrayList();
        Ingredient[] receivedIngredients = callback.getIngredients();
        ingredients.addAll(Arrays.asList(receivedIngredients));
        return ingredients;
    }

    /**
     * Popup comes up if you try to create an
     * ingredient without entering a name for it.
     */

    public void noIngredientSelected() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("No Ingredient Selected");
        alert.setHeaderText(null);
        alert.setContentText("Please select an ingredient!");

        alert.showAndWait();
    }


    public void resetSearchField () {
        searchTextField.clear();
    }
}
