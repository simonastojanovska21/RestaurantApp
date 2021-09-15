package starter.stepdefinitions.userManagement;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementLocated;
import starter.navigation.NavigateTo;
import starter.search.HeaderButtons;
import starter.search.actions.PerformActionsForUserManagement;


public class LoginUserStepsDefinition {

    @Given("{actor} wants to login to web application")
    public void userWantsToLogin(Actor actor) {
        actor.wasAbleTo(NavigateTo.theLoginPage());
    }

    @When("{actor} enters {string} and {string}")
    public void loginUser(Actor actor,String username, String password)
    {
        actor.attemptsTo(PerformActionsForUserManagement.loginUser(username,password));
    }

    @When("{actor} clicks logout button")
    public void logOutButtonClick(Actor actor)
    {
        actor.attemptsTo(Click.on(HeaderButtons.LOGOUT_BUTTON));
    }

    @Then("{actor} should be redirected to home page")
    public void successfulLogin(Actor actor)
    {
        Ensure.that(ElementLocated.by("#profileButton")).isDisplayed();
    }

    @Then("{actor} should see empty username message")
    public void emptyUsername(Actor actor)
    {
        Ensure.that(ElementLocated.by("#noUsername")).isDisplayed();
    }

    @Then("{actor} should see empty password message")
    public void emptyPassword(Actor actor)
    {
        Ensure.that(ElementLocated.by("#noPassword")).isDisplayed();
    }

    @Then("{actor} should see message about invalid credentials")
    public void invalidCredentials(Actor actor)
    {
        Ensure.that(ElementLocated.by("#invalidData")).isDisplayed();
    }

}