package code.entities;

import java.io.Serializable;

/**
 * Ingredient.java
 * Class used to store objects simulating ingredients in stock.
 * @author Tor Stenfeldt, Lucas Eliasson
 * @version 4.0
 */
public class Ingredient implements Serializable {
    private transient static double serialVersionUID = 51D;
    private String type, category;
    private Supplier supplier;
    private double stock;
    private Units unit;
    private String stockAndUnit;

    public Ingredient(String type) {
        this.type = type;
        category = "";
        supplier = new Supplier();
        stock = 1;
    }

    public Ingredient(String type, String category, double stock, Supplier supplier, Units unit) {
        this.type = type;
        this.category = category;
        this.supplier = supplier;
        this.stock = stock;
        this.unit = unit;
        stockAndUnit = stock + " " + unit;
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

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public Units getUnit() {
        return unit;
    }

    public void setUnit(Units unit) {
        this.unit = unit;
    }

    public String getStockAndUnit() {
        return stockAndUnit;
    }

    public void setStockAndUnit() {
        this.stockAndUnit = stock + " " + unit;
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
    public String toString() {
        return "Name: " + this.type + "\n" +
                "Category: " + this.category + "\n" +
                "Stock: " + this.stockAndUnit;
    }
}
