import java.util.*;

class InventoryItem {
    // ... (previous code remains the same)
}

class Order {
    private static int orderCounter = 1;
    private int orderId;
    private String username;
    private List<OrderItem> items;
    private Date orderDate;

    public Order(String username) {
        this.orderId = orderCounter++;
        this.username = username;
        this.items = new ArrayList<>();
        this.orderDate = new Date();
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public int getOrderId() {
        return orderId;
    }

    public String getUsername() {
        return username;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Date getOrderDate() {
        return orderDate;
    }
}

class OrderItem {
    private String itemName;
    private int quantity;

    public OrderItem(String itemName, int quantity) {
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }
}

class InventoryManager {
    private Map<String, InventoryItem> inventory;
    private List<Order> orders;

    public InventoryManager() {
        inventory = new HashMap<>();
        orders = new ArrayList<>();
    }

    // ... (previous methods remain the same)

    public boolean createOrder(String username, Map<String, Integer> orderItems) {
        Order order = new Order(username);
        boolean orderValid = true;

        for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
            String itemName = entry.getKey();
            int quantity = entry.getValue();

            if (inventory.containsKey(itemName) && inventory.get(itemName).getQuantity() >= quantity) {
                order.addItem(new OrderItem(itemName, quantity));
                inventory.get(itemName).setQuantity(inventory.get(itemName).getQuantity() - quantity);
            } else {
                orderValid = false;
                System.out.println("Error: Insufficient quantity for " + itemName);
                break;
            }
        }

        if (orderValid) {
            orders.add(order);
            System.out.println("Order created successfully. Order ID: " + order.getOrderId());
            return true;
        } else {
            System.out.println("Order creation failed due to insufficient inventory.");
            return false;
        }
    }

    public void viewOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            System.out.println("Order History:");
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getOrderId() + 
                                   ", User: " + order.getUsername() + 
                                   ", Date: " + order.getOrderDate());
                for (OrderItem item : order.getItems()) {
                    System.out.println("  - " + item.getItemName() + ": " + item.getQuantity());
                }
                System.out.println();
            }
        }
    }
}

public class InventoryApplication {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);
        String currentUser = null;

        while (true) {
            if (currentUser == null) {
                System.out.print("Enter username to login (or 'exit' to quit): ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                currentUser = input;
                System.out.println("Logged in as: " + currentUser);
            }

            System.out.println("\n1. Add item");
            System.out.println("2. Update quantity");
            System.out.println("3. Remove item");
            System.out.println("4. View inventory");
            System.out.println("5. Create order");
            System.out.println("6. View orders");
            System.out.println("7. Logout");
            System.out.println("8. Exit");
            System.out.print("Enter your choice (1-8): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                    // ... (previous cases remain the same)
                    break;
                case 5:
                    Map<String, Integer> orderItems = new HashMap<>();
                    while (true) {
                        System.out.print("Enter item name (or 'done' to finish): ");
                        String itemName = scanner.nextLine();
                        if (itemName.equalsIgnoreCase("done")) {
                            break;
                        }
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        orderItems.put(itemName, quantity);
                    }
                    manager.createOrder(currentUser, orderItems);
                    break;
                case 6:
                    manager.viewOrders();
                    break;
                case 7:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    break;
                case 8:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}