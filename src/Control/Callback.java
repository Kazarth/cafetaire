package Control;

import Entities.*;

/**
 * Used for callback functionality between the Controller and the different Frames.
 */
public interface Callback {
    boolean addIngredient(Ingredient ingredient);
    Ingredient getIngredient(String ingredient);
    Ingredient[] getIngredients();
    boolean increaseIngredient(String ingredient);
    boolean increaseIngredient(Ingredient ingredient);
    boolean decreaseIngredient(String ingredient);
    boolean decreaseIngredient(Ingredient ingredient);
    boolean removeIngredient(String ingredient);

    boolean addFood(Food food);
    Food getFood(String food);
    Food[] getFood();
    boolean increaseFood(String food);
    boolean increaseFood(Food food);
    boolean decreaseFood(String food);
    boolean decreaseFood(Food food);
    boolean removeFood(String food);

    boolean addSupplier (Supplier supplier);
    boolean addSupplier (String name);
    Supplier getSupplier(String supplier);
    Supplier[] getSuppliers();
    boolean removeSupplier (String name);
}
