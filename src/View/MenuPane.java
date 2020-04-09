package View;

import Entities.Styles;
import Entities.Views;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
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
    private ToggleButton toggleButton;
    private Button selectedButton;

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

        toggleButton = new ToggleButton("ToggleButton");
        toggleButton.setPrefSize(130, 50);
        toggleButton.getStylesheets().add("LeftMenuBar.css");
        toggleButton.getStyleClass().add("toggleButton");

        HBox toggleContainer = new HBox();
        toggleContainer.setPadding(new Insets(0, 0, 0, 75));
        toggleContainer.getChildren().add(toggleButton);

        mainContainer.getChildren().add(toggleContainer);

        setStyle("-fx-background-color: #21252B;");
        getChildren().add(mainContainer);
        setPrefSize(280, 768);
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
