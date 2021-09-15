package starter.stepdefinitions.shoppingCart;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementsLocated;
import starter.search.questions.MealQuestions;
import starter.search.questions.ShoppingCartQuestions;
import starter.search.targets.ShoppingCartTargets;

import java.util.Arrays;
import java.util.List;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class ShoppingCartStepDefinition {

    @Then ("{actor} user should see table with {int} ordered items")
    public void customerShouldSeeTableWithNumberOfRowsOfOrderedItems(Actor actor, int number){
        actor.attemptsTo(
                Ensure.thatTheSetOf(ElementsLocated.by(ShoppingCartTargets.ITEM_IN_SHOPPING_CART_ROW)).hasSize(number)
        );
    }

    @Then ("{actor} user should see above data about ordered items")
    public void customerShouldSeeDataInShoppingCart(Actor actor){
        List<String> expectedMealName= Arrays.asList("Meal6","Meal8","Meal2","Meal7","Meal3");

        List<String> expectedPriceForMeal=Arrays.asList("50 $","27 $","10 $","20 $","32 $");

        List<String> expectedQuantity=Arrays.asList("4","5","5","3","4");

        List<String> expectedSubtotal=Arrays.asList("200 $","135 $","50 $","60 $","128 $");

        String expectedTotal="573";

        actor.should(
                seeThat(ShoppingCartQuestions.mealNamesInShoppingCart(),equalTo(expectedMealName)),
                seeThat(ShoppingCartQuestions.mealPriceInShoppingCart(),equalTo(expectedPriceForMeal)),
                seeThat(ShoppingCartQuestions.mealQuantityInShoppingCart(),equalTo(expectedQuantity)),
                seeThat(ShoppingCartQuestions.mealSubtotalInShoppingCart(),equalTo(expectedSubtotal)),
                seeThat(ShoppingCartQuestions.orderTotal(),equalTo(expectedTotal))
        );
    }

    @When ("{actor} user clicks on plus button for Meal8, order item id 8")
    public void customerClicksOnPlusButtonForMeal8(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(ShoppingCartTargets.MEAL8_PLUS_BUTTON_IN_SHOPPING_CART)
        );
    }

    @Then ("{actor} user should see row with meal name {string} quantity {string} subtotal {string} and total {string}")
    public void customerShouldSeeRow(Actor actor, String name, String quantity, String subtotal,String total) throws InterruptedException {
        Thread.sleep(4000);
        actor.should(
                seeThat(ShoppingCartQuestions.mealInShoppingCartName(name),equalTo(name)),
                seeThat(ShoppingCartQuestions.mealInShoppingCartQuantity(name),equalTo(quantity)),
                seeThat(ShoppingCartQuestions.mealInShoppingCartSubtotal(name),equalTo(subtotal)),
                seeThat(ShoppingCartQuestions.orderTotal(),equalTo(total))
        );
    }

    @When ("{actor} user clicks on minus button for Meal7, order item id 15")
    public void customerClicksOnMinusButtonForMeal7(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(ShoppingCartTargets.MEAL7_MINUS_BUTTON_IN_SHOPPING_CART)
        );
    }

    @When ("{actor} user clicks on delete button for Meal6, order item id 7")
    public void customerClicksOnDeleteButtonForMeal6(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(ShoppingCartTargets.MEAL6_DELETE_BUTTON)
        );
    }

    @Then("{actor} user should see that total is {string}")
    public void customerChecksTotal(Actor actor, String total){
        actor.should(
                seeThat(ShoppingCartQuestions.orderTotal(),equalTo(total))
        );
    }
    @When("{actor} user clicks on continue shopping button")
    public void customerClicksOnContinueShopping(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(ShoppingCartTargets.CONTINUE_SHOPPING_BUTTON)
        );
    }
}
