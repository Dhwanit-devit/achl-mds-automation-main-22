@F3
Feature: Create Online Course

@F3S1
Scenario: Open Add Course Page

Given I am on the Course Management page
When I click on the Add course button
Then I should be redirected to the Add Course page

@F3S2
Scenario: Add Jobcode for new course

Given I am on Add course page
When I click on the Add New button
And Add all jobcode details
And Click on submit button
Then New job code is created for the course

@F3S3
Scenario: Select required checkbox for creating course

Given I am on Add course page
When I select all checkbox for member/non-member and course configuration
Then All checkbox should be marked active

@F3S4
Scenario: Enter all required basic tab details for the Online course

Given I am on the add course page
When I enter all required field in the basic tab
And Click on the submit button
Then Course is submitted and details are saved

@F3S5
Scenario: Click on submit button on May we suggest popup

Given May we suggest popup opens
When course is submitted
Then Course details are saved and course is created in Course management