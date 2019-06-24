package pageObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.System.lineSeparator;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toCollection;

abstract class AbstractBasePage {

    WebDriver driver;
    private String partialPageTitle;
    final long STANDARD_TIMEOUT_IN_SECONDS = 30;
    Actions actions;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    AbstractBasePage(WebDriver driver, String partialPageTitle) {
        this.driver = driver;
        this.partialPageTitle = partialPageTitle;
        validatePage();
        actions = new Actions(driver);
    }

    private void validatePage() {
        waitForPageToLoad();
        waitForJQueryToNotBeActive();
        waitForTitle();
        verifyOnCorrectPage();
        logger.info("On page titled {}", driver.getTitle());
    }

    private void waitForPageToLoad() {
        new WebDriverWait(driver, STANDARD_TIMEOUT_IN_SECONDS)
                .until(driver -> (JavascriptExecutor) driver)
                .executeScript("return document.readyState == 'complete'");
    }

    private void waitForJQueryToNotBeActive() {
        new WebDriverWait(driver, STANDARD_TIMEOUT_IN_SECONDS)
                .until(driver -> (JavascriptExecutor) driver)
                .executeScript("return (typeof jQuery == 'undefined' || jQuery.active == 0)");
    }

    private void waitForTitle() {
        new WebDriverWait(driver, STANDARD_TIMEOUT_IN_SECONDS)
                .until(driver -> driver.getTitle() != null);
    }

    private void verifyOnCorrectPage() {
        boolean correctPageDisplayed = driver.getTitle().contains(partialPageTitle);
        if (correctPageDisplayed) {
            return;
        }
        List<String> lines = Stream.of(
                "Failed to create " + getClass().getSimpleName(),
                "Expected title regex: " + partialPageTitle,
                "Actual title: " + driver.getTitle(),
                "Actual url: " + driver.getCurrentUrl()
        ).collect(toCollection(ArrayList::new));
        throw new RuntimeException(lines.stream().collect(joining(lineSeparator())));
    }

    boolean isMenuItemPresent(WebElement menuItem, String menuItemToSelect) {
        actions.moveToElement(menuItem).build().perform();
        return menuItem.getText().equalsIgnoreCase(menuItemToSelect);
    }
}
