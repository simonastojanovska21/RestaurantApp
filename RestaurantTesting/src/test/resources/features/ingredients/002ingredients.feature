Feature: User with role admin is logged in and manipulates with ingredients

    Scenario: Admin user is logged in and navigates to ingredients page
        Given Admin user, ROLE_ADMIN, tries to login
        When Admin user opens profile page
        And Admin user clicks on ingredients button
        Then Admin should be redirected to ingredients page

    Scenario:Admin user is logged in and views the list of ingredients, there should be 33 rows
        Given Admin user, ROLE_ADMIN, tries to login
        When Admin user is on ingredients page
        Then Admin user should see table with ingredients with 33 rows

    Scenario:Admin user is logged in and opens the form for adding new ingredient
        Given Admin user, ROLE_ADMIN, tries to login
        And Admin user is on ingredients page
        When Admin user clicks on add new ingredient button
        Then Admin user should be redirected to add new ingredient page

    Scenario:Admin user is logged in and adds new ingredient with name Ice cream and quantity 52, 34 rows
        Given Admin user, ROLE_ADMIN, tries to login
        And Admin user is on add ingredient page
        When Admin user enters "Ice cream" and "52" for ingredient and clicks submit
        Then Admin user should see table with ingredients with 34 rows

    Scenario:Admin user is logged in and opens the form for editing Fish ingredient, so name field should
        have value Fish and and quantity field should have 23 value.
        Given Admin user, ROLE_ADMIN, tries to login
        And Admin user is on ingredients page
        When Admin clicks on Edit button for Fish with id 30
        Then Admin should be redirected to Edit page for ingredient
        And Admin should see that name field has value "Fish" and quantity field has value "23"

    Scenario: Admin user is logged in and edits Fish ingredient with name EditedFish
        Given Admin user, ROLE_ADMIN, tries to login
        And Admin user is on ingredients page
        And Admin clicks on Edit button for Fish with id 30
        When Admin enters "EditedFish" in name field for edit ingredient and clicks submit
        Then Admin should be redirected to ingredients page
        And Admin should see row with name column "EditedFish" and quantity "23"

    Scenario: Admin user is logged in and edits Fish ingredient with quantity 55
        Given Admin user, ROLE_ADMIN, tries to login
        And Admin user is on ingredients page
        And Admin clicks on Edit button for Fish with id 30
        When Admin enters "55" in quantity field for edit ingredient and clicks submit
        Then Admin should be redirected to ingredients page
        And Admin should see row with name column "EditedFish" and quantity "55"

    Scenario:Admin user is logged in deletes Carrot ingredient with id 11, 33 rows
        Given Admin user, ROLE_ADMIN, tries to login
        And Admin user is on ingredients page
        When Admin clicks on Delete button for Carrot ingredient with id 11
        Then Admin user should see table with ingredients with 33 rows

