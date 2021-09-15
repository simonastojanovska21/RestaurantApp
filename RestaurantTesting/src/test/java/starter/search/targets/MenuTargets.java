package starter.search.targets;

import net.serenitybdd.screenplay.targets.Target;

public class MenuTargets {
    public static Target MENU_ITEMS_NAME=Target.the("menu item name")
            .locatedBy(".menuItemName");

    public static Target MEAL3_MINUS_BUTTON=Target.the("add to shopping cart minus button")
            .locatedBy(".minusButton-5");
    public static Target MEAL3_PLUS_BUTTON=Target.the("add to shopping cart plus button")
            .locatedBy(".plusButton-5");
    public static Target MEAL3_ADD_TO_SHOPPING_CART_BUTTON=Target.the("add to shopping cart button")
            .locatedBy(".addButton-5");
    public static Target MEAL3_QUANTITY_FIELD=Target.the("quantity text field")
            .locatedBy(".quantityField-5");

    public static Target MEAL4_PLUS_BUTTON=Target.the("add to shopping cart plus button")
            .locatedBy(".plusButton-6");
    public static Target MEAL4_ADD_TO_SHOPPING_CART_BUTTON=Target.the("add to shopping cart button")
            .locatedBy(".addButton-6");
}
