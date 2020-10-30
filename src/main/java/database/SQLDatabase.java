package database;

import enteties.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class SQLDatabase implements Database {

    private DataSource dataSource = new DataSource();

    private ResultSet baseConnection(String query) {
        String SQL_QUERY = query;
        String SQL = "use warehouse";
        try (Connection con = dataSource.getConnection();) {
            PreparedStatement pst = con.prepareStatement(SQL);
            pst.executeQuery();
            pst = con.prepareStatement(SQL_QUERY);
            ResultSet rs = pst.executeQuery();
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getWarehouseStorage(Warehouse warehouse) {
        ResultSet set = baseConnection(String.format("select storage from warehouse where warehouseId='%s'"
                , warehouse.getWarehouseID()));
        try {
            if (set != null) {
                set.next();
                return set.getInt("storage");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    @Override
    public void updateWarehouseStorage(Warehouse warehouse, int storage) {
        ResultSet set = baseConnection(String.format("update warehouse set storage=%d", storage));
    }

    @Override
    public void sendWarehouseToDB(Warehouse warehouse) {
        ResultSet set = baseConnection(String.format("insert into warehouse (warehouseId) values ('%s');"
                , warehouse.getWarehouseID()));
    }

    @Override
    public void updateDeliveryStatus(String id) {
        ResultSet set = baseConnection(String.format("update shipped set delivered=true where deliveryId='%s'",
                id));
    }

    @Override
    public void sendItemToDb(Item item) {
        ResultSet set = baseConnection(String.format("insert into item (itemType,itemValue,amount) values ('%s',%d,%d);",
                item.getItemType(), item.getItemValue(), item.getAmount()));

    }

    @Override
    public Item getItemByItemType(String itemType) {
        ResultSet set = baseConnection(String.format("select * from item where itemType='%s'", itemType));
        try {
            if (set != null) {
                Item item = new Item();
                while (set.next()) {
                    item.setItemType(set.getString("itemType"));
                    item.setItemValue(set.getInt("itemValue"));
                    item.setAmount(set.getInt("amount"));
                    return item;
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public LinkedList<Item> getItems() {
        ResultSet set = baseConnection("select * from item;");
        try {
            if (set != null) {
                LinkedList<Item> items = new LinkedList<>();
                while (set.next()) {
                    Item item = new Item();
                    item.setItemType(set.getString("itemType"));
                    item.setItemValue(set.getInt("itemValue"));
                    item.setAmount(set.getInt("amount"));
                    items.add(item);
                }
                return items;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateItemAmount(Item item, String choice) {
        int amount = getItemAmountByItemType(item.getItemType());
        if (choice.equals("Up")) {
            amount += item.getAmount();
        } else if (choice.equals("Down")) {
            if (amount > 0)
                amount--;
        }
        ResultSet set = baseConnection(String.format("update item set amount=%d where itemType='%s'",
                amount, item.getItemType()));

    }

    @Override
    public int getItemAmountByItemType(String itemType) {
        ResultSet set = baseConnection(String.format("Select * from item where itemType='%s'", itemType));
        try {
            if (set != null) {
                set.next();
                return set.getInt("amount");
            } else {
                return -1;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return -1;
    }

    @Override
    public User getUserByNameAndPass(String username, String password) {
        ResultSet set = baseConnection(String.format("Select * from user where username='%s' AND password='%s'"
                , username, password));
        try {
            if (set != null) {
                User u = new User();
                while (set.next()) {
                    u.setUsername(set.getString("username"));
                    u.setPassword(set.getString("password"));
                    u.setWarehouseID(set.getString("warehouseId"));
                    u.setUserId(set.getString("userId"));
                    switch (set.getString("securityLevel")) {
                        case "Employee":
                            u.setSecurityLevel(SecurityLevel.Employee);
                            break;
                        case "Manager":
                            u.setSecurityLevel(SecurityLevel.Manager);
                            break;
                        case "Owner":
                            u.setSecurityLevel(SecurityLevel.Owner);
                            break;
                    }
                    return u;
                }
            } else {
                return null;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Override
    public void addShipStockToDb(ShippedStock shippedStock) {
        ResultSet set = baseConnection(String.format("insert into shipped (deliveryId,sockList,location,userId,delivered,value) values ('%s','%s','%s','%s','%s',%d);"
                ,shippedStock.getDeliveryID(),shippedStock.getStockID(),shippedStock.getLocation(),shippedStock.getUser(),"false",shippedStock.getValue()));
    }
}
