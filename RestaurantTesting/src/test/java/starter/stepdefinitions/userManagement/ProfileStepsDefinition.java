package starter.stepdefinitions.userManagement;

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
import starter.search.HeaderButtons;
import starter.search.actions.PerformProfileActions;
import starter.search.questions.ProfileQuestions;
import starter.search.targets.ProfileTargets;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class ProfileStepsDefinition {

    @When("{actor} user clicks on profile button")
    public void userClicksOnProfileButton(Actor actor){
        actor.attemptsTo(
                Click.on(HeaderButtons.PROFILE_BUTTON)
        );
    }

    @Then("{actor} user should be redirected to profile page")
    public void userIsRedirectedToProfilePage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/profile")
        );
    }

    @When("{actor} user is on profile page")
    public void userIsOnProfilePage(Actor actor){
        actor.attemptsTo(
                NavigateTo.toProfilePage()
        );
    }

    @Then("{actor} user should see mealCategories and ingredients buttons")
    public void adminShouldSeeButtons(Actor actor){
        actor.attemptsTo(
                Ensure.that(ElementLocated.by(ProfileTargets.MEAL_CATEGORIES_BUTTON)).isDisplayed(),
                Ensure.that(ElementLocated.by(ProfileTargets.INGREDIENTS_BUTTON)).isDisplayed()
        );
    }

    @Then("{actor} user should not see mealCategories and ingredients buttons")
    public void nonAdminUsersShouldNotSeeButtons(Actor actor){
        actor.attemptsTo(
                Ensure.that(ElementLocated.by(ProfileTargets.MEAL_CATEGORIES_BUTTON)).isNotDisplayed(),
                Ensure.that(ElementLocated.by(ProfileTargets.INGREDIENTS_BUTTON)).isNotDisplayed()
        );
    }

    @Then("{actor} user should see his private information, name, surname,address, telephone number")
    public void customerShouldSeeHisPrivateInformation(Actor actor){
        String name="Customer2 name";
        String surname="Customer2 surname";
        String address="Customer2 address";
        String phoneNumber="070123465";

        actor.should(
                seeThat(ProfileQuestions.getCustomer2UserName(),equalTo(name)),
                seeThat(ProfileQuestions.getCustomer2UserSurname(),equalTo(surname)),
                seeThat(ProfileQuestions.getCustomer2Address(),equalTo(address)),
                seeThat(ProfileQuestions.getCustomer2PhoneNumber(),equalTo(phoneNumber))
        );
    }

    @When("{actor} user clicks on 5 star button and writes {string} in the description field")
    public void customerSelectsStarsForReview(Actor actor, String description){
        actor.attemptsTo(
                //JavaScriptClick.on(ProfileTargets.FIVE_STAR_REVIEW)
                PerformProfileActions.leaveAReview(description)
        );
        Serenity.takeScreenshot();
    }

    @Then("{actor} user should see information about successful leaving a review")
    public void customerShouldSeeInfoAboutLeavingReview(Actor actor){

        actor.attemptsTo(
                Ensure.that(ElementLocated.by(ProfileTargets.USER_HAS_LEFT_REVIEW)).isDisplayed()
        );
    }

}
