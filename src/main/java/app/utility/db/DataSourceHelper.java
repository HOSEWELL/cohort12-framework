package app.utility.db;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataSourceHelper {

    private static HikariDataSource dataSource;

    private static final String HOST = "localhost";
    private static final int PORT = 3306;
    private static final String DB_NAME = "training_db";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DataSourceHelper() {}

    public static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (DataSourceHelper.class) {
                if (dataSource == null) {
                    HikariConfig config = new HikariConfig();

                    config.setJdbcUrl(
                        "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME +
                        "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"
                    );

                    config.setUsername(USER);
                    config.setPassword(PASSWORD);

                    config.setMaximumPoolSize(10);
                    config.setMinimumIdle(2);
                    config.setIdleTimeout(30000);

                    dataSource = new HikariDataSource(config);
                }
            }
        }
        return dataSource;
    }

    public static String getBaseUrlWithoutDB() {
        return "jdbc:mysql://" + HOST + ":" + PORT +
           "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    }

    public static String getDbName() {
        return DB_NAME;
    }

    public static String getUser() {
        return USER;
    }

    public static String getPassword() {
        return PASSWORD;
    }
}