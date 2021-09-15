Feature: Users with different roles should see different pages

  Scenario Outline: Non-logged in user, accesses different pages
    Given We have a user that is not logged in
    When He tries to open "<url>"
    Then Information about unauthorized access should be shown
    Examples:
      |url|
      |/mealCategories/add|
      |/mealCategories/edit/5|
      |/mealCategories|
      |/ingredients/add|
      |/ingredients/edit/5|
      |/ingredients|
      |/meal/add|
      |/meal/edit/5|
      |/deliveries|
      |/checkOut|
      |/shoppingCart|
      |/orders/processing|
      |/profile|

  Scenario: Non-logged in user - shown buttons
    Given We have a user that is not logged in
    When He opens home page
    Then Shopping cart,deliveries,processingOrders,profile,logout buttons should not be displayed

  Scenario Outline: User with role DELIVERY, accesses different pages
    Given Delivery user is logged in
    When Delivery tries to access "<url>"
    Then Information about unauthorized access should be shown
    Examples:
      |url|
      |/mealCategories/add|
      |/mealCategories/edit/5|
      |/mealCategories|
      |/ingredients/add|
      |/ingredients/edit/5|
      |/ingredients|
      |/meal/add|
      |/meal/edit/5|
      |/orders/processing|

  Scenario: Logged in delivery - shown buttons
    Given Delivery user is logged in
    When He opens home page
    Then Processing orders button should not be displayed

  Scenario Outline: User with role EMPLOYEE, accesses different pages
    Given Employee user is logged in
    When Employee tries to access "<url>"
    Then Information about unauthorized access should be shown
    Examples:
      |url|
      |/mealCategories/add|
      |/mealCategories/edit/5|
      |/mealCategories|
      |/ingredients/add|
      |/ingredients/edit/5|
      |/ingredients|
      |/meal/add|
      |/meal/edit/5|
      |/deliveries|

  Scenario: Logged in employee - shown buttons
    Given Employee user is logged in
    When He opens home page
    Then Deliveries button should not be displayed

  Scenario Outline: User with role CUSTOMER, accesses different pages
    Given Customer user is logged in
    When Customer tries to access "<url>"
    Then Information about unauthorized access should be shown
    Examples:
      |url|
      |/mealCategories/add|
      |/mealCategories/edit/5|
      |/mealCategories|
      |/ingredients/add|
      |/ingredients/edit/5|
      |/ingredients|
      |/meal/add|
      |/meal/edit/5|
      |/deliveries|
      |/orders/processing|

    Scenario: Logged in customer - shown buttons
    Given Customer user is logged in
    When User opens home page
    Then Deliveries,processingOrders buttons should not be displayed