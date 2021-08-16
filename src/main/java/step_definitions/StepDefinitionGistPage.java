package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import manager.RestApi;
import manager.RootInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.GistPage;

public class StepDefinitionGistPage {
    private RootInitializer rootInitializer;
    private GistPage gistPage;
    private RestApi restApi;

    private static final Logger LOG = LoggerFactory.getLogger(StepDefinitionGistPage.class);

    public StepDefinitionGistPage(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        gistPage = rootInitializer.getPageManager().gistPage();
        restApi = rootInitializer.getRestApi();
    }

    @Given("no existing gist with file name {string}")
    public void deleteGistByFileName(String fileName) {
        restApi.gistApi().deleteGistsByFile(fileName);
    }

    @Given("A gist with file name {string}")
    public void createGistByFileName(String fileName) {
        if (!restApi.gistApi().isGistWithFileNamePresent(fileName)) {
            restApi.gistApi().createGistWithFileName(fileName);
            return;
        }
    }

    @When("I delete the gist the current open in the UI")
    public void deleteGistFromUi() {
        gistPage.deleteGist();
    }

    @Then("the gist with name {string} should be deleted")
    public void verifyGistIsDeleted(String fileName) {
        restApi.gistApi().verifyGistDeletionByFileName(fileName);
    }

}
