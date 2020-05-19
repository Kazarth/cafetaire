package code.control;

import code.entities.*;

import java.util.ArrayList;

/**
 * Used for callback functionality between the Controller and the different Frames.
 * @author Tor Stenfeldt
 * @version 4.0
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

    boolean addProduct(Product product);
    Product getProduct(String product);
    Product[] getProducts();
    int getNumProduct(String product);
    boolean incrementProduct(String product);
    boolean incrementProduct(Product product);
    boolean decrementProduct(String product);
    boolean decrementProduct(Product product);
    boolean removeProduct(String product);

    boolean addRecipe(Recipe recipe);
    Recipe getRecipe(String recipe);
    Recipe[] getRecipes();
    boolean removeRecipe(String recipe);

    boolean addSupplier (Supplier supplier);
    boolean addSupplier (String name);
    Supplier getSupplier(String supplier);
    ArrayList<Supplier> getSuppliers();
    int getNumSuppliers();
    boolean removeSupplier (String name);

    void setupPane();
}
