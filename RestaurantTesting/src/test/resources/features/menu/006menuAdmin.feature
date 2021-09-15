Feature: Admin is logged in and views the menu, list of meals

  Scenario: Admin user is logged in and opens the form for adding new meal
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on menu page
    When Admin user clicks on add new meal button
    Then Admin user should be redirected to add new meal

  Scenario: Admin user is logged in and add new meal
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on add meal to menu page
    When Admin enters name "Add meal" description "Add meal description" price "100" category "Main meal" imageUrl "https://i.pinimg.com/474x/99/c6/71/99c6716be635fdb4c28cdfce5f15adb6.jpg" ingredients "Lettuce,Chicken,Croutons"
    Then Admin user should see 13 meals on menu page

  Scenario: Admin wants to edit meal named Meal for edit so edit form should be displayed
  name:Meal for edit, description:Meal for edit description,  price:122,  category:Salad
  imageUrl: https://food-guide.canada.ca/sites/default/files/styles/square_400_x_400/public/2020-12/CFGPlate-crop400x400.jpg
  Ingredients for meal: Onion, Mozzarella, Tuna, Lettuce
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on menu page
    When Admin clicks on Edit button for Meal for edit with id 2
    Then Admin should be redirected to Edit page for meal with id 2
    And Admin should see that name is "Meal for edit" description is "Meal for edit description" price is "122" category is "Salad" imageUrl is "https://food-guide.canada.ca/sites/default/files/styles/square_400_x_400/public/2020-12/CFGPlate-crop400x400.jpg" and ingredients "5,6,7,8"

  Scenario: Admin wants to edit meal named Meal for edit with new name Edited meal
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on menu page
    And Admin clicks on Edit button for Meal for edit with id 2
    When Admin enters "Edited meal" in name field for edit meal
    Then Admin should be redirected to menu page

  Scenario: Admin wants to edit meal named Meal for edit with new description Edited meal description
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on menu page
    And Admin clicks on Edit button for Meal for edit with id 2
    When Admin enters "Edited meal description" in description field for edit meal
    Then Admin should be redirected to menu page

  Scenario: Admin wants to edit meal named Meal for edit with new price 35
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on menu page
    And Admin clicks on Edit button for Meal for edit with id 2
    When Admin enters "35" in price field for edit meal
    Then Admin should be redirected to menu page

  Scenario:Admin wants to edit meal named Meal for edit with new category Pizza
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on menu page
    And Admin clicks on Edit button for Meal for edit with id 2
    When Admin enters "Pizza" in category field for edit meal
    Then Admin should be redirected to menu page

  Scenario: Admin wants to edit meal named Meal for edit with new name imageUrl:https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on menu page
    And Admin clicks on Edit button for Meal for edit with id 2
    When Admin enters "https://storcpdkenticomedia.blob.core.windows.net/media/recipemanagementsystem/media/recipe-media-files/recipes/retail/desktopimages/15534.jpg?ext=.jpg" in imageUrl field for edit meal
    Then Admin should be redirected to menu page

  Scenario: Admin wants to edit Ingredients: Bacon, Pancake,Ketchup
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on menu page
    And Admin clicks on Edit button for Meal for edit with id 2
    When Admin enters "Chicken,Croutons,Corn,Cucumber" in ingredients field for edit meal
    Then Admin should be redirected to menu page

  Scenario: Admin want to delete Meal for delete by clicking on delete button after meal
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on menu page
    When Admin clicks on Delete button for Meal for delete with id 1
    Then Admin user should see 12 meals on menu page