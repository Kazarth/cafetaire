package Entities;

/**
 * IngredientTest.java
 * Gjorde denna klassen för att inte påverka originalet. Vad jag sett ska "produkten" man lägger in ha samma
 * värden som tableView. Vilket det i nuläget inte hade.
 * @author Lucas Eliasson
 * @version 1.0
 */

public class IngredientTest {
    private String name;
    private String category;
    private int stock;
    private String supplier;
    private boolean selected;

    public IngredientTest() {
        name = "";
        category = "";
        stock = 0;
        supplier = "";
        selected = false;
    }

    public IngredientTest(String name, String category, int stock, String supplier) {
        this.name = name;
        this.category = category;
        this.stock = stock;
        this.supplier = supplier;
        selected = false;
    }

    public void decrement() {
        stock--;
    }

    public void increment() {
        stock++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String toString() {
        return "Name: " + name + "\n" +
                "Category: " + category + "\n" +
                "Stock: " + stock;
    }
}
