package Entities;

/**
 * An entity for containing individual food products and their information.
 */
public class Food {
    private String type;
    private Recipe recipe;

    public Food(Food food) {
        this.type = food.getType();
        this.recipe = food.getRecipe();
    }

    public Food(String type) {
        this.type = type;
        this.recipe = null;
    }

    public Food(String type, Recipe recipe) {
        this.type = type;
        this.recipe = recipe;
    }

    public String getType() {
        return type;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
