package code.control;

import code.entities.*;
import code.model.Database;
import code.view.panes.MainPane;

import java.util.ArrayList;

/**
 * Instantiates the database and the mainFrame and holds the class handling callback between them.
 * @author Tor Stenfeldt
 * @version 4.0
 */
public class Controller implements Callback {
    private Database database;
    private MainPane mainPane;
    private SaveLoad saveLoad;

    public Controller() {
        this.database = new Database();
        this.saveLoad = new SaveLoad(database);

        if (saveLoad.hasData()) {
            // TODO: must comment away for testing purposes
            database = saveLoad.loadData();
        }

        // TODO: must comment away for testing purposes
        this.mainPane = new MainPane(this);
    }

    /**
     * Method to handle unsaved data on application EXIT
     */
    public void handleClosing() {
        saveLoad.save();
        System.err.println("Confirmed save and now Exit");
        System.exit(0);
    }

    public MainPane getMainPane() {
        return mainPane;
    }

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
    public Product[] getProducts() {
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
    public boolean addRecipe(Recipe recipe) {
        saveLoad.setEdited();
        return database.addRecipe(recipe);
    }

    @Override
    public Recipe getRecipe(String recipe) {
        return database.getRecipe(recipe);
    }

    @Override
    public Recipe[] getRecipes() {
        return database.getRecipes();
    }

    @Override
    public boolean removeRecipe(String recipe) {
        return database.removeRecipe(recipe);
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

    public void catchSafeState () {
        saveLoad.setEdited();
    }

    @Override
    public void setupPane() {
        // get centerPane
        // remove current
        // add new
    }
}
