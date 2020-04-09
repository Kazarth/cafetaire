package View;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import javax.swing.*;

import static javafx.application.Application.launch;


/**
 * The class proves a table model to be modified for specific uses.
 * @author Paul Moustakas
 * @version 1.0
 */
public class TableModel  {

    private TableView table = new TableView();


    public TableModel () {
        tableModel();
    }

    public void tableModel () {

        table.setEditable(true);

        TableColumn supplierCol = new TableColumn("Supplier");
        TableColumn lastNameCol = new TableColumn("Category");
        TableColumn emailCol = new TableColumn("Email");
        TableColumn phoneCol = new TableColumn("Email");

        table.getColumns().addAll(supplierCol, lastNameCol, emailCol, phoneCol);

        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(table);

    }
}