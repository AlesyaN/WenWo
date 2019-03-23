package ru.itis.db.dao;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class DataSourceSingleton {
    private static DriverManagerDataSource dataSource;

    public static DriverManagerDataSource getDataSource() {
        try {
            Class.forName("org.postgresql.Driver");
            if (dataSource == null) {
                dataSource = new DriverManagerDataSource();
                dataSource.setUsername("postgres");
                dataSource.setPassword("postgres");
                dataSource.setUrl("jdbc:postgresql://localhost:5432/wenwo");
                dataSource.setDriverClassName("org.postgresql.Driver");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
}
