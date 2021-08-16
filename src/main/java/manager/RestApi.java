package manager;

import rest.GistApi;
import rest.builders.UserApi;

public class RestApi {

    public RestApi() {

    }

    public GistApi gistApi() {
        return new GistApi();
    }

    public UserApi userApi() {
        return new UserApi();
    }

}
