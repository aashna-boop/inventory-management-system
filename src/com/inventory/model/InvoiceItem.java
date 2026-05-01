package com.inventory.model;

public class InvoiceItem {

    private int productId;
    private int quantity;
    private double price;
    private double total;

    public InvoiceItem() {}

    public InvoiceItem(int productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.total = price * quantity;
    }

    // getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.total = this.price * quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.total = this.price * this.quantity;
    }

    public double getTotal() {
        return total;
    }
}