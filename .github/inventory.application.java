import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class InventoryItem {
    private String name;
    private int quantity;

    public InventoryItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class InventoryManager {
    private Map<String, InventoryItem> inventory;

    public InventoryManager() {
        inventory = new HashMap<>();
    }

    public void addItem(String name, int quantity) {
        if (inventory.containsKey(name)) {
            InventoryItem item = inventory.get(name);
            item.setQuantity(item.getQuantity() + quantity);
        } else {
            inventory.put(name, new InventoryItem(name, quantity));
        }
        System.out.println("Added " + quantity + " " + name + "(s) to inventory.");
    }

    public void updateQuantity(String name, int quantity) {
        if (inventory.containsKey(name)) {
            inventory.get(name).setQuantity(quantity);
            System.out.println("Updated quantity of " + name + " to " + quantity + ".");
        } else {
            System.out.println(name + " not found in inventory.");
        }
    }

    public void removeItem(String name) {
        if (inventory.containsKey(name)) {
            inventory.remove(name);
            System.out.println("Removed " + name + " from inventory.");
        } else {
            System.out.println(name + " not found in inventory.");
        }
    }

    public void viewInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Current Inventory:");
            for (InventoryItem item : inventory.values()) {
                System.out.println(item.getName() + ": " + item.getQuantity());
            }
        }
    }
}

public class InventoryApplication {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add item");
            System.out.println("2. Update quantity");
            System.out.println("3. Remove item");
            System.out.println("4. View inventory");
            System.out.println("5. Exit");
            System.out.print("Enter your choice (1-5): ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item name: ");
                    String addName = scanner.nextLine();
                    System.out.print("Enter quantity: ");
                    int addQuantity = scanner.nextInt();
                    manager.addItem(addName, addQuantity);
                    break;
                case 2:
                    System.out.print("Enter item name: ");
                    String updateName = scanner.nextLine();
                    System.out.print("Enter new quantity: ");
                    int updateQuantity = scanner.nextInt();
                    manager.updateQuantity(updateName, updateQuantity);
                    break;
                case 3:
                    System.out.print("Enter item name to remove: ");
                    String removeName = scanner.nextLine();
                    manager.removeItem(removeName);
                    break;
                case 4:
                    manager.viewInventory();
                    break;
                case 5:
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}