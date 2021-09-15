package starter.stepdefinitions.userManagement;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.ensure.Ensure;
import starter.navigation.NavigateTo;
import starter.search.actions.PerformActionsForUserManagement;
import starter.search.targets.RegisterTargets;

public class RegisterStepsDefinition {

    @Given("{actor} wants to register to web app")
    public void userWantsToRegister(Actor actor) {
        actor.wasAbleTo(NavigateTo.toRegisterPage());
    }

    @When("{actor} enters {string} {string} {string} {string} {string} {string} {string}")
    public void userEntersData(Actor actor,String username,String password,String repeatedPassword,
                               String name, String surname,String phone,String address) {
        actor.attemptsTo(PerformActionsForUserManagement.registerUser(username,password,repeatedPassword,name,surname,phone,address));
    }

    @Then("{actor} should be redirected to login page")
    public void redirectToLoginPage(Actor actor) throws InterruptedException {
        Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/login");
    }

    @Then("{actor} should see message that passwords do not match")
    public void displayPasswordsDoNotMatch(Actor actor) {
        actor.attemptsTo(Ensure.that(RegisterTargets.PASSWORDS_DO_NOT_MATCH).isDisplayed());
    }

    @Then("{actor} should see message that username already exists")
    public void displayUsernameAlreadyExists(Actor actor) {
        actor.attemptsTo(Ensure.that(RegisterTargets.USERNAME_ALREADY_EXISTS).isDisplayed());
    }

    @Then("{actor} should see empty email message")
    public void displayEmptyEmail(Actor actor) {
        actor.attemptsTo(Ensure.that(RegisterTargets.EMPTY_EMAIL).isDisplayed());
    }

    @Then("{actor} should see empty password1 message")
    public void displayEmptyPassword1(Actor actor) {
        actor.attemptsTo(Ensure.that(RegisterTargets.EMPTY_PASSWORD1).isDisplayed());
    }

    @Then("{actor} should see empty repeatedPassword message")
    public void displayEmptyRepeatedPassword(Actor actor) {
        actor.attemptsTo(Ensure.that(RegisterTargets.EMPTY_REPEATED_PASSWORD).isDisplayed());
    }

    @Then("{actor} should see empty name message")
    public void displayEmptyName(Actor actor) {
        actor.attemptsTo(Ensure.that(RegisterTargets.EMPTY_NAME).isDisplayed());
    }

    @Then("{actor} should see empty surname message")
    public void displayEmptySurname(Actor actor) {
        actor.attemptsTo(Ensure.that(RegisterTargets.EMPTY_SURNAME).isDisplayed());
    }

    @Then("{actor} should see empty phoneNumber message")
    public void displayEmptyPhoneNumber(Actor actor) {
        actor.attemptsTo(Ensure.that(RegisterTargets.EMPTY_PHONE_NUMBER).isDisplayed());
    }

    @Then("{actor} should see empty address message")
    public void displayEmptyAddress(Actor actor) {
        actor.attemptsTo(Ensure.that(RegisterTargets.EMPTY_ADDRESS).isDisplayed());
    }
}
