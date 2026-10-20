
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    // Retrieve all products for the admin management table.
    public List<Object[]> getAllProducts() {
        List<Object[]> products = new ArrayList<Object[]>();

        String sql = "SELECT product_id, name, price, discount, stock, "
                   + "category, description, image_path "
                   + "FROM Products ORDER BY product_id";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                products.add(new Object[] {
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getDouble("discount"),
                    rs.getInt("stock"),
                    rs.getString("category"),
                    rs.getString("description"),
                    rs.getString("image_path")
                });
            }

        } catch (SQLException e) {
            System.out.println("Could not load products.");
            e.printStackTrace();
        }

        return products;
    }

    // Add a product with all its details.
    public boolean addProductDetails(
            int id,
            String name,
            double price,
            double discount,
            int stock,
            String category,
            String description,
            String imagePath) {

        String sql = "INSERT INTO Products "
                   + "(product_id, name, price, discount, stock, "
                   + "category, description, image_path) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, price);
            ps.setDouble(4, discount);
            ps.setInt(5, stock);
            ps.setString(6, category);
            ps.setString(7, description);
            ps.setString(8, imagePath);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                "Could not add product: " + e.getMessage());
            return false;
        }
    }

    // Update all editable product details.
    public boolean updateProductDetails(
            int id,
            String name,
            double price,
            double discount,
            int stock,
            String category,
            String description,
            String imagePath) {

        String sql = "UPDATE Products SET name = ?, price = ?, "
                   + "discount = ?, stock = ?, category = ?, "
                   + "description = ?, image_path = ? "
                   + "WHERE product_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setDouble(2, price);
            ps.setDouble(3, discount);
            ps.setInt(4, stock);
            ps.setString(5, category);
            ps.setString(6, description);
            ps.setString(7, imagePath);
            ps.setInt(8, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                "Could not update product: " + e.getMessage());
            return false;
        }
    }

    // Delete a product by ID.
    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM Products WHERE product_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println(
                "Could not delete product: " + e.getMessage());
            return false;
        }
    }

    // Original method retained for compatibility.
    public boolean addProduct(
            int id,
            String name,
            double price,
            double discount,
            int stock) {

        return addProductDetails(
            id, name, price, discount, stock,
            "General", "", "");
    }

    // Display products in the console.
    public void displayProducts() {
        for (Object[] p : getAllProducts()) {
            System.out.println(
                "Product ID: " + p[0]
                + "\nName: " + p[1]
                + "\nPrice: " + p[2]
                + "\nDiscount: " + p[3] + "%"
                + "\nStock: " + p[4]
                + "\n--------------------");
        }
    }

    // Search for a product by ID.
    public void searchProduct(int id) {
        String sql = "SELECT * FROM Products WHERE product_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println(
                        "Product ID: " + rs.getInt("product_id"));
                    System.out.println(
                        "Name: " + rs.getString("name"));
                    System.out.println(
                        "Price: " + rs.getDouble("price"));
                    System.out.println(
                        "Discount: " + rs.getDouble("discount") + "%");
                    System.out.println(
                        "Stock: " + rs.getInt("stock"));
                } else {
                    System.out.println("Product not found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Product search failed.");
            e.printStackTrace();
        }
    }

    // Update only price and stock (original method retained).
    public void updateProduct(int id, double price, int stock) {
        String sql = "UPDATE Products SET price = ?, stock = ? "
                   + "WHERE product_id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDouble(1, price);
            ps.setInt(2, stock);
            ps.setInt(3, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Product not found.");
            }

        } catch (SQLException e) {
            System.out.println("Product update failed.");
            e.printStackTrace();
        }
    }
}
