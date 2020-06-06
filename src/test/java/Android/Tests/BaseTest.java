package Android.Tests;

import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_ACTIVITY;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.APP_PACKAGE;
import static io.appium.java_client.remote.AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS;
import static io.appium.java_client.remote.MobileCapabilityType.AUTOMATION_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.DEVICE_NAME;
import static io.appium.java_client.remote.MobileCapabilityType.PLATFORM_VERSION;
import static io.appium.java_client.remote.MobileCapabilityType.UDID;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

import java.net.URL;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import Utilities.PropertyFileReader;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class BaseTest 
{
	private static DesiredCapabilities dc;
	private static URL url;
	
	protected AppiumDriver<MobileElement> driver;
	
	static PropertyFileReader propertyReader = new PropertyFileReader();
	
	@BeforeAll
	public static void prepareCapabilities() throws Exception 
	{
		dc = new DesiredCapabilities();
		dc.setCapability(PLATFORM_NAME, propertyReader.getPropertyValue("platform_name"));
		dc.setCapability(PLATFORM_VERSION, propertyReader.getPropertyValue("platform_version"));
		dc.setCapability(DEVICE_NAME, propertyReader.getPropertyValue("device_name"));
		dc.setCapability(UDID, propertyReader.getPropertyValue("udid"));
		dc.setCapability(AUTOMATION_NAME, propertyReader.getPropertyValue("automation_name"));
		dc.setCapability(AUTO_GRANT_PERMISSIONS, propertyReader.getPropertyValue("auto_grant_permissions"));
		dc.setCapability(APP_PACKAGE, propertyReader.getPropertyValue("app_package"));
		dc.setCapability(APP_ACTIVITY, propertyReader.getPropertyValue("app_activity"));

		url = new URL(propertyReader.getPropertyValue("url"));
	}

	@BeforeEach
	public void setUp() 
	{
		driver = new AndroidDriver<>(url, dc);
	}
	
	@AfterEach
	public void tearDown() 
	{
		driver.quit();
	}
	
	
	
}
