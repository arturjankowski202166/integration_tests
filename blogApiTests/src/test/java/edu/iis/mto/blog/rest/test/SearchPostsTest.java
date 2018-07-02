package edu.iis.mto.blog.rest.test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

public class SearchPostsTest {
    private static final String POST_BY_USER_URL = "/blog/user/4/post";
    private static final String URL_OF_USER_WITHOT_POSTS = "/blog/user/2/post";
    private static final String REMOVED_USER_URL = "/blog/user/5/post";

    @Test
    public void shouldReturnCorrectNumberOfPostsWhenValidUserIsProvided() {
        JSONObject jsonObj = new JSONObject();
        RestAssured.given().accept(ContentType.JSON).header("Content-Type", "application/json;charset=UTF-8").body(jsonObj.toString()).expect().log().all().statusCode(HttpStatus.SC_OK).and().body("size()", is(1)).when().get(POST_BY_USER_URL);
    }

    @Test
    public void shouldReturnNothingWhenUserWithNoPostsIsProvided() {
        JSONObject jsonObj = new JSONObject();
        RestAssured.given().accept(ContentType.JSON).header("Content-Type", "application/json;charset=UTF-8").body(jsonObj.toString()).expect().log().all().statusCode(HttpStatus.SC_OK).and().body("size()", is(0)).when().get(URL_OF_USER_WITHOT_POSTS);
    }

    @Test
    public void shouldReturnBadRequestWhenRemovedUserIsProvided() {
        JSONObject jsonObj = new JSONObject();
        RestAssured.given().accept(ContentType.JSON).header("Content-Type", "application/json;charset=UTF-8").body(jsonObj.toString()).expect().log().all().statusCode(HttpStatus.SC_BAD_REQUEST).when().get(REMOVED_USER_URL);
    }
}
