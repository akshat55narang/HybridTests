package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import manager.RestApi;
import manager.RootInitializer;
import pages.UserProfilePage;

public class StepDefinitionUserProfilePage {
    private RootInitializer rootInitializer;
    private UserProfilePage userProfilePage;
    private RestApi restApi;

    public StepDefinitionUserProfilePage(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        userProfilePage = rootInitializer.getPageManager().userProfilePage();
        restApi = rootInitializer.getRestApi();
    }

    @Given("I'm on the default user's profile page")
    public void openDefaultUserGistPage() {
        String loginName = restApi.userApi().getAuthenticateUserLoginName();
        userProfilePage.open(loginName);
        userProfilePage.verify();
    }

    @When("I open the gist with name {string}")
    public void openGistByName(String gistName) {
        userProfilePage.openGistByName(gistName);
    }
}
