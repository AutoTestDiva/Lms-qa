package de.aittr.lms.restAssuredTests;

import de.aittr.lms.DataBase;
import de.aittr.lms.fwRA.ZoomHelperRA;
import de.aittr.lms.fwRA.UserHelperRA;
import io.restassured.RestAssured;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;
import java.util.Arrays;

import static io.restassured.RestAssured.given;

public class TestBaseRA {

    final static Logger logger = LoggerFactory.getLogger(TestBaseRA.class);
    protected static DataBase db;
    protected static UserHelperRA user = new UserHelperRA();
    protected static ZoomHelperRA zoom = new ZoomHelperRA();

    @BeforeMethod
    public void precondition(Method method, Object[] parameters){
        RestAssured.baseURI = "http://localhost:8080";
        logger.info("Start test " + method.getName() + " with parameters " + Arrays.asList(parameters));
    }

    @AfterMethod
    public void quit(ITestResult result){
        if(result.isSuccess()){
            logger.info("PASSED: " + result.getMethod().getMethodName());
        } else {
            logger.info("FAILED: " + result.getMethod().getMethodName());
        }
        logger.info("=============== Stop test ===============");
    }

}













