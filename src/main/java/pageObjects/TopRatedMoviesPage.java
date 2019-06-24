package pageObjects;

import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class TopRatedMoviesPage extends AbstractBasePage {

    TopRatedMoviesPage(WebDriver driver) {
        super(driver, "IMDb Top 250 - IMDb");
    }

    private final By GENRE_NAME = By.cssSelector(".quicklinks  > li > a");
    private final By SORT_BY = By.id("lister-sort-by-options");
    private final By RELEASE_DATE = By.xpath("//td[@class='titleColumn']/span");
    private final By SORT_ORDER = By.xpath("//span[@title='Descending order']");
    Boolean booleanValue;

    public RefinedMoviesByGenrePage selectGenre(String genreToSelect) {
        driver.findElements(GENRE_NAME).stream()
                .filter(genreName -> isMenuItemPresent(genreName, genreToSelect))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Menu item with name " + genreToSelect + " is not found"))
                .click();
        return new RefinedMoviesByGenrePage(driver);
    }


    public TopRatedMoviesPage sortBy(String sortBy) {
        final Select movieSortOrder = new Select(driver.findElement(SORT_BY));
        movieSortOrder.selectByVisibleText(sortBy);
        return this;
    }


    public Boolean isOrderedByReleaseDate() {

        if (getSortOrderAttribute().equalsIgnoreCase("Descending order")) {
            booleanValue = Ordering.natural().reverse().isOrdered(getReleaseDate());
        } else if ((getSortOrderAttribute()).equalsIgnoreCase("Ascending order")) {
            booleanValue = Ordering.natural().isOrdered(getReleaseDate());
        } else {
            throw new IllegalArgumentException("Unexpected sorting argument : " + getSortOrderAttribute());
        }

        return booleanValue;
    }

    private List<Integer> getReleaseDate() {
        List<WebElement> releaseDatesElement = driver.findElements(RELEASE_DATE);
        List<Integer> releaseDates = new ArrayList<Integer>();

        for (WebElement e : releaseDatesElement) {
            releaseDates.add(Integer.valueOf(e.getText().replace("(", "").replace(")", "")));
        }

        return releaseDates;
    }

    private String getSortOrderAttribute() {
        return driver.findElement(SORT_ORDER).getAttribute("title");
    }

}
