package code.model;

import code.entities.*;
import code.extra.ColourTxT;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * TODO: add 'this' before global references.
 * Contains the different ingredients, food and suppliers belonging to the database.
 * @author Tor Stenfeldt
 * @version 3.0
 */
@SuppressWarnings("unused")
public class Database implements Serializable {
    private HashMap<String, Ingredient> ingredients;

    private HashMap<String, Food> food;
    private HashMap<String, Integer> nFood;

    private ArrayList<Supplier> suppliers;

    // TODO: Testing purpose IngredientTest
    private ColourTxT colourTxT = new ColourTxT();

    // TODO: Testing purpose Product
    private HashMap<String, Product> productsTest;
    private HashMap<String, Integer> nProductsTest;

    public Database() {
        this.ingredients = new HashMap<>();
        this.food = new HashMap<>();
        this.nFood = new HashMap<>();
        this.suppliers = new ArrayList<>();

        nProductsTest = new HashMap<>();
        productsTest = new HashMap<>();

        testPopulate();
    }

    public boolean addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient.getType())) {
            return false;
        }

        ingredients.put(ingredient.getType(), ingredient);
        return true;
    }

    public Ingredient getIngredient(String ingredient) {
        return ingredients.get(ingredient);
    }

    public Ingredient[] getIngredients() {
        Ingredient[] ingredients = new Ingredient[this.ingredients.size()];
        String[] keys = ((String[])this.ingredients.keySet().toArray());

        for (int i=0; i<ingredients.length; i++) {
            ingredients[i] = this.ingredients.get(keys[i]);
        }

        return ingredients;
    }

    public int getNumIngredients(String ingredient) {
        if (ingredients.containsKey(ingredient)) {
            return ingredients.get(ingredient).getStock();
        }

        return -1;
    }

    public boolean incrementIngredient(String ingredient) {
        if (ingredients.containsKey(ingredient)) {
            return ingredients.get(ingredient).increment();
        }

        return false;
    }

    public boolean incrementIngredient(Ingredient ingredient) {
        return incrementIngredient(ingredient.getType());
    }

    public boolean incrementIngredient(String ingredient, int value) {
        if (ingredients.containsKey(ingredient)) {
            return ingredients.get(ingredient).increment(value);
        }

        return false;
    }

    public boolean incrementIngredient(Ingredient ingredient, int value) {
        return incrementIngredient(ingredient.getType(), value);
    }

    public boolean decrementIngredient(String ingredient) {
        if (ingredients.containsKey(ingredient)) {
            return ingredients.get(ingredient).decrement();
        }

        return false;
    }

    public boolean decrementIngredient(Ingredient ingredient) {
        return decrementIngredient(ingredient.getType());
    }

    public boolean decrementIngredient(String ingredient, int value) {
        if (ingredients.containsKey(ingredient)) {
            return ingredients.get(ingredient).decrement(value);
        }

        return false;
    }

    public boolean decrementIngredient(Ingredient ingredient, int value) {
        return decrementIngredient(ingredient.getType(), value);
    }

    public boolean removeIngredient(String ingredient) {
        return (ingredients.remove(ingredient) != null);
    }

    public boolean addFood(Food food) {
        if (this.food.containsKey(food.getType())) {
            return false;
        }

        this.food.put(food.getType(), food);
        this.nFood.put(food.getType(), 1);
        return true;
    }


    public Food getFood(String food) {
        return this.food.get(food);
    }

    public Food[] getFood() {
        Food[] food = new Food[this.food.size()];
        String[] keys = ((String[])this.food.keySet().toArray());

        for (int i=0; i<food.length; i++) {
            food[i] = this.food.get(keys[i]);
        }

        return food;
    }

    public int getNumFood(String food) {
        if (nFood.containsKey(food)) {
            return nFood.get(food);
        }

        return -1;
    }

    public boolean increaseFood(String food) {
        if (nFood.containsKey(food)) {
            int currentVal = nFood.get(food);
            nFood.put(food, currentVal+1);
            return true;
        }

        return false;
    }

    public boolean increaseFood(Food food) {
        return increaseFood(food.getType());
    }

    public boolean decreaseFood(String food) {
        if (!nFood.containsKey(food)) {
            return false;
        }

        int currentVal = nFood.get(food);

        if (currentVal <= 0) {
            return false;
        }

        nFood.put(food, currentVal-1);
        return true;
    }

    public boolean decreaseFood(Food food) {
        return decreaseFood(food.getType());
    }

    public boolean removeFood(String food) {
        boolean containsKey = this.food.containsKey(food) && this.nFood.containsKey(food);

        this.food.remove(food);
        this.nFood.remove(food);

        return containsKey;
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
        return suppliers.size();
    }

    public boolean removeSupplier(String name) {
        return this.suppliers.removeIf(s -> s.getName().equals(name));
    }

    /*Testing purpose PRODUCT*/
    public boolean addProductTest(Product product) {
        if (productsTest.containsKey(product.getName())) {
            return false;
        }

        productsTest.put(product.getName(), product);
        nProductsTest.put(product.getName(), 1);

        System.out.println("\nAdded to Database:\n" +
                "Name: " + product.getName() + "\n" +
                "Category: " + product.getCategory() + "\n" +
                "Amount: " + product.getQuantity() + "\n"
        );

        System.out.println("Get from products HashMap:\n" +
                productsTest.get(product.getName()));

        System.out.println("\nGet from nProducts HashMap:\n" +
                nProductsTest.get(product.getName()) + "\n");
        return true;
    }

    public Product getProductTest(String product) {
        return productsTest.get(product);
    }

    public Product[] getProductTest() {
        Product[] products = new Product[this.productsTest.size()];

        Object[] keys = this.productsTest.keySet().toArray();

        for (int i=0; i<products.length; i++) {
            products[i] = this.productsTest.get(keys[i]);
        }

        return products;
    }

    public int getNumProductTest(String product) {
        if (nProductsTest.containsKey(product)) {
            return nProductsTest.get(product);
        }

        return -1;
    }

    public boolean increaseProductTest(String product) {
        if (nProductsTest.containsKey(product)) {
            int currentVal = nProductsTest.get(product);
            nProductsTest.put(product, currentVal+1);

            System.out.println(colourTxT.GREEN() + "Completed increase to database\n" +
                    "Current stock: " + productsTest.get(product).getName() + " " + nProductsTest.get(product) + colourTxT.RESET());

            return true;
        }
        System.out.println(colourTxT.RED() + "Failed to add " + product + " to database" + colourTxT.RESET());
        return false;
    }

    public boolean increaseProductTest(Product product) {
        return increaseProductTest(product.getName());
    }

    public boolean decreaseProductTest(String product) {
        if (!nProductsTest.containsKey(product)) {
            System.out.println(colourTxT.RED() + "Failed to remove " + product + " to database" + colourTxT.RESET());
            return false;
        }

        int currentVal = nProductsTest.get(product);

        if (currentVal <= 0) {
            System.out.println(colourTxT.RED() + "Amount to remove from " + product + " went below 0" + colourTxT.RESET());
            return false;
        }

        nProductsTest.put(product, currentVal-1);
        System.out.println(colourTxT.GREEN() + "Completed decrease to database\n" +
                "Current stock: " + productsTest.get(product).getName() + " " + nProductsTest.get(product) + colourTxT.RESET());
        return true;
    }

    public boolean decreaseProductTest(Product product) {
        return decreaseProductTest(product.getName());
    }

    public boolean removeProductTest(String product) {
        boolean containsKey = productsTest.containsKey(product) && nProductsTest.containsKey(product);

        productsTest.remove(product);
        nProductsTest.remove(product);

        return containsKey;
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
