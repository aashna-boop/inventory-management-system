package com.inventory.model;

public class StockItem {

    private int productId;
    private int quantity;

    // ================= CONSTRUCTOR 1 =================
    public StockItem(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // ================= CONSTRUCTOR OVERLOADING =================
    public StockItem(int productId) {
        this.productId = productId;
        this.quantity = 0;
    }

    // ================= GETTERS =================
    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    // ================= SETTER =================
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // ================= METHOD =================
    public void addStock(int qty) {
        this.quantity += qty;
    }

    // ================= METHOD OVERLOADING =================
    public void addStock(int qty, boolean isReturn) {
        if (isReturn) {
            this.quantity += qty; // return increases stock
        } else {
            this.quantity -= qty; // sale reduces stock
        }
    }
}