Feature: Ingredient rest controller

    Scenario: User tries to get list of ingredients
        When Actor tries to get a list of all ingredients
        Then Actor should see list of 33 ingredients

    Scenario: User tries to get details about ingredient with non existing id
        When Actor tries to get info about ingredient with id 47
        Then Actor should see message that ingredient is not found

    Scenario: User tries to get details about ingredient with existing id
        When Actor tries to get info about ingredient with id 1
        Then Actor should see message about selected ingredient

    Scenario: User tries to add new ingredient with missing fields
        When Actor attempts to create new ingredient with missing data
        Then Actor should see message about missing fields

    Scenario Outline: User tries to add new ingredient with empty fields
        When Actor enters "<name>" "<quantity>" for ingredient
        Then Actor should see message about empty fields
        Examples:
        |name              |quantity|
        |                  |0       |
        |IngredientTestName|0       |
        |                  |10      |

    Scenario: User tries to add new ingredient with existing name
        When Actor enters "Cheese" "5" for ingredient
        Then Actor should see message that ingredient with name already exists

    Scenario: User successfully adds new ingredient
        When Actor enters "IngredientTestName" "10" for ingredient
        Then Actor should see message about successful adding an ingredient

    Scenario: User tries to edit ingredient with non existing id
        When Actor tries to edit ingredient with id 55
        Then Actor should see message that ingredient is not found

    Scenario: User tries to edit ingredient with existing id but with missing field
        When Actor tries to edit ingredient with id 26 with missing data
        Then Then Actor should see message about missing fields

    Scenario Outline: User tries to edit ingredient with existing id but with empty fields
        When Actor tries to edit ingredient with id 26 and enters "<name>" "<quantity>"
        Then Then Actor should see message about empty fields
        Examples:
        |name       |quantity   |
        |           |0          |
        |EditedLime |0          |
        |           |100        |

    Scenario: User successfully edits ingredient with existing id
        When Actor tries to edit ingredient with id 26 and enters "EditedLime" "100"
        Then Actor should see message about successfully edited ingredient

    Scenario: User tries to delete ingredient with non existing id
        When Actor tries to delete ingredient with id 110
        Then Actor should see message that ingredient is not found

    Scenario: User successfully deletes ingredient
        When Actor tries to delete ingredient with id 11
        Then Actor should see message about successfully deleting ingredient
