package com.inventory.model;

import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private int id;
    private double totalAmount;
    private List<InvoiceItem> items;

    public Invoice() {
        items = new ArrayList<>();
    }

    public Invoice(int id, double totalAmount) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.items = new ArrayList<>();
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void addItem(InvoiceItem item) {
        this.items.add(item);
    }
}