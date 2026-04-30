package app.utility.bootstrap;

import app.utility.db.DataSourceHelper;
import app.utility.db.TableGenerator;
import app.utility.helper.ClassScanner;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Set;

@InitBootstrap
@Dependent
public class DatabaseBootstrap implements Bootstrap {

    @Inject
    private ClassScanner clazzScanner;

    @InitBootstrap
    @Inject
    private DataSourceHelper ds;

    @Override
    public void process() {
        try {
            createDatabaseIfNotExists();

            Set<Class<?>> entities = clazzScanner.scanForDbTables("app.model");

            TableGenerator.generateTables(ds.getConnection(), entities);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDatabaseIfNotExists() {

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + ds.getDbName();

            stmt.executeUpdate(sql);

            System.out.println("Database ensured.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
