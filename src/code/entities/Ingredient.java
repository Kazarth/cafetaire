package code.entities;

/**
 * Ingredient.java
 * Class used to store objects simulating ingredients in stock.
 * @author Tor Stenfeldt, Lucas Eliasson
 * @version 1.1
 */
public class Ingredient {
    private String type, category;
    private Supplier supplier;
    private int stock;

    public Ingredient(String type) {
        this.type = type;
        category = "";
        supplier = new Supplier();
        stock = 1;
    }

    public Ingredient(String type, String category, int stock, Supplier supplier) {
        this.type = type;
        this.category = category;
        this.supplier = supplier;
        this.stock = stock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public String toString() {
        return "Name: " + this.type + "\n" +
                "Category: " + this.category + "\n" +
                "Stock: " + this.stock;
    }
}
