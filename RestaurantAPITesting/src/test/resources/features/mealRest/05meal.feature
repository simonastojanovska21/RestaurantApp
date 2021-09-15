Feature: Meal rest controller


  Scenario: User tries to get list of meals
    When Actor tries to get a list of all meals
    Then Actor should see list of 12 meals

  Scenario: User tries to get details about meal with non existing id
    When Actor tries to get info about meal with id 17
    Then Actor should see message that meal is not found

  Scenario: User tries to get details about meal with existing id
    When Actor tries to get info about meal with id 3
    Then Actor should see message about selected meal

  Scenario: User tries to add new meal with missing fields
    When Actor attempts to create new meal with missing data
    Then Actor should see message about missing fields

  Scenario Outline: User tries to add new meal with empty fields
    When Actor enters "<name>" "<description>" "<price>" "<mealCategory>" "<imageUrl>" "<ingredients>" for meal
    Then Actor should see message about empty fields
    Examples:
    |name        |description          |price|mealCategory|imageUrl                                                               |ingredients|
    |            |                     |0    |0           |                                                                       |           |
    |            |Test meal description|200  |9           |https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg|8,9,10     |
    |TestMealName|                     |200  |9           |https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg|8,9,10     |
    |TestMealName|Test meal description|0    |9           |https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg|8,9,10     |
    |TestMealName|Test meal description|200  |0           |https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg|8,9,10     |
    |TestMealName|Test meal description|200  |9           |                                                                       |8,9,10     |
    |TestMealName|Test meal description|200  |9           |https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg|           |

  Scenario: User tries to add new meal with existing name
    When Actor enters "Meal1" "Test meal description" "200" "9" "https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg" "8,9,10" for meal
    Then Actor should see message that meal with name already exists

  Scenario: User tries to add new meal with non existing meal category
    When Actor enters "TestMealName" "Test meal description" "200" "15" "https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg" "8,9,10" for meal
    Then Actor should see message that meal category is not found

  Scenario: User successfully adds new meal
    When Actor enters "TestMealName" "Test meal description" "200" "9" "https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg" "8,9,10" for meal
    Then Actor should see message about successful adding meal

  Scenario: User tries to edit meal with non existing id
    When Actor tries to edit meal with id 25
    Then Actor should see message that meal is not found

  Scenario: User tries to edit meal with existing id but with missing field
    When Actor tries to edit meal with id 2 with missing data
    Then Then Actor should see message about missing fields

  Scenario Outline: User tries to edit meal with existing id but with empty fields
    When Actor tries to edit meal with id 2 and enters "<name>" "<description>" "<price>" "<mealCategory>" "<imageUrl>" "<ingredients>"
    Then Actor should see message about empty fields
    Examples:
    |name        |description             |price|mealCategory|imageUrl                                                                                                                                               |ingredients|
    |            |                        |0    |0           |                                                                                                                                                       |           |
    |            |Edited meal description |35   |2           |https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg|9,10,12,13 |
    |Edited meal |                        |35   |2           |https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg|9,10,12,13 |
    |Edited meal |Edited meal description |0    |2           |https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg|9,10,12,13 |
    |Edited meal |Edited meal description |35   |0           |https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg|9,10,12,13 |
    |Edited meal |Edited meal description |35   |2           |                                                                                                                                                       |9,10,12,13 |
    |Edited meal |Edited meal description |35   |2           |https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg|           |

  Scenario: User tries to edit meal with exiting id but with non existing meal category
    When Actor tries to edit meal with id 2 and enters "Edited meal" "Edited meal description" "35" "15" "https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg" "9,10,12,13"
    Then Actor should see message that meal category is not found

  Scenario: User successfully edits meal with existing id
    When Actor tries to edit meal with id 2 and enters "Edited meal" "Edited meal description" "35" "2" "https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg" "9,10,12,13"
    Then Actor should see message about successfully edited meal

  Scenario: User tries to get meals from non existing meal category
    When Actor tries to get meals from meal category with id 25
    Then Actor should see message that meal category is not found

  Scenario: User tries to get meals from existing meal category
    When Actor tries to get meals from meal category with id 7
    Then Actor should see list of 2 meals

  Scenario: User tries to delete meal with non existing id
    When Actor tries to delete meal with id 28
    Then Actor should see message that meal is not found

  Scenario: User successfully deletes ingredient
    When Actor tries to delete meal with id 1
    Then Actor should see message about successfully deleting meal
