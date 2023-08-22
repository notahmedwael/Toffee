import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Manager {
    public static int orderNum = 0;
    static ArrayList<User> users = new ArrayList<>();
    public static Catalog catalog = new Catalog();

    public static void readUsersFromFile(ArrayList<User> users) {
        try (Scanner scanner = new Scanner(new File("users.txt"))) {
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                String email = scanner.nextLine();
                String phoneNumber = scanner.nextLine();
                String password = scanner.nextLine();
                String tempOrder;
                ArrayList<String> pastOrdersNum = new ArrayList<>();
                while (!(tempOrder = scanner.nextLine()).equals("|||")) {
                    int tempOrderMax = Integer.parseInt(tempOrder);
                    if(orderNum < tempOrderMax){
                        orderNum = tempOrderMax;
                    }
                    pastOrdersNum.add(tempOrder);
                }
                User tempUser = new User(name, email, phoneNumber, password, pastOrdersNum);
                users.add(tempUser);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void registerMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Toffee Store!");
        System.out.println("Create account.");
        System.out.print("Your name: ");
        String name = scanner.nextLine();
        String email;

        while (true) {
            System.out.print("\nEmail: ");
            email = scanner.nextLine();
            boolean validEmail = true;
            for (User user : users) {
                if(user.getEmail() == email){
                    validEmail = false;
                    break;
                }else {
                    validEmail = true;
                }
            }
            if (validEmail) {
                break;
            }
            System.out.println("This email address is used before. Please use another email address!");
        }

        System.out.print("\nPhone number: ");
        String phoneNumber = scanner.nextLine();

        System.out.print("\nPassword: ");
        String password = scanner.nextLine();

        User tempUser = new User(name, email, phoneNumber, password);
        users.add(tempUser);
        try {
            FileWriter writer = new FileWriter("users.txt", true);

            // Write user information to the file
            writer.write(name + "\n");
            writer.write(email + "\n");
            writer.write(phoneNumber + "\n");
            writer.write(password + "\n");
            writer.write("|||\n");
            writer.close();
        }catch (IOException e) {
            System.out.println("An error occurred while writing users to file: " + e.getMessage());
        }
        System.out.println("\nAccount created successfully!");
    }
    public static void manage() {
        readUsersFromFile(users);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Hello dear user to our toffee store: ");
                System.out.println("What would you like to do: \n 1)Login. \n 2)Sign Up. \n 3)Discover our products. \n 0)Exit.");
                int choice = scanner.nextInt();
                String email, password;
                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter email: ");
                        scanner.nextLine(); //consume newline character
                        email = scanner.nextLine();
                        System.out.println("Enter password: ");
                        password = scanner.nextLine();
                        boolean found = false;
                        for (User user : users) {
                            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                                System.out.println("Login Successful");
                                found = true;
                                Login.loginDriver(user, orderNum);
                                break;
                            }
                        }
                        if (!found) {
                            System.out.println("Unsuccessful login attempt!\nWrong username or password, Please try again.");
                        }
                    }
                    case 2 -> {
                        registerMenu();
                            System.out.println("Hooray We made an account together :)\nLogin to be able to order.");
                        }

                    case 3 -> {
                        catalog.displayCatalog();
                    }
                    case 0 -> {
                        System.out.println("Thanks for using the program :)");
                        System.exit(0);
                    }
                    case 007 -> {
                        Admin admin = new Admin();
                        admin.printAllOrders();
                    }
                    default -> System.out.println("Invalid choice, Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, Please enter a number.");
                scanner.nextLine(); // consume invalid input
            }
        }
    }
}