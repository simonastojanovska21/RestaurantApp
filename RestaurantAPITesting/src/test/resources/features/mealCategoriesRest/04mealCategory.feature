Feature: MealCategories rest controller

  Scenario: User tries to get list of meal categories
    When Actor tries to get a list of all meal categories
    Then Actor should see list of 11 meal categories

  Scenario: User tries to get details about meal category with non existing id
    When Actor tries to get info about meal categories with id 12
    Then Actor should see message that meal category is not found

  Scenario: User tries to get details about meal category with existing id
    When Actor tries to get info about meal categories with id 1
    Then Actor should see message about selected meal categories

  Scenario: User tries to add new meal category with missing fields
    When Actor attempts to create new meal categories with missing data
    Then Actor should see message about missing fields

  Scenario Outline: User tries to add new meal category with empty fields
    When Actor enters "<name>" "<imageUrl>" for meal category
    Then Actor should see message about empty fields
    Examples:
    |name             |imageUrl   |
    |                 |           |
    |TestMealCategory|           |
    |                 |https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg|

  Scenario: User tries to add new meal category with existing name
    When Actor enters "Breakfast" "https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg" for meal category
    Then Actor should see message that meal category with name already exists

  Scenario: User successfully adds new meal category
    When Actor enters "TestMealCategory" "https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg" for meal category
    Then Actor should see message about successful adding meal category

  Scenario: User tries to edit meal category with non existing id
    When Actor tries to edit meal category with id 15
    Then Actor should see message that meal category is not found

  Scenario: User tries to edit meal category with existing id but with missing field
    When Actor tries to edit meal category with id 10 with missing data
    Then Then Actor should see message about missing fields

  Scenario Outline: User tries to edit meal category with existing id but with empty fields
    When Actor tries to edit meal category with id 10 and enters "<name>" "<imageUrl>"
    Then Then Actor should see message about empty fields
    Examples:
    |name         |imageUrl     |
    |             |             |
    |EditedMealCategory|        |
    |                  |https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg|

  Scenario: User successfully edits meal category with existing id
    When Actor tries to edit meal category with id 10 and enters "EditedMealCategory" "https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg"
    Then Actor should see message about successfully edited meal categories

  Scenario: User tries to delete meal category with non existing id
    When Actor tries to delete meal category with id 110
    Then Actor should see message that meal category is not found

  Scenario: User successfully deletes meal category
    When Actor tries to delete meal category with id 11
    Then Actor should see message about successfully deleting meal category