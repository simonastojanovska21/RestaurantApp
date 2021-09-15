package starter.search.targets;

import net.serenitybdd.screenplay.targets.Target;

public class OrderTargets {

    public static Target ORDER_ROW=Target.the("row")
            .locatedBy(".orderRow");

    public static Target ORDER_CUSTOMER_NAME_COLUMN=Target.the("column")
            .locatedBy(".customerName");

    public static Target ORDER_MEALS_NAMES_COLUMN=Target.the("column")
            .locatedBy(".orderedMeal");

    public static Target ORDER_DELIVERY_ADDRESS_COLUMN=Target.the("column")
            .locatedBy(".deliveryAddress");

    public static Target READY_FOR_DELIVERY_BUTTON_COLUMN=Target.the("column")
            .locatedBy(".readyForDeliveryButton");

}
