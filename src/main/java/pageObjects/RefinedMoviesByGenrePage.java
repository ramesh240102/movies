package pageObjects;

import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class RefinedMoviesByGenrePage extends AbstractBasePage {

    private final By GENRE = By.cssSelector(".text-muted > .genre");

    RefinedMoviesByGenrePage(WebDriver driver) {
        super(driver, "Feature Film, Rating Count at least 25,000, Comedy (Sorted by IMDb Rating Descending) - IMDb");
    }

    private List<WebElement> getGenreForAllMoviesDisplayed() {
        return driver.findElements(GENRE);
    }

    public int getNumberOfMoviesDisplayed() {
        return getGenreForAllMoviesDisplayed().size();
    }

    public void assertAllDisplayedMoviesGenre(String expectedGenre) {
        getGenreForAllMoviesDisplayed().forEach(actualGenre -> Assertions.assertThat(actualGenre.getText().contains(expectedGenre)).isTrue());
    }

}
