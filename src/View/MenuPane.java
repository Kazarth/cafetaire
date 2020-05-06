package View;

import Entities.Styles;
import Entities.Views;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * MenuBar.java
 * The main menu bar used for switching between panes.
 * @author Lucas Eliasson, Tor Stenfeldt
 * @version 4.0
 */
public class MenuPane extends StackPane {
    private Label title;

    private MainPane mainPane;
    private Button[] buttons;
    private Button selectedButton;
    private Button toggleButton;

    private ImageView toggleImage;
    private Image minimizeImage;
    private Image expandImage;

    private HBox toggleContainer;
    private boolean expanded;

    public MenuPane(MainPane mainPane) throws FileNotFoundException {
        this.mainPane = mainPane;
        buttons = new Button[Views.values().length];
        this.expanded = true;

        for (int i=0; i<buttons.length; i++) {
            buttons[i] = initButton(Views.values()[i]);
        }

        selectedButton = buttons[0];
        selectedButton.setStyle(Styles.getMenuButtonHighlighted());

        VBox mainContainer = new VBox();

        title = new Label("Cafetairé");
        title.getStylesheets().add("styles.css");
        title.getStyleClass().add("title");

        HBox titleContainer = new HBox();
        titleContainer.setPadding(new Insets(0, 0, 0, 28));
        titleContainer.getChildren().add(title);

        mainContainer.getChildren().add(titleContainer);

        for (Button b: buttons) {
            mainContainer.getChildren().add(b);
        }

        minimizeImage = new Image(new FileInputStream("resources/toggleButton/Crop.png"));
        expandImage = new Image(new FileInputStream("resources/toggleButton/Expand.png"));

        toggleButton = new Button("");
        toggleButton.setPrefSize(40, 40);
        toggleButton.getStylesheets().add("LeftMenuBar.css");
        toggleButton.getStyleClass().add("toggleButton");
        toggleButton.setOnAction(e -> toggle(toggleButton));
        toggleImage = new ImageView(minimizeImage);
        toggleButton.setGraphic(toggleImage);

        toggleContainer = new HBox();
        toggleContainer.setPadding(new Insets(40, 0, 0, 105));
        toggleContainer.getChildren().add(toggleButton);
        toggleContainer.setAlignment(Pos.BOTTOM_LEFT);

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
            this.buttons[i].setText("");
        }

        toggleImage = new ImageView(expandImage);
        toggleButton.setGraphic(toggleImage);
        button.setPrefSize(40,40);
        toggleContainer.setPadding(new Insets(40, 0, 0, 40));

        title.setText("C");
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

        toggleImage = new ImageView(minimizeImage);
        toggleButton.setGraphic(toggleImage);

        button.setPrefSize(40,40);
        toggleContainer.setPadding(new Insets(40, 0, 0, 105));

        title.setText("Cafetairé");
    }

    /**
     * Initialize the buttons
     * @param view Active view
     * @return new styled button
     */
    private Button initButton(Views view) throws FileNotFoundException {
        Button newButton = new Button(view.name());
        newButton.setPrefSize(280, 100);

        newButton.setStyle(Styles.getMenuButtonStandard());

        Image image = new Image(new FileInputStream("resources/deSelectedImages/" + view.name() + ".png"));
        ImageView imageView = new ImageView(image);

        Image selectedImage = new Image(new FileInputStream("resources/activeImages/" + view.name() + ".png"));
        ImageView selectedView = new ImageView(selectedImage);

        newButton.setGraphic(imageView);

        newButton.setOnMouseClicked((handler) -> {
            for (Button b: buttons) {
                b.setStyle(Styles.getMenuButtonStandard());
            }
            newButton.setGraphic(selectedView);
            mainPane.setView(view);
            newButton.setStyle(Styles.getMenuButtonHighlighted());
            selectedButton = newButton;
        });

        newButton.setOnMouseEntered((handler) -> {
                newButton.setStyle(Styles.getMenuButtonHighlighted());
                newButton.setGraphic(selectedView);
        });

        newButton.setOnMouseExited((handler) -> {
            newButton.setStyle(Styles.getMenuButtonStandard());
            newButton.setGraphic(imageView);
            selectedButton.setStyle(Styles.getMenuButtonHighlighted());
        });

        return newButton;
    }
}
