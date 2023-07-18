package builders;

import config.RequestConfiguration;
import org.apache.http.HttpStatus;
import request.PostRequest;

import java.lang.reflect.Type;

import static io.restassured.RestAssured.given;

public class GetSpecificNumberOfPosts extends BaseRequestObjectPattern<GetSpecificNumberOfPosts, PostRequest[]>{

    private Integer postLimit;

    @Override
    protected Type responsePayload() {
        return PostRequest[].class;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    public GetSpecificNumberOfPosts sendRequestPayload() {
        response = given()
                .spec(RequestConfiguration.getConfigRequest())
                .queryParam("_limit",postLimit)
                .when()
                .get("posts");
        return this;
    }

    public GetSpecificNumberOfPosts setPostLimit(Integer postLimit){
        this.postLimit = postLimit;
        return this;
    }
}
