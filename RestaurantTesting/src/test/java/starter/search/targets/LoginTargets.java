package starter.search.targets;


import net.serenitybdd.screenplay.targets.Target;

public class LoginTargets {

    public static Target USERNAME_FIELD=Target.the("username field")
            .locatedBy("#username");

    public static Target PASSWORD_FIELD=Target.the("password field")
            .locatedBy("#password");

    public static Target LogInButton=Target.the("login button")
            .locatedBy("#loginSubmit");

}
