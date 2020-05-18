package code.entities;

public class Content {
    private Ingredient ingredient;
    private double value;
    private Units unit;

    public Content(Ingredient ingredient, double value, Units unit) {
        this.ingredient = ingredient;
        this.value = value;
        this.unit = unit;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public double getValue() {
        return value;
    }

    public Units getUnit() {
        return unit;
    }
}
