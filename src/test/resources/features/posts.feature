Feature: user posts

  @javaServiceTest
  Scenario: Verify GetPosts service
    Given an api service user
    When user access GetPosts service
    Then user sees posts

  @sanity @apiEndPoint
  Scenario: Verify that get posts end point gives 200 OK response
    Given an media api user
    When user access GetPosts end point
    Then user sees 200 OK response for GetPosts

  @apiEndPoint
  Scenario: Verify that get posts end point request gets posts
    Given an media api user
    When user access GetPosts end point
    Then user sees 100 posts in response

  @apiEndPoint
  Scenario: Verify that get posts end point request gets unique posts
    Given an media api user
    When user access GetPosts end point
    Then user sees all posts has unique id

  @apiEndPoint
  Scenario Outline: Verify that get posts end point request gets expected number of posts for mentioned user id
    Given an media api user
    When user access GetPosts end point for user id <user_id>
    Then user sees <numberOfPosts> posts

    Examples:
      |user_id|numberOfPosts|
      |1      |10           |
      |2      |10           |
      |3      |10           |

  @apiEndPoint
  Scenario: Verify that get posts end point gives 404 not found response for using wrong resource
    Given an media api user
    When user access GetPosts end point with wrong resource
    Then user sees 404 not found response for GetPosts

  @apiEndPoint
  Scenario: Verify that api user is able to create post with 201 Created response
    Given an media api user
    When user access PostPost end point with valid payload
    Then user sees 201 created post response

