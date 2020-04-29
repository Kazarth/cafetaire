package View;

import Control.Callback;
import Entities.Styles;
import Entities.Supplier;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.swing.*;
import java.util.ArrayList;


public class AddNewSupplierPane extends AnchorPane {
    private JFrame frame;

    private Label title;
    private Label nameLbl;
    private Label categoryLbl, supplierLbl; // Ryck denna sista
    private Label emailLbl;
    private Label phoneLbl;

    private TextField nameField;
    private ComboBox<String> categoryBox, supplierBox; // lägg in och läs in listor som vanligt?
    private TextField emailField;
    private TextField phoneField;

    private Button addButton, cancelButton;

    private SupplierPane source;
    private Callback callback;
    private ArrayList<String> suppliers; // test purposes



    public AddNewSupplierPane(SupplierPane source, Callback callback) {
        // init Frame
        frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Add new ingredient");
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

        // test values
        suppliers = new ArrayList();
        suppliers.add("Lucas");
        suppliers.add("Julia");
        suppliers.add("Coca Cola");

        // Background pane
        setMaxWidth(600); setMaxHeight(400);
        setPrefWidth(600); setPrefHeight(400);
        setStyle(
                "-fx-background-color: #fff"
        );

        // title pane
        title = new Label("ADD NEW SUPPLIER");
        title.setStyle(Styles.getPopTitle());
        title.setLayoutX(162.0); title.setLayoutY(20);
        title.setPrefWidth(300); title.setPrefHeight(40);

        // Supplier Name pane
        nameLbl = new Label("Supplier");
        nameLbl.setStyle("-fx-text-fill: #000;");
        nameLbl.setPrefWidth(220); nameLbl.setPrefHeight(40);
        nameLbl.setLayoutX(56.0); nameLbl.setLayoutY(100);

        nameField = new TextField();
        nameField.setPromptText("Enter Supplier name");
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
        categoryBox.setStyle(Styles.getPopField());
        categoryBox.setPrefWidth(360.0); categoryBox.setPrefHeight(40);
        categoryBox.setLayoutX(144.0); categoryBox.setLayoutY(160);
        categoryBox.setItems(getCategories()); // testing

        // Email Pane
        emailLbl = new Label("E-mail");
        emailLbl.setStyle("-fx-text-fill: #000;");
        emailLbl.setPrefWidth(220); emailLbl.setPrefHeight(40);
        emailLbl.setLayoutX(56.0); emailLbl.setLayoutY(220);

        emailField  =   new TextField();
        emailField.setPromptText("Enter E-mail address");
        emailField.setStyle(Styles.getPopField());
        emailField.setPrefWidth(360); emailField.setPrefHeight(40);
        emailField.setLayoutX(144.0); emailField.setLayoutY(220);
        //supplierLbl.setLayoutX(56.0); supplierLbl.setLayoutY(220);  //Positioner

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
        addButton = new Button("ADD NEW SUPPLIER");
        addButton.setStyle(Styles.getPopAddButton());
        addButton.setPrefWidth(200); addButton.setPrefHeight(40);
        addButton.setLayoutX(75); addButton.setLayoutY(340);
        addButton.setOnAction(e -> addAction());

        cancelButton = new Button("CANCEL");
        cancelButton.setStyle(Styles.getPopCancelButton());
        cancelButton.setPrefWidth(200); cancelButton.setPrefHeight(40);
        cancelButton.setLayoutX(325.0); cancelButton.setLayoutY(340);
        cancelButton.setOnAction(e -> cancelAction());

        // Add all children
        getChildren().addAll(title, nameLbl, nameField, categoryLbl, categoryBox, emailLbl, emailField, phoneLbl,phoneField, addButton, cancelButton);
    }

    /**
     * Action performed Add-button
     */
    public void addAction() {
        Supplier supp = null;

        String supplierName = nameField.getText();
        String category     = categoryBox.getSelectionModel().getSelectedItem();
        String email        = emailField.getText();
        String phone        = phoneField.getText();

        supp = new Supplier(supplierName, category, email, phone);

        if (callback.addSupplier(supp)) {
            source.addNewSupplier(supp);
        }
        close();
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



    /*
    //From database
    private ObservableList<String> getSuppliersFromDatabase() {
        ObservableList<String> listSuppliers = FXCollections.observableArrayList();
        Supplier[] receivedSuppliers = callback.getSuppliers();

        for (Supplier supplier: receivedSuppliers) {
            listSuppliers.add(supplier.getName());
        }
        return listSuppliers;
    }

     */


    /**
     * For testing purposes
     * @return
     */
    private ObservableList<String> getSuppliers() {
        ObservableList<String> ingredients = FXCollections.observableArrayList();
        ingredients.add("Lucas AB");
        ingredients.add("Georg Inc");
        ingredients.add("Paul M");
        ingredients.add("Coca Cola");
        return ingredients;
    }

    /**
     * For testing purposes
     * @return
     */
    private ObservableList<String> getCategories() {
        ObservableList<String> ingredients = FXCollections.observableArrayList();
        ingredients.add("Dry Food");
        ingredients.add("Fresh Food");
        ingredients.add("Drink");
        return ingredients;
    }
}

