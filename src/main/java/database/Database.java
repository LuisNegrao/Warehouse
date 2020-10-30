package database;


import enteties.*;

import java.util.LinkedList;

public interface Database {

    void sendWarehouseToDB(Warehouse warehouse);

    int getWarehouseStorage(Warehouse warehouse);

    void updateWarehouseStorage(Warehouse warehouse, int storage);

    void updateDeliveryStatus(String id);

    void sendItemToDb(Item item);

    Item getItemByItemType(String itemType);

    LinkedList<Item> getItems();

    void updateItemAmount(Item item, String choice);

    User getUserByNameAndPass(String username, String password);

    int getItemAmountByItemType(String itemType);

    void addShipStockToDb(ShippedStock shippedStock);

}
