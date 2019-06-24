Feature: Movie Title
  The "Movie" page display movie title, story line and its related content.


  @LandingPageVerificationScenario
  Scenario: Verification of page title and details for one movie
    Given I goto the movie page with url "http://www.imdb.com/title/tt2084970"
    And I see the movie title as "The Imitation Game"
    Then the movie details has contents specific to the movie "The Imitation Game"

  @LandingPageVerificationScenario
  Scenario Outline: Verification of page title and details for multiple movies
    Given I goto the movie page with url <url>
    And I see the movie title as <movie name>
    Then the movie details has contents specific to the movie <movie name>
    Examples:
      | url                                  | movie name         |
      | http://www.imdb.com/title/tt2084970  | The Imitation Game |
      | https://www.imdb.com/title/tt0118799 | Life Is Beautiful  |
      | https://www.imdb.com/title/tt0032553 | The Great Dictator |
