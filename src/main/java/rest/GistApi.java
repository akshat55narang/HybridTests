package rest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pojo.request.FileAttributes;
import pojo.request.GistRequest;
import pojo.response.GistResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static rest.builders.RestFactory.getGistBuilder;
import static utils.Utils.generateDefaultFileWithContent;
import static utils.Utils.generateFileWithDefaultContent;

public class GistApi extends AbstractApi {

    private static final Logger LOG = LoggerFactory.getLogger(GistApi.class);

    public GistRequest generateDefaultGistRequest() {
        return getGistBuilder().withDescription("Automated Test")
                .isPublic(false)
                .withFiles(generateDefaultFileWithContent())
                .build();
    }

    public GistRequest generateGistRequest(String description, boolean isPublic, Map<String, FileAttributes> file) {
        return getGistBuilder().withDescription(description)
                .isPublic(isPublic)
                .withFiles(file)
                .build();
    }

    public GistRequest generateGistRequest(String description) {
        return getGistBuilder()
                .withDescription(description)
                .build();
    }

    public boolean isGistWithFileNamePresent(String fileName) {
        return getGistsByFileName(fileName).size() > 0;
    }

    public Response createGistDefaultValues() {
        RequestSpecification requestSpecification = baseRequest().body(generateDefaultGistRequest());
        return post(GISTS_API, requestSpecification);
    }

    public Response createGist(String description, boolean isPublic, Map<String, FileAttributes> file) {
        GistRequest gistRequest = generateGistRequest(description, isPublic, file);
        RequestSpecification requestSpecification = baseRequest().body(gistRequest);
        return post(GISTS_API, requestSpecification);
    }

    public String createAndGetGistIdWithDefaultValues() {
        String gistId = createGistDefaultValues().as(GistResponse.class).getId();
        LOG.info("Created Gist with id {} and default values ", gistId);
        return gistId;
    }

    public String createGist(String description, boolean isPublic, String fileName) {
        Map<String, FileAttributes> file = generateFileWithDefaultContent(fileName);
        String gistId = createGist(description, isPublic, file)
                .as(GistResponse.class).getId();
        LOG.info("Created Gist with id {}, description {} and file name {}", gistId, description, fileName);
        return gistId;
    }

    public String createGistWithFileName(String fileName) {
        Map<String, FileAttributes> file = generateFileWithDefaultContent(fileName);
        String gistId = createGist("automated test", false, file)
                .as(GistResponse.class).getId();
        LOG.info("Created Gist with id {} and file name: {}", gistId, fileName);
        return gistId;
    }

    public Response updateGist(String gistId, String description) {
        GistRequest request = generateGistRequest(description);

        RequestSpecification requestSpecification = baseRequest().body(request);
        return patch(GISTS_API + "/" + gistId, requestSpecification);
    }

    public List<GistResponse> getGistsByFileName(String fileName) {
        List<GistResponse> gistResponses = Arrays.asList(get(GISTS_API, baseRequest()).as(GistResponse[].class));
        return gistResponses.stream()
                .filter(gistResponse -> gistResponse.getFiles().containsKey(fileName)).collect(Collectors.toList());
    }

    public List<String> getAllGistIds() {
        List<String> gistIds = new ArrayList<>();
        List<GistResponse> gistResponses = Arrays.asList(get(GISTS_API, baseRequest()).as(GistResponse[].class));
        gistResponses.stream().forEach(gistResponse -> gistIds.add(gistResponse.getId()));
        return gistIds;
    }

    public Response getGistById(String gistId) {
        return get(GISTS_API + "/" + gistId, baseRequest());
    }

    public Response deleteGistById(String id) {
        return delete(GISTS_API + "/" + id, baseRequest());
    }

    public void deleteGistsByFile(String fileName) {
        List<GistResponse> gistResponses = getGistsByFileName(fileName);
        gistResponses.stream().forEach(gistResponse -> {
            String gistId = gistResponse.getId();
            LOG.info("Deleting gist with id : {} and files: {}", gistId, gistResponse.getFiles().keySet());
            deleteGistById(gistResponse.getId());
        });
    }

    public void deleteAllGists() {
        getAllGistIds().stream()
                .forEach(id -> deleteGistById(id));
    }

    public void verifyGistDeletionByFileName(String fileName) {
        assertEquals("Gist with file name {} not deleted via UI",
                getGistsByFileName(fileName).size(), 0);
    }
}
