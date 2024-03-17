package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReaderHelperUI extends BaseHelperUI {
    public CSVReaderHelperUI(WebDriver driver) {
        super(driver);
    }
    public  List<String>[] readerHelper() {
        // Путь к вашему CSV-файлу
        String csvFilePath = "src/test/resources/DataScv/GetCombinationData.csv";
        // Создаем два списка для хранения данных
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        // Создаем массив для хранения списков
        List<String>[] csvDataOfLists = new List[]{list1, list2, list3};
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
     } catch (IOException e) {
            e.printStackTrace();
        }
        return csvDataOfLists;
    }

    // Метод для разделения строки CSV на список
    private static List<String> parseCsvLine(String csvLine) {
        String[] values = csvLine.split(",");
        return Arrays.asList(values);
    }

    public void clickOnSelectYourGroup() {
        click(By.xpath("//span[contains(text(),'Select your group')]"));
    }

    public void clickOnSelectedGroup(String group) {
        click(By.xpath("//*/text()[normalize-space(.)='" + group + "']/parent::*"));
    }

    public void clickOnSelectModule() {
        click(By.xpath("//span[contains(text(),'Select module')]"));
    }

    public void clickOnSelectedModule(String module) {
        click(By.xpath("//*/text()[normalize-space(.)='" + module + "']/parent::*"));
    }

    public void clickOnSelectLesson() {
        click(By.xpath("//span[contains(text(),'Select lesson')]"));
    }

    public void clickOnSelectedLesson(String lesson) {
        click(By.xpath("//*/text()[normalize-space(.)='" + lesson + "']/parent::*"));
    }

    public void clickOnVideoLine() {
        if (isElementPresent(By.cssSelector("#video-toggle"))) {
           clickWithScroll(By.cssSelector("#video-toggle"));
        } else {
            System.out.println("Элемента Video на вкладке урока нет");
        }
    }
    public void clickWithScroll(By locator) {
        // Прокрутка вниз на 500 пикселей (можете настроить значение по вашему усмотрению)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500);");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Выполнение клика
        driver.findElement(locator).click();
    }




    public List<WebElement> findVideoElements() {
        return driver.findElements(By.xpath("//div[@id='video-collapse']"));
    }

    //------------------------------------------------------------------
    public List<WebElement> getDropdownListOfGroups() {
        WebElement dropdown = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        List<WebElement> groups = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return groups;
    }

    public List<WebElement> getDropdownListOfModules() {
        WebElement dropdown = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[2]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        List<WebElement> modules = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return modules;
    }

    public List<WebElement> getDropdownListOfLessons() {
        WebElement dropdown = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[3]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        List<WebElement> lessons = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return lessons;
    }

    public void clickOnNextSelectedLesson() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[3]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
    }

    public void clickOnNextSelectedModule() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[2]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
    }

    public void clickOnNextSelectedGroup() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
    }

    public boolean isModulePresent() {
        return isElementPresent(By.xpath("//span[contains(text(),'Select module')]"));
    }

    public boolean isTextPresent() {
        // Находим элемент с тегом <ol>
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        WebElement tabContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li")));
        // Проверьте, есть ли текст внутри элемента
        return (!tabContent.getText().isEmpty());
    }

    public void clickOnPlanLine() {
        click(By.cssSelector("#plan-toggle"));
    }

    public void clickOnTheoryLine() {
       click(By.cssSelector("#theory-toggle"));
    }

    public void clickOnHomeWorkLine() {
        click(By.cssSelector("#home_work-toggle"));
    }

    public void clickOnCodeLine() {
        click(By.cssSelector("#code-toggle"));
    }

    public void scrollPageUp() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0);");
    }

    public boolean isCodePresent() {
        return (isElementPresent(By.xpath("//button[.='Copy']")) &&
                isElementPresent(By.xpath("//button[.='Download File']")));
    }

    public boolean isPlanLinePresent() {
        return (isElementPresent(By.cssSelector("#plan-toggle")));
    }

    public boolean isTheoryLinePresent() {
      return (isElementPresent(By.cssSelector("#theory-toggle")));
    }

    public boolean isHomeWorkLinePresent() {
        return (isElementPresent(By.cssSelector("#home_work-toggle")));
    }

    public boolean isCodeLinePresent() {
        return (isElementPresent(By.cssSelector("#code-toggle")));
    }

    public boolean isVideoLinePresent() {
        return (isElementPresent(By.cssSelector("#video-toggle")));
    }

//    public void selectGroup(String cohort) {
//        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[1]/div[1]/div[1]/div[1]/span[1]/p-dropdown[1]/div[1]/div[2]/span[1]"));
//        click(By.cssSelector("[aria-label='" + cohort + "']"));
//    }
//
//    public void selectModule(String module) {
//        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[1]/div[1]/div[1]/div[2]/span[1]/p-dropdown[1]/div[1]/div[2]/span[1]"));
//        click(By.cssSelector("[aria-label='" + module + "']"));
//    }
//
//    public boolean isGroupPresent() {
//        return isElementPresent(By.xpath("//span[contains(text(),'Select your group')]"));
//    }

    public boolean selectMyGroup(String group) {
        return isElementPresent(By.cssSelector("[aria-label='" + group + "']"));
    }


//    public void selectLesson(String lesson) {
//        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[2]/div[1]/app-lessons-list[1]/div[1]/div[1]/div[1]/div[1]/div[3]/span[1]/p-dropdown[1]/div[1]/div[2]/span[1]"));
//        click(By.cssSelector("[aria-label='" + lesson + "']"));
//    }
//
//    public void clickOnMyGroup(String group) {
//        click(By.cssSelector("[aria-label='" + group + "']"));
//    }


    public boolean selectMyLesson(String lesson) {
        return isElementPresent(By.cssSelector("[aria-label='" + lesson + "']"));
    }

//    public void clickOnMyLesson(String lesson) {
//        click(By.cssSelector("[aria-label='" + lesson + "']"));
//
//    }

    public boolean isLessonPresent() {
        return isElementPresent(By.xpath("//span[contains(text(),'Select lesson')]"));
    }

    public boolean selectMyModule(String module) {
        return isElementPresent(By.cssSelector("[aria-label='" + module + "']"));
    }

    public boolean isUploadHomeWorkSolutionPresent() {
        if (isElementPresent(By.xpath("//button[contains(text(),'Upload home work solution')]"))) {
            return true;
        }
        return false;

    }

    public boolean isMyHomeWorkPresent() {
        if (isElementPresent(By.xpath("//button[contains(text(),'My home work')]"))) {
           return true;
        }
        return false;

    }


}


