Feature: Employee is logged and and views processing order and start preparing meals. After preparation of meals for order
    the employee clicks on Ready for delivery button

    Scenario: Employee is logged in and clicks on processing orders button on home page. Processing order page is displayed
        Given Employee user, ROLE_EMPLOYEE, tries to login
        When Employee user clicks on Processing orders button
        Then Employee user should be redirected to processing orders page

    Scenario: Employee user is logged in and is on processing orders page. There should be 2 processing orders
        Given Employee user, ROLE_EMPLOYEE, tries to login
        When Employee user is on processing orders page
        Then Employee user sees 3 rows in the processing order table

    Scenario:Employee is on processing order page, displayed data is:
    Customer name           Meals               Delivery address        Ready for delivery
    admin@admin.com         Meal2 x 5               Admin address
                            Meal4 x 4

    customer2@test.com      Meal3 x 1               Customer 2 address
                            Meal9 x 6

    customer1@test.com      Meal9 x 2               Customer1 address
        Given Employee user, ROLE_EMPLOYEE, tries to login
        When Employee user is on processing orders page
        Then Employee user should see above rows in the processing order table

    Scenario:Employee is on processing order page and clicks on ready for delivery button for customer1@test.com order,
    that row should be deleted and new delivery should be created
        Given Employee user, ROLE_EMPLOYEE, tries to login
        And Employee user is on processing orders page
        When Employee user clicks on Ready for delivery button for order with id 8
        Then Employee user should see that the order with id 8 was removed from the table

    Scenario: Employee user is logged in and is on processing order page after clicking on ready for delivery button
        Given Employee user, ROLE_EMPLOYEE, tries to login
        When Employee user is on processing orders page
        Then Employee user sees 2 rows in the processing order table

    Scenario:Delivery user is on deliveries page and should see records about customer1@test.com order.
        Given Delivery user tries to log in
        When Delivery user is on deliveries page
        Then Delivery sees 2 deliveries in the table