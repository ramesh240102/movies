package configuration;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageObjects.HomePage;

public class PageNavigator {

    private WebDriver driver;
    private static final Logger LOGGER = LoggerFactory.getLogger(PageNavigator.class);
    private String applicationURL = "https://www.imdb.com";

    public PageNavigator() {
        this.driver = new WebDriverConfiguration().intializeDriver();
    }

    public HomePage launchApplication() {
        driver.get(applicationURL);
        LOGGER.info("Launching application with url: " + applicationURL);
        return new HomePage(driver);
    }


    public void closeBrowser() {
        driver.quit();
        LOGGER.info("Closing the web driver connection and terminating the test.");
    }
}
