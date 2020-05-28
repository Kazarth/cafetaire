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
 * @version 4.0
 */
public class IngredientsPane extends StackPane implements EnhancedPane {
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
        this.getStylesheets().add("styles.css");
        mainContainer = new VBox();
        mainContainer.setMaxSize(1036, 698);

        mainContainer.getChildren().addAll(initTopContainer(), initFlowBottom());
        getChildren().add(mainContainer);

        mainContainer.setAlignment(Pos.CENTER);
        setStyle(Styles.getPane());
        mainContainer.setStyle(Styles.getPane());

        setPrefSize(1086, 768);
    }

    /**
     * Initializes and returns an HBox containing the title for the pane.
     * @return hBox
     */

    public HBox initUpperHBox () {

        Text textTitle = new Text();
        Font menuTitle = Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 24);
        textTitle.setFill(Paint.valueOf("#619f81"));
        textTitle.setFont(menuTitle);
        textTitle.setText("INGREDIENTS");

        upperHbox = new HBox();
        upperHbox.setPrefSize(1036, 75);
        upperHbox.setAlignment(Pos.CENTER);
        upperHbox.getChildren().add(textTitle);
        upperHbox.setStyle("-fx-background-radius: 20 20 0 0;" +
                "-fx-background-color: #FFFFFF;");

        return upperHbox;
    }

    /**
     * Initializes and returns a "filler" HBox, positioned inbetween the upper Title HBox and the
     * button HBoxes.
     * @return hBoxFiller
     */

    public HBox initFillerHBox () {
        hBoxFiller = new HBox();
        hBoxFiller.setPrefSize(1036, 40);
        hBoxFiller.setStyle("-fx-border-color: #6B6C6A; -fx-background-color: #FFFFFF; -fx-border-width: 1 0 1 0");
        return hBoxFiller;
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
        hBox.setMinSize(600, 75);
        hBox.setMaxSize(650, 75);
        hBox.setAlignment(Pos.CENTER_LEFT);

        hBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        button_newIngredient.setOnAction(e -> addNewIngredientAction());
        button_removeIngredient.setOnAction(e -> removeIngredient());
        button_editIngredient.setOnAction(e -> editAction());

        hBox.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 0 50 0 50");

        return hBox;
    }

    /**
     * Initializes and returns an HBox containing the Spinner for increasing and decreasing stock.
     * @return hBox
     */

    public HBox initHBoxRight(){
        Button button_Add = new Button();
        Button button_Remove = new Button();

        final SpinnerValueFactory.IntegerSpinnerValueFactory svf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, Integer.MAX_VALUE);
        numberSpinner.setValueFactory(svf);
        numberSpinner.disabledProperty();
        numberSpinner.setEditable(true);
        numberSpinner.setPrefHeight(38);
        numberSpinner.setPrefWidth(100);

        searchTextField = new TextField();
        searchTextField.setPromptText("SEARCH");
        searchTextField.setPrefHeight(32);
        searchTextField.setPrefWidth(150);
        searchTextField.textProperty().addListener(this::searchRecord);

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

        btnContainer = new HBox(numberSpinner, button_Add, button_Remove,searchTextField);
        btnContainer.setSpacing(10);

        btnContainer.setMinSize(435, 75);

        btnContainer.setAlignment(Pos.CENTER_RIGHT);
        btnContainer.setStyle("-fx-background-color: #FFFFFF; -fx-padding: 0 50 0 50;");

        return btnContainer;
    }

    /**
     * Initializes and returns an HBox containing the buttons in the pane.
     * @return hBox
     */

    public HBox initBtnContainer() {
        HBox btnContainer = new HBox();
        setPrefSize(1036, 75);
        btnContainer.getChildren().addAll(initHBoxLeft(), initHBoxRight());
        return btnContainer;
    }

    /**
     * Initializes and returns a FlowPane containing the TableView for the pane.
     * @return pane
     */

    public FlowPane initFlowBottom() {
        bottomPane = new FlowPane();

        bottomPane.setPadding(new Insets(15,15,15,15));

        bottomPane.setMinSize(1036, 508);
        bottomPane.setMaxSize(1036, 508);

        tableView = new TableView<>();

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        stockColumn.setCellValueFactory(new PropertyValueFactory<>("stockAndUnit"));
        supplierColumn.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        nameColumn.setPrefWidth(233);
        categoryColumn.setPrefWidth(234);
        stockColumn.setPrefWidth(234);
        supplierColumn.setPrefWidth(234);

        nameColumn.setStyle(Styles.getTableColumn());
        categoryColumn.setStyle(Styles.getTableColumn());
        stockColumn.setStyle(Styles.getTableColumn());
        supplierColumn.setStyle(Styles.getTableColumn());

        tableView.getColumns().addAll(nameColumn, categoryColumn, stockColumn, supplierColumn);

        tableView.setPrefHeight(458);
        tableView.setPrefWidth(936);
        tableView.setStyle(Styles.getTableRowSelected());

        bottomPane.setAlignment(Pos.CENTER);

        bottomPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        bottomPane.getChildren().add(tableView);

        tableView.setItems(getIngredient());

        bottomPane.setStyle("-fx-alignment: center;" +
                " -fx-background-color: #fff;" +
                " -fx-background-radius: 0 0 20 20;" +
                " -fx-padding: 0 0 50 0;");

        return bottomPane;
    }

    /**
     * Expands the pane and makes the menuPane smaller
     */
    public void expand() {
        setPrefWidth(1346);
        mainContainer.setMinWidth(1196);
        upperHbox.setMinWidth(1196);
        hBoxFiller.setMinWidth(1196);
        btnContainer.setMinWidth(560);
        bottomPane.setMinWidth(1196);

        tableView.setMinWidth(1096);
        nameColumn.setMinWidth(273);
        categoryColumn.setMinWidth(274);
        stockColumn.setMinWidth(274);
        supplierColumn.setMinWidth(274);
    }

    /**
     * Makes the pane smaller and expands the menuPane
     */
    public void contract() {
        setPrefWidth(1086);
        mainContainer.setMinWidth(1036);
        upperHbox.setMinWidth(1036);
        hBoxFiller.setMinWidth(1036);
        btnContainer.setMinWidth(435);
        bottomPane.setMinWidth(1036);

        tableView.setMinWidth(936);
        tableView.setMaxWidth(936);

        nameColumn.setMinWidth(233);
        categoryColumn.setMinWidth(234);
        stockColumn.setMinWidth(234);
        supplierColumn.setMinWidth(234);
        nameColumn.setMaxWidth(233);
        categoryColumn.setMaxWidth(234);
        stockColumn.setMaxWidth(234);
        supplierColumn.setMaxWidth(234);
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

        if (ingredient != null){
            double prodQuantity = ingredient.getStock();
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
            double prodQuantity = ingredient.getStock();
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
