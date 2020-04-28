package Entities;

/**
 * An entity used for containing the different suppliers and their contact information.
 * @author Tor Stenfeldt, Paul Moustakas
 * @version 2.0
 */
public class Supplier {
    private String  supplierName;
    private String  category;
    private String  email;
    private String  phone;

    public Supplier(String supplierName, String category, String email, String phone) {
        this.supplierName   = supplierName;
        this.category       = category;
        this.email          = email;
        this.phone          = phone;
    }

    public Supplier () {}

    public Supplier(Supplier supplier) {
        this.supplierName = supplier.getName();
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Supplier(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getName() {
        return supplierName;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
