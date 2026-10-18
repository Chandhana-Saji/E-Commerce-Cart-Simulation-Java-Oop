public class Footwear extends Product {

    private String brand;
    private int size;

    public Footwear(int productId, String name, double price, double discount, int stock,
                    String brand, int size) {

        super(productId, name, price, discount, stock);
        this.brand = brand;
        this.size = size;
    }

    public void displayFootwearDetails() {
        displayDetails();
        System.out.println("Brand: " + brand);
        System.out.println("Size: " + size);
    }
}
