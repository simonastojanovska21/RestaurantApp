package starter.search.actions;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import org.openqa.selenium.Keys;
import starter.search.targets.LoginTargets;
import starter.search.targets.RegisterTargets;

public class PerformActionsForUserManagement {

    public static Performable loginUser(String username, String password)
    {
        return Task.where("{0} attempts login",
                Enter.theValue(username)
                        .into(LoginTargets.USERNAME_FIELD),
                Enter.theValue(password)
                        .into(LoginTargets.PASSWORD_FIELD),
                Click.on(LoginTargets.LogInButton)
                );
    }

    public static Performable registerUser(String username,String password,String repeatedPassword,
                                           String name, String surname,String phone,String address)
    {
        return Task.where("{0} attempts register",
                Enter.theValue(username)
                    .into(RegisterTargets.USERNAME_FIELD),
                Enter.theValue(password)
                        .into(RegisterTargets.PASSWORD_FIELD),
                Enter.theValue(repeatedPassword)
                        .into(RegisterTargets.REPEATED_PASSWORD_FIELD),
                Enter.theValue(name)
                        .into(RegisterTargets.NAME_FIELD),
                Enter.theValue(surname)
                        .into(RegisterTargets.SURNAME_FIELD),
                Enter.theValue(phone)
                        .into(RegisterTargets.PHONE_NUMBER_FIELD),
                Enter.theValue(address)
                        .into(RegisterTargets.ADDRESS_FIELD)
                .thenHit(Keys.ENTER)
                );
    }


}
