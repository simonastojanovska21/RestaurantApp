package starter.stepsDefinition.orders;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import starter.ExceptionMessages;
import starter.data.dto.OrderItemDto;
import starter.data.model.Order;
import starter.data.model.OrderItem;
import starter.stepsDefinition.meals.MealsObjects;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderStepsDefinitions {

    @When("{admin} get a list of all orders")
    public void userTrieToGetListOfAllOrders(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/order/all")
        );
    }

    @When("{admin} get a list of all processing orders")
    public void userTrieToGetListOfAllProcessingOrders(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/order/processing")
        );
    }

    @When("{admin} get a list of all delivering orders")
    public void userTrieToGetListOfAllDeliveringOrders(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/order/delivering")
        );
    }

    @When("{admin} get a list of most ordered meals")
    public void userGetsListOfMostOrderedMeals(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/order/topOrdered")
        );
    }

    @When("{admin} tries to get list of orders for user with username {string}")
    public void userGetsListOfOrdersForUsername(Actor admin, String username){
        admin.attemptsTo(
                Get.resource("/api/order/user/{username}").with(request->request.pathParam("username",username))
        );
    }

    @When("{admin} tries to get active order for user with username {string}")
    public void userGetsActiveOrderForUsername(Actor admin, String username){
        admin.attemptsTo(
                Get.resource("/api/order/activeOrder/{username}").with(request->request.pathParam("username",username))
        );
    }

    @When("{admin} tries to pay for order with id {long}")
    public void userTriesToPayForOrder(Actor admin, Long orderId){
        admin.attemptsTo(
                Get.resource("/api/order/pay/{id}").with(request->request.pathParam("id",orderId))
        );
    }

    @When("{admin} tries to cancel order with username {string}")
    public void userTriesToCancelActiveOrderForUsername(Actor admin, String username){
        admin.attemptsTo(
                Get.resource("/api/order/cancel/{username}").with(request->request.pathParam("username",username))
        );
    }

    @When("{admin} tries to get details for order with id {long}")
    public void userTriesToGetDetailsForOrderWithId(Actor admin, Long orderId){
        admin.attemptsTo(
                Get.resource("/api/order/{id}").with(request->request.pathParam("id",orderId))
        );
    }
    @When("{admin} tries to add new order item with missing fields")
    public void userTriesToAddNewOrderItemWithMissingData(Actor admin){
        admin.attemptsTo(
                Post.to("/api/order/addOrderItem")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{}")
                        ),
                Post.to("/api/order/addOrderItem")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{   \"mealId\":0,\n" +
                                        "    \"username\":\"\"  }")
                        ),
                Post.to("/api/order/addOrderItem")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{   \"quantity\":0,\n" +
                                        "    \"username\":\"\"  }")
                        ),
                Post.to("/api/order/addOrderItem")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{   \"quantity\":0,\n" +
                                        "    \"mealId\":0  }")
                        )
        );
    }

    @When("{admin} enters {string} {string} {string} for order item")
    public void userTriesToAddNewOrderItem(Actor admin, String quantity, String mealId,String username){
        admin.attemptsTo(
                Post.to("/api/order/addOrderItem")
                        .with(request->request.header("Content-Type", "application/json")
                                .body(new OrderItemDto(Integer.parseInt(quantity),Long.parseLong(mealId),username))
                        )
        );
    }

    @When("{admin} tries to add order items quantity for order item with id {long}")
    public void userTriesToPlusOrderItemQuantity(Actor admin, Long orderItemId){
        admin.attemptsTo(
                Get.resource("/api/order/plusQuantity/{id}").with(request->request.pathParam("id",orderItemId))
        );
    }

    @When("{admin} tries to minus order items quantity for order item with id {long}")
    public void userTriesToMinusOrderItemQuantity(Actor admin, Long orderItemId){
        admin.attemptsTo(
                Get.resource("/api/order/minusQuantity/{id}").with(request->request.pathParam("id",orderItemId))
        );
    }

    @When("{admin} tries to delete order item with id {long}")
    public void userTriesToDeleteOrderItemFromOrder(Actor admin, Long orderItemId){
        admin.attemptsTo(
                Delete.from("/api/order/delete/{id}").with(request->request.pathParam("id",orderItemId))
        );
    }

    @When("{admin} gets order items for order with id {long}")
    public void userGetsOrderItemsForOrder(Actor admin, Long orderId){
        admin.attemptsTo(
                Get.resource("/api/order/orderItems/{id}").with(request->request.pathParam("id",orderId))
        );
    }

    @When("{admin} gets order items for user active order with {string}")
    public void userGetsOrderItemsForUserActiveOrder(Actor admin, String username){
        admin.attemptsTo(
                Get.resource("/api/order/orderItems/user/{username}").with(request->request.pathParam("username",username))
        );
    }

    @Then("{admin} should see list of {int} orders")
    public void userShouldSeeListOfNumberOrders (Actor admin, int number){
        admin.should(
                seeThatResponse("list of orders",
                        response->response.statusCode(200))
        );

        List<Order> orderList= Arrays.asList(SerenityRest.lastResponse().as(Order[].class));
        assertThat(orderList).hasSize(number);
    }
    @Then("{admin} should see message about user active order")
    public void userSeesMessageAboutUserActiveOrder (Actor admin){
        admin.should(
                seeThatResponse("user active order",
                        response->response.statusCode(200))
        );

        Order activeOrder=SerenityRest.lastResponse().as(Order.class);
        assertThat(activeOrder).isEqualTo(OrdersObject.AdminActiveOrder);
    }
    @Then("{admin} should see message about successfully paying for order")
    public void userSeesMessageAboutSuccessfullyPayingForOrder (Actor admin){
        admin.should(
                seeThatResponse("pay for order",
                        response->response.statusCode(200))
        );

        Order payForOrder=SerenityRest.lastResponse().as(Order.class);
        assertThat(payForOrder).isEqualTo(OrdersObject.PayForOrderWithId1);
    }
    @Then("{admin} should see message about successfully cancelling order")
    public void userSeesMessageAboutSuccessfullyCancelingOrder (Actor admin){
        admin.should(
                seeThatResponse("cancel order",
                        response->response.statusCode(200))
        );

        Order cancelOrder=SerenityRest.lastResponse().as(Order.class);
        assertThat(cancelOrder).isEqualTo(OrdersObject.CancelOrderForCustomer2);
    }
    @Then("{admin} should see message about selected order")
    public void userSeesMessageAboutSelectedOrder (Actor admin){
        admin.should(
                seeThatResponse("details for order",
                        response->response.statusCode(200))
        );

        Order detailsForOrder=SerenityRest.lastResponse().as(Order.class);
        assertThat(detailsForOrder).isEqualTo(OrdersObject.detailsForOrderWithId6);
    }
    @Then("{admin} should see message about successfully adding order item")
    public void userSeesMessageAboutSuccessfullyAddingOrderItem (Actor admin){
        admin.should(
                seeThatResponse("successfully adding order item",
                        response->response.statusCode(200))
        );

        OrderItem orderItem=SerenityRest.lastResponse().as(OrderItem.class);
        assertThat(orderItem.getQuantity()).isEqualTo(OrdersObject.addedOrderItemQuantity);
        assertThat(orderItem.getOrderItemForMeal().getId()).isEqualTo(OrdersObject.addedOrderItemMealId);
        assertThat(orderItem.getItemInOrder().getOrderedByUser().getUsername()).isEqualTo(OrdersObject.addedOrderItemUsername);
    }
    @Then("{admin} should see message that order item is not found")
    public void userSeesMessageThatOrderItemIsNotFound (Actor admin){
        admin.should(
                seeThatResponse("order item with non existing id",
                        response->response.body("message", is(ExceptionMessages.OrderItemNotFoundException)))
        );
    }
    @Then("{admin} should see message about successfully adding order item quantity")
    public void userSeesMessageAboutSuccessfullyAddingQuantity (Actor admin){
        admin.should(
                seeThatResponse("successfully adding order item quantity",
                        response->response.statusCode(200))
        );

        OrderItem orderItem=SerenityRest.lastResponse().as(OrderItem.class);
        assertThat(orderItem).isEqualTo(OrdersObject.OrderItemWithId2Plus);
        assertThat(orderItem.getItemInOrder()).isEqualTo(OrdersObject.OrderWithId1);
        assertThat(orderItem.getOrderItemForMeal()).isEqualTo(OrdersObject.mealWithId5);
    }
    @Then("{admin} should see message about successfully minus order item quantity")
    public void userSeesMessageAboutSuccessfullyMinusQuantity (Actor admin){
        admin.should(
                seeThatResponse("successfully minus order item quantity",
                        response->response.statusCode(200))
        );

        OrderItem orderItem=SerenityRest.lastResponse().as(OrderItem.class);
        assertThat(orderItem).isEqualTo(OrdersObject.OrderItemWithId1Minus);
        assertThat(orderItem.getItemInOrder()).isEqualTo(OrdersObject.OrderWithId1);
        assertThat(orderItem.getOrderItemForMeal()).isEqualTo(MealsObjects.mealWithId3);
    }
    @Then("{admin} should see message about successfully delete order item with id")
    public void userSeesMessageAboutSuccessfullyDeletingOrderItem (Actor admin){
        admin.should(
                seeThatResponse("successfully delete order item",
                        response->response.statusCode(200))
        );
    }

    @Then("{admin} should see list of {int} order items")
    public void userSeesListOfNumberOrderItems(Actor admin, int number){
        admin.should(
                seeThatResponse("list of order items",
                        response->response.statusCode(200))
        );

        List<OrderItem> orderItemList= Arrays.asList(SerenityRest.lastResponse().as(OrderItem[].class));
        assertThat(orderItemList).hasSize(number);
    }
}
