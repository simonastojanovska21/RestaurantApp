package starter.stepdefinitions.delivery;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementLocated;
import net.serenitybdd.screenplay.ensure.web.ElementsLocated;
import starter.navigation.NavigateTo;
import starter.search.HeaderButtons;
import starter.search.actions.PerformActionsForUserManagement;
import starter.search.targets.DeliveryTargets;
import starter.search.questions.DeliveryQuestions;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class DeliveriesStepDefinition {

    @Given("{actor} user tries to log in")
    public void checkIfLoggedIn(Actor actor)
    {
        actor.attemptsTo(NavigateTo.theLoginPage());
        actor.attemptsTo(PerformActionsForUserManagement.loginUser("delivery@test.com","P@ssword"));
        actor.attemptsTo(
                Ensure.that(ElementLocated.by(HeaderButtons.DELIVERIES_BUTTON)).isDisplayed()
        );
    }

    @When("{actor} clicks on Deliveries button")
    public void deliveryClicksOnDeliveryButton(Actor actor) {
        actor.attemptsTo(Click.on(HeaderButtons.DELIVERIES_BUTTON));
    }

    @When("{actor} user is on deliveries page")
    public void deliveryUserOnDeliveryPage(Actor actor)
    {
        actor.attemptsTo(NavigateTo.toDeliveriesPage());
    }

    @When("{actor} user clicks on Delivered button for delivery with id 2")
    public void clickOnDeliveryButton(Actor actor){
        //actor.attemptsTo(Ensure.that(ElementLocated.by(".delivered-2")).isDisplayed());
        actor.attemptsTo(
                Click.on(ElementLocated.by(".delivered-2"))
        );
    }

    @Then("Deliveries page should be displayed")
    public void deliveriesPageIsDisplayed() {
        Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/deliveries");
    }

    @Then("{actor} sees {int} deliveries in the table")
    public void countDeliveryRowsInTable(Actor actor, int number)
    {
        actor.attemptsTo(
                Ensure.thatTheSetOf(ElementsLocated.by(DeliveryTargets.DELIVERIES_ROW)).hasSize(number)
        );
    }

    @Then("{actor} user should see above data")
    public void checkIfDataIsDisplayed(Actor actor)
    {
        List<String> expectedIds= Arrays.asList("1","2");
        actor.should(seeThat(DeliveryQuestions.displayedIds(), equalTo(expectedIds)));

        List<String> expectedAddresses=Arrays.asList("Admin address","Customer2 address");
        actor.should(seeThat(DeliveryQuestions.displayedAddresses(),equalTo(expectedAddresses)));

        List<String> expectedTimeForDelivery=Arrays.asList("2021-11-02T15:30:25", "2021-10-02T17:30:25");
        actor.should(seeThat(DeliveryQuestions.displayedTimeForDelivery(),equalTo(expectedTimeForDelivery)));

        actor.attemptsTo(Ensure.thatTheSetOf(ElementsLocated.by(DeliveryTargets.DELIVERY_BUTTON_COLUMN)).hasSize(2));
    }

    @Then("{actor} should see that delivery with id 2 should be removed from the table")
    public void removeDelivery(Actor actor){
        Serenity.takeScreenshot();
        List<String> expectedIds= Arrays.asList("1");
        actor.should(seeThat(DeliveryQuestions.displayedIds(), equalTo(expectedIds)));

        List<String> expectedAddresses=Arrays.asList("Admin address");
        actor.should(seeThat(DeliveryQuestions.displayedAddresses(),equalTo(expectedAddresses)));

        List<String> expectedTimeForDelivery=Arrays.asList("2021-11-02T15:30:25");
        actor.should(seeThat(DeliveryQuestions.displayedTimeForDelivery(),equalTo(expectedTimeForDelivery)));

        actor.attemptsTo(Ensure.thatTheSetOf(ElementsLocated.by(DeliveryTargets.DELIVERY_BUTTON_COLUMN)).hasSize(1));
        Serenity.takeScreenshot();
    }

}
