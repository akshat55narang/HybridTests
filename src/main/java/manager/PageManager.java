package manager;

import org.openqa.selenium.WebDriver;
import pages.*;

public class PageManager {
    private WebDriver driver;
    private String url;
    private final String username;
    private final String password;
    private Login login;
    private LoggedInPage loggedInPage;
    private HomePage homePage;
    private UserProfilePage userProfilePage;
    private GistPage gistPage;

    public PageManager(WebDriver driver, String url, String username, String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public Login login() {
        return (login == null) ? new Login(driver, url) : login;
    }

    public LoggedInPage loggedInPage() {
        return (loggedInPage == null) ? new LoggedInPage(driver, url, username, password) : loggedInPage;
    }

    public HomePage homePage() {
        return (homePage == null) ? new HomePage(driver, url, username, password) : homePage;
    }

    public UserProfilePage userProfilePage() {
        return (userProfilePage == null) ? new UserProfilePage(driver, url, username, password) : userProfilePage;
    }

    public GistPage gistPage() {
        return (gistPage == null) ? new GistPage(driver, url, username, password) : gistPage;
    }
}
