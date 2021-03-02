import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected RequestSpecification baseRequest;

    @Parameters("baseUrl")
    @BeforeMethod
    public void setUp(@Optional("http://localhost:9000") String baseUrl){

        RestAssured.baseURI = baseUrl;
        baseRequest = given().headers("User-Agent","Mi user agent");

    }
}
