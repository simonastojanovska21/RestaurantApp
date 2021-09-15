Feature: Order rest controller

  Scenario: User gets a list of all orders
    When Admin get a list of all orders
    Then Admin should see list of 8 orders

  Scenario: User gets a list of all processing orders
    When Admin get a list of all processing orders
    Then Admin should see list of 2 orders

  Scenario: User gets a list of all delivering orders
    When Admin get a list of all delivering orders
    Then Admin should see list of 2 orders

  Scenario: User gets a list of 4 most ordered meals
    When Admin get a list of most ordered meals
    Then Admin should see list of 4 meals

  Scenario: User gets a list of all orders for a user with invalid username
    When Actor tries to get list of orders for user with username "test@admin.com"
    Then Actor should see message about invalid credentials

  Scenario: User gets a list of all orders for a user with valid username
    When Actor tries to get list of orders for user with username "admin@admin.com"
    Then Actor should see list of 3 orders

  Scenario: User gets active order for user with invalid username
    When Actor tries to get active order for user with username "test@admin.com"
    Then Actor should see message about invalid credentials

  Scenario: User gets active order for user with valid username
    When Actor tries to get active order for user with username "admin@admin.com"
    Then Actor should see message about user active order

  Scenario: User tries to cancel an order with invalid username
    When Actor tries to cancel order with username "test@admin.com"
    Then Actor should see message about invalid credentials

  Scenario: User tries to cancel an order with valid username
    When Actor tries to cancel order with username "customer2@test.com"
    Then Actor should see message about successfully cancelling order

  Scenario: User tries to get details for order with invalid order id
    When Actor tries to get details for order with id 10
    Then Actor should see message that order is not found

  Scenario: User tries to get details for order with valid order id
    When Actor tries to get details for order with id 6
    Then Actor should see message about selected order

  Scenario: User tries to add new order item with missing fields
    When Actor tries to add new order item with missing fields
    Then Actor should see message about missing fields

  Scenario Outline: User tries to add new order item with empty fields
    When Actor enters "<quantity>" "<mealId>" "<username>" for order item
    Then Actor should see message about empty fields
    Examples:
    |quantity|mealId|username|
    |0       |0     |        |
    |0       |6     |admin@admin.com|
    |3       |0     |admin@admin.com|
    |3       |6     |               |

  Scenario: User tries to add new order item with invalid username
    When Actor enters "3" "6" "test@admin.com" for order item
    Then Actor should see message about invalid credentials

  Scenario: User tries to add new order item with invalid meal id
    When Actor enters "3" "15" "admin@admin.com" for order item
    Then Actor should see message that meal is not found

  Scenario: User successfully add new order item to order
    When Actor enters "3" "6" "admin@admin.com" for order item
    Then Actor should see message about successfully adding order item

  Scenario: User tries to add order item quantity with invalid orderItem id
    When Actor tries to add order items quantity for order item with id 18
    Then Actor should see message that order item is not found

  Scenario: User adds order item quantity with valid orderItem id
    When Actor tries to add order items quantity for order item with id 2
    Then Actor should see message about successfully adding order item quantity

  Scenario: User tries to minus order item quantity with invalid orderItem id
    When Actor tries to minus order items quantity for order item with id 18
    Then Actor should see message that order item is not found

  Scenario: User minus order item quantity with valid orderItem id
    When Actor tries to minus order items quantity for order item with id 1
    Then Actor should see message about successfully minus order item quantity

  Scenario: User tries to delete order item with invalid orderItem id
    When Actor tries to delete order item with id 18
    Then Actor should see message that order item is not found

  Scenario: User deletes order item with valid orderItem id
    When Actor tries to delete order item with id 7
    Then Actor should see message about successfully delete order item with id

  Scenario: User gets order items for order with invalid order id
    When Actor gets order items for order with id 22
    Then Actor should see message that order is not found

  Scenario: User gets order items for order with valid order id
    When Actor gets order items for order with id 2
    Then Actor should see list of 2 order items

  Scenario: User gets order items for user active order with invalid username
    When Actor gets order items for user active order with "test@admin.com"
    Then Actor should see message about invalid credentials

  Scenario: User tries to pay for a order with non existing order id
    When Actor tries to pay for order with id 28
    Then Actor should see message that order is not found

  Scenario: User tries to pay for a order with existing order id
    When Actor tries to pay for order with id 1
    Then Actor should see message about successfully paying for order