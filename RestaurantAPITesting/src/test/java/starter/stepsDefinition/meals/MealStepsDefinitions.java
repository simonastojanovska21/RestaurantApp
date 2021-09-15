package starter.stepsDefinition.meals;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.Delete;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.interactions.Put;
import starter.ExceptionMessages;
import starter.data.dto.MealDto;
import starter.data.model.Ingredient;
import starter.data.model.Meal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

import static org.assertj.core.api.Assertions.assertThat;

public class MealStepsDefinitions {

    @When("{admin} tries to get a list of all meals")
    public void userGetsListOfAllMeals(Actor admin){
        admin.attemptsTo(
                Get.resource("/api/meals/all")
        );
    }

    @When("{admin} tries to get info about meal with id {long}")
    public void userGetsDetailsAboutMeal(Actor admin, Long mealId){
        admin.attemptsTo(
                Get.resource("/api/meals/{id}").with(request->request.pathParam("id",mealId))
        );
    }
    @When("{admin} attempts to create new meal with missing data")
    public void userCreatesMealWithMissingData(Actor admin){
        admin.attemptsTo(
                Post.to("/api/meals/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{}")
                        ),
                Post.to("/api/meals/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body(" {   \"description\":\"\",\n" +
                                        "    \"price\":0,\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"imageUrl\":\"\",\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Post.to("/api/meals/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"price\":0,\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"imageUrl\":\"\",\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Post.to("/api/meals/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"description\":\"\",\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"imageUrl\":\"\",\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Post.to("/api/meals/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"description\":\"\",\n" +
                                        "    \"price\":0,\n" +
                                        "    \"imageUrl\":\"\",\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Post.to("/api/meals/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"description\":\"\",\n" +
                                        "    \"price\":0,\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Post.to("/api/meals/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"description\":\"\",\n" +
                                        "    \"price\":0,\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"imageUrl\":\"\" }")
                        )
        );
    }
    @When("{admin} enters {string} {string} {string} {string} {string} {string} for meal")
    public void userCreatesMealWithEmptyData(Actor admin, String name, String description, String price, String mealCategoryId, String imageUrl, String ingredients){
        List<Long> ingredientsList= new ArrayList<>();
        if(!ingredients.isEmpty())
            ingredientsList=Arrays.stream(ingredients.split(",")).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        List<Long> finalIngredientsList = ingredientsList;
        admin.attemptsTo(
                Post.to("/api/meals/add")
                        .with(request->request.header("Content-Type", "application/json")
                                .body(new MealDto(name,description,Double.parseDouble(price),Long.parseLong(mealCategoryId),imageUrl, finalIngredientsList))
                        )
        );
    }
    @When("{admin} tries to edit meal with id {long}")
    public void userTriesToEditMealWithId(Actor admin, Long mealId){
        admin.attemptsTo(
                Put.to("/api/meals/edit/{id}")
                        .with(request->request.pathParam("id",mealId)
                                .header("Content-Type", "application/json")
                                .body(MealsObjects.mealWithId2)
                        )
        );
    }

    @When("{admin} tries to edit meal with id {long} with missing data")
    public void userTriesToEditMealWithIdAndMissingData(Actor admin, Long mealId){
        admin.attemptsTo(
                Put.to("/api/meals/edit/{id}")
                        .with(request->request.pathParam("id",mealId)
                                .header("Content-Type", "application/json")
                                .body("{}")
                        ),
                Put.to("/api/meals/edit/{id}")
                        .with(request->request.pathParam("id",mealId)
                                .header("Content-Type", "application/json")
                                .body(" {   \"description\":\"\",\n" +
                                        "    \"price\":0,\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"imageUrl\":\"\",\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Put.to("/api/meals/edit/{id}")
                        .with(request->request.pathParam("id",mealId)
                                .header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"price\":0,\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"imageUrl\":\"\",\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Put.to("/api/meals/edit/{id}")
                        .with(request->request.pathParam("id",mealId)
                                .header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"description\":\"\",\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"imageUrl\":\"\",\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Put.to("/api/meals/edit/{id}")
                        .with(request->request.pathParam("id",mealId)
                                .header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"description\":\"\",\n" +
                                        "    \"price\":0,\n" +
                                        "    \"imageUrl\":\"\",\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Put.to("/api/meals/edit/{id}")
                        .with(request->request.pathParam("id",mealId)
                                .header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"description\":\"\",\n" +
                                        "    \"price\":0,\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"ingredientsForMeal\":[0,0]   }")
                        ),
                Put.to("/api/meals/edit/{id}")
                        .with(request->request.pathParam("id",mealId)
                                .header("Content-Type", "application/json")
                                .body("{   \"name\":\"\" ,\n" +
                                        "    \"description\":\"\",\n" +
                                        "    \"price\":0,\n" +
                                        "    \"mealCategory\":0,\n" +
                                        "    \"imageUrl\":\"\" }")
                        )
        );
    }

    @When("{admin} tries to edit meal with id {long} and enters {string} {string} {string} {string} {string} {string}")
    public void userTriesToEditMealWithId(Actor admin, Long mealId,String name, String description, String price, String mealCategoryId, String imageUrl, String ingredients){
        List<Long> ingredientsList= new ArrayList<>();
        if(!ingredients.isEmpty())
            ingredientsList=Arrays.stream(ingredients.split(",")).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        List<Long> finalIngredientsList = ingredientsList;
        admin.attemptsTo(
                Put.to("/api/meals/edit/{id}")
                        .with(request->request.pathParam("id",mealId)
                                .header("Content-Type", "application/json")
                                .body(new MealDto(name,description,Double.parseDouble(price), Long.parseLong(mealCategoryId),imageUrl,finalIngredientsList))
                        )
        );
    }

    @When("{admin} tries to get meals from meal category with id {long}")
    public void userGetsMealsFromMealCategory(Actor admin, Long mealCategoryId){
        admin.attemptsTo(
                Get.resource("/api/meals/mealCategory/{mealCategoryId}").with(request->request.pathParam("mealCategoryId",mealCategoryId))
        );
    }
    @When("{admin} tries to delete meal with id {long}")
    public void userTriesToDeleteMealWithId(Actor admin, Long mealId){
        admin.attemptsTo(
                Delete.from("/api/meals/delete/{id}").with(request->request.pathParam("id",mealId))
        );
    }

    @Then("{admin} should see list of {int} meals")
    public void userSeesListOfNumberMeals(Actor admin, int number){
        admin.should(
                seeThatResponse("list of meals",
                        response->response.statusCode(200))
        );

        List<Meal> mealList=Arrays.asList(SerenityRest.lastResponse().as(Meal[].class));
        assertThat(mealList).hasSize(number);
    }
    @Then("{admin} should see message that meal is not found")
    public void userShouldSeeMessageThatMealIsNotFound(Actor admin){
        admin.should(
                seeThatResponse("meal with non existing id",
                        response->response.body("message", is(ExceptionMessages.MealNotFoundException)))
        );
    }
    @Then("{admin} should see message about selected meal")
    public void userSeesMessageAboutSelectedMeal(Actor admin){
        admin.should(
                seeThatResponse("details for meal with id",
                        response->response.statusCode(200))
        );

        Meal meal=SerenityRest.lastResponse().as(Meal.class);
        assertThat(meal).isEqualTo(MealsObjects.mealWithId3);
    }
    @Then("{admin} should see message that meal with name already exists")
    public void userSeesMessageThatMealWithNameExists(Actor admin){
        admin.should(
                seeThatResponse("meal with existing name",
                        response->response.body("message", is(ExceptionMessages.MealWithNameExistsException)))
        );
    }

    @Then("{admin} should see message about successful adding meal")
    public void userSeesMessageAboutSuccessfullyAddingMeal(Actor admin){
        admin.should(
                seeThatResponse("successfully adding meal",
                        response->response.statusCode(200))
        );
        Meal meal=SerenityRest.lastResponse().as(Meal.class);
        assertThat(meal.getName()).isEqualTo(MealsObjects.addedMealName);
        assertThat(meal.getDescription()).isEqualTo(MealsObjects.addedMealDescription);
        assertThat(meal.getPrice()).isEqualTo(MealsObjects.addedMealPrice);
        assertThat(meal.getMealCategory().getId()).isEqualTo(MealsObjects.addedMealMealCategory);
        assertThat(meal.getImageUrl()).isEqualTo(MealsObjects.addedMealImageUrl);
        assertThat(meal.getIngredientsForMeal().stream().map(Ingredient::getId).collect(Collectors.toList())).isEqualTo(MealsObjects.addedMealIngredientsId);
    }
    @Then("{admin} should see message about successfully edited meal")
    public void userSeesMessageAboutSuccessfullyEditingMeal(Actor admin){
        admin.should(
                seeThatResponse("successfully edited meal",
                        response->response.statusCode(200))
        );
        Meal meal=SerenityRest.lastResponse().as(Meal.class);
        assertThat(meal).isEqualTo(MealsObjects.editedMeal);
    }
    @Then("{admin} should see message about successfully deleting meal")
    public void userSeesMessageAboutSuccessfullyDeletingMeal(Actor admin){
        admin.should(
                seeThatResponse("successfully deleting meal",
                        response->response.statusCode(200))
        );
    }
}
