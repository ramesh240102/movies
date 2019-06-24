package stepDefinitions;

import configuration.PageNavigator;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import pageObjects.RefinedMoviesByGenrePage;
import pageObjects.TopRatedMoviesPage;

public class StepDefinitions {

    private PageNavigator navigator;
    private TopRatedMoviesPage topRatedMoviesPage;
    private RefinedMoviesByGenrePage refinedMoviesByGenrePage;
    private String expectedGenre;

    @Before
    public void setUp() {
        navigator = new PageNavigator();
    }

    @Given("^I load the (.*) page$")
    public void iLoadTheTopRatedMoviesPage(String menuItem) {
        topRatedMoviesPage = navigator.launchApplication().selectTitleMenu().selectMenuItem(menuItem);
    }

    @And("^I refine by Genre (.*)")
    public void iRefineByGenreComedy(String genreNameToSelect) {
        expectedGenre = genreNameToSelect;
        refinedMoviesByGenrePage = topRatedMoviesPage.selectGenre(genreNameToSelect);

    }

    @Then("^the list of movies should only contain relevant results$")
    public void theListOfMoviesShouldOnlyContainRelevantResults() {
        Assertions.assertThat(refinedMoviesByGenrePage.getNumberOfMoviesDisplayed()).isEqualTo(50);
        refinedMoviesByGenrePage.assertAllDisplayedMoviesGenre(expectedGenre);
    }

    @And("^I sort the list by (.*)$")
    public void  iSortTheListByReleaseDate(String sortBy)  {
        topRatedMoviesPage.sortBy(sortBy);
    }

    @Then("^the list of movies should be displayed in order of release date$")
    public void theListOfMoviesShouldBeDisplayedInOrderOfReleaseDate()  {
        Assert.assertTrue(topRatedMoviesPage.isOrderedByReleaseDate());
    }

    @After
    public void closeWebDriver() {
        navigator.closeBrowser();
    }
}
