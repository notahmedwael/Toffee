import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class Catalog {
    private Map<String, Item> items = new HashMap<>();

    public Catalog(){
        try {
            File myObj = new File("sweets.txt");
            Scanner myReader = new Scanner(myObj);

            while (myReader.hasNextLine()) {
                Item temp = new Item();
                String data = myReader.nextLine();
                temp.setItemName(data);
                data = myReader.nextLine();
                temp.setItemCategory(data);
                data = myReader.nextLine();
                temp.setItemDescription(data);
                data = myReader.nextLine();
                temp.setItemBrand(data);
                data = myReader.nextLine();
                temp.setItemPrice(Double.parseDouble(data));
                data = myReader.nextLine();
                temp.setItemDiscount(Double.parseDouble(data));
                data = myReader.nextLine();
                temp.setItemMeasure(data);
                items.put(temp.getItemName(), temp);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void displayCatalog() {
        for (Map.Entry<String, Item> set : items.entrySet()) {
            System.out.println(set.getKey());
        }
    }

    public Item search(String item) {
        Item tempItem = items.get(item);
        return tempItem;
    }

}
