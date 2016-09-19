package com.mypackage.lab9_10.util;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "12345";
    private static final String CONN_STRING = "jdbc:postgresql://127.0.0.1:5432/";
    private static final String DB_NAME = "store";
    private static final Logger logger = Logger.getLogger(ConnectionManager.class);

    /**
     * Get connection to DB
     *
     * @return Connection instance
     */
    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(CONN_STRING + DB_NAME, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            logger.error(e);
            return null;
        }
    }
}
