package code.view.panes;

import code.control.Callback;
import code.entities.Recipe;
import code.entities.RecipePanes;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class RecipePane extends StackPane implements EnhancedPane {
    private Pane[] views;
    private Pane currentPane;
    private RecipePanes panes;
    private int pane;


    public RecipePane(Callback callback) {
        this.panes = RecipePanes.RecipeListPane;

        /* adding panes */
        this.views = new Pane[3];
        this.views[0] = new RecipeListPane(callback, this);
        this.views[1] = new RecipeViewPane(callback, this);
        this.views[2] = new RecipeAddNewPane(callback, this, (RecipeListPane) views[0]);

        /* Gray background */
        this.setPrefSize(1086,768);
        this.setStyle(
                "-fx-background-color: #6B6C6A;"
        );
        this.getStylesheets().add("styles.css");

        this.currentPane = views[0];

        this.getChildren().add(currentPane);
    }

    @Override
    public void expand() {
        setPrefWidth(1346);
    }

    @Override
    public void contract() {
        setPrefWidth(1086);
    }

    public void refresh() {
        // TODO: refresh anything?
    }

    public void deleteRecipe(Recipe recipe) {
        RecipeListPane recipeListPane = (RecipeListPane) views[0];
        recipeListPane.deleteRecipe(recipe);
    }

    void loadRecipe(RecipePanes view, Recipe recipe) {
        RecipeViewPane recipeViewPane = (RecipeViewPane) views[1];
        recipeViewPane.setRecipe(recipe);
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
        getChildren().set(0, views[pane]);
        this.panes = view;
    }
}
