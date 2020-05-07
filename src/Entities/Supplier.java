package Entities;

import java.io.Serializable;

/**
 * Entity class for suppliers to store contact information.
 * (NOTE :  this is a new class to match the suppliers needs and to fit into the system)
 * @author Paul Moustakas
 * @version 2.0
 */
public class Supplier implements Serializable {
    private String supplierName;
    private String category;
    private String email;
    private String phone;


    public Supplier () {
        supplierName    = "";
        category        = "";
        email           = "";
        phone           = "";
    }

    public Supplier(String supplierName, String category, String email, String phone) {
        this.supplierName   = supplierName;
        this.category       = category;
        this.email          = email;
        this.phone          = phone;
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

    public String toString() {
        return "Name: " + supplierName + "\n" +
                "Category: " + category + "\n" +
                "Email: " + email + "\n" +
                "Phone: " + phone;
    }
}
