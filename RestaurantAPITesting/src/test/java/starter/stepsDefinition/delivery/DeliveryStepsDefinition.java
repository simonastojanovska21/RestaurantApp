package starter.stepsDefinition.delivery;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import starter.ExceptionMessages;
import starter.data.dto.DeliveryDto;
import starter.data.model.Delivery;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

import static org.assertj.core.api.Assertions.assertThat;

public class DeliveryStepsDefinition {

    @When("{admin} tries to get info about delivery with id {long}")
    public void adminGetsInfoForDelivery(Actor admin, Long deliveryId){
        admin.attemptsTo(
                Get.resource("/api/delivery/{id}").with(request->request.pathParam("id",deliveryId))
        );
    }

    @When("{admin} tries to get a list of all deliveries")
    public void userGetsListOfDeliveries(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/delivery/all")
        );
    }

    @When("{admin} tries to get a list of deliveries for day")
    public void userGetsListOfDeliveriesForDay(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/delivery/day/"+ LocalDateTime.of(2021,11,2,15,30,25))
        );
    }

    @When("{admin} attempts to finish delivery with id {long}")
    public void userAttemptsToFinishDeliver(Actor admin, Long id){
        admin.attemptsTo(
                Get.resource("/api/delivery/finish/{id}").with(request->request.pathParam("id",id))
        );
    }

    @When("{admin} enters {string} {string} for delivery")
    public void userCreatesDelivery(Actor admin, String address, String orderId){
        Long id=Long.parseLong(orderId);
        admin.attemptsTo(
                Post.to("/api/delivery/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body(new DeliveryDto(address,id))
                        )
        );
    }

    @When("{admin} attempts to create new delivery with missing data")
    public void userCreatesDeliveryWithMissingData(Actor admin){
        admin.attemptsTo(
                Post.to("/api/delivery/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{}")
                        ),
                Post.to("/api/delivery/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"address\":\"Customer1 address\"}")
                        ),
                Post.to("/api/delivery/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"orderId\":2}")
                        )
        );
    }

    @When("{admin} tries to get a list of deliveries for today")
    public void userGetsAListOfDeliveriesForToday(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/delivery/remaining")
        );
    }

    @Then("{admin} should see message that delivery is not found")
    public void userShouldSeeMessageDeliveryNotFound(Actor admin){
        admin.should(
                seeThatResponse("delivery with non existing id",
                        response->response.body("message", is(ExceptionMessages.DeliveryNotFoundException)))
        );
    }

    @Then("{admin} should see message about selected delivery")
    public void userSeesMessageAboutSelectedDelivery(Actor admin){
        admin.should(
                seeThatResponse("delivery with  existing id",
                        response->response.statusCode(200))
        );
        Delivery deliveryResponse= SerenityRest.lastResponse().as(Delivery.class);

        assertThat(deliveryResponse.getOrderForDelivery().getOrderedByUser()).isEqualTo(DeliveryObjects.deliveryDetailsUserExpected);
        assertThat(deliveryResponse.getOrderForDelivery()).isEqualTo(DeliveryObjects.deliveryDetailsOrderExpected);
        assertThat(deliveryResponse).isEqualTo(DeliveryObjects.detailsAboutDeliveryWithId1);
    }

    @Then("{admin} should see list of {int} deliveries")
    public void userSeesNumberOfDeliveries(Actor admin, int number){
        admin.should(
                seeThatResponse("list of deliveries for day",
                        response->response.statusCode(200))
        );

        List<Delivery> deliveries= Arrays.asList(SerenityRest.lastResponse().as(Delivery[].class));
        assertThat(deliveries).hasSize(number);
    }

    @Then("{admin} should see message about successfully finishing delivery")
    public void userSeesMessageAboutSuccessfullyFinishingDelivery(Actor admin){
        admin.should(
                seeThatResponse("delivery with  existing id",
                        response->response.statusCode(200))
        );
        Delivery finishedDelivery=SerenityRest.lastResponse().as(Delivery.class);
        assertThat(finishedDelivery.getOrderForDelivery()).isEqualTo(DeliveryObjects.orderForDeliveryAfterClickingOnFinish);
        assertThat(finishedDelivery.getOrderForDelivery().getOrderedByUser()).isEqualTo(DeliveryObjects.deliveryDetailsUserExpected);
        assertThat(finishedDelivery).isEqualTo(DeliveryObjects.deliveryAfterClickOnFinish);
    }

    @Then("{admin} should see message that order is not found")
    public void userSeesMessageThatOrderIsNotFound(Actor admin){
        admin.should(
                seeThatResponse("invalid orderId exception",
                        response->response.body("message",is(ExceptionMessages.OrderNotFoundException)))
        );
    }

    @Then("{admin} should see message about successful creating a delivery")
    public void userSeesMessageAboutSuccessfulCreatingDelivery(Actor admin){
        admin.should(
                seeThatResponse("delivery with  existing id",
                        response->response.statusCode(200))
        );
        Delivery createdDelivery=SerenityRest.lastResponse().as(Delivery.class);

        assertThat(createdDelivery.getOrderForDelivery()).isEqualTo(DeliveryObjects.orderAfterCreatingNewDelivery);
        assertThat(createdDelivery.getOrderForDelivery().getOrderedByUser()).isEqualTo(DeliveryObjects.createdDeliveryForUser);
    }
}
