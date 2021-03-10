package specifications;

import helpers.RequestHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecs {

    //It runs before a specific group
    //@BeforeGroups("Authentication")
    public static RequestSpecification generateToken(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();

        String token = RequestHelper.getUserToken();
        requestSpecBuilder.addHeader("Authorization","Bearer " + token);
        return requestSpecBuilder.build();
    }

    public static RequestSpecification generateFakeToken(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Authorization","Bearer eyJhbGciOdfiJIUzI1NiIsInR5cCICJ9.eyJhY2Nlc3NfdXVpZCI6IjQ1ZjVkYThiLTE1MTEtNDY3Yy04Y2QwLWI5NzNlNWQ0YWZhYSIsImF1dGhvcml6ZWQiOnRydWUsImV4cCI6MTYxNDczMTE5OCwidXNlcl9pZCI6MTE4fQ.dldXyzDa5VE9WUm8LlsxsVHJTyYr9Nl5h9mu_HFAtHQ");
        return requestSpecBuilder.build();
    }

}
