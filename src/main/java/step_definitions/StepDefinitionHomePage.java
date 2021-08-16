package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import manager.RootInitializer;
import pages.HomePage;

public class StepDefinitionHomePage {
    private RootInitializer rootInitializer;
    private HomePage homePage;

    public StepDefinitionHomePage(RootInitializer rootInitializer) {
        this.rootInitializer = rootInitializer;
        homePage = rootInitializer.getPageManager().homePage();
    }

    @Given("I'm on the Home Page as anonymous user")
    public void onHomePageAnonymousUser() {
        homePage.openAsAnonymousUser();
        homePage.verifyHomePageAnonymousUser();
    }

    @Given("I'm on the Home Page")
    public void onHomePage() {
        homePage.open();
        homePage.verifyHomePageDefaultUser();
    }

    @Then("I should be able to see all gists")
    public void verifyAllGistsHomePage() {
        homePage.verifyGistsHomePage();
    }

    @Then("I should see a template to create a new gist")
    public void verifyCreateGistTemplateUserHome() {
        homePage.verifyCreateGistTemplateUserHome();
    }
}
