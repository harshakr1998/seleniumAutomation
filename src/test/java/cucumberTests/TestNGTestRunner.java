package cucumberTests;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/cucumberTests", glue = "harshakr.stepDefinitions", 
monochrome = true, tags= "@Regression", plugin = {"html: taget/cucmber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
