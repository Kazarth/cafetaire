package code.entities;

import java.io.Serializable;

public class Content implements Serializable {
    private transient static double serialVersionUID = 71D;
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

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }
}
