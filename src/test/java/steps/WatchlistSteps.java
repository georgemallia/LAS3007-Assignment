package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

import PageFunctions.TVTimeHomePage;
import PageFunctions.TVTimeLogin;
import PageFunctions.TVTimeSearch;
import PageFunctions.TVTimeWatchlist;

import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WatchlistSteps 
{
	private WebDriver driver;
	
	TVTimeHomePage homePage;
	TVTimeLogin loginPage;
	TVTimeSearch searchPage;
	TVTimeWatchlist watchlistPage;
	
	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	String searchInput = "";
	String addedResult = "";
	
	List<String> showsList;
	
	public WatchlistSteps(TestContext tc)
	{
		System.out.println("watchlist steps class: getting tc.driver");
		this.driver = tc.getDriver();
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
	}

	@Given("the user logs in")
	public void the_user_logs_in()
	{
		loginPage = new TVTimeLogin(driver);		
		loginPage.visitPage();
		
		try
		{
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("password"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		homePage = loginPage.loginBtnClick();
		utils.waitForPageToSettleByXpath("//*[@id=\"container\"]/div[3]/div[2]/div", driver);
		utils.waitForPageToSettleById("home-link", driver);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	@And("the user search a show")
	public void the_user_search_a_show()
	{
		try 
		{
			searchInput = propFileReader.getPropertyValue("searchInput");
			searchPage = new TVTimeSearch(driver);
			searchPage.searchItem(searchInput);
			
			utils.waitForPageToSettleByXpath("//section[@id='shows-results']/h1", driver);
			utils.waitForPageToSettleById("search-results-container", driver);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
		 
	
	
	@When("he adds his show")
	public void he_adds_his_show()
	{
		searchPage.addShow(searchInput);
	}
	
	
	@When("he tries to re-add his show")
	public void he_tries_to_readd_his_show()
	{
		addedResult = searchPage.checkIfAdded(searchInput);
	}
	
	@When("he clicks remove show")
	public void he_clicks_remove_show()
	{
		searchPage.addShow(searchInput);
	}
	
	@When("the user searchs and adds multiple shows")
	public void the_user_searchs_and_adds_multiple_shows()
	{
		try
		{
			String shows = propFileReader.getPropertyValue("multipleShowSearch");
			showsList = utils.returnMultipleShowNames(shows);
			
			for(String s : showsList)
			{
				searchPage = new TVTimeSearch(driver);
				searchPage.searchItem(s);
				
				utils.waitForPageToSettleByXpath("//section[@id='shows-results']/h1", driver);
				
				searchPage.addShow(s);
			}
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	
	
	
	@Then("the show sould be visible under his watchlist")
	public void the_show_should_be_visible_under_his_watchlist()
	{
		List<String> showsFound = new ArrayList<String>();
		watchlistPage = new TVTimeWatchlist(driver);
		watchlistPage.visitWatchListPage();
		
		
		
		showsFound = watchlistPage.getShowList();
		
		System.out.println("the show sould be visible under his watchlist");
		for(String s : showsFound)
		{
			System.out.println(s);
		}
		
		assertTrue(showsFound.contains(searchInput.toLowerCase().trim()));
	}
	
	@Then("the show must already be marked added")
	public void the_show_must_already_be_marked_added()
	{
		assertEquals("Show Is Already Added", addedResult);
	}
	
	@Then("the show sould not be visible under his watchlist")
	public void the_show_should_not_be_visible_under_his_watchlist()
	{
		watchlistPage = new TVTimeWatchlist(driver);
		@SuppressWarnings("unused")
		String description = watchlistPage.visitWatchListPage();
		
		List<String> showslist = watchlistPage.getShowList();
		
		
		//TODO: REMOVE AFTER TESTING PURPOSES
		System.out.println("the show sould not be visible under his watchlist");
		for(String s : showslist)
		{
			System.out.println(s);
		}
		
		assertFalse(showslist.contains(searchInput));
	}
	
	@Then("the shows sould be visible under his watchlist")
	public void the_shows_should_be_visible_under_his_watchlist()
	{
		List<String> showsFound = new ArrayList<String>();
		watchlistPage = new TVTimeWatchlist(driver);
		watchlistPage.visitWatchListPage();
		showsFound = watchlistPage.getShowList();
		
		System.out.println("the shows sould be visible under his watchlist");
		for(String s : showsFound)
		{
			System.out.println(s);
		}
		
		assertTrue(showsFound.containsAll(showsList));
	}
	
	

}
