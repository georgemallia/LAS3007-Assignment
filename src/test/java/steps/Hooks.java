package steps;

import org.openqa.selenium.WebDriver;

import Utilities.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks 
{
	public static WebDriver driver;
	TestContext testContext;
	
	public Hooks(TestContext tc)
	{
        this.testContext = tc;
    }
	
	@Before
	public void openBrowser() 
	{
		System.out.println("Hook Class: Creating Driver");
		System.setProperty("browser", "chrome");	
		driver = WebDriverFactory.createWebDriver();
		testContext.setDriver(driver);
	}

	@After
    public void closeBrowser() 
	{
		if (driver != null)
		{
			driver.close(); 
		}
	} 
}
