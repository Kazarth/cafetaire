package Model;

import Entities.Food;
import Entities.Ingredient;
import Entities.IngredientTest;
import Entities.Supplier;
import Extra.ColourTxT;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Contains the different ingredients, food and suppliers belonging to the database.
 * @author Tor Stenfeldt
 * @version 3.0
 */
@SuppressWarnings("unused")
public class Database {
    private HashMap<String, Ingredient> ingredients;
    private HashMap<String, Integer> nIngredients;

    private HashMap<String, Food> food;
    private HashMap<String, Integer> nFood;

    private ArrayList<Supplier> suppliers;

    /*Testing purpose */
    private HashMap<String, IngredientTest> ingredientsTest;
    private HashMap<String, Integer> nIngredientsTest;
    private ColourTxT colourTxT = new ColourTxT();

    public Database() {
        this.ingredients = new HashMap<>();
        this.nIngredients = new HashMap<>();

        this.food = new HashMap<>();
        this.nFood = new HashMap<>();

        this.suppliers = new ArrayList<>();

        /* Testing purposes */
        ingredientsTest = new HashMap<>();
        nIngredientsTest = new HashMap<>();
        suppliers.add(new Supplier("Lucas AB"));
        suppliers.add(new Supplier("Georg AB"));
        suppliers.add(new Supplier("Julia AB"));
    }

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
        String[] keys = ((String[])this.ingredients.keySet().toArray());

        for (int i=0; i<ingredients.length; i++) {
            ingredients[i] = this.ingredients.get(keys[i]);
        }

        return ingredients;
    }

    public int getNumIngredients(String ingredient) {
        if (nIngredients.containsKey(ingredient)) {
            return nIngredients.get(ingredient);
        }

        return -1;
    }

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
        boolean containsKey = ingredients.containsKey(ingredient) && nIngredients.containsKey(ingredient);

        ingredients.remove(ingredient);
        nIngredients.remove(ingredient);

        return containsKey;
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

    public Supplier[] getSuppliers() {
        return ((Supplier[])this.suppliers.toArray());
    }

    public int getNumSuppliers() {
        return suppliers.size();
    }

    public boolean removeSupplier(String name) {
        return this.suppliers.removeIf(s -> s.getName().equals(name));
    }


    /* Testing purposes */
    public boolean addIngredientTest(IngredientTest ingredient) {
        if (ingredients.containsKey(ingredient.getName())) {
            return false;
        }

        ingredientsTest.put(ingredient.getName(), ingredient);
        nIngredientsTest.put(ingredient.getName(), 1);

        System.out.println("\nAdded to Database:\n" +
                "Name: " + ingredient.getName() + "\n" +
                "Category: " + ingredient.getCategory() + "\n" +
                "Supplier: " + ingredient.getSupplier() + "\n"
        );

        System.out.println("Get from ingredients HashMap:\n" +
                ingredientsTest.get(ingredient.getName()));

        System.out.println("\nGet from nIngredients HashMap:\n" +
                nIngredientsTest.get(ingredient.getName()) + "\n");
        return true;
    }

    public IngredientTest getIngredientTest(String ingredient) {
        return ingredientsTest.get(ingredient);
    }

    public IngredientTest[] getIngredientsTest() {
        IngredientTest[] ingredients = new IngredientTest[this.ingredients.size()];
        String[] keys = ((String[])this.ingredientsTest.keySet().toArray());

        for (int i=0; i<ingredients.length; i++) {
            ingredients[i] = this.ingredientsTest.get(keys[i]);
        }

        return ingredients;
    }

    public int getNumIngredientsTest(String ingredient) {
        if (nIngredientsTest.containsKey(ingredient)) {
            return nIngredientsTest.get(ingredient);
        }

        return -1;
    }

    public boolean increaseIngredientTest(String ingredient) {
        if (nIngredientsTest.containsKey(ingredient)) {
            int currentVal = nIngredientsTest.get(ingredient);
            nIngredientsTest.put(ingredient, currentVal+1);

            System.out.println(colourTxT.GREEN() + "Completed increase to database\n" +
                    "Current stock: " + nIngredientsTest.get(ingredient) + colourTxT.RESET());

            return true;
        }
        System.out.println(colourTxT.RED() + "Failed to add " + ingredient + " to database" + colourTxT.RESET());
        return false;
    }

    public boolean increaseIngredientTest(IngredientTest ingredient) {
        return increaseIngredientTest(ingredient.getName());
    }

    public boolean decreaseIngredientTest(String ingredient) {
        if (!nIngredientsTest.containsKey(ingredient)) {
            return false;
        }

        int currentVal = nIngredientsTest.get(ingredient);

        if (currentVal <= 0) {
            return false;
        }

        nIngredientsTest.put(ingredient, currentVal-1);
        return true;
    }

    public boolean decreaseIngredientTest(IngredientTest ingredient) {
        return decreaseIngredient(ingredient.getName());
    }

    public boolean removeIngredientTest(String ingredient) {
        boolean containsKey = ingredientsTest.containsKey(ingredient) && nIngredientsTest.containsKey(ingredient);

        ingredientsTest.remove(ingredient);
        nIngredientsTest.remove(ingredient);

        return containsKey;
    }
}
