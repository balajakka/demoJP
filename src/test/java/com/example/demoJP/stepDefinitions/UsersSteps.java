package com.example.demoJP.stepDefinitions;

import com.example.demoJP.model.comments.Comment;
import com.example.demoJP.model.posts.Post;
import com.example.demoJP.model.users.User;
import com.example.demoJP.service.GetCommentsService;
import com.example.demoJP.service.GetUsersService;
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

public class UsersSteps {
    Response getUserResponse,postUserResponse;
    User[] users;
    RequestSpecification getCommentRequest,postUserRequestSpec;
    File userCreationJsonPayloadFile = new File("./src/test/resources/testData/user.json");


    private GetUsersService getUsersService = new GetUsersService();


    @When("user access GetUsers service")
    public void user_access_getusers_service(){

        users = getUsersService.getUsers();
    }

    @Then("user sees users detail")
    public void user_sees_users_detail(){

        Assert.assertEquals(true, users.length>0);

    }

    @When("user access GetUsers end point")
    public void user_access_get_users_end_point() {
        getUserResponse = given().get("https://jsonplaceholder.typicode.com/users");
    }

    @When("user access GetUsers end point with wrong resource")
    public void user_access_getusers_endpoint_with_wrong_resource(){
        getUserResponse = given().get("https://jsonplaceholder.typicode.com/wrongusers");
    }

    @Then("user sees 200 OK response for GetUsers")
    public void user_sees_200_OK_reponse_for_get_users() {
        Assert.assertEquals(200, getUserResponse.getStatusCode());
    }

    @Then("user sees 10 users in response")
    public void user_sees_users_in_response() {
        Assert.assertEquals(10, getUserResponse.as(User[].class).length);
    }

    @Then("user sees 404 not found response for GetUsers")
    public void user_sees_404_not_found_response_for_getusers(){
        Assert.assertEquals(404,getUserResponse.statusCode());
    }
    @Then("user sees all users has unique id")
    public void user_sees_all_users_has_unique_id(){
        users = getUserResponse.as(User[].class);
        Assert.assertEquals(true, Arrays.stream(users).collect(Collectors.groupingBy(s -> s.id)).size()==users.length);
    }

    @When("user access PostUser end point with valid payload")
    public void user_access_post_user_endpoint_with_valid_payload(){
        postUserRequestSpec = given().body(userCreationJsonPayloadFile)
                                  .contentType("ContentType.JSON");
        postUserResponse = postUserRequestSpec.post("https://jsonplaceholder.typicode.com/users");

    }

    @Then("user sees 201 created user response")
    public void user_sees_201_created_user_response(){

        Assert.assertEquals(201,postUserResponse.statusCode());
    }
}
