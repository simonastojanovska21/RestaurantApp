Feature: Login user
  In order for a user to have access to certain functionalities
  of the web application, the user should be logged in

  Scenario: Successful login user to the web application
    Given User wants to login to web application
    When User enters "admin@admin.com" and "P@assword"
    Then He should be redirected to home page

  Scenario: Login with empty username
    Given User wants to login to web application
    When User enters "" and "P@assword"
    Then Invalid feedback should be displayed

  Scenario: Login with empty password
    Given User wants to login to web application
    When User enters "admin@admin.com" and ""
    Then Invalid feedback should be displayed

  Scenario: Login with invalid username or password
    Given User wants to login to web application
    When User enters "test@test.com" and "P@assword"
    Then Message about invalid credentials should be displayed


