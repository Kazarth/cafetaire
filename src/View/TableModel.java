package View;

import Control.Callback;
import Entities.Styles;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * The class proves a table model to be modified for specific uses.
 * @author Tor Stenfeldt, Lucas Eliasson, Paul Moustakas,
 * @version 2.0
 */

public class TableModel extends StackPane {
    Text[][] values;

    public TableModel(Callback callback) {
        values = new Text[4][12];



        Label[] rowHeaders = new Label[]{
//                new Label("00:00")
        };

        for (Label l : rowHeaders) {
            l.setStyle(Styles.getTime());
            l.setPrefSize(50, 47);
            l.setAlignment(Pos.CENTER_LEFT);
        }

        VBox rowBox = new VBox(rowHeaders);
        rowBox.setStyle("-fx-background-color: #FFF;" + "-fx-padding: 28 0 0 0");
        rowBox.setPrefSize(50, 564);

        Label[] columns = new Label[]{
                new Label("Supplier"),
                new Label("Category"),
                new Label("Email"),
                new Label("Phone"),
        };

        for (Label l : columns) {
            l.setStyle(Styles.getColHeader());
            l.setPrefSize(236, 47);
            l.setAlignment(Pos.CENTER);
        }

        HBox columnBox = new HBox(columns);
        columnBox.setStyle("-fx-background-color: #21252B;");
        columnBox.setPrefSize(945, 47);

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                values[i][j] = new Text();
                // TODO: set style
            }
        }

        /* TODO: fix callback
        deliveries.addActionListener((listener) -> {
            Delivery d = deliveries.get(deliveries.size());
            setValue(d.getDay(), d.getHour(), d.toString());
        });
        */

        VBox[] vBoxes = new VBox[4];

        for (int i = 0; i < 4; i++) {
            VBox[] textBoxes = new VBox[12];

            for (int j = 0; j < 12; j++) {
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
            vBoxes[i].setPrefSize(1000, 564);
        }



        HBox hBox = new HBox(vBoxes);
        hBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(columnBox, hBox);
        vBox.setAlignment(Pos.CENTER);

        HBox mainBox = new HBox(rowBox, vBox);
        getChildren().add(mainBox);
    }

    public String getDate(int x, int y) {
        return values[x][y].getText();
    }

    public void setValue(int x, int y, String s) {
        values[x][y].setText(s);
    }
}