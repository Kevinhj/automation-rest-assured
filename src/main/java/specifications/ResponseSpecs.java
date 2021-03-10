package specifications;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecs {

    public static ResponseSpecification defaultSpec(){

        //Object that build the header validation
        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectHeader("Content-Type","application/json; charset=utf-8");
        responseBuilder.expectHeader("Access-Control-Allow-Origin","http://localhost");

        return responseBuilder.build();
    }
}
