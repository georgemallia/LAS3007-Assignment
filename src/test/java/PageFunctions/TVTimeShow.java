package PageFunctions;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Utilities.CommonUtils;
import Utilities.PropertyFileReader;

public class TVTimeShow
{
	protected WebDriver driver;
	PropertyFileReader propFileReader;
	CommonUtils utils;
	
	@FindBy(css=".not-watched-label")
	public WebElement watchedBtn;
	
	@FindBy(css=".watched-label")
	public WebElement unwatchBtn;
	
	@FindBy(css=".previous")
	private WebElement previousBtn;

	@FindBy(linkText="Mark season as watched")
	private WebElement watchSeasonBtn;
	
	@FindBy(xpath="/html/body/div[3]/div[3]/div/div[2]/div/div/div[9]/div/div[1]/div[1]/div[2]/div/div[1]/div")
	private WebElement unWatchSeasonBtn;
		
	public TVTimeShow(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
		propFileReader = new PropertyFileReader();
		utils = new CommonUtils();
	}
	
	//This method opens the show's next episode to watch
	public void openShow(String showName)
	{
		WebElement showPoster;
		WebElement showList = driver.findElement(By.xpath("//*[@id=\"to-watch\"]/ul")); 
		List<WebElement> li_All = showList.findElements(By.tagName("li"));
	    System.out.println(li_All.size());
	    
	    for(int i = 1; i <= li_All.size(); i++)
	    {
	    	try 
	    	{
				WebElement show = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section/ul/li[" + i + "]/div[3]/a"));
				System.out.println("Element Text: " + show.getText().toLowerCase().trim());
				System.out.println("Search Text: " + showName.toLowerCase().trim());
				String showCmp = show.getText().toLowerCase().trim();
				if(showCmp.equalsIgnoreCase(showName))
				{
					showPoster = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section/ul/li[" + i + "]/div[2]"));
					showPoster.click();
					System.out.println("Opening Show episode");
					break;
				}
			} 
	    	catch (NoSuchElementException e) 
	    	{
	    		break;
			}
	    }
	}
	
	
	public void openShowDescription(String showName)
	{
		System.out.println("Attempting to open show description");
		WebElement showList = driver.findElement(By.xpath("//*[@id=\"to-watch\"]/ul")); 
		List<WebElement> li_All = showList.findElements(By.tagName("li"));
	    System.out.println(li_All.size());
	    
	    for(int i = 1; i <= li_All.size(); i++)
	    {
	    	try 
	    	{
				WebElement show = driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section/ul/li[" + i + "]/div[3]/a"));
				System.out.println("Element Text: " + show.getText().toLowerCase().trim());
				System.out.println("Search Text: " + showName.toLowerCase().trim());
				String showCmp = show.getText().toLowerCase().trim();
				if(showCmp.equalsIgnoreCase(showName))
				{
					utils.waitForElementToBeClickableByXpath("/html/body/div[3]/div[3]/div/div[2]/div/div/section/ul/li[" + i + "]/div[3]/a", driver);
				
					System.out.println("Attempting to open show");
					show.click();
					System.out.println("Show description opening..");
				
					break;
				}
			} 
	    	catch (NoSuchElementException e) 
	    	{
	    		break;
			}
	    }
	}
	
	
	
	public String clickWatched()
	{
		utils.waitForPageToSettleByCSS(".optanon-alert-box-wrapper", driver);
		System.out.println("Marking episode watched");
		System.out.println("Watch BTN: "  + watchedBtn.getAttribute("class"));
		if(watchedBtn.getAttribute("class").equals("not-watched-label"))
		{
			watchedBtn.click();
			System.out.println("episode marked as watched");
			return "Show episode marked as watched";
		}
		else if(watchedBtn.getAttribute("class").equals("watched-label"))
		{
			return "Show episode already marked as watched";
		}
		else
		{
			//default
			return "";
		}
	}
	
	public String clickUnwatched()
	{
		utils.waitForPageToSettleByCSS(".optanon-alert-box-wrapper", driver);
		System.out.println("Marking episode unwatch");
		System.out.println("Watch BTN: "  + unwatchBtn.getAttribute("class"));
		if(unwatchBtn.getAttribute("class").trim().equals("watched-label"))
		{
			unwatchBtn.click();
			System.out.println("Episode unwatched");
			return "Show episode marked as unwatched";
		}
		else if(unwatchBtn.getAttribute("class").trim().equals("not-watched-label"))
		{
			return "Show episode already marked as unwatched";
		}
		else
		{
			//default
			return "";
		}
	}
	
	public void getPreviousEpisode()
	{
		System.out.println("Getting Previous Episode");
		utils.waitForElementToBeClickableByCss(".previous", driver);
		previousBtn.click();
		System.out.println("Previous Episode Btn Clicked");
	}
	
	
	public void clickWatchSeasons()
	{
		System.out.println("Attempting to Watch season");
		utils.waitForPageToSettleByCSS(".optanon-alert-box-wrapper", driver);
		utils.waitForElementToBeClickableByLinkText("Mark season as watched", driver);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,400)");
		
		watchSeasonBtn.click();
		System.out.println("Watch season clicked");
	}
	
	public void unWatchSeason()
	{
		System.out.println("Season UnWatch Attempt");
		utils.waitForElementToBeClickableByCss(".watched", driver);
		
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,400)");
		
		unWatchSeasonBtn.click();
		System.out.println("unWatch Clicked");
	}
	
	public String checkSeasonWatched()
	{
		WebElement showWatched = driver.findElement(By.cssSelector(".watched"));
		
		System.out.println(showWatched.getAttribute("class"));
		
		//watched
		if(showWatched.getAttribute("class").equals("season-watched-btn watched-btn btn  watched"))
		{
			return "Season has been marked as watched";
		}
		else
		{
			//default
			return "";
		}	
	}
	
	
	
	public String checkSeasonUnWatched()
	{
		System.out.println(watchSeasonBtn.getAttribute("class"));
		//unwatched
		if(watchSeasonBtn.getAttribute("class").equals("season-watched-btn watched-btn btn"))
		{
			return "Season is not watched";
		}
		//default
		else
		{
			return "";
		}
	}
	
	
	
	public String selectPreviousSeason()
	{
		utils.waitForPageToSettleByCSS(".optanon-alert-box-wrapper", driver);
		System.out.println("Attempting to Select Previous season");
		WebElement currentSelectedSeason = driver.findElement(By.id("dSeasons"));
		
		utils.waitForPageToSettleById("dSeasons", driver);
		currentSelectedSeason.click();
		
		utils.waitForPageToSettleByCSS(".open > .dropdown-menu", driver);
		WebElement seasonList = driver.findElement(By.cssSelector(".open > .dropdown-menu"));
		List<WebElement> li_All = seasonList.findElements(By.tagName("li"));
	    System.out.println(li_All.size());

	    for(WebElement li : li_All)
	    {
	    	System.out.println("Current LI Text: " + li.getText());
	    	System.out.println("currentselectedSeason Text: " +  currentSelectedSeason.getText());
	    		    	
	    	if(li.getText().equals(currentSelectedSeason.getText()))
	    	{
	    		int pos = li_All.indexOf(li);
	    		
	    		if(pos != 0)
	    		{
	    			li_All.get(pos-1).click();
	    			System.out.println("Previous season Btn pressed");
	    			return "Previous Season Selected";
	    		}
	    		else
	    		{
	    			System.out.println("Failed to press Watch season Btn");
	    			return "Could Not Select Previous Season. First Season Currently Selected";
	    		}
	    	}
	    }
		//default
	    return "";
	}
}
