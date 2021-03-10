import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    @Parameters("baseUrl")
    @BeforeClass
    public void setUp(@Optional("http://localhost:9000") String baseUrl){

        RestAssured.baseURI = baseUrl;
    }
}
