Feature: Every user can view the menu, a list of available meals

  Scenario: Customer user clicks the menu button from header, and list of meals should be displayed
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    When Customer2 user clicks on menu button
    Then Customer2 should be redirected to menu page

  Scenario: Customer is on menu page, on each page there should be 12 meals
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    When Customer2 user is on menu page
    Then Customer2 user should see 12 meals on menu page

  Scenario: Customer clicks on meal category Sandwich from menu page, so there should be 1 meal with name Meal10 and price 10
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    When Customer2 user is on menu page
    And Customer2 user clicks on meal category Sandwich
    Then Customer2 user should see 1 meals on menu page
    And Customer2 user should see meals with names "Meal10"

  Scenario: Customer is on menu page. By clicking on plus button for Meal3, value of the input field should be 2
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    When Customer2 user clicks 1 times on plus button for Meal3
    Then Customer2 user should see that the value of input for Meal3 is "2"

  Scenario: Customer is on menu page  and he clicks 10 times on plus button for Meal3, but value of input should stay 7
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    When Customer2 user clicks 10 times on plus button for Meal3
    Then Customer2 user should see that the value of input for Meal3 is "7"

  Scenario: Customer is on menu page. Input field for quantity for Meal 3 is 3, so he clicks on minus button and the new value for
  quantity is 2
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    And Customer2 should see that the value of quantity field for Meal3 is set to "3"
    When Customer2 user clicks 1 times on minus button for Meal3
    Then Customer2 user should see that the value of input for Meal3 is "2"

  Scenario: Customer is on menu page. Input field for quantity for Meal 3 is 2, so he clicks minus 2 times,
  but the minimum value for quantity is 1, so input value should not be 0
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    And Customer2 should see that the value of quantity field for Meal3 is set to "2"
    When Customer2 user clicks 2 times on minus button for Meal3
    Then Customer2 user should see that the value of input for Meal3 is "1"

  Scenario: Customer is on menu page and click 3 times on plus button for Meal3. Then he clicks on Add. Meal3 should be in
  his shopping cart with quantity 4
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    And Customer2 user clicks 3 times on plus button for Meal3
    When Customer2 user clicks on Add from menu page for Meal3
    Then Customer2 should have "Meal3" in shopping cart with quantity "4"