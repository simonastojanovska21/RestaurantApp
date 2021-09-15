package starter.search.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import starter.search.targets.DeliveryTargets;

import java.util.List;

public class DeliveryQuestions {



    public static Question<List<String>> displayedIds()
    {
        return Text.of(DeliveryTargets.DELIVERY_ID_COLUMN)
                .describedAs("deliveries id")
                .asAList();
    }
    public static Question<List<String>> displayedAddresses() {
        return Text.of(DeliveryTargets.DELIVERY_ADDRESS_COLUMN)
                .describedAs("deliveries address")
                .asAList();
    }
    public static Question<List<String>> displayedTimeForDelivery(){
        return Text.of(DeliveryTargets.DELIVERY_TIME_FOR_DELIVERY_COLUMN)
                .describedAs("time for delivery")
                .asAList();
    }
}
