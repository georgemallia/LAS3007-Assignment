Feature: TvTime_Account

  Scenario: Account Details Change
    Given the user is logged in
    And he accesses the account page
    When he modfies account information
    And clicks save
    Then the changes should be saved
