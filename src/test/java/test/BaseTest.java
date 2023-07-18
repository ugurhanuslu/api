package test;

import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public class BaseTest {

    @BeforeSuite
    public void setup() throws IOException {

        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") +"\\src\\main\\configuration\\config.properties");
        properties.load(fileInputStream);
        RestAssured.baseURI = properties.getProperty("BASE_URI");

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

}
