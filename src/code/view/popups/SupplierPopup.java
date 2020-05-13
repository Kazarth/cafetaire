package code.view.popups;

import code.control.Callback;
import code.entities.Styles;
import code.entities.Supplier;
import code.view.panes.SupplierPane;
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

/**
 * The class handles a Pop-up window for adding supplier to supplier's in database.
 * @author Paul Moustakas, Lucas Eliasson
 * @version 1.0
 */
public class SupplierPopup extends AnchorPane {
    private SupplierPane source;
    private Callback callback;

    private JFrame frame;
    private TextField nameField;
    private ComboBox<String> categoryBox;
    private TextField emailField;
    private TextField phoneField;
    private String orgSupp;

    public SupplierPopup(SupplierPane source, Callback callback, int opener) {
        this.source = source;
        this.callback = callback;

        // Background pane
        setMaxWidth(600); setMaxHeight(400);
        setPrefWidth(600); setPrefHeight(400);
        setStyle("-fx-background-color: #fff");

        // title pane
        Label title = new Label("ADD NEW SUPPLIER");
        title.setStyle(Styles.getPopTitle());
        title.setLayoutX(162.0); title.setLayoutY(20);
        title.setPrefWidth(300); title.setPrefHeight(40);

        // Supplier Name pane
        Label nameLbl = new Label("Supplier");
        nameLbl.setStyle("-fx-text-fill: #000;");
        nameLbl.setPrefWidth(220); nameLbl.setPrefHeight(40);
        nameLbl.setLayoutX(56.0); nameLbl.setLayoutY(100);

        this.nameField = new TextField();
        this.nameField.setPromptText("Enter Supplier name");
        this.nameField.setStyle(Styles.getPopField());
        this.nameField.setPrefWidth(360);
        this.nameField.setPrefHeight(40);
        this.nameField.setLayoutX(144.0);
        this.nameField.setLayoutY(100);

        // Category pane
        Label categoryLbl = new Label("Category");
        categoryLbl.setStyle("-fx-text-fill: #000;");
        categoryLbl.setPrefWidth(220.0); categoryLbl.setPrefHeight(40);
        categoryLbl.setLayoutX(56.0); categoryLbl.setLayoutY(160);

        this.categoryBox = new ComboBox<>();
        this.categoryBox.setPromptText("Select category");
        this.categoryBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
        this.categoryBox.setPrefWidth(360.0);
        this.categoryBox.setPrefHeight(40);
        this.categoryBox.setLayoutX(144.0);
        this.categoryBox.setLayoutY(160);
        this.categoryBox.setItems(getCategories()); // testing

        // Email Pane
        Label emailLbl = new Label("E-mail");
        emailLbl.setStyle("-fx-text-fill: #000;");
        emailLbl.setPrefWidth(220); emailLbl.setPrefHeight(40);
        emailLbl.setLayoutX(56.0); emailLbl.setLayoutY(220);

        this.emailField  =   new TextField();
        this.emailField.setPromptText("Enter E-mail address");
        this.emailField.setStyle(Styles.getPopField());
        this.emailField.setPrefWidth(360);
        this.emailField.setPrefHeight(40);
        this.emailField.setLayoutX(144.0);
        this.emailField.setLayoutY(220);

        // Phone Pane
        Label phoneLbl = new Label("Phone nr.");
        phoneLbl.setStyle("-fx-text-fill: #000;");
        phoneLbl.setPrefWidth(220); phoneLbl.setPrefHeight(40);
        phoneLbl.setLayoutX(56.0);  phoneLbl.setLayoutY(280);

        this.phoneField = new TextField();
        this.phoneField.setPromptText("Enter phone number");
        this.phoneField.setStyle(Styles.getPopField());
        this.phoneField.setPrefWidth(360);
        this.phoneField.setPrefHeight(40);
        this.phoneField.setLayoutX(144.0);
        this.phoneField.setLayoutY(280);

        // Button pane
        Button addButton = new Button();
        addButton.setStyle(Styles.getPopAddButton());
        addButton.setPrefWidth(200); addButton.setPrefHeight(40);
        addButton.setLayoutX(75); addButton.setLayoutY(340);
        // New supplier
        if (opener == 0) {
            addButton.setText("ADD NEW SUPPLIER");
            addButton.setOnAction(e -> addAction());
        }
        // Edit supplier
        if (opener == 1){
            addButton.setText("SAVE SUPPLIER");
            addButton.setOnAction(e -> editAction());
        }

        Button cancelButton = new Button("CANCEL");
        cancelButton.setStyle(Styles.getPopCancelButton());
        cancelButton.setPrefWidth(200); cancelButton.setPrefHeight(40);
        cancelButton.setLayoutX(325.0); cancelButton.setLayoutY(340);
        cancelButton.setOnAction(e -> cancelAction());

        // Add all children
        getChildren().addAll(
                title,
                nameLbl,
                this.nameField,
                categoryLbl,
                this.categoryBox,
                emailLbl,
                this.emailField,
                phoneLbl,
                this.phoneField,
                addButton,
                cancelButton
        );

        initPanel();
    }

    public void initPanel() {
        this.frame = new JFrame("FX");
        this.frame.setTitle("Add new ingredient");

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
        Supplier supp;

        String supplierName = this.nameField.getText();
        String category     = this.categoryBox.getSelectionModel().getSelectedItem();
        String email        = this.emailField.getText();
        String phone        = this.phoneField.getText();

        supp = new Supplier(supplierName, category, email, phone);

        System.out.println(supp.toString());

        if (this.callback.addSupplier(supp)) {
            this.source.addNewSupplier(supp);
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
        this.frame.dispose();
    }

    /**
     * Method used to edit and item in the tableView in SupplierPane and database
     * if name remains unchanged set values from the other fields to supplier
     * if name is changed remove old name from database and add the new one
     */
    public void editAction(){
        String name = this.nameField.getText();
        String category = this.categoryBox.getValue();
        String email = this.emailField.getText();
        String phone = this.phoneField.getText();

        if (this.orgSupp.equals(name)){
            Supplier supplier = this.callback.getSupplier(this.nameField.getText());
            supplier.setCategory(category);
            supplier.setEmail(email);
            supplier.setPhone(phone);
            this.source.refresh();
            close();
        } else {
            Supplier supplier = new Supplier(name, category, email, phone);
            this.callback.addSupplier(supplier);
            this.callback.removeSupplier(this.orgSupp);
            this.source.addNewSupplier(supplier);
            this.source.removeSupplier();
            this.source.refresh();
            close();
        }
    }

    /**
     *
     * @param name initial value for item to be edited
     * @param category initial value for item to be edited
     * @param email initial value for item to be edited
     * @param phone initial value for item to be edited
     */
    public void setValuesForSupplier(String name, String category, String email, String phone) {
        this.nameField.setText(name);
        this.categoryBox.getSelectionModel().select(category);
        this.emailField.setText(email);
        this.phoneField.setText(phone);
    }

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

    public String getOrgSupp() {
        return orgSupp;
    }

    public void setOrgSupp(String orgSupp) {
        this.orgSupp = orgSupp;
    }
}

