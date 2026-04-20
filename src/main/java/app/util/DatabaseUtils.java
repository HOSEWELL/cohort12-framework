package app.util;

import app.model.Person;
import app.model.School;
import app.model.Trainer;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class DatabaseUtils {

    // Map model class to table name and fields (you can extend this)
    private static final Map<Class<?>, String> TABLE_MAP = new HashMap<>();

    static {
        TABLE_MAP.put(Person.class, "persons");
        TABLE_MAP.put(School.class, "schools");
        TABLE_MAP.put(Trainer.class, "trainers");
    }

    /**
     * Creates table dynamically using reflection on the model class
     */
    public static void createTableIfNotExists(Connection conn, Class<?> modelClass) throws Exception {
        String tableName = TABLE_MAP.get(modelClass);
        if (tableName == null) return;

        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS ").append(tableName).append(" (");
        sql.append("id INT AUTO_INCREMENT PRIMARY KEY, ");

        Field[] fields = modelClass.getDeclaredFields();

        for (Field field : fields) {
            String fieldName = field.getName();
            if (fieldName.equals("id")) continue;

            String columnType = getSqlType(field.getType());
            sql.append(fieldName).append(" ").append(columnType).append(", ");
        }

        // Remove last comma and space
        sql.setLength(sql.length() - 2);
        sql.append(")");

        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql.toString());
            System.out.println("able '" + tableName + "' is ready (created via reflection).");
        }
    }

    private static String getSqlType(Class<?> type) {
        if (type == String.class) return "VARCHAR(100)";
        if (type == int.class || type == Integer.class) return "INT";
        if (type == boolean.class || type == Boolean.class) return "BOOLEAN";
        return "VARCHAR(250)";
    }
}