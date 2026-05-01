package com.inventory.ui;

import com.inventory.db.DBConnection;
import com.inventory.model.Invoice;
import com.inventory.model.InvoiceItem;

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

    double grandTotal = 0;
    JLabel totalLabel = new JLabel("Total: 0");

    // MODEL OBJECT
    Invoice invoice = new Invoice();

    public InvoicePanel() {

        setLayout(new BorderLayout());

        // ================= TITLE =================
        JLabel title = new JLabel("INVOICE SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // ================= FORM =================
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        form.add(new JLabel("Product ID:"));
        form.add(productIdField);

        form.add(new JLabel("Quantity:"));
        form.add(quantityField);

        JButton addBtn = new JButton("Add to Invoice");
        form.add(addBtn);

        form.add(totalLabel);

        add(form, BorderLayout.NORTH);

        // ================= TABLE =================
        model = new DefaultTableModel();
        model.addColumn("Product ID");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Total");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ================= ADD ITEM =================
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

                    // CREATE MODEL OBJECT
                    InvoiceItem item = new InvoiceItem(pid, qty, price);

                    // ADD TO INVOICE OBJECT
                    invoice.addItem(item);

                    // ADD TO TABLE (UI)
                    model.addRow(new Object[]{
                            item.getProductId(),
                            item.getQuantity(),
                            item.getPrice(),
                            item.getTotal()
                    });

                    // UPDATE TOTAL
                    grandTotal += item.getTotal();
                    totalLabel.setText("Total: " + grandTotal);

                    // CLEAR INPUTS
                    productIdField.setText("");
                    quantityField.setText("");

                } else {
                    JOptionPane.showMessageDialog(this, "Product not found!");
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding item!");
            }
        });
    }
}