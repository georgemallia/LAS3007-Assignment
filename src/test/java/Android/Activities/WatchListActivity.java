package Android.Activities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import Utilities.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class WatchListActivity
{
	private AppiumDriver<MobileElement> mobileDriver;
	
	private By showlistBy = By.id("com.tozelabs.tvshowtime:id/episodesList");
	
	CommonUtils utils = new CommonUtils();
	
	public WatchListActivity(AppiumDriver<MobileElement> mobileDriver) 
	{
		this.mobileDriver = mobileDriver;
	}
	
	public void navigateToShows()
	{
		//navigate to shows
		MobileElement showsTabBtn = (MobileElement) mobileDriver.findElementByAccessibilityId("Shows");
		showsTabBtn.click();
		
		utils.waitForPresence(showlistBy, mobileDriver);
	}
	
	
	public List<String> getShows()
	{
		List<MobileElement> rtnShows;
		List<String> showNames = new ArrayList<String>();
		
		//elemet that contains all shows 
		MobileElement showsList = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/episodesList");
		
		//show show names list
		rtnShows = showsList.findElements(By.id("com.tozelabs.tvshowtime:id/showName"));
		
		for(MobileElement me : rtnShows)
		{
			showNames.add(me.getText().toLowerCase().trim());
		}
		
		System.out.println("Shows Found: " + showNames.size());
		
		return showNames;
	}

}
