package pages;

import org.openqa.selenium.WebDriver;

public class LoggedInPage extends Login {

    private final String username;
    private final String password;
    private final String pageUrl;

    public LoggedInPage(WebDriver driver, String pageUrl, String username, String password) {
        super(driver, pageUrl);
        this.username = username;
        this.pageUrl = pageUrl;
        this.password = password;
    }

    @Override
    public void open() {
        openAsUser(username, password);
    }

    public void openAsUser(String username, String password) {
        super.open();
        tryLogin(username, password);
        isLoggedIn();
    }

    public void openSubUrlWithLogin(String subUrl) {
        get(pageUrl + subUrl);
        tryLogin(username, password);
    }

}
