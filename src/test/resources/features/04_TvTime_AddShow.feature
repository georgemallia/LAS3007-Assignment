Feature: AddShow

  Scenario: Adding show to watchlist
    Given the user logs in
    And the user search a show
    When he adds his show
    Then the show should be visible under his watchlist

  Scenario: Show Already Added
    Given the user logs in
    And the user search a show
    When he tries to re-add his show
    Then the show must already be marked added

  Scenario: Remove Added Show
    Given the user logs in
    And the user search a show
    When he clicks remove show
    Then the show should not be visible under his watchlist

  Scenario: Adding Multiple Shows
    Given the user logs in
    When the user searchs and adds multiple shows
    Then the shows should be visible under his watchlist
