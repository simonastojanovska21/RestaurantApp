Feature: Customer can view details about selected meal

  Scenario: Customer is on Meal2 details page

    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    When Customer2 user clicks on meal with name Meal2
    Then Customer2 user should be redirected to Meal2 details page

  Scenario: Customer is on Meal2 details page, so there should be Meal2 as meal name, Meal2 description as meal description
  price of 10$ and list of ingredients (Pancake, Apple, Honey)
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    When Customer2 user clicks on meal with name Meal2
    Then Customer2 user should be redirected to Meal2 details page
    And Customer2 user should see name "Meal2" description "Meal2 description" price "10 $" ingredients "Waffle,Apple,Honey"

  Scenario:Customer is on Meal2 details page and he click 1 times on plus button, value of input should be 2
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    And Customer2 user clicks on meal with name Meal2
    When Customer2 user clicks 1 times on plus button for Meal2
    Then Customer2 user should see that the value of input for Meal2 is "2"

  Scenario:Customer is on Meal2 details page and he clicks 10 times on plus button, but value of input should stay 7
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    And Customer2 user clicks on meal with name Meal2
    When Customer2 user clicks 10 times on plus button for Meal2
    Then Customer2 user should see that the value of input for Meal2 is "7"

  Scenario:Customer is on Meal2 details page, the quantity is 3 so he clicks once on minus button and quantity should be 2
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    And Customer2 user clicks on meal with name Meal2
    And Customer2 should see that the value of quantity field for Meal2 is set to "3"
    When Customer2 user clicks 1 times on minus button for Meal2
    Then Customer2 user should see that the value of input for Meal2 is "2"

  Scenario:Customer is on Meal2 details page, the quantity is 5 so he clicks 7 times on minus button but quantity should be 1
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    And Customer2 user clicks on meal with name Meal2
    And Customer2 should see that the value of quantity field for Meal2 is set to "5"
    When Customer2 user clicks 7 times on minus button for Meal2
    Then Customer2 user should see that the value of input for Meal2 is "1"

  Scenario:Customer is on Meal2 details page, after the details for meal there should be list of 4 most ordered meals
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    When Customer2 user clicks on meal with name Meal2
    Then Customer2 should see table with 4 meals on bottom of page

  Scenario:Customer is on Meal2 details page, the list of 4 most ordered meals is "Meal2, Meal7, Meal8, Meal9"
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    When Customer2 user clicks on meal with name Meal2
    Then Customer2 should see table with 4 meals on bottom of page
    And Customer2 should see meals names "Meal2,Meal7,Meal8,Meal9"

  Scenario: Customer is on Meal2 details page, he clicks 4 times on plus button for Meal2 and then Add, meal should be in
  his shopping cart with quantity 5
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    And Customer2 user clicks on meal with name Meal2
    And Customer2 user clicks 4 times on plus button for Meal2
    When Customer2 clicks on Add button from details page for Meal2
    Then Customer2 should have "Meal2" in shopping cart with quantity "5"

  Scenario:Customer is on Meal2 details page, clicks 2 times on plus button for Meal as most ordered product and then on Add.
  Meal1 should be in his shopping cart with quantity 3
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on menu page
    And Customer2 user clicks on meal with name Meal2
    And Customer2 user clicks 2 times on plus button for Meal7
    When Customer2 clicks on Add button from details page for Meal7
    Then Customer2 should have "Meal7" in shopping cart with quantity "3"