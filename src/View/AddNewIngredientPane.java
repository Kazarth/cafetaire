package View;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.swing.*;

public class AddNewIngredientPane extends StackPane {
    private TextField nameField;
    private TextField categoryField; // Change to Viktors drop down
    private TextField stockField;
    private TextField supplierField; // Change to dropdown + enter text?

    public AddNewIngredientPane() {
        VBox container = new VBox();
        container.setPrefSize(600,500);

        HBox nameBox = new HBox();
        HBox categoryBox = new HBox();
        HBox stockBox = new HBox();
        HBox supplierBox = new HBox();
        HBox buttonBox = new HBox();

        Label nameLbl = new Label("Name");
        nameLbl.setPrefSize(100, 40);
        Label categoryLbl = new Label("Category");
        categoryLbl.setPrefSize(100, 40);
        Label stockLbl = new Label("Stock");
        stockLbl.setPrefSize(100, 40);
        Label supplierLbl = new Label("Supplier");
        supplierLbl.setPrefSize(100, 40);

        nameField = new TextField();
        nameField.setPromptText("Enter name");
        nameField.setPrefSize(200,40);
        categoryField = new TextField(); // change?
        categoryField.setPromptText("Enter category"); // change?
        categoryField.setPrefSize(200,40);
        stockField = new TextField();
        stockField.setPromptText("Enter stock");
        stockField.setPrefSize(200,40);
        supplierField = new TextField(); // change?
        supplierField.setPromptText("Enter supplier"); // change?
        supplierField.setPrefSize(200,40);

        Button btn1 = new Button("OK");
        btn1.setPrefSize(200, 40);
        Button btn2 = new Button("CANCEL");
        btn2.setPrefSize(200, 40);

        this.setPrefSize(600,500);

        nameBox.getChildren().addAll(nameLbl, nameField);
        categoryBox.getChildren().addAll(categoryLbl, categoryField);
        stockBox.getChildren().addAll(stockLbl, stockField);
        supplierBox.getChildren().addAll(supplierLbl, supplierField);
        buttonBox.getChildren().addAll(btn1, btn2);

        container.getChildren().addAll(nameBox, categoryBox, stockBox, supplierBox, buttonBox);

        getChildren().add(container);
    }

    /**
     * For testing purposes
     * @param args
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("FX");
        final JFXPanel fxPanel = new JFXPanel();
        frame.setTitle("Add new Ingredient");
        frame.add(fxPanel);
        frame.setBounds(0, 0, 600, 500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        frame.setResizable(false);
        frame.setVisible(true);

        //Controller controller = new Controller();

        Platform.runLater(() -> initFX(fxPanel));
    }

    /**
     * Part of testing
     * @param fxPanel
     */
    private static void initFX(JFXPanel fxPanel) {
        fxPanel.setScene(new Scene(new AddNewIngredientPane()));
    }
}
