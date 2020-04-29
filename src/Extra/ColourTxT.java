package Extra;

/**
 * This class support change of Text -and background colour used in the console.
 * Use the instantiated class to invoke colours of your choice.
 * See below instructions concerning how to reset colours.
 * @author Paul Moustakas
 * @version 1
 */
public class ColourTxT extends Thread {

    //Text colours
    public static final String RESET   =   "\u001B[0m";
    public static final String BLACK   =   "\u001B[30m";
    public static final String RED     =   "\u001B[31m";
    public static final String GREEN   =   "\u001B[32m";
    public static final String ORANGE  =   "\u001B[33m";
    public static final String BLUE    =   "\u001B[34m";
    public static final String PURPLE  =   "\u001B[35m";
    public static final String CYAN    =   "\u001B[36m";
    public static final String WHITE   =   "\u001B[37m";

    //Font
    public static final String FONT     =  "\u001B[3m"; //15
    public static final String FRAME    =  "\u001B[51"; //15

    //Custom
    public static final String BRIGHT    =   "\u001B[1m";
    public static final String REVERSE   =   "\u001B[7m";
    public static final String NEONGREEN =  "\u001B[92m";
    public static final String ENCIRCLED =  "\u001B[52m";

    //Background text colours
    public static final String BLACK_BACKGROUND    =   "\u001B[40m";
    public static final String RED_BACKGROUND      =   "\u001B[41m";
    public static final String GREEN_BACKGROUND    =   "\u001B[42m";
    public static final String YELLOW_BACKGROUND   =   "\u001B[43m";
    public static final String BLUE_BACKGROUND     =   "\u001B[44m";
    public static final String PURPLE_BACKGROUND   =   "\u001B[45m";
    public static final String CYAN_BACKGROUND     =   "\u001B[46m";
    public static final String WHITE_BACKGROUND    =   "\u001B[47m";



    /**
     * Resets the Text colour to original.
     * @return Original colour "\u001B[0m"
     */
    public String RESET () {
        return RESET;
    }



    public String NEONGREEN () {
        return NEONGREEN;
    }

    public String NEO () {
        return BRIGHT +  REVERSE + NEONGREEN + ENCIRCLED;
    }

    public String ITALIC () {
        return FONT;
    }

    public String REVERSE () {
        return REVERSE;
    }

    public String BRIGHT () {
        return BRIGHT;
    }

    public String BLACK () {
        return BLACK;
    }

    public String RED () {
        return RED;
    }

    public String GREEN () {
        return GREEN;
    }

    public String ORANGE () {
        return ORANGE;
    }

    public String BLUE () {
        return BLUE;
    }

    public String PURPLE () {
        return PURPLE;
    }

    public String CYAN () {
        return CYAN;
    }

    public String WHITE () {
        return WHITE;
    }


    //BackGrounds
    public String BACK_GREEN () {
        return GREEN_BACKGROUND;
    }

    public String BACK_RED () {
        return RED_BACKGROUND;
    }
}
