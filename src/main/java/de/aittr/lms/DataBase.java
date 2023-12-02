package de.aittr.lms;

import lombok.Value;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.sql.*;
import java.util.Map;

public abstract class DataBase {

    private static Connection connection;


    static {
        InputStream inputStream = DataBase
                .class
                .getClassLoader()
                .getResourceAsStream("application.yml");
        Yaml yaml = new Yaml();
        Map<String, Object> load = yaml.load(inputStream);

        String usernameDB = (String) load.get("username");
        String userPasswordDB = (String) load.get("password");
        String dbUrl = (String) load.get("url");

        try {
//            String usernameDB = System.getenv("DATABASE_USERNAME"); // environment variables
//            String userPasswordDB = System.getenv("DATABASE_PASSWORD"); // environment variables
            connection = DriverManager.getConnection(
                    dbUrl, usernameDB, userPasswordDB);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static ResultSet requestSelect(String query) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            result.next();
            return result;
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean execute(String query) {
        try {
            return connection.createStatement().execute(query);
        } catch (SQLException e) {
            return false;
        }
    }

    public static void requestDelete(String query) {

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}














