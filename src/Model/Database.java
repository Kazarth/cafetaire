package Model;

import Entities.Food;
import Entities.Ingredient;
import Entities.Supplier;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains the different ingredients, food and suppliers belonging to the database.
 */
public class Database {
    private HashMap<String, Ingredient> ingredients;
    private HashMap<String, Integer> nIngredients;

    private HashMap<String, Food> food;
    private HashMap<String, Integer> nFood;

    private ArrayList<Supplier> suppliers;

    public Database() {
        this.ingredients = new HashMap<>();
        this.nIngredients = new HashMap<>();

        this.food = new HashMap<>();
        this.nFood = new HashMap<>();

        suppliers = new ArrayList<>();
    }

    public Food getFood(String food) {
        if (this.food.containsKey(food)) {
            return this.food.get(food);
        }

        return null;
    }

    public int getNumberFood(String food) {
        if (nFood.containsKey(food)) {
            return nFood.get(food);
        }

        return -1;
    }

    public Supplier getSupplier(String name) {
        for (Supplier supplier : this.suppliers) {
            if (supplier.getName().equals(name)) {
                return supplier;
            }
        }

        return null;
    }

    /* TODO: backup.
    public void increaseIngredient(Ingredient ingredient) {
        String type = ingredient.getType();

        if (ingredients.containsKey(type)) {
            int currentVal = nIngredients.get(type);
            nIngredients.put(type, currentVal+1);
        } else {
            ingredients.put(type, ingredient);
            nIngredients.put(type, 1);
        }
    }
     */

    public boolean addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient.getType())) {
            return false;
        }

        ingredients.put(ingredient.getType(), ingredient);
        nIngredients.put(ingredient.getType(), 1);
        return true;
    }

    public Ingredient getIngredient(String ingredient) {
        return ingredients.get(ingredient);
    }

    public Ingredient[] getIngredients() {
        Ingredient[] ingredients = new Ingredient[this.ingredients.size()];
        for (int i=0; i<ingredients.length; i++) {
            String[] keys = ((String[])this.ingredients.keySet().toArray());
            // TODO. for each key, get value and add to array
        }

        return ingredients;
    }

    public int getNumberIngredients(String ingredient) {
        if (nIngredients.containsKey(ingredient)) {
            return nIngredients.get(ingredient);
        }

        return -1;
    }

    // TODO: getIngredient
    // TODO: getIngredients

    public boolean increaseIngredient(String ingredient) {
        if (nIngredients.containsKey(ingredient)) {
            int currentVal = nIngredients.get(ingredient);
            nIngredients.put(ingredient, currentVal+1);
            return true;
        }

        return false;
    }

    public boolean increaseIngredient(Ingredient ingredient) {
        return increaseIngredient(ingredient.getType());
    }

    public boolean decreaseIngredient(String ingredient) {
        if (!nIngredients.containsKey(ingredient)) {
            return false;
        }

        int currentVal = nIngredients.get(ingredient);

        if (currentVal <= 0) {
            return false;
        }

        nIngredients.put(ingredient, currentVal-1);
        return true;
    }

    public boolean decreaseIngredient(Ingredient ingredient) {
        return decreaseIngredient(ingredient.getType());
    }

    public boolean removeIngredient(String ingredient) {
        boolean removal = false;

        if (ingredients.containsKey(ingredient)) {
            ingredients.remove(ingredient);
            removal = true;
        }

        if (nIngredients.containsKey(ingredient)) {
            nIngredients.remove(ingredient);
            removal = true;
        }

        return removal;
    }

    public boolean addFood(Food food) {
        if (this.food.containsKey(food.getType())) {
            return false;
        }

        this.food.put(food.getType(), food);
        this.nFood.put(food.getType(), 1);
        return true;
    }

    // TODO: getFood
    // TODO: getFoodArray

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
        boolean removal = false;

        if (this.food.containsKey(food)) {
            this.food.remove(food);
            removal = true;
        }

        if (this.nFood.containsKey(food)) {
            nFood.remove(food);
            removal = true;
        }

        return removal;
    }

    public void addSupplier(Supplier supplier) {
        if (!this.suppliers.contains(supplier)) {
            this.suppliers.add(supplier);
        }
    }

    public void addSupplier(String name) {
        for (Supplier sup: this.suppliers) {
            if (sup.getName().equals(name)) {
                return;
            }
        }

        this.suppliers.add(new Supplier(name));
    }

    // TODO: getSupplier
    // TODO: getSuppliers

    public boolean removeSupplier(String name) {
        return this.suppliers.removeIf(s -> s.getName().equals(name));
    }
}
