Feature: We have Customer1 who does not have items in shopping cart

  Scenario: Customer1 clicks on Shopping cart button on home page and is redirected to Shopping cart page
    Given Customer1 user, ROLE_CUSTOMER1, tries to login
    When Customer1 user clicks on shopping cart button
    Then Customer1 user should be redirected to shopping cart page

  Scenario:Customer1 is on Shopping cart page and information about empty shopping cart is displayed
    Given Customer1 user, ROLE_CUSTOMER1, tries to login
    When Customer1 user is on shopping cart page
    Then Customer1 user should see information about empty shopping cart

  Scenario:Customer1 is on Shopping cart page and button for back to menu page is displayed, but checkout button is not shown
    Given Customer1 user, ROLE_CUSTOMER1, tries to login
    When Customer1 user is on shopping cart page
    Then Customer1 user should see back to menu button, but not checkout button

  Scenario:Customer1 is on Shopping cart page and clicks button for back to menu page so he should be redirected to Menu page
    Given Customer1 user, ROLE_CUSTOMER1, tries to login
    And Customer1 user is on shopping cart page
    When Customer1 user clicks on button for back to menu
    Then Customer1 should be redirected to menu page

  Scenario: Customer1 is on menu page, selects Meal4 with quantity 1 and adds it to shopping cart.
    Given Customer1 user, ROLE_CUSTOMER1, tries to login
    And Customer1 user is on menu page
    When Customer1 user clicks on Add from menu page for Meal4
    Then Customer1 should have "Meal4" in shopping cart with quantity "1"

  Scenario: Customer1 is on shopping cart and clicks on Check out. He is redirected on Checkout page
    Given Customer1 user, ROLE_CUSTOMER1, tries to login
    And Customer1 user is on shopping cart page
    When Customer1 user clicks on checkout button
    Then Customer1 user should be redirected to checkout page

  Scenario: Customer1 is on Checkout page where total is 12, delivery fee is 20 and total is 32$
    Given Customer1 user, ROLE_CUSTOMER1, tries to login
    When Customer1 user is on checkout page
    Then Customer1 user should see total "12 $" delivery fee "20 $" and total with delivery "32 $"

  Scenario:Customer1 is on Checkout page and clicks on Cancel order, he should be redirected to Menu and should have empty shopping cart
    Given Customer1 user, ROLE_CUSTOMER1, tries to login
    And Customer1 user is on checkout page
    When Customer1 user clicks on cancel order button
    Then Customer1 should be redirected to menu page

  Scenario: Customer1 has canceled his order, and once he opens his shopping cart he should see empty shopping cart
    Given Customer1 user, ROLE_CUSTOMER1, tries to login
    And Customer1 user is on menu page
    When Customer1 user is on shopping cart page
    Then Customer1 user should see information about empty shopping cart

