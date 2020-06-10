@TO_RUN
Feature: TvTime_Login

  Scenario: Sucessful Signin
    Given I try to sign in
    When I enter the correct credentials
    And click the sign in button
    Then I should be logged in

  Scenario: Sucessful Login
    Given I try to log in
    When I enter my login details
    And click the log in button
    Then I should be logged in

  Scenario: Login Missing Credentials
    Given I try to log in
    When I enter missing login details
    And click the log in button
    Then I should not be logged in

  Scenario: Login Incorrect Credentials
    Given I try to log in
    When I enter Incorrect login details
    And click the log in button
    Then I should not be logged in

  Scenario: Logout From Account
    Given I try to log in
    When I enter my login details
    And click the log in button
    Then I should be logged in
    And when I click the log out button
    Then I should be logged out
