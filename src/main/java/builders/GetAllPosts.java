package builders;
import config.RequestConfiguration;
import org.apache.http.HttpStatus;
import request.PostRequest;
import java.lang.reflect.Type;
import static io.restassured.RestAssured.*;

public class GetAllPosts extends BaseRequestObjectPattern<GetAllPosts, PostRequest[]>{

    @Override
    protected Type responsePayload() {
        return PostRequest[].class;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    public GetAllPosts sendRequestPayload() {
        response = given()
                .spec(RequestConfiguration.getConfigRequest())
                .when()
                .get("posts");
        return this;
    }
}
