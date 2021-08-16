package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HomePage extends GeneralMenuPage {
    private String pageUrl;

    public HomePage(WebDriver driver, String pageUrl, String username, String password) {
        super(driver, pageUrl, username, password);
        this.pageUrl = pageUrl;
    }

    public void verifyHomePageAnonymousUser() {
        assertFalse("Home Page not loaded ",
                waitUntilElementAppears(10, By.xpath("//a[@href='/forked']")));
    }

    public void verifyHomePageDefaultUser() {
        assertTrue("Default User not logged in",
                waitUntilElementAppears(10, By.xpath("//div[@id='user-links']")));
    }
    public void openAsAnonymousUser() {
        get(pageUrl);
    }

    public void verifyGistsHomePage() {
        assertTrue(waitForElementsInDom(By.xpath("//div[@class='repository-content gist-content']/div"))
                .size() > 0);
    }

    public void verifyCreateGistTemplateUserHome() {
        assertTrue(waitUntilElementAppears(3, By.xpath("//input[@name='gist[description]']")));
    }
}
