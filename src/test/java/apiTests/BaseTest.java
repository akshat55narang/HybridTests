package apiTests;

import rest.GistApi;

public class BaseTest {
    protected GistApi gistApi;

    public static final String USER_API = "/user";
    public static final String GISTS_API = "/gists";

    public BaseTest() {
        gistApi = new GistApi();
    }




}
