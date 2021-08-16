package rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static io.restassured.RestAssured.given;
import static manager.ConfigLoader.getDefaultApiUri;
import static manager.ConfigLoader.getGithubToken;

public class AbstractApi {

    public static final String USER_API = "/user";
    public static final String GISTS_API = "/gists";

    private RequestSpecification requestSpecification;


    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static List<JSONObject> jsonArrayToList(JSONArray jsonArray) {
        return IntStream.range(0, jsonArray.length())
                .mapToObj(jsonArray::getJSONObject)
                .collect(Collectors.toList());
    }

    public void accessToken() {
        //login.getAccessToken();
    }

    protected RequestSpecification baseRequest() {
        return baseRequestWithoutToken()
                .header("Authorization", "token " + getGithubToken());
    }

    protected RequestSpecification baseRequestWithoutToken() {
        return new RequestSpecBuilder().setBaseUri(getDefaultApiUri())
                .setContentType(ContentType.fromContentType("vnd.github.v3+json"))
                .build();
    }

    protected <T> RequestSpecification generatePostRequestSpecification(T body, RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .body(body);
    }

    protected Response post(String path, RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .post(path);
    }

    protected Response patch(String path, RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .patch(path);

    }

    protected Response get(String path, RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .get(path);
    }

    protected Response get(RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .get();
    }

    protected Response delete(String path, RequestSpecification requestSpecification) {
        return given()
                .spec(requestSpecification)
                .delete(path);
    }

    public <T> FluentWait<T> pollingWait(T arg) {
        return new FluentWait<T>(arg)
                .pollingEvery(Duration.ofSeconds(1));
    }
}
