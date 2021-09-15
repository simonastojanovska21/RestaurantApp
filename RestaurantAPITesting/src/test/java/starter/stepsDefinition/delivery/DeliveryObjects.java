package starter.stepsDefinition.delivery;

import starter.data.enumerations.OrderStatus;
import starter.data.enumerations.Role;
import starter.data.model.Delivery;
import starter.data.model.Order;
import starter.data.model.User;

import java.time.LocalDateTime;

public class DeliveryObjects {

    public static String[] authorities={"ROLE_ADMIN"};

    public static String[] customerAuthorities={"ROLE_CUSTOMER"};

    public static User deliveryDetailsUserExpected=new User("admin@admin.com","$2a$10$3gsLhy8ClyTQM84VqCBOiONk2TWiU/a5pkj9jhmm17LQfygh6aali",
            "Admin name","Admin surname","070123465","Admin address", Role.ROLE_ADMIN,true,true,true,true,
            authorities);

    public static Order deliveryDetailsOrderExpected=
            new Order(3L,LocalDateTime.of(2021, 7,17,19,50,50), OrderStatus.DELIVERING,deliveryDetailsUserExpected);

    public static Delivery detailsAboutDeliveryWithId1 =
            new Delivery(1L,"Admin address",LocalDateTime.of(2021,11,2,15,30,25),deliveryDetailsOrderExpected);



    public static Order orderForDeliveryAfterClickingOnFinish=
            new Order(3L,LocalDateTime.of(2021, 7,17,19,50,50), OrderStatus.RECEIVED,deliveryDetailsUserExpected);

    public static Delivery deliveryAfterClickOnFinish=
            new Delivery(1L,"Admin address",LocalDateTime.of(2021,11,2,15,30,25),orderForDeliveryAfterClickingOnFinish);


    public static User createdDeliveryForUser=
            new User("customer1@test.com","$2a$10$WT2Gimb7Dy.VSWBCQjtBeOSkcAf8p0r8eo4f5f7JZuiImxZL.BKwe",
                    "Customer1 name","Customer1 surname","070123465", "Customer1 address", Role.ROLE_CUSTOMER,true,
                    true,true,true,customerAuthorities);

    public static Order orderAfterCreatingNewDelivery=
            new Order(8L,LocalDateTime.of(2021,7,25,1,23,5),OrderStatus.DELIVERING,createdDeliveryForUser);
}
