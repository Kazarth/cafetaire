package code.view.panes;

import code.control.Callback;
import code.entities.Calendar;
import code.entities.Styles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * A class for showing a schedule of incoming deliveries etc.
 * @author Tor Stenfeldt, Lucas Eliasson
 * @version 3.0
 */
public class CalendarPane extends Pane implements EnhancedPane {
    public CalendarPane(Callback callback) {
        Label title = new Label("CALENDAR");
        title.setStyle(Styles.getBoxTitle());

        HBox titleBox = new HBox();
        titleBox.setStyle(
                "-fx-alignment: top_center;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 0 0;" +
                ""
        );
        titleBox.setPrefSize(1014, 100);
        titleBox.getChildren().add(title);

        HBox hBox_filler = new HBox();
        hBox_filler.setPrefSize(1014, 40);
        hBox_filler.setStyle(
                "-fx-border-color: #6B6C6A;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-width: 1 0 1 0"
        );

        Calendar calendar = new Calendar(callback);
        HBox tableBox = new HBox();
        tableBox.setStyle(
                "-fx-alignment: center;" +
                "-fx-background-color: #EEE;" +
                ""
        );
        tableBox.setPrefSize(1014, 522);
        tableBox.getChildren().add(calendar);

        VBox boxContainer = new VBox();
        boxContainer.setAlignment(Pos.CENTER);
        boxContainer.getChildren().addAll(titleBox, tableBox);

        HBox mainBox = new HBox();
        mainBox.setStyle(
                "-fx-alignment: center;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 20 20;" +
                "-fx-padding: 25;"
        );
        mainBox.setPrefSize(1014, 695);
        mainBox.setLayoutX(20);
        mainBox.setLayoutY(20);
        mainBox.getChildren().add(boxContainer);

        setStyle(Styles.getPane());
        setPrefSize(1054, 736);
        getChildren().add(mainBox);

        testPopulate(calendar);
    }

    public void expand() {
        setPrefWidth(1200);
    }

    public void contract() {
        setPrefWidth(1054);
    }

    public void refresh() {
        // TODO: refresh anything?
    }

    private void testPopulate(Calendar calendar) {
        calendar.setValue(1, 4, "Wheat from Baerte Kvarn");
        calendar.setValue(1, 5, "Sugar from Socker Arne AB");
        calendar.setValue(4, 3, "Beverages from Krönelein");
    }
}
