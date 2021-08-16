package utils;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Arrays;
import java.util.List;

public class ExtraExpectedConditions {

    public static ExpectedCondition<Boolean> invisibilityOfElementsLocatedBy(final By locator) {
        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                List<WebElement> elements = driver.findElements(locator);

                try {
                    return elements.stream().allMatch(element -> element.isDisplayed());
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return ("invisibility of elements located by " + locator);
            }
        };
    }

    public static ExpectedCondition<Boolean> presentAndVisible(final By... locators) {
        return new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver driver) {
                return Arrays.stream(locators)
                        .flatMap(locator -> driver.findElements(locator).stream())
                        .anyMatch(WebElement::isDisplayed);
            }

        };
    }
}
