package code.entities;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Can be used in order to get styles for different types of components.
 * @author Tor Stenfeldt, Paul Moustakas, Lucas Eliasson
 * @version 4.0
 */
public abstract class Styles {
    public static String getMenuButtonStandard() {
        return (
                "-fx-background-color: #21252B;" +
                "-fx-font-size: 18px;" +
                "-fx-text-fill: #619F81;" +
                "-fx-border-color: #619F81;" +
                "-fx-border-style: none;" +
                "-fx-border-width: 0 0 0 0;" +
                "-fx-border-radius: 0 25 25 0;" +
                "-fx-cursor: hand;"
        );
    }

    public static String getMenuButtonHighlighted() {
        return (
                "-fx-background-color: #21252B;" +
                "-fx-font-size: 18px;" +
                "-fx-text-fill: #619F81;" +
                "-fx-border-color: #619F81;" +
                "-fx-border-style: dashed;" +
                "-fx-border-width: 3 3 3 0;" +
                "-fx-border-radius: 0 25 25 0;" +
                "-fx-cursor: hand;"
        );
    }

    public static String getButton() {
        return  (
                "-fx-background-color: #619f81;" +
                " -fx-text-fill: #FFFFFF;" +
                " -fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14;" +
                "-fx-padding: 5 10 5 10;" +
                "-fx-background-radius: 10;" +
                "-fx-pref-height: 40;" +
                "-fx-pref-width: 160;"

        );
    }

    public static Font getValueButtonsFont () {
        return Font.font("Segoe UI", FontWeight.BOLD, FontPosture.REGULAR, 36);
    }

    public static String getSearchBar() {
        return (
                "-fx-text-fill: BLACK;" +
                "-fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16;"
        );
    }

    public static String getBoxTitle() {
        return (
                "-fx-text-fill: #619F81;" +
                "-fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 24;"
        );
    }

    public static String getTime() {
        return (
                "-fx-text-fill: #619F81;" +
                "-fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 12;" +
                "-fx-padding: 5"
        );
    }

    public static String getTableContent() {
        return (
                "-fx-text-fill: #619F81;" +
                "-fx-font-family: Segoe UI;" +
                "-fx-font-size: 12;" +
                "-fx-padding: 5"
        );
    }

    public static String getColHeader () {
        return (
                "-fx-text-fill: #FFF;" +
                "-fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 14;" +
                "-fx-padding: 5"
        );
    }

    public static String getPane() {
        return "-fx-background-color: #6B6C6A;";
    }

    public static String getDashboardBox() {
        return (
                "-fx-alignment: top_center;" +
                "-fx-background-color: #FFFFFF;" +
                "-fx-background-radius: 20 20 20 20;"
        );
    }

    public static String getContainerBox() {
        return (
                "-fx-alignment: center;" +
                "-fx-background-color: #FFFFFF;"
        );
    }

    public static String getTableColumn(){
        return (
                "-fx-text-fill: #000;" +
                "-fx-alignment: center;" +
                "-fx-font-size: 12;" +
                "-fx-font-family: Segoe UI;"
                );
    }
    public static String getTableRowSelected() {
        return (
                "-fx-selection-bar: #619F81;" +
                "-fx-selection-bar-non-focused: #619F81;" +
                "-fx-background-color: #FFF; "
                );
    }
  
    public static String getPopTitle() {
        return (
                "-fx-text-fill: #619F81;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 25px"
        );
    }

    public static String getPopField() {
        return (
                "-fx-background-color: #fff;" +
                "-fx-border-width: 1;" +
                "-fx-border-color: #000;"
        );
    }

    public static String getPopAddButton() {
        return (
                "-fx-background-color: #619F81;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 40;" +
                "-fx-text-fill: #fff;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: Bold;"
        );
    }

    public static String getPopCancelButton() {
        return (
                "-fx-background-color: #ddd;" +
                "-fx-background-radius: 10;" +
                "-fx-border-radius: 40;" +
                "-fx-text-fill: #fff;" +
                "-fx-font-size: 16px;" +
                "-fx-font-weight: Bold;"
        );
    }
}
