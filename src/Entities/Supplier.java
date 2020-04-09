package Entities;

/**
 * An entity used for containing the different suppliers and their contact information.
 * @author Tor Stenfeldt
 * @version 1.0
 */
public class Supplier {
    private String name;
    private String date;

    public Supplier() {
        this.name = "";
    }

    public Supplier(Supplier supplier) {
        this.name = supplier.getName();
        this.date = supplier.getDate();
    }

    public Supplier(String name) {
        this.name = name;
    }

    public Supplier(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
