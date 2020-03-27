package Entities;

/**
 * An entity for containing individual food products and their information.
 */
public class Food {
    private String type;
    private Ingredient[] ingredients; // TODO: change to ArrayList?
    private int quantity;

    public Food(Food food) {
        this.type = food.getType();
        this.ingredients = food.getIngredients();
        this.quantity = food.getQuantity();
    }

    public Food(String type) {
        this.type = type;
        this.ingredients = new Ingredient[0];
        this.quantity = 1;
    }

    public Food(String type, Ingredient[] ingredients) {
        this.type = type;
        this.ingredients = ingredients;
        this.quantity = 1;
    }

    public String getType() {
        return type;
    }

    public Ingredient[] getIngredients() {
        return ingredients;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void increase() {
        this.quantity++;
    }

    public void decrease() {
        this.quantity--;
    }

    public void addIngredient(Ingredient ingredient) {
        // TODO:
    }
}
