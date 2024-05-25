package harshakr.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import harshakr.pageObjects.MyCart;
import harshakr.pageObjects.OrdersPage;
import harshakr.pageObjects.PaymentGateway;
import harshakr.pageObjects.ProductCatalouge;
import harshakr.pageObjects.ThankYouPage;
import harshakr.testComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {

	String productName = "ADIDAS ORIGINAL";

	@Test(dataProvider = "getData", groups = {"DataSet"})
	public void submitOrder(String email, String password, String productName, String countryName) throws InterruptedException {

		String finalMessage = "THANKYOU FOR THE ORDER.";

		ProductCatalouge productCatalouge = loginPage.login(email, password);
		productCatalouge.addToCart(productName);

		MyCart mycart = productCatalouge.checkoutCart();
		boolean value = mycart.getCartList(productName);
		Assert.assertTrue(value);
		PaymentGateway payment = mycart.checkout();

		payment.fillUserDetails(countryName);
		ThankYouPage thankyou = payment.placeorder();

		String message = thankyou.getThankYouMessage();
		Assert.assertEquals(message, finalMessage);
	}

	@Test
	public void verifyOrder(String email, String password) {
		ProductCatalouge productCatalouge = loginPage.login(email, password);
		OrdersPage ordersPage = productCatalouge.ordersPage();
		Assert.assertTrue(ordersPage.getOrderedProduct(productName));

	}
	
	
	@DataProvider
	public Object[][] getData() {
		return new Object [][] {{"john.w@mytest.com","Password@1","ADIDAS ORIGINAL","India"},{"john.w@mytest.com","Password@1","ZARA COAT 3","India"}};
				
	}
}
