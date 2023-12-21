package de.aittr.lms.UITests;

import de.aittr.lms.CSVDataProviders;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GroupInfoUITests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("admin@mail.com", "Admin123!");
        app.getUserUI().closeLoginMessage();
    }

    @Test(dataProvider = "provideGetGroupData", dataProviderClass = CSVDataProviders.class)
    public void isGroupInfoUITests(String group) {
        report.add("isGroupInfoUITests" + System.lineSeparator());
        app.getUserUI().clickOnLessonsInSideBar();

        app.getUserUI().isGroupPresent();//
        app.getUserUI().clickOnSelectYourGroup();//
        if (app.getUserUI().selectMyGroup(group)) {//
            app.getUserUI().clickOnMyGroup(group);//

            report.add("********************************************************************");
            report.add("                       Group: " + group);
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
                            if (app.getUserUI().isCodePresent()) {
                                code = "+";
                            } else {
                                code = "-";
                            }
                            app.getUserUI().clickOnCodeLine();
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

                            app.getUserUI().clickOnVideoLine();
                            app.getUserUI().pause(1000);
                        }

                        report.add(String.format("    %-5s |   %-5s|    %-5s |      %-5s  |   %-5s|    %-5s%n", listOfLessons.get(i), plan, theory, homeWork, code, video + "    |"));

                        app.getUserUI().clickOnNextSelectedLesson();
                        app.getUserUI().pause(2000);
                    }
                    app.getUserUI().clickOnNextSelectedModule();
                    app.getUserUI().pause(1000);
                }
            } else {
                report.add("В данной группе модулей еще нет");
            }
            app.getUserUI().scrollPageUp();
        }
    }
}
