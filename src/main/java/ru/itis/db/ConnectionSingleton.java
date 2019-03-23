package ru.itis.db;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSingleton {
    private static Connection connection;
    private static DriverManagerDataSource dataSource = new DriverManagerDataSource();

    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            try {
                dataSource.setUsername("postgres");
                dataSource.setPassword("postgres");
                dataSource.setUrl("jdbc:postgresql://localhost:5432/wenwo");
                dataSource.setDriverClassName("org.postgresql.Driver");
                connection= dataSource.getConnection();
//                connection = DriverManager.getConnection(
//                        "jdbc:postgresql://localhost:5432/wenwo",
//                        "postgres",
//                        "postgres");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
