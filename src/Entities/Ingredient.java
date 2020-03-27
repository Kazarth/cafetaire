package Entities;

/**
 * An entity used to store individual ingredients and their information.
 */
public class Ingredient {
    private String type;
    private Supplier supplier;
    private int quantity;

    public Ingredient(Ingredient ingredient) {
        this.type = ingredient.getType();
        this.supplier = ingredient.getSupplier();
        this.quantity = 1;
    }

    public Ingredient(String type) {
        this.type = type;
        this.supplier = new Supplier();
        this.quantity = 1;
    }

    public Ingredient(String type, Supplier supplier) {
        this.type = type;
        this.supplier = supplier;
    }

    protected void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public String getType() {
        return type;
    }

    public Supplier getSupplier() {
        return supplier;
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
}
