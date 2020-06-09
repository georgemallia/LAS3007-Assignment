package steps;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.RepeatedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import PageFunctions.TVTimeHomePage;
import PageFunctions.TVTimeLogin;
import PageFunctions.TVTimeSearch;
import PageFunctions.TVTimeShow;
import PageFunctions.TVTimeWatchlist;

import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class WatchEpisodeSteps
{
	private WebDriver driver;
	
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
	String previousSeasonResult = "";
	
	public WatchEpisodeSteps(TestContext tc)
	{
		System.out.println("watch episode steps class: getting tc.driver");
		this.driver = tc.getDriver();
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
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
		utils.waitForPageToSettleById("home-link", driver);
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	
	
	@And("the user adds a show to his watchlist")
	public void the_user_adds_a_show_to_his_watchlist()
	{
		try 
		{
			searchInput = propFileReader.getPropertyValue("showInput");
			searchPage = new TVTimeSearch(driver);
			searchPage.searchItem(searchInput);
			
			utils.waitForPageToSettleByXpath("//section[@id='shows-results']/h1", driver);
			//utils.waitForPageToSettleById("search-results-container", driver);
			
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
		try 
		{
			searchInput = propFileReader.getPropertyValue("showInput");
			watchlistPage = new TVTimeWatchlist(driver);
			watchlistPage.visitWatchListPage();
			
			showPage = new TVTimeShow(driver);
			showPage.openShow(searchInput);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	@And("the show will be listed under watch next tab")
	public void the_show_will_be_listed_under_watch_next_tab()
	{
		watchlistPage.visitWatchListPage();
		watchNextResult = watchlistPage.checkShowsUnderWatchNext(searchInput);
		assertEquals("Show is under Watch Next", watchNextResult);
	}
	
	
	@And("the user opens his watchlist")
	public void the_user_opens_his_watchlist()
	{
		watchlistPage = new TVTimeWatchlist(driver);
		watchlistPage.visitWatchListPage();
	}
		
	
	@And("he marks the episode as unwatched")
	public void he_marks_the_episode_as_unwatched()
	{
		utils.waitForPageToSettleByXpath("//div[@id='top-banner']/div/div[5]", driver);
		result = showPage.clickUnwatched();
	}
	
	@And("he opens a show")
	public void he_opens_a_show()
	{
		try
		{
			searchInput = propFileReader.getPropertyValue("showInput");
			showPage = new TVTimeShow(driver);
			showPage.openShowDescription(searchInput);
			
			utils.waitForPageToSettleByCSS(".info-zone", driver);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}

	@And("he clicks the season unwatch button")
	public void he_clicks_the_season_unwatch_button()
	{
		showPage.unWatchSeason();
	}

	@When("he marks the episode as watched")
	public void he_marks_the_episode_as_watched()
	{
		utils.waitForPageToSettleByXpath("//div[@id='top-banner']/div/div[5]", driver);
		result = showPage.clickWatched();
	}

	@When("he selects previous episode")
	public void he_selects_previous_episode()
	{
		utils.waitForPageToSettleByXpath("//div[@id='top-banner']/div/div[5]", driver);
		showPage.getPreviousEpisode();
	}
	
	@When("he clicks the seen button near the season name")
	public void he_click_the_seen_button_near_the_season_name() 
	{
		showPage.clickWatchSeasons();
	}
	
	@When("he selects the previous season")
	public void he_selects_the_previous_season()
	{
		previousSeasonResult = showPage.selectPreviousSeason();
		System.out.println("Previous Season Result: " + previousSeasonResult);
	}
	
	
	
	@Then("the watched button text is changed to watched")
	public void the_watched_button_text_is_changed_to_watched()
	{
		WebElement watchedBtn = driver.findElement(By.cssSelector(".watched-label"));
		
		assertEquals("Show episode marked as watched", result);
		System.out.println("watchedBtn.getText: " + watchedBtn.getText());
		assertEquals("WATCHED!", watchedBtn.getText());
	}
	 
	@Then("the watched button text is changed to unwatched")
	public void the_watched_button_text_is_changed_to_unwatched()
	{
		WebElement watchedBtn = driver.findElement(By.cssSelector(".not-watched-label"));
		
		assertEquals("Show episode marked as unwatched", result);
		System.out.println("watchedBtn.getText: " + watchedBtn.getText());
		assertEquals("WATCHED?", watchedBtn.getText());	
	}
	
	@Then("the whole season should be marked as watched")
	public void the_whole_season_should_be_marked_as_watched()
	{
		String result = showPage.checkSeasonWatched();
		assertEquals("Season has been marked as watched", result);
	}
	
	@Then("the whole season should be marked as unwatched")
	public void the_whole_season_should_be_marked_as_unwatched()
	{
		String result = showPage.checkSeasonUnWatched();
		assertEquals("Season is not watched", result);
	}
	
}
