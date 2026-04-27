package app.utility.bootstrap;

import app.utility.db.DataSourceHelper;
import app.utility.db.TableGenerator;
import app.utility.helper.ClassScanner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Set;

@InitBootstrap
@Dependent
public class DatabaseBootstrap implements Bootstrap {

    @Inject
    private ClassScanner clazzScanner;

    @Override
    public void process() {
        try {
            createDatabaseIfNotExists();

            DataSource ds = DataSourceHelper.getDataSource();

            Set<Class<?>> entities =
                    clazzScanner.scanForDbTables("app.model");

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
}
