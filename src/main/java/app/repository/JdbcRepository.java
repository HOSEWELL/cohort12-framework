package app.repository;

import app.database.DatabaseConnection;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcRepository<T> implements GenericRepository<T> {
    private final Class<T> type;
    private final String tableName;

    public JdbcRepository(Class<T> type) {
        this.type = type;
        this.tableName = type.getSimpleName().toLowerCase() + "s";
    }

    @Override
    public void save(T entity) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Field[] fields = type.getDeclaredFields();
            StringBuilder columns = new StringBuilder();
            StringBuilder placeholders = new StringBuilder();

            for (Field field : fields) {
                if (field.getName().equals("id")) continue;
                columns.append(field.getName()).append(",");
                placeholders.append("?,");
            }
            // Trim last commas
            columns.setLength(columns.length() - 1);
            placeholders.setLength(placeholders.length() - 1);

            String sql = "INSERT INTO " + tableName + " (" + columns + ") VALUES (" + placeholders + ")";
            PreparedStatement ps = conn.prepareStatement(sql);

            int i = 1;
            for (Field field : fields) {
                if (field.getName().equals("id")) continue;
                field.setAccessible(true);
                ps.setObject(i++, field.get(entity));
            }
            ps.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM " + tableName)) {

            while (rs.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    field.setAccessible(true);
                    field.set(instance, rs.getObject(field.getName()));
                }
                list.add(instance);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
}