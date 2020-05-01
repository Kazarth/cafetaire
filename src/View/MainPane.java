package View;

import Control.Callback;
import Entities.Views;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private Pane[] views;
    private StackPane menu;
    private Callback callback;

    private int pane;

    public MainPane(Callback callback) {
        this.callback = callback;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        menu = new MenuPane(this);
        views = new Pane[5];
        views[0] = new Dashboard();
        views[1] = new IngredientsPane(callback);
        views[2] = new ProductsPane(callback);
        views[3] = new SupplierPane(callback);
        views[4] = new SchedulePane(callback);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(menu, views[0]);
        getChildren().add(hBox);
    }

    void setView(Views view) {
        pane = -1;

        for (int i=0; i<Views.values().length; i++) {
            if (Views.values()[i] == view) {
                pane = i;
                break;
            }
        }

        ((HBox)getChildren().get(0)).getChildren().set(1, views[pane]);
    }

    Pane[] getViews() {
        return views;
    }

    int getActiveView() {
        return pane;
    }
}
