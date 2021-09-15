package starter.stepsDefinition.authentication;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.Get;
import net.serenitybdd.screenplay.rest.interactions.Post;
import starter.ExceptionMessages;
import starter.data.dto.RegisterDto;
import starter.data.dto.ReviewDto;
import starter.data.model.User;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;
import static org.hamcrest.Matchers.is;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationStepsDefinition {


    @When("{admin} enters {string} {string} {string} {string} {string} {string} {string}")
    public void actorEntersData(Actor admin,String username,String password,String repeatedPassword,String name, String surname,
                                String phoneNumber,String address){

        admin.attemptsTo(
                Post.to("/api/auth/register")
                        .with(request->request.header("Content-Type", "application/json")
                                .body(new RegisterDto(username,password,repeatedPassword,name,surname,phoneNumber,address))
                        )
        );
    }

    @When("{admin} attempts to register with missing fields")
    public void adminRegistersWithMissingField(Actor admin){
        admin.attemptsTo(
                Post.to("/api/auth/register")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{}")),
                Post.to("/api/auth/register")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{ \"password\":\"P@ssword\", \"repeatedPassword\":\"P@ssw123ord\", " +
                                        "\"name\":\"TestName\", \"surname\":\"TestSurname\",  \"phoneNumber\":\"070123456\", \"address\":\"TestAddress\"}")
                        ),
                Post.to("/api/auth/register")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"username\":\"testUser@test.com\", \"repeatedPassword\":\"P@ssw123ord\", " +
                                        "\"name\":\"TestName\", \"surname\":\"TestSurname\",  \"phoneNumber\":\"070123456\", \"address\":\"TestAddress\"}")
                        ),
                Post.to("/api/auth/register")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"username\":\"testUser@test.com\", \"password\":\"P@ssword\",  " +
                                        "\"name\":\"TestName\", \"surname\":\"TestSurname\",  \"phoneNumber\":\"070123456\", \"address\":\"TestAddress\"}")
                        ),
                Post.to("/api/auth/register")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"username\":\"testUser@test.com\", \"password\":\"P@ssword\", \"repeatedPassword\":\"P@ssw123ord\", " +
                                        " \"surname\":\"TestSurname\",  \"phoneNumber\":\"070123456\", \"address\":\"TestAddress\"}")
                        ),
                Post.to("/api/auth/register")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"username\":\"testUser@test.com\", \"password\":\"P@ssword\", \"repeatedPassword\":\"P@ssw123ord\", " +
                                        "\"name\":\"TestName\",   \"phoneNumber\":\"070123456\", \"address\":\"TestAddress\"}")
                        ),
                Post.to("/api/auth/register")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"username\":\"testUser@test.com\", \"password\":\"P@ssword\", \"repeatedPassword\":\"P@ssw123ord\", " +
                                        "\"name\":\"TestName\", \"surname\":\"TestSurname\",   \"address\":\"TestAddress\"}")
                        ),
                Post.to("/api/auth/register")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"username\":\"testUser@test.com\", \"password\":\"P@ssword\", \"repeatedPassword\":\"P@ssw123ord\", " +
                                        "\"name\":\"TestName\", \"surname\":\"TestSurname\",  \"phoneNumber\":\"070123456\"}")
                        )
        );
    }

    @When("{admin} tries to get info for user with username {string}")
    public void adminGetInfoAboutUser(Actor admin, String username){
        admin.attemptsTo(
                Get.resource("/api/auth/userInfo/{username}").with(request->request.pathParam("username",username))
        );
    }

    @When("{admin} attempts to leave a review with missing fields")
    public void adminLeavesAReviewWithMissingField(Actor admin){
        admin.attemptsTo(
                Post.to("/api/auth/review")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{}")),
                Post.to("/api/auth/review")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"description\":\"review description\",\n" +
                                        "    \"username\":\"admin@admin.com\"}")),
                Post.to("/api/auth/review")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"stars\":2,\n" +
                                        "    \"username\":\"admin@admin.com\"}")),
                Post.to("/api/auth/review")
                        .with(request->request.header("Content-Type", "application/json")
                                .body("{\"stars\":2,\n" +
                                        "    \"description\":\"review description\"}"))
        );
    }

    @When("{admin} enters {string} {string} {string}")
    public void adminLeavesAReview(Actor admin, String stars, String description,String username){
        Integer starsInt=Integer.parseInt(stars);
        admin.attemptsTo(
                Post.to("/api/auth/review")
                        .with(request->request.header("Content-Type", "application/json")
                                .body(new ReviewDto(starsInt,description,username))
                        )
        );
    }

    @Then("{admin} should see message about empty fields")
    public void messageAboutEmptyField(Actor admin){
        admin.should(
                seeThatResponse("empty field exception",
                        response->response.body("message",is(ExceptionMessages.EmptyDataException)))
        );
    }

    @Then("{admin} should see message about missing fields")
    public void messageAboutMissingFields(Actor admin){
        admin.should(
                seeThatResponse("missing field exception",
                        response->response.body("message",is(ExceptionMessages.MissingFieldException))
                )
        );
    }

    @Then("{admin} should see message that passwords do not match")
    public void messageAboutPasswords(Actor admin){
        admin.should(
                seeThatResponse("missing field exception",
                        response->response.body("message",is(ExceptionMessages.PasswordsDoNotMatchException))
                )
        );
    }

    @Then("{admin} should see message that username exists")
    public void messageAboutExistingUsername(Actor admin){
        admin.should(
                seeThatResponse("missing field exception",
                        response->response.body("message",is(ExceptionMessages.InvalidUsernameException))
                )
        );
    }

    @Then("{admin} should see message about successful registration")
    public void successfulRegistration(Actor admin){
        admin.should(
                seeThatResponse("successful register",
                        response->response.statusCode(200)
                                .body("username",is("testUser@test.com")))
        );
    }

    @Then("{admin} should see message about invalid credentials")
    public void messageAboutInvalidCredentials(Actor admin){
        admin.should(
                seeThatResponse("invalid user credentials",
                        response->response.body("message",is(ExceptionMessages.UserNotFoundException)))
        );
    }

    @Then("{admin} should see message about selected user")
    public void messageAboutSelectedUser(Actor admin){
        admin.should(
                seeThatResponse("valid user credentials",
                        response->response.statusCode(200))
        );

        User user= SerenityRest.lastResponse().as(User.class);

        assertThat(user.getUsername()).isEqualTo("admin@admin.com");
    }

    @Then("{admin} should see message about successful leaving review")
    public void successfulLeavingReview(Actor admin){
        admin.should(
                seeThatResponse("successful leave a review",
                        response->response.statusCode(200))
        );
    }
}
