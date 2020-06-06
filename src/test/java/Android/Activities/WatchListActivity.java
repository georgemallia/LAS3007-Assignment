package Android.Activities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.interactions.touch.TouchActions;

import Utilities.CommonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

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
	
	public void openShowEpisode(String showName)
	{
		List<MobileElement> rtnShows = new ArrayList<MobileElement>();
		List<MobileElement> episodeNames = new ArrayList<MobileElement>();
		List<String> showNames = new ArrayList<String>();

		MobileElement showTab;
		
		//elemet that contains all shows 
		MobileElement showsList = (MobileElement) mobileDriver.findElementById("com.tozelabs.tvshowtime:id/episodesList");
		List<MobileElement> listWrapper = showsList.findElements(By.id("com.tozelabs.tvshowtime:id/episodeWatchWrapper")); 
		
		for(MobileElement el : listWrapper)
		{
			rtnShows.add(el.findElement(By.id("com.tozelabs.tvshowtime:id/showName")));
			episodeNames.add(el.findElement(By.id("com.tozelabs.tvshowtime:id/episodeName")));
		}
		
		System.out.println("rtnShows.size: " + rtnShows.size());
		System.out.println("episodeNames.size: " + episodeNames.size());

		for(MobileElement me : rtnShows)
		{
			System.out.println("ME.getText: " + me.getText().toLowerCase().trim());
			System.out.println("showname: " + showName);
			
			if(me.getText().toLowerCase().trim().equals(showName.toLowerCase().trim()))
			{
				int el = rtnShows.indexOf(me);
				episodeNames.get(el).click();
				return;
			}
		}
	}
	
	public MobileElement scrollWatchList(String term)
	{
		return ((AndroidDriver<MobileElement>) mobileDriver)
			.findElementByAndroidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true).instance(0))"
					+ ".scrollIntoView(new UiSelector().textContains(\""+term+"\").instance(0))");
	}
}
