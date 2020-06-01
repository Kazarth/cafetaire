package code.entities;

import code.control.Callback;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * TODO: desc
 * TODO: utilize; fix listeners.
 * @author Tor Stenfeldt, Lucas Eliasson
 * @version 1.0
 */
public class Calendar extends StackPane {
    private Text[][] values;
    private HBox dayBox;
    private VBox timeBox;

    public Calendar(Callback callback) {
        values = new Text[7][12];

        initHours();
        initDays();

        for (int i=0; i<values.length; i++) {
            for (int j=0; j<values[i].length; j++) {
                values[i][j] = new Text();
                values[i][j].setWrappingWidth(130);
                // TODO: set style
            }
        }

        /* TODO: fix callback
        deliveries.addActionListener((listener) -> {
            Delivery d = deliveries.get(deliveries.size());
            setValue(d.getDay(), d.getHour(), d.toString());
        });
        */

        addComponents();
    }

    private void addComponents() {
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
                textBoxes[j].setPrefSize(130, 43);
            }

            vBoxes[i] = new VBox(textBoxes);
            vBoxes[i].setPrefSize(130, 522);
        }

        HBox hBox = new HBox(vBoxes);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(dayBox, hBox);
        vBox.setAlignment(Pos.CENTER);

        HBox mainBox = new HBox(timeBox, vBox);
        getChildren().add(mainBox);
    }

    private void initDays() {
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
            l.setPrefSize(130, 43);
            l.setAlignment(Pos.CENTER);
        }

        this.dayBox = new HBox(days);
        this.dayBox.setStyle("-fx-background-color: #FFF;");
        this.dayBox.setPrefSize(916, 43);
    }

    private void initHours() {
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
            l.setMinSize(50, 43);
            l.setPrefSize(50, 43);
            l.setAlignment(Pos.CENTER_LEFT);
        }

        timeBox = new VBox(time);
        timeBox.setStyle(
                "-fx-background-color: #FFF;" +
                "-fx-padding: 28 0 0 0");
        timeBox.setPrefSize(50, 522);
    }

    public String getDate(int x, int y) {
        return values[x][y].getText();
    }

    public void setValue(int x, int y, String s) {
        values[x][y].setText(s);
    }
}
