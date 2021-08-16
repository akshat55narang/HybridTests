package rest.builders;

import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.User;
import rest.AbstractApi;

public class UserApi extends AbstractApi {
    private static final Logger LOG = LoggerFactory.getLogger(UserApi.class);

    public Response getAuthenticatedUser() {
        return get(USER_API, baseRequest());
    }

    public String getAuthenticateUserLoginName() {
        User user = getAuthenticatedUser().as(User.class);
        return user.getLogin();
    }

}
