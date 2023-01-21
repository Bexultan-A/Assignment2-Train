package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TrainDB {
    private final String HOST = "jdbc:postgresql://localhost:5432/db_train";
    private final String USERNAME = "postgres";
    private final String PASSWORD = "bq2yeS95";
    private Connection connection;

    //object that connects project to database
    public TrainDB() {
        try {
            connection = DriverManager.getConnection(HOST,USERNAME,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }
}

