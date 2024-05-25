package harshakr.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import harshakr.AbstractComponents.AbstractComponent;

public class PaymentGateway extends AbstractComponent {

	WebDriver driver;

	//This is a constructor 
	public PaymentGateway(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// The initElements method takes two args, 1 is the driver and the other which
		// points to that driver.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".form-group input")
	WebElement country;
	
	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement userCountry;
	
	@FindBy(css = ".actions a")
	WebElement placeOrderButton;
	
	By countrySearch = By.cssSelector(".ta-results");
	By confirmMessage =  By.id("toast-container");
	
	public void fillUserDetails(String countryName) {
		country.sendKeys(countryName);
		waitForElementToAppear(countrySearch);
		userCountry.click();
	}
	
	public ThankYouPage placeorder() {
		placeOrderButton.click();
		waitForElementToAppear(confirmMessage);
		return new ThankYouPage(driver);
	}
}
