Feature: TvTime_Search

  @RunAfterLogin
  Scenario: Basic Search
    Given that the user is logged in
    When I type the full text in the search field
    Then results should be displayed

  @RunAfterLogin
  Scenario: Advanced Search
    Given that the user is logged in
    When I type half the text in the search field
    Then results should be displayed

  @RunAfterLogin
  Scenario: Incorrect Search
    Given that the user is logged in
    When I type unreadable text in the search field
    Then no results should be displayed
