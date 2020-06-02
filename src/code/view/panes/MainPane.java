package code.view.panes;

import code.control.Callback;
import code.entities.Views;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javax.swing.*;

/**
 * MainPane.java
 * Contains the different panes, making up the application.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class MainPane extends StackPane {
    private EnhancedPane[] views;
    private int pane;
    private MenuPane menuPane;

    public MainPane(Callback callback) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        menuPane = new MenuPane(this);
        views = new EnhancedPane[6];
        views[0] = new DashboardPane(callback);
        views[1] = new IngredientsPane(callback);
        views[2] = new ProductsPane(callback);
        views[3] = new SupplierPane(callback);
        views[4] = new RecipePane(callback);
        views[5] = new CalendarPane(callback);

        HBox hBox = new HBox();
        hBox.getChildren().addAll(menuPane, ((Node)views[0]));
        getChildren().add(hBox);
        setPrefSize(1334, 736);
    }

    void setView(Views view) {
        pane = -1;

        for (int i=0; i<Views.values().length; i++) {
            if (Views.values()[i] == view) {
                pane = i;
                break;
            }
        }

        views[pane].refresh();
        ((HBox)getChildren().get(0)).getChildren().set(1, ((Node)views[pane]));
    }

    public boolean getExpanded() {
        return menuPane.getExpanded();
    }

    EnhancedPane[] getViews() {
        return views;
    }

    int getActiveView() {
        return pane;
    }
}
