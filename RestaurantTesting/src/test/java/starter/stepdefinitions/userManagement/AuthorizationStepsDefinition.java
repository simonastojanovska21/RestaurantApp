package starter.stepdefinitions.userManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.ensure.Ensure;
import starter.navigation.NavigateTo;
import starter.search.HeaderButtons;
import starter.search.actions.PerformActionsForUserManagement;
import starter.stepdefinitions.ParameterDefinitions;

public class AuthorizationStepsDefinition {


    @Given("We have a user that is not logged in")
    public void openHomePage()
    {
        NavigateTo.theRestaurantHomePage();
    }

    @Given("{actor} user is logged in")
    public void deliveryUserIsLoggedIn(Actor actor)
    {
        actor.attemptsTo(NavigateTo.theLoginPage());
        if(actor.getName().equals("Delivery"))
        {
            actor.attemptsTo(PerformActionsForUserManagement.loginUser("delivery@test.com","P@ssword"));
        }
        if(actor.getName().equals("Employee"))
        {
            actor.attemptsTo(PerformActionsForUserManagement.loginUser("employee@test.com","P@ssword"));
        }
        if(actor.getName().equals("Customer"))
        {
            actor.attemptsTo(PerformActionsForUserManagement.loginUser("customer1@test.com","P@ssword"));
        }
        if(actor.getName().equals("Admin"))
        {
            actor.attemptsTo(PerformActionsForUserManagement.loginUser("admin@admin.com","P@ssword"));
        }
    }

    @When("He tries to open {string}")
    public void openUrlLocation(String url)
    {
        Open.url(ParameterDefinitions.DEFAULT_URL +url);
    }

    @When("{actor} opens home page")
    public void UnauthorizedUserOpensHomePage(Actor actor){
        actor.attemptsTo(NavigateTo.theRestaurantHomePage());
    }

    @When("{actor} tries to access {string}")
    public void openUrls(Actor actor,String url) {
        actor.attemptsTo(Open.url(ParameterDefinitions.DEFAULT_URL +url));
    }

    @Then("Information about unauthorized access should be shown")
    public void unauthorizedAccessShouldBeShown()
    {
        Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/unauthorized");
    }


    @Then("Shopping cart,deliveries,processingOrders,profile,logout buttons should not be displayed")
    public void UnauthorizedUserButtons(){
        Ensure.that(HeaderButtons.SHOPPING_CART_BUTTON).isNotDisplayed();
        Ensure.that(HeaderButtons.DELIVERIES_BUTTON).isNotDisplayed();
        Ensure.that(HeaderButtons.PROCESSING_ORDERS_BUTTON).isNotDisplayed();
        Ensure.that(HeaderButtons.PROFILE_BUTTON).isNotDisplayed();
        Ensure.that(HeaderButtons.LOGOUT_BUTTON).isNotDisplayed();
    }

    @Then("Processing orders button should not be displayed")
    public void deliveryUserShownButtons()
    {
        Ensure.that(HeaderButtons.PROCESSING_ORDERS_BUTTON).isNotDisplayed();
    }

    @Then("Deliveries button should not be displayed")
    public void employeeUserShownButtons(){
        Ensure.that(HeaderButtons.DELIVERIES_BUTTON).isNotDisplayed();
    }

    @Then("Deliveries,processingOrders buttons should not be displayed")
    public void customerUserShownButtons(){
        Ensure.that(HeaderButtons.DELIVERIES_BUTTON).isNotDisplayed();
        Ensure.that(HeaderButtons.PROCESSING_ORDERS_BUTTON).isNotDisplayed();
    }
}
