
package database.gui;

import database.DatabaseConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class ProductFrame extends JFrame {

    private DefaultTableModel model;
    private int customerId;

    public ProductFrame(int customerId) {
        this.customerId = customerId;

        setTitle("E-Commerce - Products");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel(
                "Available Products", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));

        model = new DefaultTableModel(
                new String[]{
                    "ID", "Name", "Price",
                    "Discount", "Stock", "Category"
                }, 0);

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        JButton refreshButton =
                new JButton("Refresh Products");

        JButton cartButton =
                new JButton("Open Shopping Cart");

        refreshButton.addActionListener(
                e -> loadProducts());

        cartButton.addActionListener(
                e -> new CartFrame(customerId));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);
        buttonPanel.add(cartButton);

        add(title, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loadProducts();

        setVisible(true);
    }

    private void loadProducts() {

        model.setRowCount(0);

        String sql = "SELECT product_id, name, price, discount, "
                   + "stock, category FROM Products";

        try (Connection con =
                     DatabaseConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getDouble("discount"),
                    rs.getInt("stock"),
                    rs.getString("category")
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load products: "
                    + e.getMessage());
        }
    }
}
