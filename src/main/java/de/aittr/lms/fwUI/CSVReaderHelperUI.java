package de.aittr.lms.fwUI;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

    public List<String>[] readerHelper() {
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
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
    }

    public void clickOnSelectedGroup(String group) {
        click(By.xpath("//span[contains(text(),'" + group + "')]"));
    }

    public void clickOnSelectModule() {
        click(By.xpath("//span[contains(text(),'Select module')]"));
    }

    public void clickOnSelectedModule(String module) {
        click(By.xpath("//div[contains(text(),'" + module + "')]"));
    }

    public void clickOnSelectLesson() {
        click(By.xpath("//span[contains(text(),'Select lesson')]"));
    }

    public void clickOnSelectedLesson(String lesson) {
        click(By.xpath("//div[contains(text(),'" + lesson + "')]"));
    }

    public void clickOnVideoLine() {
        if (isElementPresent(By.xpath("//button[@id='video-toggle']"))) {
            click(By.xpath("//button[@id='video-toggle']"));
        } else {
            System.out.println("Элемента Video на вкладке урока нет");
        }
    }

    public void scrollElementDown(By locator) {
        // Прокрутка вниз на 500 пикселей (можете настроить значение по вашему усмотрению)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500);");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public List<WebElement> findVideoElements() {
        return driver.findElements(By.cssSelector("div.accordion-collapse.collapse.show div.accordion-body app-lesson-video.ng-star-inserted div.ng-star-inserted > div.ng-star-inserted"));
    }

    public List<WebElement> getDropdownListOfGroups() {
        WebElement dropdown = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        List<WebElement> groups = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return groups;
    }

    public List<WebElement> getDropdownListOfModules() {
        WebElement dropdown = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[2]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        List<WebElement> modules = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return modules;
    }

    public List<WebElement> getDropdownListOfLessons() {
        WebElement dropdown = driver.findElement(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[3]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        List<WebElement> lessons = dropdown.findElements(By.xpath("//p-dropdownitem"));
        return lessons;
    }

    public void clickOnNextSelectedLesson() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[3]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
    }

    public void clickOnNextSelectedModule() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[2]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
    }

    public void clickOnNextSelectedGroup() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
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
        return (isElementPresent(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/app-lesson[1]/div[2]/div[4]/div[2]/div[1]/app-lesson-code-list[1]/div[1]/div[2]/div[1]/app-lesson-code[1]/div[1]/div[1]/div[1]/div[1]/button[1]")));
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

    public boolean selectMyGroup(String group) {
        return isElementPresent(By.cssSelector("[aria-label='" + group + "']"));
    }

    public boolean selectMyLesson(String lesson) {
        return isElementPresent(By.xpath("//div[contains(text(),'" + lesson + "')]"));
    }

    public boolean isLessonPresent() {
        return isElementPresent(By.xpath("//span[contains(text(),'Select lesson')]"));
    }

    public boolean selectMyModule(String module) {
        return isElementPresent(By.xpath("//div[contains(text(),'" + module + "')]"));
    }

    public boolean isUploadYourHomeworkPresent() {
        if (isElementPresent(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-home-work[1]/div[1]/div[1]/div[1]/button[1]"))) {
            return true;
        }
        return false;
    }

    public boolean goToMyHomeWorkPresent() {
        if (isElementPresent(By.xpath("//button[@id='navigate-btn-hw']"))) {
            return true;
        }
        return false;
    }

    public boolean isSelectDirectoryToUploadPresent() {
        if (isElementPresent(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/app-home-work-upload[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/label[1]"))) {
            return true;
        }
        return false;
    }

    public void clickGoToMyHomeWork() {
        click(By.xpath("//button[@id='navigate-btn-hw']"));
    }

    public void clickOnUploadYourHomework() {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-home-work[1]/div[1]/div[1]/div[1]/button[1]"));
    }

    public boolean isClearSelectionPresent() {
        if (isElementPresent(By.xpath("//button[@id='submit-upload-hw-cancel']"))) {
            return true;
        }
        return false;
    }

    public void clickOnSelectFilesToUpload() {
        click(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/app-home-work-upload[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/label[1]"));
    }

    public boolean isTotalFilesToUploadPresent() {
        if (isElementPresent(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/app-home-work-upload[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/b[1]"))) {
            return true;
        }
        return false;
    }

    public void clickOnClearSelection() {
        click(By.xpath("//button[@id='submit-upload-hw-cancel']"));
    }

    public void clickOnCloseButtonInUploadHomework() {
        click(By.xpath("//button[@id='close-btn-modal-upload-hw']"));
    }

    public void clickOnSelectDirectoryToUpload() {
        click(By.xpath("//body/ngb-modal-window[1]/div[1]/div[1]/app-home-work-upload[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/label[1]"));
    }

    public void uploadDirectoryOn_SelectDirectoryToUpload() throws AWTException, InterruptedException {
        // Подготовка пути к папке
        String filePath = "C:\\TEST"; // Укажи путь к папке, которую надо загрузить
        // Копируем путь к файлу в буфер обмена
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = new Robot();
        // Подождать некоторое время, чтобы окно выбора папки появилось
        pause(2000);
        // Имитация нажатия на клавиши Control + V для вставки пути к папке из буфера обмена
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        // Имитация нажатия на клавишу Enter для выбора папки
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
       // Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        pause(1000);
        //кнопкой влево т.о. нажимаем в АЛЕРТЕ браузера "Загрузить"
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public void uploadFile_OnSelectFilesToUpload() throws AWTException {
        // Подготовка пути к файлу
        String filePath = "C:\\TEST\\test.docx"; // Укажи путь к файлу, который надо загрузить
        // Копируем путь к файлу в буфер обмена
        StringSelection stringSelection = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
        Robot robot = new Robot();
        // Подождать некоторое время, чтобы окно выбора файла появилось
        pause(2000);
        // Имитация нажатия на клавиши Control + V для вставки пути к файлу из буфера обмена
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        // Подождать некоторое время после ввода пути к файлу
        pause(2000);
        // Имитация нажатия на клавишу Enter для выбора файла
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public boolean isVideoElementsPresent() {
        List<WebElement> videoElements = findVideoElements();
        pause(1000);
        System.out.println(videoElements);
        if (videoElements.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}