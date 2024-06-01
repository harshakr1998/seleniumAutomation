package harshakr.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;

import harshakr.pageObjects.LoginPage;
import harshakr.pageObjects.MyCart;
import harshakr.pageObjects.PaymentGateway;
import harshakr.pageObjects.ProductCatalouge;
import harshakr.pageObjects.ThankYouPage;
import harshakr.testComponents.BaseTest;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.And.Ands;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LoginPage loginPage;
	public ProductCatalouge productCatalouge;
	public MyCart mycart;
	public PaymentGateway payment;
	public ThankYouPage thankyou;

	@Given("I landed on ecommerce page")
	public void I_landed_on_ecommerce_page() throws IOException {
		loginPage = launchApplication();
	}

	@Given("^Logged in with the username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalouge = loginPage.login(username, password);
	}

	@When("^I add the product with (.+) to Cart$")
	public void i_add_the_product_to_Cart(String productName) throws InterruptedException {
		productCatalouge.addToCart(productName);
		mycart = productCatalouge.checkoutCart();
		boolean value = mycart.getCartList(productName);
		Assert.assertTrue(value);
	}

	@And("Checkout Submit the Order by filling")
	public void checkout_and_submit_the_order() {
		payment = mycart.checkout();
		payment.fillUserDetails("India");
		thankyou = payment.placeorder();
	}

	@Then("{string} displayed on ConfirmationPage")
	public void message_displayed_on_confirmationPage(String string) {
		String message = thankyou.getThankYouMessage();
		Assert.assertEquals(message, string);
		driver.close();
	}
	
	@Then("{string} is displayed")
	public void error_message_is_displayed(String message) {
		Assert.assertEquals(message, loginPage.invalidLoginMessage());
		driver.close();
	}
}
