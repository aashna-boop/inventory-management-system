package com.inventory.ui;

import com.inventory.db.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class SupplierFormDialog extends JDialog {

    JTextField nameField, contactField, addressField;

    public SupplierFormDialog(SupplierPanel parent) {

        setTitle("Add Supplier");
        setSize(300, 250);
        setLayout(new GridLayout(4, 2));

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Contact:"));
        contactField = new JTextField();
        add(contactField);

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        JButton saveBtn = new JButton("Save");
        add(saveBtn);

        setVisible(true);

        saveBtn.addActionListener(e -> saveSupplier(parent));
    }

    private void saveSupplier(SupplierPanel parent) {

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO suppliers (name, contact, address) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);

            pst.setString(1, nameField.getText());
            pst.setString(2, contactField.getText());
            pst.setString(3, addressField.getText());

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Supplier added!");

            parent.refresh();
            dispose();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}