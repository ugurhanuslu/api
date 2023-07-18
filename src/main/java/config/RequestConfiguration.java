package config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestConfiguration {

    public static RequestSpecification getConfigRequest(){
        return new RequestSpecBuilder().setContentType(ContentType.JSON).build();
    }

}
