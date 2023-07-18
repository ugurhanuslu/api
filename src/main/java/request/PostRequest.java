package request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequest {

    private Integer userId;
    private Integer id;
    private String title;
    private String body;

}
