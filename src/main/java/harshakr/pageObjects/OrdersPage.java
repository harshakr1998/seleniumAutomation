package harshakr.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import harshakr.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{

	WebDriver driver;
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> orderedProductName;
	
	public boolean getOrderedProduct(String productName) {
	 boolean match = orderedProductName.stream().anyMatch(prd->prd.getText().equals(productName));
	 return match;
	}

}
