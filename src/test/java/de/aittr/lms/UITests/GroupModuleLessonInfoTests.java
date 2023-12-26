package de.aittr.lms.UITests;

import de.aittr.lms.CSVDataProviders;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class GroupModuleLessonInfoTests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("admin@mail.com", "Admin123!");
        app.getUserUI().closeLoginMessage();
    }

    @Test(dataProvider = "provideGetGroupModuleLessonData", dataProviderClass = CSVDataProviders.class)
    public void isGroupModuleLessonInfoTest(String group, String module, String lesson) {
        report.add("isGroupModuleLessonInfoTest" + System.lineSeparator()); // надо для report
        app.getUserUI().clickOnLessonsInSideBar();


        app.getCSVReaderUI().isGroupPresent();
        app.getCSVReaderUI().clickOnSelectYourGroup();
        if (app.getCSVReaderUI().selectMyGroup(group)) {
            app.getCSVReaderUI().clickOnMyGroup(group);
            report.add("********************************************************************");
            report.add("                       Group: " + group);
            report.add("********************************************************************");

            if (app.getCSVReaderUI().isModulePresent()) {
                app.getCSVReaderUI().selectModule(module);
                report.add("--------------------------------------------------------------------");
                report.add("                       Module: " + module);
                report.add("--------------------------------------------------------------------");
                report.add("     Lesson   |  Plan  |  Theory  |  Home work  |  Code  |  Video  |");
                report.add("--------------------------------------------------------------------");

                app.getCSVReaderUI().clickOnSelectLesson();
                if (app.getCSVReaderUI().selectMyLesson(lesson)) {
                    app.getCSVReaderUI().clickOnMyLesson(lesson);

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
                        app.getUserUI().pause(1000);
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
                        app.getUserUI().pause(1000);
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
                        app.getUserUI().pause(1000);
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
                        app.getUserUI().pause(1000);
                    }

                    if (app.getCSVReaderUI().isVideoLinePresent()) {
                        // Проверяем наличие видео
                        app.getCSVReaderUI().clickOnVideoLine();
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
                    report.add(String.format("    %-5s |   %-5s|    %-5s |      %-5s  |   %-5s|    %-5s%n", lesson, plan, theory, homeWork, code, video + "    |"));
                } else {
                    report.add("Данного урока еще нет");
                }
            } else {
                report.add("В данной группе модулей еще нет");
            }
            app.getCSVReaderUI().scrollPageUp();
        } else {
            report.add("Данной группы еще нет");
        }
    }
}