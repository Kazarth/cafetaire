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

    public boolean increment() {
        if (stock == Integer.MAX_VALUE) {
            return false;
        }

        stock++;
        return true;
    }

    public boolean increment(int value) {
        if (Integer.MAX_VALUE-stock-value <= 0) {
            return false;
        }

        stock += value;
        return true;
    }

    public boolean decrement() {
        if (stock-1<0) {
            return false;
        }

        stock--;
        return true;
    }

    public boolean decrement(int value) {
        if (stock-value<0) {
            return false;
        }

        stock -= value;
        return true;
    }

    @Override
    public String toString(){
        return "Name: " + this.type + "\n" +
                "Category: " + this.category + "\n" +
                "Amount: " + this.stock;
    }
}
