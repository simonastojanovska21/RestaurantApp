package starter.stepsDefinition.mealCategories;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import starter.ExceptionMessages;
import starter.data.dto.MealCategoryDto;
import starter.data.model.Ingredient;
import starter.data.model.MealCategory;
import starter.stepsDefinition.ingredients.IngredientObjects;

import java.util.Arrays;
import java.util.List;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

import static org.assertj.core.api.Assertions.assertThat;

public class MealCategoriesStepDefinitions {

    @When("{admin} tries to get a list of all meal categories")
    public void userTriesToGetAListOfMealCategories(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/mealCategories")
        );
    }
    @When("{admin} tries to get info about meal categories with id {long}")
    public void userTriesToGetDetailsAboutMealCategory(Actor admin, Long mealCategoryId){
        admin.attemptsTo(
                Get.resource("/api/mealCategories/{id}").with(request->request.pathParam("id",mealCategoryId))
        );
    }
    @When("{admin} attempts to create new meal categories with missing data")
    public void userCreatesMealCategoryWithMissingData(Actor admin){
        admin.attemptsTo(
                Post.to("/api/mealCategories/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{}")
                        ),
                Post.to("/api/mealCategories/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{ \"name\":\"\" }")
                        ),
                Post.to("/api/mealCategories/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"imageUrl\":\"0\"}")
                        )

        );
    }
    @When("{admin} enters {string} {string} for meal category")
    public void userEntersDataForAddingMealCategory(Actor admin, String name, String imageUrl){
        admin.attemptsTo(
                Post.to("/api/mealCategories/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body(new MealCategoryDto(name,imageUrl))
                        )

        );
    }

    @When("{admin} tries to edit meal category with id {long}")
    public void userTriesToEditMealCategoryWithId(Actor admin, Long mealCategoryId) {
        admin.attemptsTo(
                Put.to("/api/mealCategories/edit/{id}")
                        .with(request->request.pathParam("id",mealCategoryId)
                                .header("Content-Type", "application/json")
                                .body(MealCategoriesObjects.mealCategoryWithId1)
                        )
        );
    }
    @When("{admin} tries to edit meal category with id {long} with missing data")
    public void userTriesToEditMealCategoryWithMissingData(Actor admin, Long mealCategoryId){

        admin.attemptsTo(
                Put.to("/api/mealCategories/edit/{id}")
                        .with(request->request.pathParam("id",mealCategoryId)
                                .header("Content-Type", "application/json")
                                .body("{}")
                        ),
                Put.to("/api/mealCategories/edit/{id}")
                        .with(request->request.pathParam("id",mealCategoryId)
                                .header("Content-Type", "application/json")
                                .body("{ \"name\":\"\" }")
                        ),
                Put.to("/api/mealCategories/edit/{id}")
                        .with(request->request.pathParam("id",mealCategoryId)
                                .header("Content-Type", "application/json")
                                .body("{\"imageUrl\":\"0\"}")
                        )
        );
    }

    @When("{admin} tries to edit meal category with id {long} and enters {string} {string}")
    public void userTriesToEditMealCategoryWithEmptyData(Actor admin, Long mealCategoryId, String name, String imageUrl){
        admin.attemptsTo(
                Put.to("/api/mealCategories/edit/{id}")
                        .with(request->request.pathParam("id",mealCategoryId)
                                .header("Content-Type", "application/json")
                                .body(new MealCategoryDto(name,imageUrl))
                        )
        );
    }

    @When("{admin} tries to delete meal category with id {long}")
    public void userTriesToDeleteMealCategory(Actor admin, Long mealCategoryId){
        admin.attemptsTo(
                Delete.from("/api/mealCategories/delete/{id}").with(request->request.pathParam("id",mealCategoryId))
        );
    }

    @Then("{admin} should see list of {int} meal categories")
    public void userSeesListOfNumberMealCategories(Actor admin, int number){
        admin.should(
                seeThatResponse("list of meal categories",
                        response->response.statusCode(200))
        );

        List<MealCategoryDto> mealCategoriesList= Arrays.asList(SerenityRest.lastResponse().as(MealCategoryDto[].class));
        assertThat(mealCategoriesList).hasSize(number);
    }

    @Then("{admin} should see message that meal category is not found")
    public void userSeesMessageThatMealCategoryIsNotFound(Actor admin){
        admin.should(
                seeThatResponse("meal category with non existing id",
                        response->response.body("message", is(ExceptionMessages.MealCategoryNotFoundException)))
        );
    }

    @Then("{admin} should see message about selected meal categories")
    public void userSeesInfoAboutSelectedMealCategory(Actor admin){
        admin.should(
                seeThatResponse("details for meal category with id",
                        response->response.statusCode(200))
        );

        MealCategory mealCategory=SerenityRest.lastResponse().as(MealCategory.class);
        assertThat(mealCategory).isEqualTo(MealCategoriesObjects.mealCategoryWithId1);
    }

    @Then("{admin} should see message that meal category with name already exists")
    public void userSeesMessageThatMealCategoryWithNameExists(Actor admin){
        admin.should(
                seeThatResponse("meal category with name exists",
                        response->response.body("message", is(ExceptionMessages.MealCategoryWithNameExistsException)))
        );
    }
    @Then("{admin} should see message about successful adding meal category")
    public void userSeesMessageAboutSuccessfullyAddingMealCategory(Actor admin){
        admin.should(
                seeThatResponse("adding new meal category",
                        response->response.statusCode(200))
        );
        MealCategory mealCategory=SerenityRest.lastResponse().as(MealCategory.class);
        assertThat(mealCategory.getName()).isEqualTo(MealCategoriesObjects.addedMealCategoryName);
        assertThat(mealCategory.getImageUrl()).isEqualTo(MealCategoriesObjects.addedMealCategoryImageUrl);
    }
    @Then("{admin} should see message about successfully edited meal categories")
    public void userSeesMessageAboutSuccessfullyEditingMealCategory(Actor admin){
        admin.should(
                seeThatResponse("editing meal category with id",
                        response->response.statusCode(200))
        );

        MealCategory mealCategory=SerenityRest.lastResponse().as(MealCategory.class);
        assertThat(mealCategory).isEqualTo(MealCategoriesObjects.editedMealCategory);
    }
    @Then("{admin} should see message about successfully deleting meal category")
    public void userSeesMessageAboutSuccessfullyDeletingMealCategory(Actor admin){
        admin.should(
                seeThatResponse("deleting meal category with id",
                        response->response.statusCode(200))
        );
    }
}
