package com.inventory.ui;
import com.inventory.ui.InvoicePanel;
import com.inventory.report.SalesReportPanel;
import com.inventory.ui.StockPanel;



import javax.swing.*;
import java.awt.Color;

public class MainDashboard extends JFrame {

    public MainDashboard() {
        System.out.println("MAIN DASHBOARD LOADED");

        setTitle("Inventory Management System");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        // Product tab (REAL PANEL)
        tabs.add("Products", new ProductPanel());

        // Placeholder tabs
        tabs.add("Stock History", new StockHistoryPanel());
        tabs.add("Stock", new StockPanel());
        tabs.add("Suppliers", new JPanel());
        tabs.add("Invoice", new InvoicePanel());
        tabs.add("Reports", new SalesReportPanel());

        add(tabs);

        setVisible(true);
    }
}