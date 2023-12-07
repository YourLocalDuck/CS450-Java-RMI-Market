import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Map;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the registry
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Look up the remote Store
            StoreInterface store = (StoreInterface) registry.lookup("Store");

            // Create a FrontController
            FrontController frontController = new FrontController(store);

            // Program Loop
            boolean keepGoing = true;
            while (keepGoing) {
                System.out.println("What would you like to do?");
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("3. Exit");
                int choice = Integer.parseInt(System.console().readLine());
                switch (choice) {
                    case 1:
                        // Log in as Administrator or Customer, and enter into the respective loop
                        User user = login(frontController, store);
                        if (user instanceof AdministratorInterface) {
                            AdministratorInterface admin = (AdministratorInterface) user;
                            adminLoop(frontController, admin);
                        } else if (user instanceof CustomerInterface) {
                            CustomerInterface customer = (CustomerInterface) user;
                            customerLoop(frontController, customer);
                        } else {
                            System.out.println("Invalid user");
                        }
                        break;
                    case 2:
                        // Register a new Customer
                        System.out.println("Enter your username:");
                        String username = System.console().readLine();
                        System.out.println("Enter your password:");
                        String password = System.console().readLine();
                        frontController.register(username, password, new CustomerFactory());
                        break;
                    case 3:
                        // Exit the program
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    public static User login(FrontController frontController, StoreInterface store) {
        System.out.println("Enter your username:");
        String username = System.console().readLine();
        System.out.println("Enter your password:");
        String password = System.console().readLine();
        User user = frontController.login(username, password);
        while (user == null) {
            System.out.println("Invalid username or password");
            System.out.println("Enter your username:");
            username = System.console().readLine();
            System.out.println("Enter your password:");
            password = System.console().readLine();
            user = frontController.login(username, password);
        }
        return user;
    }

    public static void adminLoop(FrontController frontController, AdministratorInterface admin) throws RemoteException {
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("What would you like to do?");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Item");
            System.out.println("3. Update Item");
            System.out.println("4. Remove Item");
            System.out.println("5. View Users");
            System.out.println("6. Add User");
            System.out.println("7. Remove User");
            System.out.println("8. Add Administrator");
            System.out.println("9. Remove Administrator");
            System.out.println("10. Logout");
            System.out.println("11. Exit");
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    List<Item> inventory = frontController.adminGetInventory(admin);
                    for (Item item : inventory) {
                        System.out.println("ID: " + item.getId() +  "Name " + item.getName() + "Price " + item.getPrice() + "Description "
                                + item.getDescription() + " Quantity " + item.getQuantity());
                    }
                    break;
                case 2:
                    System.out.println("Enter the name of the item:");
                    String name = System.console().readLine();
                    System.out.println("Enter the type of the item:");
                    String type = System.console().readLine();
                    System.out.println("Enter a description of the item:");
                    String description = System.console().readLine();
                    System.out.println("Enter the price of the item:");
                    float price = Float.parseFloat(System.console().readLine());
                    System.out.println("Enter the quantity of the item:");
                    int qt = Integer.parseInt(System.console().readLine());
                    frontController.adminAddItem(admin, name, type, description, price, qt);
                    break;
                case 3:
                    System.out.println("Enter the id of the item:");
                    int id = Integer.parseInt(System.console().readLine());
                    System.out.println("Enter the name of the item:");
                    name = System.console().readLine();
                    System.out.println("Enter the price of the item:");
                    price = Float.parseFloat(System.console().readLine());
                    System.out.println("Enter the description of the item:");
                    description = System.console().readLine();
                    System.out.println("Enter the quantity of the item:");
                    int quantity = Integer.parseInt(System.console().readLine());
                    frontController.adminUpdateItem(admin, id, name, price, description, quantity);
                    break;
                case 4:
                    System.out.println("Enter the id of the item:");
                    id = Integer.parseInt(System.console().readLine());
                    frontController.adminRemoveItem(admin, id);
                    break;
                case 5:
                    List<User> users = frontController.adminGetUsers(admin);
                    for (User u : users) {
                        System.out.println("Username: " + u.getUsername());
                    }
                    break;
                case 6:
                    System.out.println("Enter the username of the user:");
                    String username = System.console().readLine();
                    System.out.println("Enter the password of the user:");
                    String password = System.console().readLine();
                    frontController.register(username, password, new CustomerFactory());
                    break;
                case 7:
                    System.out.println("Enter the username of the user:");
                    username = System.console().readLine();
                    frontController.adminRemoveCustomer(admin, username);
                    System.out.println("User removed");
                    break;
                case 8:
                    System.out.println("Enter the username of the user:");
                    username = System.console().readLine();
                    System.out.println("Enter the password of the user:");
                    password = System.console().readLine();
                    frontController.register(username, password, new AdministratorFactory());
                    break;
                case 9:
                    System.out.println("Enter the username of the user:");
                    username = System.console().readLine();
                    frontController.adminRemoveAdministrator(admin, username);
                    break;
                case 10:
                    frontController.logout(admin);
                    keepGoing = false;
                    break;
                case 11:
                    frontController.logout(admin);
                    System.exit(0);
                    break;
            }
        }
    }

    public static void customerLoop(FrontController frontController, CustomerInterface customer) throws RemoteException{
        boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("What would you like to do?");
            System.out.println("1. View Inventory");
            System.out.println("2. View Cart");
            System.out.println("3. Add Item to Cart");
            System.out.println("4. Remove Item from Cart");
            System.out.println("5. Empty Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Logout");
            System.out.println("8. Exit");
            int choice = Integer.parseInt(System.console().readLine());
            switch (choice) {
                case 1:
                    List<Item> inventory = frontController.customerGetInventory(customer);
                    for (Item item : inventory) {
                        System.out.println("ID: " + item.getId() +  "Name " + item.getName() + "Price " + item.getPrice() + "Description "
                                + item.getDescription() + " Quantity " + item.getQuantity());
                    }
                    break;
                case 2:
                    Map<Item, Integer> cart = frontController.customerGetCart(customer);
                    for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
                        Item item = entry.getKey();
                        int quantity = entry.getValue();
                        System.out.println(item.getId() + " " + item.getName() + " " + item.getPrice() + " "
                                + item.getDescription() + " " + quantity);
                    }
                    break;
                case 3:
                    System.out.println("Enter the id of the item:");
                    int id = Integer.parseInt(System.console().readLine());
                    System.out.println("Enter the quantity of the item:");
                    int qt = Integer.parseInt(System.console().readLine());
                    frontController.customerAddToCart(customer, id, qt);
                    break;
                case 4:
                    System.out.println("Enter the id of the item:");
                    id = Integer.parseInt(System.console().readLine());
                    System.out.println("Enter the quantity of the item:");
                    qt = Integer.parseInt(System.console().readLine());
                    frontController.customerRemoveFromCart(customer, id, qt);
                    break;
                case 5:
                    frontController.customerEmptyCart(customer);
                    break;
                case 6:
                    frontController.customerCheckout(customer);
                    break;
                case 7:
                    frontController.logout(customer);
                    keepGoing = false;
                    break;
                case 8:
                    frontController.logout(customer);
                    System.exit(0);
                    break;  
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
