package Control;

import Model.Database;
import View.MainFrame;

/**
 * Instantiates the database and the mainFrame and holds the class handling callback between them.
 */
public class Controller {
    private Database database;
    private MainFrame mainFrame;

    public Controller() {
        this.database = new Database();
        this.mainFrame = new MainFrame(new CallbackHandler());
    }

    /**
     * Implements Callback in order to receive data from Frames and send to the Database.
     */
    private class CallbackHandler implements Callback {
        public void increaseValue(int i, String s) {
            if (i == 0) {
                database.addIngredient(s);
            }

            if (i == 1) {
                database.addFood(s);
            }

            if (i == 2) {
                database.addSupplier(s);
            }
        }

        public boolean decreaseValue(int i, String s) {
            if (i == 0) {
                return database.removeIngredient(s);
            }

            if (i == 1) {
                return database.removeFood(s);
            }

            if (i == 2) {
                return database.removeSupplier(s);
            }

            return false;
        }

        public Object getValue(int i, String s) {
            if (i == 0) {
                return database.getIngredient(s);
            }

            if (i == 1) {
                return database.getFood(s);
            }

            if (i == 2) {
                return database.getSupplier(s);
            }

            return null;
        }
    }
}
