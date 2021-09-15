package starter.search.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.JavaScriptClick;
import net.serenitybdd.screenplay.ensure.web.ElementLocated;
import starter.search.targets.ProfileTargets;

public class PerformProfileActions {
    public static Performable leaveAReview(String description){
        return Task.where("{0} attempts to leave a review",
                JavaScriptClick.on(ProfileTargets.FIVE_STAR_REVIEW),
                Enter.theValue(description)
                .into(ProfileTargets.REVIEW_DESCRIPTION_FIELD),
                JavaScriptClick.on(ProfileTargets.SUBMIT_REVIEW_BUTTON)
        );
    }
}
