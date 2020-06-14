package Android.Activities;

import org.openqa.selenium.By;

import Utilities.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginActivity 
{
	private AppiumDriver<MobileElement> mobileDriver;
	
	private By popupBy = By.id("com.tozelabs.tvshowtime:id/md_buttonDefaultPositive");
	private By signLabelBy = By.id("com.tozelabs.tvshowtime:id/toolbar");
	private By btNavBy = By.id("com.tozelabs.tvshowtime:id/bottomNavigation");
	
	CommonUtils utils = new CommonUtils();
	
	public LoginActivity(AppiumDriver<MobileElement> mobileDriver)
	{
		this.mobileDriver = mobileDriver;
	}
	
	public void signinProcess(String username, String password)
	{
		utils.waitForPresence(popupBy, mobileDriver);
		
		MobileElement popup = (MobileElement) mobileDriver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup");
		
		if(popup.isDisplayed())
		{
			MobileElement el1 = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/md_buttonDefaultPositive");
			el1.click();
		}

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
