package code.entities;

import java.io.Serializable;

/**
 * Class used to store data about various products in the program
 * @author Viktor Polak
 * @version 1.1
 */

public class Product implements Serializable {
    private String type;
    private ProductCategories category;
    private int stock;
    private Recipe recipe;

    public Product(String type, ProductCategories category, int stock) {
        this.type = type;
        this.category = category;
        this.stock = stock;
        this.recipe = null;
    }

    public String getType() {
        return this.type;
    }

    public ProductCategories getCategory() {
        return this.category;
    }

    public int getStock() {
        return this.stock;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setCategory(ProductCategories category) {
        this.category = category;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Recipe getRecipe() {
        return this.recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    @Override
    public String toString(){
        return "Name: " + this.type + "\n" +
                "Category: " + this.category + "\n" +
                "Amount: " + this.stock;
    }
}
