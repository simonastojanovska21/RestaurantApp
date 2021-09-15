Feature: Delivery rest controller

    Scenario: User tries to get info about delivery with non existing id
        When Actor tries to get info about delivery with id 12
        Then Actor should see message that delivery is not found

    Scenario: User tries to get info about delivery with existing id
        When Actor tries to get info about delivery with id 1
        Then Actor should see message about selected delivery

    Scenario: User gets a list of all deliveries
        When Actor tries to get a list of all deliveries
        Then Actor should see list of 2 deliveries

    Scenario: User gets a list of deliveries for day
        When Actor tries to get a list of deliveries for day
        Then Actor should see list of 1 deliveries

    Scenario: User attempts to make non existing delivery finished
        When Actor attempts to finish delivery with id 12
        Then Actor should see message that delivery is not found

    Scenario: User attempts to make existing delivery finished
        When Actor attempts to finish delivery with id 1
        Then Actor should see message about successfully finishing delivery

    Scenario: User attempts to create new delivery with invalid order id
        When Actor enters "Customer1 address" "17" for delivery
        Then Actor should see message that order is not found

    Scenario Outline: User attempts to create new delivery with empty data
        When Actor enters "<address>" "<orderId>" for delivery
        Then Actor should see message about empty fields
        Examples:
        |address          |orderId|
        |                 |0      |
        |                 |8      |
        |Customer1 address|0      |

    Scenario: User attempts to create new delivery with missing data
        When Actor attempts to create new delivery with missing data
        Then Actor should see message about missing fields

    Scenario: User successfully creates a delivery
        When Actor enters "Customer1 address" "8" for delivery
        Then Actor should see message about successful creating a delivery

    Scenario: User gets a list of deliveries for today
        When Actor tries to get a list of deliveries for today
        Then Actor should see list of 1 deliveries

