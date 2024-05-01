package de.aittr.lms.UITests;

import de.aittr.lms.CSVDataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupModuleLessonInfoTests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("a04@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }

    @Test(dataProvider = "provideGetGroupModuleLessonData", dataProviderClass = CSVDataProviders.class)
    public void isGroupModuleLessonInfoTest(String group, String module, String lesson) {
        report.add("isGroupModuleLessonInfoTest" + System.lineSeparator());
        app.getUserUI().isGroupPresent();
        app.getUserUI().clickOnSelectYourGroup();

        if (app.getCSVReaderUI().selectMyGroup(group)) {
            app.getUserUI().clickOnMyGroup(group);
            report.add("********************************************************************");
            report.add("                       Group: " + group);
            report.add("********************************************************************");

            if (app.getCSVReaderUI().isModulePresent()) {
                app.getUserUI().selectModule(module);
                report.add("--------------------------------------------------------------------");
                report.add("                       Module: " + module);
                report.add("--------------------------------------------------------------------");
                report.add("     Lesson   |  Plan  |  Theory  |  Home work  |  Code  |  Video  |");
                report.add("--------------------------------------------------------------------");

                app.getCSVReaderUI().clickOnSelectLesson();
                if (app.getCSVReaderUI().selectMyLesson(lesson)) {
                    app.getUserUI().clickOnSelectedLesson(lesson);

                    String plan = "o";
                    String theory = "o";
                    String homeWork = "o";
                    String code = "o";
                    String video = "o";

                    if (app.getCSVReaderUI().isPlanLinePresent()) {
                        // Проверяем вкладку ПЛАН и наличие там текста
                        app.getCSVReaderUI().clickOnPlanLine();
                        if (app.getCSVReaderUI().isTextPresent()) {
                            plan = "+";
                        } else {
                            plan = "-";
                        }
                        app.getCSVReaderUI().clickOnPlanLine();
                        app.getCSVReaderUI().pause(500);
                    }
                    if (app.getCSVReaderUI().isTheoryLinePresent()) {
                        // Проверяем вкладку Theory
                        app.getCSVReaderUI().clickOnTheoryLine();
                        if (app.getCSVReaderUI().isTextPresent()) {
                            theory = "+";
                        } else {
                            theory = "-";
                        }
                        app.getCSVReaderUI().clickOnTheoryLine();
                        app.getCSVReaderUI().pause(500);
                    }
                    if (app.getCSVReaderUI().isHomeWorkLinePresent()) {
                        // Проверяем вкладку Домашка
                        app.getCSVReaderUI().clickOnHomeWorkLine();
                        if (app.getCSVReaderUI().isTextPresent()) {
                            homeWork = "+";
                        } else {
                            homeWork = "-";
                        }
                        app.getCSVReaderUI().clickOnHomeWorkLine();
                        app.getCSVReaderUI().pause(500);
                    }
                    if (app.getCSVReaderUI().isCodeLinePresent()) {
                        //Проверяем наличие кода
                        app.getCSVReaderUI().clickOnCodeLine();
                        if (app.getCSVReaderUI().isCodePresent()) {
                            code = "+";
                        } else {
                            code = "-";
                        }
                        app.getCSVReaderUI().clickOnCodeLine();
                        app.getCSVReaderUI().pause(500);
                    }
                    if (app.getCSVReaderUI().isVideoLinePresent()) {
                        // Проверяем наличие
                        app.getCSVReaderUI().scrollElementDown(By.xpath("//button[@id='video-toggle']"));
                        app.getCSVReaderUI().clickOnVideoLine();
                        // Использование метода для поиска видеоэлементов
                        List<WebElement> videoElements = app.getCSVReaderUI().findVideoElements();
                        // Проверьте, что найдено хотя бы одно видео
                        app.getCSVReaderUI().pause(1000);
                        if (videoElements.isEmpty()) {
                            video = "-";
                        } else {
                            video = "" + videoElements.size();

                        }
                        app.getCSVReaderUI().pause(1000);
                        app.getCSVReaderUI().clickOnVideoLine();
                        app.getCSVReaderUI().pause(1000);
                    }
                    report.add(String.format("    %-5s |   %-5s|    %-5s |      %-5s  |   %-5s|    %-5s%n", lesson, plan, theory, homeWork, code, video + "    |"));
                } else {
                    report.add("Данного урока еще нет");
                }
            } else {
                report.add("В данной группе модулей еще нет");
            }
            app.getUserUI().scrollPageUp();
        } else {
            report.add("Данной группы еще нет");
        }
    }

    @AfterMethod
    public void postCondition() {
        app.getUserUI().logOut();
    }
}