package harshakr.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import harshakr.AbstractComponents.AbstractComponent;

public class ThankYouPage extends AbstractComponent {

	WebDriver driver;

	//This is a constructor 
	public ThankYouPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// The initElements method takes two args, 1 is the driver and the other which
		// points to that driver.
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement message;
	
	public String getThankYouMessage() {
		return	message.getText().stripLeading().stripTrailing();
	}
}
