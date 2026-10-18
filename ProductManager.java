import java.util.ArrayList;

public class ProductManager {

    private ArrayList<Product> products = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("Product added successfully!");
    }

    public void displayAllProducts() {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product product : products) {
            product.displayDetails();
            System.out.println("--------------------");
        }
    }

    public void searchProduct(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                product.displayDetails();
                return;
            }
        }

        System.out.println("Product not found.");
    }

    public void viewProducts() {
        displayAllProducts();
    }

    public void updateProduct(int productId, double price, int stock) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                product.setPrice(price);
                product.setStock(stock);
                System.out.println("Product updated successfully!");
                return;
            }
        }

        System.out.println("Product not found.");
    }

    public void removeProduct(int productId) {
        for (Product product : products) {
            if (product.getProductId() == productId) {
                products.remove(product);
                System.out.println("Product removed successfully!");
                return;
            }
        }

        System.out.println("Product not found.");
    }
}