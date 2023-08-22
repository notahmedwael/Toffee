import java.util.ArrayList;

public class Item {
    private String itemName;
    private String itemCategory;
    private String itemDescription;
    private String itemBrand;
    private double itemPrice;
    private double itemDiscount;
    private String itemMeasure;
    private static final ArrayList<Item> items = new ArrayList<>();
    public Item(){
        itemName = "Null";
        itemPrice = 0;
    }
    public Item(String itemName, double itemPrice){
        this.itemName = itemName;
        this.itemPrice = itemPrice;
    }
    public void setItemName(String itemName){
        this.itemName = itemName;
    }
    public void setItemCategory(String itemCategory){
        this.itemCategory = itemCategory;
    }
    public void setItemDescription(String itemDescription){
        this.itemDescription = itemDescription;
    }
    public void setItemBrand(String itemBrand){
        this.itemBrand = itemBrand;
    }

    public void setItemPrice(double itemPrice){
        this.itemPrice = itemPrice;
    }
    public void setItemDiscount(double itemDiscount){
        this.itemDiscount = itemDiscount;
    }
    public void setItemMeasure(String itemMeasure){
        this.itemMeasure = itemMeasure;
    }
    public void displayItemInfo() {
        System.out.println("\nName: " + this.itemName);
        System.out.println("Category: " + this.itemCategory);
        System.out.println("Description: " + this.itemDescription);
        System.out.println("Brand: " + this.itemBrand);
        System.out.println("Price: " + this.itemPrice);
        System.out.println("Discount: " + this.itemDiscount);
        System.out.println("Sold by: " + this.itemMeasure + "\n");
    }

    public String getItemName(){
        return itemName;
    }
    public double getItemPrice(){
        return itemPrice;
    }
    public static ArrayList<Item> getItems() {
        return items;
    }
    public void addItem(Item item) {
        items.add(item);
    }
    public void addItem(String itemName, double itemPrice){
        Item item = new Item(itemName,itemPrice);
        items.add(item);
    }
    public void removeItem(String itemName){
        boolean itemFound = false;
        for (Item item : items){
            if (item.getItemName().equals(itemName)) {
                items.remove(item);
                System.out.println("Item named " + getItemName() + " removed successfully.");
                itemFound = true;
                break;
            }
            }
        if (!itemFound){
            System.out.println("Did not find the item named "+ getItemName() +" .");
        }
        }
    public void printItemList() {
        for (Item item : items) {
            System.out.println("Item name: " + item.getItemName() + ", Price: " + item.getItemPrice());
        }
    }
    }