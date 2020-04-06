package View;

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

    public MenuPane(MainPane mainPane) {
        this.mainPane = mainPane;
        buttons = new Button[Views.values().length];

        for (int i=0; i<buttons.length; i++) {
            buttons[i] = initButton(Views.values()[i]);
        }

        VBox mainContainer = new VBox();

        Label title = new Label("Cafetairé");
        title.setStyle("-fx-text-fill: #619F81; -fx-font-family: Segoe UI; -fx-font-weight: bold; -fx-font-size: 32");

        HBox titleContainer = new HBox();
        titleContainer.setPadding(new Insets(0, 0, 0, 28));
        titleContainer.getChildren().add(title);

        mainContainer.getChildren().add(titleContainer);

        for (Button b: buttons) {
            mainContainer.getChildren().add(b);
        }

        toggleButton = new ToggleButton("ToggleButton");
        toggleButton.setPrefSize(130, 50);
        toggleButton.getStyleClass().add("toggleButton");
        toggleButton.getStylesheets().add("LeftMenuBar.css");

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
        newButton.setLayoutY(97);
        newButton.setStyle("-fx-text-fill: #619F81;");
        newButton.getStyleClass().add("navButtons");
        newButton.getStylesheets().add("LeftMenuBar.css");
        newButton.setOnAction((handler) -> {
            // TODO: set active
            mainPane.setView(view);
        });
        return newButton;
    }
}
