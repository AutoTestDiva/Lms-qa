package de.aittr.lms.UITests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DownloadVideoTests extends TestBaseUI{

        @BeforeMethod
        public void precondition(){
                app.getUserUI().loginWithData("student@mail.com", "Qwer123!");
        }
//        Предусловие: залогиниться как пользователь
//        с данными {email: “student@mail.com“, password: “Qwerty123!“, cohort: “Cohort 34.2“}

        @Test
        public void downloadVideoTest(){

                app.getLesson().selectStudentGroup("Cohort 34.2");
                app.getLesson().selectModule("basic_programming");
                app.getLesson().selectLesson("lesson_30");
                app.getLesson().clickOnVideoPart();
//        На видео кликаю правой кнопкой мышки и выбираю “Загрузить видео как“
                // TODO click with mouse
//        Ожидаемый результат: Невозможно загрузить видео, при клике правой кнопкой мышки отсутствует “Загрузить видео как“.
                // TODO downloadVideoTest Assert, learn how can verify download on PC
        }

}
