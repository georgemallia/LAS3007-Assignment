package Android.Tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Android.Activities.LoginActivity;
import Android.Activities.SearchActivity;
import Android.Activities.ShowActivity;
import Android.Activities.WatchListActivity;
import Utilities.CommonUtils;
import Utilities.PropertyFileReader;
import io.appium.java_client.MobileElement;

public class WatchShowsTestAndroid extends BaseTestAndroid
{
	PropertyFileReader propertyReader = new PropertyFileReader();
	CommonUtils utils = new CommonUtils();
	SearchActivity search_activity;
	ShowActivity show_activity;
	WatchListActivity watchlist_activity;
	
	@Test
	public void watchEpisode()
	{
		System.out.println("Starting Watch Episode Test");
		boolean watched;
		
		try 
		{
			new LoginActivity(driver).signinProcess(propertyReader.getPropertyValue("username"), propertyReader.getPropertyValue("password"));
						
			watchlist_activity = new WatchListActivity(driver);
			watchlist_activity.navigateToShows();
			watchlist_activity.openShowEpisode(propertyReader.getPropertyValue("showEpisodeToWatch"));
			
			show_activity = new ShowActivity(driver);
			show_activity.watchEpisode();
			watched = show_activity.episodeRatingsVisible();
			
			assertTrue(watched);
			System.out.println("Watch Episode Test Finished");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void scrollToWatchedHistory()
	{
		System.out.println("Starting Scrolling to Watch History Test");
		try 
		{
			new LoginActivity(driver).signinProcess(propertyReader.getPropertyValue("username"), propertyReader.getPropertyValue("password"));
						
			watchlist_activity = new WatchListActivity(driver);
			watchlist_activity.navigateToShows();
			watchlist_activity.scrollWatchList("WATCHED HISTORY");
			
			MobileElement watchedHistoryTab = (MobileElement) driver.findElementById("com.tozelabs.tvshowtime:id/headerText");
			
			assertTrue(watchedHistoryTab.isDisplayed());
			System.out.println("Scrolling to Watch History Test Finished");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
}
