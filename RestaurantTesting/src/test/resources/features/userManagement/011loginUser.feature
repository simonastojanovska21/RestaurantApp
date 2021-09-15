Feature: Login user
  In order for a user to have access to certain functionalities
  of the web application, the user should be logged in

  Scenario: Successful login user to the web application
    Given Admin wants to login to web application
    When Admin enters "admin@admin.com" and "P@assword"
    Then Admin should be redirected to home page

  Scenario: Login with empty username
    Given User wants to login to web application
    When User enters "" and "P@assword"
    Then User should see empty username message

  Scenario: Login with empty password
    Given User wants to login to web application
    When User enters "admin@admin.com" and ""
    Then User should see empty password message

  Scenario: Login with invalid username or password
    Given User wants to login to web application
    When User enters "test@test.com" and "P@assword"
    Then User should see message about invalid credentials

  Scenario: Logout user
    Given Admin user is logged in
    When Admin clicks logout button
    Then Shopping cart,deliveries,processingOrders,profile,logout buttons should not be displayed
