package de.aittr.lms;

import java.sql.*;

public abstract class DataBase {

    private static Connection connection;

    static {
        try{
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/lms",
                    "root",
                    "$Lilu1987$"
            );
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














