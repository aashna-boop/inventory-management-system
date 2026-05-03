package com.inventory.ui;

import com.inventory.db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StockHistoryPanel extends JPanel {

    JTable table;
    DefaultTableModel model;

    public StockHistoryPanel() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("STOCK HISTORY", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"Product ID", "Quantity", "Last Updated"}, 0
        );

        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        // ===== BUTTON =====
        JButton loadBtn = new JButton("Load History");
        add(loadBtn, BorderLayout.SOUTH);

        loadBtn.addActionListener(e -> loadHistory());
    }

    // ================= LOAD HISTORY =================
    private void loadHistory() {

        model.setRowCount(0);

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT product_id, quantity, last_updated FROM stock ORDER BY last_updated DESC";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getTimestamp("last_updated")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}