import helpers.DataHelper;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import model.Comment;
import model.Post;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import specifications.RequestSpecs;

import static io.restassured.RestAssured.given;

public class BaseTest {

    private static String postResourcePath = "/v1/post";
    protected static Integer postId = 0;

    private static String commentResourcePath = "/v1/comment";
    protected static Integer commentId = 0;

    @Parameters("baseUrl")
    @BeforeClass
    public void setUp(@Optional("http://localhost:9000") String baseUrl){

        RestAssured.baseURI = baseUrl;
    }

    @BeforeGroups("create_post")
    public void createPost(){

        Post testPost = new Post(DataHelper.generateRandomTitle(), DataHelper.generateRandomContent());

        Response response = given()
                .spec(RequestSpecs.generateToken())
                .body(testPost)
                .post(postResourcePath);

        JsonPath jsonPathEvaluator = response.jsonPath();
        postId = jsonPathEvaluator.get("id");
    }

    @BeforeGroups("create_comment")
    public void createComment(){

        Comment testComment = new Comment(DataHelper.generateRandomName(), DataHelper.generateRandomComment());

        Response response = given()
                .spec(RequestSpecs.basicToken())
                .body(testComment + "/" + postId)
                .post(commentResourcePath);

        JsonPath jsonPathEvaluator = response.jsonPath();
        commentId = jsonPathEvaluator.get("id");
    }
}
