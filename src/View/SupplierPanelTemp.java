package View;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class SupplierPanelTemp extends StackPane {
    public SupplierPanelTemp() {
        String textStyle =
                "-fx-text-fill: #619F81;" +
                "-fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 36;"
        ;

        Label label = new Label("Suppliers");
        label.setStyle(textStyle);

        setStyle("-fx-background-color: #6B6C6A;");
        getChildren().add(label);
        setPrefSize(1086, 768);
    }

}
