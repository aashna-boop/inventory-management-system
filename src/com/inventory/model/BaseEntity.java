package com.inventory.model;

public abstract class BaseEntity {

    protected int id;

    public int getId() {
        return id;
    }

    public abstract String getDetails();
}