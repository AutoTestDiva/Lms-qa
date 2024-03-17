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

    @DataProvider(name = "provideGetVideoData")
    public static Object[][] provideGetVideoData() {
        String csvFile = "src/test/resources/DataScv/GetVideoData.csv";
        return readDataFromCSV(csvFile);
    }


    @DataProvider(name = "provideGetGroupData")
    public static Object[][] provideGetGroupData() {
        String csvFile = "src/test/resources/DataScv/GetGroupData.csv";
        return readDataFromCSV(csvFile);
    }
    @DataProvider(name = "provideGetGroupAndModuleData")
    public static Object[][] provideGetGroupAndModuleData() {
        String csvFile = "src/test/resources/DataScv/GetGroupAndModuleData.csv";
        return readDataFromCSV(csvFile);
    }


    @DataProvider(name = "provideGetGroupModuleLessonData")
    public static Object[][] provideGetGroupModuleLessonData() {
        String csvFile = "src/test/resources/DataScv/GetGroupModuleLessonData.csv";
        return readDataFromCSV(csvFile);
    }
    @DataProvider(name = "provideGetCombinationData")
    public static Object[][] provideGetCombinationData() {
        String csvFile = "src/test/resources/DataScv/GetCombinationData.csv";
        return readDataFromCSV(csvFile);
    }


    @DataProvider(name = "provideUploadHomeWorkSolutionData")
    public static Object[][] provideUploadHomeWorkSolutionData() {
        String csvFile = "src/test/resources/DataScv/UploadHomeWorkSolutionData.csv";
        return readDataFromCSV(csvFile);
    }

    @DataProvider(name = "provideMyHomeWorkData")
    public static Object[][] provideMyHomeWorkData() {
        String csvFile = "src/test/resources/DataScv/MyHomeWorkData.csv";
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
