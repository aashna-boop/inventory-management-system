package com.inventory.ui;

import com.inventory.db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StockPanel extends JPanel {

    JTable table;
    DefaultTableModel model;

    public StockPanel() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("STOCK MANAGEMENT", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"Product ID", "Name", "Quantity"}, 0
        );

        table = new JTable(model);

        // ===== OVERRIDING (cell rendering for low stock) =====
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {

                Component c = super.getTableCellRendererComponent(
                        table, value, isSelected, hasFocus, row, column);

                int qty = (int) table.getModel().getValueAt(row, 2);

                if (qty < 5) {
                    c.setBackground(Color.PINK); // LOW STOCK highlight
                } else {
                    c.setBackground(Color.WHITE);
                }

                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ===== BUTTON PANEL =====
        JPanel panel = new JPanel();

        JButton loadBtn = new JButton("Load Stock");
        JButton updateBtn = new JButton("Update Quantity");

        panel.add(loadBtn);
        panel.add(updateBtn);

        add(panel, BorderLayout.SOUTH);

        // ===== ACTIONS =====

        loadBtn.addActionListener(e -> loadStock());

        updateBtn.addActionListener(e -> updateStock());
    }

    // ================= LOAD =================
    private void loadStock() {

        model.setRowCount(0);

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT id, name, quantity FROM products";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ================= UPDATE =================
    private void updateStock() {

        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a row first!");
            return;
        }

        int productId = (int) model.getValueAt(row, 0);

        String newQtyStr = JOptionPane.showInputDialog("Enter new quantity:");

        try {
            int newQty = Integer.parseInt(newQtyStr);

            Connection conn = DBConnection.getConnection();

            String sql = "UPDATE products SET quantity = ? WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setInt(1, newQty);
            pst.setInt(2, productId);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Stock updated!");

            loadStock(); // refresh

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid input!");
        }
    }
}