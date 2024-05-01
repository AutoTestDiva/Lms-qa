package de.aittr.lms.UITests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AllElementsInLmsTests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("a04@dev-lms.de", "LMS-dev-pass-2024");
        app.getUserUI().closeLoginMessage();
    }

    @Test
    public void isAllElementsInLMSPresentTest() {
        report.add("isAllElementsInLMSPresentTest" + System.lineSeparator());
        app.getUserUI().clickOnSelectYourGroup();
        List<WebElement> groups = app.getUserUI().getDropdownListOfGroups();
        List<String> listOfGroups = new ArrayList<>();
        for (WebElement item : groups) {
            listOfGroups.add(item.getText());
        }
        for (int j = 37; j < listOfGroups.size(); j++) {
            app.getUserUI().clickOnSelectedGroup(listOfGroups.get(j));
            report.add("********************************************************************");
            report.add("                       Group: " + listOfGroups.get(j));
            report.add("********************************************************************");

            if (app.getUserUI().isModulePresent()) {
                app.getUserUI().clickOnSelectModule();
                List<WebElement> modules = app.getUserUI().getDropdownListOfModules();
                List<String> listOfModules = new ArrayList<>();
                for (WebElement item : modules) {
                    listOfModules.add(item.getText());
                }
                for (int l = 0; l < listOfModules.size(); l++) {
                    app.getUserUI().clickOnSelectedModule(listOfModules.get(l));
                    report.add("--------------------------------------------------------------------");
                    report.add("                       Module: " + listOfModules.get(l));
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
                            app.getCSVReaderUI().clickOnPlanLine();
                            if (app.getCSVReaderUI().isTextPresent()) {
                                plan = "+";
                            } else {
                                plan = "-";
                            }
                            app.getCSVReaderUI().clickOnPlanLine();
                            app.getUserUI().pause(1000);
                        }

                        if (app.getUserUI().isTheoryLinePresent()) {
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

                        if (app.getUserUI().isHomeWorkLinePresent()) {
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

                        if (app.getUserUI().isCodeLinePresent()) {
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

                        if (app.getUserUI().isVideoLinePresent()) {
                            // Проверяем наличие видео
                            app.getUserUI().clickOnVideoLine();
                            // Использование метода для поиска видеоэлементов
                            List<WebElement> videoElements = app.getUserUI().findVideoElements();
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
            app.getUserUI().scrollPageUp();
        }
    }

    @AfterMethod
    public void postCondition() {
        app.getUserUI().logOut();
    }
}

