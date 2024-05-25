package harshakr.tests;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import harshakr.pageObjects.MyCart;
import harshakr.pageObjects.OrdersPage;
import harshakr.pageObjects.PaymentGateway;
import harshakr.pageObjects.ProductCatalouge;
import harshakr.pageObjects.ThankYouPage;
import harshakr.testComponents.BaseTest;

public class HashMapDataTest extends BaseTest {

	String productName;

	@Test(dataProvider = "getData", groups = { "DataSet" })
	public void submitOrder(HashMap<String, String> data) throws InterruptedException {

		String finalMessage = "THANKYOU FOR THE ORDER.";

		ProductCatalouge productCatalouge = loginPage.login(data.get("email"), data.get("password"));
		productCatalouge.addToCart(data.get("product"));

		MyCart mycart = productCatalouge.checkoutCart();
		boolean value = mycart.getCartList(productName);
		Assert.assertFalse(value);
		PaymentGateway payment = mycart.checkout();

		payment.fillUserDetails(data.get("country"));
		ThankYouPage thankyou = payment.placeorder();

		String message = thankyou.getThankYouMessage();
		System.out.println(message);
		Assert.assertEquals(message, finalMessage);
	}

	@Test
	public void verifyOrder(String email, String password) {
		ProductCatalouge productCatalouge = loginPage.login(email, password);
		OrdersPage ordersPage = productCatalouge.ordersPage();
		Assert.assertTrue(ordersPage.getOrderedProduct(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		java.util.List<HashMap<String, String>> data = getJsonData(
				System.getProperty("user.dir") + "\\src\\test\\java\\harshakr\\data\\data.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

	}

//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "john.w@mytest.com");
//	map.put("password", "Password@1");
//	map.put("product", "ADIDAS ORIGINAL");
//	map.put("country", "India");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email", "john.w@mytest.com");
//	map1.put("password", "Password@1");
//	map1.put("product", "ZARA COAT 3");
//	map1.put("country", "India");

}
