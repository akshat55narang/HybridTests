package apiTests;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.response.GistResponse;

import static org.junit.Assert.assertTrue;


public class CreateGistResponseTest extends BaseTest {

    @Test
    public void createGist() {
        Response response = gistApi.createGistDefaultValues();
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void deleteGist() {
        String gistId = gistApi.createGist("delete test gist", false, "deletegist_api_testfile.txt");
        Response response = gistApi.deleteGistById(gistId);
        response.then().assertThat().statusCode(204);
    }

    @Test
    public void getGist() {
        String gistId = gistApi.createGist("get gist", false, "getgist_api_testfile.txt");
        Response response = gistApi.getGistById(gistId);
        response.then().assertThat().statusCode(200);
        assertTrue(response.as(GistResponse.class).getId().equals(gistId));
    }

    @Test
    public void updateGistDescription() {
        String updatedDescription = "update_description";
        String gistId = gistApi.createGist("update test gist", false, "updategist_api_testfile.txt");
        Response response = gistApi.updateGist(gistId, updatedDescription);

        response.then().assertThat().statusCode(200);
        response.as(GistResponse.class).getDescription().equals(updatedDescription);
    }
}
