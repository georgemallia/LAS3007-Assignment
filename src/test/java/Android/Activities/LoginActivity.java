package Android.Activities;

import org.openqa.selenium.By;

import Utilities.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginActivity 
{
	private AppiumDriver<MobileElement> mobileDriver;
	
	private By signinBy = By.id("com.tozelabs.tvshowtime:id/btLogin");
	private By signLabelBy = By.id("com.tozelabs.tvshowtime:id/toolbar");
	private By btNavBy = By.id("com.tozelabs.tvshowtime:id/bottomNavigation");
	
	CommonUtils utils = new CommonUtils();
	
	public LoginActivity(AppiumDriver<MobileElement> mobileDriver)
	{
		this.mobileDriver = mobileDriver;
	}
	
	public void signinProcess(String username, String password)
	{
		utils.waitForPresence(signinBy, mobileDriver);
		
		//sign in Button
		MobileElement signinBtn = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/btLogin");
		signinBtn.click();

		//wait for username field to be present
		utils.waitForPresence(signLabelBy, mobileDriver);
		
		//username field
		MobileElement usernameFld = (MobileElement) mobileDriver.findElementByAccessibilityId("usernameOrEmailInput");
		usernameFld.sendKeys(username);

		//password Field
		MobileElement passwordFld = (MobileElement) mobileDriver.findElementByAccessibilityId("passwordInput");
		passwordFld.sendKeys(password);

		//sign in button
		MobileElement finalSigninBtn = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/sign_in_button");
		finalSigninBtn.click();
		
		//Wait for watchlist tab to be visible
		utils.waitForPresence(btNavBy, mobileDriver);
	}
	

}