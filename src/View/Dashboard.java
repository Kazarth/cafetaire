package View;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import javax.swing.*;

public class Dashboard extends StackPane {


    public Dashboard() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        Label ingredients = new Label("Ingredients");
        ingredients.setStyle("-fx-text-fill: #619F81; -fx-font-family: Segoe UI; -fx-font-weight: bold; -fx-font-size: 36;");

        HBox topLeft = new HBox(10);
        topLeft.setPrefSize(670, 330);
        topLeft.setStyle("-fx-alignment: top_center; -fx-background-color: #FFFFFF; -fx-background-radius: 20 20 20 20;");
        topLeft.getChildren().add(ingredients);

        Label deliveries = new Label("Deliveries");
        deliveries.setStyle("-fx-text-fill: #619F81; -fx-font-family: Segoe UI; -fx-font-weight: bold; -fx-font-size: 36;");

        HBox topRight = new HBox(10);
        topRight.setPrefSize(330, 330);
        topRight.setStyle("-fx-alignment: top_center; -fx-background-color: #FFFFFF; -fx-background-radius: 20 20 20 20;");
        topRight.getChildren().add(deliveries);

        HBox topBoxes = new HBox(25);
        topBoxes.setAlignment(Pos.CENTER);
        topBoxes.getChildren().addAll(topLeft, topRight);

        Label food = new Label("Food");
        food.setStyle("-fx-text-fill: #619F81; -fx-font-family: Segoe UI; -fx-font-weight: bold; -fx-font-size: 36;");

        HBox bottomLeft = new HBox(10);
        bottomLeft.setPrefSize(670, 330);
        bottomLeft.setStyle("-fx-alignment: top_center; -fx-background-color: #FFFFFF; -fx-background-radius: 20 20 20 20;");
        bottomLeft.getChildren().add(food);

        HBox bottomRight = new HBox(10);
        bottomRight.setPrefSize(330, 330);
        bottomRight.setStyle("-fx-alignment: top_center; -fx-background-color: #FFFFFF; -fx-padding: 10; -fx-background-radius: 20 20 20 20;");

        HBox bottomBoxes = new HBox(25);
        bottomBoxes.setAlignment(Pos.CENTER);
        bottomBoxes.getChildren().addAll(bottomLeft, bottomRight);

        VBox boxes = new VBox(25);
        boxes.setAlignment(Pos.CENTER);
        boxes.getChildren().addAll(topBoxes, bottomBoxes);

        setStyle("-fx-background-color: #6B6C6A;");
        getChildren().add(boxes);
    }
}
