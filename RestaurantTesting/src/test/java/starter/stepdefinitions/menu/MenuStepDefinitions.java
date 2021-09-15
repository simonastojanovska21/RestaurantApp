package starter.stepdefinitions.menu;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementLocated;
import net.serenitybdd.screenplay.ensure.web.ElementsLocated;
import starter.search.HeaderButtons;
import starter.search.questions.MealQuestions;
import starter.search.questions.MenuQuestions;
import starter.search.targets.MealTargets;
import starter.search.targets.MenuTargets;

import java.awt.*;
import java.util.Arrays;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class MenuStepDefinitions {

    @When("{actor} user clicks on menu button")
    public void customerClicksMenuButton(Actor actor){
        actor.attemptsTo(
                Click.on(HeaderButtons.MENU_BUTTON)
        );
    }

    @Then("{actor} should be redirected to menu page")
    public void customerIsRedirectedToMenuPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/menu")
        );
    }

    @Then("{actor} user should see {int} meals on menu page")
    public void customerShouldSeeNumberOfItems(Actor actor, int number) throws InterruptedException {
        Thread.sleep(3000);
        actor.attemptsTo(
                Ensure.thatTheSetOf(ElementsLocated.by(".menuItems")).hasSize(number)
        );
    }

    @And("{actor} user clicks on meal category Pasta")
    public void customerUserClicksOnPasta(Actor actor) throws InterruptedException {
        actor.attemptsTo(
                JavaScriptClick.on(ElementLocated.by(".mealCategory-7"))
        );
        Thread.sleep(3000);
    }

    @And("{actor} user should see meals with names {string}")
    public void customerShouldSeeMealsFromCategory(Actor actor, String listOfNames) throws InterruptedException {
        Thread.sleep(2000);
        actor.should(
                seeThat(MenuQuestions.displayedMealName(),equalTo(Arrays.asList(listOfNames.split(","))))
        );
    }

    @And("{actor} user clicks on meal category Sandwich")
    public void customerUserClicksOnSandwich(Actor actor){
        actor.attemptsTo(
                Click.on(ElementLocated.by(".mealCategory-4"))
        );
    }

    @When("{actor} user clicks {int} times on plus button for Meal3")
    public void customerClicksOnPlusButton(Actor actor, int times){
        for(int i=0;i<times;i++)
        {
            actor.attemptsTo(JavaScriptClick.on(MenuTargets.MEAL3_PLUS_BUTTON));
        }
    }

    @Then("{actor} user should see that the value of input for Meal3 is {string}")
    public void customerShouldSeeValueForQuantity(Actor actor, String expectedQuantity){
        actor.should(
                seeThat(MenuQuestions.Meal3QuantityFieldValue(),equalTo(expectedQuantity))
        );
    }

    @And("{actor} should see that the value of quantity field for Meal3 is set to {string}")
    public void customerShouldSeeSetUpValue(Actor actor,String quantity){
        for (int i=1;i<Integer.parseInt(quantity);i++)
        {
            actor.attemptsTo(
                    JavaScriptClick.on(MenuTargets.MEAL3_PLUS_BUTTON)
            );
        }
        actor.should(
                seeThat(MenuQuestions.Meal3QuantityFieldValue(),equalTo(quantity))
        );
    }

    @When("{actor} user clicks {int} times on minus button for Meal3")
    public void customerClicksOnMinusButton(Actor actor, int times){
        for(int i=0;i<times;i++)
        {
            actor.attemptsTo(JavaScriptClick.on(MenuTargets.MEAL3_MINUS_BUTTON));
        }
    }

    @When("{actor} user clicks on Add from menu page for Meal3")
    public void customerClicksOnAddButton(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(MenuTargets.MEAL3_ADD_TO_SHOPPING_CART_BUTTON)
        );
    }


}
