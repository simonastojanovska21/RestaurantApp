package starter.stepdefinitions.menu;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementLocated;
import starter.navigation.NavigateTo;
import starter.search.actions.PerformMenuAdminActions;
import starter.search.questions.MenuAdminQuestions;
import starter.search.targets.MenuAdminTargets;

import java.util.Arrays;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class MenuAdminStepDefinitions {

    @When("{actor} user clicks on add new meal button")
    public void adminClicksOnAddNewMeal(Actor actor){
        actor.attemptsTo(
                Click.on(MenuAdminTargets.ADD_MEAL_TO_MENU)
        );
    }

    @Then("{actor} user should be redirected to add new meal")
    public void adminShouldBeRedirectedToAddNewMeal(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/meal/add")
        );
    }

    @And("{actor} user is on add meal to menu page")
    public void adminIsNavigatedToAddMealPage(Actor actor){
        actor.attemptsTo(
                NavigateTo.toMealAddPage()
        );
    }

    @When("{actor} enters name {string} description {string} price {string} category {string} imageUrl {string} ingredients {string}")
    public void adminAddsNewMeal(Actor actor,String name, String description, String price, String category, String imageUrl, String ingredients){
        actor.attemptsTo(
                PerformMenuAdminActions.addNewMeal(name,description,price,category,imageUrl)
        );
    }

    @When("{actor} clicks on Edit button for Meal for edit with id 2")
    public void adminClicksOnEditMeal(Actor actor) throws InterruptedException {
        actor.attemptsTo(
                JavaScriptClick.on(ElementLocated.by(".mealEdit-2"))
        );
        Thread.sleep(2000);
        Serenity.takeScreenshot();
    }

    @Then("{actor} should be redirected to Edit page for meal with id 2")
    public void redirectAdminToEditMealPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/meal/edit/2")
        );
    }

    @And("{actor} should see that name is {string} description is {string} price is {string} category is {string} imageUrl is {string} and ingredients {string}")
    public void adminShouldSeeExistingValues(Actor actor,String name, String description, String price, String category, String imagerUrl, String listOfIngredients){
        Serenity.takeScreenshot();
        actor.should(
                seeThat(MenuAdminQuestions.existingMealName(),equalTo(name)),
                seeThat(MenuAdminQuestions.existingMealDescription(),equalTo(description)),
                seeThat(MenuAdminQuestions.existingMealPrice(),equalTo(price)),
                seeThat(MenuAdminQuestions.existingMealMealCategory(),equalTo(category)),
                seeThat(MenuAdminQuestions.existingMealImageUrl(),equalTo(imagerUrl)),
                seeThat(MenuAdminQuestions.existingMealIngredients(),equalTo(Arrays.asList(listOfIngredients.split(","))))

        );
    }

    @When("{actor} enters {string} in name field for edit meal")
    public void actorEditsMealName(Actor actor,String name){
        actor.attemptsTo(
                PerformMenuAdminActions.editMealName(name)
        );
    }

    @When("{actor} enters {string} in description field for edit meal")
    public void actorEditsMealDescription(Actor actor,String description){
        actor.attemptsTo(
                PerformMenuAdminActions.editMealDescription(description)
        );
    }

    @When("{actor} enters {string} in price field for edit meal")
    public void actorsEditsMealPrice(Actor actor, String price){
        actor.attemptsTo(
                PerformMenuAdminActions.editMealPrice(price)
        );
    }

    @When("{actor} enters {string} in category field for edit meal")
    public void actorsEditsMealCategory(Actor actor, String category){
        actor.attemptsTo(
                PerformMenuAdminActions.editMealCategory(category)
        );
    }

    @When("{actor} enters {string} in imageUrl field for edit meal")
    public void adminEditsImageUrl(Actor actor, String imageUrl){
        actor.attemptsTo(
                PerformMenuAdminActions.editMealImageUrl(imageUrl)
        );
    }

    @When("{actor} enters {string} in ingredients field for edit meal")
    public void actorEditsIngredientsList(Actor actor,String ingredientsList){
        actor.attemptsTo(
                PerformMenuAdminActions.editMealIngredients()
        );
    }

    @When("{actor} clicks on Delete button for Meal for delete with id 1")
    public void adminDeletesMeal(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(".mealDelete-1")
        );
    }

}
