import helpers.DataHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Article;
import model.Post;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import specifications.RequestSpecs;
import specifications.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostTests extends BaseTest{

    private static String resourcePath = "/v1/post";

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
    public void Test_Get_All_Post_Success(){

        given()
                .spec(RequestSpecs.generateToken())
                .get(resourcePath + "s")
                .then()
                .statusCode(200)
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
}
