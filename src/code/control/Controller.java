package code.control;

import code.entities.*;
import code.extra.ColourTxT;
import code.model.Database;
import code.view.panes.MainPane;

import java.util.ArrayList;

/**
 * Instantiates the database and the mainFrame and holds the class handling callback between them.
 * @author Tor Stenfeldt
 * @version 2.0
 */
public class Controller {
    private Database database;
    private MainPane mainPane;
    private SaveLoad saveLoad;
    private ColourTxT colourTxT = new ColourTxT();

    public Controller() {
        this.database = new Database();
        this.saveLoad = new SaveLoad(database);

        if (saveLoad.hasData()) {
            database = saveLoad.loadData();
        }

        // TODO: must comment away for testing purposes?
        this.mainPane = new MainPane(new CallbackHandler());
    }

    /**
     * Method to handle unsaved data on application EXIT
     */
    public void handleClosing () {
        saveLoad.save();
        System.err.println("Confirmed save and now Exit");
        System.exit(0);
    }

    public MainPane getMainPane() {
        return mainPane;
    }

    public CallbackHandler getCallback() {
        return new CallbackHandler();
    }

    /**
     * Implements Callback in order to receive data from Frames and send to the Database.
     */
    private class CallbackHandler implements Callback {
        @Override
        public boolean addIngredient(Ingredient ingredient) {
            saveLoad.setEdited();
            return database.addIngredient(ingredient);
        }

        @Override
        public Ingredient getIngredient(String ingredient) {
            return database.getIngredient(ingredient);
        }

        @Override
        public Ingredient[] getIngredients() {
            return database.getIngredients();
        }

        @Override
        public int getNumIngredients(String ingredient) {
            return database.getNumIngredients(ingredient);
        }

        @Override
        public boolean incrementIngredient(String ingredient) {
            return database.incrementIngredient(ingredient);
        }

        @Override
        public boolean incrementIngredient(Ingredient ingredient) {
            return database.incrementIngredient(ingredient);
        }

        @Override
        public boolean incrementIngredient(String ingredient, int value) {
            return database.incrementIngredient(ingredient, value);
        }

        @Override
        public boolean incrementIngredient(Ingredient ingredient, int value) {
            return database.incrementIngredient(ingredient, value);
        }

        @Override
        public boolean decrementIngredient(String ingredient) {
            return database.decrementIngredient(ingredient);
        }

        @Override
        public boolean decrementIngredient(Ingredient ingredient) {
            return database.decrementIngredient(ingredient);
        }

        @Override
        public boolean decrementIngredient(String ingredient, int value) {
            return database.decrementIngredient(ingredient, value);
        }

        @Override
        public boolean decrementIngredient(Ingredient ingredient, int value) {
            return database.decrementIngredient(ingredient, value);
        }

        @Override
        public boolean removeIngredient(String ingredient) {
            saveLoad.setEdited();
            return database.removeIngredient(ingredient);
        }

        @Override
        public boolean addFood(Food food) {
            saveLoad.setEdited();
            return database.addFood(food);
        }

        @Override
        public Food getFood(String food) {
            return database.getFood(food);
        }

        @Override
        public Food[] getFood() {
            return database.getFood();
        }

        @Override
        public int getNumFood(String food) {
            return database.getNumFood(food);
        }

        @Override
        public boolean increaseFood(String food) {
            return database.increaseFood(food);
        }

        @Override
        public boolean increaseFood(Food food) {
            return database.increaseFood(food);
        }

        @Override
        public boolean decreaseFood(String food) {
            return database.decreaseFood(food);
        }

        @Override
        public boolean decreaseFood(Food food) {
            return database.decreaseFood(food);
        }

        @Override
        public boolean removeFood(String food) {
            saveLoad.setEdited();
            return database.removeFood(food);
        }

        @Override
        public boolean addSupplier(Supplier supplier) {
            saveLoad.setEdited();
            return database.addSupplier(supplier);
        }

        @Override
        public boolean addSupplier(String name) {
            saveLoad.setEdited();
            return database.addSupplier(name);
        }

        @Override
        public Supplier getSupplier(String supplier) {
            return database.getSupplier(supplier);
        }

        @Override
        public ArrayList<Supplier> getSuppliers() {
            return database.getSuppliers();
        }

        @Override
        public int getNumSuppliers() {
            return database.getNumSuppliers();
        }

        @Override
        public boolean removeSupplier(String name) {
            saveLoad.setEdited();
            return database.removeSupplier(name);
        }

        /*Testing purpose Product */
        @Override
        public boolean addProductTest(Product product) {
            saveLoad.setEdited();
            System.out.println(colourTxT.NEONGREEN() + "Controller received:\n" +
                    "Name: " + product.getName() + "\n" +
                    "Category: " + product.getCategory() + "\n" +
                    "Amount: " + product.getQuantity() + colourTxT.RESET());
            return database.addProductTest(product);
        }

        @Override
        public Product getProductTest(String product) {
            return database.getProductTest(product);
        }

        @Override
        public Product[] getProductTest() {
            return database.getProductTest();
        }

        @Override
        public int getNumProductTest(String product) {
            return database.getNumProductTest(product);
        }

        @Override
        public boolean increaseProductTest(String product) {
            return database.increaseProductTest(product);
        }

        @Override
        public boolean increaseProductTest(Product product) {
            return database.increaseProductTest(product);
        }

        @Override
        public boolean decreaseProductTest(String product) {
            return database.decreaseProductTest(product);
        }

        @Override
        public boolean decreaseProductTest(Product product) {
            return database.decreaseProductTest(product);
        }


        @Override
        public boolean removeProductTest(String name) {
            saveLoad.setEdited();
            return database.removeProductTest(name);
        }
    }
}
