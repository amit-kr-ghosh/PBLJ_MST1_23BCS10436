import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Custom Exception for Product Not Found
class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

public class Ques4 {
    private HashMap<String, Double> productMap;

    public Ques4() {
        productMap = new HashMap<>();
    }

    // 1. Add a product
    public void addProduct(String id, double price) {
        productMap.put(id, price);
        System.out.println("Product added: " + id + " = $" + price);
    }

    // 2. Apply discount
    public void applyDiscount(String id, double discountPercent) throws ProductNotFoundException {
        if (!productMap.containsKey(id)) {
            throw new ProductNotFoundException("Error: Product ID not found!");
        }
        if (discountPercent < 0 || discountPercent > 100) {
            throw new IllegalArgumentException("Error: Discount percentage must be between 0 and 100!");
        }

        double originalPrice = productMap.get(id);
        double newPrice = originalPrice - (originalPrice * discountPercent / 100.0);
        productMap.put(id, newPrice);
        System.out.println("New price for " + id + ": $" + newPrice);
    }

    // 3. Display all products
    public void displayProducts() {
        if (productMap.isEmpty()) {
            System.out.println("No products to display.");
        } else {
            System.out.println("All Products:");
            for (Map.Entry<String, Double> entry : productMap.entrySet()) {
                System.out.println(entry.getKey() + " = $" + entry.getValue());
            }
        }
    }

    public static void main(String[] args) {
        Ques4 store = new Ques4();
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n--- Product Management Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Apply Discount");
            System.out.println("3. Display All Products");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Product Price: $");
                    double price = sc.nextDouble();
                    sc.nextLine(); // consume newline
                    store.addProduct(id, price);
                    break;

                case 2:
                    System.out.print("Enter Product ID to apply discount: ");
                    String discountId = sc.nextLine();
                    System.out.print("Enter discount percentage: ");
                    double discount = sc.nextDouble();
                    sc.nextLine(); // consume newline
                    try {
                        store.applyDiscount(discountId, discount);
                    } catch (ProductNotFoundException | IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:
                    store.displayProducts();
                    break;

                case 4:
                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 4);

        sc.close();
    }
}
