package Control;

import Entities.Ingredient;
import Entities.Supplier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TODO: to run the test you need to comment out the MainPane initialization from the Controller for some reason.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class TestSuite {
    private Callback callback;

    public TestSuite() {
        Controller controller = new Controller();
        this.callback = controller.getCallback();
    }

    @Test
    @DisplayName("Tries adding and getting an ingredient.")
    void addIngredient() {
        Ingredient ingredient = new Ingredient("Sugar");
        callback.addIngredient(ingredient);

        Ingredient storedIngredient = callback.getIngredient(ingredient.getType());
        assertEquals(storedIngredient, ingredient);
    }

    @Test
    @DisplayName("Adding, removing then checking for an ingredient.")
    void removeIngredient() {
        Ingredient ingredient = new Ingredient("Sugar");
        callback.addIngredient(ingredient);
        callback.removeIngredient(ingredient.getType());

        Ingredient storedIngredient = callback.getIngredient(ingredient.getType());
        assertNull(storedIngredient);
    }

    @Test
    @DisplayName("Tries removing a non-existent value")
    void removeIngredientFaulty() {
        assertFalse(callback.removeIngredient("Sugar"));
    }

    @Test
    @DisplayName("Adding then increasing the number of ingredients by one.")
    void increaseIngredient() {
        Ingredient ingredient = new Ingredient("Sugar");
        callback.addIngredient(ingredient);
        callback.increaseIngredient(ingredient.getType());

        int nIngredients = callback.getNumIngredients(ingredient.getType());
        assertEquals(nIngredients, 2);
    }

    @Test
    @DisplayName("Tries increasing a non-existent value")
    void increaseIngredientFaulty() {
        assertFalse(callback.increaseIngredient("Sugar"));
    }

    @Test
    @DisplayName("Adds, increases then decreases an ingredient.")
    void decreaseIngredient() {
        Ingredient ingredient = new Ingredient("Sugar");
        callback.addIngredient(ingredient);
        callback.increaseIngredient(ingredient.getType());
        callback.decreaseIngredient(ingredient.getType());

        int nIngredients = callback.getNumIngredients(ingredient.getType());
        assertEquals(nIngredients, 1);
    }

    @Test
    @DisplayName("Tries adding then getting a supplier.")
    void addSupplier() {
        Supplier supplier = new Supplier("Baerte Kvarn");
        callback.addSupplier(supplier);

        Supplier storedSupplier = callback.getSupplier(supplier.getName());
        assertEquals(storedSupplier, supplier);
    }

    @Test
    @DisplayName("Tries adding then getting the number of suppliers.")
    void getNumSuppliers() {
        Supplier supplier = new Supplier("Baerte Kvarn");
        callback.addSupplier(supplier);

        int nSuppliers = callback.getNumSuppliers();
        assertEquals(nSuppliers, 1);
    }

    @Test
    @DisplayName("Tries adding, removing then getting a supplier")
    void removeSupplier() {
        Supplier supplier = new Supplier("Baerte Kvarn");
        callback.addSupplier(supplier);
        callback.removeSupplier("Baerte Kvarn");

        Supplier storedSupplier = callback.getSupplier("Baerte Kvarn");
        assertNull(storedSupplier);
    }

    @Test
    @DisplayName("Tries removing a Supplier when none is present")
    void removeSupplierFaulty() {
        assertFalse(callback.removeSupplier("Baerte Kvarn"));
    }
}