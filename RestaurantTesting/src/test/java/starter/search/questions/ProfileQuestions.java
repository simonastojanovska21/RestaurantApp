package starter.search.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import starter.search.targets.ProfileTargets;

public class ProfileQuestions {

    public static Question<String> getCustomer2UserName(){
        return Text.of(ProfileTargets.CUSTOMER2_USER_NAME)
                .asAString();
    }

    public static Question<String> getCustomer2UserSurname(){
        return Text.of(ProfileTargets.CUSTOMER2_USER_SURNAME)
                .asAString();
    }

    public static Question<String> getCustomer2Address(){
        return Text.of(ProfileTargets.CUSTOMER2_USER_ADDRESS)
                .asAString();
    }

    public static Question<String> getCustomer2PhoneNumber(){
        return Text.of(ProfileTargets.CUSTOMER2_USER_PHONE_NUMBER)
                .asAString();
    }
}
