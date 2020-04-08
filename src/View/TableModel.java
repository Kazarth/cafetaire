package View;

import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


/**
 * The class proves a table model to be modified for specific uses.
 * @author Paul Moustakas
 * @version 1.0
 */

public class TableModel extends StackPane {

    private TableView table = new TableView();


    public TableModel () {
        Scene sceneTable =   new Scene( null, 800, 600);
    }

    public void setupTable () {
        table.setEditable(true);

        TableColumn supplierNameCol = new TableColumn("Name");
        TableColumn categoryCol = new TableColumn("Category");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn phoneCol = new TableColumn("Phone");



        ////FIXA

        /*
        table.getColumns().addAll(supplierNameCol, categoryCol, emailCol, phoneCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);

        ((Group) scene.getRoot()).getChildren().addAll(vbox);

        stage.setScene(scene);
        stage.show();

         */

    }
}
