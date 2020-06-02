package code.entities;

public class IngredientRecipe extends Ingredient {
    private double amount;


    public IngredientRecipe(String type, double amount) {
        super(type);
        this.amount = amount;
    }

    public IngredientRecipe(String type, String category, int stock, Supplier supplier, Units unit) {
        super(type, category, stock, supplier, unit);
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
    public Units getUnit() {
        return super.getUnit();
    }

    @Override
    public void setUnit(Units unit) {
        super.setUnit(unit);
    }

    @Override
    public double getStock() {
        return super.getStock();
    }

    @Override
    public void setStock(double stock) {
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
    public boolean decrement(double value) {
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
}
