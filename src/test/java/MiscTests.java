import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.StringContains.containsString;

public class MiscTests extends BaseTest {

    private static String resourcePath = "";

    @Test
    public void Test_PING_ENDPOINT(){
        baseRequest
                .get(String.format("%s%s/ping",baseUrl,resourcePath))
        .then()
                .header("Content-Length", equalTo("50"))
                .body("response", equalTo("pong"))
                .statusCode(200);
    }

    @Test
    public void Test_Home_Page(){
        baseRequest
                .get(String.format("%s",baseUrl))
        .then()
                .body(containsString("Gin Boilerplate"))
        .statusCode(200);
    }
}
