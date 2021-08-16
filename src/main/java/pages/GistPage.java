package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class GistPage extends LoggedInPage {
    public static final String DELETE_GIST_BUTTON = "//button[normalize-space(.)='Delete']";

    private static final Logger LOG = LoggerFactory.getLogger(GistPage.class);

    public GistPage(WebDriver driver, String pageUrl, String username, String password) {
        super(driver, pageUrl, username, password);
    }

    public void deleteGist() {
        clickElementsWithActionsClass(By.xpath(DELETE_GIST_BUTTON));
        LOG.info("Alert Generated with text {}", switchTo().alert().getText());
        switchTo().alert().accept();
        assertTrue(waitUntilElementDisppears(10, By.xpath(DELETE_GIST_BUTTON)));
    }
}
