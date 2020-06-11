package steps;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;

import Utilities.PropertyFileReader;
import Utilities.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks 
{
	public static WebDriver driver;
	
	private URL gridURL;
	
	TestContext testContext;
	DesiredCapabilities capability;
	PropertyFileReader propertyReader = new PropertyFileReader();
	
	public Hooks(TestContext tc)
	{
        this.testContext = tc;
    }
	
	@Before
	public void openBrowser() throws MalformedURLException, IOException 
	{
		System.out.println("Hook Class: Creating Driver");
		//System.setProperty("browser", "firefox");	
		//driver = WebDriverFactory.createWebDriver();
		
		gridURL = new URL(propertyReader.getPropertyValue("gridUrl"));
		
		capability = new DesiredCapabilities();
		
		if (System.getProperty("browser") != null)
		{
	        capability.setBrowserName(System.getProperty("browser"));
		} 
		else 
		{
		    capability.setBrowserName(propertyReader.getPropertyValue("browser"));
		}
			
		driver = new RemoteWebDriver(gridURL, capability);

		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		testContext.setDriver(driver);
	}

	@After
    public void closeBrowser() 
	{
		if (driver != null)
		{
			System.out.println("Attempting to close the driver");
			driver.quit(); 
		}
	} 
}
