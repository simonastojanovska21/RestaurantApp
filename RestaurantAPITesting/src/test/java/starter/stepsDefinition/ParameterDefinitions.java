package starter.stepsDefinition;

import io.cucumber.java.Before;
import io.cucumber.java.ParameterType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.util.EnvironmentVariables;
import org.openqa.selenium.WebDriver;


public class ParameterDefinitions {
    public static String DEFAULT_URL="http://localhost:3000/";

    @ParameterType(".*")
    public Actor admin(String actorName) {
        return OnStage.theActorCalled(actorName).whoCan(CallAnApi.at("http://localhost:8080"));
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }
}
