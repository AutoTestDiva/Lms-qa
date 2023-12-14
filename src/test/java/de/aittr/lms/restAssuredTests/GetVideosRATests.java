package de.aittr.lms.restAssuredTests;

import de.aittr.lms.CSVDataProviders;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class GetVideosRATests extends TestBaseRA{

    private Cookie cookie;

    @Test
    public void getVideosAsAdminCohort342PositiveTest(){
        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "30"))
                .then().
                assertThat().statusCode(200)
                .assertThat().body (containsString("https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString("basic_programming"));
    }

    @Test
    public void getVideosAsAdminCohort35PositiveTest(){
        cookie = user.getLoginCookie("admin@mail.com", "Admin123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("35", "basic_programming", "lecture", "30"))
                .then().
                assertThat().statusCode(200)
                .assertThat().body (containsString("https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString("basic_programming"));
    }

    @Test
    public void getVideosAsTeacherCohort342PositiveTest() {
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "30"))
                .then().assertThat().statusCode(200)
                .assertThat().body (containsString("https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString("basic_programming"));
    }

    @Test
    public void getVideosAsTeacherCohort35PositiveTest() {
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("35", "basic_programming", "lecture", "30"))
                .then().
                assertThat().statusCode(200)
                .assertThat().body(containsString("https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString("basic_programming"));
    }

    @Test
    public void getVideosAsStudentPositiveTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "30"))
                .then().
                assertThat().statusCode(200)
                .assertThat().body(containsString("https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString("basic_programming"));
    }

    @Test
    public void getVideosCohort35AsStudent2PositiveTest(){
        cookie = user.getLoginCookie("student2@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("35", "basic_programming", "lecture", "30"))
                .then().
                assertThat().statusCode(200)
                .assertThat().body (containsString("https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString("basic_programming"));
    }

    @Test
    public void getVideosCohort36AsStudent2PositiveTest() {
        cookie = user.getLoginCookie("student2@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("36", "basic_programming", "lecture", "30"))
                .then().
                assertThat().statusCode(200)
                .assertThat().body(containsString("https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString("basic_programming"));
    }

    @Test
    public void getVideosNotStudentsGroupNegativeTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("33", "basic_programming", "lecture", "30"))
                .then().
                assertThat().statusCode(403);
    }

    @Test
    public void getVideosWithoutLoginNegativeTest(){
        given().contentType(ContentType.JSON).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "30"))
                .then().
                assertThat().statusCode(403);
    }

    @Test
    public void getVideosAsStudentWrongModuleNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "programming", "lecture", "30"))
                .then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentWrongTypeNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "video", "30"))
                .then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentLessonMinus5NegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "-5"))
                .then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentLessonAsSymbolNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "@"))
                .then().
                assertThat().statusCode(400);
    }

    @Test
    public void getVideosAsStudentLessonAsStringNegativeTest() {
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2","basic_programming","lecture","lesson"))
                .then().
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
    public void getVideosAsStudentBasProg50PositiveTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "basic_programming", "lecture", "50")).then().
                assertThat().statusCode(200)
                .assertThat().body (containsString(
                        "https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString("basic_programming"));
    }

    @Test
    public void getVideosAsStudentLinGit03PositiveTest(){
        cookie = user.getLoginCookie("student@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo("34.2", "linux_git", "lecture", "03"))
                .then()
                .assertThat().statusCode(200)
                .assertThat().body (containsString(
                        "https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString("linux_git"));
    }



    @Test(dataProvider = "provideGetVideoData", dataProviderClass = CSVDataProviders.class)
    public void getVideosAsTeacherWithCSVDataPositiveTest(String cohort, String module, String lesson){
        cookie = user.getLoginCookie("teacher@mail.com", "Qwer123!");
        given().contentType(ContentType.JSON).cookie(cookie).when().get(
                        user.urlBuilderGetVideo(cohort, module, "lecture", lesson))
                .then().assertThat().statusCode(200)
                .assertThat().body (containsString(
                        "https://lesson-videos.fra1.digitaloceanspaces.com/"))
                .assertThat().body(containsString(module));
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
