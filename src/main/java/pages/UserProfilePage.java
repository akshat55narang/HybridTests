package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static manager.ConfigLoader.getDefaultLoginName;
import static org.junit.Assert.assertTrue;
import static pages.GistPage.DELETE_GIST_BUTTON;

public class UserProfilePage extends GeneralMenuPage {

    public static final String LOGIN_NAME_TEXT = "//span[contains(normalize-space(text()),'" + getDefaultLoginName() + "')]";

    private static final Logger LOG = LoggerFactory.getLogger(UserProfilePage.class);

    public UserProfilePage(WebDriver driver, String pageUrl, String username, String password) {
        super(driver, pageUrl, username, password);
    }

    public void open(String loginName) {
        openSubUrlWithLogin("/" + loginName);
    }

    public void verify() {
        assertTrue("User profile page not open",
                waitUntilElementAppears(10, By.xpath(LOGIN_NAME_TEXT)));
    }

    public void openGistByName(String gistName) {
        clickElementsWithActionsClass(By.xpath("//a[contains(normalize-space(strong), '" + gistName + "')]"));
        assertTrue(waitUntilElementAppears(By.xpath("//a[text()='" + gistName + "']/ancestor::div/following-sibling::ul"
                + DELETE_GIST_BUTTON)));
    }
}
