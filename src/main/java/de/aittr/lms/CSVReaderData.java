package de.aittr.lms;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Создаем три листа под когорты, модули, уроки из csv-файла.
// Причем, если каких-либо данных нет, лист будет пустой

public class CSVReaderData {
    public static void main(String[] args) {
            // Путь к вашему CSV-файлу
            String csvFilePath = "src/test/resources/DataScv/GetCombinationData.csv";

            // Создаем два списка для хранения данных
            List<String> list1 = new ArrayList<>();
            List<String> list2 = new ArrayList<>();
            List<String> list3 = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            // Считываем первую строку
            String line = br.readLine();
            if (line != null) {
                list1.addAll(parseCsvLine(line));
            }

            // Считываем вторую строку
            line = br.readLine();
            if (line != null) {
                list2.addAll(parseCsvLine(line));
            }

            // Считываем третью строку
            line = br.readLine();
            if (line != null) {
                list3.addAll(parseCsvLine(line));
            }

            // Ваши списки теперь содержат данные из соответствующих строк
            //System.out.println("List 1: " + list1);
            //System.out.println("List 2: " + list2);
            //System.out.println("List 3: " + list3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод для разделения строки CSV на список
    private static List<String> parseCsvLine(String csvLine) {
        String[] values = csvLine.split(",");
        return Arrays.asList(values);
    }
}
