package app.listener;

import app.database.DatabaseConnection;
import app.model.Person;
import app.model.School;
import app.model.Trainer;
import app.util.DatabaseUtils;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\n[SYSTEM] >>> Starting Application Lifecycle...");
        System.out.println("###################################################");
        System.out.println("#      COHORT 12 DATABASE INITIALIZATION  #");
        System.out.println("###################################################");

        try (Connection conn = DatabaseConnection.getConnection()) {

            if (conn != null && !conn.isClosed()) {
                System.out.println("[SUCCESS] Connected to MySQL successfully.");

                // Dynamically create tables based on your Model files using Reflection
                DatabaseUtils.createTableIfNotExists(conn, Person.class);
                DatabaseUtils.createTableIfNotExists(conn, School.class);
                DatabaseUtils.createTableIfNotExists(conn, Trainer.class);

                System.out.println("[INFO] Reflection Engine: Schema sync completed.");
                System.out.println("[STATUS] >>> APPLICATION IS READY TO GO! <<<");
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] Database connection could not be established!");
            System.err.println("Cause: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("[ERROR] An unexpected error occurred during startup:");
            e.printStackTrace();
        }

        System.out.println("###################################################\n");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\n###################################################");
        System.out.println("#      APPLICATION SHUTTING DOWN...               #");
        System.out.println("###################################################");
        System.out.println("[INFO] Closing resources and cleaning up threads.");
        System.out.println("[STATUS] Database connections released. Goodbye!");
        System.out.println("###################################################\n");
    }
}