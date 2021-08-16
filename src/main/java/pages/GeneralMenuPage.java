package pages;

import org.openqa.selenium.WebDriver;


/**
 * Contains methods which are not page specific, functionalities in menu bar / logout, etc.
 * */
public class GeneralMenuPage extends LoggedInPage {

    //Add the urls for each page
    public static final String HOME_PAGE_URL = "/";

    public static final String SIGN_IN_MENU_BUTTON = "(//a[@data-ga-click='Header, sign in'])[2]";
    public static final String SIGN_UP_MENU_BUTTON = "//a[normalize-space(text())='Sign in']";
    public static final String USER_SUMMARY_DETAILS = "//summary[@class='Header-link name']";
    public static final String USER_AVATAR_IMAGE = USER_SUMMARY_DETAILS + "//img";


    public GeneralMenuPage(WebDriver driver, String pageUrl, String username, String password) {
        super(driver, pageUrl, username, password);
    }

    //Logout button is mostly common to all UIs, hence moved to abstract page
    public void logout() {

    }
}
