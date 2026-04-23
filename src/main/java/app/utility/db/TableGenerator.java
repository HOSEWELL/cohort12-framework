package app.utility.db;

import app.framework.DbColumn;
import app.framework.DbTable;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TableGenerator {

    public static void generateTables(DataSource ds, Set<Class<?>> entityClasses) {
        for (Class<?> clazz : entityClasses) {

            if (!clazz.isAnnotationPresent(DbTable.class)) continue;

            DbTable table = clazz.getAnnotation(DbTable.class);
            String tableName = table.name();

            List<String> columns = new ArrayList<>();
            String primaryKey = null;

            for (Field field : clazz.getDeclaredFields()) {

                if (!field.isAnnotationPresent(DbColumn.class)) continue;

                DbColumn col = field.getAnnotation(DbColumn.class);

                String columnDef = col.name() + " " + col.type();

                if (col.autoIncrement()) {
                    columnDef += " AUTO_INCREMENT";
                }

                columns.add(columnDef);

                if (col.primaryKey()) {
                    primaryKey = col.name();
                }
            }

            if (primaryKey != null) {
                columns.add("PRIMARY KEY (" + primaryKey + ")");
            }

            String sql = "CREATE TABLE IF NOT EXISTS " + tableName +
                    " (" + String.join(", ", columns) + ")";

            execute(ds, sql);
        }
    }

    private static void execute(DataSource ds, String sql) {
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            System.out.println("Executed: " + sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}