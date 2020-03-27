package View;

import Control.Callback;

/**
 * Contains the different user views through different Frames. Acts like the underlying mainframe.
 */
public class MainFrame extends Thread {
    private Frame[] frames;
    private Frame current;

    public MainFrame(Callback callback) {

        frames = new Frame[]{
                new Cashier(callback)
        };

        current = frames[0];
        current.start();
    }
}
