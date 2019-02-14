Feature: Login Test

Scenario: verify Login with valid credentials
Given Launch Orange HRM Application
When User enter "Admin" and "password123"
Then User logged in successfully


@Sanity
Scenario Outline: verify Login with valid credentials
Given Launch Orange HRM Application
When User enter credentials "<UserName>" and "<Password>"
Then User logged in successfully

Examples:
|UserName|Password|
|Admin1|Password123|
|Admin2|Password456|

@Regression@Sanity
Scenario: verify Login with valid credentials

Given Launch Orange HRM Application
When User enter userID and password
|Admin3|Password101|
Then User logged in successfully