package Control;

/**
 * Used for callback functionality between the Controller and the different Frames.
 */
public interface Callback {
    // TODO: add functionality to getAllValues?
    void increaseValue(int i, String s);
    boolean decreaseValue(int i, String s);
    Object getValue(int i, String s);
}
