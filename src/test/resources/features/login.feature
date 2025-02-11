Feature:

  Scenario Outline: User tries to login with different credentials
    Given I open the login page
    When I enter username "<username>" and password "<password>"
    And I click the login button
    Then I should see "<results>"

    Examples:
      | username      | password      | results       |
      | validUser     | validPass123  | Dashboard     |
      | invalidUser   | wrongPass     | Error Message |
      | validUser     | wrongPass     | Error Message |
      | invalidUser2  | validPass123  | Dashboard     |