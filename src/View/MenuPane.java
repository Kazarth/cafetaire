package View;

import Entities.Styles;
import Entities.Views;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * MenuBar.java
 * The main menu bar used for switching between panes.
 * @author Lucas Eliasson, Tor Stenfeldt
 * @version 4.0
 */
public class MenuPane extends StackPane {
    private MainPane mainPane;
    private Button[] buttons;
    private Button selectedButton;

    private Button contractBtn;
    private Button expandBtn;
    private HBox toggleContainer;
    private boolean toggled;
    private int toggleCount = 0;

    public MenuPane(MainPane mainPane) {
        this.mainPane = mainPane;
        buttons = new Button[Views.values().length];

        for (int i=0; i<buttons.length; i++) {
            buttons[i] = initButton(Views.values()[i]);
        }

        selectedButton = buttons[0];
        selectedButton.setStyle(Styles.getMenuButtonHighlighted());

        VBox mainContainer = new VBox();

        Label title = new Label("Cafetairé");
        title.getStylesheets().add("styles.css");
        title.getStyleClass().add("title");

        HBox titleContainer = new HBox();
        titleContainer.setPadding(new Insets(0, 0, 0, 28));
        titleContainer.getChildren().add(title);

        mainContainer.getChildren().add(titleContainer);

        for (Button b: buttons) {
            mainContainer.getChildren().add(b);
        }

        /*
        toggleButton = new ToggleButton("ToggleButton");
        toggleButton.setPrefSize(130, 50);
        toggleButton.getStylesheets().add("LeftMenuBar.css");
        toggleButton.getStyleClass().add("toggleButton");
        toggleButton.setOnAction(e -> toggleMenu(mainPane));
        */

        contractBtn = new Button("Toggle Button");
        contractBtn.setPrefSize(130, 50);
        contractBtn.getStylesheets().add("LeftMenuBar.css");
        contractBtn.getStyleClass().add("toggleButton");
        contractBtn.setOnAction(e -> contract(mainPane));

        expandBtn = new Button("Toggle");
        expandBtn.setPrefSize(65, 50);
        expandBtn.getStylesheets().add("LeftMenuBar.css");
        expandBtn.getStyleClass().add("toggleButton");
        expandBtn.setOnAction(e -> expand(mainPane));

        toggleContainer = new HBox();
        toggleContainer.setPadding(new Insets(0, 0, 0, 75));
        toggleContainer.getChildren().add(contractBtn);

        mainContainer.getChildren().add(toggleContainer);

        setStyle("-fx-background-color: #21252B;");
        getChildren().add(mainContainer);
        setPrefSize(280, 768);
    }

    private void contract(MainPane pane) {
        Pane activePane = getActiveView(pane);

        setPrefSize(20,768);
        for (int i=0; i<Views.values().length; i++) {
            buttons[i].setText(""+Views.values()[i].name().charAt(0));
        }
        toggleContainer.getChildren().add(expandBtn);
        toggleContainer.getChildren().remove(contractBtn);
        toggleContainer.setPadding(new Insets(0, 0, 0, 30));
    }

    private void expand(MainPane pane) {
        setPrefSize(280,768);
        for (int i=0; i<Views.values().length; i++) {
            buttons[i].setText(Views.values()[i].name());
        }
        toggleContainer.getChildren().add(contractBtn);
        toggleContainer.getChildren().remove(expandBtn);
        toggleContainer.setPadding(new Insets(0, 0, 0, 75));
    }

    private Pane getActiveView(MainPane pane) {
        int activePane = pane.getActiveView();
        Pane[] views = pane.getViews();

        if (views[activePane] instanceof Dashboard) {
            System.out.println("Dashboard");
            return views[activePane];
        } else if (views[activePane] instanceof IngredientsPane) {
            System.out.println("Ingredients");
            return views[activePane];
        } else if (views[activePane] instanceof ProductsPane) {
            System.out.println("Products");
            return views[activePane];
        } else if (views[activePane] instanceof SupplierPane) {
            System.out.println("Supplier");
            return views[activePane];
        } else if (views[activePane] instanceof SchedulePane) {
            System.out.println("Schedule");
            return views[activePane];
        }
        return null;
    }

    private Button initButton(Views view) {
        Button newButton = new Button(view.name());
        newButton.setPrefSize(280, 100);

        newButton.setStyle(Styles.getMenuButtonStandard());

        newButton.setOnMouseClicked((handler) -> {
            for (Button b: buttons) {
                b.setStyle(Styles.getMenuButtonStandard());
            }

            mainPane.setView(view);
            newButton.setStyle(Styles.getMenuButtonHighlighted());
            selectedButton = newButton;
        });

        newButton.setOnMouseEntered((handler) -> newButton.setStyle(Styles.getMenuButtonHighlighted()));

        newButton.setOnMouseExited((handler) -> {
            newButton.setStyle(Styles.getMenuButtonStandard());
            selectedButton.setStyle(Styles.getMenuButtonHighlighted());
        });

        return newButton;
    }
}
