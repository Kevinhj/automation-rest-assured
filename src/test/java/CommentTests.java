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
    private final int fakeCommentId = 0;
    private final int fakePostId = 0;

    @Test(groups = {"create_post"})
    public void Test_Create_Comment_Success(){

        Comment testComment = new Comment(DataHelper.generateRandomName(), DataHelper.generateRandomComment());

        given()
                .spec(RequestSpecs.basicToken())
                .body(testComment)
                .post(resourcePath + "/" + postId)
                .then()
                .statusCode(200)
                .body("message", equalTo("Comment created"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post"})
    public void Test_Invalid_Token_Cant_Create_Comment_Success(){

        Comment testComment = new Comment(DataHelper.generateRandomName(), DataHelper.generateRandomComment());

        given()
                .spec(RequestSpecs.basicFakeToken())
                .body(testComment)
                .post(resourcePath + "/" + postId)
                .then()
                .statusCode(401)
                .body("message", equalTo("Please login first"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post"})
    public void Test_Cant_Create_Comment_By_Non_Existing_PostId(){

        Comment testComment = new Comment(DataHelper.generateRandomName(), DataHelper.generateRandomComment());

        given()
                .spec(RequestSpecs.basicToken())
                .body(testComment)
                .post(resourcePath + "/" + fakePostId)
                .then()
                .statusCode(406)
                .body("message", equalTo("Comment could not be created"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 1)
    public void Test_Get_All_Comments_From_Post_Success(){

        given()
                .spec(RequestSpecs.basicToken())
                .get(resourcePath + "s/" + postId)
                .then()
                .statusCode(200)
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 1)
    public void Test_Invalid_Token_Cant_Get_All_Comments_From_Post(){

        given()
                .spec(RequestSpecs.basicFakeToken())
                .get(resourcePath + "s/" + postId)
                .then()
                .statusCode(401)
                .body("message", equalTo("Please login first"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 1)
    public void Test_Get_Comment_From_Post_Success(){

        given()
                .spec(RequestSpecs.basicToken())
                .get(resourcePath + "/" + postId + "/" + commentId)
                .then()
                .statusCode(200)
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 1)
    public void Test_Cant_Get_Comment_From_Post_By_Non_Existing_CommentId(){

        given()
                .spec(RequestSpecs.basicToken())
                .get(resourcePath + "/" + postId + "/" + fakeCommentId)
                .then()
                .statusCode(404)
                .body("Message", equalTo("Comment not found"))
                .body("error", equalTo("sql: no rows in result set"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 1)
    public void Test_Update_Comment_Success(){

        Comment testComment = new Comment(DataHelper.generateRandomName(), DataHelper.generateRandomComment());

        given()
                .spec(RequestSpecs.basicToken())
                .body(testComment)
                .put(resourcePath + "/" + postId + "/" + commentId)
                .then()
                .statusCode(200)
                .body("message", equalTo("Comment updated"))
                .spec(ResponseSpecs.defaultSpec());
    }

    @Test(groups = {"create_post", "create_comment"}, priority = 1)
    public void Test_Cant_Update_Comment_By_Non_Existing_CommentId(){

        Comment testComment = new Comment(DataHelper.generateRandomName(), DataHelper.generateRandomComment());

        given()
                .spec(RequestSpecs.basicToken())
                .body(testComment)
                .put(resourcePath + "/" + postId + "/" + fakeCommentId)
                .then()
                .statusCode(406)
                .body("message", equalTo("Comment could not be updated"))
                .body("error", equalTo("Comment not found"))
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

    @Test(groups = {"create_post", "create_comment"}, priority = 2)
    public void Test_Cant_Delete_Comment_By_Non_Existing_CommentId(){

        given()
                .spec(RequestSpecs.basicToken())
                .delete(resourcePath + "/" + postId + "/" + fakeCommentId)
                .then()
                .statusCode(406)
                .body("message", equalTo("Comment could not be deleted"))
                .body("error", equalTo("Comment not found"))
                .spec(ResponseSpecs.defaultSpec());
    }
}
