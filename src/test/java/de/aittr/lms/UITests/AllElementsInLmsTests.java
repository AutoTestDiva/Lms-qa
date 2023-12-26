package de.aittr.lms.UITests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AllElementsInLmsTests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("admin@mail.com", "Admin123!");
        app.getUserUI().closeLoginMessage();
    }

    @Test
    public void isAllElementsInLMSPresentTest() {
        report.add("isAllElementsInLMSPresentTest" + System.lineSeparator()); // надо для report
        app.getUserUI().clickOnLessonsInSideBar();

        app.getCSVReaderUI().clickOnSelectYourGroup();
        List<WebElement> groups = app.getCSVReaderUI().getDropdownListOfGroups();
        List<String> listOfGroups = new ArrayList<>();
        for (WebElement item : groups) {
            listOfGroups.add(item.getText());
        }
        for (int j = 0; j < listOfGroups.size(); j++) {
            app.getCSVReaderUI().clickOnSelectedGroup(listOfGroups.get(j));
            report.add("********************************************************************");
            report.add("                       Group: " + listOfGroups.get(j));
            report.add("********************************************************************");

            if (app.getCSVReaderUI().isModulePresent()) {
                app.getCSVReaderUI().clickOnSelectModule();
                List<WebElement> modules = app.getCSVReaderUI().getDropdownListOfModules();
                List<String> listOfModules = new ArrayList<>();
                for (WebElement item : modules) {
                    listOfModules.add(item.getText());
                }
                for (int l = 0; l < listOfModules.size(); l++) {
                    app.getCSVReaderUI().clickOnSelectedModule(listOfModules.get(l));
                    report.add("--------------------------------------------------------------------");
                    report.add("                       Module: " + listOfModules.get(l));
                    report.add("--------------------------------------------------------------------");
                    report.add("     Lesson   |  Plan  |  Theory  |  Home work  |  Code  |  Video  |");
                    report.add("--------------------------------------------------------------------");

                    app.getCSVReaderUI().clickOnSelectLesson();
                    List<WebElement> lessons = app.getCSVReaderUI().getDropdownListOfLessons();
                    List<String> listOfLessons = new ArrayList<>();
                    for (WebElement item : lessons) {
                        listOfLessons.add(item.getText());
                    }
                    for (int i = 0; i < listOfLessons.size(); i++) {
                        app.getCSVReaderUI().clickOnSelectedLesson(listOfLessons.get(i));

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
                        report.add(String.format("    %-5s |   %-5s|    %-5s |      %-5s  |   %-5s|    %-5s%n", listOfLessons.get(i), plan, theory, homeWork, code, video + "    |"));

                        app.getCSVReaderUI().clickOnNextSelectedLesson();
                        app.getUserUI().pause(2000);
                    }
                    app.getCSVReaderUI().clickOnNextSelectedModule();
                    app.getUserUI().pause(1000);
                }
            } else {
                report.add("В данной группе модулей еще нет");
            }
            app.getCSVReaderUI().scrollPageUp();
            app.getCSVReaderUI().clickOnNextSelectedGroup();
            app.getUserUI().pause(1000);
        }
    }
}

