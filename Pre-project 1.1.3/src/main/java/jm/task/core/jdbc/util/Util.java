package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/DB 1.1.3";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "java_dev";

    static {
        // Connection connection;

        //try {
        //   Driver driver = new com.mysql.cj.jdbc.Driver();
        //    DriverManager.registerDriver(driver);

        //   connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //    if (!connection.isClosed()) {
                //       System.out.println("Соединение с БД установлено!");
        //    }
        //    }
        //} catch (SQLException e) {
        //    System.err.println("Не удалось загрузить класс драйвера!");
        //}
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Не удалось загрузить класс драйвера!");
        }

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

}
