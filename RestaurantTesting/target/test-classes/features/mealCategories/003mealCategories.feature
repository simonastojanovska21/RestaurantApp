Feature: User with role admin is logged in and manipulates with meal categories

  Scenario: Admin user is logged in and navigates to meal categories page
    Given Admin user, ROLE_ADMIN, tries to login
    When Admin user opens profile page
    And Admin user clicks on meal categories button
    Then Admin should be redirected to meal categories page

  Scenario:Admin user is logged in and views the list of meal categories, there should be 11 rows
    Given Admin user, ROLE_ADMIN, tries to login
    When Admin user is on meal categories page
    Then Admin user should see table with meal categories with 11 rows

  Scenario:Admin user is logged in and open the form for adding new meal category
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on meal categories page
    When Admin user clicks on add new meal category button
    Then Admin user should be redirected to add new meal category page

  Scenario:Admin user is logged in and adds new meal category with Name testCategory
  and imageUrl https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on add new meal category page
    When Admin user enters "testCategory" and "https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg" for meal category
    Then Admin user should see table with meal categories with 12 rows

  Scenario:Admin user is logged in and opens the form for editing meal category "Meal category for edit"
  there should be name field with value Meal category for edit
  and imageUrl with value https://i.pinimg.com/originals/c9/c2/b1/c9c2b12a2b325f0080a1f328a0963341.jpg
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on meal categories page
    When Admin clicks on Edit button for Meal category for edit with id 10
    Then Admin should be redirected to Edit page for meal category
    And Admin should see that name field has value "Meal category for edit" and imageUrl has value "https://i.pinimg.com/originals/c9/c2/b1/c9c2b12a2b325f0080a1f328a0963341.jpg"

  Scenario:Admin user is logged in and edits Meal category for edit meal category with name EditedMealCategory
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on meal categories page
    And Admin clicks on Edit button for Meal category for edit with id 10
    When Admin enters "EditedMealCategory" in name field for edit meal category
    Then Admin should be redirected to meal categories page

  Scenario:Admin user is logged in and edits Meal category for edit meal category with imageUrl https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on meal categories page
    And Admin clicks on Edit button for Meal category for edit with id 10
    When Admin enters "https://www.fitfatherproject.com/wp-content/uploads/2017/09/meal-prep-400.jpg" in imageUrl field for edit meal category
    Then Admin should be redirected to meal categories page

  Scenario:Admin user is logged in deletes "Meal category for delete" meal category
    Given Admin user, ROLE_ADMIN, tries to login
    And Admin user is on meal categories page
    When Admin clicks on Delete button for Meal category for delete with id 11
    Then Admin user should see table with meal categories with 11 rows