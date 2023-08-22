import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Admin {
    public static void printAllOrders() {
        try {
            Scanner sc = new Scanner(new File("orders.txt"));
            int orderNum = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals("|||")) {
                    orderNum++;
                    continue;
                }
                if (line.matches("\\d+")) {
                    System.out.println("Order " + orderNum);
                    System.out.print("Item name: " + sc.nextLine() + ", ");
                    System.out.println("Total price: " + Double.parseDouble(sc.nextLine()));
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        }
    }

}
