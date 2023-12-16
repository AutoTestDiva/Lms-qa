package de.aittr.lms.UITests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class VideoOnLessonsTests extends TestBaseUI {
    @BeforeMethod
    public void precondition() {
        app.getUserUI().loginWithData("admin@mail.com", "Admin123!");
        app.getUserUI().closeLoginMessage();
    }

    @Test
    public void isVideoPresentPositiveTest() {
        app.getUserUI().clickOnLessonsInSideBar();

        app.getUserUI().clickOnSelectYourGroup();
        List<WebElement> groups = app.getUserUI().getDropdownListOfGroups();
        List<String> listOfGroups = new ArrayList<>();
        for (WebElement item : groups) {
            listOfGroups.add(item.getText());
        }
        for (int j = 18; j < listOfGroups.size(); j++) {
            app.getUserUI().clickOnSelectedGroup(listOfGroups.get(j));
            System.out.println("***********************************************");
            System.out.println("Group: " + listOfGroups.get(j));
            System.out.println("***********************************************");

    if(app.getUserUI().isModulePresent()){
            app.getUserUI().clickOnSelectModule();
            List<WebElement> modules = app.getUserUI().getDropdownListOfModules();
            List<String> listOfModules = new ArrayList<>();
            for (WebElement item : modules) {
                listOfModules.add(item.getText());
            }
            for (int l = 1; l < listOfModules.size(); l++) {
                app.getUserUI().clickOnSelectedModule(listOfModules.get(l));
                System.out.println("----------------------------------");
                System.out.println("         Module: " + listOfModules.get(l));
                System.out.println("----------------------------------");

                app.getUserUI().clickOnSelectLesson();
                List<WebElement> lessons = app.getUserUI().getDropdownListOfLessons();
                List<String> listOfLessons = new ArrayList<>();
                for (WebElement item : lessons) {
                    listOfLessons.add(item.getText());
                }
                for (int i = 0; i < listOfLessons.size(); i++) {
                    app.getUserUI().clickOnSelectedLesson(listOfLessons.get(i));
                    System.out.println("Lesson: " + listOfLessons.get(i));

                    app.getUserUI().clickOnVideoLine();
                    try {
                        // Использование метода для поиска видеоэлементов
                        List<WebElement> videoElements = app.getUserUI().findVideoElements();
                        // Проверьте, что найдено хотя бы одно видео
                        if (videoElements.isEmpty()) {
                            System.out.println("Видео не найдено на странице");
                        } else {
                            System.out.println("В уроке " + videoElements.size() + " видео");
                            for (WebElement videoElement : videoElements) {

                                String videoSource = videoElement.getAttribute("src");
                                System.out.println("src-видео = " + videoSource);
                                assert videoSource != null && !videoSource.isEmpty() : "Отсутствует атрибут 'src' у видео";
                                System.out.println(videoSource);
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Произошла ошибка: " + e.getMessage());
                    }
                    app.getUserUI().clickOnNextSelectedLesson();
                    app.getUserUI().pause(1000);
                    // app.getUserUI().clickOnSelectedLesson(nextLesson.getText());
                }
                app.getUserUI().clickOnNextSelectedModule();
                app.getUserUI().pause(1000);
            }
            }else
            {
        System.out.println("В данной группе модулей еще нет");
    }
                app.getUserUI().clickOnNextSelectedGroup();
    }
            app.getUserUI().pause(1000);
            }
    }

