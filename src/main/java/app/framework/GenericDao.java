package app.framework;


import app.utility.db.DataSourceHelper;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class GenericDao<T, ID> {

    private final Class<T> entityClass;
    private final String tableName;
    private final List<Field> columns = new ArrayList<>();
    private Field idField;

    private final DataSource ds;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.ds = DataSourceHelper.getDataSource();

        if (!entityClass.isAnnotationPresent(DbTable.class)) {
            throw new RuntimeException("Missing @DbTable on " + entityClass.getName());
        }

        this.tableName = entityClass.getAnnotation(DbTable.class).name();

        for (Field field : entityClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(DbColumn.class)) {
                field.setAccessible(true);
                columns.add(field);

                if (field.getAnnotation(DbColumn.class).primaryKey()) {
                    idField = field;
                }
            }
        }

        if (idField == null) {
            throw new RuntimeException("No primary key defined in " + entityClass.getName());
        }
    }

    // ---------------------------
    // CREATE
    // ---------------------------
    public void save(T entity) {
        try (Connection conn = ds.getConnection()) {

            List<String> colNames = new ArrayList<>();
            List<String> placeholders = new ArrayList<>();
            List<Object> values = new ArrayList<>();

            for (Field field : columns) {
                DbColumn col = field.getAnnotation(DbColumn.class);

                if (col.autoIncrement()) continue;

                colNames.add(col.name());
                placeholders.add("?");

                values.add(field.get(entity));
            }

            String sql = "INSERT INTO " + tableName +
                    " (" + String.join(",", colNames) + ") VALUES (" +
                    String.join(",", placeholders) + ")";

            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < values.size(); i++) {
                ps.setObject(i + 1, values.get(i));
            }

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // READ BY ID
    // ---------------------------
    public T findById(ID id) {
        try (Connection conn = ds.getConnection()) {

            String idColumn = idField.getAnnotation(DbColumn.class).name();

            String sql = "SELECT * FROM " + tableName +
                    " WHERE " + idColumn + " = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // ---------------------------
    // READ ALL
    // ---------------------------
    public List<T> findAll() {
        List<T> list = new ArrayList<>();

        try (Connection conn = ds.getConnection()) {

            String sql = "SELECT * FROM " + tableName;

            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(mapResultSet(rs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // ---------------------------
    // UPDATE
    // ---------------------------
    public void update(T entity) {
        try (Connection conn = ds.getConnection()) {

            List<String> setParts = new ArrayList<>();
            List<Object> values = new ArrayList<>();

            String idColumn = idField.getAnnotation(DbColumn.class).name();
            Object idValue = idField.get(entity);

            for (Field field : columns) {
                DbColumn col = field.getAnnotation(DbColumn.class);

                if (col.primaryKey()) continue;

                setParts.add(col.name() + "=?");
                values.add(field.get(entity));
            }

            String sql = "UPDATE " + tableName +
                    " SET " + String.join(",", setParts) +
                    " WHERE " + idColumn + "=?";

            PreparedStatement ps = conn.prepareStatement(sql);

            int index = 1;

            for (Object val : values) {
                ps.setObject(index++, val);
            }

            ps.setObject(index, idValue);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // DELETE
    // ---------------------------
    public void delete(ID id) {
        try (Connection conn = ds.getConnection()) {

            String idColumn = idField.getAnnotation(DbColumn.class).name();

            String sql = "DELETE FROM " + tableName +
                    " WHERE " + idColumn + "=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setObject(1, id);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------------------
    // MAPPER
    // ---------------------------
    private T mapResultSet(ResultSet rs) throws Exception {

        T instance = entityClass.getDeclaredConstructor().newInstance();

        for (Field field : columns) {
            DbColumn col = field.getAnnotation(DbColumn.class);

            Object value = rs.getObject(col.name());
            field.set(instance, value);
        }

        return instance;
    }
}