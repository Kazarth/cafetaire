package code.control;

import code.model.Database;

import java.io.*;

/**
 * Class to save and load the database.
 * The class invokes the save method should any new data be added
 * before closing - force save to avoid data loss.
 * @author Paul Moustakas, Tor Stenfeldt
 * @version 1.1
 */
public class SaveLoad {
    private boolean isSaved;
    private Database data;

    public SaveLoad (Database data) {
        this.data = data;
    }

    public void setEdited () {
        this.isSaved = false;
        System.out.println("Edited Change");
    }

    /**
     * Method checks if the current data is saved,
     * otherwise enforce saveData.
     */
    public void save () {
        if (!isSaved) {
            System.err.println("Checking if already saved...");
            saveData();
        }
    }

    /**
     * Method saves data to fil.
     */
    private void saveData () {
        System.err.println("Saving...");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/resources/save.ser"))) {
            oos.writeObject(data);
            isSaved = true;

        } catch (IOException e) {e.printStackTrace();}
    }

    /**
     * Method loads data from file
     * @return
     */
    public Database loadData () {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/resources/save.ser"))) {
            data = (Database)ois.readObject();
            isSaved = true;
        } catch (IOException | ClassNotFoundException e ) {
            e.printStackTrace();
        }
        return data;
    }

    public boolean hasData() {
        try (ObjectInputStream ignored = new ObjectInputStream((new FileInputStream("src/resources/save.ser")))) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
