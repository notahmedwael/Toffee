import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User {
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private int numItems;
    private double total;
    private boolean hasOrder = false;
    private final ArrayList<Item> cart = new ArrayList<>();
    private final ArrayList<Item> pastOrders = new ArrayList<>();
    private ArrayList<String> pastOrdersNum = new ArrayList<>();

    public User(String name, String email, String phoneNumber, String password, ArrayList<String> pastOrdersNum) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.numItems = 0;
        this.total = 0.0;
        this.pastOrdersNum = pastOrdersNum;
        try (Scanner scanner = new Scanner(new File("orders.txt"))) {
            while (scanner.hasNextLine()) {
                String orderNumber = scanner.nextLine();
                ArrayList<Item> tempCart = new ArrayList<>();
                String itemName;
                if(pastOrdersNum.contains(orderNumber)) {
                    while (!(itemName = scanner.nextLine()).equals("|||")) {
                        Double itemPrice = Double.valueOf(scanner.nextLine());
                        addToTempCart(itemName, itemPrice, tempCart);
                    }
                    moveToTempPastOrders(tempCart);
                }else{
                    while (!(itemName = scanner.nextLine()).equals("|||")) {
                        Double itemPrice = Double.valueOf(scanner.nextLine());
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public User(String name, String email, String phoneNumber, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.numItems = 0;
        this.total = 0.0;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public void addToCart(String itemName, double itemPrice){
        Item item = new Item(itemName,itemPrice);
        cart.add(item);
        numItems++;
    }

    public void addToTempCart(String itemName, double itemPrice, ArrayList<Item> tempCart){
        Item item = new Item(itemName,itemPrice);
        tempCart.add(item);
    }
    public void removeFromCart(String itemName){
        boolean itemFound = false;
        for (Item item : cart){
            if (item.getItemName().equals(itemName)) {
                cart.remove(item);
                System.out.println("Item removed successfully.");
                itemFound = true;
                numItems--;
                break;
            }
        }
        if (!itemFound){
            System.out.println("Did not find the item!");
        }
    }
    public double getTotal() {
        for (Item item : cart){
            total += item.getItemPrice();
        }
        return total;
    }
    public void printCart() {
        if (numItems > 0) {
            System.out.println("Here is your cart: ");
            for (Item item : cart) {
                System.out.println("Item name: " + item.getItemName() + ", Total price: " + item.getItemPrice());
            }
            System.out.println("\n\n\n");
        }
        else{
            System.out.println("Your cart is currently empty.");
        }
    }
    public void printTempCart(ArrayList<Item> tempCart, String orderNum) {
        System.out.println("Order " + orderNum);
            for (Item item : tempCart) {
                System.out.println("Item name: " + item.getItemName() + ", Total price: " + item.getItemPrice());
            }
            System.out.println("\n\n\n");
    }
    public void writeOrderNum(String email, String orderNum){
        try {
            // Open the file for reading
            File file = new File("users.txt");
            Scanner scanner = new Scanner(file);

            // Read the file line by line

            StringBuilder fileContent = new StringBuilder();
            boolean found = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().equals(email)) {
                    System.out.println("True");
                    found = true;
                    fileContent.append(line).append("\n");
                    fileContent.append(scanner.nextLine()).append("\n");
                    fileContent.append(scanner.nextLine()).append("\n");
                    fileContent.append(orderNum + "\n"); // Append the order number
                } else {
                    fileContent.append(line).append("\n");
                }
            }
            scanner.close();

            if (!found) {
                // User not found, handle error
            } else {
                // Write the modified content back to the file
                FileWriter writer = new FileWriter(file, false); // Set append flag to false
                writer.write(fileContent.toString());
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while writing users to file: " + e.getMessage());
        }
    }
    public void printPastOrders(){
        if (hasOrder){
            try (Scanner scanner = new Scanner(new File("orders.txt"))) {
                while (scanner.hasNextLine()) {
                    String orderNumber = scanner.nextLine();
                    ArrayList<Item> tempCart = new ArrayList<>();
                    String itemName;
                    if(pastOrdersNum.contains(orderNumber)) {
                        while (!(itemName = scanner.nextLine()).equals("|||")) {
                            Double itemPrice = Double.valueOf(scanner.nextLine());
                            addToTempCart(itemName, itemPrice, tempCart);
                        }
                        printTempCart(tempCart, orderNumber);
                    }else{
                        while (!(itemName = scanner.nextLine()).equals("|||")) {
                            Double itemPrice = Double.valueOf(scanner.nextLine());
                        }
                    }

                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("You have no past orders.");
        }
    }
    public void moveToPastOrders(int orderNum){
        hasOrder = true;
        pastOrders.addAll(cart);
        try {
            FileWriter writer = new FileWriter("orders.txt", true);
            // Write user information to the file
            orderNum++;
            writer.write(Integer.toString(orderNum) + "\n");
            for(Item item : cart){
                writer.write(item.getItemName() + "\n");
                writer.write(item.getItemPrice() + "\n");
            }
            writer.write("|||\n");
            writer.close();
        }catch (IOException e) {
            System.out.println("An error occurred while writing users to file: " + e.getMessage());
        }
        writeOrderNum(this.email, Integer.toString(orderNum));
        deleteCart();
    }
    public void moveToTempPastOrders(ArrayList<Item> tempCart){
        hasOrder = true;
        pastOrders.addAll(tempCart);
    }
    public void deleteCart(){
        cart.clear();
        numItems = 0;
    }
}