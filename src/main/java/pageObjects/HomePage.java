package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class HomePage extends AbstractBasePage {

    private final By TITLE_MENU = By.id("navTitleMenu");
    private final By MENU_ITEM = By.xpath("//li[@id='navTitleMenu']//div[@class='subNavListContainer']//li[6]/a");

    public HomePage(WebDriver driver) {
        super(driver, "Ratings and Reviews for New Movies and TV Shows - IMDb");
    }

    public HomePage selectTitleMenu(){
        actions.moveToElement(driver.findElement(TITLE_MENU)).build().perform();
        new WebDriverWait(driver, STANDARD_TIMEOUT_IN_SECONDS)
                .until(ExpectedConditions.elementToBeClickable((MENU_ITEM)));
        return this;
    }

    public TopRatedMoviesPage selectMenuItem(String menuName) {

        driver.findElement(TITLE_MENU).findElements(MENU_ITEM).stream()
                .filter(menu -> isMenuItemPresent(menu, menuName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Menu item with name " + menuName + " is not found"))
                .click();
        return new TopRatedMoviesPage(driver);
    }


}
