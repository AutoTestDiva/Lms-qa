package de.aittr.lms;

import java.sql.*;

public abstract class DataBase {

    private static Connection connection;

    static {
        try{
            String usernameDB = System.getenv("DATABASE_USERNAME"); // environment variables
            String userPasswordDB = System.getenv("DATABASE_PASSWORD"); // environment variables
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lms", usernameDB, userPasswordDB);
        } catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

    public static ResultSet requestSelect(String query) {
        try{
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(query);
            result.next();
            return result;
        } catch (SQLException exception){
            exception.printStackTrace();
            return null;
        }
    }

    public static boolean execute(String query) {
        try{
            return connection.createStatement().execute(query);
        } catch (SQLException e){
            return  false;
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














