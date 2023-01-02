Feature: user comments

  @javaServiceTest
  Scenario: Verify GetComments service
    Given an api service user
    When user access GetComments service
    Then user sees comments

  @sanity @apiEndPoint
  Scenario: Verify that get comments end point gives 200 OK response
    Given an media api user
    When user access GetComments end point
    Then user sees 200 OK response for Get Comments

  @apiEndPoint
  Scenario: Verify that get comments end point gives 404 not found response for using wrong resource
    Given an media api user
    When user access GetComments end point with wrong resource
    Then user sees 404 not found response for Get Comments

  @apiEndPoint
  Scenario: Verify that get comments end point request gets comments
    Given an media api user
    When user access GetComments end point
    Then user sees 500 comments in response

  @apiEndPoint
  Scenario: Verify that get comments end point request gets unique comments
    Given an media api user
    When user access GetComments end point
    Then user sees all comments has unique id

  @apiEndPoint
  Scenario Outline: Verify that get comments end point request gets expected number of comments for mentioned post id
    Given an media api user
    When user access GetComments end point for post id <post_id>
    Then user sees <numberOfComments> comments

    Examples:
    |post_id|numberOfComments|
    |1      |5               |
    |2      |5               |
    |3      |5               |



  @wireMockTest
  Scenario: Verify comments endpoint mock request gets mock response
    Given an wiremock stub mapping is configure for comments endpoint
    When user access wiremock server with mapping for comments endpoint
    Then user gets 200 ok mock response
