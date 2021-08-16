package manager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static manager.ConfigLoader.*;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static utils.Utils.getParameter;

public class RootInitializer {

    private PageManager pageManager;
    private RestApi restApi;

    private static final String URL = getDefaultWebUrl();
    private static final String USERNAME = getDefaultUsername();
    private static final String PASSWORD = getDefaultPassword();

    private static ThreadLocal<WebDriver> driverProvider = new ThreadLocal<>();

    private static final Logger LOG = LoggerFactory.getLogger(RootInitializer.class);

    public RootInitializer() {
        WebDriverManager.chromedriver().setup();
        LOG.info("Setup Chrome Driver complete!!");
        driverProvider.set(new ChromeDriver());
        getWebDriver().get(URL);
    }

    public WebDriver getWebDriver() {
        return driverProvider.get();
    }

    public PageManager getPageManager() {
        return (pageManager == null) ? new PageManager(getWebDriver(), URL, USERNAME, PASSWORD) : pageManager;
    }

    public RestApi getRestApi() {
        return (restApi == null) ? new RestApi() : restApi;
    }

    public ChromeOptions getDefaultChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(
                "--disable-popup-blocking",
                "--ignore-certificate-errors",
                "--disable-default-apps",
                "--enable-logging",
                "--ssl-protocol=tlsv1.2",
                "--webdriver-loglevel=info"
        );

        String isChromeShown = getParameter("isBrowserShown", "false");
        boolean headless = isChromeShown.contains("false") || isBlank(isChromeShown);
        if (headless) {
            chromeOptions.addArguments(
                    "--headless",
                    "--disable-gpu",
                    "--disable-translate",
                    "--disable-extensions",
                    "--enable-logging",
                    "--test-type",
                    "--disable-web-security",
                    "--no-sandbox"
            );
        }
        return chromeOptions;
    }

}
