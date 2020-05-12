package code.view.panes;

import code.control.Callback;
import code.entities.Calendar;
import code.entities.Styles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * A class for showing a schedule of incoming deliveries etc.
 * @author Tor Stenfeldt, Lucas Eliasson
 * @version 3.0
 */
public class SchedulePane extends StackPane {
    public SchedulePane(Callback callback) {
        Label title = new Label("CALENDAR");
        title.setStyle(Styles.getBoxTitle());

        HBox titleBox = new HBox();
        titleBox.setStyle(
                "-fx-alignment: top_center;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 0 0;" +
                ""
        );
        titleBox.setPrefSize(1036, 100);
        titleBox.getChildren().add(title);

        HBox tableBox = new HBox();
        tableBox.setStyle("-fx-alignment: center;" +
                "-fx-background-color: #EEE;" +
                ""
        );
        tableBox.setPrefSize(1036, 611);
        Calendar calendar = new Calendar(callback);
        tableBox.getChildren().add(calendar);

        VBox boxContainer = new VBox();
        boxContainer.setAlignment(Pos.CENTER);
        boxContainer.getChildren().addAll(titleBox, tableBox);

        HBox mainBox = new HBox();
        mainBox.setStyle("-fx-alignment: center;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 20 20;" +
                "-fx-padding: 25;" +
                ""
        );
        mainBox.setPrefSize(1036, 718);
        mainBox.getChildren().add(boxContainer);

        setStyle(Styles.getPane());
        setAlignment(Pos.CENTER);
        setPrefSize(1086, 768);
        getChildren().add(mainBox);
        setPadding(new Insets(25));

        calendar.setValue(3, 4, "Coke from the Company");
    }

    public void expand() {
        setPrefWidth(1346);
        System.out.println("Expanding");
    }

    public void contract() {
        setPrefWidth(1086);
        System.out.println("Contracting");
    }
}
