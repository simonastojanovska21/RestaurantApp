package starter.stepdefinitions.mealCategories;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementLocated;
import net.serenitybdd.screenplay.ensure.web.ElementsLocated;
import starter.navigation.NavigateTo;
import starter.search.actions.PerformActionsForMealCategories;
import starter.search.targets.MealCategoriesTargets;
import starter.search.targets.ProfileTargets;
import starter.search.questions.MealCategoriesQuestions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class MealCategoriesStepDefinition {

    @And("{actor} user clicks on meal categories button")
    public void adminClicksOnMealCategoriesButton(Actor actor){
        actor.attemptsTo(
                Click.on(ProfileTargets.MEAL_CATEGORIES_BUTTON)
        );
    }

    @Then("{actor} should be redirected to meal categories page")
    public void adminIsRedirectedToMealCategoriesPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/mealCategories")
        );
    }

    @When("{actor} user is on meal categories page")
    public void adminIsOnMealCategoriesPage(Actor actor){
        actor.attemptsTo(
                NavigateTo.toMealCategoriesPage()
        );
    }

    @Then("{actor} user should see table with meal categories with {int} rows")
    public void checkNumberOfRows(Actor actor,int number){
        actor.attemptsTo(
                Ensure.thatTheSetOf(ElementsLocated.by(".mealCategoriesRow")).hasSize(number)
        );
    }

    @When("{actor} user clicks on add new meal category button")
    public void adminClicksOnAddNewMealCategories(Actor actor){
        actor.attemptsTo(
                Click.on(MealCategoriesTargets.ADD_MEAL_CATEGORIES_BUTTON)
        );
    }

    @Then("{actor} user should be redirected to add new meal category page")
    public void adminIsRedirectedToAddNewMealCategory(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/mealCategories/add")
        );
    }

    @And("{actor} user is on add new meal category page")
    public void adminIsOnAddNewMealCategoryPage(Actor actor){
        actor.attemptsTo(
                NavigateTo.toMealCategoriesAddPage()
        );
    }

    @When("{actor} user enters {string} and {string} for meal category")
    public void adminEntersValuesForMealCategory(Actor actor, String name, String imageUrl) throws InterruptedException {
        actor.attemptsTo(
                PerformActionsForMealCategories.addNewMealCategory(name,imageUrl)
        );
        Thread.sleep(2000);
    }

    @When("{actor} clicks on Edit button for Meal category for edit with id 10")
    public void adminClicksOnEditButton(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(ElementLocated.by(".mealCategoryEdit-10"))
        );
    }

    @Then("{actor} should be redirected to Edit page for meal category")
    public void adminIsRedirectedToEditPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/mealCategories/edit/10")
        );
    }

    @And("{actor} should see that name field has value {string} and imageUrl has value {string}")
    public void adminShouldSeeExistingValues(Actor actor, String name, String imageUrl){
        actor.should(
                seeThat(MealCategoriesQuestions.existingMealCategoryName(),equalTo(name))
        );
        actor.should(
                seeThat(MealCategoriesQuestions.existingMealCategoryImageUrl(),equalTo(imageUrl))
        );
    }

    @When("{actor} enters {string} in name field for edit meal category")
    public void adminEntersEditedName(Actor actor, String name) throws InterruptedException {
        actor.attemptsTo(
                PerformActionsForMealCategories.editMealCategoryName(name)
        );
        //Thread.sleep(2000);
    }

    @When("{actor} enters {string} in imageUrl field for edit meal category")
    public void adminEditsImageUrl(Actor actor, String imageUrl){
        actor.attemptsTo(
                PerformActionsForMealCategories.editMealCategoryImageUrl(imageUrl)
        );
    }

    @And("{actor} should see row with name column {string} and imageUrl {string}")
    public void adminSeesEditedValue(Actor actor,String name, String imageUrl){
        actor.should(
                seeThat(MealCategoriesQuestions.editMealCategoryName(),equalTo(name))
        );
        actor.should(
                seeThat(MealCategoriesQuestions.editMealCategoryImageUrl(),equalTo(imageUrl))
        );
    }

    @When("{actor} clicks on Delete button for Meal category for delete with id 11")
    public void adminDeletesMealCategory(Actor actor) throws InterruptedException {
        actor.attemptsTo(
                JavaScriptClick.on(ElementLocated.by(".mealCategoryDelete-11"))
        );
        Thread.sleep(2000);
    }



}
