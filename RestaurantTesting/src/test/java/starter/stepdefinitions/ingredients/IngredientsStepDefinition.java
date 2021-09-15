package starter.stepdefinitions.ingredients;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementLocated;
import net.serenitybdd.screenplay.ensure.web.ElementsLocated;
import starter.navigation.NavigateTo;
import starter.search.HeaderButtons;
import starter.search.actions.PerformActionsForIngredients;
import starter.search.actions.PerformActionsForUserManagement;
import starter.search.targets.IngredientsTargets;
import starter.search.targets.ProfileTargets;
import starter.search.questions.IngredientsQuestions;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class IngredientsStepDefinition {

    @Given("{actor} user, ROLE_ADMIN, tries to login")
    public void loginAdminUser(Actor actor) {
        actor.attemptsTo(NavigateTo.theLoginPage());
        actor.attemptsTo(PerformActionsForUserManagement.loginUser("admin@admin.com","P@ssword"));
        actor.attemptsTo(
                Ensure.that(ElementLocated.by(HeaderButtons.PROFILE_BUTTON)).isDisplayed()
        );
        Serenity.takeScreenshot();
    }

    @When("{actor} user opens profile page")
    public void openProfilePage(Actor actor) {
        actor.attemptsTo(
                NavigateTo.toProfilePage()
        );
    }

    @And("{actor} user clicks on ingredients button")
    public void adminClicksOnIngredientButton(Actor actor){
        actor.attemptsTo(
                Click.on(ProfileTargets.INGREDIENTS_BUTTON)
        );
    }

    @Then("{actor} should be redirected to ingredients page")
    public void actorIsRedirectedToIngredientsPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/ingredients")
        );
    }

    @When("{actor} user is on ingredients page")
    public void adminInOnIngredientsPage(Actor actor){
        actor.attemptsTo(
                NavigateTo.toIngredientsPage()
        );
    }

    @Then("{actor} user should see table with ingredients with {int} rows")
    public void checkNumberOfRowsInTable(Actor actor, int number) throws InterruptedException {
        Thread.sleep(2000);
        actor.attemptsTo(
                Ensure.thatTheSetOf(ElementsLocated.by(IngredientsTargets.INGREDIENTS_ROWS)).hasSize(number)
        );
    }

    @When("{actor} user clicks on add new ingredient button")
    public void adminClicksOnAddNewIngredient(Actor actor){
        actor.attemptsTo(
                Click.on(IngredientsTargets.ADD_INGREDIENT_BUTTON)
        );
    }

    @Then("{actor} user should be redirected to add new ingredient page")
    public void adminIsRedirectedToAddNewIngredient(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/ingredients/add")
        );
    }

    @And("{actor} user is on add ingredient page")
    public void adminIsOnAddPage(Actor actor){
        actor.attemptsTo(NavigateTo.toIngredientsAddPage());
    }

    @When("{actor} user enters {string} and {string} for ingredient and clicks submit")
    public void enterNameAndQuantity(Actor actor, String name, String value){
        actor.attemptsTo(
                PerformActionsForIngredients.addNewIngredient(name,value)
        );
    }

    @When("{actor} clicks on Edit button for Fish with id 30")
    public void adminClicksEditButton(Actor actor){

//        actor.attemptsTo(
//                Scroll.to(ElementLocated.by(".ingredientEdit-30"))
//        );

        actor.attemptsTo(
                JavaScriptClick.on(ElementLocated.by(".ingredientEdit-30"))
        );
    }

    @Then("{actor} should be redirected to Edit page for ingredient")
    public void adminIsRedirectedToEditPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/ingredients/edit/30")
        );
    }

    @And("{actor} should see that name field has value {string} and quantity field has value {string}")
    public void adminShouldSeeValuesBeforeEdit(Actor actor,String  name, String quantity){
        actor.should(
                seeThat(IngredientsQuestions.existingIngredientName(),equalTo(name))
        );
        actor.should(
                seeThat(IngredientsQuestions.existingIngredientQuantity(),equalTo(quantity))
        );
    }

    @When("{actor} enters {string} in name field for edit ingredient and clicks submit")
    public void adminEntersEditedName(Actor actor, String name){
        actor.attemptsTo(
                PerformActionsForIngredients.editIngredientName(name)
        );
    }

    @When("{actor} enters {string} in quantity field for edit ingredient and clicks submit")
    public void adminEnterEditedQuantity(Actor actor, String quantity){
        actor.attemptsTo(
                PerformActionsForIngredients.editIngredientQuantity(quantity)
        );
    }

    @And("{actor} should see row with name column {string} and quantity {string}")
    public void adminSeesQuantityFieldForFish(Actor actor,String name, String quantity){
        actor.should(
                seeThat(IngredientsQuestions.fishIngredientName(),equalTo(name))
        );
        actor.should(
                seeThat(IngredientsQuestions.fishIngredientQuantity(),equalTo(quantity))
        );
    }

    @When("{actor} clicks on Delete button for Carrot ingredient with id 11")
    public void actorDeletesCarrotIngredient(Actor actor) throws InterruptedException {
        actor.attemptsTo(
                JavaScriptClick.on(ElementLocated.by(".ingredientDelete-11"))
        );
        Thread.sleep(2000);
    }
}
