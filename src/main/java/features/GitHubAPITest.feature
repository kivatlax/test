Feature: API Test for GitHub

Scenario: GET Chris Wanstrath's GitHub profile.
Given I send a GET request for user defunkt
Then I get info to confirm Chris Wanstrath is owner of this profile.

Scenario: GET bootstrap repository.
Given I send a GET request for bootstrap repository
Then I get info to confirm bootstrap repository