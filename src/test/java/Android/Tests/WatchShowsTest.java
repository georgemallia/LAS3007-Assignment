package Android.Tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import Android.Activities.LoginActivity;
import Android.Activities.SearchActivity;
import Android.Activities.ShowActivity;
import Android.Activities.WatchListActivity;
import Utilities.CommonUtils;
import Utilities.PropertyFileReader;
import io.appium.java_client.MobileElement;

public class WatchShowsTest extends BaseTest
{
	PropertyFileReader propertyReader = new PropertyFileReader();
	CommonUtils utils = new CommonUtils();
	SearchActivity search_activity;
	ShowActivity show_activity;
	WatchListActivity watchlist_activity;
	
	@Test
	public void addShow()
	{
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
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void scrollToWatchedHistory()
	{
		try 
		{
			new LoginActivity(driver).signinProcess(propertyReader.getPropertyValue("username"), propertyReader.getPropertyValue("password"));
						
			watchlist_activity = new WatchListActivity(driver);
			watchlist_activity.navigateToShows();
			watchlist_activity.scrollWatchList("WATCHED HISTORY");
			
			MobileElement watchedHistoryTab = (MobileElement) driver.findElementById("com.tozelabs.tvshowtime:id/headerText");
			
			assertTrue(watchedHistoryTab.isDisplayed());
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	

}
