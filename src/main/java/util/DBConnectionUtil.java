package util;

import lombok.extern.slf4j.Slf4j;
import model.ConfigModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class DBConnectionUtil {
    private static Connection connection = null;
    private static final String PATH_CONFIG = "config.json";

    private DBConnectionUtil() {
        ConfigModel configModel = JsonUtil.createModelFromJSON(PATH_CONFIG,ConfigModel.class);
        try {
            connection = DriverManager.getConnection(configModel.getUrl(), configModel.getUser(), configModel.getPassword());
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static Connection getConnection() {
        if (connection==null) {
            new DBConnectionUtil();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
    }

    public static Statement getStatementExecuteUpdate(String sql) {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
           log.error(e.getMessage());
        }
        return statement;
    }

    public static Statement getStatementExecuteQuery(String sql) {
        Statement statement = null;
        try {
            statement = getConnection().createStatement();
            statement.executeQuery(sql);
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return statement;
    }
}
