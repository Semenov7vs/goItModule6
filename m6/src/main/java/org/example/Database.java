package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database INSTANCE = new Database();
    private static Connection connection;
    private final String url = "jdbc:h2:file:C:/Users/these/Desktop/DataBase/test2";
    private final String username = "sa";
    private final String password = "1";

    private Database() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {
        return INSTANCE;
    }

    public static Connection getConnection() {
        return connection;
    }
}
