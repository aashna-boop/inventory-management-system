package com.inventory.ui;

import com.inventory.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class ProductPanel extends JPanel {

    // Form fields
    JTextField nameField = new JTextField();
    JTextField priceField = new JTextField();
    JTextField qtyField = new JTextField();

    // Table
    DefaultTableModel model;
    JTable table;

    public ProductPanel() {

        setLayout(new BorderLayout());

        // ================= TITLE =================
        JLabel title = new JLabel("PRODUCT MANAGEMENT", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        // ================= FORM =================
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));

        form.add(new JLabel("Name:"));
        form.add(nameField);

        form.add(new JLabel("Price:"));
        form.add(priceField);

        form.add(new JLabel("Quantity:"));
        form.add(qtyField);

        JButton addBtn = new JButton("Add Product");
        form.add(addBtn);

        // ================= TABLE =================
        model = new DefaultTableModel();
        table = new JTable(model);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Price");
        model.addColumn("Quantity");

        JScrollPane scrollPane = new JScrollPane(table);

        // ================= LAYOUT FIX =================
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(form, BorderLayout.CENTER);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ================= LOAD DATA =================
        loadProducts();

        // ================= BUTTON ACTION =================
        addBtn.addActionListener(e -> {

            try {
                Connection conn = DBConnection.getConnection();

                String sql = "INSERT INTO products(name, price, quantity) VALUES (?, ?, ?)";

                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, nameField.getText());
                pst.setDouble(2, Double.parseDouble(priceField.getText()));
                pst.setInt(3, Integer.parseInt(qtyField.getText()));

                pst.executeUpdate();

                JOptionPane.showMessageDialog(this, "Product Added Successfully!");

                loadProducts(); // refresh table

                // clear fields
                nameField.setText("");
                priceField.setText("");
                qtyField.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }

    // ================= LOAD DATA METHOD =================
    public void loadProducts() {

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM products";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}