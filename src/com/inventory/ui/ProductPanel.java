package com.inventory.ui;

import javax.swing.*;
import java.awt.*;

public class ProductPanel extends JPanel {

    public ProductPanel() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("PRODUCT MANAGEMENT", JLabel.CENTER);
        add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(4, 2));

        form.add(new JLabel("Name:"));
        JTextField nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Price:"));
        JTextField priceField = new JTextField();
        form.add(priceField);

        form.add(new JLabel("Quantity:"));
        JTextField qtyField = new JTextField();
        form.add(qtyField);

        JButton addBtn = new JButton("Add Product");
        form.add(addBtn);

        add(form, BorderLayout.CENTER);
    }
}