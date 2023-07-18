package builders;
import config.RequestConfiguration;
import org.apache.http.HttpStatus;
import request.PostRequest;
import java.lang.reflect.Type;
import static io.restassured.RestAssured.given;



public class GetPostByUser extends BaseRequestObjectPattern<GetPostByUser, PostRequest[]>{

    private Integer userId;

    @Override
    protected Type responsePayload() {
        return PostRequest[].class;
    }

    @Override
    protected int getSuccessStatusCode() {
        return HttpStatus.SC_OK;
    }

    @Override
    public GetPostByUser sendRequestPayload() {
        response = given()
                .spec(RequestConfiguration.getConfigRequest())
                .queryParam("userId",userId)
                .when()
                .get("posts");
        return this;
    }

    public GetPostByUser setUsername(Integer userId) {
        this.userId = userId;
        return this;
    }

}
