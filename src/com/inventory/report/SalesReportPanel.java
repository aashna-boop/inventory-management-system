package com.inventory.report;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SalesReportPanel extends JPanel {

    DefaultTableModel model;
    JTable table;

    ReportService service = new ReportService();

    public SalesReportPanel() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("REPORTS", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // ===== BUTTON PANEL =====
        JPanel topPanel = new JPanel();

        JButton salesBtn = new JButton("Sales Report");
        JButton lowStockBtn = new JButton("Low Stock");
        JButton topBtn = new JButton("Top Products");

        topPanel.add(salesBtn);
        topPanel.add(lowStockBtn);
        topPanel.add(topBtn);

        add(topPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel();
        table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ===== BUTTON ACTIONS =====
        salesBtn.addActionListener(e -> loadSales());
        lowStockBtn.addActionListener(e -> loadLowStock());
        topBtn.addActionListener(e -> loadTopProducts());
    }

    // ===== SALES =====
    private void loadSales() {

        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Invoice ID");
        model.addColumn("Product Name");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Total");

        List<Object[]> data = service.getSalesReport();

        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    // ===== LOW STOCK =====
    private void loadLowStock() {

        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Product ID");
        model.addColumn("Name");
        model.addColumn("Quantity");

        List<Object[]> data = service.getLowStockItems();

        for (Object[] row : data) {
            model.addRow(row);
        }
    }

    // ===== TOP PRODUCTS =====
    private void loadTopProducts() {

        model.setRowCount(0);
        model.setColumnCount(0);

        model.addColumn("Product ID");
        model.addColumn("Total Sold");

        List<Object[]> data = service.getTopProducts();

        for (Object[] row : data) {
            model.addRow(row);
        }
    }
}