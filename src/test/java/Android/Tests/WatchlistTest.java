package Android.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import Android.Activities.LoginActivity;
import Android.Activities.SearchActivity;
import Android.Activities.ShowActivity;
import Android.Activities.WatchListActivity;
import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

//Feature 1 Android Test
public class WatchlistTest extends BaseTest
{
	PropertyFileReader propertyReader = new PropertyFileReader();
	CommonUtils utils = new CommonUtils();
	SearchActivity search_activity;
	ShowActivity show_activity;
	WatchListActivity watchlist_activity;
	
	String showName = "";
	String addResult = "";
	
	List<String> showsList;
	
	@Test
	public void addShow()
	{
		try 
		{
			new LoginActivity(driver).signinProcess(propertyReader.getPropertyValue("username"), propertyReader.getPropertyValue("password"));
			
			showName = propertyReader.getPropertyValue("searchShow");
			search_activity = new SearchActivity(driver);
			search_activity.searchShow(showName);
			search_activity.openShow();
			
			show_activity = new ShowActivity(driver);
			addResult = show_activity.addShow();
			show_activity.closeShow();
			
			watchlist_activity = new WatchListActivity(driver);
			watchlist_activity.navigateToShows();
			showsList = watchlist_activity.getShows();
			
			//assert added button had the text ADDED!
			//assert showlist contains show
			assertEquals("ADDED!", addResult);
			assertTrue(showsList.contains(showName.toLowerCase().trim()));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}	
	
	@Test
	public void addMultipleShows()
	{
		try 
		{
			new LoginActivity(driver).signinProcess(propertyReader.getPropertyValue("username"), propertyReader.getPropertyValue("password"));
			
			showName = propertyReader.getPropertyValue("multipleAndriodShowSearch");
			search_activity = new SearchActivity(driver);
			show_activity = new ShowActivity(driver);
			
			List<String> shows = utils.returnMultipleShowNames(showName);
			
			//Searching multiple shows and adds them to the watchlist
			for(String s : shows)
			{
				search_activity.searchShow(s);
				search_activity.openShow();
				
				show_activity = new ShowActivity(driver);
				addResult = show_activity.addShow();
				show_activity.closeShow();
			}
			
			watchlist_activity = new WatchListActivity(driver);
			watchlist_activity.navigateToShows();
			showsList = watchlist_activity.getShows();
			
			//assert added button had the text ADDED!
			//assert showlist contains show
			assertEquals("ADDED!", addResult);
			assertTrue(showsList.contains(showName.toLowerCase().trim()));
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}	

}
