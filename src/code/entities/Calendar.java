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
    private HBox xAxisValues;

    public Calendar(Callback callback) {
        initValues();
        initDays();
        HBox valueContainer = initValueContainers();

        VBox vBox = new VBox(this.xAxisValues, valueContainer);
        vBox.setAlignment(Pos.CENTER);

        VBox yAxisValues = initHours();
        HBox mainBox = new HBox(yAxisValues, vBox);

        getChildren().add(mainBox);
    }

    private void initValues() {
        this.values = new Text[7][12];

        for (int i=0; i<this.values.length; i++) {
            for (int j=0; j<this.values[i].length; j++) {
                this.values[i][j] = new Text();
                this.values[i][j].setWrappingWidth(130);
                // TODO: set style
            }
        }

        /* TODO: fix callback
        deliveries.addActionListener((listener) -> {
            Delivery d = deliveries.get(deliveries.size());
            setValue(d.getDay(), d.getHour(), d.toString());
        });
        */
    }

    private void initDays() {
        Label[] dayTitles = new Label[]{
                new Label("Monday"),
                new Label("Tuesday"),
                new Label("Wednesday"),
                new Label("Thursday"),
                new Label("Friday"),
                new Label("Saturday"),
                new Label("Sunday")
        };

        for (Label l: dayTitles) {
            l.setStyle(Styles.getTime());
            l.setPrefSize(130, 43);
            l.setAlignment(Pos.CENTER);
        }

        HBox[] titleContainers = new HBox[dayTitles.length];

        for (int i = 0; i< dayTitles.length; i++) {
            titleContainers[i] = new HBox(dayTitles[i]);
            titleContainers[i].setAlignment(Pos.CENTER);
            titleContainers[i].setPrefSize(155, 43);
        }

        this.xAxisValues = new HBox(titleContainers);
        this.xAxisValues.setStyle("-fx-background-color: #FFF;");
        this.xAxisValues.setAlignment(Pos.CENTER_LEFT);
        this.xAxisValues.setPrefSize(916, 43);
    }

    private VBox initHours() {
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

        VBox yAxisValues = new VBox(time);
        yAxisValues.setStyle(
                "-fx-background-color: #FFF;" +
                "-fx-padding: 45 0 0 0"
        );
        yAxisValues.setPrefSize(50, 522);

        return yAxisValues;
    }

    private HBox initValueContainers() {
        VBox[] dayContainers = new VBox[7];

        for (int i=0; i<7; i++) {
            VBox[] hourContainers = new VBox[12];

            for (int j=0; j<12; j++) {
                hourContainers[j] = new VBox(this.values[i][j]);
                hourContainers[j].setStyle(
                        "-fx-border: 2;" +
                        "-fx-background-color: #FFF;" +
                        "-fx-border-color: #EEE;"
                );
                hourContainers[j].setPrefSize(130, 43);
            }

            dayContainers[i] = new VBox(hourContainers);
            dayContainers[i].setPrefSize(130, 522);
        }

        HBox values = new HBox(dayContainers);
        values.setAlignment(Pos.CENTER);
        return values;
    }

    private void resizeText(int value) {
        for (Text[] texts: this.values) {
            for (Text text: texts) {
                text.setWrappingWidth(value);
            }
        }
    }

    private void resizeTitles(int value) {
        this.xAxisValues.setPrefWidth(value);
    }

    public void expand() {
        resizeTitles(1062);
        resizeText(150);
    }

    public void contract() {
        resizeText(130);
        resizeTitles(916);
    }

    public String getDate(int x, int y) {
        return this.values[x][y].getText();
    }

    public void setValue(int x, int y, String s) {
        this.values[x][y].setText(s);
    }
}
