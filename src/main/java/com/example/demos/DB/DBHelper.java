package com.example.demos.DB;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Singleton which helps to get DB connections using Hikari Connection Pooling
 * Use getInstance() method to get access
 */
public class DBHelper {

    public static final String HOST = "localhost";
    public static final String DB_NAME = "delivery";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "qazwsxedcA7?";

    private static volatile DBHelper instance;

    private final HikariDataSource ds;

    private DBHelper() {
        String hostname = System.getProperty(HOST);
        String dbName = System.getProperty(DB_NAME);
        String userName = System.getProperty(USERNAME);
        String password = System.getProperty(PASSWORD);

        //configuring db connection and pooling with recommended params
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost/delivery");
        config.setUsername("root");
        config.setPassword("qazwsxedcA7?");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
        config.addDataSourceProperty("useServerPrepStmts", "true");
        config.addDataSourceProperty("useLocalSessionState", "true");
        config.addDataSourceProperty("rewriteBatchedStatements", "true");
        config.addDataSourceProperty("cacheResultSetMetadata", "true");
        config.addDataSourceProperty("cacheServerConfiguration", "true");
        config.addDataSourceProperty("elideSetAutoCommits", "true");
        config.addDataSourceProperty("maintainTimeStats", "false");

        ds = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public void shutdown() {

        ds.close();
    }

    public static DBHelper getInstance() {
        DBHelper localInstance = instance;
        if (localInstance == null) {
            synchronized (DBHelper.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new DBHelper();
            }
        }
        return localInstance;
    }

}