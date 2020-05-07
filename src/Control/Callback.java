package Control;

import Entities.*;

import java.util.ArrayList;

/**
 * Used for callback functionality between the Controller and the different Frames.
 * @author Tor Stenfeldt
 * @version 2.0
 */
@SuppressWarnings("unused")
public interface Callback {
    boolean addIngredient(Ingredient ingredient);
    Ingredient getIngredient(String ingredient);
    Ingredient[] getIngredients();
    int getNumIngredients(String ingredient);
    boolean incrementIngredient(String ingredient);
    boolean incrementIngredient(Ingredient ingredient);
    boolean incrementIngredient(String ingredient, int value);
    boolean incrementIngredient(Ingredient ingredient, int value);
    boolean decrementIngredient(String ingredient);
    boolean decrementIngredient(Ingredient ingredient);
    boolean decrementIngredient(String ingredient, int value);
    boolean decrementIngredient(Ingredient ingredient, int value);
    boolean removeIngredient(String ingredient);

    boolean addFood(Food food);
    Food getFood(String food);
    Food[] getFood();
    int getNumFood(String food);
    boolean increaseFood(String food);
    boolean increaseFood(Food food);
    boolean decreaseFood(String food);
    boolean decreaseFood(Food food);
    boolean removeFood(String food);

    boolean addSupplier (Supplier supplier);
    boolean addSupplier (String name);
    Supplier getSupplier(String supplier);
    ArrayList<Supplier> getSuppliers();
    int getNumSuppliers();
    boolean removeSupplier (String name);

    /* Testing purposes */
    boolean addProductTest(Product product);
    Product getProductTest(String product);
    Product[] getProductTest();
    int getNumProductTest(String product);
    boolean increaseProductTest(String product);
    boolean increaseProductTest(Product product);
    boolean decreaseProductTest(String product);
    boolean decreaseProductTest(Product  product);
    boolean removeProductTest(String name);
}
