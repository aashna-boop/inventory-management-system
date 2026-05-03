package com.inventory.report;

import com.inventory.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReportService {

    // ================= SALES REPORT =================
    public List<Object[]> getSalesReport() {

        List<Object[]> data = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT invoice_id, product_id, quantity, price, total FROM invoice_items";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                data.add(new Object[]{
                        rs.getInt("invoice_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        rs.getDouble("total")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // ================= LOW STOCK =================
    public List<Object[]> getLowStockItems() {

        List<Object[]> data = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT id, name, quantity FROM products WHERE quantity < 5";
            PreparedStatement pst = conn.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                data.add(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // ================= TOP PRODUCTS =================
    public List<Object[]> getTopProducts() {

        List<Object[]> data = new ArrayList<>();

        try {
            Connection conn = DBConnection.getConnection();

            String sql = "SELECT p.name, SUM(i.quantity) as total_sold " +
                        "FROM invoice_items i " +
                        "JOIN products p ON i.product_id = p.id " +
                        "GROUP BY p.name " +
                        "ORDER BY total_sold DESC " +
                        "LIMIT 5";

            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                data.add(new Object[]{
                        rs.getString("name"),
                        rs.getInt("total_sold")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}