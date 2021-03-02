import model.User;
import org.testng.annotations.Test;
import static helpers.DataHelper.generateRandomEmail;
import static org.hamcrest.Matchers.equalTo;

public class UserTests extends BaseTest{

    private static String resourcePath = "/v1/user";

    @Test
    public void Test_Create_User_Already_Exist(){

        User user = new User("Kevin", "kevintest@test.com","kevin" );

        baseRequest.body(user)
                .post(resourcePath + "/register")
        .then()
                .body("message", equalTo("User already exists"))
                .statusCode(406);
    }

    @Test
    public void Test_Create_User_Successful(){

        User user = new User("Kevin", generateRandomEmail(),"kevin" );
        System.out.println("Test email" + user.getEmail());

        baseRequest.body(user)
                .post(resourcePath + "/register")
                .then()
                .body("message", equalTo("Successfully registered"))
                .statusCode(200);
    }

    @Test
    public void Test_Login_User_Successful(){
        User user = new User("Kevin", "kevintest@test.com","kevin" );

        baseRequest.body(user)
                .post(resourcePath + "/login")
                .then()
                .body("message", equalTo("User signed in"))
                .statusCode(200);
    }

}
