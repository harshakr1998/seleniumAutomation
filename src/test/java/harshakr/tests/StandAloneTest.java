package harshakr.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import harshakr.pageObjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String [] args) throws InterruptedException {
		
		String productName = "ADIDAS ORIGINAL";	
		String countryName = "India";
		String finalMessage = "THANKYOU FOR THE ORDER.";
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("john.w@mytest.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@1");
		driver.findElement(By.id("login")).click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		List <WebElement> productsList = driver.findElements(By.xpath("//div[@class='card']"));
		
		WebElement prod	= productsList.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
		
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		List<WebElement> cartList =	driver.findElements(By.cssSelector(".cartSection h3"));
		 boolean match = cartList.stream().anyMatch(product->product.getText().equals(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
	 driver.findElement(By.cssSelector(".form-group input")).sendKeys(countryName);
	
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	 
	 driver.findElement(By.cssSelector(".ta-item:nth-of-type(2)")).click();
	 
	 driver.findElement(By.cssSelector(".actions a")).click();
	 
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	 
	 String message =   driver.findElement(By.cssSelector(".hero-primary")).getText().stripLeading().stripTrailing();
	 Assert.assertEquals(message, finalMessage);
	 
	driver.close();
		
		
		
		
	}
}
 