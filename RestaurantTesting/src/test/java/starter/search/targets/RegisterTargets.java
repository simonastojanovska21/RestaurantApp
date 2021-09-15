package starter.search.targets;


import net.serenitybdd.screenplay.targets.Target;

public class RegisterTargets {

    public static Target USERNAME_FIELD= Target.the("username")
            .locatedBy("#username");
    public static Target PASSWORD_FIELD=Target.the("password")
            .locatedBy("#password");
    public static Target REPEATED_PASSWORD_FIELD=Target.the("repeatedPassword")
            .locatedBy("#repeatedPassword");
    public static Target NAME_FIELD=Target.the("name")
            .locatedBy("#name");
    public static Target SURNAME_FIELD=Target.the("surname")
            .locatedBy("#surname");
    public static Target PHONE_NUMBER_FIELD=Target.the("phoneNumber")
            .locatedBy("#phoneNumber");
    public static Target ADDRESS_FIELD=Target.the("address")
            .locatedBy("#address");

    public static Target PASSWORDS_DO_NOT_MATCH=Target.the("div password message")
            .locatedBy("#passwordDoNotMatch");
    public static Target USERNAME_ALREADY_EXISTS=Target.the("div username message")
            .locatedBy("#userExists");

    public static Target EMPTY_EMAIL =Target.the("email message div")
            .locatedBy("#emptyEmail");
    public static Target EMPTY_PASSWORD1 =Target.the("password1 message div")
            .locatedBy("#emptyPassword1");
    public static Target EMPTY_REPEATED_PASSWORD =Target.the("repeatedPassword message div")
            .locatedBy("#emptyRepeatedPassword");
    public static Target EMPTY_NAME =Target.the("name message div")
            .locatedBy("#emptyName");
    public static Target EMPTY_SURNAME =Target.the("surname message div")
            .locatedBy("#emptySurname");
    public static Target EMPTY_PHONE_NUMBER =Target.the("phone number message div")
            .locatedBy("#emptyPhoneNumber");
    public static Target EMPTY_ADDRESS =Target.the("address message div")
            .locatedBy("#emptyAddress");
}
