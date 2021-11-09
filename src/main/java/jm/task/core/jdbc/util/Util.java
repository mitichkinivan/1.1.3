package jm.task.core.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static String URL = "jdbc:mysql://localhost:3306/test";
    private static String USERNAME = "root";
    private static String PASSWORD = "root1";

    public static Connection getConnection() {

        Properties properties = new Properties();

        try (InputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);

            URL = properties.getProperty("URL");
            USERNAME = properties.getProperty("USERNAME");
            PASSWORD = properties.getProperty("PASSWORD");

        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}