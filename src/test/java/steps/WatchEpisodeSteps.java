package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import PageFunctions.TVTimeHomePage;
import PageFunctions.TVTimeLogin;
import PageFunctions.TVTimeSearch;
import PageFunctions.TVTimeShow;
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

public class WatchEpisodeSteps
{

	private WebDriver driver = null;
	
	TVTimeHomePage homePage;
	TVTimeLogin loginPage;
	TVTimeSearch searchPage;
	TVTimeWatchlist watchlistPage;
	TVTimeShow showPage;
	
	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	String searchInput = "";
	String result = "";
	String watchNextResult = "";
	
	@Before
	public void openBrowser() 
	{
		System.out.print("Creating Driver");
		System.setProperty("browser", "firefox");	
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
		driver = WebDriverFactory.createWebDriver();
		PageFactory.initElements(driver, this);
	}


	@After
    public void closeBrowser() 
	{
		if (driver != null)
		{
			driver.close();
		}
	}
	
	
	@Given("that the user is logs in")
	public void that_the_user_is_logs_in()
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
	
	
	@And("the user adds a show to his watchlist")
	public void the_user_adds_a_show_to_his_watchlist()
	{
		try 
		{
			searchInput = propFileReader.getPropertyValue("searchInput");
			searchPage = new TVTimeSearch(driver);
			searchPage.searchItem(searchInput);
			
			utils.waitForPageToSettleByXpath("//section[@id='shows-results']/h1", driver);
			
			searchPage.addShow(searchInput);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@And("he opens the next episode to watch")
	public void he_opens_the_next_episode_to_watch()
	{
		watchlistPage = new TVTimeWatchlist(driver);
		watchlistPage.visitWatchListPage();
		
		showPage = new TVTimeShow(driver);
		showPage.openShow(searchInput);
	}
	
	@And("the show will be listed under watch next tab")
	public void the_show_will_be_listed_under_watch_next_tab()
	{
		watchlistPage.visitWatchListPage();
		watchNextResult = watchlistPage.checkShowsUnderWatchNext(searchInput);
		assertEquals("Show is under Watch Next", watchNextResult);
	}
	
	@When("he marks the episode as watched")
	public void he_marks_the_episode_as_watched()
	{
		utils.waitForPageToSettleByXpath("//div[@id='top-banner']/div/div[5]", driver);
		result = showPage.clickWatched();
	}
	
	@Then("the watched button text is changed to watched")
	public void the_watched_button_text_is_changed_to_watched()
	{
		WebElement watchedBtn = driver.findElement(By.cssSelector(".watched-label"));
		
		assertEquals("Show episode marked as watched", result);
		System.out.println("watchedBtn.getText: " + watchedBtn.getText());
		assertEquals("WATCHED!", watchedBtn.getText());
	}
	
	
	
}
