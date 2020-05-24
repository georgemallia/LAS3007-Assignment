@TO_RUN
Feature: AddShow

  Scenario: Adding show to watchlist
    Given the user is logs in
    And the user search a show
    When he adds his show
    Then the show sould be visible under his watchlist
