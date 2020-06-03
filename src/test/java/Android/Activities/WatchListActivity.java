package Android.Activities;

import java.util.List;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WatchListActivity
{
	private AppiumDriver<MobileElement> mobileDriver;
	
	public WatchListActivity(AppiumDriver<MobileElement> mobileDriver) 
	{
		this.mobileDriver = mobileDriver;
	}
	
	public void navigateToShows()
	{
		//navigate to shows
		MobileElement showsTabBtn = (MobileElement) mobileDriver.findElementByAccessibilityId("Shows");
		showsTabBtn.click();
	}
	
	public List<MobileElement> getShows()
	{
		List<MobileElement> rtnShows;
		
		//elemet that contains all shows 
		MobileElement showsList = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/episodesList");
		
		//TODO: GET SHOWS IDENTIFIER FROM SHOWS TAB UNDER THE: com.tozelabs.tvshowtime:id/episodesList ELEMENT
		rtnShows = showsList.findElements(By.id(""));
		
		return rtnShows;
	}

}
