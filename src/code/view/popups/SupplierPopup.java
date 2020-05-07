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
    private JFrame frame;

    private Label title;
    private Label nameLbl;
    private Label categoryLbl;
    private Label emailLbl;
    private Label phoneLbl;

    private TextField nameField;
    private ComboBox<String> categoryBox;
    private TextField emailField;
    private TextField phoneField;

    private Button addButton, cancelButton;

    private SupplierPane source;
    private Callback callback;

    private int opener;
    private String orgSupp;


    public SupplierPopup(SupplierPane source, Callback callback, int opener) {
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
        categoryBox.setStyle(Styles.getPopField() + Styles.getTableRowSelected());
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
        // New supplier
        if (opener == 0) {
            addButton.setText("ADD NEW SUPPLIER");
            addButton.setOnAction(e -> {
                addAction();
            });
        }
        // Edit supplier
        if (opener == 1){
            addButton.setText("SAVE SUPPLIER");
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
                emailLbl, emailField,
                phoneLbl,phoneField,
                addButton, cancelButton);
    }

    /**
     * Action performed Add-button
     */
    public void addAction() {
        Supplier supp;

        String supplierName = nameField.getText();
        String category     = categoryBox.getSelectionModel().getSelectedItem();
        String email        = emailField.getText();
        String phone        = phoneField.getText();

        supp = new Supplier(supplierName, category, email, phone);

        System.out.println(supp.toString());

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
        ArrayList<Supplier> receivedSuppliers = callback.getSuppliers();

        for (Supplier supplier: receivedSuppliers) {
            listSuppliers.add(supplier.getName());
        }
        return listSuppliers;
    }

     */

    /**
     * Method used to edit and item in the tableView in SupplierPane and database
     * if name remains unchanged set values from the other fields to supplier
     * if name is changed remove old name from database and add the new one
     */
    public void editAction(){
        String name = nameField.getText();
        String category = categoryBox.getValue();
        String email = emailField.getText();
        String phone = phoneField.getText();

        if (orgSupp.equals(name)){
            Supplier supplier = callback.getSupplier(nameField.getText());
            supplier.setCategory(category);
            supplier.setEmail(email);
            supplier.setPhone(phone);
            source.refresh();
            close();
        } else if (!orgSupp.equals(name)){
            Supplier supplier = new Supplier(name, category, email, phone);
            callback.addSupplier(supplier);
            callback.removeSupplier(orgSupp);
            source.addNewSupplier(supplier);
            source.removeSupplier();
            source.refresh();
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
    public void setValuesForSupplier(String name, String category, String email, String phone){
        nameField.setText(name);
        categoryBox.getSelectionModel().select(category);
        emailField.setText(email);
        phoneField.setText(phone);
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

