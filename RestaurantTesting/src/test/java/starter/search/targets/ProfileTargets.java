package starter.search.targets;

import net.serenitybdd.screenplay.targets.Target;

public class ProfileTargets {

    public static final Target MEAL_CATEGORIES_BUTTON=Target.the("meal categories button")
            .locatedBy("#mealCategoriesButton");

    public static final Target INGREDIENTS_BUTTON=Target.the("ingredients button")
            .locatedBy("#ingredientsButton");

    public static final Target CUSTOMER2_USER_NAME=Target.the("name")
            .locatedBy(".userName");
    public static final Target CUSTOMER2_USER_SURNAME=Target.the("surname")
            .locatedBy(".userSurname");
    public static final Target CUSTOMER2_USER_ADDRESS=Target.the("address")
            .locatedBy(".userAddress");
    public static final Target CUSTOMER2_USER_PHONE_NUMBER=Target.the("phoneNumber")
            .locatedBy(".userPhoneNumber");

    public static Target FIVE_STAR_REVIEW=Target.the("five start")
            .locatedBy("#fiveStar");
    public static Target REVIEW_DESCRIPTION_FIELD=Target.the("description field")
            .locatedBy("#review");
    public static Target SUBMIT_REVIEW_BUTTON=Target.the("submit")
            .locatedBy("#submit");

    public static Target USER_HAS_LEFT_REVIEW=Target.the("left review")
            .locatedBy("#leftReview");
}
