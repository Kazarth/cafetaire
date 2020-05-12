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
        public boolean addProduct(Product product) {
            saveLoad.setEdited();
            return database.addProduct(product);
        }

        @Override
        public Product getProduct(String product) {
            return database.getProduct(product);
        }

        @Override
        public Product[] getProduct() {
            return database.getProducts();
        }

        public int getNumProduct(String product) {
            return database.getNumProduct(product);
        }

        @Override
        public boolean incrementProduct(String product) {
            return database.incrementProduct(product);
        }

        public boolean incrementProduct(Product product) {
            return database.incrementProduct(product);
        }

        @Override
        public boolean decrementProduct(String product) {
            return database.decrementProduct(product);
        }

        @Override
        public boolean decrementProduct(Product product) {
            return database.decrementProduct(product);
        }

        @Override
        public boolean removeProduct(String product) {
            saveLoad.setEdited();
            return database.removeProduct(product);
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

        @Override
        public void setupPane() {
            // get centerPane
            // remove current
            // add new
        }
    }
}
