package starter.stepsDefinition.authentication;

import starter.data.dto.RegisterDto;
import starter.data.dto.ReviewDto;
import starter.data.enumerations.Role;
import starter.data.model.User;

public class AuthenticationObjects {

    public static String[] authorities={"ROLE_ADMIN"};

    public static User expectedUser=new User("admin@admin.com","$2a$10$3gsLhy8ClyTQM84VqCBOiONk2TWiU/a5pkj9jhmm17LQfygh6aali",
            "Admin name","Admin surname","070123465","Admin address", Role.ROLE_ADMIN,true,true,true,true,
            authorities);

}
