package de.aittr.lms.fwUI;

import org.openqa.selenium.*;

public class LessonHelperUI extends BaseHelperUI{

    public LessonHelperUI(WebDriver driver) {
        super(driver);
    }

    public void selectStudentGroup(String cohort) {
        click(By.xpath("//body/app-root[1]/app-layout[1]/div[1]/div[1]/div[1]/app-student-cabinet[1]/div[1]/app-lessons-list[1]/div[1]/div[1]/p-dropdown[1]/div[1]/div[1]/chevrondownicon[1]/*[1]"));
        click(By.xpath("//span[contains(text(),'" + cohort + "')]"));
    }

    public void selectModule(String module) {
        click(By.xpath("//span[contains(text(),'Select module')]"));
        click(By.xpath("//div[contains(text(),'" + module + "')]"));
    }

    public void selectLesson(String lesson) {
        click(By.xpath("//span[contains(text(),'Select lesson')]"));
        click(By.xpath("//div[contains(text(),'" + lesson + "')]"));
    }
       public void selectLessonByModuleByCohort(String cohort, String module, String lesson) {
        selectStudentGroup(cohort);
        selectModule(module);
        selectLesson(lesson);
    }
  }














