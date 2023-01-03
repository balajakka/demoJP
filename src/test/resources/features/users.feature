Feature: user details

  @javaServiceTest
  Scenario: Verify GetUsers service
    Given an api service user
    When user access GetUsers service
    Then user sees users detail

  @sanity @apiEndPoint
  Scenario: Verify that get users end point gives 200 OK response
    Given an media api user
    When user access GetUsers end point
    Then user sees 200 OK response for GetUsers

  @apiEndPoint
  Scenario: Verify that get users end point gives 404 not found response for using wrong resource
    Given an media api user
    When user access GetUsers end point with wrong resource
    Then user sees 404 not found response for GetUsers

  @apiEndPoint
  Scenario: Verify that get users end point request gets users
    Given an media api user
    When user access GetUsers end point
    Then user sees 10 users in response

  @apiEndPoint
  Scenario: Verify that get users end point request gets unique users
    Given an media api user
    When user access GetUsers end point
    Then user sees all users has unique id

  @apiEndPoint
  Scenario: Verify that api user is able to create user with 201 Created response
    Given an media api user
    When user access PostUser end point with valid payload
    Then user sees 201 created user response

