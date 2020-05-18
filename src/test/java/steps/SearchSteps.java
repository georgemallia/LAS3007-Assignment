package steps;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;

import PageFunctions.TVTimeHomePage;
import PageFunctions.TVTimeLogin;
import PageFunctions.TVTimeSearch;
import Utilities.PropertyFileReader;
import Utilities.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SearchSteps 
{
	private WebDriver driver = null;
	TVTimeHomePage homePage;
	TVTimeLogin loginPage;
	TVTimeSearch searchPage;
	PropertyFileReader propFileReader;
	
	@BeforeAll
	public void setup() throws Exception
	{
		System.setProperty("browser", "firefox");	
		propFileReader = new PropertyFileReader();
	}
	
	@Before
	public void openBrowser() {
		driver = WebDriverFactory.createWebDriver();
	}

	@After
	public void closeBrowser() {
		if (driver != null)
			driver.close();
	}
	
	@Given("that the user is logged in")
	public void that_the_user_is_logged_in()
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
		homePage.waitForPageToSettle("//*[@id=\"container\"]/div[3]/div[2]/div");
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
		homePage.waitForPageToSettle("//section[@id='shows-results']/h1");
		assertTrue(searchPage.resultsTitle.isDisplayed());
		assertTrue(searchPage.getNumberOfResults() >= 0);
	}
	
	@Then("no results should be displayed")
	public void no_results_should_be_displayed()
	{
		homePage.waitForPageToSettle("//div[@id='search-results-container']/div");
		assertTrue(searchPage.noresultsTab.isDisplayed());
		assertTrue(searchPage.getNumberOfResults() == 0);
	}

}
