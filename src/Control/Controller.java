package Control;

import Entities.*;
import Extra.ColourTxT;
import Model.Database;
import View.MainPane;

import javax.swing.*;
import java.io.FileNotFoundException;
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
        database = saveLoad.loadData();


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
        public boolean increaseIngredient(String ingredient) {
            return database.increaseIngredient(ingredient);
        }

        @Override
        public boolean increaseIngredient(Ingredient ingredient) {
            return database.increaseIngredient(ingredient);
        }

        @Override
        public boolean decreaseIngredient(String ingredient) {
            return database.decreaseIngredient(ingredient);
        }

        @Override
        public boolean decreaseIngredient(Ingredient ingredient) {
            return database.decreaseIngredient(ingredient);
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


        /* Testing purposes IngredientTest*/
        @Override
        public boolean addIngredientTest(IngredientTest ingredient) {
            saveLoad.setEdited();
            System.out.println(colourTxT.NEONGREEN() + "Controller received:\n" +
                    "Name: " + ingredient.getName() + "\n" +
                    "Category: " + ingredient.getCategory() + "\n" +
                    "Supplier: " + ingredient.getSupplier() + colourTxT.RESET());
            return database.addIngredientTest(ingredient);
        }

        @Override
        public IngredientTest getIngredientTest(String ingredient) {
            return database.getIngredientTest(ingredient);
        }

        @Override
        public IngredientTest[] getIngredientsTest() {
            return database.getIngredientsTest();
        }

        @Override
        public int getNumIngredientsTest(String ingredient) {
            return database.getNumIngredientsTest(ingredient);
        }

        @Override
        public boolean increaseIngredientTest(String ingredient) {
            return database.increaseIngredientTest(ingredient);
        }

        @Override
        public boolean increaseIngredientTest(IngredientTest ingredient) {
            System.out.println(colourTxT.NEONGREEN() + "Controller received increase request" + colourTxT.RESET());
            return database.increaseIngredientTest(ingredient);
        }

        @Override
        public boolean decreaseIngredientTest(String ingredient) {
            return database.decreaseIngredientTest(ingredient);
        }

        @Override
        public boolean decreaseIngredientTest(IngredientTest ingredient) {
            System.out.println(colourTxT.NEONGREEN() + "Controller received decrease request" + colourTxT.RESET());
            return database.decreaseIngredientTest(ingredient);
        }

        @Override
        public boolean removeIngredientTest(String ingredient) {
            saveLoad.setEdited();
            return database.removeIngredientTest(ingredient);
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
