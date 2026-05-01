package com.inventory.ui;

import com.inventory.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;

public class InvoicePanel extends JPanel {

    JTextField productIdField = new JTextField();
    JTextField quantityField = new JTextField();

    DefaultTableModel model;
    JTable table;

    public InvoicePanel() {

        setLayout(new BorderLayout());

        // ================= TITLE =================
        JLabel title = new JLabel("INVOICE SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // ================= FORM =================
        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        form.add(new JLabel("Product ID:"));
        form.add(productIdField);

        form.add(new JLabel("Quantity:"));
        form.add(quantityField);

        JButton addBtn = new JButton("Add to Invoice");
        form.add(addBtn);

        add(form, BorderLayout.CENTER);

        // ================= TABLE =================
        model = new DefaultTableModel();

        model.addColumn("Product ID");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Total");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.SOUTH);

        // ================= BUTTON ACTION =================
        addBtn.addActionListener(e -> {

            try {
                Connection conn = DBConnection.getConnection();

                int pid = Integer.parseInt(productIdField.getText());
                int qty = Integer.parseInt(quantityField.getText());

                String sql = "SELECT price FROM products WHERE id = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setInt(1, pid);

                ResultSet rs = pst.executeQuery();

                if (rs.next()) {

                    double price = rs.getDouble("price");
                    double total = price * qty;

                    model.addRow(new Object[]{
                            pid,
                            qty,
                            price,
                            total
                    });

                    // clear input fields
                    productIdField.setText("");
                    quantityField.setText("");

                } else {
                    JOptionPane.showMessageDialog(this, "Product not found!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error generating invoice!");
            }
        });
    }
}