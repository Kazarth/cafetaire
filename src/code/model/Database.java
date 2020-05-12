package code.model;

import code.entities.*;
import code.extra.ColourTxT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Contains the different ingredients, food and suppliers belonging to the database.
 * @author Tor Stenfeldt
 * @version 4.0
 */
@SuppressWarnings("unused")
public class Database implements Serializable {
    private transient final double serialVersionUID = 41D;

    private HashMap<String, Ingredient> ingredients;
    private HashMap<String, Product> products;
    private ArrayList<Supplier> suppliers;

    // TODO: Testing purpose IngredientTest
    private ColourTxT colourTxT = new ColourTxT();

    public Database() {
        this.ingredients = new HashMap<>();
        this.products = new HashMap<>();
        this.suppliers = new ArrayList<>();

        testPopulate();
    }

    public boolean addIngredient(Ingredient ingredient) {
        if (this.ingredients.containsKey(ingredient.getType())) {
            return false;
        }

        this.ingredients.put(ingredient.getType(), ingredient);
        return true;
    }

    public Ingredient getIngredient(String ingredient) {
        return this.ingredients.get(ingredient);
    }

    public Ingredient[] getIngredients() {
        Ingredient[] ingredients = new Ingredient[this.ingredients.size()];
        List<String> keys = new ArrayList<>(this.ingredients.keySet());

        for (int i=0; i<ingredients.length; i++) {
            ingredients[i] = this.ingredients.get(keys.get(i));
        }

        return ingredients;
    }

    public int getNumIngredients(String ingredient) {
        if (this.ingredients.containsKey(ingredient)) {
            return this.ingredients.get(ingredient).getStock();
        }

        return -1;
    }

    public boolean incrementIngredient(String ingredient) {
        if (this.ingredients.containsKey(ingredient)) {
            return this.ingredients.get(ingredient).increment();
        }

        return false;
    }

    public boolean incrementIngredient(Ingredient ingredient) {
        return incrementIngredient(ingredient.getType());
    }

    public boolean incrementIngredient(String ingredient, int value) {
        if (this.ingredients.containsKey(ingredient)) {
            return this.ingredients.get(ingredient).increment(value);
        }

        return false;
    }

    public boolean incrementIngredient(Ingredient ingredient, int value) {
        return incrementIngredient(ingredient.getType(), value);
    }

    public boolean decrementIngredient(String ingredient) {
        if (this.ingredients.containsKey(ingredient)) {
            return this.ingredients.get(ingredient).decrement();
        }

        return false;
    }

    public boolean decrementIngredient(Ingredient ingredient) {
        return decrementIngredient(ingredient.getType());
    }

    public boolean decrementIngredient(String ingredient, int value) {
        if (this.ingredients.containsKey(ingredient)) {
            return this.ingredients.get(ingredient).decrement(value);
        }

        return false;
    }

    public boolean decrementIngredient(Ingredient ingredient, int value) {
        return decrementIngredient(ingredient.getType(), value);
    }

    public boolean removeIngredient(String ingredient) {
        return (this.ingredients.remove(ingredient) != null);
    }

    public boolean addProduct(Product product) {
        if (this.products.containsKey(product.getType())) {
            return false;
        }

        this.products.put(product.getType(), product);
        return true;
    }


    public Product getProduct(String product) {
        return this.products.get(product);
    }

    public Product[] getProducts() {
        Product[] products = new Product[this.products.size()];
        String[] keys = ((String[])this.products.keySet().toArray());

        for (int i=0; i<products.length; i++) {
            products[i] = this.products.get(keys[i]);
        }

        return products;
    }

    public int getNumProduct(String product) {
        return this.products.get(product).getStock();
    }

    public boolean incrementProduct(String product) {
        return this.products.get(product).increment();
    }

    public boolean incrementProduct(Product product) {
        return incrementProduct(product.getType());
    }

    public boolean incrementProduct(String product, int val) {
        return this.products.get(product).increment(val);
    }

    public boolean incrementProduct(Product product, int val) {
        return incrementProduct(product.getType(), val);
    }

    public boolean decrementProduct(String product) {
        return this.products.get(product).decrement();
    }

    public boolean decrementProduct(Product product) {
        return decrementProduct(product.getType());
    }

    public boolean decrementProduct(String product, int val) {
        return this.products.get(product).decrement(val);
    }

    public boolean decrementProduct(Product product, int val) {
        return decrementProduct(product.getType(), val);
    }

    public boolean removeProduct(String product) {
        return (this.ingredients.remove(product) != null);
    }

    public boolean addSupplier(Supplier supplier) {
        if (!this.suppliers.contains(supplier)) {
            this.suppliers.add(supplier);
            return true;
        } else {
            return false;
        }
    }

    public boolean addSupplier(String name) {
        for (Supplier sup: this.suppliers) {
            if (sup.getName().equals(name)) {
                return false;
            }
        }

        this.suppliers.add(new Supplier(name));
        return true;
    }

    public Supplier getSupplier(String name) {
        for (Supplier supplier : this.suppliers) {
            if (supplier.getName().equals(name)) {
                return supplier;
            }
        }

        return null;
    }

    public ArrayList<Supplier> getSuppliers() {
        return this.suppliers;
    }

    public int getNumSuppliers() {
        return this.suppliers.size();
    }

    public boolean removeSupplier(String name) {
        return this.suppliers.removeIf(s -> s.getName().equals(name));
    }

    /**
     * Used for testing purposes.
     */
    public void testPopulate() {
        Supplier lucasAB = new Supplier("Lucas AB");
        Supplier georgeAB = new Supplier("George AB");
        Supplier paulAB = new Supplier("Paul AB");

        addSupplier(lucasAB);
        addSupplier(georgeAB);
        addSupplier(paulAB);

        Ingredient salt = new Ingredient("Salt", "Dry Food", 6, lucasAB);
        Ingredient sugar = new Ingredient("Sugar", "Dry Food", 7, georgeAB);
        Ingredient cocoa = new Ingredient("Cocoa", "Dry Food", 8, paulAB);

        addIngredient(salt);
        addIngredient(sugar);
        addIngredient(cocoa);
    }
}
