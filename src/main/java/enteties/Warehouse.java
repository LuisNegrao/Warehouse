package enteties;

import database.Database;
import database.SQLDatabase;
import utils.Auxiliar;

import java.util.*;

public class Warehouse {

    private Auxiliar auxiliar = new Auxiliar();
    private Database database = new SQLDatabase();

    String warehouseID;
    User onlineUser;
    private final int MAX_STORAGE = 25000;

    public Warehouse(User user) {
        this.onlineUser = user;
        System.out.println(user.getUserId());
        this.warehouseID = user.getWarehouseID();
        warehouseMenu();
    }

    public void warehouseMenu() {

        System.out.println("1- See Available Stock");
        System.out.println("2- Ship Sock");
        System.out.println("3- Add Stock");
        System.out.println("4- Logout");
        if (onlineUser.getSecurityLevel() != SecurityLevel.Employee) {
            System.out.println("5- Manager Options");
        }

        Scanner input = new Scanner(System.in);
        int choice = input.nextInt();

        switch (choice) {
            case 1:
                seeAvailableStockMenu(input);
                break;
            case 2:
                shipStockMenu(input);

                warehouseMenu();
                break;
            case 3:
                addStockMenu(input);
                break;
            case 4:
                break;
            case 5:
                if (onlineUser.getSecurityLevel() == SecurityLevel.Employee) {
                    auxiliar.sendErrMsg(choice + " is not a valid choice.");
                    warehouseMenu();
                    break;
                } else {
                    managerMenu();
                    warehouseMenu();
                }
                break;
            default:
                auxiliar.sendErrMsg(choice + " is not a valid choice.");
                warehouseMenu();
                break;
        }
    }

    private void managerMenu() {
        System.out.println("1- See delivery list");
        System.out.println("2- See logIn log");
        System.out.println("3- Back");
    }

    private void addStockMenu(Scanner input) {
        System.out.println("1- Add items");
        System.out.println("2- Back");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("How many items of the same type do you wish to add?");
                int amount = input.nextInt();
                System.out.println("Please introduce the itemType.");
                input.nextLine();
                String itemType = input.nextLine();
                System.out.println("Please introduce the value of the item");
                int value = input.nextInt();

                Item item = new Item(itemType, value, amount);
                int storage = database.getWarehouseStorage(this);
                if (storage + amount < MAX_STORAGE) {
                    System.out.println("HERE");
                    if (database.getItemByItemType(itemType) != null) {
                        System.out.println("HERE");
                        database.updateItemAmount(item, "UP");
                        database.updateWarehouseStorage(this, amount + item.getAmount());
                    } else {
                        database.sendItemToDb(item);
                    }
                }
                break;
            case 2:
                warehouseMenu();
                break;
            default:
                auxiliar.sendErrMsg(choice + " is not a valid choice.");
                addStockMenu(input);
                break;
        }


    }

    private void shipStockMenu(Scanner input) {
        System.out.println("1- Ship stock");
        System.out.println("2- Confirm delivery");
        System.out.println("3- Back");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                System.out.println("How many different items do you wish to ship");
                int numDifItems = input.nextInt();
                String stockList = "";
                HashMap<String, Integer> items = new HashMap<>();
                input.nextLine();
                for (int i = 0; i < numDifItems; i++) {
                    System.out.println("Introduce Item to ship");
                    String item = input.nextLine();
                    System.out.println("How many do ypu wish to ship");
                    int amount = input.nextInt();
                    items.put(item, amount);
                    stockList += "[" + item + "," + amount + "] ";
                    input.nextLine();
                }
                Iterator it = items.entrySet().iterator();
                int value = 0;
                boolean flag = true;
                while (it.hasNext()) {
                    Map.Entry pair = (Map.Entry) it.next();
                    Item item = database.getItemByItemType(pair.getKey().toString());
                    if (item == null) {
                        flag = false;
                        auxiliar.sendErrMsg("The warehouse does not have the item you want to ship: " + pair.getKey().toString());
                        break;
                    } else {
                        value = item.getItemValue() * items.get(item.getItemType());
                    }
                }
                if (flag) {
                    System.out.println("Where do you want to ship?");
                    String location = input.nextLine();
                    ShippedStock stock = new ShippedStock(location, onlineUser.getUserId(), stockList);
                    stock.setValue(value);
                    database.addShipStockToDb(stock);
                }
                break;
            case 2:
                System.out.println("Please insert the ID of the delivery that you want to mark as delivered");
                input.nextLine();
                String id = input.nextLine();
                database.updateDeliveryStatus(id);
                break;
            case 3:
                warehouseMenu();
                break;
            default:
                auxiliar.sendErrMsg(choice + " is not a valid choice.");
                shipStockMenu(input);
                break;
        }
    }

    public void seeAvailableStockMenu(Scanner input) {
        System.out.println("1- See all stock");
        System.out.println("2- See stock from a certain itemType");
        System.out.println("3- Back");

        int choice = input.nextInt();

        switch (choice) {
            case 1:
                LinkedList<Item> items = database.getItems();
                if (items == null)
                    break;

                for (Item item : items) {
                    item.print();
                }
                break;
            case 2:
                input.nextLine();
                System.out.println("What item are you looking for?");
                String itemType = input.nextLine();
                Item item = database.getItemByItemType(itemType);
                if (item == null) {
                    auxiliar.sendErrMsg(itemType + " is not a valid itemType.");
                    break;
                }
                item.print();
                break;
            case 3:
                warehouseMenu();
                break;
            default:
                auxiliar.sendErrMsg(choice + " is not a valid choice.");
                seeAvailableStockMenu(input);
                break;
        }
    }

    public String getWarehouseID() {
        return warehouseID;
    }

    public void setWarehouseID(String warehouseID) {
        this.warehouseID = warehouseID;
    }

    public int getMAX_STORAGE() {
        return MAX_STORAGE;
    }

}
