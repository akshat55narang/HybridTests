package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;
import static pages.GeneralMenuPage.SIGN_IN_MENU_BUTTON;
import static pages.GeneralMenuPage.USER_AVATAR_IMAGE;

/**
 * Class to be used both to test login functionality as well as utility to open urls directly and ensuring user is logged in
 */
public class Login extends AbstractPage {

    private WebDriver driver;
    private String pageUrl;

    public static final String LOGIN_FIELD = "//input[@id='login_field']";
    public static final String PASSWORD_INPUT = "//input[@id='password']";
    public static final String SIGN_IN_BUTTON = "//input[@value='Sign in']";


    public Login(WebDriver driver, String pageUrl) {
        super(driver);
        this.driver = driver;
        this.pageUrl = pageUrl;
    }

    public void open() {
        get(pageUrl);
    }

    public void openUrl(String pageUrl) {
        get(pageUrl);
    }

    public void openSubUrl(String subUrl) {
        get(pageUrl + subUrl);
    }

    public void tryLogin(String username, String password) {
        waitForElementToBeClickable(5, By.xpath(SIGN_IN_MENU_BUTTON)).click();
        assertTrue("User sign in page not loaded!!",
                waitUntilElementAppears(2, By.xpath(LOGIN_FIELD)));
        sendTextToFieldBy(username, By.xpath(LOGIN_FIELD));
        sendTextToFieldBy(password, By.xpath(PASSWORD_INPUT));
        waitForElementToBeClickable(By.xpath(SIGN_IN_BUTTON)).click();
        shouldBeLoggedIn();
    }

    public void shouldBeLoggedIn() {
        assertTrue("User not logged in successfully!", isLoggedIn());
    }

    public boolean isLoggedIn() {
        return waitUntilElementAppears(5, By.xpath(USER_AVATAR_IMAGE));
    }
}
