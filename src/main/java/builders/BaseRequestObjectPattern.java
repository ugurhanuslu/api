package builders;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import java.lang.reflect.Type;

public abstract class BaseRequestObjectPattern<RequestPayload,ResponsePayload>{

    protected Response response;
    protected abstract Type responsePayload();
    protected abstract int getSuccessStatusCode();
    public abstract RequestPayload sendRequestPayload();

    public ResponsePayload getResponsePayload(){
        return response.as(responsePayload());
    }

    public RequestPayload assertStatusCode(int statusCode){
        Assertions.assertThat(response.getStatusCode()).isEqualTo(statusCode);
        return (RequestPayload) this;
    }

    public RequestPayload assertRequestSuccess(){
        return assertStatusCode(getSuccessStatusCode());
    }


}
