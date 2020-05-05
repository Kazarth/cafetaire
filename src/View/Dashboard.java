package View;

import Entities.Styles;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

/**
 * Dashboard.java
 * The 'main' pane which the application will launch to.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class Dashboard extends StackPane {
    public Dashboard() {
        Label ingredients = new Label("Ingredients");
        ingredients.setStyle(Styles.getBoxTitle());

        HBox topLeft = new HBox(10);
        topLeft.setPrefSize(670, 330);
        topLeft.setStyle(Styles.getDashboardBox());
        topLeft.getChildren().add(ingredients);

        Label deliveries = new Label("Deliveries");
        deliveries.setStyle(Styles.getBoxTitle());

        HBox topRight = new HBox(10);
        topRight.setPrefSize(330, 330);
        topRight.setStyle(Styles.getDashboardBox());
        topRight.getChildren().add(deliveries);

        HBox topBoxes = new HBox(25);
        topBoxes.setAlignment(Pos.CENTER);
        topBoxes.getChildren().addAll(topLeft, topRight);

        Label food = new Label("Food");
        food.setStyle(Styles.getBoxTitle());

        HBox bottomLeft = new HBox(10);
        bottomLeft.setPrefSize(670, 330);
        bottomLeft.setStyle(Styles.getDashboardBox());
        bottomLeft.getChildren().add(food);

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
}
