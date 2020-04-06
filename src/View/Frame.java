package View;

import Control.Callback;
import Entities.Food;
import Entities.Ingredient;

/**
 * The abstract class each Frame is built around. Contains the methods used by its children.
 */
public abstract class Frame extends Thread {
    private Callback callback;

    public Frame(Callback callback) {
        this.callback = callback;
    }

    public void increaseValue(int i, String s) {
        switch (i) {
            case 1:
                if (callback.getIngredient(s) != null) {
                    callback.increaseIngredient(s);
                } else {
                    callback.addIngredient(new Ingredient(s));
                }
                break;
            case 2:
                if (callback.getFood(s) != null) {
                    callback.increaseFood(s);
                } else {
                    callback.addFood(new Food(s));
                }
                break;
            case 3:
                if (callback.getSupplier(s) == null) {
                    callback.addSupplier(s);
                }
                break;
            default:
                break;  // You shouldn't be here.
        }
    }

    public boolean decreaseValue(int i, String s) {
        return callback.decreaseValue(i, s);
    }

    public Object getValue(int i, String s) {
        return callback.getValue(i, s);
    }
}
