package harshakr.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import harshakr.AbstractComponents.AbstractComponent;

public class ProductCatalouge extends AbstractComponent {

	WebDriver driver;
	
	//This is a constructor
	public ProductCatalouge(WebDriver driver) {
		super(driver);
		this.driver = driver;
		// The initElements method takes two args, 1 is the driver and the other which
		// points to that driver.
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='card']")
	List <WebElement> productsList;
	
	@FindBy(className = "ng-animating")
	WebElement animation; 
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cart; 
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement ordersPage; 
	

	By productEle = By.id("toast-container");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.id("toast-container");
	
	
	public List<WebElement> getProductsList() {
		waitForElementToAppear(productEle);
		return productsList;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductsList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	public void addToCart(String productName){
		WebElement product = getProductByName(productName);
		product.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
	//	Thread.sleep(2000);
		waitForElementToDisappear(animation);
	}
	
	public MyCart checkoutCart() throws InterruptedException {
		cart.click();
		Thread.sleep(2000);
		return new MyCart(driver);
	}
	
	public OrdersPage ordersPage() {
		ordersPage.click();
		return new OrdersPage(driver);
	}
	
}
