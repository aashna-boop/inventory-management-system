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

    Invoice invoice = new Invoice();

    public InvoicePanel() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("INVOICE SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

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

        // TABLE
        model = new DefaultTableModel();
        model.addColumn("Product ID");
        model.addColumn("Quantity");
        model.addColumn("Price");
        model.addColumn("Total");

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ================= THREADING IMPLEMENTATION =================
        addBtn.addActionListener(e -> {

            new SwingWorker<Void, Void>() {

                @Override
                protected Void doInBackground() {

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

                            InvoiceItem item = new InvoiceItem(pid, qty, price);
                            invoice.addItem(item);

                            // UPDATE UI SAFELY
                            SwingUtilities.invokeLater(() -> {

                                model.addRow(new Object[]{
                                        item.getProductId(),
                                        item.getQuantity(),
                                        item.getPrice(),
                                        item.getTotal()
                                });

                                grandTotal += item.getTotal();
                                totalLabel.setText("Total: " + grandTotal);

                                productIdField.setText("");
                                quantityField.setText("");
                            });

                        } else {
                            SwingUtilities.invokeLater(() ->
                                    JOptionPane.showMessageDialog(null, "Product not found!")
                            );
                        }

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        SwingUtilities.invokeLater(() ->
                                JOptionPane.showMessageDialog(null, "Error processing request!")
                        );
                    }

                    return null;
                }

                @Override
                protected void done() {
                    System.out.println("Thread task completed");
                }

            }.execute();
        });
    }
}