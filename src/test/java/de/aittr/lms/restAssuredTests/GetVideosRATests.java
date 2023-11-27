package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetVideosRATests extends TestBaseRA{

    private Cookie cookie;

    @Test
    public void getVideosAsAdminCohort342PositiveTest(){
        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
        ResponseBodyExtractionOptions response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", 30)).then().
                assertThat().statusCode(200).extract().body();
        Assert.assertTrue(response != null);
    }

    @Test
    public void getVideosAsAdminCohort35PositiveTest(){
        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
        ResponseBodyExtractionOptions response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("35", "basic_programming", "lecture", 30)).then().
                assertThat().statusCode(200).extract().body();
        Assert.assertTrue(response != null);
    }

    @Test
    public void getVideosAsTeacherCohort342PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwerty123!");
        ResponseBodyExtractionOptions response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", 30)).then().
                assertThat().statusCode(200).extract().body();
        Assert.assertTrue(response != null);
    }

    @Test
    public void getVideosAsTeacherCohort35PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwerty123!");
        ResponseBodyExtractionOptions response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("35", "basic_programming", "lecture", 30)).then().
                assertThat().statusCode(200).extract().body();
        Assert.assertTrue(response != null);
    }

    @Test
    public void getVideosAsStudentPositiveTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwerty123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", 30)).then().
                assertThat().statusCode(200);
    }

    @Test
    public void getVideosNotStudentsGroupNegativeTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwerty123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("33", "basic_programming", "lecture", 30)).then().
                assertThat().statusCode(403);
    }

    @Test
    public void getVideosWithoutLoginNegativeTest(){
        given().contentType(ContentType.JSON).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", 30)).then().
                assertThat().statusCode(403);
    }

    @Test
    public void getVideosAsStudentWrongModuleNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwerty123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "programming", "lecture", 30)).then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentWrongTypeNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwerty123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "video", 30)).then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentLessonNullNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwerty123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", 0)).then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentCohortNullNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwerty123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get( //endPoint /get-videos
                        user.urlBuilderGetVideo("", "basic_programming", "lecture", 30))
                .then().
                assertThat().statusCode(500);
    }

    @Test
    public void getVideosAsStudentCohortOnlySpaceNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwerty123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get( //endPoint /get-videos
                        user.urlBuilderGetVideo(" ", "basic_programming", "lecture", 30))
                .then()
                .assertThat().statusCode(404);
//                .assertThat().statusCode(500);
    }


}
