package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelper extends BaseHelperUI{

    public GroupHelper(WebDriver driver) {
        super(driver);
    }

    public boolean isCohortInSelectPresent(String cohort) {
        click(By.className("p-dropdown-trigger"));
        if(!isElementPresent(By.cssSelector("[aria-label='" + cohort + "']"))){
            return false;
        }
        return true;
    }
}
