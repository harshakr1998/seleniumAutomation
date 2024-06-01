package harshakr.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import harshakr.pageObjects.MyCart;
import harshakr.pageObjects.PaymentGateway;
import harshakr.pageObjects.ProductCatalouge;
import harshakr.pageObjects.ThankYouPage;
import harshakr.testComponents.BaseTest;

public class ErrorValidations extends BaseTest {

	@Test(groups= {"ErrorValidation"})
	public void InvalidLoginValidation() throws InterruptedException {

		loginPage.login("john.w@mytest.com", "Password");
		Assert.assertEquals("Incorrect email or password.", loginPage.invalidLoginMessage());

	}
}
