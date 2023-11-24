package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LessonHelper extends BaseHelperUI{

    public LessonHelper(WebDriver driver) {
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
        click(By.cssSelector("[header='Home work']"));
    }

    public void selectRULanguage() {
        click(By.xpath("//details[contains(., 'На русском')]"));
    }

    public boolean isTheoryContainsText(String text) {
        return isElementPresent(By.xpath("//*[contains(text(),'" + text + "')]"));
    }
}














