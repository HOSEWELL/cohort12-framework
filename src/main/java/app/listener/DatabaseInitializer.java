package app.listener;

import app.database.DatabaseConnection;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("========================================");
        System.out.println("Initializing Database Schema...");
        System.out.println("========================================");

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // 1. Create Database if it doesn't exist
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS cohort12_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
            System.out.println("Database 'cohort12_db' is ready.");

            // 2. Create Tables if they don't exist
            String createPersons =
                    "CREATE TABLE IF NOT EXISTS persons (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(100) NOT NULL, " +
                            "nationalId VARCHAR(50) UNIQUE" +
                            ")";

            String createSchools =
                    "CREATE TABLE IF NOT EXISTS schools (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "schoolName VARCHAR(100) NOT NULL, " +
                            "schoolLocation VARCHAR(100)" +
                            ")";

            String createTrainers =
                    "CREATE TABLE IF NOT EXISTS trainers (" +
                            "id INT AUTO_INCREMENT PRIMARY KEY, " +
                            "name VARCHAR(100) NOT NULL, " +
                            "gender VARCHAR(20)" +
                            ")";

            stmt.executeUpdate(createPersons);
            stmt.executeUpdate(createSchools);
            stmt.executeUpdate(createTrainers);

            System.out.println("All tables (persons, schools, trainers) are ready.");

        } catch (SQLException e) {
            System.err.println("Database Initialization Failed!");
            System.err.println("   Error: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("========================================\n");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Application shutting down.");
    }
}