package Entities;

/**
 * An entity used to store individual ingredients and their information.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class Ingredient {
    private String type;
    private Supplier supplier;

    public Ingredient(Ingredient ingredient) {
        this.type = ingredient.getType();
        this.supplier = ingredient.getSupplier();
    }

    public Ingredient(String type) {
        this.type = type;
        this.supplier = new Supplier();
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
}
