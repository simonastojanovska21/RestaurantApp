Feature: Customer2 adds meals in shopping cart and can manipulate with the

    Scenario:Customer2 is on Shopping cart page and there should be 5 order items,
        Given Customer2 user, ROLE_CUSTOMER, tries to login
        When Customer2 user is on shopping cart page
        Then Customer2 user should see table with 5 ordered items

    Scenario:Customer2 is on Shopping cart page and there should be 5 order items with below data,
        Meal        Price       Quantity        Subtotal
        Meal6       50              4               200
        Meal8       27              5               135
        Meal2       10              5               50
        Meal7       20              3               60
        Meal3       32              4               128
        Total 573

        Given Customer2 user, ROLE_CUSTOMER, tries to login
        When Customer2 user is on shopping cart page
        Then Customer2 user should see above data about ordered items

    Scenario:Customer2 is on Shopping cart page and clicks on the delete button next to Meal6 (order item with id 7), total=380
        Given Customer2 user, ROLE_CUSTOMER, tries to login
        And Customer2 user is on shopping cart page
        When Customer2 user clicks on delete button for Meal6, order item id 7
        Then Customer2 user should see that total is "373"

    Scenario:Customer2 clicks on continue shopping button and he is redirected to menu page
        Given Customer2 user, ROLE_CUSTOMER, tries to login
        And Customer2 user is on shopping cart page
        When Customer2 user clicks on continue shopping button
        Then Customer2 should be redirected to menu page

    Scenario:Customer2 is on shopping cart page and clicks on check out button, and he is redirected to /checkOut page
        Given Customer2 user, ROLE_CUSTOMER, tries to login
        And Customer2 user is on shopping cart page
        When Customer2 user clicks on checkout button
        Then Customer2 user should be redirected to checkout page

    Scenario: Customer2 is on checkout page and total is 380, delivery fee 0 and total with delivery is 380
        Given Customer2 user, ROLE_CUSTOMER, tries to login
        When Customer2 user is on checkout page
        Then Customer2 user should see total "373 $" delivery fee "0 $" and total with delivery "373 $"