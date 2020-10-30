package enteties;

public class User {

    private String username;
    private String password;
    private Enum securityLevel;
    private String warehouseID;
    private String userId;

    public User(String username, String password, String warehouseID, String userId) {
        this.username = username;
        this.password = password;
        this.warehouseID = warehouseID;
        this.securityLevel = SecurityLevel.Employee;
        this.userId = userId;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public Enum getSecurityLevel() {
        return securityLevel;
    }

    public String getWarehouseID() {
        return warehouseID;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSecurityLevel(Enum securityLevel) {
        this.securityLevel = securityLevel;
    }

    public void setWarehouseID(String warehouseID) {
        this.warehouseID = warehouseID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

