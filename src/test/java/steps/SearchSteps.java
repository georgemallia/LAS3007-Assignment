package steps;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import PageFunctions.TVTimeHomePage;
import PageFunctions.TVTimeLogin;
import PageFunctions.TVTimeSearch;

import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps 
{
	private WebDriver driver;
	TVTimeHomePage homePage;
	TVTimeLogin loginPage;
	TVTimeSearch searchPage;
	PropertyFileReader propFileReader;
	CommonUtils utils;
		
	public SearchSteps(TestContext tc)
	{
		System.out.println("Search steps class: getting tc.driver");
		this.driver = tc.getDriver();
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
	}

	@Given("that the user is logged in")
	public void that_the_user_is_logged_in()
	{
		loginPage = new TVTimeLogin(driver);		
		loginPage.visitPage();
		
		try
		{
			utils.waitForPageToSettleByCSS(".pages__HomePageView-sc-6kvjaa-0", driver);
			loginPage.loginProcess(propFileReader.getPropertyValue("username"), propFileReader.getPropertyValue("password"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		homePage = loginPage.loginBtnClick();
		try 
		{
			utils.waitForPageToSettleByXpath("//*[@id=\"container\"]/div[3]/div[2]/div", driver);
		} 
		catch (NoSuchElementException e) 
		{
			System.out.println("Could Not find side nav. /n Attempting to find home BTN");
			utils.waitForPageToSettleById("home-link", driver);
		}
	}
	
	

	@When("I type the full text in the search field")
	public void I_type_the_full_text_in_the_search_field()
	{
		try 
		{
			searchPage = new TVTimeSearch(driver);
			searchPage.searchItem(propFileReader.getPropertyValue("searchInput"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@When("I type half the text in the search field")
	public void I_type_half_the_text_in_the_search_field()
	{
		String search = "";
		try 
		{
			searchPage = new TVTimeSearch(driver);
			search = propFileReader.getPropertyValue("searchInput");
			searchPage.searchItem(search.substring(0, search.length()/2));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	@When("I type unreadable text in the search field")
	public void I_type_unreadable_text_in_the_search_field()
	{
		try 
		{
			searchPage = new TVTimeSearch(driver);
			searchPage.searchItem(propFileReader.getPropertyValue("unreadableSearchInput"));
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	@Then("results should be displayed")
	public void results_should_be_displayed()
	{
		utils.waitForPageToSettleById("search-results-container", driver);
		utils.waitForPageToSettleByXpath("//section[@id='shows-results']/h1", driver);
		assertTrue(searchPage.resultsTitle.isDisplayed());
		assertTrue(searchPage.getNumberOfResults() >= 0);
	}
	
	@Then("no results should be displayed")
	public void no_results_should_be_displayed()
	{
		utils.waitForPageToSettleByXpath("//div[@id='search-results-container']/div", driver);
		assertTrue(searchPage.noresultsTab.isDisplayed());
		assertTrue(searchPage.getNumberOfResults() == 0);
	}
}
