package Android.Activities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import Utilities.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class ShowActivity
{
	private AppiumDriver<MobileElement> mobileDriver;
	private By ratings = By.id("com.tozelabs.tvshowtime:id/constraintLayout");

	CommonUtils utils;

	public ShowActivity(AppiumDriver<MobileElement> mobileDriver) 
	{
		this.mobileDriver = mobileDriver;
		utils = new CommonUtils();
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
	
	public void watchEpisode()
	{
		//Click Watch Btn
		MobileElement watch = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/watchStatus");
		watch.click();
		
		utils.waitForPresence(ratings, mobileDriver);
	}
	
	public boolean episodeRatingsVisible()
	{
		try 
		{
			MobileElement showRatings = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/constraintLayout");
			
			if(showRatings.isDisplayed())
			{
				return true;
			}
			else
			{
				return false;
			}

		} 
		catch (NoSuchElementException e)
		{
			return false;
		}
	}
	
}
