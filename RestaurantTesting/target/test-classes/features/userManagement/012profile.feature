Feature: User views his profile page

  Scenario: Admin user clicks on profile button
    Given Admin user, ROLE_ADMIN, tries to login
    When Admin user clicks on profile button
    Then Admin user should be redirected to profile page

  Scenario: Admin user clicks on profile button
    Given Admin user, ROLE_ADMIN, tries to login
    When Admin user is on profile page
    Then Admin user should see mealCategories and ingredients buttons

  Scenario: Customer user clicks on profile button
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    When Customer2 user is on profile page
    Then Customer2 user should not see mealCategories and ingredients buttons

  Scenario: Delivery user clicks on profile button
    Given Delivery user tries to log in
    When Delivery user is on profile page
    Then Delivery user should not see mealCategories and ingredients buttons

  Scenario: Employee user clicks on profile button
    Given Employee user, ROLE_EMPLOYEE, tries to login
    When Employee user is on profile page
    Then Employee user should not see mealCategories and ingredients buttons

  Scenario: Customer should see his private information
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    When Customer2 user is on profile page
    Then Customer2 user should see his private information, name, surname,address, telephone number

  Scenario: Customer user leaves a review
    Given Customer2 user, ROLE_CUSTOMER, tries to login
    And Customer2 user is on profile page
    When Customer2 user clicks on 5 star button and writes "Good service" in the description field
    Then Customer2 user should see information about successful leaving a review
