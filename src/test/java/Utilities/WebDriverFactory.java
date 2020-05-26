package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory 
{
	private static String PATH = "src/test/resources/drivers/";
	static WebDriver driver = null;
	
	public static WebDriver createWebDriver() 
	{
		String webdriver = System.getProperty("browser", "firefox");
		switch (webdriver)
		{
			case "firefox":
				System.setProperty("webdriver.gecko.driver", PATH+"geckodriver");
				if(driver == null || ((RemoteWebDriver)driver).getSessionId() == null)
				{
					return new FirefoxDriver();
				}
				else
				{
					return driver;
				}

			case "chrome":
				System.setProperty("webdriver.chrome.driver", PATH+"chromedriver");
				return new ChromeDriver();
			case "edge":
				System.setProperty("webdriver.edge.driver", PATH+"msedgedriver.exe");
				return new EdgeDriver();
			default:
				throw new RuntimeException("Unsupported webdriver: " + webdriver);
		}
	}
}