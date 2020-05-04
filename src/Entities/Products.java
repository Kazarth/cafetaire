package Entities;

/**
 * Class used to store data about various products in the program
 * @author Viktor Polak
 * @version 1.0
 */

public class Products {
    private String name;
    private ProductCategories categories;
    private int quantity;

    public Products(String name, ProductCategories categories, int quantity) {
        this.name = name;
        this.categories = categories;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public ProductCategories getCategories() {
        return categories;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategories(ProductCategories categories) {
        this.categories = categories;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
