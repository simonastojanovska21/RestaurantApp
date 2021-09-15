package starter.search;

import net.serenitybdd.screenplay.targets.Target;

public class HeaderButtons {
    public static Target MENU_BUTTON=Target.the("header menu button")
            .locatedBy("#menuButton");

    public static Target PROCESSING_ORDERS_BUTTON=Target.the("header orders button")
            .locatedBy("#ordersButton");

    public static Target DELIVERIES_BUTTON=Target.the("header deliveries button")
            .locatedBy("#deliveriesButton");

    public static Target PROFILE_BUTTON=Target.the("header profile button")
            .locatedBy("#profileButton");

    public static Target SHOPPING_CART_BUTTON=Target.the("header shopping cart button")
            .locatedBy("#shoppingCartButton");

    public static Target LOGOUT_BUTTON=Target.the("header logout button")
            .locatedBy("#logoutButton");

    public static Target LOGIN_BUTTON=Target.the("header login button")
            .locatedBy("#loginButton");
}
