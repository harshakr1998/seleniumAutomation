@tag
Feature: Purchase Order from a portal
  I want to use this template for my feature file

 Background:
	Given I landed on ecommerce page
 
  @Regression
  Scenario Outline: Positive Test for Submitting the order
    Given Logged in with the username <name> and password <password>
    When I add the product with <productName> to Cart
    And Checkout Submit the Order by filling
    Then "THANKYOU FOR THE ORDER." displayed on ConfirmationPage

    Examples: 
      | name  						|	password 		|	productName 		|
      | john.w@mytest.com | Password@1 	| ZARA COAT 3			|
      | john.w@mytest.com | Password@1 	| ADIDAS ORIGINAL	|