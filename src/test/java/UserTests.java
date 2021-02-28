import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

public class UserTests {

    private static String baseUrl = "https://api-coffee-testing.herokuapp.com";
    private static String resourcePath = "/v1/user";

    @Test
    public void Test_Create_User_Already_Exist(){
        given()
                .post(String.format("%s%s/register",baseUrl,resourcePath))
        .then()
                .header("Content-Length", equalTo("50"))
                .body("message", equalTo("User already exists"))
                .statusCode(200);
    } //Continue on min 45 Clase 3
}