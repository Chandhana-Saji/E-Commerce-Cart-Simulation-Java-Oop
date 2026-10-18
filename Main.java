import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ProductManager manager = new ProductManager();
        Scanner scanner = new Scanner(System.in);

        int choice;

        do {
            System.out.println("\n===== PRODUCT MANAGEMENT =====");
            System.out.println("1. Add Product");
            System.out.println("2. View Products");
            System.out.println("3. Search Product");
            System.out.println("4. Update Product");
            System.out.println("5. Remove Product");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {

                case 1:
    System.out.println("\nSelect Product Type:");
    System.out.println("1. General Product");
    System.out.println("2. Clothing");
    System.out.println("3. Gadget");
    System.out.println("4. Footwear");
    System.out.print("Enter type: ");

    int type = scanner.nextInt();

    System.out.print("Enter Product ID: ");
    int id = scanner.nextInt();

    scanner.nextLine();

    System.out.print("Enter Product Name: ");
    String name = scanner.nextLine();

    System.out.print("Enter Price: ");
    double price = scanner.nextDouble();

    System.out.print("Enter Discount: ");
    double discount = scanner.nextDouble();

    System.out.print("Enter Stock: ");
    int stock = scanner.nextInt();

    if (type == 1) {

        Product product = new Product(
            id, name, price, discount, stock
        );

        manager.addProduct(product);

    } else if (type == 2) {

        scanner.nextLine();

        System.out.print("Enter Size: ");
        String size = scanner.nextLine();

        System.out.print("Enter Material: ");
        String material = scanner.nextLine();

        Clothing clothing = new Clothing(
            id, name, price, discount, stock, size, material
        );

        manager.addProduct(clothing);

    } else if (type == 3) {

        scanner.nextLine();

        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter Warranty: ");
        String warranty = scanner.nextLine();

        Gadget gadget = new Gadget(
            id, name, price, discount, stock, brand, warranty
        );

        manager.addProduct(gadget);

    } else if (type == 4) {

        scanner.nextLine();

        System.out.print("Enter Brand: ");
        String brand = scanner.nextLine();

        System.out.print("Enter Size: ");
        int size = scanner.nextInt();

        Footwear footwear = new Footwear(
            id, name, price, discount, stock, brand, size
        );

        manager.addProduct(footwear);

    } else {
        System.out.println("Invalid product type!");
    }

    break;

                case 2:
                    manager.viewProducts();
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int searchId = scanner.nextInt();

                    manager.searchProduct(searchId);
                    break;

                case 4:
                    System.out.print("Enter Product ID: ");
                    int updateId = scanner.nextInt();

                    System.out.print("Enter New Price: ");
                    double newPrice = scanner.nextDouble();

                    System.out.print("Enter New Stock: ");
                    int newStock = scanner.nextInt();

                    manager.updateProduct(
                        updateId, newPrice, newStock
                    );
                    break;

                case 5:
                    System.out.print("Enter Product ID: ");
                    int removeId = scanner.nextInt();

                    manager.removeProduct(removeId);
                    break;

                case 6:
                    System.out.println("Exiting Product Management...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        scanner.close();
    }
}