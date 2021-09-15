Feature: User with role delivery should view delivery items that are to be delivered,
  After every delivery item, there should be Delivered button, which marks the order as received

  Scenario: Delivery user is logged in and navigates to delivery page
    Given Delivery user tries to log in
    When Delivery clicks on Deliveries button
    Then Deliveries page should be displayed

  Scenario: Delivery user is logged in and is on delivery page. There should be 2 deliveries (rows) in the table
    Given Delivery user tries to log in
    When Delivery user is on deliveries page
    Then Delivery sees 2 deliveries in the table

  Scenario: Delivery user is logged in. For each delivery, there should be displayed
    delivery id, delivery address, time for delivery, button for marking as delivered and button for details
      1	        Admin address	    2021-10-02T15:30:25
      2	        Customer2 address	2021-10-02T17:30:25
    Given Delivery user tries to log in
    When Delivery user is on deliveries page
    Then Delivery user should see above data

  Scenario: When a delivery user is logged in and views the list of deliveries and clicks on Delivered for Customer2 address,
        that delivery should be removed from list
    Given Delivery user tries to log in
    And Delivery user is on deliveries page
    When Delivery user clicks on Delivered button for delivery with id 2
    Then Delivery should see that delivery with id 2 should be removed from the table

  Scenario: Delivery user is logged in and is on the delivery page after clicking delivery
    Given Delivery user tries to log in
    When Delivery user is on deliveries page
    Then Delivery sees 1 deliveries in the table