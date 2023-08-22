import java.util.Scanner;

public class Login {
    public static Catalog catalog = new Catalog();
    public static void loginDriver(User user, int orderNum) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        String itemName, username, password, email;
        double itemPrice;
        int itemQuantity;
        while (true) {
            System.out.println("""
                    What would you like to do?
                     1)Discover our products.
                     2)Add an item to your cart.
                     3)Remove an item from your cart.
                     4)Show cart.
                     5)Edit Profile.
                     6)Checkout.
                     7)Show past orders.
                     8)Logout
                    """);
            choice = scanner.nextInt();
            switch (choice){
                case 1 -> {
                    int choice_;
                    Catalog catalog = new Catalog();
                    catalog.displayCatalog();
                    System.out.println("""
                                         1)Search Product.
                                         2)Back.
                                        """);
                    choice_ = scanner.nextInt();
                    switch (choice_){
                        case 1-> {
                            System.out.println("Search: ");
                            scanner.nextLine(); //consume newline character
                            itemName = scanner.nextLine();
                            catalog.search(itemName).displayItemInfo();
                        }
                    }

                }
                case 2 -> {
                    System.out.println("Please Enter item name:");
                    scanner.nextLine(); //consume newline character
                    itemName = scanner.nextLine();
                    System.out.println("Please Enter Quantity (limit 50):");
                    scanner.nextLine(); //consume newline character
                    itemQuantity = scanner.nextInt();
                    itemPrice = catalog.search(itemName).getItemPrice();
                    user.addToCart(itemName, itemPrice*itemQuantity);
                    System.out.println("Item added to cart successfully.");
                }
                case 3 -> {
                    System.out.println("Please enter item name to be removed:");
                    scanner.nextLine(); //consume newline character
                    itemName = scanner.nextLine();
                    user.removeFromCart(itemName);
                }
                case 4 ->
                    user.printCart();
                case 5 -> {
                    int userChoice;
                    System.out.println("""
                            What would you like to edit?
                            1)Name
                            2)Email
                            2)Password
                            """);
                    scanner.nextLine(); //consume newline character
                    userChoice = scanner.nextInt();
                    if (userChoice == 1){
                        System.out.println("Enter new name: ");
                        username = scanner.nextLine();
                        user.setName(username);
                        System.out.println("Name changed successfully to: " + user.getName());
                    } else if(userChoice == 2){
                        System.out.println("Enter new email: ");
                        email = scanner.nextLine();
                        user.setEmail(email);
                        System.out.println("Email changed successfully.");
                    }else if (userChoice == 3){
                        System.out.println("Enter new password: ");
                        password = scanner.nextLine();
                        user.setPassword(password);
                        System.out.println("Password changed successfully.");
                    }
                    else{
                        System.out.println("Invalid choice.");
                    }
                }
                case 6 -> {
                    System.out.println("""
                    How would you like to receive your order:
                    1)COD(Cash on delivery).
                          """);
                    choice = scanner.nextInt();
                    if (choice == 1){
                        Checkout checkout = new Checkout();
                        COD cod = new COD();
                        checkout.checkoutDriver(user, cod);
                        user.moveToPastOrders(orderNum);
                        user.deleteCart();
                    }
                    else {
                        System.out.println("Invalid payment method, Please try again.");
                    }
                }
                case 7->
                    user.printPastOrders();
                case 8 -> {
                    return;
                }
                default ->
                        System.out.println("Invalid choice, Please try again.");
                }
        }
    }
}