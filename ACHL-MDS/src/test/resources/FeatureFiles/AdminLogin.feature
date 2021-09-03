
@F1
Feature: Login to MDS Administrator and open Course Management
I want to test MDS admin login 

@F1B1
# Scenario: Login with correct credentials
Background: Login with correct credentials
Given I am on MDS admin login page
When I enter email and password
And I clicked on login button
And User should be logged in to admin

@F1S1
Scenario: Redirect to Course Management page
  
Given I am on MDS admin dashboard page
When I click on Course Management button
Then User is redirected to Course Management page

@F1S2
Scenario: Open Add Course Page

Given I am on the Course Management page
When I click on the Add course button
Then I should be redirected to the Add Course page