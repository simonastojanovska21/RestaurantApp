package starter.stepsDefinition.ingredients;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import starter.ExceptionMessages;
import starter.data.dto.IngredientDto;
import starter.data.model.Ingredient;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

import static org.assertj.core.api.Assertions.assertThat;

public class IngredientStepsDefinitions {

    @When("{admin} tries to get a list of all ingredients")
    public void userTriesToGetListOfAllIngredients(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/ingredients")
        );
    }
    @When("{admin} tries to get info about ingredient with id {long}")
    public void userGetsInfoAboutIngredient(Actor admin, Long ingredientId){
        admin.attemptsTo(
                Get.resource("/api/ingredients/{id}").with(request->request.pathParam("id",ingredientId))
        );
    }
    @When("{admin} attempts to create new ingredient with missing data")
    public void userTriesToCreateNewIngredientWithMissingData(Actor admin){
        admin.attemptsTo(
                Post.to("/api/ingredients/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{}")
                        ),
                Post.to("/api/ingredients/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{ \"name\":\"\" }")
                        ),
                Post.to("/api/ingredients/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"quantity\":0}")
                        )
        );
    }

    @When("{admin} enters {string} {string} for ingredient")
    public void userCreatedNewIngredient(Actor admin, String name, String quantity){
        admin.attemptsTo(
                Post.to("/api/ingredients/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body(new IngredientDto(name,Integer.parseInt(quantity)))
                        )
        );
    }

    @When("{admin} tries to edit ingredient with id {long}")
    public void userTriesToEditIngredientWithId(Actor admin, Long ingredientId){
        admin.attemptsTo(
                Put.to("/api/ingredients/edit/{id}")
                        .with(request->request.pathParam("id",ingredientId)
                                .header("Content-Type", "application/json")
                                .body(IngredientObjects.ingredientWithId26)
                        )
        );
    }

    @When("{admin} tries to edit ingredient with id {long} with missing data")
    public void userTriesToEditIngredientWithIdWithMissingData(Actor admin, Long ingredientId){
        admin.attemptsTo(
                Put.to("/api/ingredients/edit/{id}")
                        .with(request->request.pathParam("id",ingredientId)
                                .header("Content-Type", "application/json")
                                .body("{}")
                        ),
                Put.to("/api/ingredients/edit/{id}")
                        .with(request->request.pathParam("id",ingredientId)
                                .header("Content-Type", "application/json")
                                .body("{ \"name\":\"\" }")
                        ),
                Put.to("/api/ingredients/edit/{id}")
                        .with(request->request.pathParam("id",ingredientId)
                                .header("Content-Type", "application/json")
                                .body("{\"quantity\":0}")
                        )
        );
    }

    @When("{admin} tries to edit ingredient with id {long} and enters {string} {string}")
    public void userTriesToEditIngredientWithIdAndEmptyField(Actor admin, Long ingredientId, String name, String quantity){
        admin.attemptsTo(
                Put.to("/api/ingredients/edit/{id}")
                        .with(request->request.pathParam("id",ingredientId)
                                .header("Content-Type", "application/json")
                                .body(new IngredientDto(name,Integer.parseInt(quantity)))
                        )
        );
    }

    @When("{admin} tries to delete ingredient with id {long}")
    public void userTriesToDeleteIngredient(Actor admin, Long ingredientId){
        admin.attemptsTo(
                Delete.from("/api/ingredients/delete/{id}").with(request->request.pathParam("id",ingredientId))
        );
    }

    @Then("{admin} should see list of {int} ingredients")
    public void userSeesAListWithNumberOfIngredients(Actor admin, int number){
        admin.should(
                seeThatResponse("list of ingredients",
                        response->response.statusCode(200))
        );

        List<Ingredient> ingredientList= Arrays.asList(SerenityRest.lastResponse().as(Ingredient[].class));
        assertThat(ingredientList).hasSize(number);
    }

    @Then("{admin} should see message that ingredient is not found")
    public void userSeesMessageThatIngredientIsNotFound(Actor admin){
        admin.should(
                seeThatResponse("ingredient with non existing id",
                        response->response.body("message", is(ExceptionMessages.IngredientNotFoundException)))
        );
    }

    @Then("{admin} should see message about selected ingredient")
    public void userSeesMessageAboutSelectedIngredient(Actor admin){
        admin.should(
                seeThatResponse("details for ingredient with id",
                        response->response.statusCode(200))
        );

        Ingredient ingredient=SerenityRest.lastResponse().as(Ingredient.class);
        assertThat(ingredient).isEqualTo(IngredientObjects.ingredientWithId1);
    }

    @Then("{admin} should see message that ingredient with name already exists")
    public void userSeesMessageThatIngredientWithNameExists(Actor admin){
        admin.should(
                seeThatResponse("ingredient with name exists",
                        response->response.body("message", is(ExceptionMessages.IngredientWithNameExistsException)))
        );
    }

    @Then("{admin} should see message about successful adding an ingredient")
    public void userSeesMessageAboutSuccessfullyAddingIngredient(Actor admin){
        admin.should(
                seeThatResponse("adding new ingredient",
                        response->response.statusCode(200))
        );

        Ingredient ingredient=SerenityRest.lastResponse().as(Ingredient.class);
        assertThat(ingredient.getName()).isEqualTo(IngredientObjects.addedIngredientName);
        assertThat(ingredient.getQuantity()).isEqualTo(IngredientObjects.addedIngredientQuantity);
    }

    @Then("{admin} should see message about successfully edited ingredient")
    public void userSeesMessageAboutSuccessfullyEditingIngredient(Actor admin){
        admin.should(
                seeThatResponse("editing ingredient with id",
                        response->response.statusCode(200))
        );

        Ingredient ingredient=SerenityRest.lastResponse().as(Ingredient.class);
        assertThat(ingredient).isEqualTo(IngredientObjects.editedIngredient);
    }

    @Then("{admin} should see message about successfully deleting ingredient")
    public void userSeesMessageAboutSuccessfullyDeletingIngredient(Actor admin){
        admin.should(
                seeThatResponse("deleting ingredient with id",
                        response->response.statusCode(200))
        );
    }

}
