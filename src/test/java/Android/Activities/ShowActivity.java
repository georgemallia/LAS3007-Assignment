package Android.Activities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ShowActivity
{
	private AppiumDriver<MobileElement> mobileDriver;
	
	public ShowActivity(AppiumDriver<MobileElement> mobileDriver) 
	{
		this.mobileDriver = mobileDriver;
	}
	
	public String addShow()
	{
		//click the add show button
		MobileElement el11 = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/btAction");
		el11.click();

		//assert that ADDED!
		MobileElement addedBtn = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/btActionText");
		
		//should return ADDED!
		return addedBtn.getText();
	}
	
	public void closeShow()
	{
		//To close the show activity
		MobileElement closeBtn = (MobileElement) mobileDriver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Close\"]");
		closeBtn.click();

		//close the search activity
		MobileElement closeSearchBtn = (MobileElement) mobileDriver.findElementByXPath("//android.widget.ImageButton[@content-desc=\"Close\"]");
		closeSearchBtn.click();
	}
	
}
