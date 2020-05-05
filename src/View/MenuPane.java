package View;

import Entities.Styles;
import Entities.Views;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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

    private HBox toggleContainer;
    private boolean expanded;

    public MenuPane(MainPane mainPane) {
        this.mainPane = mainPane;
        buttons = new Button[Views.values().length];
        this.expanded = true;

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

        Button toggleButton = new Button("Toggle Button");
        toggleButton.setPrefSize(130, 50);
        toggleButton.getStylesheets().add("LeftMenuBar.css");
        toggleButton.getStyleClass().add("toggleButton");
        toggleButton.setOnAction(e -> toggle(toggleButton));

        toggleContainer = new HBox();
        toggleContainer.setPadding(new Insets(0, 0, 0, 75));
        toggleContainer.getChildren().add(toggleButton);

        mainContainer.getChildren().add(toggleContainer);

        setStyle("-fx-background-color: #21252B;");
        getChildren().add(mainContainer);
        setPrefSize(280, 768);
    }

    /**
     * Activated ToggleButton
     * @param button Button to be scaled
     */
    private void toggle(Button button) {
        if (this.expanded) {
            contract(button);
        } else {
            expand(button);
        }
        this.expanded = !this.expanded;
    }

    /**
     * Contracts the menu
     */
    private void contract(Button button) {
        int activeIndex = this.mainPane.getActiveView();
        Pane[] views = this.mainPane.getViews();

        switch (activeIndex) {
            case 0:
                ((Dashboard) views[activeIndex]).expand();
                break;
            case 1:
                ((IngredientsPane) views[activeIndex]).expand();
                break;
            case 2:
                ((ProductsPane) views[activeIndex]).expand();
                break;
            case 3:
                ((SupplierPane) views[activeIndex]).expand();
                break;
            case 4:
                ((SchedulePane) views[activeIndex]).expand();
                break;
            default:
                System.out.println("Shouldn't be here.");
                break;
        }

        setPrefSize(20,768);

        for (int i=0; i<Views.values().length; i++) {
            this.buttons[i].setText(""+Views.values()[i].name().charAt(0));
        }

        button.setText("Toggle");
        button.setPrefSize(80,50);
        toggleContainer.setPadding(new Insets(0, 0, 0, 24));
    }

    /**
     * Expands the menu
     */
    private void expand(Button button) {
        int activeIndex = this.mainPane.getActiveView();
        Pane[] views = this.mainPane.getViews();

        switch (activeIndex) {
            case 0:
                ((Dashboard) views[activeIndex]).contract();
                break;
            case 1:
                ((IngredientsPane) views[activeIndex]).contract();
                break;
            case 2:
                ((ProductsPane) views[activeIndex]).contract();
                break;
            case 3:
                ((SupplierPane) views[activeIndex]).contract();
                break;
            case 4:
                ((SchedulePane) views[activeIndex]).contract();
                break;
            default:
                System.out.println("Shouldn't be here.");
                break;
        }

        setPrefSize(280,768);

        for (int i=0; i<Views.values().length; i++) {
            this.buttons[i].setText(Views.values()[i].name());
        }

        button.setText("Toggle Button");
        button.setPrefSize(130,50);
        toggleContainer.setPadding(new Insets(0, 0, 0, 75));
    }

    /**
     * Initialize the buttons
     * @param view Active view
     * @return new styled button
     */
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
