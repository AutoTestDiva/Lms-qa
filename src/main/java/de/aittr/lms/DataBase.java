package de.aittr.lms.fw;

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

    public static ResultSet request(String query) {
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

}














