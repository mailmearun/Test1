Feature: Login Test

@Regression
Scenario: verify Login with valid credentials

Given Launch Orange HRM Application
When User enter credentials
Then User logged in successfully