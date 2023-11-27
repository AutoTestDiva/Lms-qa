package de.aittr.lms.fwUI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GroupHelperUI extends BaseHelperUI{

    public GroupHelperUI(WebDriver driver) {
        super(driver);
    }

    public boolean isCohortInSelectPresent(String cohort) {
        click(By.className("p-dropdown-trigger"));
        return isElementPresent(By.cssSelector("[aria-label='" + cohort + "']"));

    }
}
