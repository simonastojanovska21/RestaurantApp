Feature: Register user

  Scenario: Successful register
    Given User wants to register to web app
    When User enters "user@user.com" "P@assword" "P@assword" "User name" "User surname" "070123456" "User address"
    Then User should be redirected to login page

  Scenario: User registers where passwords do not match
    Given User wants to register to web app
    When User enters "user@user.com" "P@assword" "password" "User name" "User surname" "070123456" "User address"
    Then User should see message that passwords do not match

  Scenario: User registers where email address already exists
    Given User wants to register to web app
    When User enters "admin@admin.com" "P@assword" "P@assword" "User name" "User surname" "070123456" "User address"
    Then User should see message that username already exists

  Scenario: User registers with empty username
    Given User wants to register to web app
    When User enters "" "P@assword" "P@assword" "User name" "User surname" "070123456" "User address"
    Then User should see empty email message

  Scenario: User registers with empty password
    Given User wants to register to web app
    When User enters "user@user.com" "" "P@assword" "User name" "User surname" "070123456" "User address"
    Then User should see empty password1 message

  Scenario: User registers with empty repeatedPassword
    Given User wants to register to web app
    When User enters "user@user.com" "P@assword" "" "User name" "User surname" "070123456" "User address"
    Then User should see empty repeatedPassword message

  Scenario: User registers with empty name
    Given User wants to register to web app
    When User enters "user@user.com" "P@assword" "P@assword" "" "User surname" "070123456" "User address"
    Then User should see empty name message

  Scenario: User registers with empty surname
    Given User wants to register to web app
    When User enters "user@user.com" "P@assword" "P@assword" "User name" "" "070123456" "User address"
    Then User should see empty surname message

  Scenario: User registers with empty phoneNumber
    Given User wants to register to web app
    When User enters "user@user.com" "P@assword" "P@assword" "User name" "User surname" "" "User address"
    Then User should see empty phoneNumber message

  Scenario: User registers with empty address
    Given User wants to register to web app
    When User enters "user@user.com" "P@assword" "P@assword" "User name" "User surname" "070123456" ""
    Then User should see empty address message