
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame {

    private ProductManager manager = new ProductManager();
    private JPanel contentPanel;

    public MainFrame() {

        setTitle("E-Commerce Cart Simulation");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Temporary sample products
        manager.addProduct(
                new Product(1, "Notebook", 80.0, 5.0, 50));

        manager.addProduct(
                new Clothing(2, "T-Shirt", 500.0, 10.0, 20,
                        "M", "Cotton"));

        manager.addProduct(
                new Gadget(3, "Headphones", 1500.0, 8.0, 10,
                        "Boat", "1 year"));

        manager.addProduct(
                new Footwear(4, "Sports Shoes", 2000.0, 15.0, 15,
                        "Puma", 8));

        // Heading
        JLabel heading = new JLabel(
                "E-COMMERCE CART SIMULATION", JLabel.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 24));
        heading.setBorder(
                BorderFactory.createEmptyBorder(20, 10, 20, 10));

        add(heading, BorderLayout.NORTH);

        // Main content area
        contentPanel = new JPanel(new BorderLayout(10, 10));
        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(15, 20, 15, 20));
        add(contentPanel, BorderLayout.CENTER);

        // Navigation
        JPanel menuPanel = new JPanel(
                new FlowLayout(FlowLayout.CENTER, 12, 12));

        JButton homeButton = new JButton("Home");
        JButton productsButton = new JButton("Shop Products");
        JButton addButton = new JButton("Add Product");
        JButton cartButton = new JButton("My Cart");
        JButton customerButton = new JButton("Customer");

        menuPanel.add(homeButton);
        menuPanel.add(productsButton);
        menuPanel.add(addButton);
        menuPanel.add(cartButton);
        menuPanel.add(customerButton);

        add(menuPanel, BorderLayout.SOUTH);

        homeButton.addActionListener(e -> showHome());
        productsButton.addActionListener(e -> showProducts());
        addButton.addActionListener(e -> showAddProduct());

        cartButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this, "Shopping Cart will be added next."));

        customerButton.addActionListener(e ->
                JOptionPane.showMessageDialog(
                        this, "Customer screen will be added next."));

        showHome();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Home screen
    private void showHome() {

        contentPanel.removeAll();

        JLabel welcome = new JLabel(
                "Welcome to our online store!", JLabel.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel description = new JLabel(
                "Browse products and explore our collection.",
                JLabel.CENTER);

        JButton shopButton = new JButton("Start Shopping");
        shopButton.addActionListener(e -> showProducts());

        JButton addProductButton = new JButton("Add a Product");
        addProductButton.addActionListener(e -> showAddProduct());

        JPanel buttons = new JPanel();
        buttons.add(shopButton);
        buttons.add(addProductButton);

        JPanel homePanel = new JPanel(new GridLayout(3, 1, 10, 10));
        homePanel.add(welcome);
        homePanel.add(description);
        homePanel.add(buttons);

        contentPanel.add(homePanel, BorderLayout.CENTER);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Product listing screen
    private void showProducts() {

        contentPanel.removeAll();

        JLabel title = new JLabel(
                "Available Products", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        String[] columns = {
                "Product ID", "Name", "Price (₹)",
                "Discount (%)", "Stock"
        };

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Product product : manager.getProducts()) {
            model.addRow(new Object[]{
                    product.getProductId(),
                    product.getName(),
                    product.getPrice(),
                    product.getDiscount(),
                    product.getStock()
            });
        }

        JTable table = new JTable(model);
        table.setRowHeight(28);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addButton = new JButton("Add Product");
        addButton.addActionListener(e -> showAddProduct());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(addButton);

        contentPanel.add(title, BorderLayout.NORTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(bottomPanel, BorderLayout.SOUTH);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    // Add Product form
    private void showAddProduct() {

        contentPanel.removeAll();

        JLabel title = new JLabel("Add New Product", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBorder(BorderFactory.createEmptyBorder(15, 60, 15, 60));

        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField discountField = new JTextField();
        JTextField stockField = new JTextField();

        String[] productTypes = {
                "General Product", "Clothing", "Gadget", "Footwear"
        };
        JComboBox<String> typeBox = new JComboBox<>(productTypes);

        JTextField detail1Field = new JTextField();
        JTextField detail2Field = new JTextField();

        JLabel detail1Label = new JLabel("Extra detail 1:");
        JLabel detail2Label = new JLabel("Extra detail 2:");

        form.add(new JLabel("Product Type:"));
        form.add(typeBox);

        form.add(new JLabel("Product ID:"));
        form.add(idField);

        form.add(new JLabel("Product Name:"));
        form.add(nameField);

        form.add(new JLabel("Price:"));
        form.add(priceField);

        form.add(new JLabel("Discount (%):"));
        form.add(discountField);

        form.add(new JLabel("Stock:"));
        form.add(stockField);

        form.add(detail1Label);
        form.add(detail1Field);

        form.add(detail2Label);
        form.add(detail2Field);

        // Update labels based on product type
        Runnable updateDetailLabels = () -> {
            int type = typeBox.getSelectedIndex();

            if (type == 1) {
                detail1Label.setText("Clothing size:");
                detail2Label.setText("Material:");
            } else if (type == 2) {
                detail1Label.setText("Brand:");
                detail2Label.setText("Warranty:");
            } else if (type == 3) {
                detail1Label.setText("Brand:");
                detail2Label.setText("Footwear size:");
            } else {
                detail1Label.setText("Extra detail 1:");
                detail2Label.setText("Extra detail 2:");
            }
        };

        typeBox.addActionListener(e -> updateDetailLabels.run());
        updateDetailLabels.run();

        JButton saveButton = new JButton("Save Product");
        JButton cancelButton = new JButton("Cancel");

        JPanel buttons = new JPanel();
        buttons.add(saveButton);
        buttons.add(cancelButton);

        saveButton.addActionListener(e -> {
            try {
                int id = Integer.parseInt(idField.getText().trim());
                String name = nameField.getText().trim();
                double price = Double.parseDouble(
                        priceField.getText().trim());
                double discount = Double.parseDouble(
                        discountField.getText().trim());
                int stock = Integer.parseInt(stockField.getText().trim());

                if (name.isEmpty()) {
                    throw new IllegalArgumentException(
                            "Product name cannot be empty.");
                }

                if (id <= 0 || price < 0 || stock < 0
                        || discount < 0 || discount > 100) {
                    throw new IllegalArgumentException(
                            "Enter a positive ID, non-negative price/stock, "
                            + "and a discount between 0 and 100.");
                }

                for (Product existing : manager.getProducts()) {
                    if (existing.getProductId() == id) {
                        throw new IllegalArgumentException(
                                "This Product ID already exists.");
                    }
                }

                String detail1 = detail1Field.getText().trim();
                String detail2 = detail2Field.getText().trim();

                int type = typeBox.getSelectedIndex();
                Product product;

                switch (type) {
                    case 1:
                        if (detail1.isEmpty() || detail2.isEmpty()) {
                            throw new IllegalArgumentException(
                                    "Enter clothing size and material.");
                        }
                        product = new Clothing(
                                id, name, price, discount, stock,
                                detail1, detail2);
                        break;

                    case 2:
                        if (detail1.isEmpty() || detail2.isEmpty()) {
                            throw new IllegalArgumentException(
                                    "Enter gadget brand and warranty.");
                        }
                        product = new Gadget(
                                id, name, price, discount, stock,
                                detail1, detail2);
                        break;

                    case 3:
                        if (detail1.isEmpty() || detail2.isEmpty()) {
                            throw new IllegalArgumentException(
                                    "Enter footwear brand and size.");
                        }
                        int footwearSize = Integer.parseInt(detail2);
                        product = new Footwear(
                                id, name, price, discount, stock,
                                detail1, footwearSize);
                        break;

                    default:
                        product = new Product(
                                id, name, price, discount, stock);
                        break;
                }

                manager.addProduct(product);

                JOptionPane.showMessageDialog(
                        this, "Product added successfully!");

                showProducts();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid numbers. Footwear size must be an integer.",
                        "Invalid Input",
                        JOptionPane.ERROR_MESSAGE);

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage(),
                        "Invalid Product Details",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> showProducts());

        contentPanel.add(title, BorderLayout.NORTH);
        contentPanel.add(form, BorderLayout.CENTER);
        contentPanel.add(buttons, BorderLayout.SOUTH);

        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
