package steps;

import static org.junit.Assert.assertTrue;

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
import Utilities.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WatchlistSteps 
{
	private WebDriver driver = null;
	TVTimeHomePage homePage;
	TVTimeLogin loginPage;
	TVTimeSearch searchPage;
	TVTimeWatchlist watchlistPage;
	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	String searchInput = "";
	
	@Before
	public void openBrowser() 
	{
		System.setProperty("browser", "firefox");	
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
		driver = WebDriverFactory.createWebDriver();
	}


	@After
    public void closeBrowser() 
	{
		if (driver != null)
		{
			driver.close();
		}
		searchInput = "";
	}
	
	
	@Given("the user is logs in")
	public void the_user_is_logged_in()
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
			//assertTrue(searchPage.getNumberOfResults() >= 0);
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
	
	
	
	@Then("the show sould be visible under his watchlist")
	public void the_show_should_be_visible_under_his_watchlist()
	{
		List<String> showsFound = new ArrayList<String>();
		watchlistPage = new TVTimeWatchlist(driver);
		
		showsFound = watchlistPage.visitWatchListPage(searchInput);
		
		assertTrue(showsFound.contains(searchInput.toLowerCase().trim()));
	}
	
	
	
	
	
	
	
	

}
