package code.entities;

public class IngredientRecipe extends Ingredient {
    private double amount;
    private String unit;

    public IngredientRecipe(String type, double amount, String unit) {
        super(type);
        this.amount = amount;
        this.unit = unit;
    }

    public IngredientRecipe(String type, String category, int stock, Supplier supplier) {
        super(type, category, stock, supplier);
    }

    @Override
    public String getType() {
        return super.getType();
    }

    @Override
    public void setType(String type) {
        super.setType(type);
    }

    @Override
    public String getCategory() {
        return super.getCategory();
    }

    @Override
    public void setCategory(String category) {
        super.setCategory(category);
    }

    @Override
    public Supplier getSupplier() {
        return super.getSupplier();
    }

    @Override
    public void setSupplier(Supplier supplier) {
        super.setSupplier(supplier);
    }

    @Override
    public int getStock() {
        return super.getStock();
    }

    @Override
    public void setStock(int stock) {
        super.setStock(stock);
    }

    @Override
    public boolean increment() {
        return super.increment();
    }

    @Override
    public boolean increment(int value) {
        return super.increment(value);
    }

    @Override
    public boolean decrement() {
        return super.decrement();
    }

    @Override
    public boolean decrement(int value) {
        return super.decrement(value);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
