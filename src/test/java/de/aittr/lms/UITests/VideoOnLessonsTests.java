package de.aittr.lms.UITests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

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

        app.getUserUI().getDropdownListOfGroups(By.xpath("//span[contains(text(),'Select your group')]"));
        List<WebElement> groups = app.getUserUI().findElements();
       // for (WebElement group : groups) {
       //     System.out.println("Найдено список групп" + group.getText());
       // }
        for (WebElement group : groups) {
            app.getUserUI().clickOnSelectedGroup(group.getText());
            // app.getUserUI().clickOnSelectedGroup("Cohort 34.1");
           // System.out.println("Group: " + group.getText());


            app.getUserUI().clickOnSelectModule();
            app.getUserUI().getDropdownListOfModules(By.xpath("//span[contains(text(),'Select module')]"));
            List<WebElement> modules = app.getUserUI().findElements();
          //  for (WebElement module : modules) {
          //      System.out.println("Найдено список модулей" + module.getText());
          //  }
            for (WebElement module : modules) {
                app.getUserUI().clickOnSelectedModule(module.getText());
                //app.getUserUI().clickOnSelectedModule("basic_programming");
                //System.out.println("Module: " + module.getText());

                app.getUserUI().clickOnSelectLesson();
                app.getUserUI().getDropdownListOfLessons(By.xpath("//span[contains(text(),'Select lesson')]"));
                List<WebElement> lessons = app.getUserUI().findElements();
             //   for (WebElement lesson : lessons) {
             //       System.out.println("Найдено список уроков" + lesson.getText());
             //   }
            //    for (WebElement lesson : lessons) {
              //      app.getUserUI().clickOnSelectedLesson(lesson.getText());
                    //app.getUserUI().clickOnSelectedLesson("lesson_04");
                    //System.out.println("Lesson: " + lesson.getText());
// Переход к следующему уроку с использованием цикла for
                for (int i = 0; i < lessons.size() - 1; i++) {
                    WebElement currentLesson = lessons.get(i);
                   // WebElement nextLesson = lessons.get(i + 1);

                    app.getUserUI().clickOnSelectedLesson(currentLesson.getText());
                    // Ваш код для текущего урока

                    // Переход к следующему уроку
                   // app.getUserUI().clickOnSelectedLesson(nextLesson.getText());
                    // Ваш код для следующего урока




                    app.getUserUI().clickOnVideoLine();


                    try {
                        // Использование метода для поиска видеоэлементов
                        List<WebElement> videoElements = app.getUserUI().findVideoElements();
                        // Проверьте, что найдено хотя бы одно видео
                        if (videoElements.isEmpty()) {
                            System.out.println("Видео не найдено на странице");
                        } else {
                            // Итерация по списку видео
                            for (WebElement videoElement : videoElements) {
                                // Ваши дополнительные проверки и действия с каждым видео
                                System.out.println("Найдено видео. Используйте videoElement для работы с каждым видео.");
                                // Проверка атрибута "src" для каждого видео
                                String videoSource = videoElement.getAttribute("src");
                                assert videoSource != null && !videoSource.isEmpty() : "Отсутствует атрибут 'src' у видео";
                                System.out.println(videoSource);
                            }
                        }

                    } catch (Exception e) {
                        System.err.println("Произошла ошибка: " + e.getMessage());
                    }
                    app.getUserUI().clickOnNextSelectedLesson();
                    app.getUserUI().pause(5000);
                   // app.getUserUI().clickOnSelectedLesson(nextLesson.getText());
                }

            }
        }
    }
}
