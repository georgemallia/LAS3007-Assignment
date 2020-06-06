package Android.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

public class WatchShowsTest extends BaseTest
{
	PropertyFileReader propertyReader = new PropertyFileReader();
	CommonUtils utils = new CommonUtils();
	SearchActivity search_activity;
	ShowActivity show_activity;
	WatchListActivity watchlist_activity;
	
	@RepeatedTest(3)
	@Test
	public void addShow()
	{
		boolean watched;
		
		try 
		{
			new LoginActivity(driver).signinProcess(propertyReader.getPropertyValue("username"), propertyReader.getPropertyValue("password"));
						
			watchlist_activity = new WatchListActivity(driver);
			watchlist_activity.navigateToShows();
			
			//TODO: Implement show to watch functionality
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
	

}
