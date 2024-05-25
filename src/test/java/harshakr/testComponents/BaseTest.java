package harshakr.testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import harshakr.pageObjects.LoginPage;
import harshakr.pageObjects.ProductCatalouge;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;

	public LoginPage loginPage;

	String path = System.getProperty("user.dir") + "\\src\\main\\java\\harshakr\\resources\\GlobalData.properties";

	public WebDriver initailizeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(path);
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions cop = new ChromeOptions();
			if (browserName.contains("headless")) {
				cop.addArguments("headless");
			}
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(cop);
			driver.manage().window().setSize(new Dimension(1440, 900)); // Full Screen mode
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver = initailizeDriver();
		driver.get(path);

		loginPage = new LoginPage(driver);
		loginPage.goTo();

		return new LoginPage(driver);

	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dst = new File(System.getProperty("user.dir") + "\\screenshots\\" + testCaseName + ".png");
		FileUtils.copyFile(src, dst);
		return System.getProperty("user.dir") + "\\screenshots\\" + testCaseName + ".png";
	}

	public List<HashMap<String, String>> getJsonData(String filePath) throws IOException {
		// Read json file to String
		String jsonData = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to Json using Jackson Data bind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonData,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

}
