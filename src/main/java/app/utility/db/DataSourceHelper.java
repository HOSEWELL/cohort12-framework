package app.utility.db;

import app.utility.bootstrap.InitBootstrap;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@InitBootstrap
@ApplicationScoped
public class DataSourceHelper {

    @Resource(lookup = "java:jboss/datasources/Cohort12DS")
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public Connection getConnection() throws SQLException {
        return this.getDataSource().getConnection();
    }
}