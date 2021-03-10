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

public class PostTests extends BaseTest{
    private static String resourcePath = "/v1/post";
    private static Integer createdPost = 0;

    @BeforeGroups("create_post")
    public void createArticle(){

        Article testArticle = new Article(DataHelper.generateRandomTitle(), DataHelper.generateRandomContent());
        Post testPost = new Post(DataHelper.generateRandomTitle(), DataHelper.generateRandomContent());

        Response response = given()
                .spec(RequestSpecs.generateToken())
                .body(testPost)
                .post(resourcePath);

        JsonPath jsonPathEvaluator = response.jsonPath();
        createdPost = jsonPathEvaluator.get("id");
    }

    @Test
    public void Test_Create_Post_Success(){

        Post testPost = new Post(DataHelper.generateRandomTitle(), DataHelper.generateRandomContent());

        given()
                .spec(RequestSpecs.generateToken())
                .body(testPost)
                .post(resourcePath)
                .then()
                .statusCode(200)
                .spec(ResponseSpecs.defaultSpec());
    }
}
