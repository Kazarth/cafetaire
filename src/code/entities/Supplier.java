package code.entities;

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
        this.supplierName    = "";
        this.category        = "";
        this.email           = "";
        this.phone           = "";
    }

    public Supplier(String supplierName, String category, String email, String phone) {
        this.supplierName   = supplierName;
        this.category       = category;
        this.email          = email;
        this.phone          = phone;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Supplier(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getName() {
        return this.supplierName;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return this.supplierName;
    }
}
