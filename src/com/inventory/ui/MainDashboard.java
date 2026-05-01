package com.inventory.ui;

import javax.swing.*;
import java.awt.Color;

public class MainDashboard extends JFrame {

    public MainDashboard() {

        setTitle("Inventory Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // Product tab (REAL PANEL)
        tabs.add("Products", new ProductPanel());

        // Placeholder tabs
        tabs.add("Stock", new JPanel());
        tabs.add("Suppliers", new JPanel());
        tabs.add("Invoice", new JPanel());
        tabs.add("Reports", new JPanel());

        add(tabs);

        setVisible(true);
    }
}