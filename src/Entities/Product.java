package Entities;

/**
 * Class used to store data about various products in the program
 * @author Viktor Polak
 * @version 1.0
 */

public class Product {
    private String name;
    private ProductCategories category;
    private int quantity;

    public Product(String name, ProductCategories category, int quantity) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public ProductCategories getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(ProductCategories category) {
        this.category = category;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        return "Name: " + name + "\n" +
                "Category: " + category + "\n" +
                "Amount: " + quantity;
    }
}
