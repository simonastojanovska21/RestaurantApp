package starter.stepdefinitions.menu;

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
import starter.search.actions.PerformActionsForUserManagement;
import starter.search.actions.PerformMealActions;
import starter.search.questions.MealQuestions;
import starter.search.targets.MealTargets;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class MealStepDefinitions {

    @Given("{actor} user, ROLE_CUSTOMER, tries to login")
    public void customer2UserTriesToLogin(Actor actor){
        actor.attemptsTo(NavigateTo.theLoginPage());
        actor.attemptsTo(PerformActionsForUserManagement.loginUser("customer2@test.com","P@ssword"));
        actor.attemptsTo(
                Ensure.that(ElementLocated.by(HeaderButtons.PROFILE_BUTTON)).isDisplayed()
        );
    }

    @And("{actor} user is on menu page")
    public void customerIsOnMenuPage(Actor actor){

        actor.attemptsTo(
                NavigateTo.toMenuPage()
        );
        Serenity.takeScreenshot();
    }

    @When("{actor} user clicks on meal with name Meal2")
    public void customerClicksOnMeal2(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(".meal-4")
        );
    }

    @Then("{actor} user should be redirected to Meal2 details page")
    public void customerIsOnMeal2DetailsPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/meal/details/4")
        );
    }

    @And("{actor} user should see name {string} description {string} price {string} ingredients {string}")
    public void customerChecksMealData(Actor actor,String name, String description, String price, String listOfIngredients){
        List<String> ingredients= Arrays.asList(listOfIngredients.split(","));

        actor.attemptsTo(
                Ensure.that(ElementLocated.by(".mealDetailsName-4")).hasText(name)
        );

        actor.attemptsTo(
                Ensure.that(ElementLocated.by(".mealDetailsDescription-4")).hasText(description)
        );

        actor.attemptsTo(
                Ensure.that(ElementLocated.by(".mealDetailsPrice-4")).hasText(price)
        );

        actor.should(
                seeThat(MealQuestions.mealDetailsIngredients(),equalTo(ingredients))
        );
    }

    @When("{actor} user clicks {int} times on plus button for Meal2")
    public void customerClicksOnPlusButton(Actor actor, int times){
        for(int i=0;i<times;i++)
        {
            actor.attemptsTo(Click.on(MealTargets.MEAL2_PLUS_BUTTON));
        }
    }

    @Then("{actor} user should see that the value of input for Meal2 is {string}")
    public void customerChecksValue(Actor actor, String expectedValue){
        actor.should(
                seeThat(MealQuestions.Meal2QuantityFieldValue(),equalTo(expectedValue))
        );
    }

    @And("{actor} should see that the value of quantity field for Meal2 is set to {string}")
    public void setValueOfQuantity(Actor actor, String quantity){

        for (int i=1;i<Integer.parseInt(quantity);i++)
        {
            actor.attemptsTo(
                    Click.on(MealTargets.MEAL2_PLUS_BUTTON)
            );
        }

        actor.should(
                seeThat(MealQuestions.Meal2QuantityFieldValue(),equalTo(quantity))
        );
    }

    @When("{actor} user clicks {int} times on minus button for Meal2")
    public void customerClicksOnMinusButton(Actor actor, int times){
        for(int i=0;i<times;i++)
        {
            actor.attemptsTo(Click.on(MealTargets.MEAL2_MINUS_BUTTON));
        }
    }

    @Then("{actor} should see table with {int} meals on bottom of page")
    public void customerShouldSeeMostOrderedMeals(Actor actor, int numberMostOrdered){
        actor.attemptsTo(
                Ensure.thatTheSetOf(ElementsLocated.by(".mostOrderedMeals")).hasSize(numberMostOrdered)
        );
    }

    @And("{actor} should see meals names {string}")
    public void customerSeesNamesOfMostOrderedMeals(Actor actor, String listOfNames){
        actor.should(
                seeThat(MealQuestions.mostOrderedMealsName(),equalTo(Arrays.asList(listOfNames.split(","))))
        );
    }

    @When("{actor} clicks on Add button from details page for Meal2")
    public void customerClicksOnAddButtonForMeal2(Actor actor){
        actor.attemptsTo(
                Click.on(MealTargets.MEAL2_ADD_TO_SHOPPING_CART_BUTTON)
        );
    }

    @Then("{actor} should have {string} in shopping cart with quantity {string}")
    public void customerHasItemsInShoppingCart(Actor actor,String mealName, String quantity) throws InterruptedException {
        Thread.sleep(2000);
        actor.attemptsTo(
                NavigateTo.theShoppingCartPage()
        );
        actor.should(
            seeThat(MealQuestions.mealInShoppingCartName(mealName),equalTo(mealName))
        );
        actor.should(
                seeThat(MealQuestions.mealInShoppingCartQuantity(mealName),equalTo(quantity))
        );
    }

    @And("{actor} user clicks {int} times on plus button for Meal7")
    public void customerClicksOnPlusButtonForMeal7(Actor actor, int times){
        for(int i=0;i<times;i++)
        {
            actor.attemptsTo(JavaScriptClick.on(MealTargets.MEAL7_PLUS_BUTTON));
        }
    }

    @When("{actor} clicks on Add button from details page for Meal7")
    public void customerClicksOnAddButtonForMeal1(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(MealTargets.MEAL7_ADD_TO_SHOPPING_CART_BUTTON)
        );
    }
}
