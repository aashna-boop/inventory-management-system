package com.inventory.ui;

import com.inventory.db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class SupplierPanel extends JPanel {

    JTable table;
    DefaultTableModel model;

    public SupplierPanel() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("SUPPLIER MANAGEMENT", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);

        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Contact", "Address"}, 0
        );

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panel = new JPanel();

        JButton loadBtn = new JButton("Load");
        JButton addBtn = new JButton("Add");

        panel.add(loadBtn);
        panel.add(addBtn);

        add(panel, BorderLayout.SOUTH);

        loadBtn.addActionListener(e -> loadSuppliers());
        addBtn.addActionListener(e -> new SupplierFormDialog(this));
    }

    private void loadSuppliers() {

        model.setRowCount(0);

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT * FROM suppliers";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("address")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        loadSuppliers();
    }
}