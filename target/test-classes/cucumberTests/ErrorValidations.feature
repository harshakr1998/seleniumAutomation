
@tag
Feature: Error Validation
  I want to use this template for my feature file
  
   #Background:
#	Given I landed on ecommerce page

  @Regression
  Scenario Outline: Title of your scenario outline
  	Given I landed on ecommerce page
    When Logged in with the username <name> and password <password>
    Then "Incorrect email or password." is displayed

    Examples: 
      | name  						|	password 		|
      | john.w@mytest.com | Password1 	|
