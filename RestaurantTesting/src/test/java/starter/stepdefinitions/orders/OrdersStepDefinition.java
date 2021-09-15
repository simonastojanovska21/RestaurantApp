package starter.stepdefinitions.orders;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.ensure.web.ElementLocated;
import net.serenitybdd.screenplay.ensure.web.ElementsLocated;
import starter.navigation.NavigateTo;
import starter.search.HeaderButtons;
import starter.search.actions.PerformActionsForUserManagement;
import starter.search.questions.DeliveryQuestions;
import starter.search.questions.OrderQuestions;
import starter.search.targets.OrderTargets;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.equalTo;

public class OrdersStepDefinition {

    @Given("{actor} user, ROLE_EMPLOYEE, tries to login")
    public void employeeUserTriesToLogin(Actor actor){
        actor.attemptsTo(NavigateTo.theLoginPage());
        actor.attemptsTo(PerformActionsForUserManagement.loginUser("employee@test.com","P@ssword"));
        actor.attemptsTo(
                Ensure.that(ElementLocated.by(HeaderButtons.PROCESSING_ORDERS_BUTTON)).isDisplayed()
        );
    }

    @When("{actor} user clicks on Processing orders button")
    public void employeeUserClicksOnProcessingOrders(Actor actor){
        actor.attemptsTo(
                Click.on(HeaderButtons.PROCESSING_ORDERS_BUTTON)
        );
    }

    @Then("{actor} user should be redirected to processing orders page")
    public void employeeUserShouldBeRedirectedToProcessingOrdersPage(Actor actor){
        actor.attemptsTo(
                Ensure.thatTheCurrentPage().currentUrl().isEqualTo("http://localhost:3000/orders/processing")
        );
    }

    @When("{actor} user is on processing orders page")
    public void employeeUserIsOnProcessingOrdersPage(Actor actor){
        actor.attemptsTo(
                NavigateTo.toProcessingOrdersPage()
        );
    }

    @Then("{actor} user sees {int} rows in the processing order table")
    public void employeeSeesNumberOfRows(Actor actor, int number){
        actor.attemptsTo(
                Ensure.thatTheSetOf(ElementsLocated.by(OrderTargets.ORDER_ROW)).hasSize(number)
        );
    }

    @Then("{actor} user should see above rows in the processing order table")
    public void employeeViewsProcessingOrderData(Actor actor){
        List<String> expectedCustomerNames= Arrays.asList("admin@admin.com","customer2@test.com","customer1@test.com");
        actor.should(
            seeThat(OrderQuestions.displayedCustomerName(),equalTo(expectedCustomerNames))
        );

        List<String> expectedMeal=Arrays.asList("Meal2 x 5","Meal4 x 4","Meal3 x 1","Meal9 x 6","Meal9 x 2");
        actor.should(
            seeThat(OrderQuestions.displayedMeal(),equalTo(expectedMeal))
        );

        List<String> expectedDeliveryAddress=Arrays.asList("Admin address","Customer2 address","Customer1 address");
        actor.should(
            seeThat(OrderQuestions.displayedAddressForDelivery(),equalTo(expectedDeliveryAddress))
        );

        actor.attemptsTo(Ensure.thatTheSetOf(ElementsLocated.by(OrderTargets.READY_FOR_DELIVERY_BUTTON_COLUMN)).hasSize(3));

    }

    @When("{actor} user clicks on Ready for delivery button for order with id 8")
    public void employeeClicksOnReadyForDelivery(Actor actor){
        actor.attemptsTo(
                Click.on(ElementLocated.by(".readyForDelivery-8"))
        );
    }

    @Then("{actor} user should see that the order with id 8 was removed from the table")
    public void employeeShouldNotSeeInfoAboutRemovedRow(Actor actor) throws InterruptedException {
        Thread.sleep(2000);
        List<String> expectedCustomerNames= Arrays.asList("admin@admin.com","customer2@test.com");
        actor.should(
                seeThat(OrderQuestions.displayedCustomerName(),equalTo(expectedCustomerNames))
        );

        List<String> expectedMeal=Arrays.asList("Meal2 x 5","Meal4 x 4","Meal3 x 1","Meal9 x 6");
        actor.should(
                seeThat(OrderQuestions.displayedMeal(),equalTo(expectedMeal))
        );

        List<String> expectedDeliveryAddress=Arrays.asList("Admin address","Customer2 address");
        actor.should(
                seeThat(OrderQuestions.displayedAddressForDelivery(),equalTo(expectedDeliveryAddress))
        );

        actor.attemptsTo(Ensure.thatTheSetOf(ElementsLocated.by(OrderTargets.READY_FOR_DELIVERY_BUTTON_COLUMN)).hasSize(2));
    }

}
