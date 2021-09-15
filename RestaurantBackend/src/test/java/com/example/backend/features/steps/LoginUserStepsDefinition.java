package com.example.backend.features.steps;

import com.example.backend.features.steps.serenity.UserSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;

public class LoginUserStepsDefinition {

    @Steps
    UserSteps userSteps;

    @Given("User wants to login to web application")
    public void userWantToLogin()
    {
        System.out.println("Wants to login");
    }
    @When("User enters {string} and {string}")
    public void user_enters_and_balls(String username,String password)
    {
        System.out.format("login: %s, pass: %s \n",username,password);
    }

    @Then("He should be redirected to home page")
    public void redirectAfterSuccessfulLogin()
    {
        System.out.println("Successfull login");
    }

    @Then("Invalid feedback should be displayed")
    public void invalid_feedback()
    {
        System.out.println("Invalid feedback should be displayed for user");
    }

    @Then("Message about invalid credentials should be displayed")
    public void message_about_invalid()
    {
        System.out.println("Message about invalid credentials should be displayed");
    }
}
