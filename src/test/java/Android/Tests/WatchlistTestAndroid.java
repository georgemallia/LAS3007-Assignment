package Android.Tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import Android.Activities.LoginActivity;
import Android.Activities.SearchActivity;
import Android.Activities.ShowActivity;
import Android.Activities.WatchListActivity;
import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

@TestMethodOrder(OrderAnnotation.class)
public class WatchlistTestAndroid extends BaseTestAndroid
{
	PropertyFileReader propertyReader = new PropertyFileReader();
	CommonUtils utils = new CommonUtils();
	SearchActivity search_activity;
	ShowActivity show_activity;
	WatchListActivity watchlist_activity;
	
	boolean allAdded;
	
	String showName = "";
	String addResult = "";
	
	List<String> showsList;
	List<String> addResults;
	
	@Test
	@Order(1)
	public void addShow()
	{
		System.out.println("Starting Add Show Test");
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
			System.out.println("Add Show Test Finished");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}	
	
	@Test
	@Order(2)
	public void addMultipleShows()
	{
		System.out.println("Starting Add Multiple Shows Test");
		addResults = new ArrayList<String>();
		
		try 
		{
			new LoginActivity(driver).signinProcess(propertyReader.getPropertyValue("username"), propertyReader.getPropertyValue("password"));
			
			showName = propertyReader.getPropertyValue("multipleAndriodShowSearch");
			List<String> shows = utils.returnMultipleShowNames(showName);
			
			//Searching multiple shows and adds them to the watchlist
			for(String s : shows)
			{
				show_activity = new ShowActivity(driver);
				search_activity = new SearchActivity(driver);
				
				search_activity.searchShow(s);
				search_activity.openShow();
				
				addResults.add(show_activity.addShow());
				show_activity.closeShow();
			}
			
			watchlist_activity = new WatchListActivity(driver);
			watchlist_activity.navigateToShows();
			showsList = watchlist_activity.getShows();
			
			//assert added button had the text ADDED!
			//assert showlist contains show
			
			if(addResults.stream().allMatch(s -> s.equals("ADDED!")))
			{
				allAdded = true;
			}
			
			assertTrue(allAdded);
			assertTrue(showsList.stream().anyMatch(shows::contains));
			System.out.println("Add Multiple Shows Test finished");
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}	
}
