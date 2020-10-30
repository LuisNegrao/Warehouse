package database;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {

    private static final String DB_USERNAME = "user";
    private static final String DB_PASSWORD = "password";
    private static final String DB_URL = "jdbcUrl";
    private static final String DB_CLASS_NAME = "className";

    private static HikariConfig config = new HikariConfig();
    private static HikariDataSource dataSource;
    private static Properties properties = null;

    static {
        try {
            properties = new Properties();
            properties.load(new FileInputStream("src/main/java/database/database.properties"));
            config.setJdbcUrl(properties.getProperty(DB_URL));
            config.setDataSourceClassName(properties.getProperty(DB_CLASS_NAME));
            config.setUsername(properties.getProperty(DB_USERNAME));
            config.setPassword(properties.getProperty(DB_PASSWORD));
            dataSource = new HikariDataSource(config);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public DataSource() {
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


    /*public ResultSet connect(String query){
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
    }*/

}
