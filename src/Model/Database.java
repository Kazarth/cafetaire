package Model;

import Entities.Food;
import Entities.Ingredient;
import Entities.Supplier;

import java.util.ArrayList;

/**
 * Contains the different ingredients, food and suppliers belonging to the database.
 */
public class Database {
    private ArrayList<Ingredient> ingredients;
    private ArrayList<Food> food;
    private ArrayList<Supplier> suppliers;

    public Database() {
        this.ingredients = new ArrayList<>();
        this.food = new ArrayList<>();
        suppliers = new ArrayList<>();
    }

    public Ingredient getIngredient(String type) {
        for (Ingredient ingredient : this.ingredients) {
            if (ingredient.getType().equals(type)) {
                return ingredient;
            }
        }

        return null;
    }

    public Food getFood(String type) {
        for (Food food : this.food) {
            if (food.getType().equals(type)) {
                return food;
            }
        }

        return null;
    }

    public Supplier getSupplier(String name) {
        for (Supplier supplier : this.suppliers) {
            if (supplier.getName().equals(name)) {
                return supplier;
            }
        }

        return null;
    }

    public void addIngredient(Ingredient ingredient) {
        for (Ingredient i: this.ingredients) {
            if (i.equals(ingredient)) {
                i.increase();
                return;
            }
        }

        this.ingredients.add(ingredient);
    }

    public void addIngredient(String s) {
        for (Ingredient i: this.ingredients) {
            if (i.getType().equals(s)) {
                i.increase();
                return;
            }
        }

        this.ingredients.add(new Ingredient(s));
    }

    public void addFood(Food food) {
        for (Food f: this.food) {
            if (f.equals(food)) {
                f.increase();
                return;
            }
        }

        this.food.add(food);
    }

    public void addFood(String s) {

        for (Food f: this.food) {
            if (f.getType().equals(s)) {
                f.increase();
                return;
            }
        }

        this.food.add(new Food(s));
    }

    public void addSupplier(Supplier supplier) {
        if (!this.suppliers.contains(supplier)) {
            this.suppliers.add(supplier);
        }
    }

    public void addSupplier(String s) {
        for (Supplier sup: this.suppliers) {
            if (sup.getName().equals(s)) {
                return;
            }
        }

        this.suppliers.add(new Supplier(s));
    }

    public boolean removeIngredient(String type) {
        for (Ingredient i: this.ingredients) {
            if (i.getType().equals(type)) {
                i.decrease();

                if (i.getQuantity() == 0) {
                    this.ingredients.remove(i);
                }

                return true;
            }
        }

        return false;
    }

    public boolean removeFood(String type) {
        for (Food f: this.food) {
            if (f.getType().equals(type)) {
                f.decrease();

                if (f.getQuantity() == 0) {
                    this.food.remove(f);
                }

                return true;
            }
        }

        return false;
    }

    public boolean removeSupplier(String name) {
        return this.suppliers.removeIf(s -> s.getName().equals(name));
    }
}
