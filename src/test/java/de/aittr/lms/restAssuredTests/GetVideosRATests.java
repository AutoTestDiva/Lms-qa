package de.aittr.lms.restAssuredTests;

import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetVideosRATests extends TestBaseRA{

    private Cookie cookie;

    @Test
    public void getVideosAsAdminCohort342PositiveTest(){
        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "30")).then().
                assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsAdminCohort35PositiveTest(){
        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("35", "basic_programming", "lecture", "30")).then().
                assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort342PositiveTest() {
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "30"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length() > 2);
    }

    @Test
    public void getVideosAsTeacherCohort35PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("35", "basic_programming", "lecture", "30")).then().
                assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsStudentPositiveTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "30")).then().
                assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosCohort35AsStudent2PositiveTest(){
        cookie = user.getLoginCookie("student2@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("35", "basic_programming", "lecture", "30")).then().
                assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosCohort36AsStudent2PositiveTest(){
        cookie = user.getLoginCookie("student2@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("36", "basic_programming", "lecture", "30")).then().
                assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosNotStudentsGroupNegativeTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("33", "basic_programming", "lecture", "30")).then().
                assertThat().statusCode(403);
    }

    @Test
    public void getVideosWithoutLoginNegativeTest(){
        given().contentType(ContentType.JSON).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "30")).then().
                assertThat().statusCode(403);
    }

    @Test
    public void getVideosAsStudentWrongModuleNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "programming", "lecture", "30")).then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentWrongTypeNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "video", "30")).then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentLessonMinus5NegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "-5")).then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentLessonAsSymbolNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "@")).then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentLessonAsStringNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "lesson")).then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentCohortNullNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get( //endPoint /get-videos
                        user.urlBuilderGetVideo("", "basic_programming", "lecture", "30"))
                .then().
                assertThat().statusCode(500);
    }

    @Test
    public void getVideosAsStudentCohortOnlySpaceNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get( //endPoint /get-videos
                        user.urlBuilderGetVideo(" ", "basic_programming", "lecture", "30"))
                .then()
                .assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsTeacherCohort371BasProgram10PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("37.1", "basic_programming", "lecture", "10"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort371BasProgram13PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("37.1", "basic_programming", "lecture", "13"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort371LinGit1PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("37.1", "linux_git", "lecture", "1"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort371LinGit2PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("37.1", "linux_git", "lecture", "2"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsStudentBasProg50PositiveTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "50")).then().
                assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }


    @Test
    public void getVideosAsStudentLinGit03PositiveTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "linux_git", "lecture", "3")).then().
                assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort372BasProgr10PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("37.2", "basic_programming", "lecture", "10"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort372BasProgr15PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("37.2", "basic_programming", "lecture", "15"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort38BasProgr7PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("38", "basic_programming", "lecture", "7"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }


    @Test
    public void getVideosAsTeacherCohort38BasProgr13PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("38", "basic_programming", "lecture", "13"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort312Frontend01PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("31.2", "front_end", "lecture", "1"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort312Frontend18PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("31.2", "front_end", "lecture", "18"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort312Program5PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("31.2", "basic_programming", "lecture", "5"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort312Program55PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("31.2", "basic_programming", "lecture", "55"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort29Back02PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("29", "back_end", "lecture", "2"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort29Back15PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("29", "back_end", "lecture", "15"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

    @Test
    public void getVideosAsTeacherCohort29QA5PositiveTest(){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        String response = given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("29", "qa", "lecture", "5"))
                .then().assertThat().statusCode(200).extract().response().body().asString();
        Assert.assertTrue(response.length()>2);
    }

//    TEST-CASES
//    37.1  basic_programming lecture 10, 11, 12, 13
//    37.1 linux_git lecture 01, 02
//    34.2 basic_programming lecture 21 - 40, 50 - 54
//    34.2 linux_git lecture 03
//    37.2 basic_programming lecture 10, 11, 12, 14, 15
//    38 basic_programming lecture 07 - 13
//    31.2 front_end lecture 01 - 18
//    31.2 basic_programming lecture 01 - 22, 55 - 58
//    29 back_end lecture 01 - 10, 15 - 19
//    29 qa lecture 05

}
