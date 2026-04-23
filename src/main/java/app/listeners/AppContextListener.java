package app.listeners;


import app.utility.helper.ClassScanner;
import app.utility.db.DataSourceHelper;
import app.utility.db.TableGenerator;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Set;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        try {
            createDatabaseIfNotExists();

            DataSource ds = DataSourceHelper.getDataSource();

            Set<Class<?>> entities =
                    ClassScanner.scanForDbTables("app.model");

            TableGenerator.generateTables(ds, entities);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDatabaseIfNotExists() {

        try (Connection conn = DriverManager.getConnection(
                DataSourceHelper.getBaseUrlWithoutDB(),
                DataSourceHelper.getUser(),
                DataSourceHelper.getPassword());
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS "
                    + DataSourceHelper.getDbName();

            stmt.executeUpdate(sql);

            System.out.println("Database ensured.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("App shutting down...");
    }
}