package com.inventory;

import com.inventory.ui.MainDashboard;
import com.inventory.db.DBConnection;

public class MainApp {
    public static void main(String[] args) {

        System.out.println("App starting...");

        DBConnection.getConnection();

        new MainDashboard(); // ✔ fixed
    }
}