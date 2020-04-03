package View;

import Control.Callback;

/**
 * The abstract class each Frame is built around. Contains the methods used by its children.
 */
public abstract class Frame extends Thread {
    private Callback callback;

    public Frame(Callback callback) {
        this.callback = callback;
    }

    public void increaseValue(int i, String s) {
        callback.increaseValue(i, s);
    }

    public boolean decreaseValue(int i, String s) {
        return callback.decreaseValue(i, s);
    }

    public Object getValue(int i, String s) {
        return callback.getValue(i, s);
    }
}
