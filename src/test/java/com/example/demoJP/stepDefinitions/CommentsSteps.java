package com.example.demoJP.stepDefinitions;

import com.example.demoJP.model.comments.Comment;
import com.example.demoJP.service.GetCommentsService;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;


public class CommentsSteps {
    Response getCommentResponse, getCommentsPerPostResponse,postCommentResponse,mockResponse;
    Comment[] comments,commentsPerPost;
    RequestSpecification getCommentPerPostRequest,postCommentRequestSpec;
    File commentCreationJsonPayloadFile = new File("./src/test/resources/testData/comment.json");



    private GetCommentsService getCommentsService = new GetCommentsService();

    @Given("an api service user")
    public void an_api_service_user(){

    }

    @When("user access GetComments service")
    public void user_access_getcomments_service(){

        comments = getCommentsService.getComments();

    }

    @Then("user sees comments")
    public void user_sees_comments(){

        Assert.assertEquals(true, comments.length>0);

    }

    @Given("an media api user")
    public void an_media_api_user(){

    }

    @When("user access GetComments end point")
    public void user_access_getcomments_end_point(){
        getCommentResponse = given().get("https://jsonplaceholder.typicode.com/comments");

    }

    @When("user access GetComments end point with wrong resource")
    public void user_access_getcomments_endpoint_with_wrong_resource(){
        getCommentResponse = given().get("https://jsonplaceholder.typicode.com/wrongcomments");
    }

    @Then("user sees 200 OK response for Get Comments")
    public  void user_sees_200_OK_reponse_for_get_comments(){
        Assert.assertEquals(200,getCommentResponse.getStatusCode());
    }

    @Then("user sees 500 comments in response")
    public  void user_sees_comments_in_response(){
        Assert.assertEquals(500,getCommentResponse.as(Comment[].class).length);
    }

    @Then("user sees 404 not found response for Get Comments")
    public void user_sees_404_not_found_response_for_get_comments(){
        Assert.assertEquals(404,getCommentResponse.statusCode());
    }

    @Given("an wiremock stub mapping is configure for comments endpoint")
    public void an_wiremock_stub_mapping_is_configure_for_comments_endpoint(){
//        String url = "https://jsonplaceholder.typicode.com/mockResource";
//        String url = "http://localhost:8080/mockResource";
        String url = "/mockResource";
        ResponseDefinitionBuilder mockResponsebuild = new ResponseDefinitionBuilder();
        mockResponsebuild.withStatus(200);
        WireMock.givenThat(WireMock.get(url).willReturn(mockResponsebuild));

    }

    @When("user access wiremock server with mapping for comments endpoint")
    public void user_access_wiremock_server_with_mapping_for_comments_endpoint(){
        String url = "http://localhost:8080/mockResource";
        mockResponse = given().get(url);

    }

    @Then("user gets 200 ok mock response")
    public void user_gets_200_ok_mock_response(){

        Assert.assertEquals(200, mockResponse.getStatusCode());
    }

    @Then("user sees all comments has unique id")
    public void user_sees_all_comments_has_unique_id(){
        comments = getCommentResponse.as(Comment[].class);
        Assert.assertEquals(true, Arrays.stream(comments).collect(Collectors.groupingBy(s -> s.id)).size()==comments.length);


    }

    @When("user access GetComments end point for post id {int}")
    public void user_access_getcomments_end_point_for_post_id(int postId){

        getCommentPerPostRequest = given().queryParam("postId",postId);

        String url = "https://jsonplaceholder.typicode.com/comments";

        getCommentsPerPostResponse =getCommentPerPostRequest.get(url);

    }

    @Then("user sees {int} comments")
    public void user_sees_number_of_comments(int numberOfComments){
        commentsPerPost = getCommentsPerPostResponse.as(Comment[].class);
        Assert.assertEquals(numberOfComments, commentsPerPost.length);
    }

    @When("user access PostComment end point with valid payload")
    public void user_access_post_comment_endpoint_with_valid_payload(){
        postCommentRequestSpec = given().body(commentCreationJsonPayloadFile)
                .contentType("ContentType.JSON");
        postCommentResponse = postCommentRequestSpec.post("https://jsonplaceholder.typicode.com/comments");

    }

    @Then("user sees 201 created comment response")
    public void user_sees_201_created_comment_response(){

        Assert.assertEquals(201,postCommentResponse.statusCode());
    }

}
