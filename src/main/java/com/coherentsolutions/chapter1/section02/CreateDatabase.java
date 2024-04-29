package com.coherentsolutions.section02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDatabase {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:mysql://localhost:3306/?user=bestuser&password=bestuser";
        try (Connection conn = DriverManager.getConnection(jdbcUrl);
             Statement stmt = conn.createStatement()) {

            /*
            String sql = "CREATE DATABASE example";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");*/

            String sql = "SHOW DATABASES";
            var rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println("Database: " + rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.getMessage());
        }
    }
}
