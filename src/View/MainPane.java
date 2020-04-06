package View;

import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javax.swing.*;

/**
 * TODO: move setView etc. to Controller?
 * MainPane.java
 * Contains the different panes, making up the application.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class MainPane extends StackPane {
    private StackPane[] views;
    private StackPane menu;
    private StackPane view;

    public MainPane() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        menu = new MenuPane(this);
        views = new StackPane[5];
        views[0] = new Dashboard();
        view = views[0];

        HBox hBox = new HBox();
        hBox.getChildren().addAll(menu, view);
        getChildren().add(hBox);
    }

    void setView(Views view) {
        switch (view.name()) {
            case "Dashboard":
                this.view = views[0];
                break;
            case "Ingredients":
                this.view = views[1];
                break;
            case "Perishables":
                this.view = views[2];
                break;
            case "Suppliers":
                this.view = views[3];
                break;
            case "Schedule":
                this.view = views[4];
                break;
            default:
                break;
        }
    }
}
