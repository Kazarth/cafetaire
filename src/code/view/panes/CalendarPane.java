package code.view.panes;

import code.control.Callback;
import code.entities.Calendar;
import code.entities.Styles;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 * A class for showing a schedule of incoming deliveries etc.
 * @author Tor Stenfeldt, Lucas Eliasson
 * @version 3.0
 */
public class CalendarPane extends Pane implements EnhancedPane {
    private Calendar calendar;
    private FlowPane calendarPane;
    private VBox mainContainer;

    public CalendarPane(Callback callback) {
        Text title = new Text("CALENDAR");
        title.setStyle(Styles.getTitle());
        title.setFill(Paint.valueOf("#619f81"));

        HBox titleContainer = new HBox(title);
        titleContainer.setPrefWidth(1014);
        titleContainer.setMinHeight(70);
        titleContainer.setMaxHeight(70);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setStyle(
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 0 0"
        );

        HBox filler = new HBox();
        filler.setPrefWidth(1014);
        filler.setMinHeight(35);
        filler.setMaxHeight(35);
        filler.setStyle(
                "-fx-border-color: #6B6C6A;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-border-width: 1 0 1 0"
        );

        this.calendar = new Calendar(callback);
        this.calendarPane = new FlowPane();
        this.calendarPane.setPrefSize(1014, 590);
        this.calendarPane.setAlignment(Pos.CENTER);
        this.calendarPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.calendarPane.setStyle(
                "-fx-alignment: center;" +
                " -fx-background-color: #fff;" +
                " -fx-background-radius: 0 0 20 20;"
        );
        this.calendarPane.getChildren().add(this.calendar);

        this.mainContainer = new VBox();
        this.mainContainer.setPrefSize(1014, 695);
        this.mainContainer.setMaxHeight(695);
        this.mainContainer.setStyle(Styles.getPane());
        this.mainContainer.setLayoutX(20);
        this.mainContainer.setLayoutY(20);
        this.mainContainer.getChildren().addAll(titleContainer, filler, this.calendarPane);

        setStyle(Styles.getPane());
        setPrefSize(1054, 736);
        getChildren().add(this.mainContainer);

        testPopulate();
    }

    public void expand() {
        setPrefWidth(1200);

        mainContainer.setPrefWidth(1160);
        calendarPane.setPrefWidth(1160);
        calendar.expand();
    }

    public void contract() {
        setPrefWidth(1054);

        calendar.contract();
        calendarPane.setPrefWidth(1014);
        mainContainer.setPrefWidth(1014);
    }

    public void refresh() {
        // TODO: refresh anything?
    }

    private void testPopulate() {
        this.calendar.setValue(1, 4, "Wheat from Baerte Kvarn");
        this.calendar.setValue(1, 5, "Sugar from Socker Arne AB");
        this.calendar.setValue(4, 3, "Beverages from Krönelein");
    }
}
