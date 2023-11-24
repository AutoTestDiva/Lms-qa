package de.aittr.lms;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.util.List;

public class CSVDataProviders {

    @DataProvider(name = "provideWrongUserData")
    public static Object[][] provideWrongUserData() {
        String csvFile = "src/test/resources/DataScv/WrongUserData.csv";
        return readDataFromCSV(csvFile);
    }

    @DataProvider(name = "provideUsersForLoginTest")
    public static Object[][] provideUsersForLoginTest() {
        String csvFile = "src/test/resources/DataScv/UsersForLoginTest.csv";
        return readDataFromCSV(csvFile);
    }

    @DataProvider(name = "provideNotValidPassword")
    public static Object[][] provideNotValidPassword() {
        String csvFile = "src/test/resources/DataScv/NotValidPassword.csv";
        return readDataFromCSV(csvFile);
    }

    private static Object[][] readDataFromCSV(String csvFile) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))) {
            List<String[]> data = reader.readAll();
            Object[][] dataArray = new Object[data.size()][];
            for (int i = 0; i < data.size(); i++) {
                dataArray[i] = data.get(i);
            }
            return dataArray;
        } catch (IOException | CsvException e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }


}
