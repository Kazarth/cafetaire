package Entities;

import Control.Callback;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * TODO: desc
 * @author Tor Stenfeldt, Lucas Eliasson
 * @version 1.0
 */
public class Calendar extends StackPane {
    Text[][] values;

    public Calendar(Callback callback) {
        values = new Text[7][12];

        Label[] time = new Label[] {
                new Label("00:00"),
                new Label("02:00"),
                new Label("04:00"),
                new Label("06:00"),
                new Label("08:00"),
                new Label("10:00"),
                new Label("12:00"),
                new Label("14:00"),
                new Label("16:00"),
                new Label("18:00"),
                new Label("20:00"),
                new Label("22:00")
        };

        for (Label l : time) {
            l.setStyle(Styles.getTime());
            l.setPrefSize(50, 47);
            l.setAlignment(Pos.CENTER_LEFT);
        }

        VBox timeBox = new VBox(time);
        timeBox.setStyle(
                "-fx-background-color: #FFF;" +
                "-fx-padding: 28 0 0 0");
        timeBox.setPrefSize(50, 564);

        Label[] days = new Label[] {
                new Label("Monday"),
                new Label("Tuesday"),
                new Label("Wednesday"),
                new Label("Thursday"),
                new Label("Friday"),
                new Label("Saturday"),
                new Label("Sunday")
        };

        for (Label l: days) {
            l.setStyle(Styles.getTime());
            l.setPrefSize(135, 47);
            l.setAlignment(Pos.CENTER);
        }

        HBox dayBox = new HBox(days);
        dayBox.setStyle("-fx-background-color: #FFF;");
        dayBox.setPrefSize(945, 47);

        for (int i=0; i<values.length; i++) {
            for (int j=0; j<values[i].length; j++) {
                values[i][j] = new Text();
                values[i][j].setStyle(Styles.getTableContent()); // TODO: fix style
            }
        }

        /* TODO: fix callback
        deliveries.addActionListener((listener) -> {
            Delivery d = deliveries.get(deliveries.size());
            setValue(d.getDay(), d.getHour(), d.toString());
        });
        */

        VBox[] vBoxes = new VBox[7];

        for (int i=0; i<7; i++) {
            VBox[] textBoxes = new VBox[12];

            for (int j=0; j<12; j++) {
                textBoxes[j] = new VBox(values[i][j]);
                textBoxes[j].setStyle("-fx-border: 2;" +
                        "-fx-background-color: #FFF;" +
                        "-fx-border-color: #EEE;" +
                        "" +
                        ""
                );
                textBoxes[j].setPrefSize(135, 47);
            }

            vBoxes[i] = new VBox(textBoxes);
            vBoxes[i].setPrefSize(135, 564);
        }

        HBox hBox = new HBox(vBoxes);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(dayBox, hBox);
        vBox.setAlignment(Pos.CENTER);

        HBox mainBox = new HBox(timeBox, vBox);
        getChildren().add(mainBox);
    }

    public String getDate(int x, int y) {
        return values[x][y].getText();
    }

    /**
     *
     * @param day the day of the delivery. Assume the array starts at 1.
     * @param hour the hour of the delivery. Each increment is two hours.
     * @param details
     */
    public void setValue(int day, int hour, String details) {
        values[day-1][hour].setText(details);
    }
}
