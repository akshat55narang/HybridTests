package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ExtraExpectedConditions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.function.Consumer;

/**
 * This class contains all utility methods / wrappers around Webdriver
 */
public abstract class AbstractPage {
    private WebDriver driver;

    private static final Logger LOG = LoggerFactory.getLogger(AbstractPage.class);

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public FluentWait<WebDriver> newWait() {
        return new WebDriverWait(driver, Integer.parseInt("60"))
                .ignoring(StaleElementReferenceException.class);
    }

    public void get(String url) {
        driver.get(url);
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public String getPageSource() {
        return driver.getPageSource();
    }

    public WebDriver.Navigation navigate() {
        return driver.navigate();
    }

    public WebDriver.TargetLocator switchTo() {
        return driver.switchTo();
    }

    public void reloadCurrentPage() {
        driver.navigate().refresh();
    }

    public void clickButtonWithText(String text) {
        String xpath = "//span[text()='']";
        clickElementsWithActionsClass(By.xpath(xpath));
    }

    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    public void switchContextToLastOpenTab() {
        Set<String> handles = getWindowHandles();
        ArrayList<String> tabs = new ArrayList(handles);
        switchTo().window(tabs.get(tabs.size() - 1));
    }

    public void switchContextToMainWindow() {
        ArrayList<String> tabs = new ArrayList<>(getWindowHandles());
        switchTo().window(tabs.get(0));
    }

    public void switchToWindowWhichContainsUrl(String stringInUrl) {
        Set<String> handles = getWindowHandles();
        String matchingWindow = handles.stream().filter(handle -> switchTo().window(handle)
                .getCurrentUrl().contains(stringInUrl))
                .findAny().orElseThrow(NotFoundException::new);
        switchTo().window(matchingWindow);
    }

    public boolean isUrlOpenInAnyBrowserTab(String stringInUrl) {
        Set<String> handles = getWindowHandles();
        return handles.stream()
                .anyMatch(handle -> switchTo().window(handle).getCurrentUrl().contains(stringInUrl));
    }

    public void closeOpenTabsWithUrl(String stringInUrl) {
        Set<String> handles = getWindowHandles();
        handles.stream()
                .filter(handle -> switchTo().window(handle).getCurrentUrl().contains(stringInUrl))
                .forEach(handle -> switchTo().window(handle).close());
    }

    public void acceptUnhandledAlerts() {
        try {
            Alert alert = switchTo().alert();
            alert.accept();
            LOG.info("Accepted unhandled alert: {}", alert.getText());
        } catch (NoAlertPresentException e) {
            LOG.info("Alert already removed: {}", e.getMessage());
        }
    }

    public boolean waitUntilElementDisppears(By by) {
        try {
            return newWait().until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean waitUntilElementDisppears(int timeoutInSeconds, By by) {
        try {
            return newWait().withTimeout(Duration.ofSeconds(timeoutInSeconds))
                    .until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean waitUntilElementAppears(By by) {
        try {
            return newWait().until(ExtraExpectedConditions.presentAndVisible(by));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean waitUntilElementAppears(int timeOutInSeconds, By by) {
        try {
            return newWait()
                    .withTimeout(Duration.ofSeconds(timeOutInSeconds))
                    .until(ExtraExpectedConditions.presentAndVisible(by));
        } catch (TimeoutException e) {
            return false;
        }
    }

    public WebElement waitForElement(int timeOutInSeconds, By by) {
        return newWait().withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForElement(int timeOutInSeconds, WebElement element) {
        return newWait().withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitForElementsInDom(By by) {
        return newWait().until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement waitForElementInDom(By by) {
        return newWait().until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementInDom(int timeOutInSeconds, By by) {
        return newWait().withTimeout(Duration.ofSeconds(timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementToBeClickable(int timeoutInSeconds, By by) {
        return newWait().withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitForElementToBeClickable(By by) {
        return newWait().until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitForElementToBeClickable(int timeoutInSeconds, WebElement element) {
        return newWait().withTimeout(Duration.ofSeconds(timeoutInSeconds))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeClickable(WebElement element) {
        return newWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void moveToElement(WebElement element) {
        new Actions(driver).moveToElement(element)
                .perform();
    }

    public void clickElementsWithActionsClass(WebElement... elements) {
        Arrays.asList(elements).forEach(element -> moveToElementAndClick(element));
    }

    public void clickElementsWithActionsClass(By... locators) {
        Arrays.asList(locators).forEach(locator -> moveToElementAndClick(waitForElementToBeClickable(locator)));
    }

    public void doubleClickElementWithActionsClass(WebElement element) {
        new Actions(driver).doubleClick(element).perform();
    }

    public void doubleClickElementsWithActionsClass(WebElement... elements) {
        Arrays.asList(elements)
                .forEach(element -> doubleClickElementsWithActionsClass(element));
    }

    public void moveToElementAndClick(WebElement element) {
        new Actions(driver).moveToElement(element)
                .click().perform();
    }

    public void moveToMultipleElementsAndClick(WebElement... elements) {
        moveToMultipleElements(actions -> actions.click().build().perform(), elements);
    }

    public void moveToMultipleElements(Consumer<Actions> actionsConsumer, WebElement... elements) {
        Actions actions = new Actions(driver);
        Arrays.asList(elements).forEach(element -> moveToElement(element));
        actionsConsumer.accept(actions);
    }

    public void parsDateTimeInFormat(String dateTimeFormat, String dateTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = dateFormat.parse(dateTime);
        } catch (ParseException e) {
            LOG.info("Cannot parse Date Format : {}", date);
            LOG.info("Exception occured : {}", e);
        }
    }

    public void sendTextToFieldBy(String text, By by) {
        WebElement field = waitForElementInDom(10, by);
        field.click();
        field.clear();
        field.sendKeys(text);
    }

    public Object executeScript(String script, Object... objects) {
        return ((JavascriptExecutor) driver).executeScript(script, objects);
    }

    public void scrollDown() {
        executeScript("scroll(0, -250");
    }

    public void scrollDownUntilElementIsVisible(WebElement element) {
        executeScript("arguments[0].scrollIntoView()", element);
    }

    public void openInNewTab(String url) {
        executeScript("window.open(" + url + ")");
        switchToWindowWhichContainsUrl(url);
    }

    public void rightClick(WebElement element) {
        new Actions(driver).contextClick(element).perform();
    }

}
