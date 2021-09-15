package starter.search.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import starter.search.targets.OrderTargets;

import java.util.List;

public class OrderQuestions {

    public static Question<List<String>> displayedCustomerName(){
        return Text.of(OrderTargets.ORDER_CUSTOMER_NAME_COLUMN)
        .asAList();
    }

    public static Question<List<String>> displayedMeal(){
        return Text.of(OrderTargets.ORDER_MEALS_NAMES_COLUMN)
                .asAList();
    }

    public static Question<List<String>> displayedAddressForDelivery(){
        return Text.of(OrderTargets.ORDER_DELIVERY_ADDRESS_COLUMN)
                .asAList();
    }


}
