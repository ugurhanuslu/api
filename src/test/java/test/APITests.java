package test;

import builders.GetPostByUser;
import builders.GetSpecificNumberOfPosts;
import dataGenerator.UserPayloadGenerator;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;
import request.PostRequest;
import builders.GetAllPosts;

public class APITests extends BaseTest {

    @Test
    public void getAllPost() {
        PostRequest[] postRequests = new GetAllPosts().sendRequestPayload().assertRequestSuccess().getResponsePayload();
        Assertions.assertThat(postRequests.length).isEqualTo(100);
    }

    @Test
    public void getPostByUserId() {
        UserPayloadGenerator userPayloadGenerator = new UserPayloadGenerator();
        Integer userId = userPayloadGenerator.generateUserId();
        PostRequest[] postRequests = new GetPostByUser().setUsername(userId).sendRequestPayload().assertRequestSuccess().getResponsePayload();

        for (PostRequest post : postRequests) {
            Assertions.assertThat(post.getUserId()).isEqualTo(userId);
        }
    }

    @Test
    public void GetSpecificNumberOfPostsTest() {
        UserPayloadGenerator userPayloadGenerator = new UserPayloadGenerator();
        Integer postLimiter = userPayloadGenerator.generatePostLimiter();
        PostRequest[] postRequests = new GetSpecificNumberOfPosts().setPostLimit(postLimiter).sendRequestPayload().assertRequestSuccess().getResponsePayload();
        Assertions.assertThat(postRequests.length).isEqualTo(postLimiter);
    }
}
