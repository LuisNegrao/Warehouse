package enteties;

import java.util.UUID;

public class ShippedStock {

    private String deliveryID;
    private String stockID;
    private String location;
    private String userId;
    private boolean delivered;
    private int value;

    public ShippedStock(String location, String user, String delivery) {
        this.deliveryID = UUID.randomUUID().toString();
        this.location = location;
        this.userId = user;
        this.delivered = false;
        this.stockID = delivery;
        this.value = 0;
    }

    public void send() {
        //TODO remove pallets and items form db and put them in shipping column;
    }

    public void remove() {
        //TODO delivered = true + update db
    }

    public String getDeliveryID() {
        return deliveryID;
    }

    public String getLocation() {
        return location;
    }

    public String getUser() {
        return userId;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public String getStockID() {

        return stockID;
    }

    public void setDeliveryID(String deliveryID) {
        this.deliveryID = deliveryID;
    }

    public void setStockID(String stockID) {
        this.stockID = stockID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
