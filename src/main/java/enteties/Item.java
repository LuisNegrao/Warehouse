package enteties;

public class Item {

    private String itemType;
    private int itemValue;
    private int amount;

    public Item(String itemType, int itemValue, int amount) {
        this.itemType = itemType;
        this.itemValue = itemValue;
        this.amount = amount;
    }

    public Item(String itemType, int itemValue) {
        this.itemType = itemType;
        this.itemValue = itemValue;
        this.amount = 1;
    }
    public Item() {

    }

    public void print() {
        System.out.println("------------------------------------------------");
        System.out.println("Item: " + itemType);
        System.out.println("  Value: " + itemValue);
        System.out.println("  Amount: " + amount);
        System.out.println("------------------------------------------------");
    }

    public String getItemType() {
        return itemType;
    }

    public int getItemValue() {
        return itemValue;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public void setItemValue(int itemValue) {
        this.itemValue = itemValue;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
