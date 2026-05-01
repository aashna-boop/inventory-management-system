package com.inventory.ui;

import com.inventory.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class InvoicePanel extends JPanel {

    JTextField productIdField = new JTextField();
    JTextField quantityField = new JTextField();
    JLabel resultLabel = new JLabel("Total: 0");

    public InvoicePanel() {

        setLayout(new BorderLayout());

        // ===== TITLE =====
        JLabel title = new JLabel("INVOICE SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel form = new JPanel();
        form.setLayout(new GridLayout(5, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        form.add(new JLabel("Product ID:"));
        form.add(productIdField);

        form.add(new JLabel("Quantity:"));
        form.add(quantityField);

        JButton calcBtn = new JButton("Calculate Total");
        form.add(calcBtn);

        form.add(resultLabel);

        add(form, BorderLayout.CENTER);

        // ===== BUTTON ACTION =====
        calcBtn.addActionListener(e -> {

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

                    resultLabel.setText("Total: ₹ " + total);
                } else {
                    resultLabel.setText("Product not found!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                resultLabel.setText("Error calculating total");
            }
        });
    }
}