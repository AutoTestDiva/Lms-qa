package de.aittr.lms.fwUI;

import org.openqa.selenium.*;
import java.util.List;

public class LessonHelperUI extends BaseHelperUI{

    public LessonHelperUI(WebDriver driver) {
        super(driver);
    }

    public void selectStudentGroup(String cohort) {
        click(By.className("p-dropdown-trigger"));
        click(By.cssSelector("[aria-label='" + cohort + "']"));
    }

    public void selectModule(String module) {
        click(By.cssSelector("[placeholder='Select module']"));
        click(By.cssSelector("[aria-label='" + module + "']"));
    }

    public void selectLesson(String lesson) {
        click(By.cssSelector("[placeholder='Select lesson']"));
        click(By.cssSelector("[aria-label='" + lesson + "']"));
    }

    public void clickOnVideoPart() {
        click(By.id("p-accordiontab-9"));
    }

    public void clickOnTheory(String language) {
        click(By.xpath("//span[contains(text(), ' Theory ')]"));
        if(isElementPresent(By.xpath("//details[contains(., 'English')]"))
                && !language.isEmpty()){
            if(language.equalsIgnoreCase("ru")){
                click(By.xpath("//details[contains(., 'На русском')]"));
            } else if (language.equalsIgnoreCase("en")) {
                click(By.xpath("//details[contains(., 'English')]"));
            }
        }
    }

    public void clickOnHomeWork() {
        click(By.cssSelector("[header='Home work']"));
    }

    public void selectRULanguage() {
        click(By.xpath("//details[contains(., 'На русском')]"));
    }

    public boolean isTheoryContainsText(String text) {
        return isElementPresent(By.xpath("//*[contains(text(),'" + text + "')]"));
    }

    public void selectLessonByModuleByCohort(String cohort, String module, String lesson) {
        selectStudentGroup(cohort);
        selectModule(module);
        selectLesson(lesson);
    }

    public void clickOnRUTheory() { // bette use clickOnTheory(String language)
        // universal if choose language exist
        clickOnTheory("");
        selectRULanguage();
    }

    public boolean isAllVideoInLessonEnabled() {
        click(By.xpath("//span[contains(text(),'Video')]"));
        scrollDown(70);

        boolean result = true;
        double videoDuration = 0;

        List<WebElement> videoElements = driver.findElements(By.tagName("video"));
        for(WebElement video : videoElements){
            try{
                 videoDuration = getVideoDuration(video);
            }catch (ScriptTimeoutException e){
                System.out.println("Timeout waiting for video to be visible or meet condition."); // не пишет
                result = false;
            }catch (Exception e){
                System.out.println("The video is not displayed. ");
                result = false;
            }

            if(videoDuration == 0){
                result = false;
            }
        }

        return result;
    }

    private double getVideoDuration(WebElement videoPlayer) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        double duration = (Double) jsExecutor.executeAsyncScript(
                "var callback = arguments[arguments.length - 1];" +
                        "var video = arguments[0];" +
                        "video.onloadedmetadata = function() {" +
                        "  callback(video.duration);" +
                        "};" +
                        "video.load();", videoPlayer);
        System.out.println("********** Video length = " + duration + " (sec)");
        return duration;
    }


}














