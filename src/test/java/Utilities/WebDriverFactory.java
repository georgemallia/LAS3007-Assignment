package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class WebDriverFactory {
	
	private static String PATH = "src/test/resources/drivers/";

	public static WebDriver createWebDriver() {
		String webdriver = System.getProperty("browser", "firefox");
		switch (webdriver) {
		case "firefox":
			//FirefoxOptions opts = new FirefoxOptions();
			//opts.addArguments("-private");
			System.setProperty("webdriver.gecko.driver", PATH+"geckodriver");
			return new FirefoxDriver();
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