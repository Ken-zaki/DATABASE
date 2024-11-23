import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Display d = new Display();

    public static void main(String[] args) {
        while (true) {
            try {
                d.getDisplayMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        login();
                        break;
                    case 2:
                        register();
                        break;
                    case 3:
                        System.out.println("+-----------------------------------+");
                        System.out.println("|   Thank you for using the system. |");
                        System.out.println("+-----------------------------------+");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("+---------------------------------------+");
                        System.out.println("|   Invalid option. Please try again.   |");
                        System.out.println("+---------------------------------------+");
                        break;
                }
            } catch (Exception e) {
                System.out.println("+---------------------------------------+");
                System.out.println("|   Invalid input. Please try again.    |");
                System.out.println("+---------------------------------------+");
                scanner.nextLine();
            }
        }
    }

    public static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Account account = Account.login(username, password);

        if (account != null) {
            System.out.println("+------------------+");
            System.out.println("| Login Successful |");
            System.out.println("+------------------+");

            if (account instanceof User) {
                userMenu((User) account);
            } else if (account instanceof Admin) {
                adminMenu((Admin) account);
            }
        } else {
            System.out.println("+-----------------------------------+");
            System.out.println("| Invalid username or password.     |");
            System.out.println("+-----------------------------------+");
        }
    }

    public static void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        String role = password.contains("@") ? "Admin" : "User";
        boolean success = Account.register(username, password, role);
        
        if (success) {
            System.out.println("+-----------------------------------+");
            System.out.println(" Registration successful as " + role + ".");
            System.out.println("+-----------------------------------+");
        } else {
            System.out.println("+-----------------------------------+");
            System.out.println("| Registration failed. Try again.   |");
            System.out.println("+-----------------------------------+");
        }

    }

    public static void userMenu(User user) {
        while (true) {
            try {
                d.getDisplayUser(user);
                int choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        viewCategory();
                        break;
                    case 2:
                        System.out.println("+-----------------------------------+");
                        System.out.println("|   Logout successful. Goodbye!     |");
                        System.out.println("+-----------------------------------+");
                        return;
                    default:
                        System.out.println("+---------------------------------------+");
                        System.out.println("|   Invalid choice. Please try again.   |");
                        System.out.println("+---------------------------------------+");
                        break;
                }
            } catch (Exception e) {
                System.out.println("+---------------------------------------+");
                System.out.println("|   Invalid input. Please try again.    |");
                System.out.println("+---------------------------------------+");
                scanner.nextLine(); 
            }
        }
    }

    public static void adminMenu(Admin admin) {
        while (true) {
            try {
                d.getDisplayAdmin(admin);
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addResource(admin);
                        break;
                    case 2:
                        removeResource(admin);
                        break;
                    case 3:
                        viewCategory();
                        break;
                    case 4:
                        System.out.println("+-----------------------------------+");
                        System.out.println("|   Logout successful. Goodbye!     |");
                        System.out.println("+-----------------------------------+");
                        return;
                    default:
                        System.out.println("+---------------------------------------+");
                        System.out.println("|   Invalid choice. Please try again.   |");
                        System.out.println("+---------------------------------------+");
                        break;
                }
            } catch (Exception e) {
                System.out.println("+---------------------------------------+");
                System.out.println("|   Invalid input. Please try again.    |");
                System.out.println("+---------------------------------------+");
                scanner.nextLine(); 
            }
        }
    }

    public static void viewCategory() {
        d.viewResources();
        int choice = scanner.nextInt();
        scanner.nextLine(); 
    
        String category;
        switch (choice) {
            case 1: category = "Healthcare"; break;
            case 2: category = "Educational"; break;
            case 3: category = "Food"; break;
            case 4: category = "Transportation"; break;
            default:
                System.out.println("+---------------------------------------+");
                System.out.println("|   Invalid choice. Please try again.   |");
                System.out.println("+---------------------------------------+");
                return;
        }
    
        List<Resource> resources = Resource.fetchResourcesByCategory(category);
    

        if (resources.isEmpty()) {
            System.out.println("+------------------------------------------+");
            System.out.println("|   No resources found in this category.   |");
            System.out.println("+------------------------------------------+");
        } else {
            for (Resource resource : resources) {
                System.out.println(resource);
            }
        }
    }


    public static void addResource(Admin admin) {
        d.viewResources();
        int choice = scanner.nextInt();
        scanner.nextLine(); 
        
        String category;
        switch (choice) {
            case 1: category = "Healthcare"; break;
            case 2: category = "Educational"; break;
            case 3: category = "Food"; break;
            case 4: category = "Transportation"; break;
            default:
                System.out.println("+---------------------------------------+");
                System.out.println("|   Invalid choice. Please try again.   |");
                System.out.println("+---------------------------------------+");
                return;
        }
        System.out.print("Enter resource name: ");
        String name = scanner.nextLine();
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter details: ");
        String details = scanner.nextLine();
    
        if (Resource.addResourceToDB(category, name, location, details)) {
            System.out.println("+-----------------------------------+");
            System.out.println("|   Resource added successfully.    |");
            System.out.println("+-----------------------------------+");
        } else {
            System.out.println("+-----------------------------------+");
            System.out.println("|   Error adding resource.          |");
            System.out.println("+-----------------------------------+");
        }
    }
    

    public static void removeResource(Admin admin) {
        System.out.print("Enter resource name to remove: ");
        String name = scanner.nextLine();
    
        if (Resource.removeResourceFromDB(name)) {
            System.out.println("+-----------------------------------+");
            System.out.println("|   Resource removed successfully.  |");
            System.out.println("+-----------------------------------+");
        } else {
            System.out.println("+-----------------------------------+");
            System.out.println("|   No resource found to remove.    |");
            System.out.println("+-----------------------------------+");
        }
    }
}

