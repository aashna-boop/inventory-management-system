package com.inventory.model;

// ===== INTERFACE =====
interface Displayable {
    String getDisplayInfo();
}

// ===== MODEL CLASS =====
public class Supplier extends BaseEntity implements Displayable {

    private int id;
    private String name;
    private String contact;
    private String address;

    // ===== CONSTRUCTOR =====
    public Supplier(int id, String name, String contact, String address) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    // ===== GETTERS =====
    public int getId() { return id; }
    public String getName() { return name; }
    public String getContact() { return contact; }
    public String getAddress() { return address; }

    // ===== OVERRIDE METHOD (INTERFACE) =====
    @Override
    public String getDisplayInfo() {
        return name + " (" + contact + ")";
    }
}