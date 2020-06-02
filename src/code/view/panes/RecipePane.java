package code.view.panes;

import code.control.Callback;
import code.entities.Recipe;
import code.entities.RecipePanes;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 * // TODO: desc
 * @author Tor Stenfeldt, Lucas Eliasson, Paul Moustakas.
 * @version 2.0
 */
public class RecipePane extends StackPane implements EnhancedPane {
    private EnhancedPane[] views;
    private EnhancedPane currentPane;
    private RecipePanes panes;
    private int pane;

    public RecipePane(Callback callback) {
        this.panes = RecipePanes.RecipeListPane;

        /* adding panes */
        this.views = new EnhancedPane[3];
        this.views[0] = new RecipeListPane(callback, this);
        this.views[1] = new RecipeViewPane(callback, this);
        this.views[2] = new RecipeAddNewPane(callback, this, (RecipeListPane) views[0]);

        /* Gray background */
        this.setPrefSize(1054,736);
        this.setStyle(
                "-fx-background-color: #6B6C6A;"
        );
        this.getStylesheets().add("styles.css");
        this.currentPane = views[0];
        this.getChildren().add((Node) currentPane);
    }

    @Override
    public void expand() {
        setPrefWidth(1200);
        views[pane].expand();
    }

    @Override
    public void contract() {
        setPrefWidth(1054);
        views[pane].contract();
    }

    public void refresh() {
        views[pane].refresh();
    }

    public void deleteRecipe(Recipe recipe) {
        ((RecipeListPane) views[0]).deleteRecipe(recipe);
    }

    void loadRecipe(RecipePanes view, Recipe recipe) {
        ((RecipeViewPane) this.views[1]).setRecipe(recipe);
        setView(view);
    }

    void setView(RecipePanes view) {
        this.pane = -1;

        for (int i=0; i<RecipePanes.values().length; i++) {
            if (RecipePanes.values()[i] == view) {
                this.pane = i;
                break;
            }
        }

        views[pane].refresh();
        getChildren().set(0, (Node) views[pane]);
        this.panes = view;
    }
}
