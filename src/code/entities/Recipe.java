package code.entities;

import java.util.ArrayList;

/**
 * TODO: description
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class Recipe {
    private String name;
    private String category;
    private ArrayList<Ingredient> ingredients;
    private String instructions;

    public Recipe(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
        this.instructions = "";
        this.category = "test";
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getInstructions() {
        return instructions;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }
}
