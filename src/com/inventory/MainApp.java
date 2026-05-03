package com.inventory;

import com.inventory.ui.MainDashboard;
import com.inventory.db.DBConnection;


public class MainApp {

    public static void main(String[] args) {

        System.out.println("App starting...");

        try {
            DBConnection.getConnection();
            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        new MainDashboard();
    }
}