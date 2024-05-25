package harshakr.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import harshakr.AbstractComponents.AbstractComponent;

public class MyCart extends AbstractComponent{

	WebDriver driver;

	//This is a constructor 
	public MyCart(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// The initElements method takes two args, 1 is the driver and the other which
		// points to that driver.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartList;
	
	@FindBy(css = ".totalRow button")
	WebElement checkout;
	
	public boolean getCartList(String productName) {
		 boolean match = cartList.stream().anyMatch(product->product.getText().equals(productName));
		 return match;
	}
	
	public PaymentGateway checkout() {
		System.out.println(checkout.isDisplayed());
		checkout.click();	
		return new PaymentGateway(driver);
	}
}
