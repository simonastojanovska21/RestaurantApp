package starter.search.targets;


import net.serenitybdd.screenplay.targets.Target;

public class DeliveryTargets {

    public static final Target DELIVERIES_ROW=Target.the("delivery table rows")
            .locatedBy(".deliveryRows");

    public static final Target DELIVERY_ID_COLUMN=Target.the("delivery id column")
            .locatedBy(".deliveryId");

    public static final Target DELIVERY_ADDRESS_COLUMN=Target.the("delivery address column")
            .locatedBy(".deliveryAddress");

    public static final Target DELIVERY_TIME_FOR_DELIVERY_COLUMN =Target.the("delivery time for delivery column")
            .locatedBy(".timeForDelivery");

    public static final Target DELIVERY_BUTTON_COLUMN =Target.the("delivery time for delivery column")
            .locatedBy(".deliveredButton");
}
