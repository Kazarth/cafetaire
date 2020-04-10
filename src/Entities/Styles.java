package Entities;

/**
 * Can be used in order to get styles for different types of components.
 * @author Tor Stenfeldt
 * @version 1.0
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
                "-fx-border-radius: 0 25 25 0;"
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
                "-fx-border-radius: 0 25 25 0;"
        );
    }

    public static String getButton() {
        return  (
                "-fx-background-color: #619f81;" +
                " -fx-text-fill: #FFFFFF;" +
                " -fx-font-family: Segoe UI;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16;" +
                "-fx-padding: 5 10 5 10;" +
                "-fx-background-radius: 10;"
        );
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
}
