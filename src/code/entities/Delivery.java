package code.entities;

public class Delivery<T> {
    private int day, hour;
    private Supplier supplier;
    private T object;

    public Delivery(int day, int hour, Supplier supplier, T object) {
        this.day = day;
        this.hour = hour;
        this.supplier = supplier;
        this.object = object;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public T getObject() {
        return object;
    }

    @Override
    public String toString() {
        return object + " from " + supplier.getName();
    }
}
