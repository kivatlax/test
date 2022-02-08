Feature: Reqres API Test

Scenario: Validate a user could be inserted, updated, listed and deleted on Reqres API.
Given I create a new user
And I update the user information
And I list the user information
And I delete the user
Then I confirm new user has been deleted.