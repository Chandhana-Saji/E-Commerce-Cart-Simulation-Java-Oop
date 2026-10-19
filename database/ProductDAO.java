
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO {

    public boolean addProduct(
            int productId,
            String name,
            double price,
            double discount,
            int stock) {

        String sql = """
            INSERT INTO Products
            (product_id, name, price, discount, stock, category)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, productId);
            statement.setString(2, name);
            statement.setDouble(3, price);
            statement.setDouble(4, discount);
            statement.setInt(5, stock);
            statement.setString(6, "General");

            statement.executeUpdate();

            System.out.println("Product saved to database!");
            return true;

        } catch (SQLException e) {
            System.out.println("Could not save product.");
            e.printStackTrace();
            return false;
        }
    }
    public void displayProducts() {

    String sql = "SELECT * FROM Products";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql);
         ResultSet result = statement.executeQuery()) {

        while (result.next()) {
            System.out.println("Product ID: " + result.getInt("product_id"));
            System.out.println("Name: " + result.getString("name"));
            System.out.println("Price: " + result.getDouble("price"));
            System.out.println("Discount: " + result.getDouble("discount") + "%");
            System.out.println("Stock: " + result.getInt("stock"));
            System.out.println("--------------------");
        }

    } catch (SQLException e) {
        System.out.println("Could not display products.");
        e.printStackTrace();
    }
}
public void searchProduct(int productId) {

    String sql = "SELECT * FROM Products WHERE product_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, productId);

        try (ResultSet result = statement.executeQuery()) {

            if (result.next()) {
                System.out.println("Product ID: " + result.getInt("product_id"));
                System.out.println("Name: " + result.getString("name"));
                System.out.println("Price: " + result.getDouble("price"));
                System.out.println("Discount: " + result.getDouble("discount") + "%");
                System.out.println("Stock: " + result.getInt("stock"));
            } else {
                System.out.println("Product not found.");
            }
        }

    } catch (SQLException e) {
        System.out.println("Product search failed!");
        e.printStackTrace();
    }
}
public void updateProduct(int productId, double price, int stock) {

    String sql = "UPDATE Products SET price = ?, stock = ? WHERE product_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setDouble(1, price);
        statement.setInt(2, stock);
        statement.setInt(3, productId);

        int rows = statement.executeUpdate();

        if (rows > 0) {
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found.");
        }

    } catch (SQLException e) {
        System.out.println("Product update failed!");
        e.printStackTrace();
    }
}
public void deleteProduct(int productId) {

    String sql = "DELETE FROM Products WHERE product_id = ?";

    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, productId);

        int rows = statement.executeUpdate();

        if (rows > 0) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found.");
        }

    } catch (SQLException e) {
        System.out.println("Product deletion failed!");
        e.printStackTrace();
    }
}
}
