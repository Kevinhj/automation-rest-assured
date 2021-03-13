import helpers.DataHelper;
import model.Post;
import org.testng.annotations.Test;
import specifications.RequestSpecs;
import specifications.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostTests extends BaseTest{

    private static String resourcePath = "/v1/post";
    private final int fakePostId = 0;

    @Test
    public void Test_Create_Post_Success(){

        Post testPost = new Post(DataHelper.generateRandomTitle(), DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateToken())
                .body(testPost)
                .post(resourcePath)
                .then()
                .statusCode(200)
                .body("message", equalTo("Post created"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test
    public void Test_Invalid_Token_Cant_Create_Post_Success(){

        Post testPost = new Post(DataHelper.generateRandomTitle(), DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateFakeToken())
                .body(testPost)
                .post(resourcePath)
                .then()
                .statusCode(401)
                .body("message", equalTo("Please login first"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test
    public void Test_Get_All_Post_Success(){

        given()
                .spec(RequestSpecs.generateToken())
                .get(resourcePath + "s")
                .then()
                .statusCode(200)
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test
    public void Test_Invalid_Token_Cant_Get_All_Post_Success(){

        given()
                .spec(RequestSpecs.generateFakeToken())
                .get(resourcePath + "s")
                .then()
                .statusCode(401)
                .body("message", equalTo("Please login first"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = "create_post", priority = 1)
    public void Test_Get_Post_By_Id_Success(){

        given()
                .spec(RequestSpecs.generateToken())
                .get(resourcePath + "/" +  postId)
                .then()
                .statusCode(200)
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test
    public void Test_Cant_Get_Post_By_Non_Existing_Id(){

        given()
                .spec(RequestSpecs.generateToken())
                .get(resourcePath + "/" +  fakePostId)
                .then()
                .statusCode(404)
                .body("Message", equalTo("Post not found"))
                .body("error", equalTo("sql: no rows in result set"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = "create_post", priority = 2)
    public void Test_Update_Post_Success(){

        Post testPost = new Post(DataHelper.generateRandomTitle(), DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateToken())
                .body(testPost)
                .put(resourcePath + "/" +  postId)
                .then()
                .statusCode(200)
                .body("message", equalTo("Post updated"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test
    public void Test_Cant_Update_Post_By_Non_Existing_Id(){

        Post testPost = new Post(DataHelper.generateRandomTitle(), DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateToken())
                .body(testPost)
                .put(resourcePath + "/" +  fakePostId)
                .then()
                .statusCode(406)
                .body("message", equalTo("Post could not be updated"))
                .body("error", equalTo("Post not found"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = "create_post", priority = 2)
    public void Test_Invalid_Token_Cant_Update_Post_Success(){

        Post testPost = new Post(DataHelper.generateRandomTitle(), DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateFakeToken())
                .body(testPost)
                .put(resourcePath + "/" +  postId)
                .then()
                .statusCode(401)
                .body("message", equalTo("Please login first"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = "create_post", priority = 3)
    public void Test_Delete_Post_Success(){

        given()
                .spec(RequestSpecs.generateToken())
                .delete(resourcePath + "/" +  postId)
                .then()
                .statusCode(200)
                .body("message",equalTo("Post deleted"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = "create_post", priority = 3)
    public void Test_Invalid_Token_Cant_Delete_Post_Success(){

        given()
                .spec(RequestSpecs.generateFakeToken())
                .delete(resourcePath + "/" +  postId)
                .then()
                .statusCode(401)
                .body("message", equalTo("Please login first"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test
    public void Test_Cant_Delete_Post_By_Non_Existing_Id(){

        given()
                .spec(RequestSpecs.generateToken())
                .delete(resourcePath + "/" +  fakePostId)
                .then()
                .statusCode(406)
                .body("message", equalTo("Post could not be deleted"))
                .body("error", equalTo("Post not found"))
                .spec(ResponseSpecs.defaultSpec());
    }
}
