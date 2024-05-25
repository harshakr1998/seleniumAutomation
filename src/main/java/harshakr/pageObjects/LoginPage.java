package harshakr.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import harshakr.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent {

	WebDriver driver;

	// This is a constructor
	public LoginPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// The initElements method takes two args, 1 is the driver and the other which
		// points to that driver.
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginButton;
	
	@FindBy(css = "[class*=flyInOut")
	WebElement invalidLoginMessage ;

	public ProductCatalouge login(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginButton.click();
		return new ProductCatalouge(driver);
	}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public String invalidLoginMessage() {
		waitForWebElementToAppear(invalidLoginMessage);
		return invalidLoginMessage.getText();
	}
}
