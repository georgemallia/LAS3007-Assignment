package steps;

import org.openqa.selenium.WebDriver;

public class TestContext 
{
	public static WebDriver driver;
	
	public WebDriver getDriver()
	{
		System.out.println("Test Context class: Returning Driver");
		return driver;
	}

	public void setDriver(WebDriver driver)
	{
		System.out.println("Test Context class: setting Driver");
		TestContext.driver = driver;
	}

	public TestContext() 
	{
	
	}
}
