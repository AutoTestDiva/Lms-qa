package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

    public void clickOnTheory() {
        click(By.cssSelector("[header=' Theory ']"));
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

    public void clickOnRUTheory() {
        clickOnTheory();
        selectRULanguage();
    }

    public boolean isVideoDisplayed() {

        return false;
    }
}














