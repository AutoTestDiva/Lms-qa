package de.aittr.lms.UITests;

import de.aittr.lms.ApplicationManager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.remote.Browser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

public class TestBaseUI {
  protected List<String> report = new LinkedList<>();

  protected static ApplicationManager app = new ApplicationManager(
          System.getProperty("browser", Browser.CHROME.browserName()));

  Logger logger = LoggerFactory.getLogger(TestBaseUI.class); // надо для report
  @BeforeSuite
  public void setUp(){
    app.init();
  }

  @AfterSuite(enabled = true)
  public void tierDown(){
    app.stop();
  }

  @BeforeMethod
  public void startTest(Method m, Object[] p){
    logger.info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
  }

//  private void printToFile() { // надо метод  для report
//    String dir = "report/";
//    String fileName =
//            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
//                    + "_report.txt";
//    try {
//      Path directories = Files.createDirectories(Path.of(dir));
//      Path file = Files.createFile(Path.of(directories + "/", fileName));
//      BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.toFile(), true));
//      for (String r : report) {
//        bufferedWriter.write(r + System.lineSeparator());
//      }
//      bufferedWriter.close();
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }
//
//  }

  private void printToFile() {
    String dir = "report/";
    String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + "_report.txt";

    System.out.println("File name: " + fileName);
    try {
      // Проверяем, существует ли директория, если нет, то создаем
      if (!Files.exists(Paths.get(dir))) {
        System.out.println("File not exist: " + dir);

        Files.createDirectories(Paths.get(dir));
      }

      System.out.println("create dir " + dir);

      // Создаем файл внутри директории
      Path file = Paths.get(dir, fileName);
      Files.createFile(file);

      try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.toFile(), true))) {

        System.out.println("pring lines " + report);

        for (String r : report) {
          bufferedWriter.write(r + System.lineSeparator());
        }
      }

      logger.info("Test logs have been written to: " + file.toString());
    } catch (IOException e) {
      logger.error("Error writing test logs to file", e);
      throw new RuntimeException(e);
    }
  }

  @AfterMethod
  public void stopTest(ITestResult result){
    if (result.isSuccess()) {
      logger.info("PASSED: " + result.getMethod().getMethodName());
    } else {
      logger.info("FAILED: " + result.getMethod().getMethodName() + " Screenshot: " + app.getUserUI().takeScreenshot());
    }
    logger.info("============== Stop test =================");
    System.out.println("test stopped, try start print to file");
    printToFile();
  }

}