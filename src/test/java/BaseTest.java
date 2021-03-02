import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected RequestSpecification baseRequest;
    protected ResponseSpecification headerSpec;

    @Parameters("baseUrl")
    @BeforeMethod
    public void setUp(@Optional("http://localhost:9000") String baseUrl){

        RestAssured.baseURI = baseUrl;
        baseRequest = given().headers("User-Agent","Mi user agent");

        //Object that build the header validation
        ResponseSpecBuilder responseBuilder = new ResponseSpecBuilder();
        responseBuilder.expectHeader("Content-Type","application/json; charset=utf-8");
        responseBuilder.expectHeader("Access-Control-Allow-Origin","http://localhost");

        headerSpec = responseBuilder.build();
    }
}
