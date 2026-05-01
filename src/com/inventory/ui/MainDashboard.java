
package com.inventory.ui;
import javax.swing.*;

//creating class
public class MainDashboard extends JFrame {
    //constructur
    public MainDashboard(){
        //window settings
        setTitle("Inventory Management System");
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centre the window

        //tabbed pane
        JTabbedPane tabs = new JTabbedPane();

        //add tabs empty for now
        tabs.add("Products", new JPanel());
        tabs.add("Stock", new JPanel());
        tabs.add("Suppliers", new JPanel());
        tabs.add("Invoice", new JPanel());
        tabs.add("Reports", new JPanel());

        //add tabs to frame
        add(tabs);

        //make visible
        setVisible(true);
    }
    
}