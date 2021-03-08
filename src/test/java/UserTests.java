import model.User;
import org.testng.annotations.Test;
import specifications.ResponseSpecs;

import static helpers.DataHelper.generateRandomEmail;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTests extends BaseTest{

    private static String resourcePath = "/v1/user";

    @Test
    public void Test_Create_User_Already_Exist(){

        User user = new User("Kevin", "kevintest@test.com","kevin" );

        given()
                .body(user)
                .post(resourcePath + "/register")
        .then()
                .body("message", equalTo("User already exists"))
                .statusCode(406);
    }

    @Test
    public void Test_Create_User_Successful(){

        User user = new User("Kevin", generateRandomEmail(),"kevin" );
        System.out.println("Test email" + user.getEmail());

        given()
                .body(user)
                .post(resourcePath + "/register")
                .then()
                .body("message", equalTo("Successfully registered"))
                .statusCode(200)
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test
    public void Test_Login_User_Successful(){
        User user = new User("Kevin", "kevintest@test.com","kevin" );

        given()
                .body(user)
                .post(resourcePath + "/login")
            .then()
                .body("message", equalTo("User signed in"))
                .statusCode(200)
                .spec(ResponseSpecs.defaultSpec());
    }

}
