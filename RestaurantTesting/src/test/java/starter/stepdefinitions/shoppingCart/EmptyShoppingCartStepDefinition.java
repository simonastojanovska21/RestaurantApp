package starter.stepdefinitions.shoppingCart;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementLocated;
import starter.navigation.NavigateTo;
import starter.search.HeaderButtons;
import starter.search.actions.PerformActionsForUserManagement;
import starter.search.questions.ShoppingCartQuestions;
import starter.search.targets.MenuTargets;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class EmptyShoppingCartStepDefinition {

    @Given ("{actor} user, ROLE_CUSTOMER1, tries to login")
    public void customer1IsLoggedIN(Actor actor){
        actor.attemptsTo(NavigateTo.theLoginPage());
        actor.attemptsTo(PerformActionsForUserManagement.loginUser("customer1@test.com","P@ssword"));
        actor.attemptsTo(
                Ensure.that(ElementLocated.by(HeaderButtons.PROFILE_BUTTON)).isDisplayed()
        );
    }

    @When ("{actor} user clicks on shopping cart button")
    public void customerClicksOnShoppingCartButton(Actor actor){
        actor.attemptsTo(
                Click.on(HeaderButtons.SHOPPING_CART_BUTTON)
        );
    }

    @Then ("{actor} user should be redirected to shopping cart page")
    public void customerShouldBeRedirectedToShoppingCartPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/shoppingCart")
        );
    }

    @When ("{actor} user is on shopping cart page")
    public void customerIsOnShoppingCartPage(Actor actor){
        actor.attemptsTo(
                NavigateTo.theShoppingCartPage()
        );
    }

    @Then ("{actor} user should see information about empty shopping cart")
    public void customerShouldSeeInfoAboutEmptyShoppingCart(Actor actor){
        actor.attemptsTo(
                Ensure.that(ElementLocated.by("#emptyShoppingCart")).isDisplayed()
        );
    }

    @Then ("{actor} user should see back to menu button, but not checkout button")
    public void customerShouldSeeBackToMenuButNotCheckOutButton(Actor actor){
        actor.attemptsTo(
                Ensure.that(ElementLocated.by("#backToMenu")).isDisplayed(),
                Ensure.that(ElementLocated.by("#checkOut")).isNotDisplayed()
        );
    }

    @When ("{actor} user clicks on button for back to menu")
    public void customerClicksOnBackToMenuButton(Actor actor){
        actor.attemptsTo(
                Click.on(ElementLocated.by("#backToMenu"))
        );
    }

    @When ("{actor} user clicks on Add from menu page for Meal4")
    public void customerClickOnAddButton(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(MenuTargets.MEAL4_ADD_TO_SHOPPING_CART_BUTTON)
        );
    }

    @When ("{actor} user clicks on checkout button")
    public void customerClicksOnCheckoutButton(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(ElementLocated.by("#checkOut"))
        );
    }

    @Then ("{actor} user should be redirected to checkout page")
    public void customerIsRedirectedToCheckoutPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/checkOut")
        );
    }

    @When ("{actor} user is on checkout page")
    public void customerIsOnCheckOutPage(Actor actor){
        actor.attemptsTo(
                NavigateTo.toCheckOutPage()
        );
    }

    @Then ("{actor} user should see total {string} delivery fee {string} and total with delivery {string}")
    public void customerShouldSeeDataAboutOrder(Actor actor,String total,String deliveryFee, String totalWithDelivery){
        actor.should(
                seeThat(ShoppingCartQuestions.totalPriceForOrder(),equalTo(total)),
                seeThat(ShoppingCartQuestions.deliveryFee(),equalTo(deliveryFee)),
                seeThat(ShoppingCartQuestions.totalWithDelivery(),equalTo(totalWithDelivery))
        );
    }

    @When ("{actor} user clicks on cancel order button")
    public void customerClicksOnCancelOrder(Actor actor){
        actor.attemptsTo(
                JavaScriptClick.on(ElementLocated.by("#cancelOrder"))
        );
    }
}
