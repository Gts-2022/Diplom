package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();
    private static final String url = System.getProperty("db.url");
    private static final String username = System.getProperty("db.user");
    private static final String password = System.getProperty("db.password");

    private SQLHelper() {
    }

    @SneakyThrows
    public static Connection getConn() {
        return DriverManager.getConnection(url, username, password);
    }

    @SneakyThrows
    public static void cleanDataBase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM payment_entity");
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
    }

    @SneakyThrows
    public static String getPaymentGateStatus() {
        var status = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        return runner.query(getConn(), status, new ScalarHandler<>());
    }

    @SneakyThrows
    public static String getCreditGateStatus() {
        var status = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        return runner.query(getConn(), status, new ScalarHandler<>());
    }
}