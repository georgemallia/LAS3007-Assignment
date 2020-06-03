package Android.Activities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class LoginActivity 
{

	private AppiumDriver<MobileElement> mobileDriver;
	
	public LoginActivity(AppiumDriver<MobileElement> mobileDriver)
	{
		this.mobileDriver = mobileDriver;
	}
	
	public void signinProcess(String username, String password)
	{
		//sign in Button
		MobileElement signinBtn = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/btLogin");
		signinBtn.click();

		//username field
		MobileElement usernameFld = (MobileElement) mobileDriver.findElementByAccessibilityId("usernameOrEmailInput");
		usernameFld.sendKeys(username);

		//password Field
		MobileElement passwordFld = (MobileElement) mobileDriver.findElementByAccessibilityId("passwordInput");
		passwordFld.sendKeys(password);

		//sign in button
		MobileElement finalSigninBtn = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/sign_in_button");
		finalSigninBtn.click();
	}
	

}
