package com.example.demoJP.stepDefinitions;

import com.example.demoJP.model.comments.Comment;
import com.example.demoJP.model.posts.Post;
import com.example.demoJP.service.GetCommentsService;
import com.example.demoJP.service.GetPostsService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.Arrays;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class PostsSteps {
    Response getPostResponse,getPostsPerUserResponse;
    Post[] posts, postsPerUser;
    RequestSpecification getPostsPerUserRequest;


    private GetPostsService getPostsService = new GetPostsService();


    @When("user access GetPosts service")
    public void user_access_getposts_service() {

        posts = getPostsService.getPosts();
    }

    @Then("user sees posts")
    public void user_sees_posts() {

        Assert.assertEquals(true, posts.length > 0);

    }

    @When("user access GetPosts end point")
    public void user_access_getposts_end_point() {
        getPostResponse = given().get("https://jsonplaceholder.typicode.com/posts");
    }

    @When("user access GetPosts end point with wrong resource")
    public void user_access_getposts_endpoint_with_wrong_resource(){
        getPostResponse = given().get("https://jsonplaceholder.typicode.com/wrongposts");
    }

    @Then("user sees 200 OK response for GetPosts")
    public void user_sees_200_OK_reponse_for_get_posts() {
        Assert.assertEquals(200, getPostResponse.getStatusCode());
    }

    @Then("user sees 100 posts in response")
    public  void user_sees_posts_in_response(){
        Assert.assertEquals(100,getPostResponse.as(Post[].class).length);
    }

    @Then("user sees 404 not found response for GetPosts")
    public void user_sees_404_not_found_response_for_getposts(){
        Assert.assertEquals(404,getPostResponse.statusCode());
    }

    @Then("user sees all posts has unique id")
    public void user_sees_all_posts_has_unique_id(){
        posts = getPostResponse.as(Post[].class);
        Assert.assertEquals(true, Arrays.stream(posts).collect(Collectors.groupingBy(s -> s.id)).size()==posts.length);


    }

    @When("user access GetPosts end point for user id {int}")
    public void user_access_get_posts_end_point_for_user_id_specified(int userId){
        getPostsPerUserRequest = given().queryParam("userId",userId);

        String url = "https://jsonplaceholder.typicode.com/posts";

        getPostsPerUserResponse =getPostsPerUserRequest.get(url);
    }

    @Then("user sees {int} posts")
    public void user_sees_number_of_posts_per_userId(int numberOfPosts){
        postsPerUser = getPostsPerUserResponse.as(Post[].class);
        Assert.assertEquals(numberOfPosts, postsPerUser.length);
    }
}
