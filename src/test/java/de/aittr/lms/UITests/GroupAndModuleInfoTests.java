package de.aittr.lms.UITests;

import de.aittr.lms.CSVDataProviders;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GroupAndModuleInfoTests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("a04@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }

    @Test(dataProvider = "provideGetGroupAndModuleData", dataProviderClass = CSVDataProviders.class)
    public void isGroupAndModuleInfoTests(String group, String module) {
        report.add("isGroupAndModuleInfoTests" + System.lineSeparator());
        app.getUserUI().isGroupPresent();
        app.getUserUI().clickOnSelectYourGroup();
        if (app.getUserUI().selectMyGroup(group)) {
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

                app.getUserUI().clickOnSelectLesson();
                List<WebElement> lessons = app.getUserUI().getDropdownListOfLessons();
                List<String> listOfLessons = new ArrayList<>();
                for (WebElement item : lessons) {
                    listOfLessons.add(item.getText());
                }
                for (int i = 0; i < listOfLessons.size(); i++) {
                    app.getUserUI().clickOnSelectedLesson(listOfLessons.get(i));

                    String plan = "o";
                    String theory = "o";
                    String homeWork = "o";
                    String code = "o";
                    String video = "o";

                    if (app.getUserUI().isPlanLinePresent()) {
                        // Проверяем вкладку ПЛАН и наличие там текста
                        app.getUserUI().clickOnPlanLine();
                        if (app.getUserUI().isTextPresent()) {
                            plan = "+";
                        } else {
                            plan = "-";
                        }
                        app.getUserUI().clickOnPlanLine();
                        app.getUserUI().pause(1000);
                    }

                    if (app.getUserUI().isTheoryLinePresent()) {
                        // Проверяем вкладку Theory
                        app.getUserUI().clickOnTheoryLine();
                        if (app.getUserUI().isTextPresent()) {
                            theory = "+";
                        } else {
                            theory = "-";
                        }
                        app.getUserUI().clickOnTheoryLine();
                        app.getUserUI().pause(1000);
                    }

                    if (app.getUserUI().isHomeWorkLinePresent()) {
                        // Проверяем вкладку Домашка
                        app.getUserUI().clickOnHomeWorkLine();
                        if (app.getUserUI().isTextPresent()) {
                            homeWork = "+";
                        } else {
                            homeWork = "-";
                        }
                        app.getUserUI().clickOnHomeWorkLine();
                        app.getUserUI().pause(1000);
                    }

                    if (app.getUserUI().isCodeLinePresent()) {
                        //Проверяем наличие кода
                        app.getUserUI().clickOnCodeLine();
                        if (app.getCSVReaderUI().isCodePresent()) {
                            code = "+";
                        } else {
                            code = "-";
                        }
                        app.getUserUI().clickOnCodeLine();
                        app.getUserUI().pause(1000);
                    }

                    if (app.getCSVReaderUI().isVideoLinePresent()) {
                        // Проверяем наличие видео
                        app.getUserUI().clickOnVideoLine();
                        // Использование метода для поиска видеоэлементов
                        List<WebElement> videoElements = app.getCSVReaderUI().findVideoElements();
                        // Проверьте, что найдено хотя бы одно видео
                        if (videoElements.isEmpty()) {
                            video = "-";
                        } else {
                            video = "" + videoElements.size();

                          /* выводит src каждого видео:
                            for (WebElement videoElement : videoElements) {
                                String videoSource = videoElement.getAttribute("src");
                                System.out.println("src-видео = " + videoSource);
                               assert videoSource != null && !videoSource.isEmpty() : "Отсутствует атрибут 'src' у видео";
                                System.out.println(videoSource);
                            }*/
                        }
                        app.getCSVReaderUI().clickOnVideoLine();
                        app.getUserUI().pause(1000);
                    }
                    report.add(String.format("    %-5s |   %-5s|    %-5s |      %-5s  |   %-5s|    %-5s%n", listOfLessons.get(i), plan, theory, homeWork, code, video + "    |"));
                    app.getCSVReaderUI().clickOnNextSelectedLesson();
                    app.getUserUI().pause(2000);
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







