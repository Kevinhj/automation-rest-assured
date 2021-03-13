import helpers.DataHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Comment;
import model.Post;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;
import specifications.RequestSpecs;
import specifications.ResponseSpecs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommentTests extends BaseTest {

    private static String resourcePath = "/v1/comment";

    @Test(groups = {"create_post"})
    public void Test_Create_Comment_Success(){

        Comment testComment = new Comment(DataHelper.generateRandomName(), DataHelper.generateRandomComment());

        System.out.println("Here is the post "+ postId);
        given()
                .spec(RequestSpecs.basicToken())
                .body(testComment)
                .post(resourcePath + "/" + postId)
                .then()
                .statusCode(200)
                .body("message", equalTo("Comment created"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 1)
    public void Test_Get_All_Comments_From_Post_Success(){

        System.out.println("Here is the value of post id" + postId);
        given()
                .spec(RequestSpecs.basicToken())
                .get(resourcePath + "s/" + postId)
                .then()
                .statusCode(200)
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 1)
    public void Test_Get_Comment_From_Post_Success(){

        System.out.println("Here is the value of post id" + postId);
        given()
                .spec(RequestSpecs.basicToken())
                .get(resourcePath + "/" + postId + "/" + commentId)
                .then()
                .statusCode(200)
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 1)
    public void Test_Update_Comment_Success(){

        Comment testComment = new Comment(DataHelper.generateRandomName(), DataHelper.generateRandomComment());

        System.out.println("Here is the post "+ postId);
        given()
                .spec(RequestSpecs.basicToken())
                .body(testComment)
                .put(resourcePath + "/" + postId + "/" + commentId)
                .then()
                .statusCode(200)
                .body("message", equalTo("Comment updated"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 2)
    public void Test_Delete_Comment_Success(){

        given()
                .spec(RequestSpecs.basicToken())
                .delete(resourcePath + "/" + postId + "/" + commentId)
                .then()
                .statusCode(200)
                .body("message", equalTo("Comment deleted"))
                .spec(ResponseSpecs.defaultSpec());
    }
    //Negative test -> create a comment for a non existing post
}
