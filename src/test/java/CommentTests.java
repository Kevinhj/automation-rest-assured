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
    protected static Integer commentId = 0;

    @Test(groups = {"create_post"}, priority = 1)
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

    //Negative test -> create a comment for a non existing post
}
