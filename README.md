# movies
**IMDB Movies Website**

This project is a maven based project and uses Selenium and cucumber frameworks. Two cucumber feature file:
 1) TopRatedMovies.feature - has 2 scenarios to verify top rated movies filtered based on genre (@TopRatedMoviesByComedyGenre) and filtered based on release date (@TopRatedMoviesSortByReleaseDate)
 2) MovieTitle.feature -  has a scenario to verify page title and details for a single movie (@LandingPageVerificationScenario) and a scenario outline with examples to verify the same for multiple movies (@LandingPageVerificationScenario)
  
 As the scenario to automate was relatively simpler, the framework is not complicated too much with a separate utility class, property files, etc.. 
 Also, to avoid the element of doubt regarding the browsers and versions installed in the test machine where this will be run, this test is limited to the most widely used chrome browser on windows. But if needed, the tests can be easily configured to run on any browser by adding few additional methods in the WebDriverConfiguration class, having the right drivers and passing an additional argument in the command line.

**Executing tests from the editor (e.g., Intellij)**

Update the desired test tag in the TestRunner class (src/test/java/testRunner). Run the TestRunner class as a junit / cucumber test. 
